package com.dianping.zebra.group.config;

public interface GroupConfigChangeListener {

	void onChange(GroupConfigChangeEvent event);

	String getName();

}
