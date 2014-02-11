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
package com.dianping.zebra.parser.sqlParser;

import java.util.List;

import com.dianping.zebra.parser.util.Utils;
import com.dianping.zebra.parser.valueobject.ColumnObject;
import com.dianping.zebra.parser.valueobject.Value;

public class SetElement implements Value{
	public ColumnObject col;
	public Object value;
	public void appendParams(List<Object> params) {
		Utils.appendParams(col, params);
		Utils.appendParams(value, params);
		
	}
	public void appendSQL(StringBuilder sb) {
		Utils.appendSQLList(col, sb);
		sb.append("=");
		Utils.appendSQLList(value, sb);
	
	}
}
