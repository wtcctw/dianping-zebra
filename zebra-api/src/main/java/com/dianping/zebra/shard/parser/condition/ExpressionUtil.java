/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-21
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.shard.parser.condition;

import com.dianping.zebra.shard.parser.condition.TwoArgsExpression.RELATION;

/**
 * TODO Comment of ExpressionUtil
 * @author danson.liu
 *
 */
public class ExpressionUtil {

	public static int convertToComparison(RELATION relation) {
		int rel;
		switch (relation) {
		case EQ:
			rel = Comparative.Equivalent;
			break;
		case NOT_EQ:
			rel = Comparative.NotEquivalent;
			break;
		case GT:
			rel = Comparative.GreaterThan;
			break;
		case GT_EQ:
			rel = Comparative.GreaterThanOrEqual;
			break;
		case LT:
			rel = Comparative.LessThan;
			break;
		case LT_EQ:
			rel = Comparative.LessThanOrEqual;
			break;
		case BETWEEN:
			rel = Comparative.Between;
			break;
		default:
			rel = -1;
			break;
		}
		return rel;
	}

}
