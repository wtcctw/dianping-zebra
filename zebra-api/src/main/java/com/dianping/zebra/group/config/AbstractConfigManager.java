package com.dianping.zebra.group.config;

import com.dianping.zebra.config.ConfigService;
import com.dianping.zebra.util.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractConfigManager {

	private static final Logger logger = LogManager.getLogger(AbstractConfigManager.class);

	protected final ConfigService configService;

	protected List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<PropertyChangeListener>();

	private ExecutorService listenerNotifyThreadPool = Executors.newFixedThreadPool(5, new ThreadFactory() {
		private AtomicInteger id = new AtomicInteger(0);

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setDaemon(true);
			t.setName("Dal-Config-Notify-" + id.incrementAndGet());

			return t;
		}
	});

	public AbstractConfigManager(ConfigService configService) {
		this.configService = configService;
		this.configService.addPropertyChangeListener(new InnerPropertyChangeListener());
	}

	public boolean getProperty(String key, boolean defaultValue) {
		String value = configService.getProperty(key);

		if ("true".equalsIgnoreCase(value)) {
			return true;
		} else if ("false".equalsIgnoreCase(value)) {
			return false;
		}

		return defaultValue;
	}

	protected int getProperty(String key, int defaultValue) {
		String value = configService.getProperty(key);

		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		} else {
			return defaultValue;
		}
	}

	protected long getProperty(String key, long defaultValue) {
		String value = configService.getProperty(key);

		if (StringUtils.isNotBlank(value)) {
			return Long.parseLong(value);
		} else {
			return defaultValue;
		}
	}

	protected String getProperty(String key, String defaultValue) {
		String value = configService.getProperty(key);

		if (StringUtils.isNotBlank(value)) {
			return value;
		} else {
			return defaultValue;
		}
	}

	private void notifyListeners(final PropertyChangeEvent evt) {
		for (final PropertyChangeListener listener : listeners) {
			Runnable task = new Runnable() {

				@Override
				public void run() {
					listener.propertyChange(evt);
				}
			};

			listenerNotifyThreadPool.submit(task);
		}
	}

	protected abstract void onPropertyUpdated(PropertyChangeEvent evt);

	class InnerPropertyChangeListener implements PropertyChangeListener {
		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			try {
				onPropertyUpdated(evt);
				notifyListeners(evt);
			} catch (Exception e) {
				logger.error("fail to update property, apply old config!", e);
			}
		}
	}
}
