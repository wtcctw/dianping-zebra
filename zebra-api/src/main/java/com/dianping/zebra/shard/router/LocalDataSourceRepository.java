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

public class LocalDataSourceRepository implements DataSourceRepository {

	private Map<String, NamedDataSource> dataSources = new HashMap<String, NamedDataSource>();

	public LocalDataSourceRepository(Map<String, DataSource> dataSourcePool) {
		for (Entry<String, DataSource> dataSourceEntry : dataSourcePool.entrySet()) {
			String dbIndex = dataSourceEntry.getKey();
			DataSource dataSource = dataSourceEntry.getValue();

			registerDataSource(dbIndex, dataSource);
		}
	}

	private void registerDataSource(String name, DataSource dataSource) {
		dataSources.put(name.toLowerCase(), new NamedDataSource(name, dataSource));
	}

	@Override
	public NamedDataSource getDataSource(String dsName) {
		return dataSources.get(dsName.toLowerCase());
	}
}

/**
 * 返回带identity标记的DataSource，方便JDBC层在该Datasource级别执行多条SQL语句时复用Connection, 针对一条SQL被拆成多个表，而其中有多个表在同一个DataSource上，那么对于最上层的一次JDBC API调用
 * 提供底层的Connection复用
 * 
 * @author danson.liu
 *
 */
class NamedDataSource {
	String identity;

	DataSource dataSource;

	public NamedDataSource(String identity, DataSource dataSource) {
		this.identity = identity;
		this.dataSource = dataSource;
	}
}