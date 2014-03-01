package com.dianping.zebra.group.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import junit.framework.Assert;

import org.junit.Test;


public class DPGroupPreparedStatementTest extends MultiDatabaseTestCase {
	
	private String selectSql = "select * from PERSON p where p.NAME = ?";
	
	private String updateSql = "update PERSON p set p.Name = ? where p.NAME = ?";
	
	@Test
	public void test_read_and_write_perpared_statement() throws Exception{
		execute(new ConnectionCallback() {
			@Override
			public Object doInConnection(Connection conn) throws Exception {
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement(selectSql);
				ps.setString(1, "writer");
				ResultSet executeQuery = ps.executeQuery();
				executeQuery.next();
				Assert.assertEquals("writer", executeQuery.getString(2));
				ps.close();
				
				PreparedStatement ps1 = conn.prepareStatement(updateSql);
				ps1.setString(1, "writer-new");
				ps1.setString(2, "writer");
				ps1.executeUpdate();
				ps1.close();
				
				PreparedStatement ps2 = conn.prepareStatement(selectSql);
				ps2.setString(1, "writer-new");
				ResultSet executeQuery2 = ps2.executeQuery();
				executeQuery2.next();
				Assert.assertEquals("writer-new", executeQuery2.getString(2));
				ps2.close();
				
				conn.commit();
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
