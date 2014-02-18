package com.dianping.zebra.group.healthcheck;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.dianping.zebra.group.config.BaseGroupConfigChangeEvent;
import com.dianping.zebra.group.config.GroupConfigChangeListener;
import com.dianping.zebra.group.config.GroupConfigManager;

public class MysqlHealthCheckImpl implements GroupConfigChangeListener, HealthCheck {
	private int healthCheckInterval;

	private int maxErrorTimes;

	private GroupConfigManager configManager;

	private Map<String, Integer> dskeyFailCount = new ConcurrentHashMap<String, Integer>();

	private LinkedBlockingQueue<dsException> dsqueue = new LinkedBlockingQueue<dsException>();

	public MysqlHealthCheckImpl(GroupConfigManager configManager) {
		this.configManager = configManager;
		// this.healthCheckInterval = configManager.getXX();
		init();
	}

	public void notifyException(String dsKey, SQLException e) {
		dsqueue.add(new dsException(dsKey, e));
	}

	@Override
	public void onChange(BaseGroupConfigChangeEvent event) {
		// TODO Auto-generated method stub

	}

	private class checkHealthCycle implements Runnable {

		@Override
		public void run() {
			while (true) {
				// TODO
				dskeyFailCount.clear();
				try {
					Thread.sleep(healthCheckInterval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	private class dsExceptionStat implements Runnable {

		@Override
		public void run() {
			while (true) {
				dsException tmp = dsqueue.poll();
				//judge condition
				if (tmp.getE().getErrorCode() == 0 && tmp.getE().getSQLState() == null) {
					Integer times = dskeyFailCount.get(tmp.getDsKey());
					if (times == null) {
						dskeyFailCount.put(tmp.getDsKey(), 1);
					} else {
						if (times >= maxErrorTimes) {
							// TODO
							configManager.markDown(tmp.getDsKey());
							dskeyFailCount.remove(tmp.getDsKey());
						} else {
							dskeyFailCount.put(tmp.getDsKey(), times + 1);
						}
					}
				}
			}

		}

	}

	private class dsException {
		String dsKey;

		SQLException e;

		public String getDsKey() {
			return dsKey;
		}

		public void setDsKey(String dsKey) {
			this.dsKey = dsKey;
		}

		public SQLException getE() {
			return e;
		}

		public void setE(SQLException e) {
			this.e = e;
		}

		public dsException(String dsKey, SQLException e) {
			super();
			this.dsKey = dsKey;
			this.e = e;
		}
	}

	private void init() {
		checkHealthCycle checkHealthThread = new checkHealthCycle();
		dsExceptionStat dsExceptionStatThread = new dsExceptionStat();
		checkHealthThread.run();
		dsExceptionStatThread.run();
	}

	@Override
	public String getName() {
		return "health-checke-listener";
	}

}
