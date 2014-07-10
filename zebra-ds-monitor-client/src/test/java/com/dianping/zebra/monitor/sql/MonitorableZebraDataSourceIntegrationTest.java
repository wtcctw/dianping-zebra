/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-6
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.monitor.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.IDataSet;
import org.junit.Test;

import com.dianping.avatar.tracker.ExecutionContext;
import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.avatar.tracker.TrackerContext;
import com.dianping.hawk.protocol.Messages.SqlMsg;

/**
 * @author danson.liu
 *
 */
public class MonitorableZebraDataSourceIntegrationTest extends ZebraMultiMonitorableDBBasedTestCase {
	
	private static final String 	TOKEN 				= "token-abc";
	private ExecutionContext 		executionContext;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		TrackerContext trackerContext = new TrackerContext();
		trackerContext.setToken(TOKEN);
		executionContext = ExecutionContextHolder.getContext();
		executionContext.setTrackerContext(trackerContext);
	}
	
	@Test
	public void testMonitorSingleQuery() throws SQLException {
		String sql = "select * from person where name = ?";
		Connection connection = getJdbcConnection();
		long begin = System.currentTimeMillis();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "lucy5");
		ResultSet resultSet = prepareStatement.executeQuery();
		System.out.println("1: " + (System.currentTimeMillis() - begin));
		try {
			assertTrue(resultSet.next());
			SqlMsg sqlMsg = executionContext.get(SqlMonitorImpl.SQL_MONITOR_MSG);
			assertSqlMsg(sqlMsg, TOKEN, "select * from person where name = ?", Arrays.asList("lucy5"), 1, 
					ConnectionMetaAnalyzer.DS_ZEBRA, "", "");
			assertEquals(1, sqlMsg.getSubSqlsCount());
			assertSqlMsg(sqlMsg.getSubSqls(0), "", "select * from person_6 where name = ?", Arrays.asList("lucy5"), 1, 
					ConnectionMetaAnalyzer.DS_C3P0, "h2db", "h2schema");
			assertFalse(resultSet.next());
		} finally {
			resultSet.close();
			connection.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMonitorMultiQuery() throws SQLException {
		String sql = "select * from person where id = 17";
		Connection connection = getJdbcConnection();
		long begin = System.currentTimeMillis();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		System.out.println("2: " + (System.currentTimeMillis() - begin));
		try {
			assertTrue(resultSet.next());
			SqlMsg sqlMsg = executionContext.get(SqlMonitorImpl.SQL_MONITOR_MSG);
			assertSqlMsg(sqlMsg, TOKEN, sql, Collections.EMPTY_LIST, 1, ConnectionMetaAnalyzer.DS_ZEBRA,
					"", "");
			assertEquals(8, sqlMsg.getSubSqlsCount());
			assertFalse(resultSet.next());
		} finally {
			resultSet.close();
			connection.close();
		}
	}
	
	private void assertSqlMsg(SqlMsg sqlMsg, String token, String sql, List<String> expectParams, int count, int dbId, String db, String schema) {
		assertNotNull(sqlMsg);
		assertEquals(token, sqlMsg.getToken());
		assertEquals(sql, sqlMsg.getSql());
		assertEquals(expectParams, sqlMsg.getParamsList());
		assertEquals(count, sqlMsg.getCount());
		assertEquals(dbId, sqlMsg.getDspath());
		assertTrue("".equals(sqlMsg.getAlias()));
		assertNotNull(sqlMsg.getWhen());
		assertNotNull(sqlMsg.getServer());
		assertEquals(db, sqlMsg.getDb());
		assertEquals(schema, sqlMsg.getSchema());
	}

	private Connection getJdbcConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	@Override
	protected String getDBBaseUrl() {
		return "jdbc:h2:mem:";
	}

	@Override
	protected String getCreateScriptConfigFile() {
		return "db-datafiles/dml-zebra-sqlmonitor.xml";
	}

	@Override
	protected String getDataFile() {
		return "db-datafiles/data-zebra-sqlmonitor.xml";
	}
	
	@Override
	protected String[] getSpringConfigLocations() {
		return new String[] {"ctx-zebra-sqlmonitor.xml"};
	}

	@Override
	protected DataSource getDataSource() {
		return (DataSource) context.getBean("zebraDS");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new DefaultDataSet();
	}

}
