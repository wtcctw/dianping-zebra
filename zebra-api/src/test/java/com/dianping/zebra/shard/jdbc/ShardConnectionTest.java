package com.dianping.zebra.shard.jdbc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;

public class ShardConnectionTest extends ZebraMultiDBBaseTestCase {

	@Override
	protected String getDBBaseUrl() {
		return "jdbc:h2:mem:";
	}

	@Override
	protected String getCreateScriptConfigFile() {
		return "db-datafiles/createtable-multidb-lifecycle.xml";
	}

	@Override
	protected String getDataFile() {
		return "db-datafiles/data-multidb-lifecycle.xml";
	}

	@Override
	protected String[] getSpringConfigLocations() {
		return new String[] { "ctx-multidb-lifecycle.xml" };
	}

	public DataSource getDataSource() {
		return (DataSource) context.getBean("zebraDS");
	}

	@Test
	public void test_sql_connection_api_supported() throws SQLException {
		Connection conn = getDataSource().getConnection();

		assertFalse(conn.isClosed());
		assertTrue(conn.getAutoCommit());
		conn.setAutoCommit(false);
		assertFalse(conn.getAutoCommit());

		assertTrue((conn.createStatement() instanceof ShardStatement));
		assertTrue((conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE) instanceof ShardStatement));
		assertTrue((conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
		      ResultSet.HOLD_CURSORS_OVER_COMMIT) instanceof ShardStatement));

		assertTrue((conn.prepareStatement("sql") instanceof ShardPreparedStatement));
		assertTrue((conn.prepareStatement("sql", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE) instanceof ShardPreparedStatement));
		assertTrue((conn.prepareStatement("sql", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
		      ResultSet.HOLD_CURSORS_OVER_COMMIT) instanceof ShardPreparedStatement));
		assertTrue((conn.prepareStatement("sql", Statement.RETURN_GENERATED_KEYS) instanceof ShardPreparedStatement));
		assertTrue((conn.prepareStatement("sql", new int[0]) instanceof ShardPreparedStatement));
		assertTrue((conn.prepareStatement("sql", new String[0]) instanceof ShardPreparedStatement));
	}
}
