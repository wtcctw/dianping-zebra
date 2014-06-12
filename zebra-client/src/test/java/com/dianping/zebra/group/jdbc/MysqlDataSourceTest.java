package com.dianping.zebra.group.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.dianping.zebra.group.Constants;

public class MysqlDataSourceTest extends MultiDatabaseTestCase{
	
	@Test
	public void test_read_only() throws Exception{
		execute(new ConnectionCallback() {
			
			@Override
			public Object doInConnection(Connection conn) throws Exception {
				Statement stmt = conn.createStatement();
				
				 ResultSet resultSet = stmt.executeQuery("select @@read_only");
				 
				 while (resultSet.next()) {
						// switch database
					 System.out.println(resultSet.getInt(1));
				 }
				return null;
			}
		});
	}
	

	@Override
	protected String getConfigManagerType() {
		return Constants.CONFIG_MANAGER_TYPE_LOCAL;
	}

	@Override
	protected String getResourceId() {
		return "sample.mysql.v3";
	}

	@Override
	protected String getSchema() {
		return  getClass().getResource("/schema.sql").getPath();
	}

	@Override
	protected DataSourceEntry[] getDataSourceEntryArray() {
		DataSourceEntry[] entries = new DataSourceEntry[3];

		DataSourceEntry entry1 = new DataSourceEntry("jdbc:h2:mem:test;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets.xml", true);
		DataSourceEntry entry2 = new DataSourceEntry("jdbc:h2:mem:test1;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets1.xml",
		      false);
		DataSourceEntry entry3 = new DataSourceEntry("jdbc:h2:mem:test2;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets2.xml",
		      false);

		entries[0] = entry1;
		entries[1] = entry2;
		entries[2] = entry3;

		return entries;
	}
}
