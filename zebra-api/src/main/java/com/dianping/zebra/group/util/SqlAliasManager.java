package com.dianping.zebra.group.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.zebra.util.StringUtils;

public class SqlAliasManager {

	private static final String SQL_NAME = "sql_statement_name";

	private static final String SQL_RETRY_SUFFIX = " (retry-by-zebra)";

	private static final ThreadLocal<String> sqlAlias = new ThreadLocal<String>();

	private static final int MAX_ALLOWED_SQL_CHAR = 1024;

	private static final int MAX_ALLOWED_TRUNCATED_SQL_NUM = 2000;

	private static final Set<String> cachedTruncatedSqls = Collections.synchronizedSet(new HashSet<String>(
	      MAX_ALLOWED_TRUNCATED_SQL_NUM));

	public static String getAvatarSqlAlias() {
		return ExecutionContextHolder.getContext().get(SQL_NAME);
	}

	private static String getCachedTruncatedSql(String sql) {
		if (sql == null) {
			return null;
		}

		if (sql.length() > MAX_ALLOWED_SQL_CHAR) {
			sql = sql.substring(0, MAX_ALLOWED_SQL_CHAR);
		}

		if (!cachedTruncatedSqls.contains(sql) && cachedTruncatedSqls.size() > MAX_ALLOWED_TRUNCATED_SQL_NUM) {
			return null;
		} else {
			cachedTruncatedSqls.add(sql);
			return sql;
		}
	}

	public static String getSqlAlias() {
		return sqlAlias.get();
	}

	public static void setRetrySqlAlias() {
		String alias = sqlAlias.get();

		if (alias != null) {
			sqlAlias.set(alias + SQL_RETRY_SUFFIX);
		}
	}

	public static void setSqlAlias(String sql) {
		String sqlName = ExecutionContextHolder.getContext().get(SQL_NAME);

		if (StringUtils.isBlank(sqlName)) {
			sqlAlias.set(getCachedTruncatedSql(sql));
		} else {
			sqlAlias.set(sqlName);
		}
	}

}
