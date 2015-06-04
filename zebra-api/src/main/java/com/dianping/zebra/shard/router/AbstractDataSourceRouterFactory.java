package com.dianping.zebra.shard.router;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dianping.zebra.shard.config.ExceptionConfig;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.dianping.zebra.shard.router.rule.DimensionRule;
import com.dianping.zebra.shard.router.rule.DimensionRuleImpl;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.TableShardRule;
import com.dianping.zebra.shard.router.rule.WhitelistDimensionRule;

public abstract class AbstractDataSourceRouterFactory implements DataSourceRouterFactory {

	public static RouterRule build(RouterRuleConfig routerConfig) {
		RouterRule routerRule = new RouterRule();
		Map<String, TableShardRule> tableShardRules = new HashMap<String, TableShardRule>();
		for (TableShardRuleConfig ruleConfig : routerConfig.getTableShardConfigs()) {
			List<TableShardDimensionConfig> dimensionConfigs = ruleConfig.getDimensionConfigs();
			if (dimensionConfigs != null && !dimensionConfigs.isEmpty()) {
				TableShardRule shardRule = new TableShardRule(ruleConfig.getTableName());
				shardRule.setGeneratedPK(ruleConfig.getGeneratedPK());
				arrangeDimensionConfigs(dimensionConfigs);
				for (TableShardDimensionConfig dimensionConfig : dimensionConfigs) {
					shardRule.addDimensionRule(buildDimensionRule(dimensionConfig));
				}
				tableShardRules.put(ruleConfig.getTableName(), shardRule);
			}
		}
		routerRule.setTableShardRules(tableShardRules);
		return routerRule;
	}

	private static void arrangeDimensionConfigs(List<TableShardDimensionConfig> dimensionConfigs) {
		if (dimensionConfigs.size() == 1) {
			dimensionConfigs.get(0).setMaster(true);
		}
		Collections.sort(dimensionConfigs, new Comparator<TableShardDimensionConfig>() {
			@Override
			public int compare(TableShardDimensionConfig o1, TableShardDimensionConfig o2) {
				return Boolean.valueOf(o2.isMaster()).compareTo(Boolean.valueOf(o1.isMaster()));
			}
		});
	}

	private static DimensionRule buildDimensionRule(TableShardDimensionConfig dimensionConfig) {
		DimensionRuleImpl rule = new DimensionRuleImpl();
		rule.setWhiteListRules(buildWhitelistDimensionRules(dimensionConfig.getExceptions(), dimensionConfig.isMaster()));
		rule.init(dimensionConfig);
		return rule;
	}

	private static List<DimensionRule> buildWhitelistDimensionRules(List<ExceptionConfig> exceptions, boolean isMaster) {
		if (exceptions == null || exceptions.isEmpty()) {
			return Collections.emptyList();
		}
		List<DimensionRule> rules = new ArrayList<DimensionRule>();
		for (ExceptionConfig exception : exceptions) {
			WhitelistDimensionRule rule = new WhitelistDimensionRule();
			rule.init(exception);
			rule.setMaster(isMaster);
			rules.add(rule);
		}
		return rules;
	}
}
