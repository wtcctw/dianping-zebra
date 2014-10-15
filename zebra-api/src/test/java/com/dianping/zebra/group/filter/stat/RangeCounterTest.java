package com.dianping.zebra.group.filter.stat;

/**
 * Created by Dozer on 9/15/14.
 */

import org.junit.Assert;
import org.junit.Test;

public class RangeCounterTest {
	@Test
	public void test_count() {
		RangeCounter targer = new RangeCounter(10, 1000, 5000);

		targer.increment(0);
		targer.increment(9);
		targer.increment(500);
		targer.increment(1000);
		targer.increment(1100);
		targer.increment(5000);
		targer.increment(5500);
		targer.increment(Integer.MAX_VALUE);

		Assert.assertEquals(targer.getResult().size(), 5);
		Assert.assertEquals(targer.getResult().get(0).get(), 1);
		Assert.assertEquals(targer.getResult().get(10).get(), 1);
		Assert.assertEquals(targer.getResult().get(1000).get(), 2);
		Assert.assertEquals(targer.getResult().get(5000).get(), 2);
		Assert.assertEquals(targer.getResult().get(Integer.MAX_VALUE).get(), 2);

	}

	@Test
	public void test_init_empty() {
		RangeCounter targer = new RangeCounter();
		Assert.assertEquals(targer.getResult().size(), 2);
	}

	@Test
	public void test_init_ignore_same_and_error_range() {
		RangeCounter targer = new RangeCounter(1, 2, 2, 6, 5);
		Assert.assertEquals(targer.getResult().size(), 5);
	}

	@Test
	public void test_init_success() {
		RangeCounter targer = new RangeCounter(1, 2, 3, 4, 5);
		Assert.assertEquals(targer.getResult().size(), 7);
	}

	@Test
	public void test_init_zero_and_max() {
		RangeCounter targer = new RangeCounter(0, 1, Integer.MAX_VALUE);
		Assert.assertEquals(targer.getResult().size(), 3);
	}
}
