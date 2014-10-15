package com.dianping.zebra.group.router;

import com.dianping.zebra.group.config.DataSourceConfigManager;

public final class DataSourceRouterFactory {
	private DataSourceRouterFactory() {
	}

	public static DataSourceRouter getDataSourceRouter(DataSourceConfigManager dataSourceConfigManager) {
		//		if (Constants.ROUTER_STRATEGY_ROUNDROBIN.equalsIgnoreCase(dataSourceConfigManager.getRouterStrategy())) {
		//			DataSourceRouter router = new WeightDataSourceRouter(dataSourceConfigManager.getGroupDataSourceConfig()
		//			      .getDataSourceConfigs());
		//			return router;
		//		} else {
		throw new IllegalArgumentException("Strategy not supported: ");
		//		}
	}
}
