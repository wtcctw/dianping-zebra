package com.dianping.zebra.group.util;

import com.dianping.avatar.tracker.ExecutionContext;
import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.avatar.tracker.InitialInstanceThreadLocal;

public class DaoContextHolder {
	private static final String SQL_NAME = "sql_statement_name";

	private static final ThreadLocal<ExecutionContext> CONTEXT = new InitialInstanceThreadLocal<ExecutionContext>(
	      ExecutionContext.class);

	/**
	 * retrieve current app's tracker context
	 * 
	 * @return
	 */
	public static ExecutionContext getContext() {
		return CONTEXT.get();
	}

	public static void setSqlName(String statementName) {
		ExecutionContextHolder.getContext().add(SQL_NAME, statementName);
	}

	public static void clearSqlName() {
		ExecutionContextHolder.getContext().clear(SQL_NAME);
	}
}
