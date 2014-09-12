package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/2/14.
 */

import junit.framework.Assert;
import org.junit.Test;

public class DefaultFilterManagerTest {

	@Test
	public void test_load_by_empty_name() {
		JdbcFilter filter = FilterManagerFactory.getFilterManager().loadFilter("", "");
		Assert.assertTrue(filter instanceof FilterWrapper);
		Assert.assertEquals(((FilterWrapper) filter).size(), 0);
	}

	@Test
	public void test_load_by_name() {
		JdbcFilter filter = FilterManagerFactory.getFilterManager().loadFilter("stat", "");
		Assert.assertTrue(filter instanceof FilterWrapper);
		Assert.assertEquals(((FilterWrapper) filter).size(), 1);
	}

	@Test
	public void test_load_by_two_name() {
		JdbcFilter filter = FilterManagerFactory.getFilterManager().loadFilter("stat,no_exist", "");
		Assert.assertTrue(filter instanceof FilterWrapper);
		Assert.assertEquals(((FilterWrapper) filter).size(), 1);
	}

	@Test
	public void test_load_with_ignore() {
		JdbcFilter filter = FilterManagerFactory.getFilterManager().loadFilter("stat", "!stat");
		Assert.assertTrue(filter instanceof FilterWrapper);
		Assert.assertEquals(((FilterWrapper) filter).size(), 0);
	}
}
