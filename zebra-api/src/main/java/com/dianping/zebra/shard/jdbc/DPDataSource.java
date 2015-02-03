/**
 * Project: ${zebra-client.aid}
 *
 * File Created at 2011-6-7 $Id$
 *
 * Copyright 2010 dianping.com. All rights reserved.
 *
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.shard.jdbc;

import com.dianping.zebra.shard.router.DataSourceRouter;
import com.dianping.zebra.shard.router.DataSourceRouterFactory;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * Zebra的JDBC DataSource Wrapper
 *
 * @author Leo Liang
 */
public class DPDataSource implements DataSource {

	private Map<String, DataSource> dataSourcePool;

	private DataSourceRouterFactory routerFactory;

	private DataSourceRouter router;

	private boolean performanceMonitorSwitchOn = false;

	/**
	 * @return the performanceMonitorSwitchOn
	 */
	public boolean isPerformanceMonitorSwitchOn() {
		return performanceMonitorSwitchOn;
	}

	/**
	 * @param performanceMonitorSwitchOn the performanceMonitorSwitchOn to set
	 */
	public void setPerformanceMonitorSwitchOn(boolean performanceMonitorSwitchOn) {
		this.performanceMonitorSwitchOn = performanceMonitorSwitchOn;
	}

	/**
	 * @param routerFactory the routerFactory to set
	 */
	public void setRouterFactory(DataSourceRouterFactory routerFactory) {
		this.routerFactory = routerFactory;
	}

	public void setDataSourcePool(Map<String, DataSource> dataSourcePool) {
		this.dataSourcePool = dataSourcePool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection()
	 */
	@Override
	public Connection getConnection() {
		DPConnection connection = new DPConnection();
		connection.setRouter(router);
		connection.setPerformanceMonitorSwitchOn(performanceMonitorSwitchOn);
		return connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Connection getConnection(String username, String password) {
		DPConnection connection = new DPConnection(username, password);
		connection.setRouter(router);
		connection.setPerformanceMonitorSwitchOn(performanceMonitorSwitchOn);
		return connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLogWriter()
	 */
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getLogWriter");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLoginTimeout()
	 */
	@Override
	public int getLoginTimeout() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getLoginTimeout");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
	 */
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setLogWriter");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLoginTimeout(int)
	 */
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setLoginTimeout");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport isWrapperFor");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport unwrap");
	}

	public void init() {
		if (dataSourcePool == null || dataSourcePool.isEmpty()) {
			throw new IllegalArgumentException("dataSourcePool is required.");
		}
		if (routerFactory == null) {
			throw new IllegalArgumentException("routerRuleFile must be set.");
		}
		this.router = routerFactory.getRouter();
		this.router.setDataSourcePool(dataSourcePool);
		this.router.init();
	}

}
