package com.dianping.zebra.monitor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.zebra.biz.entity.AlarmProjectConfigContent;
import com.dianping.zebra.biz.service.HaHandler;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

@Service
public class MySQLMonitorThreadGroupImpl implements MySQLMonitorThreadGroup {

	@Autowired
	private HaHandler hahandler;

	@Autowired
	private AlarmManager alarmManager;

	@Autowired
	private DataSourceMonitorConfigBuilder monitorConfigBuilder;
	
	@Autowired
	private ProjectConfigHandler projectConfigHandler;

	private Map<String, MySQLMonitorThread> monitorThreads = new ConcurrentHashMap<String, MySQLMonitorThread>();

	@Override
	public synchronized void startOrRefreshMonitor(DataSourceConfig config) {
		String dsId = config.getId();
		
		AlarmProjectConfigContent projectConfig = projectConfigHandler.getProjectConfigByKey(dsId.substring(0, dsId.indexOf("-")));
		
		DataSourceMonitorConfig monitorConfig = monitorConfigBuilder.buildMonitorConfig(dsId);

		if (!this.monitorThreads.containsKey(dsId)) {
			MySQLMonitorThread monitor = new MySQLMonitorThread(projectConfig,monitorConfig, config, hahandler, alarmManager);
			monitor.setName("Dal-Monitor-(" + config.getId() + ")");
			monitor.setDaemon(true);
			monitor.start();

			this.monitorThreads.put(dsId, monitor);
		} else {
			MySQLMonitorThread thread = this.monitorThreads.remove(dsId);
			thread.interrupt();

			MySQLMonitorThread monitor = new MySQLMonitorThread(projectConfig,monitorConfig, config, hahandler, alarmManager);
			monitor.setName("Dal-Monitor-(" + config.getId() + ")");
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
	public synchronized void removeMonitor(String dsId) {
		MySQLMonitorThread thread = this.monitorThreads.remove(dsId);
		if (thread != null) {
			thread.interrupt();
		}
	}

	@PreDestroy
	public synchronized void destory() {
		for (MySQLMonitorThread thread : this.monitorThreads.values()) {
			thread.interrupt();
		}
	}
}
