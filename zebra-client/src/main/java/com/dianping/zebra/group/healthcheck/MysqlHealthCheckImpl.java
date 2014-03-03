package com.dianping.zebra.group.healthcheck;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.SystemConfigManager;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.manager.GroupDataSourceManager;
import com.dianping.zebra.group.manager.GroupDataSourceManagerFactory;
import com.dianping.zebra.group.router.GroupDataSourceTarget;

public class MysqlHealthCheckImpl implements HealthCheck {
	// TODO thread not safe
	private int healthCheckInterval;

	// TODO thread not safe
	private int maxErrorTimes;

	private DataSourceConfigManager dataSourceConfigManager;

	private SystemConfigManager systemConfigManager;

	private GroupDataSourceManager groupDataSourceManager;

	private ConcurrentHashMap<String, AtomicInteger> dskeyFailCount = new ConcurrentHashMap<String, AtomicInteger>();

	public MysqlHealthCheckImpl(DataSourceConfigManager dataSourceConfigManager, SystemConfigManager systemConfigManager) {
		this.dataSourceConfigManager = dataSourceConfigManager;
		this.systemConfigManager = systemConfigManager;
		this.groupDataSourceManager = GroupDataSourceManagerFactory.getGroupDataSourceManger(dataSourceConfigManager);
		this.healthCheckInterval = systemConfigManager.getSystemConfig().getHealthCheckInterval();
		this.maxErrorTimes = systemConfigManager.getSystemConfig().getMaxErrorCounter();
		this.systemConfigManager.addListerner(new HealthCheckChangeListener());
		// TODO thread not safe
		init();
	}

	public int getHealthCheckInterval() {
		return healthCheckInterval;
	}

	public void setHealthCheckInterval(int healthCheckInterval) {
		this.healthCheckInterval = healthCheckInterval;
	}

	public int getMaxErrorTimes() {
		return maxErrorTimes;
	}

	public void setMaxErrorTimes(int maxErrorTimes) {
		this.maxErrorTimes = maxErrorTimes;
	}

	public void notifyException(GroupDataSourceTarget dsTarget, SQLException e) {
		// TODO comment
		if (dsTarget.isReadOnly() == false) {
			return;
		}
		if (e.getErrorCode() == 0 && e.getSQLState() == null) {
			dskeyFailCount.putIfAbsent(dsTarget.getId(), new AtomicInteger(1));
			AtomicInteger times = dskeyFailCount.get(dsTarget.getId());
			if (times.get() >= maxErrorTimes) {
				if (dataSourceConfigManager.getUnAvailableDataSources().containsKey(dsTarget.getId()) == false) {
					dataSourceConfigManager.markDown(dsTarget.getId());
				}
				// dskeyFailCount.remove(dsKey);
			} else {
				// dskeyFailCount.put(tmp.getDsKey(), times.i);
				times.incrementAndGet();
			}
		}
		// dsqueue.add(new dsException(dsKey, e));
	}

	public class HealthCheckChangeListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(Constants.ELEMENT_HEALTH_CHECK_INTERVAL)) {
				int interval = (Integer) evt.getNewValue();
				if (interval != healthCheckInterval) {
					healthCheckInterval = interval;
					// TODO
				}
			}
		}
	}

	private class checkHealthCycle implements Runnable {
		@Override
		public void run() {
			while (true) {
				Map<String, DataSourceConfig> unAvailableDsMap = dataSourceConfigManager.getUnAvailableDataSources();
				Iterator<String> iter = unAvailableDsMap.keySet().iterator();
				while (iter.hasNext()) {
					Object key = iter.next();
					if (groupDataSourceManager.isAvailable((String) key) == true) {
						dataSourceConfigManager.markUp((String) key);
					}
				}
				dskeyFailCount = new ConcurrentHashMap<String, AtomicInteger>();
				try {
					Thread.sleep(healthCheckInterval);
				} catch (InterruptedException ignore) {
				}
			}
		}
	}

	@Override
	public void init() {
		checkHealthCycle checkHealthTask = new checkHealthCycle();
		Thread thread = new Thread(checkHealthTask);
		thread.setDaemon(true);
		thread.setName("CheckHealthTask");
		thread.start();
	}
}
