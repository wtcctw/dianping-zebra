package com.dianping.zebra.group.datasources;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public interface SingleDataSourceManager {
	
	public SingleDataSource createDataSource(String user, DataSourceConfig config);
	
	public SingleDataSource getDataSource(String dsId);

	public void destoryDataSource(String dsId, String user);

	public void init();
}