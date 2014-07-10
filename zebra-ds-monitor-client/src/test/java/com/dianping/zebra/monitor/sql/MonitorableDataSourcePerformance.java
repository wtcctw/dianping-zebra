/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-7
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.monitor.sql;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author danson.liu
 *
 */
public class MonitorableDataSourcePerformance {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws SQLException, InterruptedException {
		DataSource dataSource = createMonitorableDataSource();
		Connection connection = dataSource.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.execute(getCreateTableScript());
			loadDatas(connection);
			CountDownLatch latch = new CountDownLatch(60);
			for (int i = 0; i < 40; i++) {
				new QueryPersonTask(dataSource, latch).start();
			}
			for (int i = 0; i < 20; i++) {
				new InsertPersonTask(dataSource, latch).start();
			}
			System.out.println("complete1!");
			latch.await();
			statement.execute(getDropTableScript());
			System.out.println("complete2: " + latch.getCount());
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		} finally {
			System.out.println("here");
			connection.close();
			System.exit(0);
		}
	}
	
	static Random random = new Random();

	static class QueryPersonTask extends Thread {
		private final DataSource 		dataSource;
		private final CountDownLatch 	latch;
		public QueryPersonTask(DataSource dataSource, CountDownLatch latch) {
			this.dataSource = dataSource;
			this.latch = latch;
		}
		@Override
		public void run() {
			String sql = "select * from person";
			Connection connection = null;
			ResultSet resultSet = null;
			Statement statement = null;
			try {
				connection = dataSource.getConnection();
				statement = connection.createStatement();
				for (int i = 0; i < 5000; i++) {
					try {
						resultSet = statement.executeQuery(sql);
						while (resultSet.next()) {
	//						System.out.println(resultSet.getInt(1));
	//						if (random.nextInt(1000) % 30 == 0) {
	//							System.out.print(".");
	//						}
						}
					} finally {
						if (resultSet != null) {
							try {
								resultSet.close();
							} catch(Exception e) {
								e.printStackTrace();
							}
						}
					}
					try {
						Thread.sleep(random.nextInt(500));
					} catch (InterruptedException e) {
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					statement.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					connection.close();
				} catch (SQLException e) {
				}
				latch.countDown();
			}
		}
	}
	
	static class InsertPersonTask extends Thread {
		private final DataSource 		dataSource;
		private final CountDownLatch 	latch;
		public InsertPersonTask(DataSource dataSource, CountDownLatch latch) {
			this.dataSource = dataSource;
			this.latch = latch;
			setDaemon(true);
		}
		@Override
		public void run() {
			String sql = "insert into person(name, age, gender, height) values(?, ?, ?, ?)";
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			try {
				connection = dataSource.getConnection();
				for (int i = 0; i < 500; i++) {
					try {
						prepareStatement = connection.prepareStatement(sql);
						prepareStatement.setString(1, "john" + i);
						prepareStatement.setInt(2, i % 2 == 0 ? 24 : 25);
						prepareStatement.setString(3, i % 2 == 0 ? "male" : "female");
						prepareStatement.setInt(4, 140 + i);
						prepareStatement.execute();
					} finally {
						prepareStatement.close();
					}
					try {
						Thread.sleep(random.nextInt(300));
					} catch (InterruptedException e) {
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				latch.countDown();
			}
		}
	}
	
	private static String getCreateTableScript() {
		return "create table if not exists person ("
				+ "id 		IDENTITY,"
				+ "name 	VARCHAR(255),"
				+ "age		INT,"
				+ "gender 	VARCHAR(20),"
				+ "height 	INT"
				+ ")";
	}

	private static String getDropTableScript() {
		return "drop table person";
	}
	
	private static void loadDatas(Connection connection) throws SQLException {
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(
					"insert into person(name, age, gender, height) values(?, ?, ?, ?)"
			);
			for (int i = 0; i < 100; i++) {
				prepareStatement.setString(1, "john" + i);
				prepareStatement.setInt(2, i % 2 == 0 ? 24 : 25);
				prepareStatement.setString(3, i % 2 == 0 ? "male" : "female");
				prepareStatement.setInt(4, 140 + i);
				prepareStatement.addBatch();
			}
			prepareStatement.executeBatch();
		} finally {
			prepareStatement.close();
		}
	}

	private static MonitorableDataSource createMonitorableDataSource() {
		try {
			ComboPooledDataSource c3p0DataSource = new ComboPooledDataSource();
			c3p0DataSource.setJdbcUrl("jdbc:h2:mem:sql-monitor-db;DB_CLOSE_DELAY=-1;MULTI_THREADED=1;LOCK_TIMEOUT=10000");
			c3p0DataSource.setDriverClass("org.h2.Driver");
			c3p0DataSource.setMinPoolSize(20);
			c3p0DataSource.setMaxPoolSize(400);
			c3p0DataSource.setInitialPoolSize(200);
			c3p0DataSource.setNumHelperThreads(6);
			c3p0DataSource.setMaxAdministrativeTaskTime(5);
			return new MonitorableDataSource(c3p0DataSource);
		} catch (PropertyVetoException e) {
			throw new RuntimeException("create c3p0 datasource failed.", e);
		}
	}

}
