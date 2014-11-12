package com.dianping.zebra.group.filter.visitor;

import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dozer on 10/8/14.
 */
public class MergeSqlVisitor implements Visitor {
	public final AtomicInteger parameterNodeIndex = new AtomicInteger();

	public void andNode(AndNode node) throws StandardException {
		visit(node.getLeftOperand());
		visit(node.getRightOperand());
	}

	public void binaryOperatorNode(BinaryOperatorNode node) throws StandardException {
		if (node.getLeftOperand() instanceof ConstantNode) {
			node.setLeftOperand(getNewParameterNode());
		}
		if (node.getRightOperand() instanceof ConstantNode) {
			node.setRightOperand(getNewParameterNode());
		}
		visit(node.getLeftOperand());
		visit(node.getRightOperand());
	}

	public void cursorNode(CursorNode node) throws StandardException {
		visit(node.getResultSetNode());
	}

	private void deleteNode(DeleteNode node) throws StandardException {
		visit(node.getResultSetNode());
	}

	private ParameterNode getNewParameterNode() {
		ParameterNode newNode = new ParameterNode();
		newNode.setNodeType(NodeTypes.PARAMETER_NODE);
		return newNode;
	}

	public void inListOperatorNode(InListOperatorNode node) throws StandardException {
		ValueNodeList list = node.getRightOperandList().getNodeList();
		if (list.size() > 0) {
			list.clear();
			ParameterNode newNode = getNewParameterNode();
			list.add(newNode);
		}

		visit(list);
	}

	private void insertNode(InsertNode node) throws StandardException {
		visit(node.getResultSetNode());
	}

	public void notNode(NotNode node) throws StandardException {
		visit(node.getOperand());
	}

	public void orNode(OrNode node) throws StandardException {
		visit(node.getLeftOperand());
		visit(node.getRightOperand());
	}

	private void parameterNode(ParameterNode node) {
		node.init(parameterNodeIndex.getAndIncrement(), null);
	}

	public void resultColumn(ResultColumn node) throws StandardException {
		if (node.getExpression() instanceof ConstantNode) {
			node.setExpression(getNewParameterNode());
		}
		visit(node.getExpression());
	}

	public void resultColumnList(ResultColumnList node) throws StandardException {
		for (ResultColumn n : node) {
			visit(n);
		}
	}

	private void rowResultSetNode(RowResultSetNode node) throws StandardException {
		visit(node.getResultColumns());
	}

	public void selectNode(SelectNode node) throws StandardException {
		visit(node.getWhereClause());
		visit(node.getResultColumns());
	}

	@Override
	public boolean skipChildren(Visitable node) throws StandardException {
		return false;
	}

	@Override
	public boolean stopTraversal() {
		return false;
	}

	public void subQueryNode(SubqueryNode node) throws StandardException {
		visit(node.getLeftOperand());
		visit(node.getResultSet());
	}

	private void updateNode(UpdateNode node) throws StandardException {
		visit(node.getResultSetNode());
	}

	public void valueNodeList(ValueNodeList node) throws StandardException {
		for (ValueNode n : node) {
			visit(n);
		}
	}

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
		case NodeTypes.DELETE_NODE:
			deleteNode((DeleteNode) node);
			break;
		case NodeTypes.UPDATE_NODE:
			updateNode((UpdateNode) node);
			break;
		case NodeTypes.INSERT_NODE:
			insertNode((InsertNode) node);
			break;
		case NodeTypes.SUBQUERY_NODE:
			subQueryNode((SubqueryNode) node);
			break;
		case NodeTypes.AND_NODE:
			andNode((AndNode) node);
			break;
		case NodeTypes.NOT_NODE:
			notNode((NotNode) node);
			break;
		case NodeTypes.OR_NODE:
			orNode((OrNode) node);
			break;
		case NodeTypes.IN_LIST_OPERATOR_NODE:
			inListOperatorNode((InListOperatorNode) node);
			break;
		case NodeTypes.PARAMETER_NODE:
			parameterNode((ParameterNode) node);
			break;
		case NodeTypes.BINARY_DIVIDE_OPERATOR_NODE:
		case NodeTypes.BINARY_EQUALS_OPERATOR_NODE:
		case NodeTypes.BINARY_GREATER_EQUALS_OPERATOR_NODE:
		case NodeTypes.BINARY_GREATER_THAN_OPERATOR_NODE:
		case NodeTypes.BINARY_LESS_EQUALS_OPERATOR_NODE:
		case NodeTypes.BINARY_LESS_THAN_OPERATOR_NODE:
		case NodeTypes.BINARY_MINUS_OPERATOR_NODE:
		case NodeTypes.BINARY_NOT_EQUALS_OPERATOR_NODE:
		case NodeTypes.BINARY_PLUS_OPERATOR_NODE:
		case NodeTypes.BINARY_TIMES_OPERATOR_NODE:
			binaryOperatorNode((BinaryOperatorNode) node);
			break;
		case NodeTypes.RESULT_COLUMN_LIST:
			resultColumnList((ResultColumnList) node);
			break;
		case NodeTypes.RESULT_COLUMN:
			resultColumn((ResultColumn) node);
			break;
		case NodeTypes.VALUE_NODE_LIST:
			valueNodeList((ValueNodeList) node);
			break;
		case NodeTypes.ROW_RESULT_SET_NODE:
			rowResultSetNode((RowResultSetNode) node);
			break;
		}

		return node;
	}

	@Override
	public boolean visitChildrenFirst(Visitable node) {
		return false;
	}
}
