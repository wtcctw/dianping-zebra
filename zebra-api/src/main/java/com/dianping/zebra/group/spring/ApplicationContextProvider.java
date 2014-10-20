package com.dianping.zebra.group.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dozer on 10/15/14.
 */
public final class ApplicationContextProvider implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@SuppressWarnings("unchecked")
   public static <T> Map<String, T> getBeanByClass(Class<T> clazz) {
		if (applicationContext == null) {
			return new HashMap<String, T>();
		}
		return applicationContext.getBeansOfType(clazz);
		
	}

	@SuppressWarnings("static-access")
   @Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
