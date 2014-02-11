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
package com.dianping.zebra.parser.valueobject;

import java.util.Arrays;
import java.util.List;


public class OraNestedTab extends ValueObject{
	private String argListStr;
	private static final int NOROWNUM=-100;
	private String nestSelectionSQL="";
	private String nestCastType="";
	private int rowNumber=NOROWNUM;
	public OraNestedTab(Object[] argListObjs,ORA_FUN_TYPE functionType){
		this.init(getArgListString(argListObjs), NOROWNUM, functionType);
	}
	public OraNestedTab(Object[] argList, int rowNumber, ORA_FUN_TYPE functionType) {
		this.init(getArgListString(argList), rowNumber, functionType);
	}
	private String getArgListString(Object[] objs){
		return getArgListString(Arrays.asList(objs));
	}
	private String getArgListString(List<Object> obj){
		boolean appendSplitter = false;
		StringBuilder sb=new StringBuilder();
		for (Object columnName : obj) {
			if (appendSplitter) {
				sb.append(",");
			} else {
				appendSplitter = true;
			}
			sb.append(columnName);
		}
		return sb.toString();
	}
	public OraNestedTab(List<Object> argListObjs,ORA_FUN_TYPE functionType){
		this.init(getArgListString(argListObjs), NOROWNUM, functionType);
	}
	public OraNestedTab(List<Object> argList, int rowNumber, ORA_FUN_TYPE functionType) {
		this.init(getArgListString(argList), rowNumber, functionType);
	}
	public OraNestedTab(String argList,ORA_FUN_TYPE functionType){
		this.init(argList, NOROWNUM, functionType);
	}
	public OraNestedTab(String argList, int rowNumber, ORA_FUN_TYPE functionType) {
		this.init(argList, rowNumber, functionType);
	}
	
	private void init(String argListStr,int rowNumber,ORA_FUN_TYPE functionType){
		this.argListStr=argListStr;
		this.rowNumber=rowNumber;
		if(argListStr==null){
			throw new RuntimeException("请输入正确的argList");
		}
		switch (functionType) {
		case STR2VARLIST:
			nestSelectionSQL="STR2VARLIST";
			nestCastType="VARTABLETYPE";
			break;
		case STR2NUMLIST:
			nestSelectionSQL="STR2NUMLIST";
			nestCastType="NUMTABLETYPE";
			break;
		default:
			throw new RuntimeException("错误的OraFunction enum类型");
		}
	}
	public void appendParams(List<Object> params) {
		params.add(argListStr);
		if(rowNumber!=NOROWNUM){
		params.add(rowNumber);
		}
	}
	
	public void appendSQL(StringBuilder sb) {
		sb.append("select * from  table(cast(");
		sb.append(nestSelectionSQL);
		sb.append("(?) AS " );
		sb.append(nestCastType+"))");
		if(rowNumber > 0){
			sb.append(" WHERE rownum<=?");
		}
	}
	public ValueType getType() {
		return ValueType.OraNestFunc;
	}
}
