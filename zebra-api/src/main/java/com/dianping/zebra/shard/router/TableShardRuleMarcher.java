package com.dianping.zebra.shard.router;

import java.util.List;

import com.dianping.zebra.shard.exception.ShardRouterException;
import com.dianping.zebra.shard.parser.MySQLParseResult;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.TableShardRule;

public interface TableShardRuleMarcher {
	TableShardRule march(RouterRule rr, MySQLParseResult result, List<Object> params) throws ShardRouterException;
}
