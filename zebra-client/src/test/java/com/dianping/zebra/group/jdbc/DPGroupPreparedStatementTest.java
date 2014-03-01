package com.dianping.zebra.group.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;


public class DPGroupPreparedStatementTest extends MultiDatabaseTestCase {
	
	@Test
	public void test_write_and_read_perpared_statement() throws Exception{
		execute(new ConnectionCallback() {
			@Override
			public Object doInConnection(Connection conn) throws Exception {
				PreparedStatement ps = conn.prepareStatement("select * from PERSON p where p.NAME = ?");
				
				ps.setString(1, "writer");
				
				ResultSet executeQuery = ps.executeQuery();
				
				executeQuery.next();
				
				System.out.println(executeQuery.getString(2));
				return null;
			}
		});
	}

	@Override
	protected String getConfigManagerType() {
		return "local";
	}

	@Override
	protected String getResourceId() {
		return "sample.ds";
	}

	@Override
	protected String getSchema() {
		return "src/test/resources/schema.sql";
	}

	@Override
	protected DataSourceEntry[] getDataSourceEntryArray() {
		DataSourceEntry[] entries = new DataSourceEntry[3];

		DataSourceEntry entry1 = new DataSourceEntry("jdbc:h2:mem:test;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets.xml");
		DataSourceEntry entry2 = new DataSourceEntry("jdbc:h2:mem:test1;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets1.xml");
		DataSourceEntry entry3 = new DataSourceEntry("jdbc:h2:mem:test2;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets2.xml");

		entries[0] = entry1;
		entries[1] = entry2;
		entries[2] = entry3;

		return entries;
	}	

}
