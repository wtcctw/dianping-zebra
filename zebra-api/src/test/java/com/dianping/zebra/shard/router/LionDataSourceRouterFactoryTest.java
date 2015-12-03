package com.dianping.zebra.shard.router;

import org.junit.Test;

public class LionDataSourceRouterFactoryTest {

	@Test
	public void test(){
		LionDataSourceRouterFactory factory = new LionDataSourceRouterFactory("dppoiuser");
		
		ShardRouter router = factory.getRouter();
		
		RouterResult router2 = router.router("select * from Users where id = 1", null);
		
		for(RouterTarget rt : router2.getTargetedSqls()){
			System.out.println(rt.getDataSourceName());
			for(String sql : rt.getSqls()){
				System.out.println(sql);
			}
		}
	}
}
