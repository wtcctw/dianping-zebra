package com.dianping.zebra.shard.router;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dianping.zebra.shard.exception.ShardRouterException;
import com.dianping.zebra.shard.parser.MySQLParseResult;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.TableShardRule;

public class DefaultTableShardRuleMarcher implements TableShardRuleMarcher {

	@Override
	public TableShardRule march(RouterRule rr, MySQLParseResult result, List<Object> params)
			throws ShardRouterException {
		Map<String, TableShardRule> tableShardRules = rr.getTableShardRules();
		int matchedTimes = 0;
		TableShardRule matchedTableShardRule = null;

		Set<String> relatedTables = result.getRouterContext().getTableSets();
		for (String relatedTable : relatedTables) {
			TableShardRule tmp = tableShardRules.get(relatedTable);
			if (tmp != null) {
				matchedTableShardRule = tmp;
				matchedTimes++;
			}

			if (matchedTimes > 1) {
				throw new ShardRouterException("Matched more than one table shard rules is not supported now.");
			}
		}

		if (matchedTableShardRule == null) {
			throw new ShardRouterException("Cannot find any table shard rule for table " + relatedTables);
		}

		return matchedTableShardRule;
	}
}
