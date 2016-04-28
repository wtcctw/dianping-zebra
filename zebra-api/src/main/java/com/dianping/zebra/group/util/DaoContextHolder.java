package com.dianping.zebra.group.util;

import com.dianping.avatar.tracker.ExecutionContextHolder;

public class DaoContextHolder {
	private static final String SQL_NAME = "sql_statement_name";

	public static String getSqlName() {
		return ExecutionContextHolder.getContext().get(SQL_NAME);
	}

	public static void setSqlName(String statementName) {
		ExecutionContextHolder.getContext().add(SQL_NAME, statementName);
	}

	public static void clearSqlName() {
		ExecutionContextHolder.getContext().clear(SQL_NAME);
	}
}
