package com.dianping.zebra.group.router;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupDataSourceContext implements Serializable {
	private static final String CONTEXT_NAME = "com.dianping.zebra.group.router.GroupDataSourceContext";

	private static final long serialVersionUID = 6881706441282935789L;

	private static final Logger logger = LoggerFactory.getLogger(GroupDataSourceContext.class);

	private static ThreadLocal<GroupDataSourceContext> threadLocalContext = new InheritableThreadLocal<GroupDataSourceContext>();

	private static GroupDataSourceContext dsContext;

	private static Class<?> trackerContextClass;

	private static Method getContextMethod;

	private static Method setContextMethod;

	private static Method getExtensionMethod;

	private static Method addExtensionMethod;

	private boolean forceReadFromMasterByCookie = false;

	private boolean forceReadFromMaster = false;

	static {
		try {
			Class<?> contextHolderClass = Class.forName("com.dianping.dpsf.ContextUtil");
			trackerContextClass = Class.forName("com.dianping.avatar.tracker.TrackerContext");

			getContextMethod = contextHolderClass.getDeclaredMethod("getContext", new Class[] {});
			setContextMethod = contextHolderClass.getDeclaredMethod("setContext", new Class[] { trackerContextClass });
			getExtensionMethod = trackerContextClass.getDeclaredMethod("getExtension", new Class[] { String.class });
			addExtensionMethod = trackerContextClass.getDeclaredMethod("addExtension", new Class[] { String.class });

			getContextMethod.setAccessible(true);
			setContextMethod.setAccessible(true);
			getExtensionMethod.setAccessible(true);
			addExtensionMethod.setAccessible(true);

		} catch (Exception e) {
			logger.warn("No App context: " + e.getMessage(), e);
		}

	}

	public static GroupDataSourceContext get() {
		dsContext = threadLocalContext.get();

		if (dsContext == null) {
			// 先尝试从dpsf中获取
			dsContext = getContextFromDpsf();

			if (dsContext == null) {
				dsContext = new GroupDataSourceContext();
			}

			threadLocalContext.set(dsContext);
		}

		return dsContext;
	}

	private static GroupDataSourceContext getContextFromDpsf() {
		GroupDataSourceContext _dsContext = null;

		if (trackerContextClass != null) {
			try {
				Object trackerContext = getContextMethod.invoke(null);

				if (trackerContext == null) {
					trackerContext = trackerContextClass.newInstance();
					setContextMethod.invoke(null, trackerContext);
				}
				_dsContext = (GroupDataSourceContext) getExtensionMethod.invoke(trackerContext, CONTEXT_NAME);

				if (_dsContext == null) {
					_dsContext = new GroupDataSourceContext();
					addExtensionMethod.invoke(trackerContext, CONTEXT_NAME);
				}

			} catch (Exception e) {
				logger.warn("Error in dpsf, ignore TrackerContext from dpsf", e);
			}
		}

		return _dsContext;
	}

	public static void clear() {
		threadLocalContext.remove();
	}

	public boolean isForceReadFromMaster() {
		return forceReadFromMaster;
	}

	public void setForceReadFromMaster(boolean forceReadFromMaster) {

		this.forceReadFromMaster = forceReadFromMaster;
	}

	public boolean isForceReadFromMasterByCookie() {
		return forceReadFromMasterByCookie;
	}

	public void setForceReadFromMasterByCookie(boolean forceReadFromMasterByCookie) {
		this.forceReadFromMasterByCookie = forceReadFromMasterByCookie;
	}

}
