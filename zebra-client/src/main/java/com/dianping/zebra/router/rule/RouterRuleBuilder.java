/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-14
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
package com.dianping.zebra.router.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dianping.zebra.router.config.ExceptionConfig;
import com.dianping.zebra.router.config.RouterRuleConfig;
import com.dianping.zebra.router.config.TableShardDimensionConfig;
import com.dianping.zebra.router.config.TableShardRuleConfig;

/**
 * @author danson.liu
 *
 */
public class RouterRuleBuilder {

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
//					String dimensionId = ruleConfig.getTableName() + index++;
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
				return new Boolean(o2.isMaster()).compareTo(new Boolean(o1.isMaster()));
			}
		});
	}

	private static DimensionRule buildDimensionRule(TableShardDimensionConfig dimensionConfig) {
		DimensionRuleImpl rule = new DimensionRuleImpl();
		rule.setWhiteListRules(buildWhitelistDimensionRules(
				dimensionConfig.getExceptions(), 
				dimensionConfig.isMaster()
		));
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
