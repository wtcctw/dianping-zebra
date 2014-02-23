package com.dianping.zebra.group.config1;

import java.beans.PropertyChangeListener;

import com.dianping.zebra.group.config.system.entity.SystemConfig;

public interface SystemConfigManager {
	public void init();

	public void addListerner(PropertyChangeListener listener);
	
	public SystemConfig getSystemConfig();
}
