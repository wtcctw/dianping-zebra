package com.dianping.zebra.group.router;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;

public class DataSourceRouterFactory {

	public static DataSourceRouter getDataSourceRouter(DataSourceConfigManager dataSourceConfigManager) {
		if (Constants.ROUTER_STRATEGY_ROUNDROBIN.equalsIgnoreCase(dataSourceConfigManager.getRouterStrategy())) {
			DataSourceRouter router = new WeightDataSourceRouter(dataSourceConfigManager.getDataSourceConfigs());
			return router;
		} else {
			throw new IllegalArgumentException("Strategy not supported: " + dataSourceConfigManager.getRouterStrategy());
		}
	}
}
