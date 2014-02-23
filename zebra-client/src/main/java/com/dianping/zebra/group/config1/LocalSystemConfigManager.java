package com.dianping.zebra.group.config1;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

import com.dianping.zebra.group.config.system.entity.SystemConfig;
import com.dianping.zebra.group.config.system.transform.DefaultSaxParser;
import com.dianping.zebra.group.exception.GroupConfigException;

public class LocalSystemConfigManager implements SystemConfigManager {

	private String resourceId;

	private List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<PropertyChangeListener>();

	private AtomicReference<SystemConfig> systemConfig = new AtomicReference<SystemConfig>();

	public LocalSystemConfigManager(String resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public void init() {
		try {
			systemConfig.set(DefaultSaxParser.parse(this.resourceId));
		} catch (Throwable e) {
			throw new GroupConfigException(String.format(
			      "fail to initialize LocalSystemConfigManager with config file[%s].", this.resourceId), e);
		}
	}

	@Override
	public void addListerner(PropertyChangeListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public SystemConfig getSystemConfig() {
		return systemConfig.get();
	}
	
	
}
