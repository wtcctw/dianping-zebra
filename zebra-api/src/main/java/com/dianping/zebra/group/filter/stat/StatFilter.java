package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.AbstractJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;
import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.StatementNode;

/**
 * Created by Dozer on 9/2/14.
 */
public class StatFilter extends AbstractJdbcFilter {

	private ThreadLocal<Long> executeTime = new ThreadLocal<Long>();

	@Override public void closeGroupConnectionError(JdbcMetaData metaData, Exception exp) {
		StatContext.getDataSourceSummary().getCloseGroupConnectionErrorCount().incrementAndGet();
		StatContext.getDataSource(metaData).getCloseGroupConnectionErrorCount().incrementAndGet();
	}

	@Override public void closeGroupConnectionSuccess(JdbcMetaData metaData) {
		StatContext.getDataSourceSummary().getCloseGroupConnectionSuccessCount().incrementAndGet();
		StatContext.getDataSource(metaData).getCloseGroupConnectionSuccessCount().incrementAndGet();
	}

	@Override public void executeBefore(JdbcMetaData metaData) {
		executeTime.set(System.currentTimeMillis());
	}

	@Override public void executeError(JdbcMetaData metaData, Exception exp) {
		if (executeTime.get() != null) {
			long time = System.currentTimeMillis() - executeTime.get();
			StatContext.getExecuteSummary().getErrorTimeRange().increment(time);
			StatContext.getExecute(metaData).getErrorTimeRange().increment(time);
			StatContext.getExecuteSummary().getErrorTime().addAndGet(time);
			StatContext.getExecute(metaData).getErrorTime().addAndGet(time);
		}
		executeTime.set(null);
		visitNode(metaData, exp);
	}

	@Override public void executeSuccess(JdbcMetaData metaData) {
		if (executeTime.get() != null) {
			long time = System.currentTimeMillis() - executeTime.get();
			StatContext.getExecuteSummary().getSuccessTimeRange().increment(time);
			StatContext.getExecute(metaData).getSuccessTimeRange().increment(time);
			StatContext.getExecuteSummary().getSuccessTime().addAndGet(time);
			StatContext.getExecute(metaData).getSuccessTime().addAndGet(time);
		}
		executeTime.set(null);
		visitNode(metaData);
	}

	@Override public void getGroupConnectionError(JdbcMetaData metaData, Exception exp) {
		StatContext.getDataSourceSummary().getGetGroupConnectionErrorCount().incrementAndGet();
		StatContext.getDataSource(metaData).getGetGroupConnectionErrorCount().incrementAndGet();
	}

	@Override public void getGroupConnectionSuccess(JdbcMetaData metaData) {
		StatContext.getDataSourceSummary().getGetGroupConnectionSuccessCount().incrementAndGet();
		StatContext.getDataSource(metaData).getGetGroupConnectionSuccessCount().incrementAndGet();
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
