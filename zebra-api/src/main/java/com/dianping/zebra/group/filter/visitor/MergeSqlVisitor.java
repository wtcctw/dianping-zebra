package com.dianping.zebra.group.filter.visitor;

import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dozer on 10/8/14.
 */
public class MergeSqlVisitor implements Visitor {
	public final AtomicInteger parameterNodeIndex = new AtomicInteger();

	public void cursorNode(CursorNode node) throws StandardException {
		visit(node.getResultSetNode());
	}

	public void selectNode(SelectNode node) throws StandardException {
		visit(node.getWhereClause());
	}

	public void andNode(AndNode node) throws StandardException {
		visit(node.getLeftOperand());
		visit(node.getRightOperand());
	}

	public void orNode(OrNode node) throws StandardException {
		visit(node.getLeftOperand());
		visit(node.getRightOperand());
	}

	public void notNode(NotNode node) throws StandardException {
		visit(node.getOperand());
	}

	public void inListOperatorNode(InListOperatorNode node) throws StandardException {
		ValueNodeList list = node.getRightOperandList().getNodeList();
		if (list.size() > 0) {
			list.clear();
			list.add(getNewParameterNode());
		}
	}

	public void binaryOperatorNode(BinaryOperatorNode node) throws StandardException {
		if (node.getLeftOperand().getNodeType() != NodeTypes.COLUMN_REFERENCE) {
			node.setLeftOperand(getNewParameterNode());
		}
		if (node.getRightOperand().getNodeType() != NodeTypes.COLUMN_REFERENCE) {
			node.setRightOperand(getNewParameterNode());
		}
	}

	private ParameterNode getNewParameterNode() {
		ParameterNode newNode = new ParameterNode();
		newNode.setNodeType(NodeTypes.PARAMETER_NODE);
		newNode.init(parameterNodeIndex.getAndIncrement(), null);
		return newNode;
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
		case NodeTypes.BINARY_EQUALS_OPERATOR_NODE:
		case NodeTypes.BINARY_GREATER_EQUALS_OPERATOR_NODE:
		case NodeTypes.BINARY_GREATER_THAN_OPERATOR_NODE:
		case NodeTypes.BINARY_LESS_EQUALS_OPERATOR_NODE:
		case NodeTypes.BINARY_LESS_THAN_OPERATOR_NODE:
		case NodeTypes.BINARY_NOT_EQUALS_OPERATOR_NODE:
			binaryOperatorNode((BinaryOperatorNode) node);
			break;
		}
		return node;
	}

	@Override
	public boolean visitChildrenFirst(Visitable node) {
		return false;
	}

	@Override
	public boolean stopTraversal() {
		return false;
	}

	@Override
	public boolean skipChildren(Visitable node) throws StandardException {
		return false;
	}
}
