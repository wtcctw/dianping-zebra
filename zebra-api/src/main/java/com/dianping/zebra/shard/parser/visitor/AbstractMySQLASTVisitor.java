package com.dianping.zebra.shard.parser.visitor;

import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitorAdapter;
import com.dianping.zebra.shard.parser.MySQLParseResult;

public class AbstractMySQLASTVisitor extends MySqlASTVisitorAdapter {

	protected MySQLParseResult result;

	public AbstractMySQLASTVisitor(MySQLParseResult result) {
		this.result = result;
	}
	
	@Override
	public boolean visit(SQLExprTableSource x) {
		SQLName table = (SQLName) x.getExpr();
		result.getRouterContext().getTableSets().add(table.getSimpleName());

		return true;
	}

	public MySQLParseResult getResult() {
		return result;
	}
}
