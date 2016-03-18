package com.dianping.zebra.shard.jdbc.parallel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Callable;

import com.dianping.zebra.group.util.SqlAliasManager;

public class PreparedStatementExecuteQueryCallable implements Callable<ResultSet> {

	private PreparedStatement stmt;

	public PreparedStatementExecuteQueryCallable(PreparedStatement stmt, String sqlName) {
		SqlAliasManager.setSqlAlias2(sqlName);
		this.stmt = stmt;
	}

	@Override
	public ResultSet call() throws Exception {
		return stmt.executeQuery();
	}
}
