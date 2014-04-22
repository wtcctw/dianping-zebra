package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.GroupConfigException;
import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PoolBackedDataSource;

public class DefaultGroupDataSourceManager implements GroupDataSourceManager {

	private static final Logger logger = LoggerFactory.getLogger(DefaultGroupDataSourceManager.class);

	private ConcurrentMap<String, DataSourceConfig> dataSourceConfigs = new ConcurrentHashMap<String, DataSourceConfig>();

	private ConcurrentMap<String, DataSourceBizWrapper> dataSources = new ConcurrentHashMap<String, DataSourceBizWrapper>();

	private BlockingQueue<DataSource> toBeClosedDataSource = new LinkedBlockingQueue<DataSource>();

	private ConcurrentMap<String, AtomicInteger> dsSeq = new ConcurrentHashMap<String, AtomicInteger>();

	@Override
	public synchronized void createDataSource(String biz, DataSourceConfig config) {
		String id = config.getId();
		if (dataSources.get(id) == null) {
			dataSources.put(id, new DataSourceBizWrapper(initDataSources(config), biz));
			dataSourceConfigs.put(id, config);
		} else {
			DataSourceBizWrapper dataSourceBizWrapper = dataSources.get(id);
			if (config.toString().equals(dataSourceConfigs.get(id).toString())) {
				dataSourceBizWrapper.addBiz(biz);
			} else {
				DataSource oldDataSource = dataSourceBizWrapper.getDataSource();
				dataSourceBizWrapper.setDataSource(initDataSources(config));
				this.toBeClosedDataSource.offer(oldDataSource);
				dataSourceBizWrapper.addBiz(biz);
				dataSourceConfigs.put(id, config);
			}
		}
	}

	@Override
	public synchronized void destory(String dsId, String biz) {
		DataSourceBizWrapper dataSourceBizWrapper = dataSources.get(dsId);

		if (dataSourceBizWrapper != null) {
			dataSourceBizWrapper.removeBiz(biz);

			if (logger.isDebugEnabled()) {
				logger.debug(String.format("biz set %s", dataSourceBizWrapper.bizMapper));
			}

			if (dataSourceBizWrapper.isEmpty()) {
				this.toBeClosedDataSource.offer(dataSources.remove(dsId).getDataSource());
			}
		}
	}

	@Override
	public void init() {
		Thread dataSourceMonitor = new Thread(new CloseDataSourceTask());

		dataSourceMonitor.setDaemon(true);
		dataSourceMonitor.setName("Thread-" + CloseDataSourceTask.class.getSimpleName());
		dataSourceMonitor.start();
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
						logger.warn("fail to close dataSource since dataSource is null or dataSource is not an instance of PoolBackedDataSource.");
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

	public class DataSourceBizWrapper {
		private DataSource dataSource;

		private Set<String> bizMapper = new HashSet<String>();

		public DataSourceBizWrapper(DataSource ds, String biz) {
			dataSource = ds;
			bizMapper.add(biz);
		}

		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}

		public DataSource getDataSource() {
			return dataSource;
		}

		public boolean isEmpty() {
			return bizMapper.size() == 0 ? true : false;
		}

		public void addBiz(String biz) {
			bizMapper.add(biz);
		}

		public void removeBiz(String biz) {
			bizMapper.remove(biz);
		}
	}

	@Override
	public Connection getConnection(String dsId) throws SQLException {
		DataSourceBizWrapper dataSourceBizWrapper = dataSources.get(dsId);
		if (dataSourceBizWrapper != null) {
			return dataSourceBizWrapper.getDataSource().getConnection();
		}

		throw new SQLException(String.format("Cannot find dataSource for %s.", dsId));
	}
}
