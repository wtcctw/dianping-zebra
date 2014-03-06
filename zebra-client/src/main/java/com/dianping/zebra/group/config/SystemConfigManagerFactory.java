package com.dianping.zebra.group.config;

import java.util.HashMap;
import java.util.Map;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.exception.GroupConfigException;

public class SystemConfigManagerFactory {

	private static Map<String, SystemConfigManager> systemConfigManagers = new HashMap<String, SystemConfigManager>();

	public static SystemConfigManager getConfigManger(String configManagerType, String resourceId) {
		SystemConfigManager systemConfigManager = systemConfigManagers.get(resourceId);

		if (systemConfigManager == null) {
			synchronized (systemConfigManagers) {
				systemConfigManager = systemConfigManagers.get(resourceId);

				if (systemConfigManager == null) {
					if (Constants.CONFIG_MANAGER_TYPE_LOCAL.equalsIgnoreCase(configManagerType)) {
						LocalConfigService configService = new LocalConfigService(resourceId);
						configService.init();
						systemConfigManager = new DefaultSystemConfigManager(resourceId, configService);
					} else if (Constants.CONFIG_MANAGER_TYPE_REMOTE.equalsIgnoreCase(configManagerType)) {
						LionConfigService configService = new LionConfigService(resourceId);
						configService.init();
						systemConfigManager = new DefaultSystemConfigManager(resourceId, configService);
					} else {
						throw new GroupConfigException(
						      String.format("illegal systemConfigManagerType[%s]", configManagerType));
					}
					systemConfigManager.init();
					systemConfigManagers.put(resourceId, systemConfigManager);
				}

			}
		}

		return systemConfigManager;
	}
}
