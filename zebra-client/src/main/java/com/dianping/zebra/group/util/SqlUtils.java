package com.dianping.zebra.group.util;

import java.sql.SQLException;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.dianping.zebra.group.router.SqlType;

public class SqlUtils {
	private static final Pattern SELECT_FOR_UPDATE_PATTERN = Pattern.compile("^select\\s+.*\\s+for\\s+update.*$",
	      Pattern.CASE_INSENSITIVE);

	public static SqlType getSqlType(String sql) throws SQLException {
		SqlType sqlType = null;
		sql = sql.trim();

		if (StringUtils.startsWithIgnoreCase(sql, "select")) {
			if (SELECT_FOR_UPDATE_PATTERN.matcher(sql).matches()) {
				sqlType = SqlType.SELECT_FOR_UPDATE;
			} else {
				sqlType = SqlType.SELECT;
			}
		} else if (StringUtils.startsWithIgnoreCase(sql, "insert")) {
			sqlType = SqlType.INSERT;
		} else if (StringUtils.startsWithIgnoreCase(sql, "update")) {
			sqlType = SqlType.UPDATE;
		} else if (StringUtils.startsWithIgnoreCase(sql, "delete")) {
			sqlType = SqlType.DELETE;
		} else if (StringUtils.startsWithIgnoreCase(sql, "replace")) {
			sqlType = SqlType.REPLACE;
		} else if (StringUtils.startsWithIgnoreCase(sql, "truncate")) {
			sqlType = SqlType.TRUNCATE;
		} else if (StringUtils.startsWithIgnoreCase(sql, "create")) {
			sqlType = SqlType.CREATE;
		} else if (StringUtils.startsWithIgnoreCase(sql, "drop")) {
			sqlType = SqlType.DROP;
		} else if (StringUtils.startsWithIgnoreCase(sql, "load")) {
			sqlType = SqlType.LOAD;
		} else if (StringUtils.startsWithIgnoreCase(sql, "merge")) {
			sqlType = SqlType.MERGE;
		} else if (StringUtils.startsWithIgnoreCase(sql, "show")) {
			sqlType = SqlType.SHOW;
		} else {
			throw new SQLException("only select, insert, update, delete,replace,truncate sql is supported");
		}
		return sqlType;
	}

}
