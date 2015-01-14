package com.dianping.zebra.group.router;

import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

public class DpdlReadWriteStrategyImpl implements CustomizedReadWriteStrategy {
	private static Method getContextMethod = null;

	private static Method isAuthenticatedMethod = null;

	private static boolean serviceFlag = false;

	private GroupDataSourceConfig config;

	static {
		try {
			Class<?> contextHolderClass = Class.forName("com.dianping.avatar.tracker.ExecutionContextHolder");
			getContextMethod = contextHolderClass.getDeclaredMethod("getTrackerContext", new Class[] { });
			getContextMethod.setAccessible(true);

			Class<?> contextClass = Class.forName("com.dianping.avatar.tracker.TrackerContext");
			isAuthenticatedMethod = contextClass.getDeclaredMethod("isAuthenticated", new Class[] { });
			isAuthenticatedMethod.setAccessible(true);

			serviceFlag = true;
		} catch (ClassNotFoundException ignore) {
		} catch (SecurityException ignore) {
		} catch (NoSuchMethodException ignore) {
		}
	}

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public boolean forceReadFromMaster() {
		if (serviceFlag && config != null && config.getLoginForceWrite()) {
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

	@Override public void setGroupDataSourceConfig(GroupDataSourceConfig config) {
		this.config = config;
	}
}
