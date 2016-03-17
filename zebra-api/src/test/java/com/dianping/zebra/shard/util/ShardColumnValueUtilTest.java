package com.dianping.zebra.shard.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.dianping.zebra.shard.exception.SQLParseException;
import com.dianping.zebra.shard.exception.ShardRouterException;
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

	@Test(expected = ShardRouterException.class)
	public void testMultipalInsertion() throws SQLParseException {
		SQLParsedResult parseResult = SQLParser.parse(
				"INSERT INTO `User` (`Name`,`Tel`,`Alias`,`Email`)VALUES('zhuhao','123','hao.zhu','z@d'),('zhuhao1','1233','hao.zhu1','z@d')");
		List<Object> params = null;
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("Name");

		ShardColumnValueUtil.eval(ctx, shardColumns);
	}

	@Test
	public void testSingleInsertion() throws SQLParseException {
		SQLParsedResult parseResult = SQLParser
				.parse("INSERT INTO `User` (`Name`,`Tel`,`Alias`,`Email`)VALUES('zhuhao','123','hao.zhu','z@d')");
		List<Object> params = null;
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("Name");

		List<ColumnValue> values = ShardColumnValueUtil.eval(ctx, shardColumns);

		Assert.assertEquals(1, values.size());
		ColumnValue columnValue = values.get(0);
		Assert.assertEquals("zhuhao", columnValue.getValue().get("Name"));
	}

	@Test
	public void testLargerThan() throws SQLParseException {
		SQLParsedResult parseResult = SQLParser.parse("select a,b from db where `c` >= 1");
		List<Object> params = null;
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("c");

		List<ColumnValue> values = ShardColumnValueUtil.eval(ctx, shardColumns);

		Assert.assertEquals(0, values.size());
	}

	@Test
	public void testLessThan() throws SQLParseException {
		SQLParsedResult parseResult = SQLParser.parse("select a,b from db where `c` <= 1");
		List<Object> params = null;
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("c");

		List<ColumnValue> values = ShardColumnValueUtil.eval(ctx, shardColumns);

		Assert.assertEquals(0, values.size());
	}

	@Test
	public void testNotEqual() throws SQLParseException {
		SQLParsedResult parseResult = SQLParser.parse("select a,b from db where `c` != 1");
		List<Object> params = null;
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("c");

		List<ColumnValue> values = ShardColumnValueUtil.eval(ctx, shardColumns);

		Assert.assertEquals(0, values.size());
	}

	@Test
	public void testIn() throws SQLParseException {
		SQLParsedResult parseResult = SQLParser.parse("select a,b from db where `c` in (1,2,3,4)");
		List<Object> params = null;
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("c");

		List<ColumnValue> values = ShardColumnValueUtil.eval(ctx, shardColumns);

		Assert.assertEquals(4, values.size());
	}

	@Test
	public void testPreparedIn() throws SQLParseException {
		SQLParsedResult parseResult = SQLParser.parse("select a,b from db where `c` in (?,?,?,?)");
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		params.add(2);
		params.add(3);
		params.add(4);
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("c");

		List<ColumnValue> values = ShardColumnValueUtil.eval(ctx, shardColumns);

		Assert.assertEquals(4, values.size());
	}

	@Test
	public void testPreparedIn2() throws SQLParseException {
		SQLParsedResult parseResult = SQLParser.parse(
				"SELECT A.ReceiptID, A.UserID, A.DealGroupID, A.DealID from RS_Receipt A WHERE A.UserID IN (28152647,22050) AND A.ReceiptID IN (234460949,234400906,234400907,234400908) ORDER BY A.ReceiptID");
		List<Object> params = null;
		ShardEvalContext ctx = new ShardEvalContext(parseResult, params);
		Set<String> shardColumns = new HashSet<String>();
		shardColumns.add("UserID");

		List<ColumnValue> values = ShardColumnValueUtil.eval(ctx, shardColumns);

		Assert.assertEquals(2, values.size());
	}
}
