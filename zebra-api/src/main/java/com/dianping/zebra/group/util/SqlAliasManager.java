package com.dianping.zebra.group.util;

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.zebra.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SqlAliasManager {

    private static final String SQL_NAME = "sql_statement_name";

    private static final String SQL_RETRY_SUFFIX = " (retry-by-zebra)";

    private static final ThreadLocal<String> sqlAlias = new ThreadLocal<String>();

    private static final int MAX_ALLOWED_SQL_CHAR = 1024;

    private static final int MAX_ALLOWED_TRUNCATED_SQL_NUM = 2000;

    private static final Map<String, Object> cachedTruncatedSqls = new ConcurrentHashMap<String, Object>(
            MAX_ALLOWED_TRUNCATED_SQL_NUM);

    private static final Object PRESENT = new Object();

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

        if (!cachedTruncatedSqls.containsKey(sql) && cachedTruncatedSqls.size() > MAX_ALLOWED_TRUNCATED_SQL_NUM) {
            return null;
        } else {
            cachedTruncatedSqls.put(sql, PRESENT);
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
