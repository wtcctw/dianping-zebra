package com.dianping.zebra.environment.filter;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.router.GroupDataSourceContext;

public class DPDataSourceContext extends GroupDataSourceContext implements Serializable {
	private static final String CONTEXT_NAME = "com.dianping.zebra.group.router.GroupDataSourceContext";

	private static final long serialVersionUID = 6881706441282935789L;

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

	public static DPDataSourceContext getContext() {
		// 如果trackeContext存在dsContext,则优先使用TrackerContext中的dsContext
		DPDataSourceContext dsContext = getFromTrackerContext();
		if (dsContext != null) {
			// 保证GroupDataSourceContext也是使用该dsContext
			GroupDataSourceContext.set(dsContext);
		} else {
			// trackerContext不存在dsContext，则从GroupDataSourceContext获取
			GroupDataSourceContext context = GroupDataSourceContext.get();

			// 既然使用了DPDataSourceContext的方法，要求DataSourceContext实际的类也是DPDataSourceContext
			if (context instanceof DPDataSourceContext) {
				dsContext = (DPDataSourceContext) context;
			} else {
				dsContext = new DPDataSourceContext(context);
				GroupDataSourceContext.set(dsContext);
			}
			// 将DPDataSourceContext放到TrackerContext
			putIntoTrackerContext(dsContext);
		}

		return dsContext;
	}

	public static void clearContext() {
		GroupDataSourceContext.clear();

		clearTrackerContext();
	}

	@Override
	public boolean getMasterFlag() {
		return super.getMasterFlag();
	}

	@Override
	public void setMasterFlag(boolean masterFlag) {
		this.setMasterFlag(masterFlag, true);
	}

	public void setMasterFlag(boolean masterFlag, boolean shouldSetCookie) {
		super.setMasterFlag(masterFlag);
		this.shouldSetCookie = shouldSetCookie;
	}

	public boolean isShouldSetCookie() {
		return shouldSetCookie;
	}

	public DPDataSourceContext(GroupDataSourceContext context) {
		this.setMasterFlag(context.getMasterFlag());
	}

	// **************** 以下代码与TrackerContext相关 ***************************

	private static DPDataSourceContext getFromTrackerContext() {
		DPDataSourceContext dsContext = null;

		if (trackerContextExist) {
			try {
				Object trackerContext = getContextMethod.invoke(null);

				if (trackerContext == null) {
					trackerContext = trackerContextClass.newInstance();
					setContextMethod.invoke(null, trackerContext);
				}
				dsContext = (DPDataSourceContext) getExtensionMethod.invoke(trackerContext, CONTEXT_NAME);
			} catch (Exception e) {
				logger.warn("Error in TrackerContext, ignore TrackerContext", e);
			}
		}

		return dsContext;
	}

	private static void putIntoTrackerContext(DPDataSourceContext _dsContext) {
		if (trackerContextExist) {
			try {
				Object trackerContext = getContextMethod.invoke(null);

				if (trackerContext == null) {
					trackerContext = trackerContextClass.newInstance();
					setContextMethod.invoke(null, trackerContext);
				}

				addExtensionMethod.invoke(trackerContext, CONTEXT_NAME);
			} catch (Exception e) {
				logger.warn("Error in TrackerContext, ignore TrackerContext", e);
			}
		}
	}

	private static void clearTrackerContext() {
		// 如果存在trackerContext，清理之
		if (trackerContextExist) {
			try {
				clearContextMethod.invoke(null);
			} catch (Exception e) {
				logger.warn("Error in TrackerContext, ignore clear TrackerContext", e);
			}
		}
	}
}
