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
package com.dianping.zebra.parser.util;

import java.util.List;

import com.dianping.zebra.parser.Constant;
import com.dianping.zebra.parser.valueobject.Value;

public class Utils {
	private final static Object[] ARRAYOBJ = new Object[] {};


	public static void appendSQL(Object obj, StringBuilder sb) {
		if (obj instanceof Value) {
			((Value) obj).appendSQL(sb);
		} else if(obj==null){
			throw new RuntimeException("expression中的值不能为null,如果想使用null请使用DBFunctions提供的NULL");
		} else if(obj instanceof String) {
			sb.append("'").append(obj).append("'");
		} else {
			sb.append(obj);
		}
	}

	
	/**
	 * 处理内联ListValueObject
	 * 
	 * @param obj
	 * @param sb
	 */
	@SuppressWarnings("unchecked")
	public static void appendSQLList(Object obj, StringBuilder sb) {

		if (obj instanceof List) {
			boolean splider = false;
			for (Object innerObj : (List) obj) {

				if (splider) {
					sb.append(",");
				} else {
					splider = true;
				}
				appendSQLList(innerObj, sb);
			}
		} else if (obj != null
				&& ARRAYOBJ.getClass().isAssignableFrom(obj.getClass())) {
			boolean splider = false;
			for (Object innerObj : (Object[]) obj) {
				if (splider) {
					sb.append(",");
				} else {
					splider = true;
				}
				appendSQL(innerObj, sb);
			}
		} else {
			appendSQL(obj, sb);
		}
	}



	@SuppressWarnings("unchecked")
	public static void appendParams(Object obj, List<Object> params) {
		if (obj instanceof Value) {
			((Value) obj).appendParams(params);
		} else if (obj instanceof List) {
			for (Object innerObj : (List) obj) {
				appendParams(innerObj, params);
			}
		} else if (obj != null
				&& ARRAYOBJ.getClass().isAssignableFrom(obj.getClass())) {
			for (Object innerObj : (Object[]) obj) {
				appendParams(innerObj, params);
			}
		} else {
			params.add(obj);
		}

	}
	//
	// public static String getEmbracedClassName(String simplePrimitiveName) {
	// String temp=(String) simpleNameToEmbraceClassNameMap
	// .get(simplePrimitiveName);
	// if (temp != null) {
	// simplePrimitiveName = temp;
	// }
	// return simplePrimitiveName;
	// }
	
	public static void toString(Object obj, StringBuilder sb) {
		if (obj instanceof Value) {
			if(Constant.useToString(obj)) {
				sb.append(obj.toString());
			} else {
				((Value) obj).appendSQL(sb);
			}
		} else if(obj==null){
			throw new RuntimeException("expression中的值不能为null,如果想使用null请使用DBFunctions提供的NULL");
		} else if(obj instanceof String) {
			sb.append("'").append(obj).append("'");
		} else {
			sb.append(obj);
		}
	}
	
	public static void listToString(Object obj, StringBuilder sb) {
		if (obj instanceof List) {
			boolean splider = false;
			for (Object innerObj : (List) obj) {

				if (splider) {
					sb.append(",");
				} else {
					splider = true;
				}
				listToString(innerObj, sb);
			}
		} else if (obj != null
				&& ARRAYOBJ.getClass().isAssignableFrom(obj.getClass())) {
			boolean splider = false;
			for (Object innerObj : (Object[]) obj) {
				if (splider) {
					sb.append(",");
				} else {
					splider = true;
				}
				toString(innerObj, sb);
			}
		} else {
			toString(obj, sb);
		}
	}


}
