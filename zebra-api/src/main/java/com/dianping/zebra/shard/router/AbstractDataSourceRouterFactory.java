package com.dianping.zebra.shard.router;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;

public abstract class AbstractDataSourceRouterFactory implements DataSourceRouterFactory {
	
	protected Map<String, DataSource> getDataSourcePoolFromConfig(RouterRuleConfig config) {
		Map<String, DataSource> pool = new HashMap<String, DataSource>();
		for (TableShardRuleConfig table : config.getTableShardConfigs()) {
			for (TableShardDimensionConfig dimension : table.getDimensionConfigs()) {
				String[] jdbcRefs = dimension.getDbIndexes().split(",");
				for (String jdbcRef : jdbcRefs) {
					if(!pool.containsKey(jdbcRef)){
						GroupDataSource groupDataSource = new GroupDataSource(jdbcRef);
						
						groupDataSource.setForceWriteOnLogin(false); // HACK turn off
						groupDataSource.init();
						pool.put(jdbcRef, groupDataSource);
					}
				}
			}
		}

		return pool;
	}
}
