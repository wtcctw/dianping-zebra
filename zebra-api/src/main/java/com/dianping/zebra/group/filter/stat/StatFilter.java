package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.AbstractJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;
import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dozer on 9/2/14.
 */
public class StatFilter extends AbstractJdbcFilter {

	@Override public void closeGroupConnectionError(JdbcMetaData metaData, Exception exp) {
		StatContext.closeGroupConnectionErrorCount.incrementAndGet();
	}

	@Override public void closeGroupConnectionSuccess(JdbcMetaData metaData) {
		StatContext.closeGroupConnectionSuccessCount.incrementAndGet();
	}

	@Override public void executeError(JdbcMetaData metaData, Exception exp) {
		visitNode(metaData, exp);
	}

	@Override public void executeSuccess(JdbcMetaData metaData) {
		visitNode(metaData);
	}

	@Override public void getGroupConnectionError(JdbcMetaData metaData, Exception exp) {
		StatContext.getGroupConnectionErrorCount.incrementAndGet();
	}

	@Override public void getGroupConnectionSuccess(JdbcMetaData metaData) {
		StatContext.getGroupConnectionSuccessCount.incrementAndGet();
	}

	private void visitNode(JdbcMetaData metaData, Exception exp) {
		visitNode(metaData.getNode(), exp);
		visitNode(metaData.getBatchedNode(), exp);
	}

	private void visitNode(JdbcMetaData node) {
		visitNode(node, null);
	}

	private void visitNode(StatementNode node, Exception exp) {
		try {
			new SqlStatVisitor(exp).visit(node);
		} catch (StandardException e) {
		}
	}

	private void visitNode(List<StatementNode> nodes, Exception exp) {
		if (nodes == null) {
			return;
		}
		for (StatementNode node : nodes) {
			visitNode(node, exp);
		}
	}

	class SqlStatVisitor implements Visitor {
		private final Exception exception;

		public SqlStatVisitor() {
			exception = null;
		}

		public SqlStatVisitor(Exception exp) {
			this.exception = exp;
		}

		public void cursorNode(CursorNode node) throws StandardException {
			visit(node.getResultSetNode());
		}

		public void deleteNode(DeleteNode node) {
			increment(StatContext.deleteSuccessCount, StatContext.deleteErrorCount);
		}

		private void increment(AtomicLong success, AtomicLong error) {
			if (exception == null) {
				success.incrementAndGet();
			} else {
				error.incrementAndGet();
			}
		}

		public void insertNode(InsertNode node) {
			increment(StatContext.insertSuccessCount, StatContext.insertErrorCount);
		}

		public void selectNode(SelectNode node) {
			increment(StatContext.selectSuccessCount, StatContext.selectErrorCount);
		}

		@Override public boolean skipChildren(Visitable node) throws StandardException {
			return false;
		}

		@Override public boolean stopTraversal() {
			return false;
		}

		public void updateNode(UpdateNode node) {
			increment(StatContext.updateSuccessCount, StatContext.updateErrorCount);
		}

		@Override public Visitable visit(Visitable node) throws StandardException {
			if (node == null) {
				return node;
			}

			switch (((QueryTreeNode) node).getNodeType()) {
			case NodeTypes.CURSOR_NODE: {
				cursorNode((CursorNode) node);
				break;
			}
			case NodeTypes.SELECT_NODE: {
				selectNode((SelectNode) node);
				break;
			}
			case NodeTypes.UPDATE_NODE: {
				updateNode((UpdateNode) node);
				break;
			}
			case NodeTypes.INSERT_NODE: {
				insertNode((InsertNode) node);
				break;
			}
			case NodeTypes.DELETE_NODE: {
				deleteNode((DeleteNode) node);
				break;
			}
			}
			return node;
		}

		@Override public boolean visitChildrenFirst(Visitable node) {
			return false;
		}
	}
}
