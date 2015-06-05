package com.dianping.zebra.shard.router;

import com.dianping.zebra.config.LionConfigService;
import com.dianping.zebra.config.LionKey;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.google.gson.Gson;

public class LionDataSourceRouterFactory extends AbstractDataSourceRouterFactory implements DataSourceRouterFactory {
	private final RouterRuleConfig routerConfig;

	public LionDataSourceRouterFactory(String ruleName) {
		LionConfigService configService = LionConfigService.getInstance();
		
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
	public ShardRouter getRouter() {
		ShardRouterImpl router = new ShardRouterImpl();
		RouterRule routerRule = build(routerConfig);
		router.setRouterRule(routerRule);

		return router;
	}
}