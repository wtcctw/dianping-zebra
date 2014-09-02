package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/2/14.
 */

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/common/appcontext-zebra-api.xml")
public class DefaultFilterManagerTest {

	@Test
	public void test_load_by_name() {
		JdbcFilter filter = FilterManagerFactory.getFilterManager().loadFilter("stat");
		Assert.assertTrue(filter instanceof FilterWrapper);
		Assert.assertEquals(((FilterWrapper) filter).size(), 1);
	}

	@Test
	public void test_load_by_empty_name() {
		JdbcFilter filter = FilterManagerFactory.getFilterManager().loadFilter("");
		Assert.assertTrue(filter instanceof FilterWrapper);
		Assert.assertEquals(((FilterWrapper) filter).size(), 0);
	}
}
