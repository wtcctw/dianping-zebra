package com.dianping.zebra.monitor.filter;

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.status.StatusExtensionRegister;
import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcContext;
import com.dianping.zebra.group.filter.delegate.FilterAction;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
import com.dianping.zebra.group.monitor.GroupDataSourceMBean;
import com.dianping.zebra.group.util.SqlUtils;
import com.dianping.zebra.group.util.StringUtils;
import com.dianping.zebra.monitor.monitor.GroupDataSourceMonitor;
import com.site.helper.Stringizers;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
	public <S> void closeSingleDataSource(JdbcContext metaData, S source, FilterActionWithSQLExcption<S> action)
	      throws SQLException {
		super.closeSingleDataSource(metaData, source, action);
		Cat.logEvent("DataSource.Destoryed", metaData.getDataSourceId());
	}

	@Override
	public <S, T> T execute(JdbcContext context, S source, FilterFunctionWithSQLException<S, T> action)
	      throws SQLException {
		String sqlName = ExecutionContextHolder.getContext().get(SQL_STATEMENT_NAME);
		Transaction t;
		if (StringUtils.isBlank(sqlName)) {
			t = Cat.newTransaction("SQL", getCachedSqlName(context));
			if (context.isBatch()) {
				t.addData(Stringizers.forJson().compact().from(context.getBatchedSql()));
			} else {
				t.addData(context.getSql());
			}
		} else {
			t = Cat.newTransaction("SQL", sqlName);
			t.addData(context.getSql());
		}

		try {
			T result = super.execute(context, source, action);

			if (context.getRealJdbcContext() != null) {
				Cat.logEvent("SQL.Database", context.getRealJdbcContext().getJdbcUrl(), Event.SUCCESS, context
				      .getRealJdbcContext().getDataSourceId());
			}

			String params = Stringizers.forJson().compact()
			      .from(context.getParams(), CatConstants.MAX_LENGTH, CatConstants.MAX_ITEM_LENGTH);
			if (context.isBatch()) {
				if (context.getBatchedSql() != null) {
					for (String sql : context.getBatchedSql()) {
						Cat.logEvent("SQL.Method", SqlUtils.buildSqlType(sql), Transaction.SUCCESS, params);
						logSqlLengthEvent(sql, params);
					}
				}
			} else {
				if (context.getSql() != null) {
					Cat.logEvent("SQL.Method", SqlUtils.buildSqlType(context.getSql()), Transaction.SUCCESS, params);
					logSqlLengthEvent(context.getSql(), params);
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
	public <S> FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(JdbcContext metaData,
	      S source, FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult> action) {
		FailOverDataSource.FindMasterDataSourceResult result = super.findMasterFailOverDataSource(metaData, source,
		      action);
		if (result != null && result.isChangedMaster()) {
			Cat.logEvent("DAL.Master", "Found-" + metaData.getDataSourceId());
		}
		return result;
	}

	private String getCachedSqlName(JdbcContext context) {
		if (!context.isBatch()) {
			String sql = context.getSql();
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
			if (context.getBatchedSql() == null) {
				return null;
			} else {
				return "batched";
			}
		}
	}

	@Override
	public <S> void initGroupDataSource(JdbcContext metaData, S source, FilterAction<S> action) {
		super.initGroupDataSource(metaData, source, action);
		if (source instanceof GroupDataSourceMBean) {
			StatusExtensionRegister.getInstance().register(new GroupDataSourceMonitor((GroupDataSourceMBean) source));
		}
	}

	@Override
	public <S> DataSource initSingleDataSource(JdbcContext metaData, S source, FilterFunction<S, DataSource> action) {
		DataSource result = super.initSingleDataSource(metaData, source, action);
		Cat.logEvent("DataSource.Created", metaData.getDataSourceId());
		return result;
	}

	@Override
	public <S> void refreshGroupDataSource(JdbcContext metaData, String propertiesName, S source, FilterAction<S> action) {
		Transaction t = Cat.newTransaction(DAL_CAT_TYPE, "DataSource.Refresh-" + metaData.getDataSourceId());
		Cat.logEvent("DAL.Refresh.Property", propertiesName);
		try {
			super.refreshGroupDataSource(metaData, propertiesName, source, action);
			t.setStatus(Message.SUCCESS);
		} catch (RuntimeException exp) {
			t.setStatus(exp);
		} finally {
			t.complete();
		}
	}

	@Override
	public <S> void switchFailOverDataSource(JdbcContext metaData, S source, FilterAction<S> action) {
		Transaction t = Cat.newTransaction(DAL_CAT_TYPE, "FailOver");
		try {
			super.switchFailOverDataSource(metaData, source, action);
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
