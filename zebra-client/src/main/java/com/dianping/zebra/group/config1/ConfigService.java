package com.dianping.zebra.group.config1;

import java.beans.PropertyChangeListener;

public interface ConfigService {
	
	public void init();

	public Object getProperty(String key);

	public void addPropertyChangeListener(PropertyChangeListener listener);
}
