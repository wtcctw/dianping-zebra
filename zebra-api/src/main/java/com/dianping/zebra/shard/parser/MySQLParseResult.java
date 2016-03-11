package com.dianping.zebra.shard.parser;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.dianping.zebra.shard.exception.ZebraParseException;
import com.dianping.zebra.shard.merge.MergeContext;
import com.dianping.zebra.shard.router.RouterContext;
import com.dianping.zebra.util.SqlType;

public class MySQLParseResult {

	private SQLStatement stmt;

	private SqlType type;

	private RouterContext routerContext;

	private MergeContext mergeContext;

	public MySQLParseResult(SqlType type, SQLStatement stmt) throws ZebraParseException {
		this.stmt = stmt;
		this.type = type;
		this.routerContext = new RouterContext();
		this.mergeContext = new MergeContext();
	}

	public SqlType getType() {
		return type;
	}

	public SQLStatement getStmt() {
		return stmt;
	}

	public RouterContext getRouterContext() {
		return routerContext;
	}

	public MergeContext getMergeContext() {
		return mergeContext;
	}
}
