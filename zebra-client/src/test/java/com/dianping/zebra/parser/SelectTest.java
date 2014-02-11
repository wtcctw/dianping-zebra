package com.dianping.zebra.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dianping.zebra.parser.qlParser.DPMySQLParser;
import com.dianping.zebra.parser.qlParser.MySQLWalker;
import com.dianping.zebra.parser.sqlParser.mySql.MySelect;

public class SelectTest {

	@Test
	public void testSelect() throws Exception {
		String sql = "SELECT SUM(A) FROM B JOIN C ON a=a WHERE a=1 GROUP BY A,B ORDER BY a;";

		MySQLWalker.beg_return ret = DPMySQLParser.parse(sql);
		MySelect select = (MySelect) ret.obj;

		assertEquals(2, select.getWhere().getGroupByColumns().size());
	}

}
