package com.dianping.zebra.shard.router;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.dianping.zebra.shard.exception.SQLParseException;
import com.dianping.zebra.shard.exception.ShardRouterException;
import com.dianping.zebra.shard.router.RouterResult.RouterTarget;
import com.dianping.zebra.shard.router.builder.LionRouterBuilder;
import com.dianping.zebra.shard.router.rule.TableShardRule;
import com.dianping.zebra.shard.router.rule.dimension.DimensionRule;

public class LionDataSourceRouterFactoryTest {

	@Test
	public void test() throws ShardRouterException, SQLParseException {
		LionRouterBuilder factory = new LionRouterBuilder("dppoiuser");

		ShardRouter router = factory.build();

		RouterResult router2 = router.router("select * from Users where id = 1", null);

		for (RouterTarget rt : router2.getSqls()) {
			System.out.println(rt.getDataSourceName());
			for (String sql : rt.getSqls()) {
				System.out.println(sql);
			}
		}
	}

	@Test
	public void test1() {
		LionRouterBuilder factory = new LionRouterBuilder("unifiedorder");

		ShardRouter router = factory.build();
		Map<String, TableShardRule> tableShardRules = router.getRouterRule().getTableShardRules();

		for (Entry<String, TableShardRule> entry : tableShardRules.entrySet()) {
			TableShardRule rule = entry.getValue();
			System.out.println("Logical Table Name : " + entry.getKey());

			for (DimensionRule dimesion : rule.getDimensionRules()) {
				// 表明是主维度
				if (dimesion.isMaster()) {
					Map<String, Set<String>> allDBAndTables = dimesion.getAllDBAndTables();

					for (Entry<String, Set<String>> dbTableEntry : allDBAndTables.entrySet()) {
						System.out.println("Database name : " + dbTableEntry.getKey());
						System.out.println("Pysical Table Name : " + dbTableEntry.getValue());
					}
				}
			}
		}
	}
}
