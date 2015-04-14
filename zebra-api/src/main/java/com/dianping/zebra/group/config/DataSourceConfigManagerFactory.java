package com.dianping.zebra.group.config;

import com.dianping.zebra.Constants;
import com.dianping.zebra.config.LionConfigService;
import com.dianping.zebra.config.PropertyConfigService;
import com.dianping.zebra.group.exception.IllegalConfigException;

public final class DataSourceConfigManagerFactory {

	private DataSourceConfigManagerFactory() {
	}

	public static DataSourceConfigManager getConfigManager(String configManagerType, String name) {
		DataSourceConfigManager dataSourceConfigManager = null;

		if (Constants.CONFIG_MANAGER_TYPE_LOCAL.equalsIgnoreCase(configManagerType)) {
			PropertyConfigService configService = new PropertyConfigService(name);
			configService.init();
			dataSourceConfigManager = new DefaultDataSourceConfigManager(name, configService);
		} else if (Constants.CONFIG_MANAGER_TYPE_REMOTE.equalsIgnoreCase(configManagerType)) {
			dataSourceConfigManager = new DefaultDataSourceConfigManager(name, LionConfigService.getInstance());
		} else {
			throw new IllegalConfigException(String.format("illegal dataSourceConfigManagerType[%s]", configManagerType));
		}

		dataSourceConfigManager.init();

		return dataSourceConfigManager;
	}
}
