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

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.IDataSet;
import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import com.dianping.avatar.tracker.ExecutionContext;
import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.avatar.tracker.TrackerContext;
import com.dianping.dpdl.sql.DPDataSource;
import com.dianping.hawk.protocol.Messages.SqlMsg;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author danson.liu
 *
 */
public class MonitorableDpdlDataSourceIntegrationTest extends ZebraMultiMonitorableDBBasedTestCase {

	private static final String 	WRITE_DS 			= "writeDS";
	private static final String 	READ_DS_2 			= "readDS-2";
	private static final String 	READ_DS_1 			= "readDS-1";
	
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
	public void testMonitorQuery() throws DataSetException, SQLException, Exception {
		String sql = "select * from person where gender = ? order by id";
		Connection connection = getJdbcConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "male");
		ResultSet resultSet = prepareStatement.executeQuery();
		try {
			resultSet.next();
			assertEquals("lucy1", resultSet.getString("name"));
			resultSet.next();
			assertEquals("lucy3", resultSet.getString("name"));
			resultSet.next();
			assertEquals("lucy4", resultSet.getString("name"));
			assertSqlMsg(sql, Arrays.asList("male"), 1);
		} finally {
			resultSet.close();
			connection.close();
		}
	}
	
	@Test
	public void testMonitorUpdate() throws SQLException {
		String sql = "update person set name = ? where id = ?";
		Connection connection = getJdbcConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "john");
		prepareStatement.setInt(2, 0);
		int updatedCount = prepareStatement.executeUpdate();
		assertEquals(1, updatedCount);
		assertSqlMsg(sql, Arrays.asList("john", "0"), 1);
		sql = "select * from person where name = ?";
		prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "john");
		ResultSet resultSet = prepareStatement.executeQuery();
		assertTrue(resultSet.next());
		assertEquals(0, resultSet.getInt("id"));
		assertSqlMsg(sql, Arrays.asList("john"), 1);
	}
	
	@Test
	public void testMonitorExecute() throws SQLException {
		String sql = "update person set name = ? where id = ?";
		Connection connection = getJdbcConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "john");
		prepareStatement.setInt(2, 0);
		boolean hasResultSet = prepareStatement.execute();
		assertFalse(hasResultSet);
		assertSqlMsg(sql, Arrays.asList("john", "0"), 1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMonitorBatch() throws SQLException {
		String sql = "update person set name = ? where id = ?";
		Connection connection = getJdbcConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "john");
		prepareStatement.setInt(2, 0);
		prepareStatement.addBatch();
		prepareStatement.setString(1, "jim");
		prepareStatement.setInt(2, 1);
		prepareStatement.addBatch();
		int[] updatedCounts = prepareStatement.executeBatch();
		assertEquals(1, updatedCounts[0]);
		assertEquals(1, updatedCounts[1]);
		assertSqlMsg(sql, Collections.EMPTY_LIST, 2);
		
		sql = "select * from person where name = ?";
		prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "john");
		ResultSet resultSet = prepareStatement.executeQuery();
		assertTrue(resultSet.next());
		assertEquals(0, resultSet.getInt("id"));
		assertSqlMsg(sql, Arrays.asList("john"), 1);
		
		prepareStatement.setString(1, "jim");
		resultSet = prepareStatement.executeQuery();
		assertTrue(resultSet.next());
		assertEquals(1, resultSet.getInt("id"));
		assertSqlMsg(sql, Arrays.asList("jim"), 1);
	}

	private void assertSqlMsg(String sql, List<String> expectParams, int count) {
		SqlMsg sqlMsg = executionContext.get(SqlMonitorImpl.SQL_MONITOR_MSG);
		assertNotNull(sqlMsg);
		assertEquals(TOKEN, sqlMsg.getToken());
		assertEquals(sql, sqlMsg.getSql());
		assertEquals(expectParams, sqlMsg.getParamsList());
		assertEquals(count, sqlMsg.getCount());
		assertEquals(ConnectionMetaAnalyzer.DS_DPDL | ConnectionMetaAnalyzer.DS_C3P0, sqlMsg.getDspath());
		assertTrue("".equals(sqlMsg.getAlias()));
		assertNotNull(sqlMsg.getWhen());
		assertNotNull(sqlMsg.getServer());
		assertEquals("h2db", sqlMsg.getDb());
		assertEquals("h2schema", sqlMsg.getSchema());
	}

	@Override
	protected String getDBBaseUrl() {
		return "jdbc:h2:mem:";
	}

	@Override
	protected String getCreateScriptConfigFile() {
		return "db-datafiles/dml-dpdl-sqlmonitor.xml";
	}

	@Override
	protected String getDataFile() {
		return "db-datafiles/data-dpdl-sqlmonitor.xml";
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new DefaultDataSet();
	}

	@Override
	protected DataSource getDataSource() {
		DPDataSource dpdlDataSource = new DPDataSource();
		dpdlDataSource.setWriteDS(WRITE_DS);
		Map<String, String> readDSMap = new HashMap<String, String>();
		readDSMap.put(READ_DS_1, "10");
		readDSMap.put(READ_DS_2, "6");
		dpdlDataSource.setReadDS(readDSMap);
		dpdlDataSource.setBeanFactory(createMockedBeanFactory());
		try {
			dpdlDataSource.afterPropertiesSet();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		return new MonitorableDataSource(dpdlDataSource);
	}

	private Connection getJdbcConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	private BeanFactory createMockedBeanFactory() {
		BeanFactory beanFactory = EasyMock.createMock(BeanFactory.class);
		EasyMock.expect(beanFactory.getBean(WRITE_DS)).andReturn(createC3p0DataSource(
				"sql-monitor-main-db"
		)).anyTimes();
		EasyMock.expect(beanFactory.getBean(READ_DS_1)).andReturn(createC3p0DataSource(
				"sql-monitor-slave-db1"
		)).anyTimes();
		EasyMock.expect(beanFactory.getBean(READ_DS_2)).andReturn(createC3p0DataSource(
				"sql-monitor-slave-db2"
		)).anyTimes();
		EasyMock.replay(beanFactory);
		return beanFactory;
	}

	private Object createC3p0DataSource(String dbName) {
		try {
			ComboPooledDataSource c3p0DataSource = new ComboPooledDataSource();
			c3p0DataSource.setJdbcUrl("jdbc:h2:mem:" + dbName + ";DB_CLOSE_DELAY=-1");
			c3p0DataSource.setDriverClass("org.h2.Driver");
			c3p0DataSource.setMinPoolSize(2);
			c3p0DataSource.setMaxPoolSize(4);
			c3p0DataSource.setInitialPoolSize(2);
			return new MonitorableDataSource(c3p0DataSource);
		} catch (PropertyVetoException e) {
			throw new RuntimeException("create c3p0 datasource failed.", e);
		}
	}
	
}
