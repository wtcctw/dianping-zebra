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
			StatContext.getDataSourceSummary().getCloseGroupConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseGroupConnectionSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseGroupConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseGroupConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S> void closeGroupDataSource(JdbcMetaData metaData, S source,
			FilterActionWithSQLExcption<S> action) throws SQLException {
		try {
			super.closeGroupDataSource(metaData, source, action);
			StatContext.getDataSourceSummary().getCloseGroupDataSourceSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseGroupDataSourceSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseGroupDataSourceErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseGroupDataSourceErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S> void closeSingleConnection(JdbcMetaData metaData, S source,
			FilterActionWithSQLExcption<S> action) throws SQLException {
		try {
			super.closeSingleConnection(metaData, source, action);
			StatContext.getDataSourceSummary().getCloseSingleConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseSingleConnectionSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseSingleConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseSingleConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S> void closeSingleDataSource(JdbcMetaData metaData, S source,
			FilterActionWithSQLExcption<S> action) throws SQLException {
		try {
			super.closeSingleDataSource(metaData, source, action);
			StatContext.getDataSourceSummary().getCloseSingleDataSourceSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseSingleDataSourceSuccessCount().incrementAndGet();
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getCloseSingleDataSourceErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getCloseSingleDataSourceErrorCount().incrementAndGet();
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
		}
	}

	@Override public <S> GroupConnection getGroupConnection(JdbcMetaData metaData, S source,
			FilterFunctionWithSQLException<S, GroupConnection> action) throws SQLException {
		try {
			GroupConnection result = super.getGroupConnection(metaData, source, action);
			StatContext.getDataSourceSummary().getGetGroupConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getGetGroupConnectionSuccessCount().incrementAndGet();
			return result;
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getGetGroupConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getGetGroupConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S> SingleConnection getSingleConnection(JdbcMetaData metaData, S source,
			FilterFunctionWithSQLException<S, SingleConnection> action) throws SQLException {
		try {
			SingleConnection result = super.getSingleConnection(metaData, source, action);
			StatContext.getDataSourceSummary().getGetSingleConnectionSuccessCount().incrementAndGet();
			StatContext.getDataSource(metaData).getGetSingleConnectionSuccessCount().incrementAndGet();
			return result;
		} catch (SQLException exp) {
			StatContext.getDataSourceSummary().getGetSingleConnectionErrorCount().incrementAndGet();
			StatContext.getDataSource(metaData).getGetSingleConnectionErrorCount().incrementAndGet();
			throw exp;
		}
	}

	@Override public <S> void initGroupDataSource(JdbcMetaData metaData, S source, FilterAction<S> action) {
		super.initGroupDataSource(metaData, source, action);
		StatContext.getDataSourceSummary().getInitGroupDataSourceSuccessCount().incrementAndGet();
		StatContext.getDataSource(metaData).getInitGroupDataSourceSuccessCount().incrementAndGet();
	}

	@Override public <S> DataSource initSingleDataSource(JdbcMetaData metaData, S source,
			FilterFunction<S, DataSource> action) {
		DataSource result = super.initSingleDataSource(metaData, source, action);
		StatContext.getDataSourceSummary().getInitSingleDataSourceSuccessCount().incrementAndGet();
		StatContext.getDataSource(metaData).getInitSingleDataSourceSuccessCount().incrementAndGet();
		return result;
	}

	@Override public <S> void refreshGroupDataSource(JdbcMetaData metaData, String propertiesName, S source,
			FilterAction<S> action) {
		super.refreshGroupDataSource(metaData, propertiesName, source, action);
		StatContext.getDataSourceSummary().getRefreshGroupDataSourceSuccessCount().incrementAndGet();
		StatContext.getDataSource(metaData).getRefreshGroupDataSourceSuccessCount().incrementAndGet();
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
