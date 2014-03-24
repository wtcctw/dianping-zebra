package com.dianping.zebra.group.manager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.GroupConfigException;
import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PoolBackedDataSource;

public class C3P0GroupDataSourceManager implements GroupDataSourceManager {

	public static final String ID = "c3p0";

	private static final Logger logger = LoggerFactory.getLogger(C3P0GroupDataSourceManager.class);

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
				if (value.getCanWrite()) {
					this.writeId = key;
				}

				DataSource dataSource = initDataSources(value);
				this.dataSources.put(key, dataSource);
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
		try {
			DataSource unPooledDataSource = DataSources.unpooledDataSource(value.getJdbcUrl(), value.getUser(),
			      value.getPassword());

			Map<String, Object> props = new HashMap<String, Object>();

			props.put("driverClass", value.getDriverClass());
			props.put("initialPoolSize", value.getInitialPoolSize());
			props.put("minPoolSize", value.getMinPoolSize());
			props.put("maxPoolSize", value.getMaxPoolSize());
			props.put("checkoutTimeout", value.getCheckoutTimeout());

			for (Any any : value.getProperties()) {
				props.put(any.getName(), any.getValue());
			}

			PoolBackedDataSource pooledDataSource = (PoolBackedDataSource) DataSources.pooledDataSource(
			      unPooledDataSource, props);
			dsSeq.putIfAbsent(value.getId(), new AtomicInteger(0));
			String dsId = value.getId() + "-" + dsSeq.get(value.getId()).incrementAndGet();
			pooledDataSource.setIdentityToken(dsId);

			logger.info(String.format("dataSource [%s] created. Properties are [%s]", dsId,
			      ReflectionToStringBuilder.toString(pooledDataSource)));
			return pooledDataSource;
		} catch (Throwable e) {
			throw new GroupConfigException(e);
		}
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

					if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
						PoolBackedDataSource poolBackedDataSource = (PoolBackedDataSource) dataSource;
						String dsId = poolBackedDataSource.getIdentityToken();

						if (poolBackedDataSource.getNumBusyConnections() == 0) {
							logger.info("closing the datasource : " + dsId);
							poolBackedDataSource.close();
							logger.info("datasource : " + dsId + " closed");
						} else {
							toBeClosedDataSource.offer(poolBackedDataSource);
						}
					} else {
						// Normally not happen
						logger.warn("fail to close dataSource since dataSource is null or dataSource is not an instance of ComboPooledDataSource.");
					}

					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (Throwable e) {
					logger.error("Error occurs while closing ds", e);
				}
			}
		}
	}

	class ConfigChangedListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			logger.info("detect dataSourceConfig changed");
			Map<String, DataSource> newDataSources = new ConcurrentHashMap<String, DataSource>();
			List<DataSource> toBeDestoryDataSource = new ArrayList<DataSource>();
			String wDsId = null;

			Map<String, DataSourceConfig> newConfig = groupConfigManager.getAvailableDataSources();
			try {
				for (Entry<String, DataSourceConfig> entry : newConfig.entrySet()) {
					String key = entry.getKey();
					DataSourceConfig value = entry.getValue();
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

					if (value.getCanWrite()) {
						wDsId = value.getId();
					}
				}

				Set<String> idSets = new HashSet<String>();
				for (Entry<String, DataSourceConfig> entry : dataSourceConfigsCache.get().entrySet()) {
					String key = entry.getKey();
					if (!newConfig.containsKey(key)) {
						toBeDestoryDataSource.add(dataSources.get(key));
						idSets.add(((PoolBackedDataSource) dataSources.get(key)).getIdentityToken());
					}
				}

				logger.info(String.format("New dataSource config changed from %s to %s",
				      printDataSourceConfig(dataSourceConfigsCache.get()), printDataSourceConfig(newConfig)));

				logger.info(String.format("To destroy following dataSources: %s", idSets));

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
			} catch (Throwable e) {
				logger.warn("cannot refresh dataSource!", e);
			}
		}

		private String printDataSourceConfig(Map<String, DataSourceConfig> config) {
			StringBuilder sb = new StringBuilder("{");
			boolean isFirst = true;

			for (Entry<String, DataSourceConfig> entry : config.entrySet()) {
				if (isFirst) {
					isFirst = false;
				} else {
					sb.append(",");
				}
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(ReflectionToStringBuilder.toString(entry.getValue()));
			}

			sb.append("}");

			return sb.toString();
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
