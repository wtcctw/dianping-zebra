package com.dianping.zebra.group.router;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;

public class GroupDataSourceRouterFactoryTest {

	private DataSourceConfigManager dataSourceConfigManager;

	private DataSourceRouter dataSourceRouter;

	private Map<String, Integer> counter = new HashMap<String, Integer>();

	@Before
	public void init() {
		String dataSourceResourceId = "sample.ds.v2";
		String configManagerType = "local";
		this.dataSourceConfigManager = DataSourceConfigManagerFactory.getConfigManager(configManagerType,
		      dataSourceResourceId);
		this.dataSourceRouter = DataSourceRouterFactory.getDataSourceRouter(dataSourceConfigManager);
	}

	@Test
	public void testReadSelect() {
		for (int i = 0; i < 1000; i++) {
			String readSql = "select * from a";
			RouterContext routerInfo = new RouterContext(readSql);
			RounterTarget target = dataSourceRouter.select(routerInfo);

			Integer integer = counter.get(target.getId());
			if (integer == null) {
				integer = 1;
			} else {
				integer++;
			}

			counter.put(target.getId(), integer);
		}

		for (Entry<String, Integer> entry : counter.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
}
