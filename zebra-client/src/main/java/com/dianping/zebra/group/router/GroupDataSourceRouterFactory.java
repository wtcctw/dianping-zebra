package com.dianping.zebra.group.router;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;

public class GroupDataSourceRouterFactory {

	public static GroupDataSourceRouter getDataSourceRouter(DataSourceConfigManager dataSourceConfigManager) {
		if (Constants.ROUTER_STRATEGY_ROUNDROBIN.equalsIgnoreCase(dataSourceConfigManager.getRouterStrategy())) {
			GroupReadWriteDataSourceRouter readWriteDataSourceRouter = new GroupReadWriteDataSourceRouter(
			      dataSourceConfigManager);
			dataSourceConfigManager.addListerner(readWriteDataSourceRouter);

			return readWriteDataSourceRouter;

		} else {
			throw new IllegalArgumentException("Strategy not supported: " + dataSourceConfigManager.getRouterStrategy());
		}
	}
}
