/**
 * Project: zebra-sql-monitor-client
 *
 * File Created at 2011-10-27
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

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;


/**
 * 该<code>DataSource</code>用于收集各底层DataSource的执行信息，包括sql, sql params, sql destination, 执行耗时等信息
 *
 * @author danson.liu
 */
public class MonitorableDataSource implements DataSource {

	/**
	 * 被监控的DataSource
	 */
	private DataSource innerDataSource;
	
	private String jdbcUrl;

	public MonitorableDataSource(DataSource innerDataSource) {
		this.innerDataSource = getInnerDataSource(innerDataSource);
		
		Connection conn = null;
		try {
			conn = innerDataSource.getConnection();
			this.jdbcUrl = conn.getMetaData().getURL();
			
			Cat.logEvent("SQL.JdbcUrl", this.jdbcUrl);
		} catch (Exception ignore) {
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		try{
			return new MonitorableConnection(innerDataSource.getConnection());
		}catch(SQLException e){
			if(jdbcUrl != null){
				Transaction t = Cat.newTransaction("SQL", null);
				
				try {
					Cat.logEvent("SQL.Database", jdbcUrl, "ERROR", null);
					t.setStatus(e);
				} finally {
					t.complete();
				}
			}

			throw e;
		}
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return new MonitorableConnection(innerDataSource.getConnection(username, password));
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return innerDataSource.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		innerDataSource.setLogWriter(out);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return innerDataSource.getLoginTimeout();
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException("getParentLogger");
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		innerDataSource.setLoginTimeout(seconds);
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return innerDataSource.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return innerDataSource.isWrapperFor(iface);
	}

	private DataSource getInnerDataSource(DataSource innerDataSource) {
		if (innerDataSource instanceof MonitorableDataSource) {
			return getInnerDataSource(((MonitorableDataSource) innerDataSource).innerDataSource);
		}
		return innerDataSource;
	}
}
