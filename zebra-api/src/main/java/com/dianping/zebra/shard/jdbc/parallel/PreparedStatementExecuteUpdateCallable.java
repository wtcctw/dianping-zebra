package com.dianping.zebra.shard.jdbc.parallel;

import java.sql.PreparedStatement;
import java.util.concurrent.Callable;

import com.dianping.zebra.group.util.DaoContextHolder;

public class PreparedStatementExecuteUpdateCallable implements Callable<UpdateResult> {

	private PreparedStatement stmt;
	private String sql;
	private String sqlName;

	public PreparedStatementExecuteUpdateCallable(PreparedStatement stmt, String sqlName, String sql) {
		this.stmt = stmt;
		this.sqlName = sqlName;
		this.sql = sql;
	}

	@Override
	public UpdateResult call() throws Exception {
		DaoContextHolder.setSqlName(sqlName);
		int executeUpdate = stmt.executeUpdate();

		if (sql.trim().charAt(0) == 'i' || sql.trim().charAt(0) == 'I') {
			return new UpdateResult(executeUpdate, stmt.getGeneratedKeys());
		}

		return new UpdateResult(executeUpdate);
	}
}
