package com.dianping.zebra.group.healthcheck;

import java.sql.SQLException;

import com.dianping.zebra.group.router.GroupDataSourceTarget;

public interface HealthCheck {

	public void notifyException(GroupDataSourceTarget dsTarget, SQLException e);
	
}
