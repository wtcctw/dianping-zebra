package com.dianping.zebra.shard.parser;

import com.dianping.zebra.shard.parser.qlParser.DPMySQLParser;
import com.dianping.zebra.shard.parser.qlParser.MySQLWalker;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MySelect;
import org.junit.Assert;
import org.junit.Test;

public class SelectTest {

	@Test
	public void testSelect() throws Exception {
		String sql = "SELECT SUM(A) FROM B JOIN C ON a=a WHERE a=1 GROUP BY A,B ORDER BY a;";

		MySQLWalker.beg_return ret = DPMySQLParser.parse(sql);
		MySelect select = (MySelect) ret.obj;

		Assert.assertEquals(2, select.getWhere().getGroupByColumns().size());
	}

}
