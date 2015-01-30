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
package com.dianping.zebra.shard.parser.valueObject;

import java.util.List;

import com.dianping.zebra.shard.parser.util.Utils;

public class TwoOperandsFunctionObject extends ValueObject {

	private TWO_OPERANDS_FUNC func;
	private Object arg1;
	private Object arg2;

	public TwoOperandsFunctionObject(TWO_OPERANDS_FUNC func, Object arg1,
			Object arg2) {
		this.func = func;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	private String getFuncName(TWO_OPERANDS_FUNC func) {
		switch (func) {
		case BITAND:
			return "bitand";

		case BITOR:
			return "bitor";
			
		case BITXOR_TB:
			return "bitxor_tb";

		case NVL:
			return "nvl";
			
		case CONCAT:
			return "concat";

		case ADD:
			return "+";

		case SUBTRACT:
			return "-";

		case MULTIPLY:
			return "*";

		case DIVIDE:
			return "/";
			
		case IFNULL:
			return "IFNULL";
		
		case MOD:
			return "%";

		default:
			throw new RuntimeException("not supported func:" + func);
		}
	}

	public void appendSQL(StringBuilder sb) {
		switch (func) {
		case BITAND:
		case BITOR:
		case BITXOR_TB:			
		case NVL:
		case IFNULL:
		case CONCAT:
			operationSignalBeforeArgsFunction(sb);
			break;
			
		case ADD:
		case SUBTRACT:
		case MULTIPLY:
		case DIVIDE:
			simpleOperation(sb);
			break;
			
		default:
			throw new RuntimeException("not supported func:" + func);
		}
	}

	private void operationSignalBeforeArgsFunction(StringBuilder sb) {
		sb.append(getFuncName(func));
		sb.append("(");
		Utils.appendSQLList(arg1, sb);
		sb.append(",");
		Utils.appendSQLList(arg2, sb);
		sb.append(")");
	}

	private void simpleOperation(StringBuilder sb) {
		sb.append("(");
		Utils.appendSQLList(arg1, sb);
		sb.append(getFuncName(func));
		Utils.appendSQLList(arg2, sb);
		sb.append(")");
	}

	public ValueType getType() {
		return ValueType.TwoOperandsFunc;
	}

	public void appendParams(List<Object> params) {
		Utils.appendParams(arg1, params);
		Utils.appendParams(arg2, params);
	}
	public String getNestedColName() {
		String ret=null;
		
		if(arg1 instanceof ColumnObject){
			ret=((ColumnObject)arg1).getColumnName();
		}
		if(arg2 instanceof ColumnObject){
			if(ret==null){
				ret=((ColumnObject)arg2).getColumnName();
			}else{
				throw new IllegalArgumentException("双参数函数中不能出现多个列名的函数");
			}
		}
		return ret;
	}
}
