package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/2/14.
 */
public interface FilterManager {
	void addFilter(String name, JdbcFilter filter);

	void init();

	JdbcFilter loadFilter(String remoteConfig,String beanConfig);
}
