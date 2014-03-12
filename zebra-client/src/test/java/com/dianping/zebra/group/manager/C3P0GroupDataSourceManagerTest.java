package com.dianping.zebra.group.manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;

import org.junit.Test;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.jdbc.SingleDatabaseTestCase;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0GroupDataSourceManagerTest extends SingleDatabaseTestCase{

	@Test
	public void testConnect() throws SQLException, IOException {
		DataSourceConfigManager groupConfigManager = DataSourceConfigManagerFactory.getConfigManager("local",
		      "sample.ds");
		GroupDataSourceManager dataSourceManager = GroupDataSourceManagerFactory.getGroupDataSourceManger(
		      groupConfigManager, Constants.CONNECTION_POOL_TYPE_C3P0);

		Connection connection = dataSourceManager.getReadConnection("db1");

		Statement statement = connection.createStatement();

		boolean execute = statement.execute("select * from PERSON");
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
		// dataSourceManager.destory();
	}

	@Override
   protected String getConfigManagerType() {
	   return "local";
   }

	@Override
   protected String getDataSets() {
	   return "datasets.xml";
   }

	@Override
   protected String getResourceId() {
	   return "sample.ds";
   }

	@Override
   protected String getSchema() {
	   return "src/test/resources/schema.sql";
   }

}
