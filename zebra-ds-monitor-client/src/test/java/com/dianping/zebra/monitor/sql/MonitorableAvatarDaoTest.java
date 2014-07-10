/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-8
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

import javax.sql.DataSource;

import org.junit.Test;

import com.dianping.avatar.tracker.ExecutionContext;
import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.avatar.tracker.TrackerContext;
import com.dianping.hawk.protocol.Messages.SqlMsg;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * @author danson.liu
 *
 */
public class MonitorableAvatarDaoTest extends ZebraMonitorableDBBasedTestCase {
	
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
	
	@Test
	public void testSimpleDao() {
		PersonDao personDao = (PersonDao) context.getBean("personDao");
		Person found = personDao.findPerson(0);
		assertEquals(0, found.getId());
		assertEquals("lucy0", found.getName());
		assertEquals(23, found.getAge());
		assertEquals("female", found.getGender());
		assertEquals(170, found.getHeight());
		assertSqlMsg("SELECT ID, NAME, AGE, GENDER, HEIGHT FROM PERSON WHERE ID = ?", "Person.findPerson", 1);
	}
	
	private void assertSqlMsg(String sql, String category, int count) {
		SqlMsg sqlMsg = executionContext.get(SqlMonitorImpl.SQL_MONITOR_MSG);
		assertNotNull(sqlMsg);
		assertEquals(TOKEN, sqlMsg.getToken());
		assertEquals(sql, sqlMsg.getSql());
		assertEquals(count, sqlMsg.getCount());
		assertEquals(ConnectionMetaAnalyzer.DS_C3P0, sqlMsg.getDspath());
		assertEquals(category, sqlMsg.getAlias());
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
	
	@Override
	protected String[] getSpringConfigLocations() {
		return new String[] {"ctx-avatar-dao-sqlmonitor.xml"};
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
