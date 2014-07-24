package com.dianping.zebra.group.util;

import org.junit.Assert;
import org.junit.Test;

public class AtomicRefreshTest {
	@Test
	public void test_atomich_refresh() {
		AtomicRefresh ar = new AtomicRefresh();

		ar.setUser("user");
		Assert.assertFalse(ar.needToRefresh());
		ar.setUser("user1");
		Assert.assertFalse(ar.needToRefresh());

		ar.setPassword("password");
		Assert.assertTrue(ar.needToRefresh());

		Assert.assertEquals("user1", ar.getNewUser());
		Assert.assertEquals("password", ar.getNewPassword());

		ar.reset();

		ar.setPassword("password3");
		Assert.assertFalse(ar.needToRefresh());
		ar.setUser("user3");
		Assert.assertTrue(ar.needToRefresh());
	}

	@Test
	public void test_atomich_refresh_with_null() {
		AtomicRefresh ar = new AtomicRefresh();
		ar.setUser(null);
		Assert.assertFalse(ar.needToRefresh());
		ar.setPassword(null);
		Assert.assertFalse(ar.needToRefresh());
		ar.setUser("user");
		Assert.assertFalse(ar.needToRefresh());
		ar.setPassword("password");
		Assert.assertTrue(ar.needToRefresh());

		ar.reset();

		ar.setUser(null);
		Assert.assertFalse(ar.needToRefresh());

		ar.setPassword(null);
		Assert.assertTrue(ar.needToRefresh());
	}

}
