package com.dianping.zebra.group.datasources;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.jdbc.AbstractDataSource;

public interface SingleDataSourceManager {
	
	public SingleDataSource createDataSource(AbstractDataSource reference, DataSourceConfig config);
	
	public SingleDataSource getDataSource(String dsId);

	public void destoryDataSource(String dsId, AbstractDataSource reference);

	public void init();
}