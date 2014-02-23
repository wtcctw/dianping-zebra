package com.dianping.zebra.group.healthcheck;

import java.util.concurrent.atomic.AtomicReference;

import com.dianping.zebra.group.config1.GroupConfigManager;

public class HealthCheckFactory {

	private static AtomicReference<HealthCheck> mysqlHealthCheck = new AtomicReference<HealthCheck>();

	public static HealthCheck getCheck(String type, GroupConfigManager configManager) {
		if ("mysql".equalsIgnoreCase(type)) {
			HealthCheck healthCheck = mysqlHealthCheck.get();
			if (healthCheck == null) {
				synchronized (mysqlHealthCheck) {
					healthCheck = mysqlHealthCheck.get();
					if (healthCheck == null) {
						healthCheck = new MysqlHealthCheckImpl(configManager);
						mysqlHealthCheck.set(healthCheck);
					}
				}
			}
			return mysqlHealthCheck.get();
		}
		return null;
	}
}
