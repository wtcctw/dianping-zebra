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
package com.dianping.zebra.parser.sqlParser.mySql;

import java.util.List;

import com.dianping.zebra.parser.sqlParser.DMLCommon;
import com.dianping.zebra.parser.util.Utils;
import com.dianping.zebra.parser.valueobject.Value;
import com.dianping.zebra.parser.valueobject.variable.BindVar;

public class RangeSelector implements Value {

	private MyWhereCondition where = null;

	public RangeSelector(MyWhereCondition where) {
		this.where = where;
	}

	public void appendParams(List<Object> params) {
		Object start = where.getStart();
		Object range = where.getRange();
		if (start != null) {
			Utils.appendParams(start, params);
		}
		if (range != null) {
			Utils.appendParams(range, params);
		}
	}

	public void appendSQL(StringBuilder sb) {
		Object start = where.getStart();
		Object range = where.getRange();
		if (range != null) {
			sb.append(" LIMIT ");
			if (start != null) {
				Utils.appendSQL(start, sb);
				sb.append(",");
			}
			if (range != null) {
				Utils.appendSQL(range, sb);
			}
		}
	}

	public int getSkip(List<Object> param) {
		Object st = where.getStart();
		if (st == null) {
			return DMLCommon.DEFAULT_SKIP_MAX;
		}
		if (st instanceof Integer) {
			return ((Integer) st).intValue() ;
		}
		if (st instanceof BindVar) {
			int index = ((BindVar) st).getIndex();
			Object obj = param.get(index);
			if (!(obj instanceof Integer)) {
				throw new IllegalArgumentException("绑定变量发生错误:当前的绑定变量是" + obj);
			}
			return (Integer) obj;
		}
		return DMLCommon.DEFAULT_SKIP_MAX;
	}

	public int getMax(List<Object> param) {

		Object range = where.getRange();
		Integer intg = null;
		if (range instanceof Integer) {
			intg = (Integer) range;
		} else if (range instanceof BindVar) {
			int index = ((BindVar) range).getIndex();
			Object obj = param.get(index);
			if (!(obj instanceof Integer)) {
				throw new IllegalArgumentException("绑定变量发生错误:当前的绑定变量是" + obj);
			}
			intg = (Integer) obj;
		} else {
			intg = DMLCommon.DEFAULT_SKIP_MAX;
		}

		return intg;
	}
}
