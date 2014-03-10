package com.dianping.zebra.environment.filter;

import java.io.Serializable;

public class DPDataSourceContext implements Serializable {
	public static final String CONTEXT_NAME = "com.dianping.zebra.group.router.GroupDataSourceContext";

	private static final long serialVersionUID = 6881706441282935789L;

	private boolean forceReadFromMaster = false;

	private boolean shouldSetCookie = false;

	public boolean getForceReadFromMaster() {
		return forceReadFromMaster;
	}

	public void setForceReadFromMaster(boolean forceReadFromMaster) {
		this.setForceReadFromMaster(forceReadFromMaster, true);
	}

	public void setForceReadFromMaster(boolean forceReadFromMaster, boolean shouldSetCookie) {
		this.forceReadFromMaster = forceReadFromMaster;
		this.shouldSetCookie = shouldSetCookie;
	}

	public boolean shouldSetCookie() {
		return shouldSetCookie;
	}

	public void setShouldSetCookie(boolean shouldSetCookie) {
   	this.shouldSetCookie = shouldSetCookie;
   }
	
	

}
