package com.dianping.zebra.group.util;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class SqlUtils {

	/**
	 * 用于判断是否是一个select ... for update的sql
	 */
	private static final Pattern SELECT_FOR_UPDATE_PATTERN = Pattern.compile("^select\\s+.*\\s+for\\s+update.*$",
	      Pattern.CASE_INSENSITIVE);

	public static SqlType getSqlType(String sql) throws SQLException {
		SqlType sqlType = null;
		String noCommentsSql = sql;
		if (sql.contains("/*")) {
			noCommentsSql = StringUtils.stripComments(sql, "'\"", "'\"", true, false, true, true).trim();
		}

		if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "select")) {
			if (noCommentsSql.toLowerCase().contains(" for ")
			      && SELECT_FOR_UPDATE_PATTERN.matcher(noCommentsSql).matches()) {
				sqlType = SqlType.SELECT_FOR_UPDATE;
			} else {
				sqlType = SqlType.SELECT;
			}
		} else if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "insert")) {
			sqlType = SqlType.INSERT;
		} else if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "update")) {
			sqlType = SqlType.UPDATE;
		} else if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "delete")) {
			sqlType = SqlType.DELETE;
		} else if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "show")) {
			sqlType = SqlType.SHOW;
		} else if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "replace")) {
			sqlType = SqlType.REPLACE;
		} else if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "truncate")) {
			sqlType = SqlType.TRUNCATE;
		} else if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "create")) {
			sqlType = SqlType.CREATE;
		} else if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "drop")) {
			sqlType = SqlType.DROP;
		} else if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "load")) {
			sqlType = SqlType.LOAD;
		} else if (StringUtils.startsWithIgnoreCaseAndWs(noCommentsSql, "merge")) {
			sqlType = SqlType.MERGE;
		} else if (noCommentsSql.toLowerCase().contains("call")) {
			sqlType = SqlType.EXECUTE;
		} else {
			throw new SQLException(
			      "only select, insert, update, delete,replace,truncate,create,drop,load,merge,call sql is supported");
		}
		return sqlType;
	}

	public static String buildSqlType(String sql) {
		try {
			char c = sql.trim().charAt(0);
			if (c == 's' || c == 'S') {
				return "Select";
			} else if (c == 'u' || c == 'U') {
				return "Update";
			} else if (c == 'i' || c == 'I') {
				return "Insert";
			} else if (c == 'd' || c == 'D') {
				return "Delete";
			} else if (c == 'c' || c == 'C') {
				return "Call";
			} else if (c == 'b' || c == 'B') {
				return "Batch";
			}
		} catch (Exception e) {
		}
		return "Execute";
	}

}
