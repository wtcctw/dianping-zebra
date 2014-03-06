package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicReference;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.system.entity.SystemConfig;
import com.dianping.zebra.group.exception.GroupConfigException;

public class DefaultSystemConfigManager extends AbstractConfigManager implements SystemConfigManager {

	private AtomicReference<SystemConfig> systemConfig = new AtomicReference<SystemConfig>();

	public DefaultSystemConfigManager(String resourceId, ConfigService configService) {
		super(resourceId, configService);
	}

	@Override
	public void addListerner(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	protected String getKey(String key) {
		return String.format("%s.%s", this.resourceId, key);
	}

	@Override
	public SystemConfig getSystemConfig() {
		return this.systemConfig.get();
	}

	@Override
	public void init() {
		try {
			this.systemConfig.set(initSystemConfig());
		} catch (Throwable e) {
			throw new GroupConfigException(String.format(
			      "Fail to initialize DefaultSystemConfigManager with config file[%s].", this.resourceId), e);
		}
	}

	public SystemConfig initSystemConfig() {
		SystemConfig config = new SystemConfig();

		config.setCookieDomain(getProperty(Constants.ELEMENT_COOKIE_DOMAIN, config.getCookieDomain()));
		config.setHealthCheckInterval(getProperty(Constants.ELEMENT_HEALTH_CHECK_INTERVAL,
		      config.getHealthCheckInterval()));
		config.setMaxErrorCounter(getProperty(Constants.ELEMENT_MAX_ERROR_COUNTER, config.getMaxErrorCounter()));
		config.setRetryTimes(getProperty(Constants.ELEMENT_RETRY_TIMES, config.getRetryTimes()));
		config.setCookieName(getProperty(Constants.ELEMENT_COOKIE_NAME, config.getCookieName()));
		config.setEncryptSeed(getProperty(Constants.ELEMENT_ENCRYPT_SEED, config.getEncryptSeed()));
		config.setCookieExpiredTime(getProperty(Constants.ELEMENT_COOKIE_EXPIRED_TIME, config.getCookieExpiredTime()));

		return config;
	}

	protected void updateProperties(PropertyChangeEvent evt) {
		String key = evt.getPropertyName();
		Object newValue = evt.getNewValue();

		synchronized (this.systemConfig) {
			SystemConfig config = this.systemConfig.get();

			if (key.equals(Constants.ELEMENT_COOKIE_DOMAIN)) {
				config.setCookieDomain((String) newValue);
			} else if (key.equals(Constants.ELEMENT_HEALTH_CHECK_INTERVAL)) {
				config.setHealthCheckInterval(Integer.parseInt((String) newValue));
			} else if (key.equals(Constants.ELEMENT_MAX_ERROR_COUNTER)) {
				config.setMaxErrorCounter(Integer.parseInt((String) newValue));
			} else if (key.equals(Constants.ELEMENT_RETRY_TIMES)) {
				config.setRetryTimes((Integer) newValue);
			} else if (key.equals(Constants.ELEMENT_COOKIE_NAME)) {
				config.setCookieName((String) newValue);
			} else if (key.equals(Constants.ELEMENT_ENCRYPT_SEED)) {
				config.setEncryptSeed((String) newValue);
			} else if (key.equals(Constants.ELEMENT_COOKIE_EXPIRED_TIME)) {
				config.setCookieExpiredTime(Integer.parseInt((String) newValue));
			}
		}
	}
}
