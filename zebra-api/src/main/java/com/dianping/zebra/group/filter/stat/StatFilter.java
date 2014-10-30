package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcContext;
import com.dianping.zebra.group.filter.delegate.FilterAction;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
import com.dianping.zebra.group.filter.stat.server.ServerHelper;
import com.dianping.zebra.group.jdbc.GroupConnection;
import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.StatementNode;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dozer on 9/2/14.
 */
public class StatFilter extends DefaultJdbcFilter {
	@Override
	public void init() {
		ServerHelper.start();
		super.init();
	}

	@Override
	public <S> void closeGroupConnection(JdbcContext context, S source, FilterActionWithSQLExcption<S> action)
		  throws SQLException {
		try {
			super.closeGroupConnection(context, source, action);
			StatContext.getDataSourceSummary().getCloseConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(context).getCloseConnectionSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(context).getCloseConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override
	public <S> void closeGroupDataSource(JdbcContext context, S source, FilterActionWithSQLExcption<S> action)
		  throws SQLException {
		try {
			super.closeGroupDataSource(context, source, action);
			StatContext.getDataSourceSummary().getCloseDataSourceSuccessCount().incrementAndGet();
			StatContext.getDataSource(context).getCloseDataSourceSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseDataSourceErrorCount().incrementAndGet();
			StatContext.getDataSource(context).getCloseDataSourceErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override
	public <S> void closeSingleConnection(JdbcContext context, S source, FilterActionWithSQLExcption<S> action)
		  throws SQLException {
		try {
			super.closeSingleConnection(context, source, action);
			StatContext.getDataSourceSummary().getCloseConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(context).getCloseConnectionSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(context).getCloseConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override
	public <S> void closeSingleDataSource(JdbcContext context, S source, FilterActionWithSQLExcption<S> action)
		  throws SQLException {
		try {
			super.closeSingleDataSource(context, source, action);
			StatContext.getDataSourceSummary().getCloseDataSourceSuccessCount().incrementAndGet();
			StatContext.getDataSource(context).getCloseDataSourceSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseDataSourceErrorCount().incrementAndGet();
			StatContext.getDataSource(context).getCloseDataSourceErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override
	public <S, T> T execute(JdbcContext context, S source, FilterFunctionWithSQLException<S, T> action)
		  throws SQLException {
		Long executeStart = System.currentTimeMillis();
		try {
			T result = super.execute(context, source, action);
			long time = System.currentTimeMillis() - executeStart;
			StatContext.getExecuteSummary().getSuccessTimeRange().increment(time);
			StatContext.getExecute(context).getSuccessTimeRange().increment(time);
			StatContext.getExecuteSummary().getSuccessTime().addAndGet(time);
			StatContext.getExecute(context).getSuccessTime().addAndGet(time);
			visitNode(context, result);
			return result;
		} catch (SQLException exp) {
			long time = System.currentTimeMillis() - executeStart;
			StatContext.getExecuteSummary().getErrorTimeRange().increment(time);
			StatContext.getExecute(context).getErrorTimeRange().increment(time);
			StatContext.getExecuteSummary().getErrorTime().addAndGet(time);
			StatContext.getExecute(context).getErrorTime().addAndGet(time);
			visitNode(context, null, exp);
			throw exp;
		}
	}

	@Override
	public <S> GroupConnection getGroupConnection(JdbcContext context, S source,
		  FilterFunctionWithSQLException<S, GroupConnection> action) throws SQLException {
		try {
			GroupConnection result = super.getGroupConnection(context, source, action);
			StatContext.getDataSourceSummary().getGetConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(context).getGetConnectionSuccessCount().incrementAndGet();
			return result;
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getGetConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(context).getGetConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override
	public <S> SingleConnection getSingleConnection(JdbcContext context, S source,
		  FilterFunctionWithSQLException<S, SingleConnection> action) throws SQLException {
		try {
			SingleConnection result = super.getSingleConnection(context, source, action);
			StatContext.getDataSourceSummary().getGetConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(context).getGetConnectionSuccessCount().incrementAndGet();
			return result;
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getGetConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(context).getGetConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override
	public <S> void initGroupDataSource(JdbcContext context, S source, FilterAction<S> action) {
		super.initGroupDataSource(context, source, action);
		StatContext.getDataSourceSummary().getInitDataSourceSuccessCount().incrementAndGet();
		StatContext.getDataSource(context).getInitDataSourceSuccessCount().incrementAndGet();
	}

	@Override
	public <S> DataSource initSingleDataSource(JdbcContext context, S source, FilterFunction<S, DataSource> action) {
		DataSource result = super.initSingleDataSource(context, source, action);
		StatContext.getDataSourceSummary().getInitDataSourceSuccessCount().incrementAndGet();
		StatContext.getDataSource(context).getInitDataSourceSuccessCount().incrementAndGet();
		return result;
	}

	@Override
	public <S> void refreshGroupDataSource(JdbcContext context, String propertiesName, S source,
		  FilterAction<S> action) {
		super.refreshGroupDataSource(context, propertiesName, source, action);
		StatContext.getDataSourceSummary().getRefreshDataSourceSuccessCount().incrementAndGet();
		StatContext.getDataSource(context).getRefreshDataSourceSuccessCount().incrementAndGet();
	}

	@Override
	public <S> Boolean resultSetNext(JdbcContext context, S source, FilterFunctionWithSQLException<S, Boolean> action)
		  throws SQLException {
		Boolean result = super.resultSetNext(context, source, action);
		if (result) {
			StatContext.getExecuteSummary().getSelectRow().incrementAndGet();
			StatContext.getExecute(context).getSelectRow().incrementAndGet();
		}
		return result;
	}

	private void visitNode(JdbcContext context, Object result, Exception exp) {
		if (!context.isBatch()) {

			try {
				new SqlStatVisitor(context, result, exp).visit(context.getNode());
			} catch (StandardException e) {
			}

		} else {
			if (!(result instanceof int[])) {
				return;
			}
			int[] intResult = (int[]) result;

			if (context.isPrepared()) {
				for (int k = 0; k < intResult.length; k++) {
					try {
						new SqlStatVisitor(context, intResult[k], exp).visit(context.getNode());
					} catch (StandardException e) {
					}
				}
			} else {
				if (context.getBatchedNode() == null) {
					return;
				}
				List<StatementNode> batchedNodes = context.getBatchedNode();
				if (intResult.length != batchedNodes.size()) {
					return;
				}

				for (int k = 0; k < intResult.length; k++) {
					try {
						new SqlStatVisitor(context, intResult[k], exp).visit(batchedNodes.get(k));
					} catch (StandardException e) {
					}
				}
			}
		}
	}

	private void visitNode(JdbcContext metadata, Object result) {
		visitNode(metadata, result, null);
	}
}
