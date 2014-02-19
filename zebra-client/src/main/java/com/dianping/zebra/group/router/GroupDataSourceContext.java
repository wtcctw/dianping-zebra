package com.dianping.zebra.group.router;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.avatar.tracker.TrackerContext;
import com.dianping.dpsf.ContextUtil;

/**
 * 
 * TODO 放在哪个auth类里（与登录标识一样）？
 * 
 * @author atell
 * 
 */
public class GroupDataSourceContext implements Serializable {
	private static final String CONTEXT_NAME = "com.dianping.zebra.group.router.GroupDataSourceContext";

	private static final long serialVersionUID = 6881706441282935789L;

	private static final Logger logger = LoggerFactory.getLogger(GroupDataSourceContext.class);

	private static ThreadLocal<GroupDataSourceContext> threadLocalContext = new InheritableThreadLocal<GroupDataSourceContext>();

	private static GroupDataSourceContext dsContext;

	private boolean forceReadFromMasterByCookie = false;

	private boolean forceReadFromMaster = false;

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
		try {
			Class.forName("com.dianping.dpsf.ContextUtil");// 检查是否依赖了dpsf，如果没有依赖，则忽略

			TrackerContext trackerContext = (TrackerContext) ContextUtil.getContext();
			if (trackerContext == null) {
				trackerContext = new TrackerContext();
				ContextUtil.setContext(trackerContext);
			}
			_dsContext = (GroupDataSourceContext) trackerContext.getExtension(CONTEXT_NAME);
			if (_dsContext == null) {
				_dsContext = new GroupDataSourceContext();
				trackerContext.addExtension(CONTEXT_NAME, _dsContext);
			}

		} catch (ClassNotFoundException e) {
		} catch (Exception e) {
			logger.warn("Error in dpsf, ignore TrackerContext from dpsf", e);
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
