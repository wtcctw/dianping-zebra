package com.dianping.zebra.group.config;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.exception.IllegalConfigException;

import java.util.HashMap;
import java.util.Map;

public final class DataSourceConfigManagerFactory {

	private static volatile Map<String, DataSourceConfigManager> dataSourceConfigManagers = new HashMap<String, DataSourceConfigManager>();

	private DataSourceConfigManagerFactory() {
	}

	public static DataSourceConfigManager getConfigManager(String configManagerType, String name) {
		DataSourceConfigManager dataSourceConfigManager = dataSourceConfigManagers.get(getFormattedName(name));

		if (dataSourceConfigManager == null) {
			synchronized (dataSourceConfigManagers) {
				dataSourceConfigManager = dataSourceConfigManagers.get(getFormattedName(name));

				if (dataSourceConfigManager == null) {
					if (Constants.CONFIG_MANAGER_TYPE_LOCAL.equalsIgnoreCase(configManagerType)) {
						LocalConfigService configService = new LocalConfigService(name);
						configService.init();
						dataSourceConfigManager = new DefaultDataSourceConfigManager(name, configService);
					} else if (Constants.CONFIG_MANAGER_TYPE_REMOTE.equalsIgnoreCase(configManagerType)) {
						LionConfigService configService = new LionConfigService();
						configService.init();
						dataSourceConfigManager = new DefaultDataSourceConfigManager(name, configService);
					} else {
						throw new IllegalConfigException(String.format("illegal dataSourceConfigManagerType[%s]",
							  configManagerType));
					}
					dataSourceConfigManager.init();
					dataSourceConfigManagers.put(getFormattedName(name), dataSourceConfigManager);
				}
			}
		}

		return dataSourceConfigManager;
	}

	private static String getFormattedName(String name) {
		return "[group]" + name;
	}
}
