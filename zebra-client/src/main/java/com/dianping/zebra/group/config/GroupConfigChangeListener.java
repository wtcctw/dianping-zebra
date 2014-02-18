package com.dianping.zebra.group.config;

public interface GroupConfigChangeListener {

	public void onChange(BaseGroupConfigChangeEvent event);
	
	public String getName();
}
