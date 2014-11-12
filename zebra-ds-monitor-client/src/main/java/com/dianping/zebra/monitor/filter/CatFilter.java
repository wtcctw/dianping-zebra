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
import com.dianping.zebra.group.util.SqlUtils;
import com.dianping.zebra.group.util.StringUtils;
import com.dianping.zebra.monitor.monitor.GroupDataSourceMonitor;
import com.site.helper.Stringizers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Dozer on 9/5/14.
 */
public class CatFilter extends DefaultJdbcFilter {
	private static final int MAX_LENGTH_PER_SQL = 1024 * 10; // about 10k

	private static final int MAX_CACHED_SQL = 2000;

	private static final String DAL_CAT_TYPE = "DAL";

	private static final String SQL_STATEMENT_NAME = "sql_statement_name";

	private static final Map<Integer, String> SQL_LENGTH_RANGE = new LinkedHashMap<Integer, String>();

	static {
		SQL_LENGTH_RANGE.put(1024, "<= 1K");
		SQL_LENGTH_RANGE.put(10240, "<= 10K");
		SQL_LENGTH_RANGE.put(102400, "<= 100K");
		SQL_LENGTH_RANGE.put(1024 * 1024, "<= 1M");
		SQL_LENGTH_RANGE.put(10240 * 1024, "<= 10M");
		SQL_LENGTH_RANGE.put(102400 * 1024, "<= 100M");
		SQL_LENGTH_RANGE.put(Integer.MAX_VALUE, "> 100M");
	}

	private Set<String> cachedSqlSet = new HashSet<String>();

	@Override
	public void closeSingleDataSource(SingleDataSource source, JdbcFilter chain) throws SQLException {
		chain.closeSingleDataSource(source, chain);
		Cat.logEvent("DataSource.Destoryed", source.getConfig().getId());
	}

	@Override
	public <T> T execute(GroupStatement source, Connection conn, String sql, List<String> batchedSql,
			boolean isBatched, boolean autoCommit, Object sqlParams, JdbcFilter chain) throws SQLException {
		String sqlName = ExecutionContextHolder.getContext().get(SQL_STATEMENT_NAME);
		Transaction t;
		if (StringUtils.isBlank(sqlName)) {
			t = Cat.newTransaction("SQL", getCachedSqlName(sql, batchedSql, isBatched));
			if (isBatched) {
				t.addData(Stringizers.forJson().compact().from(batchedSql));
			} else {
				t.addData(sql);
			}
		} else {
			t = Cat.newTransaction("SQL", sqlName);
			t.addData(sql);
		}

		try {
			T result = chain.execute(source, conn, sql, batchedSql, isBatched, autoCommit, sqlParams, chain);

			SingleConnection singleConnection = conn instanceof SingleConnection ? (SingleConnection) conn : null;

			if (singleConnection != null) {
				Cat.logEvent("SQL.Database", singleConnection.getConfig().getJdbcUrl(), Event.SUCCESS,
						singleConnection.getConfig().getId());
			}
			String params = Stringizers.forJson().compact()
					.from(sqlParams, CatConstants.MAX_LENGTH, CatConstants.MAX_ITEM_LENGTH);
			if (isBatched) {
				if (batchedSql != null) {
					for (String bSql : batchedSql) {
						Cat.logEvent("SQL.Method", SqlUtils.buildSqlType(bSql), Transaction.SUCCESS, params);
						logSqlLengthEvent(sql, params);
					}
				}
			} else {
				if (sql != null) {
					Cat.logEvent("SQL.Method", SqlUtils.buildSqlType(sql), Transaction.SUCCESS, params);
					logSqlLengthEvent(sql, params);
				}
			}

			t.setStatus(Transaction.SUCCESS);
			return result;
		} catch (SQLException exp) {
			t.setStatus(exp);
			throw exp;
		} catch (RuntimeException exp) {
			t.setStatus(exp);
			throw exp;
		} finally {
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

	private String getCachedSqlName(String sql, List<String> batchedSql, boolean isBatch) {
		if (!isBatch) {
			if (sql == null) {
				return null;
			} else {
				int len = sql.length();

				if (len > MAX_LENGTH_PER_SQL) {
					sql = sql.substring(0, MAX_LENGTH_PER_SQL);
				}

				if (!cachedSqlSet.contains(sql) && cachedSqlSet.size() > MAX_CACHED_SQL) {
					return null;
				} else {
					cachedSqlSet.add(sql);
					return sql;
				}
			}
		} else {
			if (batchedSql == null) {
				return null;
			} else {
				return "batched";
			}
		}
	}

	@Override
	public void initGroupDataSource(GroupDataSource source, JdbcFilter chain) {
		chain.initGroupDataSource(source, chain);
		StatusExtensionRegister.getInstance().register(new GroupDataSourceMonitor(source));
	}

	@Override
	public DataSource initSingleDataSource(SingleDataSource source, JdbcFilter chain) {
		DataSource result = chain.initSingleDataSource(source, chain);
		Cat.logEvent("DataSource.Created", source.getConfig().getId());
		return result;
	}

	@Override
	public void refreshGroupDataSource(GroupDataSource source, String propertiesName, JdbcFilter chain) {
		Transaction t = Cat.newTransaction(DAL_CAT_TYPE, "DataSource.Refresh-" + source.getJdbcRef());
		Cat.logEvent("DAL.Refresh.Property", propertiesName);
		try {
			chain.refreshGroupDataSource(source, propertiesName, chain);
			t.setStatus(Message.SUCCESS);
		} catch (RuntimeException exp) {
			t.setStatus(exp);
		} finally {
			t.complete();
		}
	}

	@Override
	public void switchFailOverDataSource(FailOverDataSource source, JdbcFilter chain) {
		Transaction t = Cat.newTransaction(DAL_CAT_TYPE, "FailOver");
		try {
			chain.switchFailOverDataSource(source, chain);
			Cat.logEvent("DAL.FailOver", "Success");
			t.setStatus(Message.SUCCESS);
		} catch (RuntimeException exp) {
			Cat.logEvent("DAL.FailOver", "Failed");
			t.setStatus("Fail to find any master database");
		} finally {
			t.complete();
		}
	}

	private void logSqlLengthEvent(String sql, String params) {
		int length = sql == null ? 0 : sql.length();

		for (Map.Entry<Integer, String> item : SQL_LENGTH_RANGE.entrySet()) {
			if (length <= item.getKey()) {
				Cat.logEvent("SQL.Length", item.getValue(), Transaction.SUCCESS, params);
				return;
			}
		}
	}
}
