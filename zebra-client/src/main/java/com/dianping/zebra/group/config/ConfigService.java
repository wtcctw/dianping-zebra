package com.dianping.zebra.group.config;

import java.beans.PropertyChangeListener;

public interface ConfigService {
	public void init();

	public String getProperty(String key);

	public void addPropertyChangeListener(PropertyChangeListener listener);
}
