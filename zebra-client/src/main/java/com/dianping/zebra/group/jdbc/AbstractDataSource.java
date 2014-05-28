package com.dianping.zebra.group.jdbc;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.exception.GroupDataSourceException;

public abstract class AbstractDataSource implements DataSource {

	protected String configManagerType = Constants.CONFIG_MANAGER_TYPE_REMOTE;

	private PrintWriter out = null;

	private int loginTimeout = 0;

	protected String name;

	@Override
	public int getLoginTimeout() throws SQLException {
		return this.loginTimeout;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return out;
	}

	protected void init() {
	}

	protected void close() throws SQLException {
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.getClass().isAssignableFrom(iface);
	}

	public void setConfigManagerType(String configManagerType) {
		if (StringUtils.isBlank(configManagerType)) {
			throw new GroupDataSourceException("configManagerType must not be blank");
		}

		this.configManagerType = configManagerType;
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		this.loginTimeout = seconds;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		this.out = out;
	}
	
	public void setName(String name){
		this.name = name;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> iface) throws SQLException {
		try {
			return (T) this;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException("getParentLogger");
	}
}
