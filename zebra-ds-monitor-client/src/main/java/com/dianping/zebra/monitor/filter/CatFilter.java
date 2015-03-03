package com.dianping.zebra.monitor.filter;

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.status.StatusExtensionRegister;
import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.datasources.SingleDataSource;
import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcFilter;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.jdbc.GroupStatement;
import com.dianping.zebra.monitor.monitor.GroupDataSourceMonitor;
import com.dianping.zebra.util.SqlUtils;
import com.dianping.zebra.util.StringUtils;
import com.site.helper.Stringizers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Dozer on 9/5/14.
 */
public class CatFilter extends DefaultJdbcFilter {
    private static final String CAT_TYPE = "DAL";

    private static final String SQL_NAME = "sql_statement_name";

    private static final int MAX_ALLOWED_SQL_CHAR = 1024;

    private static final int MAX_ALLOWED_TRUNCATED_SQL_NUM = 2000;

    private static final Map<Integer, String> SQL_LENGTH_RANGE = new LinkedHashMap<Integer, String>();

    private static final int TOO_LARGE_SQL_LENGTH_INDEX = 3; // > 100k

    static {
        SQL_LENGTH_RANGE.put(1024, "<= 1K");
        SQL_LENGTH_RANGE.put(10240, "<= 10K");
        SQL_LENGTH_RANGE.put(102400, "<= 100K");
        SQL_LENGTH_RANGE.put(1024 * 1024, "<= 1M");
        SQL_LENGTH_RANGE.put(10240 * 1024, "<= 10M");
        SQL_LENGTH_RANGE.put(102400 * 1024, "<= 100M");
        SQL_LENGTH_RANGE.put(Integer.MAX_VALUE, "> 100M");
    }

    private static Set<String> cachedTruncatedSqls = Collections.synchronizedSet(new HashSet<String>(
            MAX_ALLOWED_TRUNCATED_SQL_NUM));

    @Override
    public void closeSingleDataSource(SingleDataSource source, JdbcFilter chain) throws SQLException {
        chain.closeSingleDataSource(source, chain);
        Cat.logEvent("DataSource.Destoryed", source.getConfig().getId());
    }

    @Override
    public Connection getRealConnection(GroupStatement source, String sql, boolean forceWriter, JdbcFilter chain) throws SQLException {
        try {
            return chain.getRealConnection(source, sql, forceWriter, chain);
        } catch (SQLException exp) {
            String sqlName = ExecutionContextHolder.getContext().get(SQL_NAME);
            Transaction t;
            if (StringUtils.isBlank(sqlName)) {
                t = Cat.newTransaction("SQL", getCachedTruncatedSql(sql));
                t.addData(sql);
            } else {
                t = Cat.newTransaction("SQL", sqlName);
                t.addData(sql);
            }

            Cat.logError(exp);
            t.setStatus(exp);
            t.complete();

            throw exp;
        }
    }

    @Override
    public <T> T execute(GroupStatement source, Connection conn, String sql, List<String> batchedSql, boolean isBatched,
                         boolean autoCommit, Object sqlParams, JdbcFilter chain) throws SQLException {
        String sqlName = ExecutionContextHolder.getContext().get(SQL_NAME);
        Transaction t;
        if (StringUtils.isBlank(sqlName)) {
            if (isBatched) {
                t = Cat.newTransaction("SQL", "batched");
                t.addData(Stringizers.forJson().compact().from(batchedSql));
            } else {
                t = Cat.newTransaction("SQL", getCachedTruncatedSql(sql));
                t.addData(sql);
            }
        } else {
            t = Cat.newTransaction("SQL", sqlName);
            t.addData(sql);
        }

        try {
            T result = chain.execute(source, conn, sql, batchedSql, isBatched, autoCommit, sqlParams, chain);
            t.setStatus(Transaction.SUCCESS);

            return result;
        } catch (SQLException exp) {
            Cat.logError(exp);
            t.setStatus(exp);

            throw exp;
        } finally {
            try {
                logSqlDatabaseEvent(conn);
                logSqlMethodEvent(sql, batchedSql, isBatched, sqlParams);
            } catch (Throwable exp) {
                Cat.logError(exp);
            }

            t.complete();
        }
    }

    @Override
    public FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(
            FailOverDataSource.MasterDataSourceMonitor source, JdbcFilter chain) {
        FailOverDataSource.FindMasterDataSourceResult result = chain.findMasterFailOverDataSource(source, chain);

        if (result != null && result.isChangedMaster()) {
            Cat.logEvent("DAL.Master", "Found-" + result.getDsId());
        }

        return result;
    }

    private String getCachedTruncatedSql(String sql) {
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

    @Override
    public void initGroupDataSource(GroupDataSource source, JdbcFilter chain) {
        Transaction transaction = Cat.newTransaction(CAT_TYPE, "DataSource.Init-" + source.getJdbcRef());
        try {
            chain.initGroupDataSource(source, chain);
            StatusExtensionRegister.getInstance().register(new GroupDataSourceMonitor(source));
            transaction.setStatus(Message.SUCCESS);
        } catch (RuntimeException e) {
            Cat.logError(e);
            transaction.setStatus(e);
            throw e;
        } finally {
            transaction.complete();
        }
    }

    @Override
    public DataSource initSingleDataSource(SingleDataSource source, JdbcFilter chain) {
        DataSource result = chain.initSingleDataSource(source, chain);
        Cat.logEvent("DataSource.Created", source.getConfig().getId());
        return result;
    }

    private void logSqlDatabaseEvent(Connection conn) throws SQLException {
        SingleConnection singleConnection = conn instanceof SingleConnection ? (SingleConnection) conn : null;
        if (singleConnection != null && conn.getMetaData() != null) {
            Cat.logEvent("SQL.Database", conn.getMetaData().getURL(), Event.SUCCESS, singleConnection.getDataSourceId());
        }
    }

    private void logSqlLengthEvent(String sql) {
        int length = (sql == null) ? 0 : sql.length();

        int counter = 0;
        for (Map.Entry<Integer, String> item : SQL_LENGTH_RANGE.entrySet()) {
            if (length <= item.getKey()) {
                if (counter < TOO_LARGE_SQL_LENGTH_INDEX) {
                    Cat.logEvent("SQL.Length", item.getValue(), Event.SUCCESS, "");
                } else {
                    Cat.logEvent("SQL.Length", item.getValue(), "long-length-sql", String.valueOf(length));
                }
                return;
            }

            counter++;
        }
    }

    private void logSqlMethodEvent(String sql, List<String> batchedSql, boolean isBatched, Object sqlParams) {
        String params = Stringizers.forJson().compact()
                .from(sqlParams, CatConstants.MAX_LENGTH, CatConstants.MAX_ITEM_LENGTH);
        if (isBatched) {
            if (batchedSql != null) {
                for (String bSql : batchedSql) {
                    Cat.logEvent("SQL.Method", SqlUtils.buildSqlType(bSql), Event.SUCCESS, params);
                    logSqlLengthEvent(sql);
                }
            }
        } else {
            if (sql != null) {
                Cat.logEvent("SQL.Method", SqlUtils.buildSqlType(sql), Event.SUCCESS, params);
                logSqlLengthEvent(sql);
            }
        }
    }

    @Override
    public void refreshGroupDataSource(GroupDataSource source, String propertiesName, JdbcFilter chain) {
        Transaction t = Cat.newTransaction(CAT_TYPE, "DataSource.Refresh-" + source.getJdbcRef());
        Cat.logEvent("DAL.Refresh.Property", propertiesName);
        try {
            chain.refreshGroupDataSource(source, propertiesName, chain);
            t.setStatus(Message.SUCCESS);
        } catch (RuntimeException exp) {
            Cat.logError(exp);
            t.setStatus(exp);
            throw exp;
        } finally {
            t.complete();
        }
    }

    @Override
    public void switchFailOverDataSource(FailOverDataSource source, JdbcFilter chain) {
        Transaction t = Cat.newTransaction(CAT_TYPE, "FailOver");
        try {
            chain.switchFailOverDataSource(source, chain);
            Cat.logEvent("DAL.FailOver", "Success");
            t.setStatus(Message.SUCCESS);
        } catch (RuntimeException exp) {
            Cat.logEvent("DAL.FailOver", "Failed");
            Cat.logError(exp);
            t.setStatus("Fail to find any master database");
            throw exp;
        } finally {
            t.complete();
        }
    }
}
