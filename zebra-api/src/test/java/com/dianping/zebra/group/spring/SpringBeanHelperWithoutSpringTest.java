package com.dianping.zebra.group.spring;

import com.dianping.zebra.group.filter.wall.WallFilterConfig;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Dozer on 10/15/14.
 */

public class SpringBeanHelperWithoutSpringTest {
	@Test
	public void test_load_by_class() {
		Map<String, WallFilterConfig> result = SpringBeanHelper.getBeanByClass(WallFilterConfig.class);
		Assert.assertEquals(result.size(), 0);
	}
}
