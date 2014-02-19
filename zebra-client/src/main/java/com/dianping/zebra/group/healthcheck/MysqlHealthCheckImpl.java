package com.dianping.zebra.group.healthcheck;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.dianping.zebra.group.config.BaseGroupConfigChangeEvent;
import com.dianping.zebra.group.config.GroupConfigChangeListener;
import com.dianping.zebra.group.config.GroupConfigManager;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.manager.GroupDataSourceManager;
import com.dianping.zebra.group.manager.GroupDataSourceManagerFactory;

public class MysqlHealthCheckImpl implements HealthCheck {
	private int healthCheckInterval;

	private int maxErrorTimes;

	private GroupConfigManager configManager;
	
	private GroupDataSourceManager groupdatasourcemanager;

	private ConcurrentHashMap<String, AtomicInteger> dskeyFailCount = new ConcurrentHashMap<String, AtomicInteger>();

	public MysqlHealthCheckImpl(GroupConfigManager configManager) {
		this.configManager = configManager;
		this.groupdatasourcemanager = GroupDataSourceManagerFactory.getGroupDataSourceManger(configManager);
		this.healthCheckInterval = configManager.getGroupDataSourceConfig().getHealthCheckInterval();
		this.maxErrorTimes = configManager.getGroupDataSourceConfig().getHealthCheckInterval();
		configManager.addListerner(new ConfigChangeListener());
		init();
	}

	public void notifyException(String dsKey, SQLException e) {
		if (e.getErrorCode() == 0 && e.getSQLState() == null) {
			dskeyFailCount.putIfAbsent(dsKey, new AtomicInteger(1));
			AtomicInteger times = dskeyFailCount.get(dsKey);
			if (times.get() >= maxErrorTimes) {
				if(configManager.getUnAvailableDataSources().containsKey(dsKey) == false){
					configManager.markDown(dsKey);
				}
				// dskeyFailCount.remove(dsKey);
			} else {
				// dskeyFailCount.put(tmp.getDsKey(), times.i);
				times.incrementAndGet();
			}
		}
		// dsqueue.add(new dsException(dsKey, e));
	}

	public class ConfigChangeListener implements GroupConfigChangeListener {

		@Override
		public void onChange(BaseGroupConfigChangeEvent event) {
			int interval = event.getGroupDatasourceConfig().getHealthCheckInterval();
			if (interval != healthCheckInterval) {
				healthCheckInterval = interval;
				// TODO
			}
		}

		@Override
		public String getName() {
			return "health-checker-listner";
		}

	}

	private class checkHealthCycle implements Runnable {

		@Override
		public void run() {
			while (true) {
				Map<String, DataSourceConfig> unAvailableDsMap = configManager.getUnAvailableDataSources();
				Iterator iter = unAvailableDsMap.keySet().iterator(); 
				while (iter.hasNext()) { 
				    Object key = iter.next(); 
				    if(groupdatasourcemanager.isAvailable((String)key) == true){
				   	 configManager.markUp((String)key);
				    }
				} 
				dskeyFailCount = new ConcurrentHashMap<String, AtomicInteger>();
				try {
					Thread.sleep(healthCheckInterval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	private void init() {
		checkHealthCycle checkHealthTask = new checkHealthCycle();
		Thread thread = new Thread(checkHealthTask);
		thread.setDaemon(true);
		thread.setName("checkHealthTask");
		thread.start();
	}

}
