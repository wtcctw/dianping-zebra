/**
 * Project: zebra-environment
 * 
 * File Created at Mar 10, 2014
 * 
 */
package com.dianping.zebra.environment.filter;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.router.CustomizedReadWriteStrategy;

/**
 * @author Leo Liang
 */
public class CustomizedReadWriteStrategyImpl implements CustomizedReadWriteStrategy {

	private static final Logger logger = LoggerFactory.getLogger(CustomizedReadWriteStrategyImpl.class);

	private static final String FORCE_READ_FROM_MASTER = "ForceReadFromMaster";

	private static final String SHOULD_SET_COOKIE = "shouldSetCookie";

	public static final String CONTEXT_NAME = "com.dianping.zebra.group.router.GroupDataSourceContext";

	private static Class<?> trackerContextClass;

	private static Method getContextMethod;

	private static Method setContextMethod;

	private static Method getExtensionMethod;

	private static Method addExtensionMethod;

	private static Method clearContextMethod;

	private static boolean trackerContextExist;

	static {
		try {
			Class<?> contextHolderClass = Class.forName("com.dianping.avatar.tracker.ExecutionContextHolder");
			trackerContextClass = Class.forName("com.dianping.avatar.tracker.TrackerContext");

			getContextMethod = contextHolderClass.getDeclaredMethod("getTrackerContext", new Class[] {});
			setContextMethod = contextHolderClass.getDeclaredMethod("setTrackerContext",
			      new Class[] { trackerContextClass });
			clearContextMethod = contextHolderClass.getDeclaredMethod("clearContext");
			getExtensionMethod = trackerContextClass.getDeclaredMethod("getExtension", new Class[] { String.class });
			addExtensionMethod = trackerContextClass.getDeclaredMethod("addExtension", new Class[] { String.class,
			      Object.class });

			getContextMethod.setAccessible(true);
			setContextMethod.setAccessible(true);
			clearContextMethod.setAccessible(true);
			getExtensionMethod.setAccessible(true);
			addExtensionMethod.setAccessible(true);

			trackerContextExist = true;

		} catch (Exception e) {
			logger.warn("No App context, because: " + e.getMessage());
		}
	}

	@Override
	public boolean forceReadFromMaster() {
		if (!trackerContextExist) {
			return false;
		}

		return getBooleanFromContext(FORCE_READ_FROM_MASTER);
	}

	public static void setForceReadFromMaster() {
		setForceReadFromMaster(true);
	}

	public static void setForceReadFromMaster(boolean shouldSetCookie) {
		if (trackerContextExist) {
			getContext().put(FORCE_READ_FROM_MASTER, true);
			getContext().put(SHOULD_SET_COOKIE, shouldSetCookie);
		}
	}

	public static boolean shouldSetCookie() {
		if (!trackerContextExist) {
			return false;
		}

		return getBooleanFromContext(SHOULD_SET_COOKIE);
	}

	private static boolean getBooleanFromContext(String key) {
		Boolean value = (Boolean) getContext().get(key);

		if (value != null) {
			return value.booleanValue();
		}
		return false;
	}

	private static HashMap<String, Object> getContext() {
		HashMap<String, Object> dsContext = getFromTrackerContext();

		if (dsContext == null) {
			dsContext = new HashMap<String, Object>();

			putIntoTrackerContext(dsContext);
		}

		return dsContext;
	}

	public static void createTrackerContextIfNotExists() {
		getContext();
	}

	public static void clear() {
		clearTrackerContext();
	}

	@SuppressWarnings("unchecked")
	private static HashMap<String, Object> getFromTrackerContext() {
		HashMap<String, Object> dsContext = null;

		if (trackerContextExist) {
			try {
				Object trackerContext = getContextMethod.invoke(null);

				if (trackerContext == null) {
					trackerContext = trackerContextClass.newInstance();
					setContextMethod.invoke(null, trackerContext);
				}
				dsContext = (HashMap<String, Object>) getExtensionMethod.invoke(trackerContext, CONTEXT_NAME);
			} catch (Exception e) {
				logger.warn("Error in TrackerContext, ignore TrackerContext", e);
			}
		}

		return dsContext;
	}

	private static void putIntoTrackerContext(HashMap<String, Object> dsContext) {
		if (trackerContextExist) {
			try {
				Object trackerContext = getContextMethod.invoke(null);

				if (trackerContext == null) {
					trackerContext = trackerContextClass.newInstance();
					setContextMethod.invoke(null, trackerContext);
				}

				addExtensionMethod.invoke(trackerContext, CONTEXT_NAME, dsContext);
			} catch (Exception e) {
				logger.warn("Error in TrackerContext, ignore TrackerContext", e);
			}
		}
	}

	private static void clearTrackerContext() {
		if (trackerContextExist) {
			try {
				clearContextMethod.invoke(null);
			} catch (Exception e) {
				logger.warn("Error in TrackerContext, ignore clear TrackerContext", e);
			}
		}
	}

}
