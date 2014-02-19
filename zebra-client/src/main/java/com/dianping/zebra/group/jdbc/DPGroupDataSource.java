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

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.GroupConfigManager;
import com.dianping.zebra.group.config.GroupConfigManagerFactory;
import com.dianping.zebra.group.exception.GroupDataSourceException;
import com.dianping.zebra.group.manager.GroupDataSourceManager;
import com.dianping.zebra.group.manager.GroupDataSourceManagerFactory;
import com.dianping.zebra.group.router.GroupDataSourceRouter;
import com.dianping.zebra.group.router.GroupDataSourceRouterFactory;

/**
 * @author Leo Liang
 * 
 * 
 */
public class DPGroupDataSource implements DataSource {
	private PrintWriter out = null;

	private int loginTimeout = 0;

	private String resourceId;

	private String configManagerType = Constants.DEFAULT_CONFIG_MANAGER_TYPE;

	private GroupConfigManager configManager;

	private GroupDataSourceRouter router;

	private GroupDataSourceManager dataSourceManager;

	public DPGroupDataSource() {
		this(null, null);
	}

	public DPGroupDataSource(String configManagerType, String resourceId) {
		this.resourceId = resourceId;
		this.configManagerType = configManagerType;
	}

	public void init() {
		if (StringUtils.isBlank(resourceId)) {
			throw new GroupDataSourceException("resourceId must not be blank");
		}

		if (StringUtils.isBlank(configManagerType)) {
			throw new GroupDataSourceException("configManagerType must not be blank");
		}

		this.configManager = GroupConfigManagerFactory.getConfigManger(configManagerType, resourceId);
		this.router = GroupDataSourceRouterFactory.getDataSourceRouter(configManager);
		this.dataSourceManager = GroupDataSourceManagerFactory.getGroupDataSourceManger(configManager);
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public void setConfigManagerType(String configManagerType) {
		this.configManagerType = configManagerType;
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
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
	 */
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
