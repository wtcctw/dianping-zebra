package com.dianping.zebra.shard.router;

import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public abstract class AbstractDataSourceRouterFactory implements DataSourceRouterFactory {
    protected Map<String, DataSource> getDataSourcePoolFromConfig(RouterRuleConfig config) {
        Map<String, DataSource> pool = new HashMap<String, DataSource>();
        for (TableShardRuleConfig table : config.getTableShardConfigs()) {
            for (TableShardDimensionConfig dimension : table.getDimensionConfigs()) {
                String[] dsNames = dimension.getDbIndexes().split(",");
                for (String ds : dsNames) {
                    GroupDataSource groupDataSource = new GroupDataSource(ds);
                    groupDataSource.init();
                    pool.put(ds, groupDataSource);
                }
            }
        }

        return pool;
    }
}
