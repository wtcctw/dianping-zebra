package com.dianping.zebra.shard.parser;

import org.junit.Test;

import com.dianping.zebra.shard.exception.ShardParseException;

import junit.framework.Assert;

public class SQLRewriteTest {

	@Test
	public void test() throws ShardParseException {
		DefaultSQLRewrite rewriter = new DefaultSQLRewrite();
		SQLParsedResult result = SQLParser
				.parse("/*+zebra:w*/select a,b /*comment**/ from db where `c` = 1 limit 10,100 #this is comment");

		String newSql = rewriter.rewrite(result, "db", "db1");

		Assert.assertEquals("/*+zebra:w*/", result.getRouterContext().getSqlhint().getForceMasterComment());
		Assert.assertEquals("SELECT a, b\nFROM db1\nWHERE `c` = 1\nLIMIT 0, 110", newSql);
	}

	@Test
	public void testNoComment() throws ShardParseException {
		DefaultSQLRewrite rewriter = new DefaultSQLRewrite();
		SQLParsedResult result = SQLParser
				.parse("select a,b /*comment**/ from db where `c` = 1 limit 10,100 #this is comment");

		String newSql = rewriter.rewrite(result, "db", "db1");

		Assert.assertNotNull(result.getRouterContext().getSqlhint());
		Assert.assertEquals("SELECT a, b\nFROM db1\nWHERE `c` = 1\nLIMIT 0, 110", newSql);
	}

	@Test
	public void testLimit() throws ShardParseException {
		DefaultSQLRewrite rewriter = new DefaultSQLRewrite();
		SQLParsedResult result = SQLParser
				.parse("select a,b /*comment**/ from db where `c` = 1 limit 10 #this is comment");

		String newSql = rewriter.rewrite(result, "db", "db1");

		Assert.assertNotNull(result.getRouterContext().getSqlhint());
		Assert.assertEquals("SELECT a, b\nFROM db1\nWHERE `c` = 1\nLIMIT 10", newSql);
	}
	
	@Test
	public void testLimit2() throws ShardParseException {
		DefaultSQLRewrite rewriter = new DefaultSQLRewrite();
		SQLParsedResult result = SQLParser
				.parse("select a,b /*comment**/ from db where `c` = 1 limit 10 offset 10 #this is comment");

		String newSql = rewriter.rewrite(result, "db", "db1");

		Assert.assertNotNull(result.getRouterContext().getSqlhint());
		Assert.assertEquals(result.getMergeContext().getLimit(),10);
		Assert.assertEquals(result.getMergeContext().getOffset(),10);
		Assert.assertEquals("SELECT a, b\nFROM db1\nWHERE `c` = 1\nLIMIT 0, 20", newSql);
	}
}
