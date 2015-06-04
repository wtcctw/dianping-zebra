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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.shard.router.rule.DimensionRule;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.TableShardRule;

/**
 * @author hao.zhu <br>
 *
 */

public class DataSourceRepository {

	private static Map<String, DataSource> dataSources = new HashMap<String, DataSource>();

	private DataSourceRepository(){
	}

	public static DataSource getDataSource(String dsName) {
		return dataSources.get(dsName.toLowerCase());
	}

	public static void init(Map<String, DataSource> dataSourcePool) {
		for (Entry<String, DataSource> dataSourceEntry : dataSourcePool.entrySet()) {
			String dbIndex = dataSourceEntry.getKey();
			DataSource dataSource = dataSourceEntry.getValue();

			dataSources.put(dbIndex, dataSource);
		}
	}

	public static void init(RouterRule routerRule) {
		for (TableShardRule shardRule : routerRule.getTableShardRules().values()) {
			for (DimensionRule dimensionRule : shardRule.getDimensionRules()) {
				for (String jdbcRef : dimensionRule.getAllDBAndTables().keySet()) {
					if (!dataSources.containsKey(jdbcRef)) {
						GroupDataSource groupDataSource = new GroupDataSource(jdbcRef);
						groupDataSource.setForceWriteOnLogin(false); // HACK turn off
						groupDataSource.init();

						dataSources.put(jdbcRef, groupDataSource);
					}
				}
			}
		}
	}
}