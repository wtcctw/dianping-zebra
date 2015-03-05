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

import java.util.Arrays;
import java.util.List;

import com.dianping.zebra.shard.parser.util.Utils;

public class VarOperandsFunctionObject extends ValueObject {

	private VAR_OPERANDS_FUNC func;
	private List<Object> args;

	public VarOperandsFunctionObject(VAR_OPERANDS_FUNC func, Object ...args) {
		this.func = func;
		this.args = Arrays.asList(args);
	}

	private String getFuncName(VAR_OPERANDS_FUNC func) {
		switch (func) {
		case DECODE:
			return "decode";

		default:
			throw new RuntimeException("not supported func:" + func);
		}
	}

	public void appendSQL(StringBuilder sb) {
		switch (func) {
		case DECODE:
			sb.append(getFuncName(func));
			sb.append("(");
			boolean addComma = false;
			for(Object obj : args){
				if(addComma){
					sb.append(",");
				}
				else{
					addComma = true;
				}
				Utils.appendSQLList(obj, sb);
			}
			sb.append(")");
			break;
			
		default:
			throw new RuntimeException("not supported func:" + func);
		}
	}

	public ValueType getType() {
		return ValueType.VarOperandFunc;
	}

	public void appendParams(List<Object> params) {
		for(Object obj : args){
			Utils.appendParams(obj, params);
		}
	}
}
