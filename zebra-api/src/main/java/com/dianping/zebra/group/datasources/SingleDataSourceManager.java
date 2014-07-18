package com.dianping.zebra.group.datasources;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public interface SingleDataSourceManager {

	public InnerSingleDataSource createDataSource(DataSourceConfig config);

	public void destoryDataSource(InnerSingleDataSource dataSource);

	public void init();
}