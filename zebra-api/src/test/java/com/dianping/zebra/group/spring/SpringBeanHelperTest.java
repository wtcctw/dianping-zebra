package com.dianping.zebra.group.spring;

import com.dianping.zebra.group.filter.wall.WallFilterConfig;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by Dozer on 10/15/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/common/appcontext-zebra-test.xml",
	  "classpath:config/spring/common/appcontext-zebra-filter-config-test.xml" })
public class SpringBeanHelperTest {
	@Test
	public void test_load_by_class() {
		Map<String, WallFilterConfig> result = SpringBeanHelper.getBeanByClass(WallFilterConfig.class);
		Assert.assertEquals(result.size(), 1);
	}
}
