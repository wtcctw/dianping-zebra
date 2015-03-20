package com.dianping.zebra.admin.monitor;

import java.util.Map;

public interface MySQLMonitorManager {
	
	public void addJdbcRef(String jdbcRef);
	
	public void removeJdbcRef(String jdbcRef);
	
	public Map<String,InstanceStatus> listStatus();
}
