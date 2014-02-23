package com.dianping.zebra.group.manager;

import java.sql.Connection;
import java.sql.SQLException;

public interface GroupDataSourceManager {

	public void init() ;

	public boolean isAvailable(String id);

	public Connection getConnection(String id) throws SQLException;

	public Connection getWriteConnection() throws SQLException;
	
	public void destory() throws SQLException;
}
