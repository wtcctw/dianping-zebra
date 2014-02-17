package com.dianping.zebra.group.manager;

import java.sql.Connection;

public interface GroupDatasourceManager {
	public boolean isAvailable(String id);
	
	public Connection getConnection(String id);
}
