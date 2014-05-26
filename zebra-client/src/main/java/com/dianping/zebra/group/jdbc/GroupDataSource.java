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
import com.dianping.zebra.group.util.JDBCExceptionUtils;

public class GroupDataSource extends AbstractDataSource {

	private static final Logger logger = LoggerFactory.getLogger(GroupDataSource.class);

	private LoadBalancedDataSource readDataSource;

	private FailOverDataSource writeDataSource;

	private SingleDataSourceManager singleDataSourceManager;

	private DataSourceConfigManager dataSourceConfigManager;

	private SystemConfigManager systemConfigManager;

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
		return new GroupConnection(readDataSource, writeDataSource, dataSourceConfigManager);
	}

	private Map<String, DataSourceConfig> getFailOverConfig() {
		Map<String, DataSourceConfig> dataSourceConfigMap = new HashMap<String, DataSourceConfig>();

		for (Entry<String, DataSourceConfig> entry : dataSourceConfigManager.getDataSourceConfigs().entrySet()) {
			String key = entry.getKey();
			DataSourceConfig config = entry.getValue();

			if (config.isCanWrite()) {
				dataSourceConfigMap.put(key, config);
			}
		}

		return dataSourceConfigMap;
	}

	private Map<String, DataSourceConfig> getLoadBalancedConfig() {
		Map<String, DataSourceConfig> dataSourceConfigMap = new HashMap<String, DataSourceConfig>();

		for (Entry<String, DataSourceConfig> entry : dataSourceConfigManager.getDataSourceConfigs().entrySet()) {
			String key = entry.getKey();
			DataSourceConfig config = entry.getValue();

			if (config.isCanRead()) {
				dataSourceConfigMap.put(key, config);
			}
		}

		return dataSourceConfigMap;
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
	}

	private void initDataSources() {
		this.readDataSource = new LoadBalancedDataSource(name, getLoadBalancedConfig(), systemConfigManager,
		      singleDataSourceManager);
		this.readDataSource.init();
		this.writeDataSource = new FailOverDataSource(name, getFailOverConfig(), singleDataSourceManager);
		this.writeDataSource.init();
	}

	private void refresh() {
		LoadBalancedDataSource readDataSource = null;
		FailOverDataSource writeDataSource = null;
		boolean preparedSwitch = false;
		try {
			readDataSource = new LoadBalancedDataSource(name, getLoadBalancedConfig(), systemConfigManager,
			      singleDataSourceManager);
			readDataSource.init();
			writeDataSource = new FailOverDataSource(name, getFailOverConfig(), singleDataSourceManager);
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