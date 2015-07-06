package com.dianping.zebra.shard.router.rule;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Dozer @ 7/3/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class SimpleDataSourceProviderTest {
	@Test
	public void test_split_db() throws Exception {
		SimpleDataSourceProvider target = new SimpleDataSourceProvider();
		assertArrayEquals(new String[] { "a", "b_2", "b_3", "b_4", "c" }, target.splitDb("a,b_[2-4],c").toArray());
	}
}