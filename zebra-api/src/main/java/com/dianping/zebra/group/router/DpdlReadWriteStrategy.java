package com.dianping.zebra.group.router;

import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

public class DpdlReadWriteStrategy implements ReadWriteStrategy {
	private static final Logger logger = LogManager.getLogger(DpdlReadWriteStrategy.class);

	private static Method getContextMethod = null;

	private static Method isAuthenticatedMethod = null;

	private GroupDataSourceConfig config;

	static {
		try {
			Class<?> contextHolderClass = Class.forName("com.dianping.avatar.tracker.ExecutionContextHolder");
			getContextMethod = contextHolderClass.getDeclaredMethod("getTrackerContext", new Class[] {});
			getContextMethod.setAccessible(true);

			Class<?> contextClass = Class.forName("com.dianping.avatar.tracker.TrackerContext");
			isAuthenticatedMethod = contextClass.getDeclaredMethod("isAuthenticated", new Class[] {});
			isAuthenticatedMethod.setAccessible(true);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public boolean shouldReadFromMaster() {
		if (config != null && config.getForceWriteOnLogin()) {
			try {
				Object context = getContextMethod.invoke(null);
				if (context != null) {
					return (Boolean) isAuthenticatedMethod.invoke(context);
				}
			} catch (Exception error) {
				logger.error(error.getMessage(), error);
			}
		}

		return false;
	}

	@Override
	public void setGroupDataSourceConfig(GroupDataSourceConfig config) {
		this.config = config;
	}
}
