package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/2/14.
 */
public interface FilterManager {
	JdbcFilter loadFilter(String filterBeanName);
	void init();
}
