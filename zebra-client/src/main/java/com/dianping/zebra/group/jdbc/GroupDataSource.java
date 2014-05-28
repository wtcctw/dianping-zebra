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
import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.datasources.LoadBalancedDataSource;
import com.dianping.zebra.group.datasources.SingleDataSourceManager;
import com.dianping.zebra.group.datasources.SingleDataSourceManagerFactory;
import com.dianping.zebra.group.exception.GroupDataSourceException;
import com.dianping.zebra.group.router.CustomizedReadWriteStrategy;
import com.dianping.zebra.group.util.JDBCExceptionUtils;

public class GroupDataSource extends AbstractDataSource {

	private static final Logger logger = LoggerFactory.getLogger(GroupDataSource.class);

	private LoadBalancedDataSource readDataSource;

	private FailOverDataSource writeDataSource;

	private SingleDataSourceManager singleDataSourceManager;

	private DataSourceConfigManager dataSourceConfigManager;

	private SystemConfigManager systemConfigManager;

	private CustomizedReadWriteStrategy customizedReadWriteStrategy;

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

	public void init() {
		if (StringUtils.isBlank(name)) {
			throw new GroupDataSourceException("name must not be blank");
		}

		this.singleDataSourceManager = SingleDataSourceManagerFactory.getDataSourceManager();
		this.dataSourceConfigManager = DataSourceConfigManagerFactory.getConfigManager(configManagerType, name);
		this.dataSourceConfigManager.addListerner(new GroupDataSourceConfigChangedListener());
		this.systemConfigManager = SystemConfigManagerFactory.getConfigManger(configManagerType,
		      Constants.DEFAULT_SYSTEM_RESOURCE_ID);
		this.initDataSources();
		this.loadCustomizedReadWriteStrategy();
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

	private void initDataSources() {
		try {
			Map<String, DataSourceConfig> loadBalancedConfigMap = new HashMap<String, DataSourceConfig>();
			Map<String, DataSourceConfig> failOverConfigMap = new HashMap<String, DataSourceConfig>();

			for (Entry<String, DataSourceConfig> entry : dataSourceConfigManager.getDataSourceConfigs().entrySet()) {
				String key = entry.getKey();
				DataSourceConfig config = entry.getValue();

				if (config.isActive()) {
					if (config.isCanRead()) {
						loadBalancedConfigMap.put(key, config);
					}

					if (config.isCanWrite()) {
						failOverConfigMap.put(key, config);
					}
				} else {
					this.singleDataSourceManager.destoryDataSource(config.getId(), name);
				}
			}

			this.readDataSource = new LoadBalancedDataSource(name, loadBalancedConfigMap, systemConfigManager,
			      singleDataSourceManager);
			this.readDataSource.init();
			this.writeDataSource = new FailOverDataSource(name, failOverConfigMap, singleDataSourceManager);
			this.writeDataSource.init();
		} catch (RuntimeException e) {
			try {
				this.close(this.readDataSource, this.writeDataSource);
			} catch (SQLException e1) {
			}

			throw e;
		}
	}

	private void refresh() {
		Map<String, DataSourceConfig> loadBalancedConfigMap = new HashMap<String, DataSourceConfig>();
		Map<String, DataSourceConfig> failOverConfigMap = new HashMap<String, DataSourceConfig>();

		for (Entry<String, DataSourceConfig> entry : dataSourceConfigManager.getDataSourceConfigs().entrySet()) {
			String key = entry.getKey();
			DataSourceConfig config = entry.getValue();

			if (config.isActive()) {
				if (config.isCanRead()) {
					loadBalancedConfigMap.put(key, config);
				}

				if (config.isCanWrite()) {
					failOverConfigMap.put(key, config);
				}
			} else {
				this.singleDataSourceManager.destoryDataSource(config.getId(), name);
			}
		}

		LoadBalancedDataSource readDataSource = null;
		FailOverDataSource writeDataSource = null;
		boolean preparedSwitch = false;
		try {
			readDataSource = new LoadBalancedDataSource(name, loadBalancedConfigMap, systemConfigManager,
			      singleDataSourceManager);
			readDataSource.init();
			writeDataSource = new FailOverDataSource(name, failOverConfigMap, singleDataSourceManager);
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
			logger.info("refresh the dataSources successfully!");

			// destroy old dataSources
			try {
				this.close(tmpReadDataSource, tmpWriteDataSource);
			} catch (Throwable e) {
				logger.error("error when destroy old dataSources", e);
			}
		}
	}

	class GroupDataSourceConfigChangedListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			refresh();
		}
	}
}