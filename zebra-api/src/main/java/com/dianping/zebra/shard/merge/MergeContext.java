package com.dianping.zebra.shard.merge;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock.Limit;

public class MergeContext {
	private int offset = Integer.MIN_VALUE;

	private int limit = Integer.MAX_VALUE;

	private Limit limitExpr;

	private List<String> groupByColumns = new ArrayList<String>();

	private List<SQLSelectItem> selectLists = new ArrayList<SQLSelectItem>();

	private SQLOrderBy orderBy;

	private boolean distinct;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Limit getLimitExpr() {
		return limitExpr;
	}

	public void setLimitExpr(Limit limitExpr) {
		this.limitExpr = limitExpr;
	}

	public List<String> getGroupByColumns() {
		return groupByColumns;
	}

	public void setGroupByColumns(List<String> groupByColumns) {
		this.groupByColumns = groupByColumns;
	}

	public List<SQLSelectItem> getSelectLists() {
		return selectLists;
	}

	public void setSelectLists(List<SQLSelectItem> selectLists) {
		this.selectLists = selectLists;
	}

	public SQLOrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(SQLOrderBy orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}
}
