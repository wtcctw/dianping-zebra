package com.dianping.zebra.monitor.sql;

import java.beans.PropertyVetoException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import com.dianping.avatar.tracker.ExecutionContext;
import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.avatar.tracker.TrackerContext;
import com.dianping.hawk.protocol.Messages.SqlMsg;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * 
 * @author danson.liu
 *
 */
public class MonitorableC3P0DataSourceIntegrationTest extends ZebraMonitorableDBBasedTestCase {
	
	private static final String 	TOKEN 				= "token-abc";
	private ExecutionContext 		executionContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TrackerContext trackerContext = new TrackerContext();
		trackerContext.setToken(TOKEN);
		executionContext = ExecutionContextHolder.getContext();
		executionContext.setTrackerContext(trackerContext);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testMonitorQuery() throws Exception {
		String sql = "select * from person where gender = 'male'";
		ITable actualData = getConnection().createQueryTable("Result_Preson_Male", sql);
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(MonitorableC3P0DataSourceIntegrationTest.class.getResource("/db-datafiles/expected-data-c3p0-sqlmonitor.xml"));
		Assertion.assertEquals(expectedDataSet.getTable("person"), actualData);
		assertSqlMsg(sql, Collections.EMPTY_LIST, 1);
	}
	
	@Test
	public void testMonitorQueryWithManyParams() throws Exception {
		String sql = "select * from person where gender = ? and gender = ? and gender = ? and gender = ? and gender = ?" +
				" and gender = ? and gender = ? and gender = ? and gender = ? and gender = ? and gender = ? and gender = ?";
		PreparedStatement prepareStatement = getJdbcConnection().prepareStatement(sql);
		List<String> expectedParams = new ArrayList<String>();
		String genderVal = "male";
		for (int i = 1; i <= 12; i++) {
			prepareStatement.setString(i, genderVal);
			if (i <= SqlMonitorImpl.PARAM_MAXCOUNT) {
				expectedParams.add(genderVal);
			}
		}
		ITable actualData = getConnection().createTable("Result_Preson_Male", prepareStatement);
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(MonitorableC3P0DataSourceIntegrationTest.class.getResource("/db-datafiles/expected-data-c3p0-sqlmonitor.xml"));
		Assertion.assertEquals(expectedDataSet.getTable("person"), actualData);
		assertSqlMsg(sql, expectedParams, 1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMonitorCall() throws SQLException {
		String sql = "CALL QUERY('select * from person')";
		Connection connection = getJdbcConnection();
		CallableStatement callableStatement = connection.prepareCall(sql);
		ResultSet resultSet = callableStatement.executeQuery();
		try {
			assertTrue(resultSet.next());
			assertEquals(0, resultSet.getInt("id"));
			assertEquals("lucy0", resultSet.getString("name"));
			assertEquals(23, resultSet.getInt("age"));
			assertEquals("female", resultSet.getString("gender"));
			assertEquals(170, resultSet.getInt("height"));
			assertSqlMsg(sql, Collections.EMPTY_LIST, 1);
		} finally {
			resultSet.close();
			connection.close();
		}
	}

	private void assertSqlMsg(String sql, List<String> expectParams, int count) {
		SqlMsg sqlMsg = executionContext.get(SqlMonitorImpl.SQL_MONITOR_MSG);
		assertNotNull(sqlMsg);
		assertEquals(TOKEN, sqlMsg.getToken());
		assertEquals(sql, sqlMsg.getSql());
		assertEquals(expectParams, sqlMsg.getParamsList());
		assertEquals(count, sqlMsg.getCount());
		assertEquals(ConnectionMetaAnalyzer.DS_C3P0, sqlMsg.getDspath());
		assertTrue("".equals(sqlMsg.getAlias()));
		assertNotNull(sqlMsg.getWhen());
		assertNotNull(sqlMsg.getServer());
		assertEquals("h2db", sqlMsg.getDb());
		assertEquals("h2schema", sqlMsg.getSchema());
	}

	@Override
	protected String getDataSetFilePath() {
		return "db-datafiles/data-c3p0-sqlmonitor.xml";
	}

	@Override
	protected String getCreateTableScriptPath() {
		return "db-datafiles/dml-c3p0-sqlmonitor.xml";
	}
	
	private Connection getJdbcConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	@Override
	protected DataSource getDataSource() {
		return new MonitorableDataSource(createC3p0DataSource());
	}
	
	private ComboPooledDataSource createC3p0DataSource() {
		try {
			ComboPooledDataSource c3p0DataSource = new ComboPooledDataSource();
			c3p0DataSource.setJdbcUrl("jdbc:h2:mem:sql-monitor-db;DB_CLOSE_DELAY=-1");
			c3p0DataSource.setDriverClass("org.h2.Driver");
			c3p0DataSource.setMinPoolSize(2);
			c3p0DataSource.setMaxPoolSize(4);
			c3p0DataSource.setInitialPoolSize(2);
			return c3p0DataSource;
		} catch (PropertyVetoException e) {
			throw new RuntimeException("create c3p0 datasource failed.", e);
		}
	}

}
