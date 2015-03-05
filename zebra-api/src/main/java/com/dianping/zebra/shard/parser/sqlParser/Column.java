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
package com.dianping.zebra.shard.parser.sqlParser;

import com.dianping.zebra.shard.parser.valueObject.Value;

/**
 * select |column| from
 * @author qing.gu
 *
 */
public interface Column extends Value{
	public String getColumn() ;
	public String getTable() ;
	public String getAlias() ;

}
