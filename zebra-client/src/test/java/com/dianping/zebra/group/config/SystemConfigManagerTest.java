package com.dianping.zebra.group.config;

import junit.framework.Assert;

import org.junit.Test;

import com.dianping.zebra.group.config.system.entity.SystemConfig;

public class SystemConfigManagerTest {
	
	@Test
	public void testConfig(){
		SystemConfigManager systemConfigManager = SystemConfigManagerFactory.getConfigManger("local", "zebra.system");
		
		SystemConfig config = systemConfigManager.getSystemConfig();
		
		Assert.assertEquals(10, config.getHealthCheckInterval());
		Assert.assertEquals(3, config.getMaxErrorCounter());
		Assert.assertEquals(2, config.getRetryTimes());
		Assert.assertEquals(2, config.getCookieExpiredTime());
		Assert.assertEquals("roundrobin", config.getRouterStrategy());
		Assert.assertEquals("roundrobin", config.getRouterStrategy());
		Assert.assertEquals(".dianping.com", config.getCookieDomain());
		Assert.assertEquals("zebra", config.getCookieName());
		Assert.assertEquals("2123174217368174103", config.getEncryptSeed());
	}

}
