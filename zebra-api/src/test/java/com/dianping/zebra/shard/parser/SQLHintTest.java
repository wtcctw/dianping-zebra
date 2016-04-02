package com.dianping.zebra.shard.parser;

import org.junit.Test;

import junit.framework.Assert;

public class SQLHintTest {

	@Test
	public void test0() {
		String line = "";

		SQLHint hint = SQLHint.parseHint(line);

		Assert.assertNotNull(hint);
	}

	@Test
	public void test1() {
		String line = "/*+zebra:w*/";

		SQLHint hint = SQLHint.parseHint(line);

		Assert.assertEquals(true, hint.isForceMaster());
		Assert.assertNull(hint.getColumns());
	}

	@Test
	public void test2() {
		String line = "/*+zebra:w|skv=UserID(1,2,3)*/";

		SQLHint hint = SQLHint.parseHint(line);

		Assert.assertEquals(true, hint.isForceMaster());
		Assert.assertNotNull(hint.getColumns());
		Assert.assertEquals(3, hint.getColumns().get("UserID").size());
	}

	@Test
	public void test3() {
		String line = "/*+zebra:skv=UserID(1,2,3)*/";

		SQLHint hint = SQLHint.parseHint(line);

		Assert.assertEquals(false, hint.isForceMaster());
		Assert.assertNotNull(hint.getColumns());
		Assert.assertEquals(3, hint.getColumns().get("UserID").size());
	}

	@Test
	public void test4() {
		String line = "/*+zebra:skv=UserID(1,2,3)&OrderID(1,2,3,4)*/";

		SQLHint hint = SQLHint.parseHint(line);

		Assert.assertEquals(false, hint.isForceMaster());
		Assert.assertNotNull(hint.getColumns());
		Assert.assertEquals(3, hint.getColumns().get("UserID").size());
		Assert.assertEquals(4, hint.getColumns().get("OrderID").size());
	}

	@Test
	public void test5() {
		String line = "/*+zebra:skv=UserID(1)&OrderID(1,2)|w*/";

		SQLHint hint = SQLHint.parseHint(line);

		Assert.assertEquals(true, hint.isForceMaster());
		Assert.assertNotNull(hint.getColumns());
		Assert.assertEquals(1, hint.getColumns().get("UserID").size());
		Assert.assertEquals(2, hint.getColumns().get("OrderID").size());
	}
	
	@Test
	public void test6() {
		String line = "/*+zebra:sk=UserId|w*/";

		SQLHint hint = SQLHint.parseHint(line);

		Assert.assertEquals(true, hint.isForceMaster());
		Assert.assertEquals("UserId", hint.getShardColumn());
	}
	
	@Test
	public void test7() {
		String line = "/*+zebra:sk=UserId|w|skv=UserID(1)&OrderID(1,2)*/";

		SQLHint hint = SQLHint.parseHint(line);

		Assert.assertEquals(true, hint.isForceMaster());
		Assert.assertEquals("UserId", hint.getShardColumn());
		Assert.assertNotNull(hint.getColumns());
		Assert.assertEquals(1, hint.getColumns().get("UserID").size());
		Assert.assertEquals(2, hint.getColumns().get("OrderID").size());
	}
}
