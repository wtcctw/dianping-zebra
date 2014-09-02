package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.util.SpringBeanUtils;
import com.dianping.zebra.group.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dozer on 9/2/14.
 */
public class DefaultFilterManager implements FilterManager {
	public boolean hasSpringContext;

	public void init() {
		try {
			Class.forName("org.springframework.context.ApplicationContextAware");
		} catch (ClassNotFoundException ignore) {
		}
		hasSpringContext = true;
	}

	@Override public JdbcFilter loadFilter(String filterBeanName) {
		if (!hasSpringContext || StringUtils.isBlank(filterBeanName)) {
			return new FilterWrapper();
		}

		List<JdbcFilter> result = new ArrayList<JdbcFilter>();
		String[] beanNames = filterBeanName.split(",");
		for (String beanName : beanNames) {
			Object tempBean = SpringBeanUtils.loadByName(beanName);
			if (tempBean == null) {
				continue;
			}
			if (!(tempBean instanceof JdbcFilter)) {
				continue;
			}
			result.add((JdbcFilter) tempBean);
		}

		return new FilterWrapper(result);
	}
}