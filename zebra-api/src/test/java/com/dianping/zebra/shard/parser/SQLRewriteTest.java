package com.dianping.zebra.shard.parser;

import org.junit.Test;

import com.dianping.zebra.shard.exception.SQLParseException;

import junit.framework.Assert;

public class SQLRewriteTest {

	@Test
	public void test() throws SQLParseException {
		DefaultSQLRewrite rewriter = new DefaultSQLRewrite();
		SQLParsedResult result = SQLParser
				.parse("/*zebra:+w*/select a,b /*comment**/ from db where `c` = 1 limit 10,100 #this is comment");

		String newSql = rewriter.rewrite(result, "db1");

		Assert.assertEquals("/*zebra:+w*/", result.getRouterContext().getHintComment());
		Assert.assertEquals("SELECT a, b\nFROM db1\nWHERE `c` = 1\nLIMIT 0, 110", newSql);
	}

	@Test
	public void testNoComment() throws SQLParseException {
		DefaultSQLRewrite rewriter = new DefaultSQLRewrite();
		SQLParsedResult result = SQLParser
				.parse("select a,b /*comment**/ from db where `c` = 1 limit 10,100 #this is comment");

		String newSql = rewriter.rewrite(result, "db1");

		Assert.assertNull(result.getRouterContext().getHintComment());
		Assert.assertEquals("SELECT a, b\nFROM db1\nWHERE `c` = 1\nLIMIT 0, 110", newSql);
	}
	
	@Test
	public void testLimit() throws SQLParseException {
		DefaultSQLRewrite rewriter = new DefaultSQLRewrite();
		SQLParsedResult result = SQLParser
				.parse("select a,b /*comment**/ from db where `c` = 1 limit 10 #this is comment");

		String newSql = rewriter.rewrite(result, "db1");

		Assert.assertNull(result.getRouterContext().getHintComment());
		Assert.assertEquals("SELECT a, b\nFROM db1\nWHERE `c` = 1\nLIMIT 10", newSql);
	}
}
