package com.dianping.zebra.group.jdbc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.status.StatusExtension;
import com.dianping.cat.status.StatusExtensionRegister;
import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.SystemConfigManager;
import com.dianping.zebra.group.config.SystemConfigManagerFactory;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.datasources.LoadBalancedDataSource;
import com.dianping.zebra.group.datasources.SingleDataSourceManagerFactory;
import com.dianping.zebra.group.exception.DalException;
import com.dianping.zebra.group.monitor.DalStatusExtension;
import com.dianping.zebra.group.monitor.GroupDataSourceMBean;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.dianping.zebra.group.router.CustomizedReadWriteStrategy;
import com.dianping.zebra.group.util.JDBCExceptionUtils;
import com.dianping.zebra.group.util.StringUtils;

public class GroupDataSource extends AbstractDataSource implements GroupDataSourceMBean {

	private static final Logger logger = LoggerFactory.getLogger(GroupDataSource.class);

	private String name;

	private GroupDataSourceConfig groupConfigCache;

	private LoadBalancedDataSource readDataSource;

	private FailOverDataSource writeDataSource;

	private DataSourceConfigManager dataSourceConfigManager;

	private SystemConfigManager systemConfigManager;

	private CustomizedReadWriteStrategy customizedReadWriteStrategy;

	private DalStatusExtension statusExtension;

	public GroupDataSource() {
	}

	public GroupDataSource(String name) {
		this.name = name;
	}

	public void close() throws SQLException {
		this.close(this.readDataSource, this.writeDataSource);
	}

	private void close(LoadBalancedDataSource readDataSource, FailOverDataSource writeDataSource) throws SQLException {
		List<SQLException> exps = new ArrayList<SQLException>();

		try {
			if (readDataSource != null) {
				readDataSource.close();
			}
		} catch (SQLException e) {
			exps.add(e);
		}

		try {
			if (writeDataSource != null) {
				writeDataSource.close();
			}
		} catch (SQLException e) {
			exps.add(e);
		}

		JDBCExceptionUtils.throwSQLExceptionIfNeeded(exps);
	}

	@Override
	public GroupDataSourceConfig getConfig() {
		return groupConfigCache;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getConnection(null, null);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return new GroupConnection(readDataSource, writeDataSource, dataSourceConfigManager, customizedReadWriteStrategy);
	}

	private Map<String, DataSourceConfig> getFailoverConfig() {
		Map<String, DataSourceConfig> failoverConfigMap = new HashMap<String, DataSourceConfig>();

		for (Entry<String, DataSourceConfig> entry : dataSourceConfigManager.getDataSourceConfigs().entrySet()) {
			String key = entry.getKey();
			DataSourceConfig config = entry.getValue();

			if (config.isActive() && config.isCanWrite()) {
				failoverConfigMap.put(key, config);
			}
		}

		return failoverConfigMap;
	}

	private Map<String, DataSourceConfig> getLoadBalancedConfig() {
		Map<String, DataSourceConfig> loadBalancedConfigMap = new HashMap<String, DataSourceConfig>();

		for (Entry<String, DataSourceConfig> entry : dataSourceConfigManager.getDataSourceConfigs().entrySet()) {
			String key = entry.getKey();
			DataSourceConfig config = entry.getValue();

			if (config.isActive() && config.isCanRead()) {
				loadBalancedConfigMap.put(key, config);
			}
		}

		return loadBalancedConfigMap;
	}

	@Override
	public synchronized Map<String, SingleDataSourceMBean> getReaderSingleDataSourceMBean() {
		return this.readDataSource.getCurrentDataSourceMBean();
	}

	public StatusExtension getStatusExtension() {
		return this.statusExtension;
	}

	@Override
	public synchronized SingleDataSourceMBean getWriteSingleDataSourceMBean() {
		return this.writeDataSource.getCurrentDataSourceMBean();
	}

	public void init() {
		if (StringUtils.isBlank(name)) {
			throw new DalException("name cannot be blank");
		}

		this.dataSourceConfigManager = DataSourceConfigManagerFactory.getConfigManager(configManagerType, name);
		this.dataSourceConfigManager.addListerner(new GroupDataSourceConfigChangedListener());
		this.groupConfigCache = this.dataSourceConfigManager.getGroupDataSourceConfig();
		this.systemConfigManager = SystemConfigManagerFactory.getConfigManger(configManagerType,
		      Constants.DEFAULT_SYSTEM_RESOURCE_ID);

		SingleDataSourceManagerFactory.getDataSourceManager().init();

		this.initDataSources();
		this.loadCustomizedReadWriteStrategy();
		this.statusExtension = new DalStatusExtension(this);
		StatusExtensionRegister.getInstance().register(this.statusExtension);

		logger.info("GroupDataSource successfully initialized.");
	}

	private void initDataSources() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			throw new DalException("Cannot find mysql driver class[com.mysql.jdbc.Driver]", ex);
		}

		try {
			this.readDataSource = new LoadBalancedDataSource(getLoadBalancedConfig(), systemConfigManager
			      .getSystemConfig().getRetryTimes());
			this.readDataSource.init();
			this.writeDataSource = new FailOverDataSource(getFailoverConfig());
			this.writeDataSource.initFailFast();
		} catch (RuntimeException e) {
			try {
				this.close(this.readDataSource, this.writeDataSource);
			} catch (SQLException ignore) {
			}

			throw new DalException("fail to initialize group dataSource", e);
		}
	}

	private void loadCustomizedReadWriteStrategy() {
		ServiceLoader<CustomizedReadWriteStrategy> strategies = ServiceLoader.load(CustomizedReadWriteStrategy.class);

		if (strategies != null) {
			for (CustomizedReadWriteStrategy strategy : strategies) {
				if (strategy != null) {
					customizedReadWriteStrategy = strategy;
					break;
				}
			}
		}
	}

	private void refresh() {
		GroupDataSourceConfig groupConfigTmp = this.dataSourceConfigManager.getGroupDataSourceConfig();
		if (groupConfigCache.toString().equals(groupConfigTmp.toString())) {
			return;
		} else {
			groupConfigCache = groupConfigTmp;
		}

		logger.info("start to refresh the dataSources...");

		Transaction tran = Cat.newTransaction("DAL", "Refresh");

		LoadBalancedDataSource readDataSource = null;
		FailOverDataSource writeDataSource = null;
		boolean preparedSwitch = false;
		try {
			readDataSource = new LoadBalancedDataSource(getLoadBalancedConfig(), systemConfigManager.getSystemConfig()
			      .getRetryTimes());
			readDataSource.init();
			writeDataSource = new FailOverDataSource(getFailoverConfig());
			writeDataSource.init();

			preparedSwitch = true;
		} catch (Throwable e) {
			Cat.logError("error when create new dataSources", e);
			try {
				close(readDataSource, writeDataSource);
			} catch (Throwable ignore) {
			}
		}

		if (preparedSwitch) {
			LoadBalancedDataSource tmpReadDataSource = this.readDataSource;
			FailOverDataSource tmpWriteDataSource = this.writeDataSource;

			synchronized (this) {
				// switch
				this.readDataSource = readDataSource;
				this.writeDataSource = writeDataSource;
			}

			// destroy old dataSources
			try {
				this.close(tmpReadDataSource, tmpWriteDataSource);
			} catch (Throwable e) {
				Cat.logError("error when destroy old dataSources", e);
			}

			tran.setStatus(Message.SUCCESS);
			logger.info("refresh the dataSources successfully!");
		} else {
			tran.setStatus("fail to refresh the dataSource");
			logger.warn("fail to refresh the dataSource");
		}

		tran.complete();
	}

	public void setName(String name) {
		this.name = name;
	}

	class GroupDataSourceConfigChangedListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			refresh();
		}
	}
}