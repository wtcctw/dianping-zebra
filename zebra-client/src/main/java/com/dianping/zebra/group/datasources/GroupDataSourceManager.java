package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.SQLException;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public interface GroupDataSourceManager {
	
	public void createDataSource(String biz, DataSourceConfig config);
	
	public Connection getConnection(String dsId) throws SQLException;

	public void destory(String dsId, String biz);

	public void init();
}
