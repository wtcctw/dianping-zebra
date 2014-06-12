package com.dianping.zebra.group.datasources;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.SingleDataSourceException;
import com.dianping.zebra.group.jdbc.AbstractDataSource;

public class DefaultSingleDataSourceManager implements SingleDataSourceManager {

	private Map<String, DataSourceConfig> dataSourceConfigs = new HashMap<String, DataSourceConfig>();

	private Map<String, SingleDataSource> dataSources = new HashMap<String, SingleDataSource>();

	private BlockingQueue<SingleDataSource> toBeClosedDataSource = new LinkedBlockingQueue<SingleDataSource>();

	@Override
	public synchronized SingleDataSource createDataSource(AbstractDataSource reference, DataSourceConfig config) {
		String id = config.getId();
		SingleDataSource dataSource = dataSources.get(id);

		if (dataSource == null) {
			dataSource = new SingleDataSource(config);
			dataSource.getReferences().add(reference);
			dataSources.put(id, dataSource);
			dataSourceConfigs.put(id, config);
		} else {
			if (config.toString().equals(dataSourceConfigs.get(id).toString())) {
				dataSource.getReferences().add(reference);
			} else {
				SingleDataSource newDataSource = new SingleDataSource(config);
				newDataSource.getReferences().addAll(dataSource.getReferences());
				this.toBeClosedDataSource.offer(dataSource);
				newDataSource.getReferences().add(reference);
				this.dataSources.put(id, newDataSource);
				dataSourceConfigs.put(id, config);

				return newDataSource;
			}
		}

		return dataSource;
	}

	@Override
	public synchronized void destoryDataSource(String dsId, AbstractDataSource reference) {
		SingleDataSource dataSource = dataSources.get(dsId);

		if (dataSource != null) {
			dataSource.getReferences().remove(reference);

			if (dataSource.getReferences().isEmpty()) {
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
				} catch (SingleDataSourceException e) {
					if (dataSource != null) {
						toBeClosedDataSource.offer(dataSource);
					}
				} catch (Throwable ignore) {
				}
			}
		}
	}

	@Override
	public SingleDataSource getDataSource(String dsId) {
		return dataSources.get(dsId);
	}
}
