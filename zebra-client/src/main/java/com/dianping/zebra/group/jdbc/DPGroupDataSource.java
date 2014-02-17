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

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.GroupConfigManager;
import com.dianping.zebra.group.config.GroupConfigManagerFactory;
import com.dianping.zebra.group.router.DataSourceSelector;

/**
 * @author Leo Liang
 * 
 * 
 */
public class DPGroupDataSource implements DataSource {
	private String resourceId;

	private String configManagerType = Constants.DEFAULT_CONFIG_MANAGER_TYPE;

	private GroupConfigManager configManager;

	private DataSourceSelector router;

	public DPGroupDataSource() {
		this(null, null);
	}

	public DPGroupDataSource(String configManagerType, String resourceId) {
		this.resourceId = resourceId;
		this.configManagerType = configManagerType;
	}

	public void init() {
		this.configManager = GroupConfigManagerFactory.getConfigManger(configManagerType);
		this.configManager.init();
		this.router = GroupDataSourceRouter.getDataSourceRouter(configManager);
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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
	 */
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLoginTimeout(int)
	 */
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLoginTimeout()
	 */
	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
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
