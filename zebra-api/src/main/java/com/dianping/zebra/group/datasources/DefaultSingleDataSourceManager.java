package com.dianping.zebra.group.datasources;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.DalException;

public class DefaultSingleDataSourceManager implements SingleDataSourceManager {

	private BlockingQueue<InnerSingleDataSource> toBeClosedDataSource = new LinkedBlockingQueue<InnerSingleDataSource>();

	@Override
	public synchronized InnerSingleDataSource createDataSource(DataSourceConfig config) {
		return new InnerSingleDataSource(config);
	}

	@Override
	public synchronized void destoryDataSource(InnerSingleDataSource dataSource) {
		if (dataSource != null) {
			this.toBeClosedDataSource.offer(dataSource);
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
				InnerSingleDataSource dataSource = null;
				try {
					dataSource = toBeClosedDataSource.take();
					dataSource.close();
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (DalException e) {
					if (dataSource != null) {
						toBeClosedDataSource.offer(dataSource);
					}
				} catch (Throwable ignore) {
				}
			}
		}
	}
}
