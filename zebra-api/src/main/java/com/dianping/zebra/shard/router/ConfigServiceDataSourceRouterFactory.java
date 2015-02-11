package com.dianping.zebra.shard.router;

import com.dianping.zebra.Constants;
import com.dianping.zebra.config.ConfigService;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.RouterRuleBuilder;
import com.google.gson.Gson;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ConfigServiceDataSourceRouterFactory implements DataSourceRouterFactory {
    private final ConfigService configService;

    private final String ruleName;

    public ConfigServiceDataSourceRouterFactory(ConfigService configService, String ruleName) {
        this.configService = configService;
        this.ruleName = ruleName;
    }

    @Override
    public DataSourceRouter getRouter() {
        DataSourceRouterImpl router = new DataSourceRouterImpl();
        RouterRuleConfig routerConfig = new Gson().fromJson(configService.getProperty(getShardKey()), RouterRuleConfig.class);
        RouterRule routerRule = RouterRuleBuilder.build(routerConfig);
        router.setRouterRule(routerRule);
        return router;
    }

    private String getShardKey() {
        return String.format("%s.%s.%s", Constants.DEFAULT_SHARDING_PRFIX, ruleName, "shard");
    }
}
