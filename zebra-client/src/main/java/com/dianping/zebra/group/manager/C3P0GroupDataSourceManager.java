package com.dianping.zebra.group.manager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.GroupConfigException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0GroupDataSourceManager implements GroupDataSourceManager {

	public static final String ID = "c3p0";

	private Logger logger = LoggerFactory.getLogger(C3P0GroupDataSourceManager.class);

	private AtomicReference<Map<String, DataSourceConfig>> dataSourceConfigsCache = new AtomicReference<Map<String, DataSourceConfig>>();

	private Map<String, DataSource> dataSources = new ConcurrentHashMap<String, DataSource>();

	private BlockingQueue<DataSource> toCloseDataSource = new LinkedBlockingQueue<DataSource>();

	private DataSourceConfigManager groupConfigManager;

	private volatile boolean closed = false;

	public C3P0GroupDataSourceManager(DataSourceConfigManager groupConfigManager) {
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
	public Connection getReadConnection(String id) throws SQLException {
		checkClosed();
		return getConnection(id);
	}

	public DataSource getDataSource(String id) {
		return dataSources.get(id);
	}

	private Connection getConnection(String id) throws SQLException {
		DataSource dataSource = dataSources.get(id);

		if (dataSource != null) {
			return dataSource.getConnection();
		} else {
			throw new SQLException(String.format("Fail to get connection for dataSource[%s]", id));
		}
	}

	@Override
	public Connection getWriteConnection() throws SQLException {
		checkClosed();

		for (DataSourceConfig config : dataSourceConfigsCache.get().values()) {
			if (config.isReadonly()) {
				return getConnection(config.getId());
			}
		}

		throw new SQLException("Fail to find write dataSource.");
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
		this.groupConfigManager.addListerner(new ConfigChangedListener());

		Thread thread = new Thread(new CheckOutConnectionTask());
		thread.setDaemon(true);
		thread.setName("Thread-" + CheckOutConnectionTask.class.getName());

		thread.start();
	}

	private DataSource initDataSources(DataSourceConfig value) {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		try {
			dataSource.setIdentityToken(value.getId());
			dataSource.setUser(value.getUser());
			dataSource.setDriverClass(value.getDriverClass());
			dataSource.setJdbcUrl(value.getJdbcUrl());
			dataSource.setPassword(value.getPassword());
			dataSource.setInitialPoolSize(value.getInitialPoolSize());
			dataSource.setMinPoolSize(value.getMinPoolSize());
			dataSource.setMaxPoolSize(value.getMaxPoolSize());
			dataSource.setConnectionCustomizerClassName(value.getConnectionCustomizeClassName());

			for (Any any : value.getProperties()) {
				// TODO franky wu
				MethodUtils.invokeMethod(dataSource, "set" + StringUtils.capitalize(any.getName()), any.getValue());
			}
		} catch (Throwable e) {
			throw new GroupConfigException(e);
		}

		return dataSource;
	}

	@Override
	public boolean isAvailable(String id) {
		// TODO
		return true;
	}

	class CheckOutConnectionTask implements Runnable {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					DataSource dataSource = toCloseDataSource.take();

					if (dataSource != null && dataSource instanceof ComboPooledDataSource) {
						ComboPooledDataSource comboDataSource = (ComboPooledDataSource) dataSource;
						String dsId = comboDataSource.getIdentityToken();

						if (C3P0DataSourceRuntimeMonitor.INSTANCE.getCheckedOutCount(dsId) <= 0) {
							comboDataSource.close();
							C3P0DataSourceRuntimeMonitor.INSTANCE.removeCounter(comboDataSource.getIdentityToken());
						} else {
							toCloseDataSource.offer(comboDataSource);
						}
					} else {
						// Normally not happen
						logger.warn("fail to close dataSource since dataSource is null or dataSource is not an instance of ComboPooledDataSource.");
					}

					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	class ConfigChangedListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Map<String, DataSourceConfig> newConfig = groupConfigManager.getAvailableDataSources();

			for (Entry<String, DataSourceConfig> entry : newConfig.entrySet()) {
				String key = entry.getKey();
				DataSourceConfig value = entry.getValue();
				try {
					if (dataSourceConfigsCache.get().containsKey(key)) {
						DataSourceConfig originDataSourceConfig = dataSourceConfigsCache.get().get(key);
						if (!originDataSourceConfig.toString().equals(value.toString())) {
							DataSource dataSource = initDataSources(value);
							DataSource originDataSource = dataSources.get(key);
							dataSources.put(key, dataSource);

							destoryDataSource(originDataSource);
						}
					} else {
						dataSources.put(key, initDataSources(value));
					}
				} catch (Throwable e) {
					logger.warn("cannot init new dataSource due to illegal dataSource config!", e);
				}
			}

			for (Entry<String, DataSourceConfig> entry : dataSourceConfigsCache.get().entrySet()) {
				String key = entry.getKey();
				if (!newConfig.containsKey(key)) {
					destoryDataSource(dataSources.get(key));
				}
			}

			dataSourceConfigsCache.set(newConfig);
		}

		private void destoryDataSource(DataSource dataSource) {
			if (dataSource != null) {
				boolean isSuccess = toCloseDataSource.offer(dataSource);
				if (!isSuccess) {
					logger.warn("blocking queue for closed datasources is full!");
				}
			}
		}
	}
}
