/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-15
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
package com.dianping.zebra.shard.router;

import javax.sql.DataSource;

import java.util.*;
import java.util.Map.Entry;

/**
 * local configuration based datasource repository 以后可以考虑支持从Zookeeper那边读取DataSource的配置构建Repository
 * 
 * @author danson.liu
 * @author hao.zhu <br>
 *         modified at 20150203 <br>
 *         delete read/write split & weighted dataSources since these features are implemented in GroupDataSource
 *
 */

public class LocalDataSourceRepository{

	private static Map<String, DataSource> dataSources = new HashMap<String, DataSource>();

	public static void init(Map<String, DataSource> dataSourcePool){
		for (Entry<String, DataSource> dataSourceEntry : dataSourcePool.entrySet()) {
			String dbIndex = dataSourceEntry.getKey();
			DataSource dataSource = dataSourceEntry.getValue();

			dataSources.put(dbIndex, dataSource);
		}
	}

	public static DataSource getDataSource(String dsName) {
		return dataSources.get(dsName.toLowerCase());
	}
}