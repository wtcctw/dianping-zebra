/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-6-16 $Id$
 * 
 * Copyright 2010 dianping.com. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.shard.parser.condition;

import java.util.ArrayList;
import java.util.List;

import com.dianping.zebra.shard.parser.Constant;
import com.dianping.zebra.shard.parser.condition.TwoArgsExpression.RELATION;
import com.dianping.zebra.shard.parser.util.Utils;
import com.dianping.zebra.shard.parser.valueObject.ColumnObject;
import com.dianping.zebra.shard.parser.valueObject.OraNestedTab;
import com.dianping.zebra.shard.parser.valueObject.RowJepVisitor;
import com.dianping.zebra.shard.parser.valueObject.Value;
import com.dianping.zebra.shard.parser.valueObject.ValueObject;


public  class ExpressionGroup implements Expression {


	protected boolean priorty;
	protected List<Expression> expressions = new ArrayList<Expression>();  

	public ExpressionGroup(Expression expression1, Expression expression2) {
		this.expressions.add(expression1);
		this.expressions.add(expression2);
	}
	
	public ExpressionGroup() {
	}
	
	protected String getConjunction(){
		return " and ";
	}
	
	
	public boolean isPriorty() {
		return priorty;
	}


	public void setPriorty(boolean priorty) {
		this.priorty = priorty;
	}


	public void addTwoArgsExpression(Object left, TwoArgsExpression.RELATION relation, Object right) {
		TwoArgsExpression expression = new TwoArgsExpression(left, relation, right);
		expressions.add(expression);
	}

	public void addOneArgExpression(Object obj, OneArgExpression.RELATION relation) {
		OneArgExpression expression = new OneArgExpression(obj, relation);
		expressions.add(expression);
	}

	
	public void addExpressionGroup(ExpressionGroup expGroup){
		expressions.add(expGroup);
	}
	
	public void addIsNull(Object obj){
		addOneArgExpression(obj, OneArgExpression.RELATION.IS_NULL);
	}
	
	public void addIsNotNull(Object obj){
		addOneArgExpression(obj, OneArgExpression.RELATION.IS_NOT_NULL);
	}

	public void addLike(Object arg1, Object arg2){
		addTwoArgsExpression(arg1, TwoArgsExpression.RELATION.LIKE, arg2);
	}

	/**
	 * 添加一个表达式，也就是<b>exp1</b> <b>relation</b> <b>exp2</b> 多个表达式之间以and连接
	 * 
	 * @param left
	 *            左表达式，值可以为以下的几种
	 *            <ul>
	 *            <li>
	 *            <b>基本类型：</b>java的基本数据类型以及其包装类，解释器会自动转换为key=?的形式并将该基本数据类型以setObject
	 *            ()的形式输入到perpareStatement中</li>
	 *            <li>
	 *            <b>List类型：</b>如果是List类型，则会自动将list中的每一项自动的进行分析，分别再转化为<b>基本类型
	 *            、List类型或ValueObject</b>进行处理，两个List中的对象之间，自动以","分隔</b></li>
	 *            <li>
	 *            <b>{@link ValueObject}
	 *            类型：</b>如果是ValueObject类型，则会按照ValueObject类型的相关规则进行处理</li>
	 *            </ul>
	 * @param relation
	 *            表达式之间的关系，具体请见{@link RELATION}
	 * @param right
	 *            右表达式，值可以为以下的几种
	 *            <ul>
	 *            <li>
	 *            <b>基本类型：</b>java的基本数据类型以及其包装类，解释器会自动转换为key=?的形式并将该基本数据类型以setObject
	 *            ()的形式输入到perpareStatement中</li>
	 *            <li>
	 *            <b>List类型：</b>如果是List类型，则会自动将list中的每一项自动的进行分析，分别再转化为<b>基本类型
	 *            、List类型或ValueObject</b>进行处理，两个List中的对象之间，自动以","分隔</b></li>
	 *            <li>
	 *            <b>{@link ValueObject}
	 *            类型：</b>如果是ValueObject类型，则会按照ValueObject类型的相关规则进行处理</li>
	 *            </ul>
	 */	
	public void addNotEqual(Object arg1, Object arg2){
		addTwoArgsExpression(arg1, TwoArgsExpression.RELATION.NOT_EQ, arg2);
	}

	public void addEqual(Object arg1, Object arg2){
		addTwoArgsExpression(arg1, TwoArgsExpression.RELATION.EQ, arg2);
	}

	public void addGreaterThan(Object arg1, Object arg2){
		addTwoArgsExpression(arg1, TwoArgsExpression.RELATION.GT, arg2);
	}

	public void addLessThan(Object arg1, Object arg2){
		addTwoArgsExpression(arg1, TwoArgsExpression.RELATION.LT, arg2);
	}

	public void addGreaterOrEqual(Object arg1, Object arg2){
		addTwoArgsExpression(arg1, TwoArgsExpression.RELATION.GT_EQ, arg2);
	}

	public void addLessOrEqual(Object arg1, Object arg2){
		addTwoArgsExpression(arg1, TwoArgsExpression.RELATION.LT_EQ, arg2);		
	}
	
	public void addIn(Object obj, List<?> args){
		addTwoArgsExpression(obj, TwoArgsExpression.RELATION.IN, args);
	}
	public void addIn(Object obj, OraNestedTab oraFunction){
		addTwoArgsExpression(obj, TwoArgsExpression.RELATION.IN, oraFunction);
	}
	public void addNotIn(Object obj, OraNestedTab oraFunction){
		addTwoArgsExpression(obj, TwoArgsExpression.RELATION.NOT_IN, oraFunction);
	}
	public void addNotIn(Object obj, List<?> args){
		addTwoArgsExpression(obj, TwoArgsExpression.RELATION.NOT_IN, args);
	}
	public void addNotLike(Object obj, Object arg2){
		addTwoArgsExpression(obj, TwoArgsExpression.RELATION.NOT_LIKE, arg2);
	}
	

	public Object getValue(String key) {
		for (Value expression : expressions) {
			if (expression instanceof TwoArgsExpression) {
				TwoArgsExpression expr = (TwoArgsExpression) expression;
				if (expr.getRelation() == TwoArgsExpression.RELATION.EQ) {
					Object left = expr.getLeft();
					Object right = expr.getRight();
					if ((left instanceof ColumnObject) && !(right instanceof ColumnObject)) {
						if (left.toString().equalsIgnoreCase(key)) {
							return right;
						}
					}
					if (!(left instanceof ColumnObject) && (right instanceof ColumnObject)) {
						if (right.toString().equalsIgnoreCase(key)) {
							return left;
						}
					}
				}
			}
		}

		return null;
	}

	public void appendParams(List<Object> params) {
		for(Value exp:expressions){
		Utils.appendParams(exp, params);
		}
	}

	public void appendSQL(StringBuilder sb) {
		boolean appendSplitter = false;
		for (Value expression : expressions) {
			if (appendSplitter) {
				sb.append(getConjunction());
			} else {
				appendSplitter = true;
			}
			sb.append("(");
			Utils.appendSQL(expression, sb);
			sb.append(")");
		}
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean appendSplitter = false;
		for (Value expression : expressions) {
			if (appendSplitter) {
				sb.append(getConjunction());
			} else {
				appendSplitter = true;
			}
			if(this instanceof OrExpressionGroup) {
				if(Constant.useToString(expression)) {
					sb.append(expression.toString());
				} else {
					Utils.appendSQL(expression, sb);
				}
			} else if(!Constant.useToString(expression)) {
				Utils.appendSQL(expression, sb);
			} else if(expression instanceof TwoArgsExpression) {
				sb.append(expression.toString());
			} else if(expression instanceof ExpressionGroup) {
				if(expression instanceof OrExpressionGroup) {
					sb.append('(');
					sb.append(expression.toString());
					sb.append(')');
				} else {
					sb.append(expression.toString());
				}
			} else {
				sb.append(expression.toString());
			}
		}
		return sb.toString();
	}

	public void eval(RowJepVisitor visitor, boolean inAnd) {
//		for(Expression e :expressions){
//			if(e instanceof OrExpressionGroup){
//				RowJepVisitor vis=new RowJepVisitor();
//				e.eval(vis, true);
//				Map<String, Comparative> mp= vis.getComparable();
//				for(Entry<String,Comparative> et:mp.entrySet()){
//					visitor.put(et.getKey(), new ComparableElement(et.getValue(),true,et.getValue().getComparison()));
//				}
//			}else{
//				e.eval(visitor, true);
//			}
//		}
		
	}

	public List<Expression> getExpressions() {
		return expressions;
	}
	
	public void addBetween(Object arg1, Object arg2){
		addTwoArgsExpression(arg1, TwoArgsExpression.RELATION.BETWEEN, arg2);
	}
	
	public void addNotBetween(Object arg1, Object arg2){
		addTwoArgsExpression(arg1, TwoArgsExpression.RELATION.NOT_BETWEEN, arg2);
	}

}
