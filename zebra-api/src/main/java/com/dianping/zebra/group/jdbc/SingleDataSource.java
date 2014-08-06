package com.dianping.zebra.group.jdbc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.datasources.DataSourceState;
import com.dianping.zebra.group.datasources.InnerSingleDataSource;
import com.dianping.zebra.group.datasources.SingleDataSourceManagerFactory;
import com.dianping.zebra.group.exception.DalException;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.dianping.zebra.group.util.AtomicRefresh;
import com.dianping.zebra.group.util.SmoothReload;
import com.dianping.zebra.group.util.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Dozer <br/>
 *         1.实现了自动 reload 机制。 <br/>
 *         2.实现了帐号密码原子修改，只改帐号或者密码不会触发重新加载流程，只有两个同时修改后，才会一次性触发重新加载。<br/>
 *         3.并发策略：所有修改innerDs的地方都需要同步（set方法需要同步；init需要同步;refresh需要同步）。
 * 
 */
public class SingleDataSource extends AbstractDataSource implements DataSource, SingleDataSourceMBean {

	private final Logger logger = LogManager.getLogger(this.getClass());

	private AtomicRefresh atomicRefresh = new AtomicRefresh();

	private volatile InnerSingleDataSource innerDs;

	private DataSourceConfig c3p0Config = new DataSourceConfig();

	private volatile DataSourceConfig lionConfig;

	private DataSourceConfigManager dataSourceConfigManager;

	private String jdbcRef;

	private boolean useLionConfig = true;

	public SingleDataSource() {

	}

	public SingleDataSource(String jdbcRef) {
		this.setJdbcRef(jdbcRef);
	}

	/**
	 * just for auto replace spring c3p0 bean
	 * 
	 * @param jdbcRef
	 * @param useLionConfig
	 */
	public SingleDataSource(String jdbcRef, boolean useLionConfig) {
		this.useLionConfig = useLionConfig;
		this.c3p0Config.setActive(true);
		this.setJdbcRef(jdbcRef);
	}

	private DataSourceConfig buildDataSourceConfig() {
		if (!useLionConfig) {
			return c3p0Config;
		}

		if (lionConfig.getDriverClass() == null || lionConfig.getDriverClass().length() <= 0) {
			// in case that DBA has not give default value to driverClass.
			lionConfig.setDriverClass(c3p0Config.getDriverClass());
		}

		// merge c3p0 properties
		for (Any property : c3p0Config.getProperties()) {
			String key = property.getName();
			String value = property.getValue();
			Any any = findAny(lionConfig.getProperties(), key);

			if (any != null) {
				any.setValue(value);
			} else {
				Any any1 = new Any();
				any1.setName(key);
				any1.setValue(value);
				lionConfig.getProperties().add(any1);
			}
		}

		return lionConfig;
	}

	public synchronized void close() {
		destoryInnerDs(innerDs);
	}

	private void destoryInnerDs(InnerSingleDataSource dataSource) {
		if (dataSource == null) {
			return;
		}

		Transaction t = Cat.newTransaction("DAL", "DataSource.Destory");
		try {
			Cat.logEvent("DAL.Destory.Start", dataSource.getId());
			SingleDataSourceManagerFactory.getDataSourceManager().destoryDataSource(dataSource);
			t.setStatus(Message.SUCCESS);
		} finally {
			// wait one second for dataSource to destroy
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}

			t.complete();
		}
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
	public DataSourceConfig getConfig() {
		return this.innerDs == null ? null : this.innerDs.getConfig();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getInnerDs().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return getInnerDs().getConnection(username, password);
	}

	@Override
	public String getCurrentState() {
		return this.innerDs == null ? null : this.innerDs.getCurrentState();
	}

	@Override
	public String getId() {
		return jdbcRef;
	}

	private DataSource getInnerDs() throws SQLException {
		if (innerDs == null) {
			synchronized (this) {
				if (innerDs == null) {
					innerDs = initDataSource();
				}
			}
		}
		return innerDs;
	}

	public String getJdbcRef() {
		return jdbcRef;
	}

	@Override
	public int getNumBusyConnection() {
		return this.innerDs == null ? 0 : this.innerDs.getNumBusyConnection();
	}

	@Override
	public int getNumConnections() {
		return this.innerDs == null ? 0 : this.innerDs.getNumConnections();
	}

	@Override
	public long getNumFailedCheckins() {
		return this.innerDs == null ? 0 : this.innerDs.getNumFailedCheckins();
	}

	@Override
	public long getNumFailedCheckouts() {
		return this.innerDs == null ? 0 : this.innerDs.getNumFailedCheckouts();
	}

	@Override
	public int getNumIdleConnection() {
		return this.innerDs == null ? 0 : this.innerDs.getNumIdleConnection();
	}

	@Override
	public int getNumUnClosedOrphanedConnections() {
		return this.innerDs == null ? 0 : this.innerDs.getNumUnClosedOrphanedConnections();
	}

	@Override
	public DataSourceState getState() {
		return this.innerDs == null ? DataSourceState.INITIAL : this.innerDs.getState();
	}

	@Override
	public int getThreadPoolNumActiveThreads() {
		return this.innerDs == null ? 0 : this.innerDs.getThreadPoolNumActiveThreads();
	}

	@Override
	public int getThreadPoolNumIdleThreads() {
		return this.innerDs == null ? 0 : this.innerDs.getThreadPoolNumIdleThreads();
	}

	@Override
	public int getThreadPoolNumTasksPending() {
		return this.innerDs == null ? 0 : this.innerDs.getThreadPoolNumTasksPending();
	}

	@Override
	public int getThreadPoolSize() {
		return this.innerDs == null ? 0 : this.innerDs.getThreadPoolSize();
	}

	public void init() {
		if (StringUtils.isBlank(jdbcRef)) {
			throw new DalException("jdbcRef cannot be empty");
		}

		if (useLionConfig) {
			this.dataSourceConfigManager = DataSourceConfigManagerFactory.getConfigManager(configManagerType, jdbcRef,
			      true, false);
			lionConfig = this.dataSourceConfigManager.getSingleDataSourceConfig();
			this.dataSourceConfigManager.addListerner(new SingleDataSourceConfigChangedListener());
		}
		registerMonitor();

		logger.info("SingleDataSource successfully initialized.");
	}

	private InnerSingleDataSource initDataSource() throws SQLException {
		Transaction t = Cat.newTransaction("DAL", "DataSource.Init");

		try {
			InnerSingleDataSource ds = SingleDataSourceManagerFactory.getDataSourceManager().createDataSource(
			      buildDataSourceConfig());
			t.setStatus(Message.SUCCESS);

			return ds;
		} catch (IllegalConfigException exp) {
			Cat.logError(exp);
			throw exp;
		} finally {
			t.complete();
		}
	}

	protected void refresh(String propertyToChange) {
		if (innerDs == null) {
			return;
		}

		Transaction t = Cat.newTransaction("DAL", "DataSource.Refresh");
		Cat.logEvent("DAL.Refresh.Property", propertyToChange);
		try {
			InnerSingleDataSource tempDs = innerDs;

			innerDs = initDataSource();

			destoryInnerDs(tempDs);
			t.setStatus(Message.SUCCESS);
		} catch (Exception e) {
			Cat.logError(e);
			t.setStatus(e);
		} finally {
			t.complete();
		}
	}

	private void refreshUserAndPassword() {
		c3p0Config.setUsername(atomicRefresh.getNewUser());
		c3p0Config.setPassword(atomicRefresh.getNewPassword());
		atomicRefresh.reset();

		if (lionConfig != null) {
			SmoothReload sr = new SmoothReload(lionConfig.getWarmupTime());
			sr.waitForReload();
		}

		refresh("user&password");
	}

	private void registerMonitor() {
		try {
			// compatible to Cat <= 1.0.4
			Class<?> registerClass = Class.forName("com.dianping.cat.status.StatusExtensionRegister");
			Class<?> extensionInterfaceClass = Class.forName("com.dianping.cat.status.StatusExtension");
			Class<?> extensionClass = Class.forName("com.dianping.zebra.group.monitor.SingleDataSourceMonitor");
			Method getInstanceMethod = registerClass.getMethod("getInstance");
			Method registerMethod = registerClass.getMethod("register", extensionInterfaceClass);
			Constructor<?> constructor = extensionClass.getConstructor(SingleDataSourceMBean.class);

			registerMethod.invoke(getInstanceMethod.invoke(registerClass), constructor.newInstance(this));
		} catch (Exception e) {
			Cat.logError(e);
		}
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
		this.c3p0Config.setId(jdbcRef);
	}

	public synchronized void setJdbcUrl(String jdbcUrl) {
		if (useLionConfig) {
			return;
		}

		c3p0Config.setJdbcUrl(jdbcUrl);
		refresh("jdbcUrl");
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

	public synchronized void setNumHelperThreads(int numHelperThreads) {
		setProperty("numHelperThreads", String.valueOf(numHelperThreads));
	}

	public synchronized void setOverrideDefaultPassword(String overrideDefaultPassword) {
		setProperty("overrideDefaultPassword", overrideDefaultPassword);
	}

	public synchronized void setOverrideDefaultUser(String overrideDefaultUser) {
		setProperty("overrideDefaultUser", overrideDefaultUser);
	}

	public synchronized void setPassword(String password) {
		if (useLionConfig) {
			return;
		}

		// innerDs 未加载的时候不需要 reload 逻辑
		if (innerDs == null) {
			c3p0Config.setPassword(password);
			return;
		}

		atomicRefresh.setPassword(password);
		if (atomicRefresh.needToRefresh()) {
			refreshUserAndPassword();
		}
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

	public synchronized void setTestConnectionOnCheckin(boolean testConnectionOnCheckin) {
		setProperty("testConnectionOnCheckin", String.valueOf(testConnectionOnCheckin));
	}

	public synchronized void setTestConnectionOnCheckout(boolean testConnectionOnCheckout) {
		setProperty("testConnectionOnCheckout", String.valueOf(testConnectionOnCheckout));
	}

	public synchronized void setUnreturnedConnectionTimeout(int unreturnedConnectionTimeout) {
		setProperty("unreturnedConnectionTimeout", String.valueOf(unreturnedConnectionTimeout));
	}

	public synchronized void setUser(String user) {
		if (useLionConfig) {
			return;
		}

		// innerDs 未加载的时候不需要 reload 逻辑
		if (innerDs == null) {
			c3p0Config.setUsername(user);
			return;
		}

		atomicRefresh.setUser(user);
		if (atomicRefresh.needToRefresh()) {
			refreshUserAndPassword();
		}
	}

	public synchronized void setUserOverridesAsString(String userOverridesAsString) {
		setProperty("userOverridesAsString", userOverridesAsString);
	}

	public synchronized void setUsesTraditionalReflectiveProxies(boolean usesTraditionalReflectiveProxies) {
		setProperty("usesTraditionalReflectiveProxies", String.valueOf(usesTraditionalReflectiveProxies));
	}

	class SingleDataSourceConfigChangedListener implements PropertyChangeListener {
		private String userKey = ".jdbc." + Constants.ELEMENT_USER;

		private String passwordKey = ".jdbc." + Constants.ELEMENT_PASSWORD;

		@Override
		public synchronized void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().startsWith(Constants.DEFAULT_DATASOURCE_SINGLE_PRFIX + "." + jdbcRef)) {

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

				lionConfig = dataSourceConfigManager.getSingleDataSourceConfig();
				refresh(evt.getPropertyName());
			}
		}
	}
}