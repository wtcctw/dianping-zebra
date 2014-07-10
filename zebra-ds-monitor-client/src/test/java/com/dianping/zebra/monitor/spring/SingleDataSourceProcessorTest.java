package com.dianping.zebra.monitor.spring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dianping.zebra.monitor.sql.MonitorableDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring/appcontext-test.xml" })
public class SingleDataSourceProcessorTest {

	@Autowired
	private ComboPooledDataSource masterDs;

	@Test
	@Ignore
	public void test_c3p0_datasource_to_single_datasource()
			throws InterruptedException, SQLException {

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						query();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						query();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		Thread.sleep(3000);

		 masterDs.setUser("test2");
		 System.out.println("change username");
		 masterDs.setPassword("test2");
		 System.out.println("change password");

		Thread.sleep(3000);
	}

	private void query() throws SQLException {
		Connection conn = masterDs.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("select @@read_only");
		result.close();
		stmt.close();
		conn.close();
	}
}
