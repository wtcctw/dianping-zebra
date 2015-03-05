package com.dianping.zebra.shard.router;

import com.dianping.zebra.config.ConfigService;
import com.dianping.zebra.config.LionKey;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.RouterRuleBuilder;
import com.google.gson.Gson;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ConfigServiceDataSourceRouterFactory extends AbstractDataSourceRouterFactory {
    private final ConfigService configService;

    private final String ruleName;

    private final RouterRuleConfig routerConfig;

    public ConfigServiceDataSourceRouterFactory(ConfigService configService, String ruleName) {
        this.configService = configService;
        this.ruleName = ruleName;
        this.routerConfig = new Gson().fromJson(configService.getProperty(LionKey.getShardConfigKey(ruleName)), RouterRuleConfig.class);

        if (routerConfig.getTableShardConfigs() != null) {
            for (TableShardRuleConfig tableConfig : routerConfig.getTableShardConfigs()) {
                if (tableConfig.getDimensionConfigs() != null) {
                    for (TableShardDimensionConfig dimensionConfig : tableConfig.getDimensionConfigs()) {
                        dimensionConfig.setTableName(tableConfig.getTableName());
                    }
                }
            }
        }
    }

    @Override
    public DataSourceRouter getRouter() {
        DataSourceRouterImpl router = new DataSourceRouterImpl();
        RouterRule routerRule = RouterRuleBuilder.build(routerConfig);
        router.setRouterRule(routerRule);
        return router;
    }

    @Override
    public Map<String, DataSource> getDataSourcePool() {
        return getDataSourcePoolFromConfig(routerConfig);
    }
}
