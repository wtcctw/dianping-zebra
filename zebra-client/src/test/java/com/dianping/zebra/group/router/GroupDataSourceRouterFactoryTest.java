package com.dianping.zebra.group.router;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;

public class GroupDataSourceRouterFactoryTest {

	private DataSourceConfigManager dataSourceConfigManager;

	private DataSourceRouter dataSourceRouter;

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
		String readSql = "select * from a";
		RouterContext routerInfo = new RouterContext(readSql);
		RounterTarget target = dataSourceRouter.select(routerInfo);

		isInRead(target);
	}

	@Test
	public void testWriteSelect() {
		String writeSql = "update a set xx=xx";
		RouterContext routerInfo = new RouterContext(writeSql);
		RounterTarget target = dataSourceRouter.select(routerInfo);

		isInWrite(target);
	}

	@Test
	public void testExcludeSelect() {
		String writeSql = "update a set xx=xx";
		RouterContext routerInfo = new RouterContext(writeSql);
		RounterTarget target = dataSourceRouter.select(routerInfo);
		isInWrite(target);

		Set<RounterTarget> excludeTarget = new HashSet<RounterTarget>();
		excludeTarget.add(target);
		routerInfo = new RouterContext(excludeTarget);
		Assert.assertNotNull(dataSourceRouter.select(routerInfo));

	}

	private void isInRead(RounterTarget target) {
		System.out.println(target);
		Assert.assertNotSame("db1", target.getId());
	}

	private void isInWrite(RounterTarget target) {
		System.out.println(target);
		Assert.assertEquals("db1", target.getId());
	}

}
