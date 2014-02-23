package com.dianping.zebra.group.config1;

public interface GroupConfigChangeListener {

	public void onChange(BaseGroupConfigChangeEvent event);
	
	public String getName();
}
