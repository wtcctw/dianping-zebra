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
import java.util.Set;

import com.dianping.zebra.shard.exception.ShardRouterException;
import com.dianping.zebra.shard.parser.SQLHint;
import com.dianping.zebra.shard.router.rule.ShardEvalContext.ColumnValue;
import com.dianping.zebra.shard.router.rule.dimension.DimensionRule;
import com.dianping.zebra.shard.util.ShardColumnValueUtil;
import com.dianping.zebra.util.SqlType;

public class TableShardRule {

	private final String tableName;

	private String generatedPk;

	private List<DimensionRule> rules = new ArrayList<DimensionRule>();

	public TableShardRule(String tableName) {
		this.tableName = tableName;
	}

	public ShardEvalResult eval(ShardEvalContext ctx) {
		SqlType type = ctx.getParseResult().getType();

		// force dimension from hint
		SQLHint sqlhint = ctx.getParseResult().getRouterContext().getSqlhint();
		if (sqlhint != null & sqlhint.getShardColumn() != null) {
			DimensionRule dimensionRule = findDimensionRule(sqlhint.getShardColumn());
			ShardEvalResult result = evalDimension(ctx, type, dimensionRule);

			if (result != null) {
				return result;
			}
		}

		for (DimensionRule rule : rules) {
			ShardEvalResult result = evalDimension(ctx, type, rule);

			if (result != null) {
				return result;
			}
		}

		if (type != SqlType.INSERT) {
			ShardEvalResult result = new ShardEvalResult();
			for (DimensionRule rule : rules) {
				if (rule.isMaster()) {
					result.setDbAndTables(rule.getAllDBAndTables());
					break;
				}
			}

			return result;
		} else {
			throw new ShardRouterException("Cannot find any shard columns in your insert sql.");
		}
	}

	private DimensionRule findDimensionRule(String shardColumn) {
		for (DimensionRule rule : rules) {
			Set<String> shardColumns = rule.getShardColumns();

			if (shardColumns.size() == 1 && shardColumns.contains(shardColumn)) {
				return rule;
			}
		}

		return null;
	}

	private ShardEvalResult evalDimension(ShardEvalContext ctx, SqlType type, DimensionRule rule) {
		List<ColumnValue> columnValues = ShardColumnValueUtil.eval(ctx, rule.getShardColumns());
		ctx.setColumnValues(columnValues);

		if (type == SqlType.SELECT) {
			if (columnValues != null && columnValues.size() > 0) {
				return rule.eval(ctx);
			}
		} else if (type == SqlType.INSERT) {
			if (rule.isMaster()) {
				if (columnValues != null && columnValues.size() > 0) {
					return rule.eval(ctx);
				}
			}
		} else if (type == SqlType.UPDATE || type == SqlType.DELETE) {
			if (rule.isMaster()) {
				if (columnValues != null && columnValues.size() > 0) {
					return rule.eval(ctx);
				}
			}
		} else {
			throw new ShardRouterException("Unsupported Sql type");
		}

		return null;
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

	public String getGeneratedPk() {
		return generatedPk;
	}

	public void setGeneratedPk(String generatedPk) {
		this.generatedPk = generatedPk;
	}
}
