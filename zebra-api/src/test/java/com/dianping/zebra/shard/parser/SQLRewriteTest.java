package com.dianping.zebra.shard.parser;

import org.junit.Test;

import com.dianping.zebra.shard.exception.ZebraParseException;

import junit.framework.Assert;

public class SQLRewriteTest {

	@Test
	public void test() throws ZebraParseException {
		DefaultSQLRewrite rewriter = new DefaultSQLRewrite();
		MySQLParserResult result = MySQLParser
				.parse("/*zebra:+w*/select a,b /*comment**/ from db where `c` = 1 limit 10,100 #this is comment");

		String newSql = rewriter.rewrite(result, "db1");

		Assert.assertEquals("/*zebra:+w*/", result.getHintComment());
		Assert.assertEquals("SELECT a, b\nFROM db1\nWHERE `c` = 1\nLIMIT 0, 110", newSql);
	}

	@Test
	public void testNoComment() throws ZebraParseException {
		DefaultSQLRewrite rewriter = new DefaultSQLRewrite();
		MySQLParserResult result = MySQLParser
				.parse("select a,b /*comment**/ from db where `c` = 1 limit 10,100 #this is comment");

		String newSql = rewriter.rewrite(result, "db1");

		Assert.assertNull(result.getHintComment());
		Assert.assertEquals("SELECT a, b\nFROM db1\nWHERE `c` = 1\nLIMIT 0, 110", newSql);
	}
	
	@Test
	public void testLimit() throws ZebraParseException {
		DefaultSQLRewrite rewriter = new DefaultSQLRewrite();
		MySQLParserResult result = MySQLParser
				.parse("select a,b /*comment**/ from db where `c` = 1 limit 10 #this is comment");

		String newSql = rewriter.rewrite(result, "db1");

		Assert.assertNull(result.getHintComment());
		Assert.assertEquals("SELECT a, b\nFROM db1\nWHERE `c` = 1\nLIMIT 10", newSql);
	}
}
