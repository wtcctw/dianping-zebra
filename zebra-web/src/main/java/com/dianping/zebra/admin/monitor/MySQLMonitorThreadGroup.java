package com.dianping.zebra.admin.monitor;

import java.util.Map;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public interface MySQLMonitorThreadGroup {

	public void startOrRefreshMonitor(DataSourceConfig dsConfig, boolean isMHA);

	public void suspendMonitor(String dsId, boolean isMHA);

	public void removeMonitor(String dsId);

	public Map<String, MySQLMonitorThread> getMonitors();
}
