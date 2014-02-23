package com.dianping.zebra.group.router;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.group.config1.GroupConfigManager;
import com.dianping.zebra.group.config1.GroupConfigManagerFactory;

public class GroupDataSourceRouterFactoryTest {

	private GroupConfigManager configManager;

	@Before
	public void init() {
		String resourceId = "datasources.xml";
		String configManagerType = "local";
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

	@Test
	public void testExcludeSelect() {
		GroupDataSourceRouter dataSourceRouter = GroupDataSourceRouterFactory.getDataSourceRouter(configManager);

		String writeSql = "update a set xx=xx";
		GroupDataSourceRouterInfo routerInfo = new GroupDataSourceRouterInfo(writeSql);
		GroupDataSourceTarget target = dataSourceRouter.select(routerInfo);
		isWrite(target);

		Set<GroupDataSourceTarget> excludeTarget = new HashSet<GroupDataSourceTarget>();
		excludeTarget.add(target);
		Assert.assertNull(dataSourceRouter.select(routerInfo, excludeTarget));

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
