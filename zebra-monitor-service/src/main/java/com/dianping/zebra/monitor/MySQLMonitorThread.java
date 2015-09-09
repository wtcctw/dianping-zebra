package com.dianping.zebra.monitor;

import java.math.BigInteger;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.dianping.cat.Cat;
import com.dianping.zebra.biz.entity.AlarmProjectConfigContent;
import com.dianping.zebra.biz.entity.AlarmResource;
import com.dianping.zebra.biz.service.HaHandler;
import com.dianping.zebra.biz.service.HaHandler.Operator;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public class MySQLMonitorThread extends Thread {

	@Autowired
	ProjectConfigHandler projectConfigHandler;

	private static final Logger logger = LogManager.getLogger(MySQLMonitorThread.class);

	private DataSourceConfig config;

	private DataSourceMonitorConfig monitorConfig;

	private HaHandler hahandler;

	private AlarmManager alarmManager;

	// 实时状态
	private volatile long lastUpdatedTime = System.currentTimeMillis();

	private int delay = 0;

	private Status currentState = Status.INIT;

	private long ALARM_INTERVAL = 5 * 60 * 1000L;

	private String jdbcUrl;

	private String host;

	private AlarmProjectConfigContent projectConfig;

	public MySQLMonitorThread(AlarmProjectConfigContent projectConfig, DataSourceMonitorConfig monitorConfig,
			DataSourceConfig config, HaHandler haHandler, AlarmManager alarmManager) {
		this.projectConfig = projectConfig;
		this.monitorConfig = monitorConfig;
		this.config = config;

		String url = config.getJdbcUrl();
		String cleanURI = url.substring(5);
		URI uri = URI.create(cleanURI);
		this.jdbcUrl = "jdbc:" + uri.getScheme() + "://" + uri.getHost() + ":" + uri.getPort();
		this.host = uri.getHost();

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

		if (!config.getActive() && projectConfig.isAutoMarkup()) {
			doMarkUp();
		} else if (config.getActive()
				&& (projectConfig.isAutoMarkdownForDown() || projectConfig.isAutoMarkdownForDown())) {
			doMarkDown();
		} else {
			currentState = config.getActive() ? Status.ALIVE : Status.DEAD;
		}
	}

	private void doMarkDown() {
		FixedLengthLinkedList timestamp = new FixedLengthLinkedList(monitorConfig);
		FixedLengthLinkedList nulldurationstamp = new FixedLengthLinkedList(monitorConfig);
		long lastAlarmTime = 0L;

		currentState = Status.ALIVE;
		while (!Thread.currentThread().isInterrupted()) {
			Connection con = null;
			Statement stmt = null;

			try {
				con = DriverManager.getConnection(this.jdbcUrl, monitorConfig.getUsername(),
						monitorConfig.getPassword());
				stmt = con.createStatement();
				stmt.setQueryTimeout(1);
				stmt.executeQuery("SELECT 1");

				if (isDelay(stmt, nulldurationstamp, lastAlarmTime)) {
					break;
				}
				// 如果能连上，则清空队列中的异常；因为要求连续的异常
				timestamp.clear();
			} catch (SQLException e) {
				timestamp.addLast(System.currentTimeMillis());

				Cat.logError(e);

				if (timestamp.shouldAction()) {
					if (projectConfig.isAutoMarkdownForDown()) {
						hahandler.markdown(config.getId(), Operator.ZEBRA);
						logger.info("markDown " + config.getId());

						alarmManager.alarm(AlarmResource.MARKDOWN,
								new AlarmContent(config.getId(), "ZEBRA", "ZEBRA", host, "MarkDown", delay));

						break;
					} else {
						alarmManager.alarm(AlarmResource.MARKDOWN,
								new AlarmContent(config.getId(), "ZEBRA", "ZEBRA", host, "忽略", delay));

						timestamp.clear();
					}
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

	private boolean isDelay(Statement stmt, FixedLengthLinkedList nulldurationstamp, long lastAlarmTime) {
		boolean isDelay = false;

		try {
			ResultSet rs = stmt.executeQuery("show slave status");
			while (rs.next()) {
				Object o = rs.getObject("Seconds_Behind_Master");
				if (o == null) {
					nulldurationstamp.addLast(System.currentTimeMillis());
					if (nulldurationstamp.shouldAction()) {
						if (projectConfig.isAutoMarkdownForDelay()) {
							hahandler.markdown(config.getId(), Operator.ZEBRA);
							logger.info("markDown" + config.getId());

							alarmManager.alarm(AlarmResource.MARKDOWN,
									new AlarmContent(config.getId(), "ZEBRA", "ZEBRA", host, "MarkDown", -1));

							isDelay = true;
							break;
						} else {
							alarmManager.alarm(AlarmResource.MARKDOWN,
									new AlarmContent(config.getId(), "ZEBRA", "ZEBRA", host, "忽略", -1));

							nulldurationstamp.clear();
						}
					}
				} else if (o instanceof BigInteger) {
					delay = ((BigInteger) o).intValue();

					if (delay >= projectConfig.getMinDelayTime()) {
						if (lastAlarmTime == 0L) {
							alarmManager.alarm(AlarmResource.DELAY,
									new AlarmContent(config.getId(), "ZEBRA", "ZEBRA", host, null, delay));

							lastAlarmTime = System.currentTimeMillis();
						} else {
							long interval = System.currentTimeMillis() - lastAlarmTime;

							if (interval >= ALARM_INTERVAL) {
								alarmManager.alarm(AlarmResource.DELAY,
										new AlarmContent(config.getId(), "ZEBRA", "ZEBRA", host, null, delay));
							}
						}
					}

					if (delay >= projectConfig.getMaxDelayTime() && projectConfig.isAutoMarkdownForDelay()) {
							hahandler.markdown(config.getId(), Operator.ZEBRA);
							logger.info("markDown " + config.getId());

							isDelay = true;
							alarmManager.alarm(AlarmResource.MARKDOWN,
									new AlarmContent(config.getId(), "ZEBRA", "ZEBRA", host, "MarkDowN", delay));

							break;
					}

					nulldurationstamp.clear();
				} else {
					logger.error("unknown Seconds_Behind_Master:" + config.getId());
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
				con = DriverManager.getConnection(this.jdbcUrl, monitorConfig.getUsername(),
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
				Cat.logError(ignore);
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
