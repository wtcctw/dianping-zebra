package com.dianping.zebra.group.config1;

import java.beans.PropertyChangeListener;

import com.dianping.zebra.group.config.system.entity.SystemConfig;

public class RemoteSystemConfigManager implements SystemConfigManager {

	private final String resourceId;

	public RemoteSystemConfigManager(String resourceId) {
		this.resourceId = resourceId;
	   // TODO Auto-generated constructor stub
   }

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListerner(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
   public SystemConfig getSystemConfig() {
	   // TODO Auto-generated method stub
	   return null;
   }
}
