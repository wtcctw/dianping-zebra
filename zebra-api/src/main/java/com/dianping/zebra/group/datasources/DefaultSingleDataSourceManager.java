package com.dianping.zebra.group.datasources;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.DalException;
import com.dianping.zebra.group.filter.JdbcFilter;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DefaultSingleDataSourceManager implements SingleDataSourceManager {

	private Thread dataSourceMonitor;

	private BlockingQueue<SingleDataSource> toBeClosedDataSource = new LinkedBlockingQueue<SingleDataSource>();

	@Override
	public synchronized SingleDataSource createDataSource(DataSourceConfig config,
			List<JdbcFilter> filters) {
		return new SingleDataSource(config, filters);
	}

	@Override
	public synchronized void destoryDataSource(SingleDataSource dataSource) {
		if (dataSource != null) {
			this.toBeClosedDataSource.offer(dataSource);
		}
	}

	@Override
	public synchronized void init() {
		if (dataSourceMonitor == null) {
			dataSourceMonitor = new Thread(new CloseDataSourceTask());

			dataSourceMonitor.setDaemon(true);
			dataSourceMonitor.setName("Dal-" + CloseDataSourceTask.class.getSimpleName());
			dataSourceMonitor.start();
		}
	}

	class CloseDataSourceTask implements Runnable {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				SingleDataSource dataSource = null;
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
				} catch (Exception ignore) {
				}
			}
		}
	}
}
