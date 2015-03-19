package com.dianping.zebra.admin.monitor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public class MySQLMonitorManagerImpl implements MySQLMonitorManager {

	private MonitorConfig monitorConfig;

	private Map<String, MySQLMonitorThread> monitorThreads = new ConcurrentHashMap<String, MySQLMonitorThread>();

	@Override
	public synchronized void startOrRefreshMonitor(DataSourceConfig dsConfig) {
		String dsId = dsConfig.getId();
		if (!this.monitorThreads.containsKey(dsId)) {
			MySQLMonitorThread monitor = new MySQLMonitorThread(this.monitorConfig, dsConfig);
			monitor.setName("Dal-Monitor-Slave(" + dsConfig.getId() + ")");
			monitor.setDaemon(true);
			monitor.start();

			this.monitorThreads.put(dsId, monitor);
		}else{
			MySQLMonitorThread thread = this.monitorThreads.remove(dsId);
			thread.interrupt();

			MySQLMonitorThread monitor = new MySQLMonitorThread(this.monitorConfig, dsConfig);
			monitor.setName("Dal-Monitor-Slave(" + dsConfig.getId() + ")");
			monitor.setDaemon(true);
			monitor.start();

			this.monitorThreads.put(dsId, monitor);
		}
	}

	@Override
	public synchronized Map<String, MySQLMonitorThread> getMonitors() {
		return monitorThreads;
	}

	@Override
	public void init(MonitorConfig monitorConfig) {
		this.monitorConfig = monitorConfig;
	}

	@Override
	public synchronized void removeMonitor(String dsId) {
		MySQLMonitorThread thread = this.monitorThreads.remove(dsId);
		if (thread != null) {
			thread.interrupt();
		}
	}
}
