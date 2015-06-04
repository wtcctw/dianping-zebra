package com.dianping.zebra.shard.router;

import com.dianping.zebra.config.ConfigService;
import com.dianping.zebra.config.LionKey;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.RouterRuleBuilder;
import com.google.gson.Gson;

public class DataSourceLionRouterFactory implements DataSourceRouterFactory {
	private final RouterRuleConfig routerConfig;

	public DataSourceLionRouterFactory(ConfigService configService, String ruleName) {
		this.routerConfig = new Gson().fromJson(configService.getProperty(LionKey.getShardConfigKey(ruleName)),
		      RouterRuleConfig.class);

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
}
