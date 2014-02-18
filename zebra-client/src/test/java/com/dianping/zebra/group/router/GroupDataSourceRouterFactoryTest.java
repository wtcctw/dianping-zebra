package com.dianping.zebra.group.router;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.group.config.GroupConfigManager;
import com.dianping.zebra.group.config.GroupConfigManagerFactory;

public class GroupDataSourceRouterFactoryTest {

	private GroupConfigManager configManager;

	@Before
	public void init() {
		String resourceId = "datasources.xml";
		String configManagerType = "type";
		this.configManager = GroupConfigManagerFactory.getConfigManger(configManagerType, resourceId);
	}

	@Test
	public void testReadSelect() {
		GroupDataSourceRouter dataSourceRouter = GroupDataSourceRouterFactory.getDataSourceRouter(configManager);

		String readSql = "select * from a";
		GroupDataSourceRouterInfo routerInfo = new GroupDataSourceRouterInfo(readSql);
		GroupDataSourceTarget target = dataSourceRouter.select(routerInfo);

		isRead(target);
	}

	@Test
	public void testWriteSelect() {
		GroupDataSourceRouter dataSourceRouter = GroupDataSourceRouterFactory.getDataSourceRouter(configManager);

		String writeSql = "update a set xx=xx";
		GroupDataSourceRouterInfo routerInfo = new GroupDataSourceRouterInfo(writeSql);
		GroupDataSourceTarget target = dataSourceRouter.select(routerInfo);

		isWrite(target);
	}

	private void isRead(GroupDataSourceTarget target) {
		System.out.println(target);
		Assert.assertEquals("db2", target.getId());
		Assert.assertEquals(true, target.isReadOnly());
	}

	private void isWrite(GroupDataSourceTarget target) {
		System.out.println(target);
		Assert.assertEquals("db1", target.getId());
		Assert.assertEquals(false, target.isReadOnly());
	}

}
