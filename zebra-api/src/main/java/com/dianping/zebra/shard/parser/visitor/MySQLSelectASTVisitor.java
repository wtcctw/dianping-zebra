package com.dianping.zebra.shard.parser.visitor;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectGroupByClause;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock.Limit;
import com.dianping.zebra.shard.parser.MySQLParseResult;

public class MySQLSelectASTVisitor extends AbstractMySQLASTVisitor {

	public MySQLSelectASTVisitor(MySQLParseResult result) {
		super(result);
	}

	@Override
	public boolean visit(MySqlSelectQueryBlock x) {
		result.getMergeContext().setSelectLists(x.getSelectList());
		if (x.getDistionOption() == 2) {
			result.getMergeContext().setDistinct(true);
		}

		return true;
	}

	@Override
	public boolean visit(Limit x) {
		if (x.getOffset() instanceof SQLIntegerExpr) {
			SQLIntegerExpr offsetExpr = (SQLIntegerExpr) x.getOffset();
			if (offsetExpr != null) {
				int offset = offsetExpr.getNumber().intValue();
				result.getMergeContext().setOffset(offset);
			}
		}

		if (x.getRowCount() instanceof SQLIntegerExpr) {
			SQLIntegerExpr rowCountExpr = (SQLIntegerExpr) x.getRowCount();
			if (rowCountExpr != null) {
				int limit = rowCountExpr.getNumber().intValue();
				result.getMergeContext().setLimit(limit);
			}
		}

		result.getMergeContext().setLimitExpr(x);
		return true;
	}
	
	@Override
	public boolean visit(SQLSelectGroupByClause x) {
		List<String> groupByColumns = new ArrayList<String>();
		List<SQLExpr> items = x.getItems();

		for (SQLExpr expr : items) {
			groupByColumns.add(((SQLName) expr).getSimpleName());
		}

		result.getMergeContext().setGroupByColumns(groupByColumns);
		return true;
	}

	@Override
	public boolean visit(SQLOrderBy x) {
		result.getMergeContext().setOrderBy(x);
		return true;
	}
}
