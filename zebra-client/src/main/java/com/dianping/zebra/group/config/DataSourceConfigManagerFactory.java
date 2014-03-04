package com.dianping.zebra.group.config;

import java.util.HashMap;
import java.util.Map;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.exception.GroupConfigException;

public class DataSourceConfigManagerFactory {

	private static Map<String, DataSourceConfigManager> dataSourceConfigManagers = new HashMap<String, DataSourceConfigManager>();

	public static DataSourceConfigManager getConfigManager(String configManagerType, String resourceId) {
		DataSourceConfigManager dataSourceConfigManager = dataSourceConfigManagers.get(resourceId);

		if (dataSourceConfigManager == null) {
			synchronized (dataSourceConfigManagers) {
				dataSourceConfigManager = dataSourceConfigManagers.get(resourceId);

				if (dataSourceConfigManager == null) {
					if (Constants.CONFIG_MANAGER_TYPE_LOCAL.equalsIgnoreCase(configManagerType)) {
						LocalConfigService configService = new LocalConfigService(resourceId);
						configService.init();
						dataSourceConfigManager = new DefaultDataSourceConfigManager(resourceId, configService);
					} else if (Constants.CONFIG_MANAGER_TYPE_REMOTE.equalsIgnoreCase(configManagerType)) {
						RemoteConfigService configService = new RemoteConfigService(resourceId);
						configService.init();
						dataSourceConfigManager = new DefaultDataSourceConfigManager(resourceId, configService);
					} else {
						throw new GroupConfigException(String.format("illegal dataSourceConfigManagerType[%s]",
						      configManagerType));
					}
					dataSourceConfigManager.init();
					dataSourceConfigManagers.put(resourceId, dataSourceConfigManager);
				}

			}
		}

		return dataSourceConfigManager;
	}
}
