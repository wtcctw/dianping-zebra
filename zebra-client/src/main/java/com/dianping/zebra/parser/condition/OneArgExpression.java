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
package com.dianping.zebra.parser.condition;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.dianping.zebra.parser.util.Utils;
import com.dianping.zebra.parser.valueobject.RowJepVisitor;

public class OneArgExpression implements ComparableAndInExpression {
	
	public Object getObj() {
		return obj;
	}

	public RELATION getRelation() {
		return relation;
	}

	private Object obj;
	
	private RELATION relation;
	
	public static enum RELATION {
		/**
		 * exp is null
		 */
		IS_NULL,
		/**
		 * exp is not null
		 */
		IS_NOT_NULL
	}
	
	
	public OneArgExpression(Object obj, RELATION relation){
		this.obj = obj;
		this.relation = relation;
	}
	
	private static String getReltaionString(RELATION relation) {
		switch (relation) {
		case IS_NULL:
			return " is null";
			
		case IS_NOT_NULL:
			return " is not null";
			
		default:
			throw new RuntimeException("no supported relation:" + relation);
		}
	}
	
	public void appendInfo(StringBuilder sb, List<Object> params) {

	}

	public void appendParams(List<Object> params) {
		Utils.appendParams(obj, params);
	}

	public void appendSQL(StringBuilder sb) {
		switch (relation) {
		case IS_NULL:
		case IS_NOT_NULL:
			Utils.appendSQLList(obj, sb);
			sb.append(getReltaionString(relation));
			break;
		
		default:
			throw new RuntimeException("not supported relation:" + relation);
		}
		
	}

	public void eval(RowJepVisitor visitor,boolean inAnd) {
//		ColumnObject col = null;
//		if (obj instanceof ColumnObject) {
//			col = (ColumnObject) obj;
//		}
//		
//		visitor.put(col.getColumnName(), new ComparableElement(
//			new UnknowValueObject(), inAnd, Comparative.Equivalent));
		
	}

	@Override
	public Set<Comparative> eval(Set<String> tableAliases, String column, List<Object> params) {
		return Collections.emptySet();
	}


}
