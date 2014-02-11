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
package com.dianping.zebra.parser.tableObject;

import com.dianping.zebra.parser.valueobject.Value;

/**
 * 用于实现强制索引和强制忽视索引的接口
 * 
 */
public interface TableName extends Value {
	public void setAlias(String alias);
	public String getTableName();
	public String getAlias();

}