package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;
import com.dianping.zebra.group.filter.delegate.FilterAction;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
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

	@Override public <S> void closeGroupConnection(JdbcMetaData metaData, S source,
			FilterActionWithSQLExcption<S> action)
			throws SQLException {
		try {
			super.closeGroupConnection(metaData, source, action);
			StatContext.getDataSourceSummary().getCloseConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseConnectionSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseConnectionErrorCount().incrementAndGet();
			throw exp;
		} catch (RuntimeException exp) {
			StatContext.getDataSourceSummary().getCloseConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S> void closeGroupDataSource(JdbcMetaData metaData, S source,
			FilterActionWithSQLExcption<S> action) throws SQLException {
		try {
			super.closeGroupDataSource(metaData, source, action);
			StatContext.getDataSourceSummary().getCloseDataSourceSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseDataSourceSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseDataSourceErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseDataSourceErrorCount().incrementAndGet();
			throw exp;
		} catch (RuntimeException exp) {
			StatContext.getDataSourceSummary().getCloseDataSourceErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseDataSourceErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S> void closeSingleConnection(JdbcMetaData metaData, S source,
			FilterActionWithSQLExcption<S> action) throws SQLException {
		try {
			super.closeSingleConnection(metaData, source, action);
			StatContext.getDataSourceSummary().getCloseConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseConnectionSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseConnectionErrorCount().incrementAndGet();
			throw exp;
		} catch (RuntimeException exp) {
			StatContext.getDataSourceSummary().getCloseConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S> void closeSingleDataSource(JdbcMetaData metaData, S source,
			FilterActionWithSQLExcption<S> action) throws SQLException {

		try {
			super.closeSingleDataSource(metaData, source, action);
			StatContext.getDataSourceSummary().getCloseDataSourceSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseDataSourceSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseDataSourceErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseDataSourceErrorCount().incrementAndGet();
			throw exp;
		} catch (RuntimeException exp) {
			StatContext.getDataSourceSummary().getCloseDataSourceErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseDataSourceErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S, T> T execute(JdbcMetaData metaData, S source, FilterFunctionWithSQLException<S, T> action)
			throws SQLException {
		Long executeStart = System.currentTimeMillis();
		try {
			T result = super.execute(metaData, source, action);
			long time = System.currentTimeMillis() - executeStart;
			StatContext.getExecuteSummary().getSuccessTimeRange().increment(time);
			StatContext.getExecute(metaData).getSuccessTimeRange().increment(time);
			StatContext.getExecuteSummary().getSuccessTime().addAndGet(time);
			StatContext.getExecute(metaData).getSuccessTime().addAndGet(time);
			visitNode(metaData, result);
			return result;
		} catch (SQLException exp) {
			long time = System.currentTimeMillis() - executeStart;
			StatContext.getExecuteSummary().getErrorTimeRange().increment(time);
			StatContext.getExecute(metaData).getErrorTimeRange().increment(time);
			StatContext.getExecuteSummary().getErrorTime().addAndGet(time);
			StatContext.getExecute(metaData).getErrorTime().addAndGet(time);
			visitNode(metaData, null, exp);
			throw exp;
		} catch (RuntimeException exp) {
			long time = System.currentTimeMillis() - executeStart;
			StatContext.getExecuteSummary().getErrorTimeRange().increment(time);
			StatContext.getExecute(metaData).getErrorTimeRange().increment(time);
			StatContext.getExecuteSummary().getErrorTime().addAndGet(time);
			StatContext.getExecute(metaData).getErrorTime().addAndGet(time);
			visitNode(metaData, null, exp);
			throw exp;
		}
	}

	@Override public <S> GroupConnection getGroupConnection(JdbcMetaData metaData, S source,
			FilterFunctionWithSQLException<S, GroupConnection> action) throws SQLException {
		try {
			GroupConnection result = super.getGroupConnection(metaData, source, action);
			StatContext.getDataSourceSummary().getGetConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getGetConnectionSuccessCount().incrementAndGet();
			return result;
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getGetConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getGetConnectionErrorCount().incrementAndGet();
			throw exp;
		} catch (RuntimeException exp) {
			StatContext.getDataSourceSummary().getGetConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getGetConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S> SingleConnection getSingleConnection(JdbcMetaData metaData, S source,
			FilterFunctionWithSQLException<S, SingleConnection> action) throws SQLException {
		try {
			SingleConnection result = super.getSingleConnection(metaData, source, action);
			StatContext.getDataSourceSummary().getGetConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getGetConnectionSuccessCount().incrementAndGet();
			return result;
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getGetConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getGetConnectionErrorCount().incrementAndGet();
			throw exp;
		} catch (RuntimeException exp) {
			StatContext.getDataSourceSummary().getGetConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getGetConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S> void initGroupDataSource(JdbcMetaData metaData, S source, FilterAction<S> action) {
		super.initGroupDataSource(metaData, source, action);
		StatContext.getDataSourceSummary().getInitDataSourceSuccessCount().incrementAndGet();
		StatContext.getDataSource(metaData).getInitDataSourceSuccessCount().incrementAndGet();
	}

	@Override public <S> DataSource initSingleDataSource(JdbcMetaData metaData, S source,
			FilterFunction<S, DataSource> action) {
		DataSource result = super.initSingleDataSource(metaData, source, action);
		StatContext.getDataSourceSummary().getInitDataSourceSuccessCount().incrementAndGet();
		StatContext.getDataSource(metaData).getInitDataSourceSuccessCount().incrementAndGet();
		return result;
	}

	@Override public <S> void refreshGroupDataSource(JdbcMetaData metaData, String propertiesName, S source,
			FilterAction<S> action) {
		super.refreshGroupDataSource(metaData, propertiesName, source, action);
		StatContext.getDataSourceSummary().getRefreshDataSourceSuccessCount().incrementAndGet();
		StatContext.getDataSource(metaData).getRefreshDataSourceSuccessCount().incrementAndGet();
	}

	@Override public <S> Boolean resultSetNext(JdbcMetaData metaData, S source,
			FilterFunctionWithSQLException<S, Boolean> action) throws SQLException {
		Boolean result = super.resultSetNext(metaData, source, action);
		if (result) {
			StatContext.getExecuteSummary().getSelectRow().incrementAndGet();
			StatContext.getExecute(metaData).getSelectRow().incrementAndGet();
		}
		return result;
	}

	private void visitNode(JdbcMetaData metaData, Object result, Exception exp) {
		if (!metaData.isBatch()) {

			try {
				new SqlStatVisitor(metaData, result, exp).visit(metaData.getNode());
			} catch (StandardException e) {
			}

		} else {
			if (!(result instanceof int[])) {
				return;
			}
			int[] intResult = (int[]) result;

			if (metaData.isPrepared()) {
				for (int k = 0; k < intResult.length; k++) {
					try {
						new SqlStatVisitor(metaData, intResult[k], exp).visit(metaData.getNode());
					} catch (StandardException e) {
					}
				}
			} else {
				if (metaData.getBatchedNode() == null) {
					return;
				}
				List<StatementNode> batchedNodes = metaData.getBatchedNode();
				if (intResult.length != batchedNodes.size()) {
					return;
				}

				for (int k = 0; k < intResult.length; k++) {
					try {
						new SqlStatVisitor(metaData, intResult[k], exp).visit(batchedNodes.get(k));
					} catch (StandardException e) {
					}
				}
			}
		}
	}

	private void visitNode(JdbcMetaData metadata, Object result) {
		visitNode(metadata, result, null);
	}
}
