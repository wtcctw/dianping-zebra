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
 * 
 */
public class CustomizedReadWriteStrategyImpl implements CustomizedReadWriteStrategy {

	private static final Logger logger = LoggerFactory.getLogger(DPDataSourceContext.class);

	private static Class<?> trackerContextClass;

	private static Method getContextMethod;

	private static Method setContextMethod;

	private static Method getExtensionMethod;

	private static Method addExtensionMethod;

	private static Method clearContextMethod;

	private static boolean trackerContextExist;

	private boolean shouldSetCookie = false;

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
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setForceReadFromMaster(boolean fromMaster){
		
	}

}
