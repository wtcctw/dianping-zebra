package com.dianping.zebra.admin.monitor;

public interface MySQLMonitorManager {
	
	public void addJdbcRef(String jdbcRef);
	
	public void removeJdbcRef(String jdbcRef);
}
