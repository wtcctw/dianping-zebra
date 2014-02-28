package com.dianping.zebra.group.jdbc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;

import org.junit.Test;

import com.dianping.zebra.group.exception.GroupConfigException;
import com.dianping.zebra.group.exception.GroupDataSourceException;

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

	@Test
	// TODO
	public void testCallableStatementUseWriteConnection() throws Exception {
		Connection conn = getDataSource().getConnection();

		conn.prepareCall("select * from PERSON");
	}

	@Test
	public void test_create_single_statement() throws Exception {
		Connection connection = getDataSource().getConnection();

		Statement statement = connection.createStatement();
		boolean result = statement.execute("select * from PERSON");

		assertTrue(result);
		ResultSet rsSet = statement.getResultSet();
		rsSet.next();
		Assert.assertEquals("Bob", rsSet.getString(2));
		rsSet.next();
		Assert.assertEquals("Alice", rsSet.getString(2));
		rsSet.next();
		Assert.assertEquals("Charlie", rsSet.getString(2));
	}

	@Test
	public void test_create_double_statement() {

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

}
