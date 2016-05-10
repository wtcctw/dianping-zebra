package com.dianping.zebra.monitor.filter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.status.StatusExtensionRegister;
import com.dianping.zebra.filter.DefaultJdbcFilter;
import com.dianping.zebra.filter.JdbcFilter;
import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.jdbc.GroupStatement;
import com.dianping.zebra.group.util.SqlAliasManager;
import com.dianping.zebra.monitor.monitor.GroupDataSourceMonitor;
import com.dianping.zebra.monitor.util.SqlMonitorUtils;
import com.dianping.zebra.single.jdbc.SingleConnection;
import com.dianping.zebra.single.jdbc.SingleDataSource;
import com.dianping.zebra.util.SqlUtils;
import com.site.helper.Stringizers;

/**
 * Created by Dozer on 9/5/14.
 */
public class CatFilter extends DefaultJdbcFilter {
    private static final String CAT_TYPE = "DAL";

    private GroupDataSourceMonitor monitor = null;
    
    @Override
    public void closeSingleDataSource(SingleDataSource source, JdbcFilter chain) throws SQLException {
        chain.closeSingleDataSource(source, chain);
        Cat.logEvent("DataSource.Destoryed", source.getConfig().getId());
        StatusExtensionRegister.getInstance().unregister(this.monitor);
    }

    @Override
    public SingleConnection getSingleConnection(SingleDataSource source, JdbcFilter chain) throws SQLException {
        try {
            return chain.getSingleConnection(source, chain);
        } catch (SQLException exp) {
            Transaction t = Cat.newTransaction("SQL", SqlAliasManager.getSqlAlias());

            Cat.logEvent("SQL.Database", source.getConfig().getJdbcUrl(), "ERROR", source.getConfig().getId());
            Cat.logError(exp);

            t.setStatus(exp);
            t.complete();
            throw exp;
        }
    }

    @Override
    public Connection getRealConnection(GroupStatement source, String sql, boolean forceWriter, JdbcFilter chain)
            throws SQLException {
        SqlAliasManager.setSqlAlias(sql);

        return chain.getRealConnection(source, sql, forceWriter, chain);
    }

    @Override
    public <T> T executeGroupStatement(GroupStatement source, Connection conn, String sql, List<String> batchedSql, boolean isBatched,
                         boolean autoCommit, Object sqlParams, JdbcFilter chain) throws SQLException {
        Transaction t;
        if (isBatched) {
            t = Cat.newTransaction("SQL", "batched");
            t.addData(Stringizers.forJson().compact().from(batchedSql));
        } else {
            t = Cat.newTransaction("SQL", SqlAliasManager.getSqlAlias());
            t.addData(sql);
        }

        try {
            T result = chain.executeGroupStatement(source, conn, sql, batchedSql, isBatched, autoCommit, sqlParams, chain);
            t.setStatus(Transaction.SUCCESS);

            return result;
        } catch (SQLException exp) {
            Cat.logError(exp);
            t.setStatus(exp);

            String state = exp.getSQLState();
            if (state != null && state.equalsIgnoreCase("SQL.Blacklist")) {
                Cat.logEvent("SQL.Blacklist", SqlAliasManager.getSqlAlias());
            }
            
            throw exp;
        } finally {
            try {
            	 logSqlMethodEvent(sql, batchedSql, isBatched, sqlParams);
                logSqlDatabaseEvent(conn);
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

    @Override
    public void initGroupDataSource(GroupDataSource source, JdbcFilter chain) {
        Transaction transaction = Cat.newTransaction(CAT_TYPE, "DataSource.Init-" + source.getJdbcRef());
        try {
            chain.initGroupDataSource(source, chain);
            this.monitor = new GroupDataSourceMonitor(source);
            StatusExtensionRegister.getInstance().register(this.monitor);
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
        Cat.logEvent("DataSource.Type", source.getConfig().getType());
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

        if (length <= SqlMonitorUtils.BIG_SQL) {
            Cat.logEvent("SQL.Length", SqlMonitorUtils.getSqlLengthName(length), Message.SUCCESS, "");
        } else {
            Cat.logEvent("SQL.Length", SqlMonitorUtils.getSqlLengthName(length), "long-sql warning", "");
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
