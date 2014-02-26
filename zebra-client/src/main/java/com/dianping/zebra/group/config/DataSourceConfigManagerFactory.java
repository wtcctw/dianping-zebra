package com.dianping.zebra.group.config;

import java.util.HashMap;
import java.util.Map;

import com.dianping.zebra.group.exception.GroupConfigException;

public class DataSourceConfigManagerFactory {

	private static Map<String, DataSourceConfigManager> dataSourceConfigManagers = new HashMap<String, DataSourceConfigManager>();

	public static DataSourceConfigManager getConfigManager(String configManagerType, String resourceId) {
		DataSourceConfigManager dataSourceConfigManager = dataSourceConfigManagers.get(resourceId);

		if (dataSourceConfigManager == null) {
			synchronized (dataSourceConfigManagers) {
				dataSourceConfigManager = dataSourceConfigManagers.get(resourceId);

				if (dataSourceConfigManager == null) {
					if ("local".equalsIgnoreCase(configManagerType)) {
						dataSourceConfigManager = new DefaultDataSourceConfigManager(resourceId, new LocalConfigService(
						      resourceId));
					} else if ("remote".equalsIgnoreCase(configManagerType)) {
						dataSourceConfigManager = new DefaultDataSourceConfigManager(resourceId, new RemoteConfigService(
						      resourceId));
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
