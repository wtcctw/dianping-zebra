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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.dianping.zebra.group.exception.GroupDataSourceException;
import com.dianping.zebra.group.router.CustomizedReadWriteStrategy;
import com.dianping.zebra.group.util.JDBCExceptionUtils;

public class GroupDataSource extends AbstractDataSource {

	private static final Logger logger = LoggerFactory.getLogger(GroupDataSource.class);

	private LoadBalancedDataSource readDataSource;

	private FailOverDataSource writeDataSource;

	private DataSourceConfigManager dataSourceConfigManager;

	private SystemConfigManager systemConfigManager;

	private CustomizedReadWriteStrategy customizedReadWriteStrategy;

	private GroupDataSourceConfig groupConfigCache;

	private String name;

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

	public void init() {
		if (StringUtils.isBlank(name)) {
			throw new GroupDataSourceException("name must not be blank");
		}

		this.dataSourceConfigManager = DataSourceConfigManagerFactory.getConfigManager(configManagerType, name);
		this.dataSourceConfigManager.addListerner(new GroupDataSourceConfigChangedListener());
		this.groupConfigCache = this.dataSourceConfigManager.getGroupDataSourceConfig();
		this.systemConfigManager = SystemConfigManagerFactory.getConfigManger(configManagerType,
		      Constants.DEFAULT_SYSTEM_RESOURCE_ID);
		
		SingleDataSourceManagerFactory.getDataSourceManager().init();
		
		this.initDataSources();
		this.loadCustomizedReadWriteStrategy();
	}

	private void initDataSources() {
		try {
			this.readDataSource = new LoadBalancedDataSource(getLoadBalancedConfig(), systemConfigManager
			      .getSystemConfig().getRetryTimes());
			this.readDataSource.init();
			this.writeDataSource = new FailOverDataSource(getFailoverConfig());
			this.writeDataSource.init();
		} catch (RuntimeException e) {
			try {
				this.close(this.readDataSource, this.writeDataSource);
			} catch (SQLException e1) {
			}

			throw e;
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
			logger.error("error when create new dataSources", e);

			try {
				close(readDataSource, writeDataSource);
			} catch (Throwable ignore) {
			}
		}

		if (preparedSwitch) {
			LoadBalancedDataSource tmpReadDataSource = this.readDataSource;
			FailOverDataSource tmpWriteDataSource = this.writeDataSource;

			// switch
			this.readDataSource = readDataSource;
			this.writeDataSource = writeDataSource;

			// destroy old dataSources
			try {
				this.close(tmpReadDataSource, tmpWriteDataSource);
			} catch (Throwable e) {
				logger.error("error when destroy old dataSources", e);
			}

			logger.info("refresh the dataSources successfully!");
		}
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