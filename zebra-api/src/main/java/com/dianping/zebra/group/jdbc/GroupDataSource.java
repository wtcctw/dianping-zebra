package com.dianping.zebra.group.jdbc;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.*;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.datasources.LoadBalancedDataSource;
import com.dianping.zebra.group.datasources.SingleDataSourceManagerFactory;
import com.dianping.zebra.group.exception.DalException;
import com.dianping.zebra.group.filter.FilterManagerFactory;
import com.dianping.zebra.group.filter.JdbcContext;
import com.dianping.zebra.group.filter.delegate.FilterAction;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
import com.dianping.zebra.group.monitor.GroupDataSourceMBean;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.dianping.zebra.group.router.CustomizedReadWriteStrategy;
import com.dianping.zebra.group.router.CustomizedReadWriteStrategyWrapper;
import com.dianping.zebra.group.router.RouterType;
import com.dianping.zebra.group.util.AtomicRefresh;
import com.dianping.zebra.group.util.JDBCExceptionUtils;
import com.dianping.zebra.group.util.SmoothReload;
import com.dianping.zebra.group.util.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

public class GroupDataSource extends AbstractDataSource implements GroupDataSourceMBean {

	private final Logger logger = LogManager.getLogger(this.getClass());

	private AtomicRefresh atomicRefresh = new AtomicRefresh();

	private DataSourceConfig c3p0Config = new DataSourceConfig();

	private CustomizedReadWriteStrategy customizedReadWriteStrategy;

	private DataSourceConfigManager dataSourceConfigManager;

	private String filterStr;

	private GroupDataSourceConfig groupConfig = new GroupDataSourceConfig();

	private volatile boolean init = false;

	private String jdbcRef;

	private ConfigService customerConfigService;

	private String jdbcUrlExtra;

	private LoadBalancedDataSource readDataSource;

	private RouterType routerType = RouterType.ROUND_ROBIN;

	private SystemConfigManager systemConfigManager;

	private FailOverDataSource writeDataSource;

	public GroupDataSource() {
	}

	public GroupDataSource(String jdbcRef) {
		this.jdbcRef = jdbcRef;
	}

	public void setConfigService(ConfigService configService) {
		this.customerConfigService = configService;
	}

	protected GroupDataSourceConfig buildGroupConfig() {
		GroupDataSourceConfig newGroupConfig = this.dataSourceConfigManager.getGroupDataSourceConfig();

		return buildGroupConfig(newGroupConfig);
	}

	protected GroupDataSourceConfig buildGroupConfig(GroupDataSourceConfig newGroupConfig) {
		buildGroupConfigMergeProperties(newGroupConfig);
		buildGroupConfigJdbcUrlExtra(newGroupConfig);
		buildGroupConfigMergeC3P0Properties(newGroupConfig);
		buildGroupConfigFilter(newGroupConfig);
		return newGroupConfig;
	}

	protected void buildGroupConfigFilter(GroupDataSourceConfig newGroupConfig) {
		String remoteConfig = newGroupConfig.getFilters();
		String beanConfig = this.filterStr;
		Set<String> result = new HashSet<String>();

		if (!StringUtils.isBlank(remoteConfig)) {
			String[] remoteFilters = remoteConfig.split(",");
			result.addAll(Arrays.asList(remoteFilters));
		}

		if (!StringUtils.isBlank(beanConfig)) {
			String[] beanFilters = beanConfig.split(",");
			for (String beanFilter : beanFilters) {
				if (beanFilter.startsWith("!") && beanFilter.length() > 1) {
					result.remove(beanFilter.substring(1));
				} else {
					result.add(beanFilter);
				}
			}
		}
		newGroupConfig.setFilters(StringUtils.joinCollectionToString(result, ","));
	}

	protected void buildGroupConfigJdbcUrlExtra(GroupDataSourceConfig newGroupConfig) {
		if (!StringUtils.isBlank(jdbcUrlExtra)) {
			for (DataSourceConfig cfg : newGroupConfig.getDataSourceConfigs().values()) {
				String[] urlInfo = cfg.getJdbcUrl().split("\\?");
				String url = urlInfo[0];
				String param = urlInfo.length > 1 ? urlInfo[1] : null;

				if (StringUtils.isBlank(param) && StringUtils.isBlank(jdbcUrlExtra)) {
					continue;
				}

				Map<String, String> map = new HashMap<String, String>();
				StringUtils.splitStringToMap(map, param);
				StringUtils.splitStringToMap(map, jdbcUrlExtra);

				cfg.setJdbcUrl(String.format("%s?%s", url, StringUtils.joinMapToString(map)));
			}
		}
	}

	protected void buildGroupConfigMergeC3P0Properties(GroupDataSourceConfig newGroupConfig) {
		for (Entry<String, DataSourceConfig> entry : newGroupConfig.getDataSourceConfigs().entrySet()) {
			DataSourceConfig config = entry.getValue();

			if (config.getDriverClass() == null || config.getDriverClass().length() <= 0) {
				// in case that DBA has not give default value to driverClass.
				config.setDriverClass(c3p0Config.getDriverClass());
			}

			for (Any property : c3p0Config.getProperties()) {
				String key = property.getName();
				String value = property.getValue();
				Any any = findAny(config.getProperties(), key);

				if (any != null) {
					any.setValue(value);
				} else {
					Any any1 = new Any();
					any1.setName(key);
					any1.setValue(value);
					config.getProperties().add(any1);
				}
			}
		}
	}

	protected void buildGroupConfigMergeProperties(GroupDataSourceConfig newGroupConfig) {
		newGroupConfig.setRouterStrategy(this.groupConfig.getRouterStrategy());
		newGroupConfig.setTransactionForceWrite(this.groupConfig.getTransactionForceWrite());
		newGroupConfig.setWriteFirst(this.groupConfig.getWriteFirst());
	}

	public void close() throws SQLException {
		this.close(this.readDataSource, this.writeDataSource);
	}

	private void close(final LoadBalancedDataSource read, final FailOverDataSource write) throws SQLException {
		this.filter
			.closeGroupDataSource(this.context.clone(), this, new FilterActionWithSQLExcption<GroupDataSource>() {
				@Override
				public void execute(GroupDataSource source) throws SQLException {

					List<SQLException> exps = new ArrayList<SQLException>();

					try {
						if (read != null) {
							read.close();
						}
					} catch (SQLException e) {
						exps.add(e);
					}

					try {
						if (write != null) {
							write.close();
						}
					} catch (SQLException e) {
						exps.add(e);
					}

					JDBCExceptionUtils.throwSQLExceptionIfNeeded(exps);
				}
			});
	}

	private Any findAny(List<Any> all, String name) {
		for (Any any : all) {
			if (any.getName().equals(name)) {
				return any;
			}
		}

		return null;
	}

	@Override
	public GroupDataSourceConfig getConfig() {
		return groupConfig;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getConnection(null, null);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return filter.getGroupConnection(this.context.clone(), this,
			new FilterFunctionWithSQLException<GroupDataSource, GroupConnection>() {
				@Override
				public GroupConnection execute(GroupDataSource source) throws SQLException {
					return new GroupConnection(source.readDataSource, source.writeDataSource,
						source.customizedReadWriteStrategy, source.routerType, source.context.clone(),
						source.filter);
				}
			});
	}

	private Map<String, DataSourceConfig> getFailoverConfig(Map<String, DataSourceConfig> configs) {
		Map<String, DataSourceConfig> failoverConfigMap = new HashMap<String, DataSourceConfig>();

		for (Entry<String, DataSourceConfig> entry : configs.entrySet()) {
			String key = entry.getKey();
			DataSourceConfig config = entry.getValue();

			if (config.isActive() && config.isCanWrite()) {
				failoverConfigMap.put(key, config);
			}
		}

		return failoverConfigMap;
	}

	public String getJdbcUrlExtra() {
		return jdbcUrlExtra;
	}

	public void setJdbcUrlExtra(String jdbcUrlExtra) {
		this.jdbcUrlExtra = jdbcUrlExtra;
	}

	private Map<String, DataSourceConfig> getLoadBalancedConfig(Map<String, DataSourceConfig> configs) {
		Map<String, DataSourceConfig> loadBalancedConfigMap = new HashMap<String, DataSourceConfig>();

		for (Entry<String, DataSourceConfig> entry : configs.entrySet()) {
			String key = entry.getKey();
			DataSourceConfig config = entry.getValue();

			if (config.isActive() && config.isCanRead()) {
				loadBalancedConfigMap.put(key, config);
			}
		}

		return loadBalancedConfigMap;
	}

	private long getMaxWarmupTime() {
		long max = 0l;
		for (DataSourceConfig config : groupConfig.getDataSourceConfigs().values()) {
			if (config.getWarmupTime() > max) {
				max = config.getWarmupTime();
			}
		}
		return max;
	}

	@Override
	public synchronized Map<String, SingleDataSourceMBean> getReaderSingleDataSourceMBean() {
		return this.readDataSource.getCurrentDataSourceMBean();
	}

	@Override
	public synchronized SingleDataSourceMBean getWriteSingleDataSourceMBean() {
		return this.writeDataSource.getCurrentDataSourceMBean();
	}

	public void init() {
		if (StringUtils.isBlank(jdbcRef)) {
			throw new DalException("jdbcRef cannot be empty");
		}

		this.dataSourceConfigManager = customerConfigService != null ?
			DataSourceConfigManagerFactory.getConfigManager(customerConfigService, jdbcRef) :
			DataSourceConfigManagerFactory.getConfigManager(configManagerType, jdbcRef);
		this.dataSourceConfigManager.addListerner(new GroupDataSourceConfigChangedListener());
		this.groupConfig = buildGroupConfig();
		this.systemConfigManager = SystemConfigManagerFactory.getConfigManger(configManagerType,
			Constants.DEFAULT_SYSTEM_RESOURCE_ID);

		SingleDataSourceManagerFactory.getDataSourceManager().init();

		this.initFilters();

		this.filter.initGroupDataSource(this.context.clone(), this, new FilterAction<GroupDataSource>() {
			@Override
			public void execute(GroupDataSource source) {
				source.initDataSources();
				source.loadCustomizedReadWriteStrategy();
			}
		});

		this.init = true;
		logger.info(String.format("GroupDataSource(%s) successfully initialized.", jdbcRef));
	}

	private void initDataSources() {
		try {
			this.readDataSource = new LoadBalancedDataSource(getLoadBalancedConfig(groupConfig.getDataSourceConfigs()),
				this.context.clone(), this.filter, systemConfigManager.getSystemConfig().getRetryTimes());
			this.readDataSource.init();
			this.writeDataSource = new FailOverDataSource(getFailoverConfig(groupConfig.getDataSourceConfigs()),
				this.context.clone(), this.filter);
			this.writeDataSource.init();
		} catch (RuntimeException e) {
			try {
				this.close(this.readDataSource, this.writeDataSource);
			} catch (SQLException ignore) {
			}

			throw new DalException("fail to initialize group dataSource [" + jdbcRef + "]", e);
		}
	}

	private void initFilters() {
		this.context = new JdbcContext();
		this.context.setDataSourceId(this.jdbcRef);
		this.context.setDataSource(this);
		this.context.setDataSourceProperties(this);
		this.filter = FilterManagerFactory.getFilterManager().loadFilter(this.groupConfig.getFilters());
	}

	private void loadCustomizedReadWriteStrategy() {
		ServiceLoader<CustomizedReadWriteStrategy> strategies = ServiceLoader.load(CustomizedReadWriteStrategy.class);
		CustomizedReadWriteStrategyWrapper wraper = new CustomizedReadWriteStrategyWrapper();

		if (strategies != null) {
			for (CustomizedReadWriteStrategy strategy : strategies) {
				if (strategy != null) {
					wraper.addStrategy(strategy);
				}
			}
		}

		if (wraper != null) {
			customizedReadWriteStrategy = wraper;
		}
	}

	private void refresh(String propertyToChange) {
		if (this.init) {
			final GroupDataSourceConfig newGroupConfig = buildGroupConfig();

			if (!groupConfig.toString().equals(newGroupConfig.toString())) {
				filter.refreshGroupDataSource(this.context.clone(), propertyToChange, this,
					new FilterAction<GroupDataSource>() {
						@Override
						public void execute(GroupDataSource source) {
							refreshIntenal(newGroupConfig);
						}
					});
			}
		}
	}

	private void refreshIntenal(GroupDataSourceConfig groupDataSourceConfig) {
		logger.info(String.format("start to refresh the dataSources(%s)...", jdbcRef));

		LoadBalancedDataSource newReadDataSource = null;
		FailOverDataSource newWriteDataSource = null;
		boolean preparedSwitch = false;
		try {
			newReadDataSource = new LoadBalancedDataSource(
				getLoadBalancedConfig(groupDataSourceConfig.getDataSourceConfigs()), this.context.clone(),
				this.filter,
				systemConfigManager.getSystemConfig().getRetryTimes());
			newReadDataSource.init();
			newWriteDataSource = new FailOverDataSource(getFailoverConfig(groupDataSourceConfig.getDataSourceConfigs()),
				this.context.clone(), this.filter);
			newWriteDataSource.init(false);

			preparedSwitch = true;
		} catch (Exception e) {
			try {
				close(newReadDataSource, newWriteDataSource);
			} catch (Exception ignore) {
			}
		}

		if (preparedSwitch) {
			LoadBalancedDataSource tmpReadDataSource = this.readDataSource;
			FailOverDataSource tmpWriteDataSource = this.writeDataSource;

			synchronized (this) {
				// switch
				this.readDataSource = newReadDataSource;
				this.writeDataSource = newWriteDataSource;
			}

			// destroy old dataSources
			try {
				this.close(tmpReadDataSource, tmpWriteDataSource);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			logger.info(String.format("refresh the dataSources(%s) successfully!", jdbcRef));
		} else {
			logger.warn(String.format("fail to refresh the dataSource(%s)", jdbcRef));
		}

		// switch config
		groupConfig = groupDataSourceConfig;
	}

	private void refreshUserAndPassword() {
		atomicRefresh.reset();

		SmoothReload sr = new SmoothReload(getMaxWarmupTime());
		sr.waitForReload();

		refresh("user&password");
	}

	public synchronized void setAcquireIncrement(int acquireIncrement) {
		setProperty("acquireIncrement", String.valueOf(acquireIncrement));
	}

	public synchronized void setAcquireRetryAttempts(int acquireRetryAttempts) {
		setProperty("acquireRetryAttempts", String.valueOf(acquireRetryAttempts));
	}

	public synchronized void setAcquireRetryDelay(int acquireRetryDelay) {
		setProperty("acquireRetryDelay", String.valueOf(acquireRetryDelay));
	}

	public synchronized void setAutoCommitOnClose(boolean autoCommitOnClose) {
		setProperty("autoCommitOnClose", String.valueOf(autoCommitOnClose));
	}

	public synchronized void setAutomaticTestTable(String automaticTestTable) {
		setProperty("automaticTestTable", automaticTestTable);
	}

	public synchronized void setBreakAfterAcquireFailure(boolean breakAfterAcquireFailure) {
		setProperty("breakAfterAcquireFailure", String.valueOf(breakAfterAcquireFailure));
	}

	public synchronized void setCheckoutTimeout(int checkoutTimeout) {
		setProperty("checkoutTimeout", String.valueOf(checkoutTimeout));
	}

	public synchronized void setConnectionCustomizerClassName(String connectionCustomizerClassName) {
		setProperty("connectionCustomizerClassName", connectionCustomizerClassName);
	}

	public synchronized void setConnectionTesterClassName(String connectionTesterClassName) {
		setProperty("connectionTesterClassName", connectionTesterClassName);
	}

	public synchronized void setDataSourceName(String dataSourceName) {
		setProperty("dataSourceName", dataSourceName);
	}

	public synchronized void setDebugUnreturnedConnectionStackTraces(boolean debugUnreturnedConnectionStackTraces) {
		setProperty("debugUnreturnedConnectionStackTraces", String.valueOf(debugUnreturnedConnectionStackTraces));
	}

	public synchronized void setDescription(String description) {
		setProperty("description", description);
	}

	public synchronized void setDriverClass(String driverClass) {
		c3p0Config.setDriverClass(driverClass);
		refresh("driverClass");
	}

	public synchronized void setFactoryClassLocation(String factoryClassLocation) {
		setProperty("factoryClassLocation", factoryClassLocation);
	}

	public void setFilter(String filter) {
		this.filterStr = filter;
	}

	public synchronized void setForceIgnoreUnresolvedTransactions(boolean forceIgnoreUnresolvedTransactions) {
		setProperty("forceIgnoreUnresolvedTransactions", String.valueOf(forceIgnoreUnresolvedTransactions));
	}

	public synchronized void setIdleConnectionTestPeriod(int idleConnectionTestPeriod) {
		setProperty("idleConnectionTestPeriod", String.valueOf(idleConnectionTestPeriod));
	}

	public synchronized void setInitialPoolSize(int initialPoolSize) {
		setProperty("initialPoolSize", String.valueOf(initialPoolSize));
	}

	public void setJdbcRef(String jdbcRef) {
		this.jdbcRef = jdbcRef;
	}

	public synchronized void setMaxAdministrativeTaskTime(int maxAdministrativeTaskTime) {
		setProperty("maxAdministrativeTaskTime", String.valueOf(maxAdministrativeTaskTime));
	}

	public synchronized void setMaxConnectionAge(int maxConnectionAge) {
		setProperty("maxConnectionAge", String.valueOf(maxConnectionAge));
	}

	public synchronized void setMaxIdleTime(int maxIdleTime) {
		setProperty("maxIdleTime", String.valueOf(maxIdleTime));
	}

	public synchronized void setMaxIdleTimeExcessConnections(int maxIdleTimeExcessConnections) {
		setProperty("maxIdleTimeExcessConnections", String.valueOf(maxIdleTimeExcessConnections));
	}

	public synchronized void setMaxPoolSize(int maxPoolSize) {
		setProperty("maxPoolSize", String.valueOf(maxPoolSize));
	}

	public synchronized void setMaxStatements(int maxStatements) {
		setProperty("maxStatements", String.valueOf(maxStatements));
	}

	public synchronized void setMaxStatementsPerConnection(int maxStatementsPerConnection) {
		setProperty("maxStatementsPerConnection", String.valueOf(maxStatementsPerConnection));
	}

	public synchronized void setMinPoolSize(int minPoolSize) {
		setProperty("minPoolSize", String.valueOf(minPoolSize));
	}

	// Compatible old GroupDataSource < 2.4.8
	public void setName(String name) {
		this.jdbcRef = name;
	}

	public synchronized void setNumHelperThreads(int numHelperThreads) {
		setProperty("numHelperThreads", String.valueOf(numHelperThreads));
	}

	public synchronized void setOverrideDefaultPassword(String overrideDefaultPassword) {
		setProperty("overrideDefaultPassword", overrideDefaultPassword);
	}

	public synchronized void setOverrideDefaultUser(String overrideDefaultUser) {
		setProperty("overrideDefaultUser", overrideDefaultUser);
	}

	public synchronized void setPreferredTestQuery(String preferredTestQuery) {
		setProperty("preferredTestQuery", preferredTestQuery);
	}

	public synchronized void setProperties(Properties properties) {
		for (Entry<Object, Object> item : properties.entrySet()) {
			setProperty(String.valueOf(item.getKey().toString()), String.valueOf(item.getValue()));
		}
	}

	private void setProperty(String name, String value) {
		Any any = null;
		for (Any a : c3p0Config.getProperties()) {
			if (a.getName().equals(name)) {
				any = a;
				break;
			}
		}

		if (any == null) {
			any = new Any();
			c3p0Config.getProperties().add(any);
		}

		any.setName(name);
		any.setValue(value);

		refresh(name);
	}

	public synchronized void setPropertyCycle(int propertyCycle) {
		setProperty("propertyCycle", String.valueOf(propertyCycle));
	}

	public void setRouterStrategy(String routerStrategy) {
		this.groupConfig.setRouterStrategy(routerStrategy);
		refresh("routerStrategy");
	}

	// hack for single datasource replace
	public void setRouterType(String routerType) {
		this.routerType = RouterType.getRouterType(routerType);
	}

	public synchronized void setTestConnectionOnCheckin(boolean testConnectionOnCheckin) {
		setProperty("testConnectionOnCheckin", String.valueOf(testConnectionOnCheckin));
	}

	public synchronized void setTestConnectionOnCheckout(boolean testConnectionOnCheckout) {
		setProperty("testConnectionOnCheckout", String.valueOf(testConnectionOnCheckout));
	}

	public void setTransactionForceWrite(boolean transactionForceWrite) {
		this.groupConfig.setTransactionForceWrite(transactionForceWrite);
		refresh("transactionForceWrite");
	}

	public synchronized void setUnreturnedConnectionTimeout(int unreturnedConnectionTimeout) {
		setProperty("unreturnedConnectionTimeout", String.valueOf(unreturnedConnectionTimeout));
	}

	public synchronized void setUserOverridesAsString(String userOverridesAsString) {
		setProperty("userOverridesAsString", userOverridesAsString);
	}

	public synchronized void setUsesTraditionalReflectiveProxies(boolean usesTraditionalReflectiveProxies) {
		setProperty("usesTraditionalReflectiveProxies", String.valueOf(usesTraditionalReflectiveProxies));
	}

	public void setWriteFirst(boolean writeFirst) {
		this.groupConfig.setWriteFirst(writeFirst);
		refresh("writeFirst");
	}

	class GroupDataSourceConfigChangedListener implements PropertyChangeListener {
		private String passwordKey = ".jdbc." + Constants.ELEMENT_PASSWORD;

		private String userKey = ".jdbc." + Constants.ELEMENT_USER;

		@Override
		public synchronized void propertyChange(PropertyChangeEvent evt) {
			if (evt.getNewValue().equals(evt.getOldValue())) {
				return;
			}

			if (evt.getPropertyName().endsWith(userKey)) {
				atomicRefresh.setUser(evt.getNewValue().toString());
				if (atomicRefresh.needToRefresh()) {
					refreshUserAndPassword();
				}
				return;
			}

			if (evt.getPropertyName().endsWith(passwordKey)) {
				atomicRefresh.setPassword(evt.getNewValue().toString());
				if (atomicRefresh.needToRefresh()) {
					refreshUserAndPassword();
				}
				return;
			}

			refresh(evt.getPropertyName());
		}
	}
}