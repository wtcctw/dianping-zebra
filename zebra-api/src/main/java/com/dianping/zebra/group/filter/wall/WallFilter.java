package com.dianping.zebra.group.filter.wall;

import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
import com.dianping.zebra.group.util.StringUtils;
import jodd.cache.LRUCache;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dozer on 9/24/14.
 */
public class WallFilter extends DefaultJdbcFilter {
    protected final static Pattern ID_PATTERN = Pattern.compile("(.*)(\\/\\*)([a-zA-Z0-9]{10})(\\*\\/$)");
    private static final int MAX_ID_LENGTH = 10;
    private static final LRUCache<String, String> sqlIdCache = new LRUCache<String, String>(1024, 60 * 60);

    protected String addIdToSql(String sql, JdbcMetaData metaData) {
        try {
            return String.format("%s/*%s*/", sql, generateId(sql));
        } catch (NoSuchAlgorithmException e) {
            return sql;
        }
    }

    protected void analyticsExecute(JdbcMetaData sql, long executeTime) {

    }

    @Override
    public <S, T> T execute(JdbcMetaData metaData, S source, FilterFunctionWithSQLException<S, T> action)
            throws SQLException {
        findLongSqlString(metaData);
        long executeStart = System.currentTimeMillis();
        T result = super.execute(metaData, source, action);
        long executeTime = System.currentTimeMillis() - executeStart;
        analyticsExecute(metaData, executeTime);
        return result;
    }

    protected void findLongSqlString(JdbcMetaData metaData) throws SQLException {
    }

    protected String generateId(String sql) throws NoSuchAlgorithmException {
        if (StringUtils.isBlank(sql)) {
            return null;
        }

        String sqlId = sqlIdCache.get(sql);

        if (sqlId != null) {
            return sqlId;
        }

        sqlId = StringUtils.sha1(sql).substring(MAX_ID_LENGTH);

        sqlIdCache.put(sql, sqlId);

        return sqlId;
    }

    protected String getIdFromSQL(String sql) {
        Matcher matcher = ID_PATTERN.matcher(sql);
        if (matcher.matches()) {
            return matcher.group(3);
        } else {
            return null;
        }
    }

    public int getOrder() {
        return MIN_ORDER;
    }

    @Override
    public <S> String sql(JdbcMetaData metaData, S source, FilterFunction<S, String> action) {
        String result = super.sql(metaData, source, action);
        result = addIdToSql(result, metaData);
        return result;
    }
}