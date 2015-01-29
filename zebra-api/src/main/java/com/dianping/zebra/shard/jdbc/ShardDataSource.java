package com.dianping.zebra.shard.jdbc;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Dozer @ 2015-01
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ShardDataSource implements DataSource {
	@Override public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override public Connection getConnection() throws SQLException {
		return null;
	}

	@Override public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

	public void init(){}

	public void close(){}

	@Override public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override public void setLogWriter(PrintWriter out) throws SQLException {

	}

	@Override public void setLoginTimeout(int seconds) throws SQLException {

	}

	@Override public int getLoginTimeout() throws SQLException {
		return 0;
	}
}
