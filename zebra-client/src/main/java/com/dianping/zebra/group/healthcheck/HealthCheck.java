package com.dianping.zebra.group.healthcheck;

import java.sql.SQLException;

public interface HealthCheck {

	public void notifyException(String dsKey, SQLException e);
	
}
