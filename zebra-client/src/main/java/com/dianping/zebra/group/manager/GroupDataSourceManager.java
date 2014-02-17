package com.dianping.zebra.group.manager;

import java.sql.Connection;

public interface GroupDataSourceManager {
	
	public void init();
	
	public boolean isAvailable(String id);
	
	public Connection getConnection(String id);
	
	public void destory();
}
