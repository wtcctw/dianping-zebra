package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.ConfigChange;
import com.dianping.lion.client.LionException;
import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.exception.IllegalConfigException;

public class LionConfigService implements ConfigService {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<PropertyChangeListener>();

	@Override
	public String getProperty(String key) {
		try {
			String value = ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(key);
			return value == null ? null : value.trim();
		} catch (LionException e) {
			return null;
		}
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
					if (key.startsWith(Constants.DEFAULT_DATASOURCE_SINGLE_PRFIX)
						  || key.startsWith(Constants.DEFAULT_DATASOURCE_GROUP_PRFIX)
						  || key.startsWith(Constants.DEFAULT_DATASOURCE_ZEBRA_SQL_BLACKLIST_PRFIX)
					      || key.startsWith(Constants.DEFAULT_DATASOURCE_ZEBRA_PRFIX)) {
						PropertyChangeEvent event = new AdvancedPropertyChangeEvent(this, key, null, value);
						for (PropertyChangeListener listener : listeners) {
							listener.propertyChange(event);
						}
					}
				}
			});
		} catch (LionException e) {
			logger.error("fail to initilize Remote Config Manager for DAL", e);
			throw new IllegalConfigException(e);
		}
	}
}
