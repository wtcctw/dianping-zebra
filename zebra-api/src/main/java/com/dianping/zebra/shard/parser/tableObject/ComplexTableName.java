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
package com.dianping.zebra.shard.parser.tableObject;


/**
 * 用于包装TableName,实现了一些StringTableName无法实现的功能。
 * 可以支持添加强制索引列，和添加强制忽视索引列。
 *
 */
public interface ComplexTableName {

	public abstract void addIgnoredIndex(String ignoredIndexStr);

	public abstract void addIndexColumn(String indexName) ;

	public abstract void setTableName(String tableName);

}