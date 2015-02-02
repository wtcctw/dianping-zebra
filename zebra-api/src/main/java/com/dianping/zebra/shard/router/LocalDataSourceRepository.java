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
 * local configuration based datasource repository
 * 以后可以考虑支持从Zookeeper那边读取DataSource的配置构建Repository
 * @author danson.liu
 *
 */

//todo: 这里需要大改，改成 SpringDataSourceRespository ??
public class LocalDataSourceRepository implements DataSourceRepository {
	
	public static final String WRITE_DB_INDEX_SUFFIX = "_W";
	public static final String READ_DB_INDEX_SUFFIX = "_R";
	
	private Map<String, List<NamedDataSource>> dataSources = new HashMap<String, List<NamedDataSource>>();
	
	private Random random = new Random();
	
	public LocalDataSourceRepository(Map<String, Object> dataSourcePool) {
		constructDataSourceMap(dataSourcePool);
	}
	
	private void constructDataSourceMap(Map<String, Object> dataSourcePool) {
		for (Entry<String, Object> dataSourceEntry : dataSourcePool.entrySet()) {
			String dbIndex = dataSourceEntry.getKey().toUpperCase();
			Object dataSource = dataSourceEntry.getValue();
			if (!dbIndex.endsWith(WRITE_DB_INDEX_SUFFIX) && !dbIndex.endsWith(READ_DB_INDEX_SUFFIX)) {
				registerDataSource(dbIndex + WRITE_DB_INDEX_SUFFIX, dataSource, null);
				registerDataSource(dbIndex + READ_DB_INDEX_SUFFIX, dataSource, null);
			} else {
				registerDataSource(dbIndex, dataSource, null);
			}
		}
	}
	
	private void registerDataSource(String name, Object dataSource, String alias) {
		//暂时都是weight=1，或者自己在配置List时，同一个Datasource多放几次，以确定权重
		if (!dataSources.containsKey(name)) {
			dataSources.put(name, new ArrayList<NamedDataSource>());
		}
		List<NamedDataSource> dsList = dataSources.get(name);
		String identity = alias != null ? alias : name;
		if (dataSource instanceof DataSource) {
			addWeightedDataSource(dsList, identity, new WeightedDataSource((DataSource) dataSource));
		} else if (dataSource instanceof WeightedDataSource) {
			addWeightedDataSource(dsList, identity, (WeightedDataSource) dataSource);
		} else if (dataSource.getClass().isArray()) {
			for (int i = 0; i < ((Object[]) dataSource).length; i++) {
				registerDataSource(name, ((Object[]) dataSource)[i], name + "_" + i);
			}
		} else if (dataSource instanceof Collection<?>) {
			int i = 0;
			for (Object ds : (Collection<?>) dataSource) {
				registerDataSource(name, ds, name + "_" + i++);
			}
		} else {
			throw new IllegalStateException("Illegal datasource type, DataSource and WeightedDataSource can be allowed.");
		}
	}
	
	private void addWeightedDataSource(List<NamedDataSource> sources, String identity, WeightedDataSource weightedDS) {
		for (int i = 0; i < weightedDS.weight; i++) {
			sources.add(new NamedDataSource(identity, weightedDS.dataSource));
		}
	}

	@Override
	public NamedDataSource getReadDataSource(String dsName) {
		dsName = dsName.toUpperCase() + READ_DB_INDEX_SUFFIX;
		List<NamedDataSource> dbList = dataSources.get(dsName);
		return dbList.get(random.nextInt(dbList.size()));
	}

	@Override
	public NamedDataSource getWriteDataSource(String dsName) {
		dsName = dsName.toUpperCase() + WRITE_DB_INDEX_SUFFIX;
		List<NamedDataSource> dbList = dataSources.get(dsName);
		return dbList.get(random.nextInt(dbList.size()));
	}
	
	public static class WeightedDataSource {
		public static final int DEFAULT_WEIGHT = 1;
		DataSource dataSource;
		int weight = DEFAULT_WEIGHT;
		public WeightedDataSource(DataSource dataSource, int weight) {
			this.dataSource = dataSource;
			this.weight = weight;
		}
		public WeightedDataSource(DataSource dataSource) {
			this(dataSource, DEFAULT_WEIGHT);
		}
	}

}

/**
 * 返回带identity标记的DataSource，方便JDBC层在该Datasource级别执行多条SQL语句时复用Connection,
 * 针对一条SQL被拆成多个表，而其中有多个表在同一个DataSource上，那么对于最上层的一次JDBC API调用
 * 提供底层的Connection复用
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