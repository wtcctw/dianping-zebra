package com.dianping.zebra.group.router;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.datasources.GroupDataSourceManagerFactory;

public class GroupDataSourceRouterFactory {

	public static GroupDataSourceRouter getDataSourceRouter(DataSourceConfigManager dataSourceConfigManager) {
		if (Constants.ROUTER_STRATEGY_ROUNDROBIN.equalsIgnoreCase(dataSourceConfigManager.getRouterStrategy())) {
			GroupReadWriteDataSourceRouter readWriteDataSourceRouter = new GroupReadWriteDataSourceRouter(
			      dataSourceConfigManager,GroupDataSourceManagerFactory.getDataSourceManager());
			dataSourceConfigManager.addListerner(readWriteDataSourceRouter);

			return readWriteDataSourceRouter;

		} else {
			throw new IllegalArgumentException("Strategy not supported: " + dataSourceConfigManager.getRouterStrategy());
		}
	}
}
