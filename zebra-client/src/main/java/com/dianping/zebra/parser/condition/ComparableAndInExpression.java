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

import java.util.List;
import java.util.Set;

public interface ComparableAndInExpression extends Expression{

	Set<Comparative> eval(Set<String> tableAliases, String column, List<Object> params);
	
}
