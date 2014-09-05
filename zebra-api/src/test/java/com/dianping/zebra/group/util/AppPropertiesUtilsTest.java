package com.dianping.zebra.group.util;

import org.junit.Assert;
import org.junit.Test;

public class AppPropertiesUtilsTest {

	@Test
	public void test(){
		Assert.assertEquals("zebra-test", AppPropertiesUtils.getAppName());
	}
}
