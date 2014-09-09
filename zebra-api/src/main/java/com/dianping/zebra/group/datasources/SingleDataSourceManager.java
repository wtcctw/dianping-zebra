package com.dianping.zebra.group.datasources;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public interface SingleDataSourceManager {

	public SingleDataSource createDataSource(DataSourceConfig config);

	public void destoryDataSource(SingleDataSource dataSource);

	public void init();
}