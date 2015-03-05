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
package com.dianping.zebra.shard.parser.condition;

import com.dianping.zebra.shard.parser.valueObject.RowJepVisitor;
import com.dianping.zebra.shard.parser.valueObject.Value;
public interface Expression extends Value{
	public void eval(RowJepVisitor visitor, boolean inAnd);
}
