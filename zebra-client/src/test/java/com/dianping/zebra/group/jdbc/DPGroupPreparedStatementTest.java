package com.dianping.zebra.group.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

public class DPGroupPreparedStatementTest extends MultiDatabaseTestCase {

	private String selectSql = "select * from PERSON p where p.AGE = ?";

	private String updateSql = "update PERSON p set p.Name = ? where p.NAME = ?";

	private void assertResultInReadDs(String res) {
		Assert.assertTrue(Arrays.asList(new String[] { "reader1", "reader2" }).contains(res));
	}

	@Test
	public void test_read_and_write_perpared_statement() throws Exception {
		execute(new ConnectionCallback() {
			@Override
			public Object doInConnection(Connection conn) throws Exception {
				String name = (String) executeOnRealDB(new ConnectionCallback() {

					@Override
					public Object doInConnection(Connection conn) throws Exception {
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("select * from PERSON");
						if (rs.next()) {
							return rs.getString(2);
						} else {
							return null;
						}
					}
				}, true, -1);
				Assert.assertEquals("writer", name);

				PreparedStatement ps = conn.prepareStatement(selectSql);
				ps.setInt(1, 18);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				rs.next();
				assertResultInReadDs(rs.getString(2));
				ps.close();

				PreparedStatement ps1 = conn.prepareStatement(updateSql);
				ps1.setString(1, "writer-new");
				ps1.setString(2, "writer");
				ps1.executeUpdate();
				ps1.close();

				name = (String) executeOnRealDB(new ConnectionCallback() {

					@Override
					public Object doInConnection(Connection conn) throws Exception {
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("select * from PERSON");
						if (rs.next()) {
							return rs.getString(2);
						} else {
							return null;
						}
					}
				}, true, -1);
				Assert.assertEquals("writer-new", name);

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
		return "sample.ds.v2";
	}

	@Override
	protected String getSchema() {
        return  getClass().getResource("/schema.sql").getPath();
	}

	@Override
	protected DataSourceEntry[] getDataSourceEntryArray() {
		DataSourceEntry[] entries = new DataSourceEntry[3];

		entries[0] = new DataSourceEntry("jdbc:h2:mem:test;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets.xml", true);
		entries[1] = new DataSourceEntry("jdbc:h2:mem:test1;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets1.xml", false);
		entries[2] = new DataSourceEntry("jdbc:h2:mem:test2;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets2.xml", false);

		return entries;
	}

}
