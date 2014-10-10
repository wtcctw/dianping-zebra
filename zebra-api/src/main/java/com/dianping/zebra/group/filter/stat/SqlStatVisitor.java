package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.JdbcContext;
import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dozer on 9/16/14.
 */

public class SqlStatVisitor implements Visitor {
	private final Exception exception;

	private final JdbcContext context;

	private final Object result;

	public SqlStatVisitor(JdbcContext context, Object result, Exception exp) {
		this.context = context;
		this.exception = exp;
		this.result = result;
	}

	public void createTableNode(CreateTableNode node) {
		sqlNode(node);
	}

	public void cursorNode(CursorNode node) throws StandardException {
		visit(node.getResultSetNode());
	}

	public void deleteNode(DeleteNode node) {
		sqlNode(node);
		StatContext.getExecuteSummary().getDeleteRow().addAndGet(getResultUpdateRows());
		StatContext.getExecute(context).getDeleteRow().addAndGet(getResultUpdateRows());
		increment(StatContext.getExecuteSummary().getDeleteSuccessCount(), StatContext.getExecuteSummary()
		      .getDeleteErrorCount());
		increment(StatContext.getExecute(context).getDeleteSuccessCount(), StatContext.getExecute(context)
		      .getDeleteErrorCount());
	}

	private long getResultUpdateRows() {
		if (result instanceof Integer) {
			return ((Integer) result).intValue();
		}
		if (result instanceof Long) {
			return ((Long) result).longValue();
		}
		return 0;
	}

	private void increment(AtomicLong success, AtomicLong error) {
		if (exception == null) {
			success.incrementAndGet();
		} else {
			error.incrementAndGet();
		}
	}

	public void insertNode(InsertNode node) {
		sqlNode(node);
		StatContext.getExecuteSummary().getInsertRow().addAndGet(getResultUpdateRows());
		StatContext.getExecute(context).getInsertRow().addAndGet(getResultUpdateRows());
		increment(StatContext.getExecuteSummary().getInsertSuccessCount(), StatContext.getExecuteSummary()
		      .getInsertErrorCount());
		increment(StatContext.getExecute(context).getInsertSuccessCount(), StatContext.getExecute(context)
		      .getInsertErrorCount());
	}

	public void selectNode(SelectNode node) {
		sqlNode(node);
		increment(StatContext.getExecuteSummary().getSelectSuccessCount(), StatContext.getExecuteSummary()
		      .getSelectErrorCount());
		increment(StatContext.getExecute(context).getSelectSuccessCount(), StatContext.getExecute(context)
		      .getSelectErrorCount());
	}

	@Override
	public boolean skipChildren(Visitable node) throws StandardException {
		return false;
	}

	public void sqlNode(Visitable node) {
		if (this.context.isTransaction()) {
			StatContext.getExecuteSummary().getTransactionCount().incrementAndGet();
			StatContext.getExecute(context).getTransactionCount().incrementAndGet();
		}

		increment(StatContext.getExecuteSummary().getSuccessCount(), StatContext.getExecuteSummary().getErrorCount());
		increment(StatContext.getExecute(context).getSuccessCount(), StatContext.getExecute(context).getErrorCount());
	}

	@Override
	public boolean stopTraversal() {
		return false;
	}

	public void updateNode(UpdateNode node) {
		sqlNode(node);
		StatContext.getExecuteSummary().getUpdateRow().addAndGet(getResultUpdateRows());
		StatContext.getExecute(context).getUpdateRow().addAndGet(getResultUpdateRows());
		increment(StatContext.getExecuteSummary().getUpdateSuccessCount(), StatContext.getExecuteSummary()
		      .getUpdateErrorCount());
		increment(StatContext.getExecute(context).getUpdateSuccessCount(), StatContext.getExecute(context)
		      .getUpdateErrorCount());
	}

//	private <T extends QueryTreeNode> void visit(QueryTreeNodeList<T> nodes) throws StandardException {
//		for (QueryTreeNode node : nodes) {
//			visit(node);
//		}
//	}

	@Override
	public Visitable visit(Visitable node) throws StandardException {
		if (node == null) {
			return node;
		}

		switch (((QueryTreeNode) node).getNodeType()) {
		case NodeTypes.CURSOR_NODE:
			cursorNode((CursorNode) node);
			break;
		case NodeTypes.SELECT_NODE:
			selectNode((SelectNode) node);
			break;
		case NodeTypes.UPDATE_NODE:
			updateNode((UpdateNode) node);
			break;
		case NodeTypes.INSERT_NODE:
			insertNode((InsertNode) node);
			break;
		case NodeTypes.DELETE_NODE:
			deleteNode((DeleteNode) node);
			break;
		case NodeTypes.CREATE_TABLE_NODE:
			createTableNode((CreateTableNode) node);
			break;
		}
		return node;
	}

	@Override
	public boolean visitChildrenFirst(Visitable node) {
		return false;
	}
}
