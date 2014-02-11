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
package com.dianping.zebra.parser;

import com.dianping.zebra.parser.condition.ExpressionGroup;
import com.dianping.zebra.parser.condition.TwoArgsExpression;
import com.dianping.zebra.parser.sqlParser.Delete;
import com.dianping.zebra.parser.sqlParser.Select;
import com.dianping.zebra.parser.tableObject.imp.TableNameSubQueryImp;

/**
 *
 * @author qing.gu
 */
public class Constant {
	
	private final static Class<?>[] HAS_TOSTRING = {ExpressionGroup.class, TwoArgsExpression.class, 
					Select.class, Delete.class, TableNameSubQueryImp.class};
	public static boolean useToString(Object obj) {
		for(Class<?> clazz : HAS_TOSTRING) {
			if(clazz.isInstance(obj)) {
				return true;
			}
		}
		return false;
	}
	

}
