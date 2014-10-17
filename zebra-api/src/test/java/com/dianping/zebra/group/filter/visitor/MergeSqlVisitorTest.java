package com.dianping.zebra.group.filter.visitor;

import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;
import com.foundationdb.sql.unparser.NodeToString;
import org.junit.Test;

/**
 * Created by Dozer on 10/17/14.
 */
public class MergeSqlVisitorTest {
	@Test
	public void test_merge_sql() throws StandardException {
		NodeToString nodeToString = new NodeToString();

		MergeSqlVisitor visitor = new MergeSqlVisitor();
		SQLParser parser = new SQLParser();
		StatementNode result1 = parser.parseStatement("select * from test where id != ? and id not in (?,?,1) or id = 3 and id > 3*2 and id not in (select id from test2 where id = 3)");
//		StatementNode result1 = parser.parseStatement("insert into test (name) values (select id from test2 where id = now())");

		System.out.println(nodeToString.toString(result1));
		visitor.visit(result1);
		System.out.println(nodeToString.toString(result1));
	}
}
