package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
import com.dianping.zebra.group.jdbc.GroupConnection;
import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.StatementNode;

import java.sql.SQLException;

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
			visitNode(metaData);
			return result;
		} catch (SQLException exp) {
			long time = System.currentTimeMillis() - executeStart;
			StatContext.getExecuteSummary().getErrorTimeRange().increment(time);
			StatContext.getExecute(metaData).getErrorTimeRange().increment(time);
			StatContext.getExecuteSummary().getErrorTime().addAndGet(time);
			StatContext.getExecute(metaData).getErrorTime().addAndGet(time);
			visitNode(metaData, exp);
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

	private void visitNode(JdbcMetaData metaData, Exception exp) {
		if (!metaData.isBatch()) {
			try {
				new SqlStatVisitor(metaData, exp).visit(metaData.getNode());
			} catch (StandardException e) {
			}
		} else {
			if (metaData.getBatchedNode() == null) {
				return;
			}
			for (StatementNode node : metaData.getBatchedNode()) {
				try {
					new SqlStatVisitor(metaData, exp).visit(node);
				} catch (StandardException e) {
				}
			}
		}
	}

	private void visitNode(JdbcMetaData node) {
		visitNode(node, null);
	}
}
