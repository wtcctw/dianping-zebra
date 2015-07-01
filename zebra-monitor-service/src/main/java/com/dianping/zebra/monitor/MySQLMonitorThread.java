package com.dianping.zebra.monitor;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dianping.zebra.biz.service.HaHandler;
import com.dianping.zebra.biz.service.HaHandler.Operator;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.monitor.AlarmManager.AlarmContent;

public class MySQLMonitorThread extends Thread {

	private static final Logger logger = LogManager.getLogger(MySQLMonitorThread.class);

	private DataSourceConfig config;

	private DataSourceMonitorConfig monitorConfig;

	private HaHandler hahandler;

	private AlarmManager alarmManager;

	// 实时状态
	private volatile long lastUpdatedTime = System.currentTimeMillis();

	private int delay = 0;

	private Status currentState = Status.INIT;

	private long lastAlarmTime = 0L;

	private long ALARM_INTERVAL = 5 * 60 * 1000L;

	private String jdbcUrl;

	public MySQLMonitorThread(DataSourceMonitorConfig monitorConfig, DataSourceConfig config, HaHandler haHandler,
	      AlarmManager alarmManager) {
		this.monitorConfig = monitorConfig;
		this.config = config;

		String url = config.getJdbcUrl();
		String cleanURI = url.substring(5);
		URI uri = URI.create(cleanURI);
		this.jdbcUrl = "jdbc:" + uri.getScheme() + "://" + uri.getHost() + ":" + uri.getPort();

		this.hahandler = haHandler;
		this.alarmManager = alarmManager;
	}

	private void close(Connection con, Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ignore) {
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ingore) {
			}
		}
	}

	public void setCurrentState(Status state) {
		this.currentState = state;
	}

	public Status getCurrentState() {
		return currentState;
	}

	public long getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	@Override
	public void run() {
		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("找不到驱动程序类 ，加载驱动失败！", e);
			return;
		}

		// 确保tomcat加载应用完成，sleep 30秒
		// 同时限制不能频繁的markup和markdown，之间至少间隔30秒
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			return;
		}

		if (!config.getActive() && monitorConfig.isAutoMarkUp()) {
			doMarkUp();
		} else if (config.getActive() && monitorConfig.isAutoMarkDown()) {
			doMarkDown();
		} else {
			currentState = config.getActive() ? Status.ALIVE : Status.DEAD;
		}
	}

	private void doMarkDown() {
		FixedLengthLinkedList timestamp = new FixedLengthLinkedList(monitorConfig);
		currentState = Status.ALIVE;
		while (!Thread.currentThread().isInterrupted()) {
			Connection con = null;
			Statement stmt = null;

			try {
				con = DriverManager.getConnection(this.jdbcUrl, monitorConfig.getUsername(), monitorConfig.getPassword());
				stmt = con.createStatement();
				stmt.setQueryTimeout(1);
				stmt.executeQuery("SELECT 1");

				if (isDelay(stmt)) {
					break;
				}
				// 如果能连上，则清空队列中的异常；因为要求连续的异常
				timestamp.clear();
			} catch (SQLException e) {
				timestamp.addLast(System.currentTimeMillis());

				if (timestamp.shouldAction()) {
					hahandler.markdown(config.getId(), Operator.ZEBRA);
					logger.info("markDown " + config.getId());

					alarmManager.alarm(new AlarmContent(config.getId(), delay, "markDown"));
					break;
				}
			} finally {
				lastUpdatedTime = System.currentTimeMillis();
				close(con, stmt);
			}

			try {
				TimeUnit.SECONDS.sleep(monitorConfig.getPingIntervalSeconds());
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	private boolean isDelay(Statement stmt) {
		boolean isDelay = false;

		try {
			ResultSet rs = stmt.executeQuery("show slave status");
			while (rs.next()) {
				delay = rs.getInt("Seconds_Behind_Master");

				if (delay >= monitorConfig.getDelayTime() / 3 && delay < monitorConfig.getDelayTime() / 2) {
					if (lastAlarmTime == 0L) {
						alarmManager.alarm(new AlarmContent(config.getId(), delay, null));
					} else {
						long interval = System.currentTimeMillis() - lastAlarmTime;

						if (interval >= ALARM_INTERVAL) {
							alarmManager.alarm(new AlarmContent(config.getId(), delay, null));
						}
					}
				}

				if (delay >= monitorConfig.getDelayTime()) {
					hahandler.markdown(config.getId(), Operator.ZEBRA);
					logger.info("markDown " + config.getId());

					isDelay = true;
					alarmManager.alarm(new AlarmContent(config.getId(), delay, "markDown"));
					break;
				}

				break;
			}
		} catch (SQLException ignore) {
		}

		return isDelay;
	}

	private void doMarkUp() {
		currentState = Status.DEAD;
		FixedLengthLinkedList timestamp = new FixedLengthLinkedList(monitorConfig);

		while (!Thread.currentThread().isInterrupted()) {
			Connection con = null;
			Statement stmt = null;
			try {
				con = DriverManager.getConnection(config.getJdbcUrl(), monitorConfig.getUsername(),
				      monitorConfig.getPassword());
				stmt = con.createStatement();
				stmt.executeQuery("SELECT 1");

				timestamp.addLast(System.currentTimeMillis());

				if (timestamp.shouldAction()) {
					hahandler.markup(config.getId(), Operator.ZEBRA);
					logger.info("markUp " + config.getId());

					break;
				}
			} catch (SQLException ignore) {
				// 如果不能连上，则清空队列中正常的次数；
				timestamp.clear();
			} finally {
				lastUpdatedTime = System.currentTimeMillis();
				close(con, stmt);
			}

			try {
				TimeUnit.SECONDS.sleep(monitorConfig.getPingIntervalSeconds());
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public int getDelay() {
		return this.delay;
	}

	public void terminate() {
		this.interrupt();
	}

	public static class FixedLengthLinkedList extends LinkedList<Long> {
		private static final long serialVersionUID = 3800705659963203862L;

		private DataSourceMonitorConfig monitorConfig;

		public FixedLengthLinkedList(DataSourceMonitorConfig monitorConfig) {
			this.monitorConfig = monitorConfig;
		}

		public void addLast(Long e) {
			if (this.size() >= monitorConfig.getPingFailLimit()) {
				super.removeFirst();
			}

			super.addLast(e);
		}

		public long getDistance() {
			return super.getLast() - super.getFirst();
		}

		public boolean shouldAction() {
			return (size() == monitorConfig.getPingFailLimit()) && (getDistance() <= monitorConfig.getValidPeriod());
		}
	}
}
