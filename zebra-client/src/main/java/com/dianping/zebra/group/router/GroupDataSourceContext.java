package com.dianping.zebra.group.router;

import java.io.Serializable;

public class GroupDataSourceContext implements Serializable {
	private static final long serialVersionUID = 6881706441282935789L;

	private static ThreadLocal<GroupDataSourceContext> threadLocalContext = new InheritableThreadLocal<GroupDataSourceContext>() {
		public GroupDataSourceContext initialValue() {
			return new GroupDataSourceContext();
		}
	};

	private boolean masterFlag = false;

	public static GroupDataSourceContext get() {
		return threadLocalContext.get();
	}

	public static void set(GroupDataSourceContext context) {
		threadLocalContext.set(context);
	}

	public static void clear() {
		threadLocalContext.remove();
	}

	public boolean getMasterFlag() {
		return masterFlag;
	}

	public void setMasterFlag(boolean masterFlag) {
		this.masterFlag = masterFlag;
	}

}
