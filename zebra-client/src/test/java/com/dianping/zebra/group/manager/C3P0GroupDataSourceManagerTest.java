package com.dianping.zebra.group.manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;

import org.junit.Test;

import com.dianping.zebra.group.config1.GroupConfigManager;
import com.dianping.zebra.group.config1.GroupConfigManagerFactory;
import com.dianping.zebra.group.config1.LocalGroupConfigManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0GroupDataSourceManagerTest {

	@Test
	public void testConnect() throws SQLException, IOException {
		GroupConfigManager groupConfigManager = GroupConfigManagerFactory.getConfigManger(LocalGroupConfigManager.ID,
		      "datasources.xml");
		GroupDataSourceManager dataSourceManager = GroupDataSourceManagerFactory
		      .getGroupDataSourceManger(groupConfigManager);

		Connection connection = dataSourceManager.getConnection("db1");
		dataSourceManager.getConnection("db1");

		Statement statement = connection.createStatement();

		boolean execute = statement.execute("select * from test");
		ResultSet resultSet = statement.getResultSet();

		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);

			System.out.println(id + "-" + name);
		}

		Assert.assertEquals(true, execute);


		C3P0GroupDataSourceManager c3p0 = (C3P0GroupDataSourceManager) dataSourceManager;

		ComboPooledDataSource c3p0DataSource = (ComboPooledDataSource) c3p0.getDataSource("db1");
		

		System.out.println(c3p0DataSource.getUser());
		System.out.println(c3p0DataSource.getPassword());
		System.out.println(c3p0DataSource.getInitialPoolSize());
		System.out.println(c3p0DataSource.getMaxPoolSize());
		System.out.println(c3p0DataSource.getDriverClass());
		System.out.println(c3p0DataSource.getConnectionCustomizerClassName());
		connection.close();
		c3p0DataSource.close();
		//System.in.read();
		// dataSourceManager.destory();
	}

}
