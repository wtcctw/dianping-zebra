package com.dianping.zebra.group.util;

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.zebra.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SqlAliasManager {

    private static final String SQL_NAME = "sql_statement_name";

    private static final String SQL_RETRY_SUFFIX = " (retry-by-zebra)";

    private static final String MORE_SQL_NAME = "other";

    private static final ThreadLocal<String> sqlAlias = new ThreadLocal<String>();

    private static final int MAX_ALLOWED_SQL_CHAR = 1024;

    private static final int MAX_ALLOWED_TRUNCATED_SQL_NUM = 1000;

    private static final Map<Integer, Object> cachedTruncatedSqls = new ConcurrentHashMap<Integer, Object>(
            MAX_ALLOWED_TRUNCATED_SQL_NUM * 2);

    private static volatile boolean cachedTruncatedSqlsIsFull = false;

    private static final Object PRESENT = new Object();

    public static String getAvatarSqlAlias() {
   	 String sqlName1 = ExecutionContextHolder.getContext().get(SQL_NAME);
   	 String sqlName2 = DaoContextHolder.getContext().get(SQL_NAME);
   	 
   	 if(StringUtils.isNotBlank(sqlName1)){
   		 return sqlName1;
   	 }else{
   		 return sqlName2;
   	 }
    }

    private static String getCachedTruncatedSql(String sql) {
        if (StringUtils.isEmpty(sql)) {
            return null;
        }

        if (cachedTruncatedSqlsIsFull) {
            return MORE_SQL_NAME;
        } else {
            cachedTruncatedSqlsIsFull = cachedTruncatedSqls.size() >= MAX_ALLOWED_TRUNCATED_SQL_NUM;
        }

        if (sql.length() > MAX_ALLOWED_SQL_CHAR) {
            sql = sql.substring(0, MAX_ALLOWED_SQL_CHAR);
        }
        int sqlHash = sql.hashCode();

        if (!cachedTruncatedSqls.containsKey(sqlHash)) {
            if (cachedTruncatedSqlsIsFull) {
                return MORE_SQL_NAME;
            }
            cachedTruncatedSqls.put(sqlHash, PRESENT);
        }
        return sql;
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
        String sqlName1 = ExecutionContextHolder.getContext().get(SQL_NAME);
        String sqlName2 = DaoContextHolder.getContext().get(SQL_NAME);

        if(StringUtils.isNotBlank(sqlName1)){
      	  sqlAlias.set(sqlName1);
        }else if(StringUtils.isNotBlank(sqlName2)){
      	  sqlAlias.set(sqlName2);
        }else{
            sqlAlias.set(getCachedTruncatedSql(sql));
        }
    }
    
    public static void setSqlAlias2(String alias){
    	sqlAlias.set(alias);
    }
}