package com.dianping.zebra.group.datasources;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public class DefaultSingleDataSourceManager implements SingleDataSourceManager {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSingleDataSourceManager.class);

	private Map<String, DataSourceConfig> dataSourceConfigs = new HashMap<String, DataSourceConfig>();

	private Map<String, SingleDataSource> dataSources = new HashMap<String, SingleDataSource>();

	private BlockingQueue<SingleDataSource> toBeClosedDataSource = new LinkedBlockingQueue<SingleDataSource>();

	@Override
	public synchronized SingleDataSource createDataSource(String user, DataSourceConfig config) {
		String id = config.getId();
		SingleDataSource dataSource = dataSources.get(id);

		if (dataSource == null) {
			dataSource = new SingleDataSource(config);
			dataSource.getUsers().add(user);
			dataSources.put(id, dataSource);
			dataSourceConfigs.put(id, config);
		} else {
			if (config.toString().equals(dataSourceConfigs.get(id).toString())) {
				dataSource.getUsers().add(user);
			} else {
				SingleDataSource newDataSource = new SingleDataSource(config);
				newDataSource.getUsers().addAll(dataSource.getUsers());
				this.toBeClosedDataSource.offer(dataSource);
				newDataSource.getUsers().add(user);
				this.dataSources.put(id, newDataSource);
				dataSourceConfigs.put(id, config);
			}
		}

		return dataSource;
	}

	@Override
	public synchronized void destoryDataSource(String dsId, String user) {
		SingleDataSource dataSource = dataSources.get(dsId);

		if (dataSource != null) {
			dataSource.getUsers().remove(user);

			if (dataSource.getUsers().isEmpty()) {
				this.toBeClosedDataSource.offer(dataSources.remove(dsId));
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

	class CloseDataSourceTask implements Runnable {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				SingleDataSource dataSource = null;
				try {
					dataSource = toBeClosedDataSource.take();
					dataSource.close();
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (Throwable e) {
					if (dataSource != null) {
						toBeClosedDataSource.offer(dataSource);
					}

					logger.error("Error occurs while closing ds", e);
				}
			}
		}
	}

	@Override
	public SingleDataSource getDataSource(String dsId) {
		return dataSources.get(dsId);
	}
}
