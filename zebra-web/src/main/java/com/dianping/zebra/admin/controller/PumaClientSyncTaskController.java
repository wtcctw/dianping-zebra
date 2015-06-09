package com.dianping.zebra.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dianping.zebra.admin.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.shard.router.DataSourceRouterFactory;
import com.dianping.zebra.shard.router.LionDataSourceRouterFactory;
import com.dianping.zebra.shard.router.ShardRouter;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.TableShardRule;

@Controller
@RequestMapping(value = "/syncTask")
public class PumaClientSyncTaskController extends BasicController {

	@Autowired
	private PumaClientSyncTaskMapper dao;
	
	public Object getExecutePlan(String ruleName){
		DataSourceRouterFactory routerFactory = new LionDataSourceRouterFactory(ruleName);
		ShardRouter router = routerFactory.getRouter();
		router.init();
		
		Map<String, List<PumaClientSyncTaskEntity>> result = new HashMap<String, List<PumaClientSyncTaskEntity>>();
		RouterRule routerRule = router.getRouterRule();
		
		for(Entry<String, TableShardRule> entry : routerRule.getTableShardRules().entrySet()){
			
			
			
			
			
			
		}
		
		
		
		
		return null;
	}
}
