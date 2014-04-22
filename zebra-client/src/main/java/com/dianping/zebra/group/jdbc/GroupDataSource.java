/**
 * Project: zebra-client
 * 
 * File Created at Feb 17, 2014
 * 
 */
package com.dianping.zebra.group.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.SystemConfigManager;
import com.dianping.zebra.group.config.SystemConfigManagerFactory;
import com.dianping.zebra.group.datasources.GroupDataSourceManager;
import com.dianping.zebra.group.datasources.GroupDataSourceManagerFactory;
import com.dianping.zebra.group.exception.GroupDataSourceException;
import com.dianping.zebra.group.router.GroupDataSourceRouter;
import com.dianping.zebra.group.router.GroupDataSourceRouterFactory;

/**
 * @author Leo Liang
 * 
 * 
 */
public class GroupDataSource implements DataSource {
	private PrintWriter out = null;

	private int loginTimeout = 0;

	private String name;

	private String configManagerType = Constants.CONFIG_MANAGER_TYPE_REMOTE;

	private GroupDataSourceManager dataSourceManager;

	private GroupDataSourceRouter router;

	private SystemConfigManager systemConfigManager;

	private DataSourceConfigManager dataSourceConfigManager;

	public GroupDataSource() {
		this(null);
	}

	public GroupDataSource(String name) {
		this.name = name;
	}

	public void init() {
		if (StringUtils.isBlank(name)) {
			throw new GroupDataSourceException("name must not be blank");
		}

		if (StringUtils.isBlank(configManagerType)) {
			throw new GroupDataSourceException("configManagerType must not be blank");
		}

		this.dataSourceManager = GroupDataSourceManagerFactory.getDataSourceManager();
		this.dataSourceConfigManager = DataSourceConfigManagerFactory.getConfigManager(configManagerType, name);
		this.systemConfigManager = SystemConfigManagerFactory.getConfigManger(configManagerType,
		      Constants.DEFAULT_SYSTEM_RESOURCE_ID);
		this.router = GroupDataSourceRouterFactory.getDataSourceRouter(dataSourceConfigManager);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setConfigManagerType(String configManagerType) {
		this.configManagerType = configManagerType;
	}

	public void setSystemConfigManager(SystemConfigManager systemConfigManager) {
		this.systemConfigManager = systemConfigManager;
	}

	public void setDataSourceConfigManager(DataSourceConfigManager dataSourceConfigManager) {
		this.dataSourceConfigManager = dataSourceConfigManager;
	}

	public void setRouter(GroupDataSourceRouter router) {
		this.router = router;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLogWriter()
	 */
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
	 */
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		this.out = out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLoginTimeout(int)
	 */
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		this.loginTimeout = seconds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLoginTimeout()
	 */
	@Override
	public int getLoginTimeout() throws SQLException {
		return this.loginTimeout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		try {
			return (T) this;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.getClass().isAssignableFrom(iface);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
		return new GroupConnection(dataSourceManager, router, systemConfigManager, dataSourceConfigManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
	 */
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return new GroupConnection(dataSourceManager, router, systemConfigManager, dataSourceConfigManager, username,
		      password);
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException("getParentLogger");
	}

}
