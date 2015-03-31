package com.dianping.zebra.admin.monitor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.zebra.admin.monitor.handler.HaHandler;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

@Service
public class MySQLMonitorThreadGroupImpl implements MySQLMonitorThreadGroup {

	@Autowired
	private MonitorConfig monitorConfig;

	@Autowired
	private HaHandler hahandler;
	
	@Autowired
	private SpringContextLoadFinished contextLoader;
	
	private Map<String, MySQLMonitorThread> monitorThreads = new ConcurrentHashMap<String, MySQLMonitorThread>();

	@Override
	public synchronized void startOrRefreshMonitor(DataSourceConfig dsConfig, boolean isMHA) {
		String dsId = dsConfig.getId();
		if (!this.monitorThreads.containsKey(dsId)) {
			MySQLMonitorThread monitor = new MySQLMonitorThread(this.monitorConfig, dsConfig, hahandler);
			monitor.setName("Dal-Monitor-Slave(" + dsConfig.getId() + ")");
			monitor.setContextLoader(contextLoader);
			monitor.setDaemon(true);

			if (isMHA) {
				monitor.setCurrentState(Status.MHA_MARK_DOWN);
			} else {
				monitor.start();
			}

			this.monitorThreads.put(dsId, monitor);
		} else {
			MySQLMonitorThread thread = this.monitorThreads.remove(dsId);
			thread.interrupt();

			MySQLMonitorThread monitor = new MySQLMonitorThread(this.monitorConfig, dsConfig, hahandler);
			monitor.setName("Dal-Monitor-Slave(" + dsConfig.getId() + ")");
			monitor.setContextLoader(contextLoader);
			monitor.setDaemon(true);

			if (isMHA) {
				monitor.setCurrentState(Status.MHA_MARK_DOWN);
			} else {
				monitor.start();
			}

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
	public synchronized void destory(){
		for(MySQLMonitorThread thread : this.monitorThreads.values()){
			thread.interrupt();
		}
	}
	
	@Override
   public synchronized void suspendMonitor(String dsId, boolean isMHA) {
		if(monitorThreads.containsKey(dsId)){
			MySQLMonitorThread monitor = monitorThreads.get(dsId);
			monitor.interrupt();

			if(isMHA){
				monitor.setCurrentState(Status.MHA_MARK_DOWN);
			}
		}
   }
}
