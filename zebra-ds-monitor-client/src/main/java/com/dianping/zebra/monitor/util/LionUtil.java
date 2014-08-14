package com.dianping.zebra.monitor.util;

import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.LionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.TypedStringValue;

/**
 * Created by Dozer on 8/13/14.
 */
public final class LionUtil {
	private LionUtil() {
	}

	public static String getLionValueFromBean(BeanDefinition bean, String key) {
		try {
			String value = ((TypedStringValue) bean.getPropertyValues().getPropertyValue(key).getValue())
					.getValue().trim();

			return isLionKey(value) ? getLionConfig(trimLionKey(value)) : value;
		} catch (Exception e) {
			return null;
		}
	}

	private static boolean isLionKey(String key) {
		return key.startsWith("${") && key.endsWith("}");
	}

	private static String trimLionKey(String key) {
		if (isLionKey(key)) {
			key = key.substring(2, key.length() - 1);
		}
		return key;
	}

	public static String getLionConfig(String key) {
		try {
			return ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(key);
		} catch (LionException e) {
			return null;
		}
	}
}
