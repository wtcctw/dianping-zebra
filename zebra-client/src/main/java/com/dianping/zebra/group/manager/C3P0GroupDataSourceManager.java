package com.dianping.zebra.group.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.ActiveDataSourceChangeEvent;
import com.dianping.zebra.group.config.BaseGroupConfigChangeEvent;
import com.dianping.zebra.group.config.GroupConfigChangeListener;
import com.dianping.zebra.group.config.GroupConfigManager;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0GroupDataSourceManager implements GroupDataSourceManager {

	private Logger logger = LoggerFactory.getLogger(C3P0GroupDataSourceManager.class);

	private Map<String, DataSourceConfig> dataSourceConfigsCache = new HashMap<String, DataSourceConfig>();

	private Map<String, DataSource> dataSources = new ConcurrentHashMap<String, DataSource>();

	private GroupConfigManager groupConfigManager;

	private volatile boolean closed = false;

	public C3P0GroupDataSourceManager(GroupConfigManager groupConfigManager) {
		this.groupConfigManager = groupConfigManager;
	}

	@Override
	public void init() {
		for (Entry<String, DataSourceConfig> entry : groupConfigManager.getAvailableDataSources().entrySet()) {
			String key = entry.getKey();
			try {
				dataSources.put(key, initDataSources(entry.getValue()));
			} catch (RuntimeException e) {
				logger.error(String.format("fail to connect the database[%s]", key), e);
				throw e;
			}
		}

		this.groupConfigManager.addListerner(new GroupDataSourceManagerListener());
	}

	private DataSource initDataSources(DataSourceConfig value) {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		Properties properties = new Properties();

		properties.setProperty("jdbcUrl", value.getJdbcUrl());
		properties.setProperty("driverClass", value.getDriverClass());
		properties.setProperty("user", value.getUser());
		properties.setProperty("password", value.getPassword());
		properties.setProperty("minPoolSize", String.valueOf(value.getMinPoolSize()));
		properties.setProperty("maxPoolSize", String.valueOf(value.getMaxPoolSize()));
		properties.setProperty("initialPoolSize", String.valueOf(value.getInitialPoolSize()));

		for (Any any : value.getProperties()) {
			// TODO franky wu
			properties.setProperty(any.getName(), any.getValue());
		}

		dataSource.setProperties(properties);

		return dataSource;
	}

	@Override
	public boolean isAvailable(String id) {
		// TODO
		return true;
	}

	@Override
	public Connection getConnection(String id) throws SQLException {
		checkClosed();

		DataSource dataSource = dataSources.get(id);

		if (dataSource != null) {
			return dataSource.getConnection();
		} else {
			return null;
		}
	}

	@Override
	public void destory() throws SQLException {
		closed = true;

	}

	public void checkClosed() throws SQLException {
		if (closed) {
			throw new SQLException("C3P0GroupDataSourceManager is closed.");
		}
	}

	class GroupDataSourceManagerListener implements GroupConfigChangeListener {

		@Override
		public void onChange(BaseGroupConfigChangeEvent event) {
			if (event instanceof ActiveDataSourceChangeEvent) {
				Map<String, DataSourceConfig> newConfig = event.getDatasourceConfigs();
				for (Entry<String, DataSourceConfig> entry : newConfig.entrySet()) {
					String key = entry.getKey();
					DataSourceConfig value = entry.getValue();
					DataSourceConfig originDataSourceConfig = dataSourceConfigsCache.get(key);
					if (originDataSourceConfig != null && !originDataSourceConfig.toString().equals(value.toString())) {
						DataSource dataSource = initDataSources(value);
						
						//DataSource originDataSource = 
					}
					// 1. diff DataSourceConfig with tmp DataSourceConfig
					// 2. if different ,
					// rebuild DataSource
					// update Map<String, DataSource>
					// put old DataSource into destory-ThreadPool
				}

				// update DataSourceConfigCache
			}
		}

		@Override
		public String getName() {
			return C3P0GroupDataSourceManager.class.getSimpleName() + "-Listener";
		}
	}
}
