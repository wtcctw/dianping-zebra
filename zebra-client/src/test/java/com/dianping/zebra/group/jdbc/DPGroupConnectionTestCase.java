package com.dianping.zebra.group.jdbc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.dianping.zebra.group.exception.GroupConfigException;
import com.dianping.zebra.group.exception.GroupDataSourceException;

/**
 * 
 * @author damonzhu
 * 
 */
public class DPGroupConnectionTestCase extends MultiDatabaseTestCase {

	@Test(expected = GroupDataSourceException.class)
	public void test_sql_connnection_without_config() {
		DPGroupDataSource dataSource = new DPGroupDataSource("local", "");
		dataSource.init();
	}

	@Test(expected = GroupConfigException.class)
	public void test_sql_connnection_error() {
		DPGroupDataSource dataSource = new DPGroupDataSource("local", "sample.ds.error");
		dataSource.init();
	}

	@Test
	public void test_sql_connection_api_supported() throws SQLException {
		Connection conn = getDataSource().getConnection();

		assertFalse(conn.isClosed());
		assertTrue(conn.getAutoCommit());
		assertNull(conn.getWarnings());
		assertTrue((conn.getMetaData() instanceof DPGroupDatabaseMetaData));

		assertTrue((conn.createStatement() instanceof DPGroupStatement));
		assertTrue((conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE) instanceof DPGroupStatement));
		assertTrue((conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
		      ResultSet.HOLD_CURSORS_OVER_COMMIT) instanceof DPGroupStatement));

		assertTrue((conn.prepareStatement("sql") instanceof DPGroupPreparedStatement));
		assertTrue((conn.prepareStatement("sql", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE) instanceof DPGroupPreparedStatement));
		assertTrue((conn.prepareStatement("sql", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
		      ResultSet.HOLD_CURSORS_OVER_COMMIT) instanceof DPGroupPreparedStatement));
		assertTrue((conn.prepareStatement("sql", Statement.RETURN_GENERATED_KEYS) instanceof DPGroupPreparedStatement));
		assertTrue((conn.prepareStatement("sql", new int[0]) instanceof DPGroupPreparedStatement));
		assertTrue((conn.prepareStatement("sql", new String[0]) instanceof DPGroupPreparedStatement));

	}

	@Test
	public void java_sql_connection_api_supported() throws Exception {
		Connection conn = getDataSource().getConnection();

		conn.prepareCall("select * from PERSON");
	}

	// @Test
	// TODO
	public void test_callable_statement_use_write_connection() throws Exception {
		execute(new ConnectionCallback() {

			@Override
			public Object doInConnection(Connection conn) throws Exception {
				Statement statement1 = conn.prepareCall("select * from PERSON");
				ResultSet rsSet1 = statement1.getResultSet();
				rsSet1.next();
				String value = rsSet1.getString(2);
				Assert.assertEquals("writer", value);
				return null;
			}
		});

	}

	@Test
	public void test_create_single_read_statement_on_same_connection() throws Exception {
		execute(new StatementCallback() {

			@Override
			public Object doInStatement(Statement stmt) throws Exception {
				boolean result = stmt.execute("select * from PERSON");

				assertTrue(result);
				ResultSet rsSet = stmt.getResultSet();
				rsSet.next();
				Assert.assertNotSame("writer", rsSet.getString(2));
				return null;
			}
		});
	}

	@Test
	public void test_create_read_write_statement_on_same_connection() throws Exception {
		execute(new ConnectionCallback() {

			@Override
			public Object doInConnection(Connection conn) throws Exception {
				Assert.assertNotSame("writer", executeSelectSql(conn, "select * from PERSON"));
				executeUpdateSql(conn, "update PERSON set NAME = 'writer-new'");
				Assert.assertEquals("writer-new", executeSelectSql(conn, "select * from PERSON"));
				return null;
			}
		});
	}

	@Test
	public void test_create_write_read_statement_on_same_connection() throws Exception {
		execute(new ConnectionCallback() {

			@Override
			public Object doInConnection(Connection conn) throws Exception {
				executeUpdateSql(conn, "update PERSON set NAME = 'writer-new'");
				Assert.assertEquals("writer-new", executeSelectSql(conn, "select * from PERSON"));
				return null;
			}
		});
	}

	@Test
	public void test_create_multi_read_statement_on_same_connection() throws Exception {
		execute(new ConnectionCallback() {
			@Override
			public Object doInConnection(Connection conn) throws Exception {
				String value = executeSelectSql(conn, "select * from PERSON");
				Assert.assertNotSame("writer", value);
				Assert.assertEquals(value, executeSelectSql(conn, "select * from PERSON"));
				return null;
			}
		});
	}

	@Test
	public void test_create_multi_write_statement_on_same_connection() throws Exception {
		execute(new ConnectionCallback() {
			@Override
			public Object doInConnection(Connection conn) throws Exception {
				executeUpdateSql(conn, "update PERSON set NAME = 'writer-new1'");
				executeUpdateSql(conn, "update PERSON set NAME = 'writer-new2'");

				Assert.assertEquals("writer-new2", executeSelectSql(conn, "select * from PERSON"));
				return null;
			}
		});
	}

	@Test
	public void test_create_multi_read_statement_on_different_connection() throws Exception {
		final Set<String> set = new HashSet<String>();

		for (int i = 0; i < 100; i++) {
			execute(new StatementCallback() {
				@Override
				public Object doInStatement(Statement stmt) throws Exception {
					set.add(executeSelectSql(stmt, "select * from PERSON"));
					return null;
				}
			});
		}

		Assert.assertEquals(2, set.size());
		for (String value : set) {
			Assert.assertNotSame("writer", value);
		}
	}

	@Test
	public void test_create_write_read_statement_on_different_connection() throws Exception {
		final Set<String> set = new HashSet<String>();

		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0) {
				execute(new StatementCallback() {
					@Override
					public Object doInStatement(Statement stmt) throws Exception {
						set.add(executeSelectSql(stmt, "select * from PERSON"));
						return null;
					}
				});

			} else {
				execute(new StatementCallback() {
					@Override
					public Object doInStatement(Statement stmt) throws Exception {
						executeUpdateSql(stmt, "update PERSON set NAME = 'writer-new'");
						Assert.assertEquals("writer-new", executeSelectSql(stmt, "select * from PERSON"));
						return null;
					}
				});

			}
		}

		Assert.assertEquals(2, set.size());
		for (String value : set) {
			Assert.assertNotSame("writer-new", value);
		}
	}

	@Test
	public void test_W_with_autocommit() {

	}

	@Test
	public void test_W_without_autocommit() {

	}

	@Test
	public void test_connection_retry() {

	}

	@Test
	public void test_WW_without_trans() {

	}

	@Test
	public void test_WR_without_trans() {

	}

	@Test
	public void test_RR_without_trans() throws Exception {

	}

	@Test
	public void test_RW_without_trans() throws Exception {

	}

	@Test
	public void test_WW_with_trans() {

	}

	@Test
	public void test_WR_with_trans() {

	}

	@Test
	public void test_RR_with_trans() throws Exception {

	}

	@Test
	public void test_RW_with_trans() throws Exception {

	}

	@Test
	public void test_W_R_within_timeout() throws Exception {

	}

	@Test
	public void test_W_R_without_timeout() throws Exception {

	}

	@Test
	public void test_WW() throws Exception {

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

		DataSourceEntry entry1 = new DataSourceEntry("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "datasets.xml");
		DataSourceEntry entry2 = new DataSourceEntry("jdbc:h2:mem:test1;DB_CLOSE_DELAY=-1", "datasets1.xml");
		DataSourceEntry entry3 = new DataSourceEntry("jdbc:h2:mem:test2;DB_CLOSE_DELAY=-1", "datasets2.xml");

		entries[0] = entry1;
		entries[1] = entry2;
		entries[2] = entry3;

		return entries;
	}

	public void executeUpdateSql(Connection conn, String sql) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}
	
	public void executeUpdateSql(Statement stmt, String sql) throws SQLException {
		stmt.execute(sql);
	}

	public String executeSelectSql(Connection conn, String sql) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		ResultSet rsSet = stmt.getResultSet();
		rsSet.next();

		return rsSet.getString(2);
	}

	public String executeSelectSql(Statement stmt, String sql) throws SQLException {
		stmt.execute(sql);
		ResultSet rsSet = stmt.getResultSet();
		rsSet.next();

		return rsSet.getString(2);
	}
}
