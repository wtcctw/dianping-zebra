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
package com.dianping.zebra.shard.router.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dianping.zebra.shard.exception.ShardRouterException;
import com.dianping.zebra.shard.parser.MySQLParseResult;
import com.dianping.zebra.util.SqlType;

public class TableShardRule {

	private final String tableName;

	private List<DimensionRule> rules = new ArrayList<DimensionRule>();

	private String generatedPK;

	public TableShardRule(String tableName) {
		this.tableName = tableName;
	}

	public List<DimensionRule> getDimensionRules() {
		return this.rules;
	}

	public void addDimensionRules(List<DimensionRule> dimensionRules) {
		if (dimensionRules != null) {
			this.rules.addAll(dimensionRules);
		}
	}

	public void addDimensionRule(DimensionRule dimensionRule) {
		if (dimensionRule != null) {
			this.rules.add(dimensionRule);
		}
	}

	public String getTableName() {
		return tableName;
	}

	public ShardMatchResult eval(MySQLParseResult parseResult, List<Object> params) {
		ShardMatchContext matchContext = new ShardMatchContext(parseResult, params);

		for (DimensionRule rule : rules) {
			if (!rule.match(matchContext)) {
				break;
			}
		}

		afterMatch(matchContext);

		return matchContext.getMatchResult();
	}

	private void afterMatch(ShardMatchContext matchContext) {
		MySQLParseResult parseResult = matchContext.getParseResult();
		ShardMatchResult matchResult = matchContext.getMatchResult();
		boolean dbAndTbsIsEmpty = matchResult.isDbAndTbsEmpty();

		if ((parseResult.getType() == SqlType.INSERT) && dbAndTbsIsEmpty) {
			throw new ShardRouterException("Insert clause[" + parseResult + "] can't be routed.");
		}

		Map<String, Set<String>> potentialDBAndTbs = matchResult.getPotentialDBAndTbs();

		if (potentialDBAndTbs != null && !potentialDBAndTbs.isEmpty()) {
			if (!matchResult.isDbAndTablesSetted()) {
				matchResult.addDBAndTables(potentialDBAndTbs);
			}
		}
	}

	public void setGeneratedPK(String generatedPK) {
		this.generatedPK = generatedPK;
	}

	public String getGeneratedPK() {
		return generatedPK;
	}

}
