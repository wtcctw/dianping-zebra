package com.dianping.zebra.monitor;

import java.util.Map;

import com.dianping.zebra.biz.dto.InstanceStatusDto;

public interface MySQLMonitorManager {
	
	public void addJdbcRef(String jdbcRef);
	
	public void removeJdbcRef(String jdbcRef);
	
	public Map<String,InstanceStatusDto> listStatus();
}
