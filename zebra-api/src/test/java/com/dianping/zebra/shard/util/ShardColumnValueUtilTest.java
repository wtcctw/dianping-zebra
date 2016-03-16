package com.dianping.zebra.shard.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.dianping.zebra.shard.exception.SQLParseException;
import com.dianping.zebra.shard.parser.SQLParsedResult;
import com.dianping.zebra.shard.parser.SQLParser;
import com.dianping.zebra.shard.router.rule.ShardEvalContext;
import com.dianping.zebra.shard.router.rule.ShardEvalContext.ColumnValue;

import junit.framework.Assert;

public class ShardColumnValueUtilTest {

	@Test
	public void testMultipalShardColumn1() throws SQLParseException {

		SQLParsedResult parseResult = SQLParser.parse("select a,b from db where `c` = 1 and `d` = 2");
		List<Object> params = null;
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("c");
		shardColumns.add("d");
		
		List<ColumnValue> values = ShardColumnValueUtil.eval(ctx, shardColumns);
		
		Assert.assertEquals(1, values.size());
		ColumnValue columnValue = values.get(0);
		Assert.assertEquals(1, columnValue.getValue().get("c"));
		Assert.assertEquals(2, columnValue.getValue().get("d"));
	}
	
	@Test
	public void testMultipalShardColumn2() throws SQLParseException {

		SQLParsedResult parseResult = SQLParser.parse("select a,b from db where `c` = 1 and `d` = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(2);
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("c");
		shardColumns.add("d");
		
		List<ColumnValue> values = ShardColumnValueUtil.eval(ctx, shardColumns);
		
		Assert.assertEquals(1, values.size());
		ColumnValue columnValue = values.get(0);
		Assert.assertEquals(1, columnValue.getValue().get("c"));
		Assert.assertEquals(2, columnValue.getValue().get("d"));
	}
	
	@Test
	public void testMultipalShardColumn3() throws SQLParseException {

		SQLParsedResult parseResult = SQLParser.parse("select a,b from db where `c` = 1 and `d` = ? and `e` = 1");
		List<Object> params = new ArrayList<Object>();
		params.add(2);
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("c");
		shardColumns.add("d");
		shardColumns.add("e");
		
		List<ColumnValue> values = ShardColumnValueUtil.eval(ctx, shardColumns);
		
		Assert.assertEquals(1, values.size());
		ColumnValue columnValue = values.get(0);
		Assert.assertEquals(1, columnValue.getValue().get("c"));
		Assert.assertEquals(2, columnValue.getValue().get("d"));
		Assert.assertEquals(1, columnValue.getValue().get("e"));
	}

}
