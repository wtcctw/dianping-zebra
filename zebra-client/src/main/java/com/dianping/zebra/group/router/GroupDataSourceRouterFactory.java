package com.dianping.zebra.group.router;

import java.util.concurrent.atomic.AtomicReference;

import com.dianping.zebra.group.config.GroupConfigManager;

public class GroupDataSourceRouterFactory {

	private static final String ROUNDROBIN = "roundrobin";

	private static AtomicReference<GroupReadWriteDataSourceRouter> readWriteDataSourceRouter = new AtomicReference<GroupReadWriteDataSourceRouter>();

	public static GroupDataSourceRouter getDataSourceRouter(GroupConfigManager configManager) {

		String routerStrategy = configManager.getGroupDataSourceConfig().getRouterStrategy();

		if (ROUNDROBIN.equalsIgnoreCase(routerStrategy)) {

			if (readWriteDataSourceRouter.get() == null) {
				synchronized (readWriteDataSourceRouter) {
					if (readWriteDataSourceRouter.get() == null) {
						GroupReadWriteDataSourceRouter _readWriteDataSourceRouter = new GroupReadWriteDataSourceRouter(
						      configManager.getAvailableDataSources());
						configManager.addListerner(_readWriteDataSourceRouter);
						readWriteDataSourceRouter.set(_readWriteDataSourceRouter);
					}
				}
			}

			return readWriteDataSourceRouter.get();

		} else {
			throw new IllegalArgumentException("Strategy is not correct: " + routerStrategy);
		}

	}
}
