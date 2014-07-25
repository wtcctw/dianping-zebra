package com.dianping.zebra.group.config;

import java.util.HashMap;
import java.util.Map;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.exception.IllegalConfigException;

public final class DataSourceConfigManagerFactory {

	private static volatile Map<String, DataSourceConfigManager> dataSourceConfigManagers = new HashMap<String, DataSourceConfigManager>();

	private DataSourceConfigManagerFactory() {
	}

	public static DataSourceConfigManager getConfigManager(String configManagerType, String name,
	      boolean isSingleDataSource, boolean verbose) {
		DataSourceConfigManager dataSourceConfigManager = dataSourceConfigManagers.get(getFormattedName(name,
		      isSingleDataSource));

		if (dataSourceConfigManager == null) {
			synchronized (dataSourceConfigManagers) {
				dataSourceConfigManager = dataSourceConfigManagers.get(getFormattedName(name, isSingleDataSource));

				if (dataSourceConfigManager == null) {
					if (Constants.CONFIG_MANAGER_TYPE_LOCAL.equalsIgnoreCase(configManagerType)) {
						LocalConfigService configService = new LocalConfigService(name);
						configService.init();
						dataSourceConfigManager = new DefaultDataSourceConfigManager(name, configService, isSingleDataSource);
					} else if (Constants.CONFIG_MANAGER_TYPE_REMOTE.equalsIgnoreCase(configManagerType)) {
						LionConfigService configService = new LionConfigService();
						configService.init();
						dataSourceConfigManager = new DefaultDataSourceConfigManager(name, configService, isSingleDataSource);
					} else {
						throw new IllegalConfigException(String.format("illegal dataSourceConfigManagerType[%s]",
						      configManagerType));
					}
					dataSourceConfigManager.setVerbose(verbose);
					dataSourceConfigManager.init();
					dataSourceConfigManagers.put(getFormattedName(name, isSingleDataSource), dataSourceConfigManager);
				}
			}
		}

		return dataSourceConfigManager;
	}

	private static String getFormattedName(String name, boolean isSingleDataSource) {
		return "[" + (isSingleDataSource ? "single" : "group") + "]" + name;
	}
}
