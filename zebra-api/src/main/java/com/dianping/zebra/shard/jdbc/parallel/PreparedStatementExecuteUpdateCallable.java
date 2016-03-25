package com.dianping.zebra.shard.jdbc.parallel;

import java.sql.PreparedStatement;
import java.util.concurrent.Callable;

import com.dianping.zebra.group.util.SqlAliasManager;

public class PreparedStatementExecuteUpdateCallable implements Callable<UpdateResult> {

	private PreparedStatement stmt;
	private String sql;

	public PreparedStatementExecuteUpdateCallable(PreparedStatement stmt, String sqlName,String sql) {
		SqlAliasManager.setSqlAlias2(sqlName);
		this.stmt = stmt;
		this.sql = sql;
	}

	@Override
	public UpdateResult call() throws Exception {
		long now = System.currentTimeMillis();
		int executeUpdate = stmt.executeUpdate();
		System.out.println("UpdateTime Time = " + (System.currentTimeMillis() - now));
		
		if (sql.trim().charAt(0) == 'i' || sql.trim().charAt(0) == 'I') {
			return new UpdateResult(executeUpdate, stmt.getGeneratedKeys());
		}
		
		return new UpdateResult(executeUpdate);
	}
}
