package com.dianping.zebra.shard.parser;

import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.junit.Assert;
import org.junit.Test;



import com.dianping.zebra.shard.parser.qlParser.DPMySQLParser;
import com.dianping.zebra.shard.parser.qlParser.MySQLWalker;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MySelect;

public class SelectTest {

	@Test
	public void testSelect1() throws Exception {
		String sql = "SELECT SUM(A) FROM B JOIN C ON a=a WHERE a=1 GROUP BY A,B ORDER BY a;";

		MySQLWalker.beg_return ret = DPMySQLParser.parse(sql);
		MySelect select = (MySelect) ret.obj;

		Assert.assertEquals(2, select.getWhere().getGroupByColumns().size());
	}

	// Not support syntax like : "COUNT(1) AS Count" when alias is 'Count'
	@Test(expected=MismatchedSetException.class)
	public void testSelect2() throws Exception {
		String sql = "SELECT A.OrderID, COUNT(1) AS Count FROM TG_Receipt_New A         WHERE         A.UserID = ?         AND A.Status = 0 and A.EndDate > now()         GROUP BY A.OrderID         ORDER BY A.OrderID DESC";

		DPMySQLParser.parse(sql);
	}

	// Not support syntax : 有复杂的IF语句，并且有join语句
	@Test(expected=NoViableAltException.class)
	public void testSelect3() throws Exception {
		String sql = "SELECT w.DealGroupID   FROM TG_WishList w   JOIN TG_DealGroup d ON d.DealGroupID = w.DealGroupID   WHERE w.UserID = ? AND w.Status = 1 AND d.PublishStatus = 1   ORDER BY IF((d.MaxJoin = 0 OR d.CurrentJoin < d.MaxJoin ) AND NOW() BETWEEN d.BeginDate AND d.EndDate, 1, 0) DESC, w.WishListID DESC;";

		DPMySQLParser.parse(sql);
	}
}
