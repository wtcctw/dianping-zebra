package com.dianping.zebra.group.router;

import java.lang.reflect.Method;

import com.dianping.cat.Cat;

public class DpdlReadWriteStrategyImpl implements CustomizedReadWriteStrategy {
	private static Method getContextMethod = null;

	private static Object[] defObjs = new Object[] {};

	private static Method isAuthenticatedMethod = null;

	private static boolean serviceFlag = false;

	static {
		try {
			Class contextHolderClass = Class.forName("com.dianping.avatar.tracker.ExecutionContextHolder");
			getContextMethod = contextHolderClass.getDeclaredMethod("getTrackerContext", new Class[] {});
			getContextMethod.setAccessible(true);

			Class contextClass = Class.forName("com.dianping.avatar.tracker.TrackerContext");
			isAuthenticatedMethod = contextClass.getDeclaredMethod("isAuthenticated", new Class[] {});
			isAuthenticatedMethod.setAccessible(true);

			serviceFlag = true;
		} catch (Exception ignore) {
		}
	}

	@Override
	public boolean forceReadFromMaster() {
		if (serviceFlag) {
			try {
				Object context = getContextMethod.invoke(null, defObjs);
				if (context != null) {
					Boolean result = (Boolean) this.isAuthenticatedMethod.invoke(context, defObjs);
					Cat.logEvent("DAL", "UseForceWriteByAuthenticated");
					return result;
				}
			} catch (Exception ignore) {
			}
		}
		return false;
	}

}
