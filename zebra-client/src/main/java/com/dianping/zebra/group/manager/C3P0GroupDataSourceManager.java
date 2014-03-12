package com.dianping.zebra.group.manager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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

	private BlockingQueue<DataSource> toBeClosedDataSource = new LinkedBlockingQueue<DataSource>();

	private DataSourceConfigManager groupConfigManager;

	private volatile boolean closed = false;

	private volatile String writeId;

	private ConcurrentMap<String, AtomicInteger> dsSeq = new ConcurrentHashMap<String, AtomicInteger>();

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	private Lock wLock = lock.writeLock();

	private Lock rLock = lock.readLock();

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

		rLock.lock();
		try {
			for (DataSource dataSource : this.dataSources.values()) {
				this.toBeClosedDataSource.offer(dataSource);
			}
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public Connection getReadConnection(String id) throws SQLException {
		return getConnection(id);
	}

	public DataSource getDataSource(String id) {
		rLock.lock();
		try {
			return dataSources.get(id);
		} finally {
			rLock.unlock();
		}
	}

	private Connection getConnection(String id) throws SQLException {
		checkClosed();
		DataSource dataSource = null;
		rLock.lock();
		try {
			dataSource = dataSources.get(id);
		} finally {
			rLock.unlock();
		}

		if (dataSource != null) {
			return dataSource.getConnection();
		} else {
			throw new SQLException(String.format("Fail to get connection for dataSource[%s]", id));
		}
	}

	// private boolean checkConnection(DataSource dataSource) throws Throwable {
	// Connection connection = null;
	// try {
	// connection = dataSource.getConnection();
	// return connection != null;
	// } catch (Throwable e) {
	// throw e;
	// } finally {
	// if (connection != null) {
	// connection.close();
	// }
	// }
	// }

	@Override
	public Connection getWriteConnection() throws SQLException {
		rLock.lock();
		try {
			return getConnection(this.writeId);
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public void init() {
		Map<String, DataSourceConfig> availableDataSources = groupConfigManager.getAvailableDataSources();
		for (Entry<String, DataSourceConfig> entry : availableDataSources.entrySet()) {
			String key = entry.getKey();
			DataSourceConfig value = entry.getValue();
			try {
				if (!value.isReadonly()) {
					this.writeId = key;
				}

				DataSource dataSource = initDataSources(value);
				// if (checkConnection(dataSource)) {
				this.dataSources.put(key, dataSource);
				// }
			} catch (Throwable e) {
				logger.error(String.format("fail to connect the database[%s]", key), e);
				throw new RuntimeException(e);
			}
		}

		this.dataSourceConfigsCache.set(availableDataSources);
		this.groupConfigManager.addListerner(new ConfigChangedListener());

		Thread thread = new Thread(new CloseDataSourceTask());
		thread.setDaemon(true);
		thread.setName("Thread-" + CloseDataSourceTask.class.getName());

		thread.start();
	}

	private DataSource initDataSources(DataSourceConfig value) {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		try {
			dsSeq.putIfAbsent(value.getId(), new AtomicInteger(0));

			String dsId = value.getId() + "-" + dsSeq.get(value.getId()).incrementAndGet();

			dataSource.setIdentityToken(dsId);
			dataSource.setUser(value.getUser());
			dataSource.setDriverClass(value.getDriverClass());
			dataSource.setJdbcUrl(value.getJdbcUrl());
			dataSource.setPassword(value.getPassword());
			dataSource.setInitialPoolSize(value.getInitialPoolSize());
			dataSource.setMinPoolSize(value.getMinPoolSize());
			dataSource.setMaxPoolSize(value.getMaxPoolSize());
			dataSource.setCheckoutTimeout(value.getCheckoutTimeout());
			dataSource.setConnectionCustomizerClassName(value.getConnectionCustomizeClassName());

			for (Any any : value.getProperties()) {
				MethodUtils.invokeMethod(dataSource, "set" + StringUtils.capitalize(any.getName()), any.getValue());
			}
			C3P0DataSourceRuntimeMonitor.INSTANCE.initCounter(dsId);
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

	class CloseDataSourceTask implements Runnable {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					DataSource dataSource = toBeClosedDataSource.take();

					if (dataSource != null && (dataSource instanceof ComboPooledDataSource)) {
						ComboPooledDataSource comboDataSource = (ComboPooledDataSource) dataSource;
						String dsId = comboDataSource.getIdentityToken();

						if (C3P0DataSourceRuntimeMonitor.INSTANCE.getCheckedOutCount(dsId) <= 0) {
							comboDataSource.close();
							C3P0DataSourceRuntimeMonitor.INSTANCE.removeCounter(dsId);
						} else {
							toBeClosedDataSource.offer(comboDataSource);
						}
					} else {
						// Normally not happen
						logger.warn("fail to close dataSource since dataSource is null or dataSource is not an instance of ComboPooledDataSource.");
					}

					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	class ConfigChangedListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Map<String, DataSource> newDataSources = new ConcurrentHashMap<String, DataSource>();
			List<DataSource> toBeDestoryDataSource = new ArrayList<DataSource>();
			String wDsId = null;

			Map<String, DataSourceConfig> newConfig = groupConfigManager.getAvailableDataSources();
			for (Entry<String, DataSourceConfig> entry : newConfig.entrySet()) {
				String key = entry.getKey();
				DataSourceConfig value = entry.getValue();
				try {
					if (dataSourceConfigsCache.get().containsKey(key)) {
						DataSourceConfig originDataSourceConfig = dataSourceConfigsCache.get().get(key);
						if (!originDataSourceConfig.toString().equals(value.toString())) {
							newDataSources.put(key, initDataSources(value));
							toBeDestoryDataSource.add(dataSources.get(key));
						} else {
							newDataSources.put(key, dataSources.get(key));
						}
					} else {
						newDataSources.put(key, initDataSources(value));
					}

					if (!value.isReadonly()) {
						wDsId = value.getId();
					}
				} catch (Throwable e) {
					logger.warn("cannot init new dataSource due to illegal dataSource config!", e);
				}
			}

			for (Entry<String, DataSourceConfig> entry : dataSourceConfigsCache.get().entrySet()) {
				String key = entry.getKey();
				if (!newConfig.containsKey(key)) {
					toBeDestoryDataSource.add(dataSources.get(key));
				}
			}

			wLock.lock();
			try {
				writeId = wDsId;
				dataSourceConfigsCache.set(newConfig);
				dataSources = newDataSources;
			} finally {
				wLock.unlock();
			}
			
			for (DataSource ds : toBeDestoryDataSource) {
				destoryDataSource(ds);
			}
		}

		private void destoryDataSource(DataSource dataSource) {
			if (dataSource != null) {
				boolean isSuccess = toBeClosedDataSource.offer(dataSource);
				if (!isSuccess) {
					logger.warn("blocking queue for closed datasources is full!");
				}
			}
		}
	}
}
