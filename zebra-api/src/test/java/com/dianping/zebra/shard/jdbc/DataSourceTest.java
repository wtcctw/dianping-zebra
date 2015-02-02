/**
 * Project: zebra-client
 * 
 * File Created at 2011-6-27
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
package com.dianping.zebra.shard.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Leo Liang
 * 
 */
public class DataSourceTest extends ZebraBaseTestCase {
	@Test
	public void testInitWithoutDataSourcePool() {
		DPDataSource dataSource = new DPDataSource();
		try {
			dataSource.init();
			Assert.fail("DPDataSource can't init without dataSourcePool");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("dataSourcePool is required.", e.getMessage());
		}
	}

	@Test
	public void testInitWithoutDataSourcePool2() {
		DPDataSource dataSource = new DPDataSource();
		try {
			dataSource.setDataSourcePool(new HashMap<String, Object>());
			dataSource.init();
			Assert.fail("DPDataSource can't init with an empty dataSourcePool");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("dataSourcePool is required.", e.getMessage());
		}
	}

	@Test
	public void testInitWithoutRouterFactory() {
		DPDataSource dataSource = new DPDataSource();
		try {
			Map<String, Object> dataSourcePool = new HashMap<String, Object>();
			dataSourcePool.put("mock", new Object());
			dataSource.setDataSourcePool(dataSourcePool);
			dataSource.init();
			Assert.fail("DPDataSource can't init without routerFactory");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("routerRuleFile must be set.", e.getMessage());
		}
	}

	@Test
	public void testGetConnection() {
		DPDataSource dataSource = new DPDataSource();
		Connection conn = dataSource.getConnection();
		Assert.assertNotNull(conn);
		Assert.assertTrue((conn instanceof DPConnection));
	}

	@Test
	public void testGetConnection2() {
		DPDataSource dataSource = new DPDataSource();
		Connection conn = dataSource.getConnection("leo", "test");
		Assert.assertNotNull(conn);
		Assert.assertTrue((conn instanceof DPConnection));
	}

	protected String[] getSupportedOps() {
		return new String[] { "getConnection", "init", "setDataSourcePool", "setSyncEventNotifier", "getEventNotifier",
				"setRouterFactory", "isPerformanceMonitorSwitchOn", "setPerformanceMonitorSwitchOn" };
	}

	protected Object getTestObj() {
		return new DPDataSource();
	}

}
