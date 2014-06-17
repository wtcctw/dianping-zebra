package com.dianping.zebra.group.monitor;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public interface SingleDataSourceMBean {

	public String getId();

	public DataSourceConfig getConfig();

	public String getCurrentState();

	public int getNumConnections();

	public int getNumBusyConnection();

	public int getNumIdleConnection();

	public int getNumUnClosedOrphanedConnections();

	public long getNumFailedCheckins();

	public long getNumFailedCheckouts();

	public int getThreadPoolSize();

	public int getThreadPoolNumActiveThreads();

	public int getThreadPoolNumIdleThreads();

	public int getThreadPoolNumTasksPending();
}
