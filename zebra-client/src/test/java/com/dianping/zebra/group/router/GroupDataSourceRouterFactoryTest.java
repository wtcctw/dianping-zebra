package com.dianping.zebra.group.router;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.SystemConfigManager;
import com.dianping.zebra.group.config.SystemConfigManagerFactory;

public class GroupDataSourceRouterFactoryTest {

	private DataSourceConfigManager dataSourceConfigManager;

	private SystemConfigManager systemConfigManager;

	private GroupDataSourceRouter dataSourceRouter;

	@Before
	public void init() {
		String dataSourceResourceId = "sample.ds";
		String systemResourceId = "zebra.system";
		String configManagerType = "local";
		this.dataSourceConfigManager = DataSourceConfigManagerFactory.getConfigManager(configManagerType,
		      dataSourceResourceId);
		this.systemConfigManager = SystemConfigManagerFactory.getConfigManger(configManagerType, systemResourceId);
		this.dataSourceRouter = GroupDataSourceRouterFactory.getDataSourceRouter(dataSourceConfigManager,
		      systemConfigManager);
	}

	@Test
	public void testReadSelect() {
		String readSql = "select * from a";
		GroupDataSourceRouterInfo routerInfo = new GroupDataSourceRouterInfo(readSql);
		GroupDataSourceTarget target = dataSourceRouter.select(routerInfo);

		isRead(target);
	}

	@Test
	public void testWriteSelect() {
		String writeSql = "update a set xx=xx";
		GroupDataSourceRouterInfo routerInfo = new GroupDataSourceRouterInfo(writeSql);
		GroupDataSourceTarget target = dataSourceRouter.select(routerInfo);

		isWrite(target);
	}

	@Test
	public void testExcludeSelect() {
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
