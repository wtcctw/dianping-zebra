package com.dianping.zebra.group.config;

import junit.framework.Assert;

import org.junit.Test;

import com.dianping.zebra.group.config.system.entity.SystemConfig;

public class SystemConfigManagerTest {
	
	@Test
	public void testConfig(){
		SystemConfigManager systemConfigManager = SystemConfigManagerFactory.getConfigManger("local", "zebra.v2.system");
		
		SystemConfig config = systemConfigManager.getSystemConfig();
		
		Assert.assertEquals(2, config.getRetryTimes());
	}

}
