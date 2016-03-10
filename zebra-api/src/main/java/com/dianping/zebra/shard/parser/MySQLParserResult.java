package com.dianping.zebra.shard.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectGroupByClause;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock.Limit;
import com.dianping.zebra.shard.exception.ZebraParseException;

public class MySQLParserResult {

	private SQLStatement stmt;

	private boolean select = false;

	private boolean insert = false;

	private boolean update = false;

	private boolean delete = false;

	private Set<String> tableSets = new HashSet<String>();

	private String hintComment;

	// select statement related fields
	private int offset = Integer.MIN_VALUE;

	private int limit = Integer.MAX_VALUE;

	private Limit limitExpr;

	private List<String> groupByColumns;

	private List<SQLSelectItem> selectLists;

	private SQLOrderBy orderBy;

	private boolean hasDistinct;

	private void findExprTableSource(SQLTableSource tableSource, Set<SQLExprTableSource> tableSources) {
		if (tableSource instanceof SQLJoinTableSource) {
			SQLJoinTableSource joinTableSource = (SQLJoinTableSource) tableSource;
			findExprTableSource(joinTableSource.getLeft(), tableSources);
			findExprTableSource(joinTableSource.getRight(), tableSources);
		} else if (tableSource instanceof SQLExprTableSource) {
			tableSources.add((SQLExprTableSource) tableSource);
		}
	}

	public MySQLParserResult(SQLStatement parseStatement) throws ZebraParseException {
		this.stmt = parseStatement;

		if (stmt instanceof SQLSelectStatement) {
			SQLSelectStatement selectStatement = (SQLSelectStatement) stmt;
			// TODO dialect
			MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) selectStatement.getSelect().getQuery();
			Set<SQLExprTableSource> tableSources = new HashSet<SQLExprTableSource>();
			findExprTableSource(query.getFrom(), tableSources);

			for (SQLExprTableSource exprTableSource : tableSources) {
				addTable((SQLName) exprTableSource.getExpr());
			}

			this.limitExpr = query.getLimit();
			if (limitExpr != null) {
				if (limitExpr.getOffset() instanceof SQLIntegerExpr) {
					SQLIntegerExpr offsetExpr = (SQLIntegerExpr) limitExpr.getOffset();
					if (offsetExpr != null) {
						this.offset = offsetExpr.getNumber().intValue();
					}
				}

				if (limitExpr.getRowCount() instanceof SQLIntegerExpr) {
					SQLIntegerExpr rowCountExpr = (SQLIntegerExpr) limitExpr.getRowCount();
					if (rowCountExpr != null) {
						this.limit = rowCountExpr.getNumber().intValue();
					}
				}
			}

			SQLSelectGroupByClause groupBy = query.getGroupBy();
			if (groupBy != null) {
				this.groupByColumns = new ArrayList<String>();
				List<SQLExpr> items = groupBy.getItems();

				for (SQLExpr expr : items) {
					this.groupByColumns.add(((SQLName) expr).getSimpleName());
				}
			}

			this.selectLists = query.getSelectList();

			// for(SQLSelectItem selectItem : this.selectLists){
			// if(selectItem.getExpr() instanceof SQLAllColumnExpr){
			// throw new ZebraParseException("SELECT * is not allowed!");
			// }
			// }

			this.orderBy = query.getOrderBy();
			if (query.getDistionOption() == 2) {
				this.hasDistinct = true;
			}
			select = true;
		} else if (stmt instanceof SQLInsertStatement) {
			SQLInsertStatement insertStatement = (SQLInsertStatement) stmt;
			addTable(insertStatement.getTableName());
			insert = true;
		} else if (stmt instanceof SQLUpdateStatement) {
			SQLUpdateStatement updateStatement = (SQLUpdateStatement) stmt;
			addTable(updateStatement.getTableName());
			update = true;
		} else if (stmt instanceof SQLDeleteStatement) {
			SQLDeleteStatement deleteStatement = (SQLDeleteStatement) stmt;
			addTable(deleteStatement.getTableName());
			delete = true;
		}

	}

	private void addTable(SQLName sqlName) {
		if (sqlName != null) {
			tableSets.add(sqlName.getSimpleName());
		}
	}

	public Set<String> getRelatedTables() {
		return tableSets;
	}

	public boolean isSelect() {
		return select;
	}

	public boolean isInsert() {
		return insert;
	}

	public boolean isUpdate() {
		return update;
	}

	public boolean isDelete() {
		return delete;
	}

	public int getLimit() {
		return limit;
	}

	public int getOffset() {
		return offset;
	}

	public List<String> getGroupByColumns() {
		return groupByColumns;
	}

	public SQLStatement getStmt() {
		return stmt;
	}

	public String getHintComment() {
		return hintComment;
	}

	public void setHintComment(String hintComment) {
		this.hintComment = hintComment;
	}

	public List<SQLSelectItem> getSelectLists() {
		return selectLists;
	}

	public SQLOrderBy getOrderBy() {
		return orderBy;
	}

	public boolean distinct() {
		return hasDistinct;
	}

	public Limit getLimitExpr() {
		return limitExpr;
	}
}
