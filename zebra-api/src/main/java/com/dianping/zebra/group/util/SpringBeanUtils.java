package com.dianping.zebra.group.util;

import com.dianping.zebra.group.spring.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

/**
 * Created by Dozer on 9/2/14.
 */
public class SpringBeanUtils {
	public static Object loadByName(String name) {
		try {
			Class.forName("org.springframework.context.ApplicationContextAware");
		} catch (ClassNotFoundException ignore) {
			return null;
		}

		ContextSingletonBeanFactoryLocator.getInstance().useBeanFactory()

		ApplicationContext context = ApplicationContextProvider.getCurrentApplicationContext();
		if (context == null) {
			return null;
		}

		return context.getBean(name);
	}
}
