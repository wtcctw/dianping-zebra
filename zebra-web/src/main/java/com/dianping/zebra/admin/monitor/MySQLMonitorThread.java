package com.dianping.zebra.admin.monitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import com.dianping.zebra.admin.monitor.handler.HaHandler;
import com.dianping.zebra.admin.monitor.handler.HaHandler.Operator;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public class MySQLMonitorThread extends Thread {

	private DataSourceConfig config;

	private MonitorConfig monitorConfig;

	private volatile long lastUpdatedTime = 0L;

	private Status currentState = Status.ALIVE;

	private HaHandler hahandler;

	private SpringContextLoadFinished contextLoader;

	public MySQLMonitorThread(MonitorConfig monitorConfig, DataSourceConfig config, HaHandler haHandler) {
		this.monitorConfig = monitorConfig;
		this.config = config;
		this.hahandler = haHandler;
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

	public void setContextLoader(SpringContextLoadFinished contextLoader) {
		this.contextLoader = contextLoader;
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

		// 确保spring容器启动完成
		while (!Thread.currentThread().isInterrupted()) {
			if (!contextLoader.isLoaded()) {
				try {
					TimeUnit.SECONDS.sleep(monitorConfig.getPingIntervalSeconds());
				} catch (InterruptedException e) {
					break;
				}
			} else {
				break;
			}
		}

		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}
		
		// 确保tomcat加载应用完成，sleep 30秒
		// 同时限制不能频繁的markup和markdown，之间至少间隔30秒
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			return;
		}

		// 如果该库是active=false的状态，则自动ping检测markup
		if (!config.getActive()) {
			currentState = Status.DEAD;
			FixedLengthLinkedList timestamp = new FixedLengthLinkedList(monitorConfig);

			while (!Thread.currentThread().isInterrupted()) {
				Connection con = null;
				Statement stmt = null;
				try {
					con = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword());
					stmt = con.createStatement();
					stmt.setQueryTimeout(monitorConfig.getQueryTimeout());
					stmt.executeQuery(monitorConfig.getTestSql());

					timestamp.addLast(System.currentTimeMillis());

					if (timestamp.shouldAction()) {
						hahandler.markup(config.getId(), Operator.ZEBRA);

						System.out.println("markup " + config.getId());
						break;
					}
				} catch (SQLException ignore) {
					// 如果不能连上，则清空队列中正常的次数；
					ignore.printStackTrace();
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
		} else {
			// 如果该库是active=true的状态，则自动ping检测markdown
			FixedLengthLinkedList timestamp = new FixedLengthLinkedList(monitorConfig);
			currentState = Status.ALIVE;
			while (!Thread.currentThread().isInterrupted()) {
				Connection con = null;
				Statement stmt = null;

				try {
					con = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword());
					stmt = con.createStatement();
					stmt.setQueryTimeout(monitorConfig.getQueryTimeout());
					stmt.executeQuery(monitorConfig.getTestSql());

					// 如果能连上，则清空队列中的异常；因为要求连续的异常
					timestamp.clear();
				} catch (SQLException e) {
					e.printStackTrace();
					timestamp.addLast(System.currentTimeMillis());

					if (timestamp.shouldAction()) {
						hahandler.markdown(config.getId(), Operator.ZEBRA);
						System.out.println("markdown " + config.getId());

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
	}

	public void terminate() {
		this.interrupt();
	}

	public static class FixedLengthLinkedList extends LinkedList<Long> {
		private static final long serialVersionUID = 3800705659963203862L;

		private MonitorConfig monitorConfig;

		public FixedLengthLinkedList(MonitorConfig monitorConfig) {
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
