package com.dianping.zebra.group.config;

import com.dianping.zebra.Constants;
import com.dianping.zebra.group.exception.IllegalConfigException;

import java.util.HashMap;
import java.util.Map;

public final class SystemConfigManagerFactory {

	private static Map<String, SystemConfigManager> systemConfigManagers = new HashMap<String, SystemConfigManager>();

	private SystemConfigManagerFactory() {
	}

	public static SystemConfigManager getConfigManger(String configManagerType, String name) {
		SystemConfigManager systemConfigManager = systemConfigManagers.get(name);

		if (systemConfigManager == null) {
			synchronized (systemConfigManagers) {
				systemConfigManager = systemConfigManagers.get(name);

				if (systemConfigManager == null) {
					if (Constants.CONFIG_MANAGER_TYPE_LOCAL.equalsIgnoreCase(configManagerType)) {
						LocalConfigService configService = new LocalConfigService(name);
						configService.init();
						systemConfigManager = new DefaultSystemConfigManager(name, configService);
					} else if (Constants.CONFIG_MANAGER_TYPE_REMOTE.equalsIgnoreCase(configManagerType)) {
						LionConfigService configService = new LionConfigService();
						configService.init();
						systemConfigManager = new DefaultSystemConfigManager(name, configService);
					} else {
						throw new IllegalConfigException(String.format("illegal systemConfigManagerType[%s]",
								configManagerType));
					}
					systemConfigManager.init();
					systemConfigManagers.put(name, systemConfigManager);
				}

			}
		}

		return systemConfigManager;
	}
}
