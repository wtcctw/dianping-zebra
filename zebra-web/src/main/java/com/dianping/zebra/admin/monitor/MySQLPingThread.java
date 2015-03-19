package com.dianping.zebra.admin.monitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import com.dianping.zebra.admin.monitor.handler.DalHaHandler;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public class MySQLPingThread extends Thread {

	private int pingLimit;

	private int pingIntervalSeconds;

	private DataSourceConfig config;

	private MySQLConfig monitorConfig;

	private final long validPeriod = 30 * 1000; // 30 seconds

	public MySQLPingThread(MySQLConfig monitorConfig, DataSourceConfig config) {
		this.monitorConfig = monitorConfig;
		this.config = config;
		this.pingLimit = this.monitorConfig.getPingFailLimit();
		this.pingIntervalSeconds = this.monitorConfig.getPingIntervalSeconds();
	}

	@Override
	public void run() {
		// 如果该库是active=false的状态，则自动ping检测markup
		if (!config.getActive()) {
			FixedLengthLinkedList timestamp = new FixedLengthLinkedList(this.pingLimit, this.validPeriod);

			while (!Thread.currentThread().isInterrupted()) {
				Connection con = null;
				Statement stmt = null;

				try {
					con = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword());
					stmt = con.createStatement();
					stmt.setQueryTimeout(1);
					stmt.executeQuery(monitorConfig.getTestSql());

					timestamp.addLast(System.currentTimeMillis());

					if (timestamp.shouldAction()) {
						DalHaHandler.markup(config.getId());

						System.out.println("markup " + config.getId());
						break;
					}
				} catch (SQLException ignore) {
					//如果不能连上，则清空队列中正常的次数；
					timestamp.clear();
				} finally {
					close(con, stmt);
				}

				try {
					TimeUnit.SECONDS.sleep(pingIntervalSeconds);
				} catch (InterruptedException e) {
					break;
				}
			}
		}

		// 如果该库是active=true的状态，则自动ping检测markdown
		FixedLengthLinkedList timestamp = new FixedLengthLinkedList(this.pingLimit, this.validPeriod);
		while (!Thread.currentThread().isInterrupted()) {
			Connection con = null;
			Statement stmt = null;

			try {
				con = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword());
				stmt = con.createStatement();
				stmt.setQueryTimeout(1);
				stmt.executeQuery(monitorConfig.getTestSql());

				//如果能连上，则清空队列中的异常；因为要求连续的异常
				timestamp.clear();
			} catch (SQLException e) {
				timestamp.addLast(System.currentTimeMillis());

				if (timestamp.shouldAction()) {
					DalHaHandler.markdown(config.getId());
					System.out.println("markdown " + config.getId());

					break;
				}
			} finally {
				close(con, stmt);
			}

			try {
				TimeUnit.SECONDS.sleep(pingIntervalSeconds);
			} catch (InterruptedException e) {
				break;
			}
		}
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

	public void terminate() {
		this.interrupt();
	}

	public static class FixedLengthLinkedList extends LinkedList<Long> {
		private static final long serialVersionUID = 3800705659963203862L;

		private final int maxLength;

		private final long validPeriod;

		public FixedLengthLinkedList(int maxLength, long validPeriod) {
			this.maxLength = maxLength;
			this.validPeriod = validPeriod;
		}

		public void addLast(Long e) {
			if (this.size() >= this.maxLength) {
				super.removeFirst();
			}

			super.addLast(e);
		}

		public long getDistance() {
			return super.getLast() - super.getFirst();
		}
		
		public boolean shouldAction() {
			return (size() == maxLength) && (getDistance() <= validPeriod);
		}
	}
}
