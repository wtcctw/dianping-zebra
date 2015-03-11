package com.dianping.zebra.mysql.monitor;

import java.io.IOException;

public class MySQLMonitorTest {


	public static void main(String[] args) throws IOException {
		MySQLConfig mysqlConfig = new MySQLConfig();
		mysqlConfig.setJdbcRef("test");
		mysqlConfig.setTestSql("SELECT 1");
		
		MySQLMonitor monitor = new MySQLMonitor(mysqlConfig);

		try {
			monitor.connect();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		System.in.read();
   }
}
