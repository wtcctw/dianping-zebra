package com.dianping.zebra.mysql.monitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.mysql.ha.DalHaHandler;

public class MySQLPingThread extends Thread {

	/* ping失败超过4次，则将该读库mark down */
	private int PING_FAIL_LIMIT = 4;

	/* 每次ping的间隔时间 */
	private int INTERVAL_SECONDS = 2;

	private DataSourceConfig config;

	private int pingFailCounter = 0;

	private MySQLConfig monitorConfig;

	public MySQLPingThread(MySQLConfig monitorConfig, DataSourceConfig config) {
		this.monitorConfig = monitorConfig;
		this.config = config;
		this.PING_FAIL_LIMIT = this.monitorConfig.getPingFailLimit();
		this.INTERVAL_SECONDS = this.monitorConfig.getPingIntervalSeconds();
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			Connection con = null;
			Statement stmt = null;

			try {
				con = DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword());
				stmt = con.createStatement();
				stmt.executeQuery(monitorConfig.getTestSql());

				if (pingFailCounter > 0) {
					pingFailCounter--;

					if (pingFailCounter == 0) {
						DalHaHandler.markup(config.getId());
					}
				}
			} catch (SQLException e) {
				pingFailCounter++;

				if (pingFailCounter == PING_FAIL_LIMIT) {
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
			} catch (InterruptedException e1) {
				break;
			}
		}
	}

	public void terminate() {
		this.interrupt();
	}
}
