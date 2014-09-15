package com.dianping.zebra.monitor.filter;

/**
 * Created by Dozer on 9/9/14.
 */

import com.dianping.zebra.group.filter.FilterManagerFactory;
import com.dianping.zebra.group.filter.FilterWrapper;
import com.dianping.zebra.group.filter.JdbcFilter;
import org.junit.Test;

public class CatFilterTest {
	@Test
	public void load_config_test() {
		JdbcFilter filter = FilterManagerFactory.getFilterManager().loadFilter("cat", null);
		junit.framework.Assert.assertTrue(filter instanceof FilterWrapper);
		junit.framework.Assert.assertEquals(((FilterWrapper) filter).size(), 1);
	}
}
