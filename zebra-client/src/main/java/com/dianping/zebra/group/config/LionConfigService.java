package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.ConfigChange;
import com.dianping.lion.client.LionException;
import com.dianping.zebra.group.exception.GroupConfigException;

public class LionConfigService implements ConfigService {

	private List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<PropertyChangeListener>();

	private final ConcurrentMap<String, String> cache = new ConcurrentHashMap<String, String>();

	public LionConfigService(String resourceId) {
	}

	@Override
	public String getProperty(String key) {
		if (!cache.containsKey(key)) {
			try {
				String value = ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(key);
				
				if(value != null){
					cache.put(key, value);
				}else{
					return null;
				}
			} catch (LionException e) {
			}
		}
		return cache.get(key);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	public void init() {
		try {
			ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).addChange(new ConfigChange() {
				@Override
				public void onChange(String key, String value) {
					String oldValue = cache.get(key);
					cache.put(key, value);
					PropertyChangeEvent event = new AdvancedPropertyChangeEvent(this, key, oldValue, value);
					for (PropertyChangeListener listener : listeners) {
						listener.propertyChange(event);
					}
				}
			});
		} catch (LionException e) {
			throw new GroupConfigException(e);
		}
	}
}
