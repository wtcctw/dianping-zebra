/**
 * Project: zebra-environment
 * 
 * File Created at Mar 10, 2014
 * 
 */
package com.dianping.zebra.environment.filter;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.router.CustomizedReadWriteStrategy;

/**
 * @author Leo Liang
 */
public class CustomizedReadWriteStrategyImpl implements CustomizedReadWriteStrategy {

	private static final Logger logger = LoggerFactory.getLogger(CustomizedReadWriteStrategyImpl.class);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dianping.zebra.group.router.CustomizedReadWriteStrategy#forceReadFromMaster()
	 */
	@Override
	public boolean forceReadFromMaster() {
		if (!trackerContextExist) {
			return false;
		}

		return getContext().getForceReadFromMaster();
	}

	public static void setForceReadFromMaster(boolean fromMaster) {
		setForceReadFromMaster(fromMaster, true);
	}

	public static void setForceReadFromMaster(boolean fromMaster, boolean shouldSetCookie) {
		if (trackerContextExist) {
			getContext().setForceReadFromMaster(fromMaster, shouldSetCookie);
		}
	}

	public static boolean shouldSetCookie() {
		if (!trackerContextExist) {
			return false;
		}

		return getContext().shouldSetCookie();
	}

	private static DPDataSourceContext getContext() {
		DPDataSourceContext dsContext = getFromTrackerContext();

		if (dsContext == null) {
			dsContext = new DPDataSourceContext();

			putIntoTrackerContext(dsContext);
		}

		return dsContext;
	}

	public static void clear() {
		clearTrackerContext();
	}

	private static DPDataSourceContext getFromTrackerContext() {
		DPDataSourceContext dsContext = null;

		if (trackerContextExist) {
			try {
				Object trackerContext = getContextMethod.invoke(null);

				if (trackerContext == null) {
					trackerContext = trackerContextClass.newInstance();
					setContextMethod.invoke(null, trackerContext);
				}
				dsContext = (DPDataSourceContext) getExtensionMethod.invoke(trackerContext,
				      DPDataSourceContext.CONTEXT_NAME);
			} catch (Exception e) {
				logger.warn("Error in TrackerContext, ignore TrackerContext", e);
			}
		}

		return dsContext;
	}

	private static void putIntoTrackerContext(DPDataSourceContext dsContext) {
		if (trackerContextExist) {
			try {
				Object trackerContext = getContextMethod.invoke(null);

				if (trackerContext == null) {
					trackerContext = trackerContextClass.newInstance();
					setContextMethod.invoke(null, trackerContext);
				}

				addExtensionMethod.invoke(trackerContext, DPDataSourceContext.CONTEXT_NAME, dsContext);
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
