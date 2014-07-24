package com.dianping.zebra.group.jdbc;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class SingleDataSourceTest extends H2DatabaseTestCase {
	protected static final String USER = "sa";

	private SingleDataSource single;

	@Before
	public void init() {
		single = new SingleDataSource("db1", false);
		single.setJdbcUrl(JDBC_URL);
		single.setUser(USER);
		single.setDriverClass(JDBC_DRIVER);
		single.setPassword(PASSWORD);
		single.init();
	}

	@Test
	public void test_query() throws SQLException {
		Connection conn = single.getConnection();
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery("select * from PERSON");
		assertTrue(result.next());
	}

	@Override
	protected String getDataSets() {
		return "datasets.xml";
	}

	@Override
	protected String getSchema() {
		return getClass().getResource("/schema.sql").getPath();
	}

}
