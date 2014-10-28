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
import java.util.Set;

/**
 * Created by Dozer on 9/5/14.
 */
public class CatFilter extends DefaultJdbcFilter {
	private static final String DAL_CAT_TYPE = "DAL";

	private static final String SQL_STATEMENT_NAME = "sql_statement_name";

	private Set<Integer> allSqlSet = new HashSet<Integer>();

	@Override
	public <S> void closeSingleDataSource(JdbcContext metaData, S source,
		  FilterActionWithSQLExcption<S> action) throws SQLException {
		super.closeSingleDataSource(metaData, source, action);
		Cat.logEvent("DataSource.Destoryed", metaData.getDataSourceId());
	}

	@Override
	public <S, T> T execute(JdbcContext metaData, S source, FilterFunctionWithSQLException<S, T> action)
		  throws SQLException {
		String sqlName = ExecutionContextHolder.getContext().get(SQL_STATEMENT_NAME);
		Transaction t;
		if (StringUtils.isBlank(sqlName)) {
			t = Cat.newTransaction("SQL", getSqlName(metaData));
			if (metaData.isBatch()) {
				t.addData(Stringizers.forJson().compact().from(metaData.getBatchedSql()));
			} else {
				t.addData(metaData.getSql());
			}
		} else {
			t = Cat.newTransaction("SQL", sqlName);
			t.addData(metaData.getSql());
		}

		try {
			T result = super.execute(metaData, source, action);

			if (metaData.getRealJdbcContext() != null) {
				Cat.logEvent("SQL.Database", metaData.getRealJdbcContext().getJdbcUrl(), Event.SUCCESS,
					  metaData.getRealJdbcContext().getDataSourceId());
				Cat.logEvent("SQL.Method", SqlUtils.buildSqlType(metaData.getSql()), Transaction.SUCCESS,
					  Stringizers.forJson().compact()
							.from(metaData.getParams(), CatConstants.MAX_LENGTH, CatConstants.MAX_ITEM_LENGTH));
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
	public <S> FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(
		  JdbcContext metaData, S source,
		  FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult> action) {
		FailOverDataSource.FindMasterDataSourceResult result = super
			  .findMasterFailOverDataSource(metaData, source, action);
		if (result != null && result.isChangedMaster()) {
			Cat.logEvent("DAL.Master", "Found-" + metaData.getDataSourceId());
		}
		return result;
	}

	@Override
	public <S> void initGroupDataSource(JdbcContext metaData, S source, FilterAction<S> action) {
		super.initGroupDataSource(metaData, source, action);
		if (source instanceof GroupDataSourceMBean) {
			StatusExtensionRegister.getInstance().register(new GroupDataSourceMonitor((GroupDataSourceMBean) source));
		}
	}

	@Override
	public <S> DataSource initSingleDataSource(JdbcContext metaData, S source,
		  FilterFunction<S, DataSource> action) {
		DataSource result = super.initSingleDataSource(metaData, source, action);
		Cat.logEvent("DataSource.Created", metaData.getDataSourceId());
		return result;
	}

	private String getSqlName(JdbcContext metaData) {
		if (metaData.isBatch()) {
			if (metaData.getSql() == null) {
				return null;
			}
			int hash = metaData.getSql().hashCode();
			if (!allSqlSet.contains(hash) && allSqlSet.size() > 1000) {
				return null;
			}
			allSqlSet.add(hash);
			return metaData.getSql();
		} else {
			if (metaData.getBatchedSql() == null) {
				return null;
			}
			return "batched";
		}
	}

	@Override
	public <S> void refreshGroupDataSource(JdbcContext metaData, String propertiesName, S source,
		  FilterAction<S> action) {
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
}
