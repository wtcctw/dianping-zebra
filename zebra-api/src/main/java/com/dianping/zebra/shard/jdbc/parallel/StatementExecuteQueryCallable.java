package com.dianping.zebra.shard.jdbc.parallel;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

import com.dianping.zebra.group.util.SqlAliasManager;

public class StatementExecuteQueryCallable implements Callable<ResultSet> {

	private Statement stmt;
	
	private String sql;

	public StatementExecuteQueryCallable(Statement stmt, String sqlName,String sql) {
		SqlAliasManager.setSqlAlias2(sqlName);
		this.stmt = stmt;
		this.sql = sql;
	}

	@Override
	public ResultSet call() throws Exception {
		return stmt.executeQuery(sql);
	}
}
