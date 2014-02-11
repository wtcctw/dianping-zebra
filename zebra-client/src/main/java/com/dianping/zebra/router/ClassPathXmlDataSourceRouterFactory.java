/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-13
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.router;

import com.dianping.zebra.router.config.RouterRuleConfig;
import com.dianping.zebra.router.config.XmlDataSourceRouterConfigLoader;
import com.dianping.zebra.router.rule.RouterRule;
import com.dianping.zebra.router.rule.RouterRuleBuilder;

/**
 * @author danson.liu
 * 
 */
public class ClassPathXmlDataSourceRouterFactory implements DataSourceRouterFactory {

	private final String					routerRuleFile;

	private XmlDataSourceRouterConfigLoader	routerConfigLoader	= new XmlDataSourceRouterConfigLoader();

	public ClassPathXmlDataSourceRouterFactory(String routerRuleFile) {
		this.routerRuleFile = routerRuleFile;
	}

	/**
	 * @param routerConfigLoader
	 *            the routerConfigLoader to set
	 */
	public void setRouterConfigLoader(XmlDataSourceRouterConfigLoader routerConfigLoader) {
		this.routerConfigLoader = routerConfigLoader;
	}

	@Override
	public DataSourceRouter getRouter() {
		DataSourceRouterImpl router = new DataSourceRouterImpl();
		RouterRuleConfig routerConfig = routerConfigLoader.loadConfig(routerRuleFile);
		RouterRule routerRule = RouterRuleBuilder.build(routerConfig);
		router.setRouterRule(routerRule);
		return router;
	}

}
