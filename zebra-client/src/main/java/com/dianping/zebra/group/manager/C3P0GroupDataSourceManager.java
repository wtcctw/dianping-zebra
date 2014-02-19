package com.dianping.zebra.group.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

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

	private AtomicReference<Map<String, DataSourceConfig>> dataSourceConfigsCache = new AtomicReference<Map<String, DataSourceConfig>>();

	private Map<String, DataSource> dataSources = new ConcurrentHashMap<String, DataSource>();

	private BlockingQueue<DataSource> toCloseDataSource = new LinkedBlockingQueue<DataSource>();

	private GroupConfigManager groupConfigManager;

	private volatile boolean closed = false;

	public C3P0GroupDataSourceManager(GroupConfigManager groupConfigManager) {
		this.groupConfigManager = groupConfigManager;
	}

	public void checkClosed() throws SQLException {
		if (closed) {
			throw new SQLException("C3P0GroupDataSourceManager is closed.");
		}
	}

	@Override
	public void destory() throws SQLException {
		closed = true;

		for (DataSource dataSource : this.dataSources.values()) {
			this.toCloseDataSource.offer(dataSource);
		}
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
	public void init() {
		Map<String, DataSourceConfig> available = groupConfigManager.getAvailableDataSources();
		for (Entry<String, DataSourceConfig> entry : available.entrySet()) {
			String key = entry.getKey();
			try {
				this.dataSources.put(key, initDataSources(entry.getValue()));
			} catch (RuntimeException e) {
				logger.error(String.format("fail to connect the database[%s]", key), e);
				throw e;
			}
		}

		this.dataSourceConfigsCache.set(available);
		this.groupConfigManager.addListerner(new GroupDataSourceManagerListener());

		Thread thread = new Thread(new CheckOutConnectionTask());
		thread.setDaemon(true);
		thread.setName("Thread-" + CheckOutConnectionTask.class.getClass().getSimpleName());

		thread.start();
	}

	private DataSource initDataSources(DataSourceConfig value) {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		dataSource.close();

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

	class GroupDataSourceManagerListener implements GroupConfigChangeListener {

		@Override
		public String getName() {
			return C3P0GroupDataSourceManager.class.getSimpleName() + "-Listener";
		}

		@Override
		public void onChange(BaseGroupConfigChangeEvent event) {
			if (event instanceof ActiveDataSourceChangeEvent) {
				Map<String, DataSourceConfig> newConfig = event.getDatasourceConfigs();
				for (Entry<String, DataSourceConfig> entry : newConfig.entrySet()) {
					String key = entry.getKey();
					DataSourceConfig value = entry.getValue();
					DataSourceConfig originDataSourceConfig = dataSourceConfigsCache.get().get(key);
					if (originDataSourceConfig != null && !originDataSourceConfig.toString().equals(value.toString())) {
						DataSource dataSource = initDataSources(value);
						DataSource originDataSource = dataSources.get(key);

						dataSources.put(key, dataSource);
						boolean isSuccess = toCloseDataSource.offer(originDataSource);

						if (!isSuccess) {
							logger.warn("blocking queue for closed datasources is full!");
						}
					}
				}

				dataSourceConfigsCache.set(newConfig);
			}
		}
	}

	class CheckOutConnectionTask implements Runnable {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				DataSource dataSource = toCloseDataSource.poll();

				if (dataSource != null && dataSource instanceof ComboPooledDataSource) {
					ComboPooledDataSource comboDataSource = (ComboPooledDataSource) dataSource;
					comboDataSource.close();
				}
			}
		}
	}
}
