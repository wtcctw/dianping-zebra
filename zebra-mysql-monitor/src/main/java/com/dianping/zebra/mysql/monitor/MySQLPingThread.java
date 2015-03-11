package com.dianping.zebra.mysql.monitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.mysql.ha.DalHaHandler;

public class MySQLPingThread extends Thread {

	/* ping失败超过4次，则将该读库mark down ; 或者成功超过4次，则将该读库mark up */
	private int PING_LIMIT = 4;

	/* 每次ping的间隔时间 */
	private int INTERVAL_SECONDS = 2;

	private DataSourceConfig config;

	private int pingFailCounter = 0;

	private int pingSuccessCounter = 0;

	private MySQLConfig monitorConfig;

	private int validPeriod;

	public MySQLPingThread(MySQLConfig monitorConfig, DataSourceConfig config) {
		this.monitorConfig = monitorConfig;
		this.config = config;
		this.PING_LIMIT = this.monitorConfig.getPingFailLimit();
		this.INTERVAL_SECONDS = this.monitorConfig.getPingIntervalSeconds();
		this.validPeriod = this.PING_LIMIT * this.INTERVAL_SECONDS * 1000;
	}

	@Override
	public void run() {
		// 如果该库是active=false的状态，则自动ping检测markup
		if (!config.getActive()) {
			while (!Thread.currentThread().isInterrupted()) {
				Connection con = null;
				Statement stmt = null;

				try {
					con = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword());
					stmt = con.createStatement();
					stmt.executeQuery(monitorConfig.getTestSql());

					this.pingSuccessCounter++;

					if (pingSuccessCounter == PING_LIMIT) {
						DalHaHandler.markup(config.getId());

						break;
					}
				} catch (SQLException ignore) {
				}

				try {
					TimeUnit.SECONDS.sleep(INTERVAL_SECONDS);
				} catch (InterruptedException e) {
					break;
				}
			}
		}

		// 如果该库是active=true的状态，则自动ping检测markdown
		while (!Thread.currentThread().isInterrupted()) {
			Connection con = null;
			Statement stmt = null;

			try {
				con = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword());
				stmt = con.createStatement();
				stmt.executeQuery(monitorConfig.getTestSql());
			} catch (SQLException e) {
				if (pingFailCounter < PING_LIMIT) {
					pingFailCounter++;
				} else if (pingFailCounter == PING_LIMIT) {
					DalHaHandler.markdown(config.getId());
				}
			} finally {
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

			try {
				TimeUnit.SECONDS.sleep(INTERVAL_SECONDS);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public void terminate() {
		this.interrupt();
	}
}
