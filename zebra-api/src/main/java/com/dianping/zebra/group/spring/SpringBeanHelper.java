package com.dianping.zebra.group.spring;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dozer on 10/15/14.
 */
public final class SpringBeanHelper {
	private static Boolean _hasSpring = null;

	private static boolean hasSpring() {
		if (_hasSpring == null) {
			try {
				Class.forName("org.springframework.context.ApplicationContextAware");
				_hasSpring = true;
			} catch (ClassNotFoundException e) {
				_hasSpring = false;
			}
		}
		return _hasSpring;
	}

	public static <T> Map<String, T> getBeanByClass(Class<T> clazz) {
		if (!hasSpring()) {
			return new HashMap<String, T>();
		}
		return ApplicationContextProvider.getBeanByClass(clazz);
	}
}
