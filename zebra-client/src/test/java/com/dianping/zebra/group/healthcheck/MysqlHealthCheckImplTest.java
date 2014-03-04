package com.dianping.zebra.group.healthcheck;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DefaultDataSourceConfigManager;
import com.dianping.zebra.group.config.LocalConfigService;
import com.dianping.zebra.group.config.SystemConfigManager;
import com.dianping.zebra.group.manager.GroupDataSourceManager;
import com.dianping.zebra.group.manager.GroupDataSourceManagerFactory;
import com.dianping.zebra.group.router.GroupDataSourceTarget;

public class MysqlHealthCheckImplTest {
	private DataSourceConfigManager dataSourceConfigManager;

	private SystemConfigManager systemConfigManager;

	private GroupDataSourceManager groupDataSourceManager;

	@Before
	public void init() {
		String resourceId = "sample.ds";
		this.dataSourceConfigManager = new DefaultDataSourceConfigManager(resourceId, new LocalConfigService(resourceId));
		this.groupDataSourceManager = GroupDataSourceManagerFactory.getGroupDataSourceManger(dataSourceConfigManager,
		      Constants.CONNECTION_POOL_TYPE_C3P0);
		this.dataSourceConfigManager.init();
	}

	@Test
	public void testReadNotifyException() {
		MysqlHealthCheckImpl mysqlhealthcheckimpl = new MysqlHealthCheckImpl(this.dataSourceConfigManager,
		      this.systemConfigManager, groupDataSourceManager);
		GroupDataSourceTarget dsTarget = new GroupDataSourceTarget("db1", 1, true);
		SQLException e = new SQLException();
		int i = mysqlhealthcheckimpl.getMaxErrorTimes() + 1;
		while (i > 0) {
			mysqlhealthcheckimpl.notifyException(dsTarget, e);
			i--;
		}
		Assert.assertEquals(this.dataSourceConfigManager.getAvailableDataSources().size(), 1);
		Assert.assertEquals(this.dataSourceConfigManager.getUnAvailableDataSources().size(), 1);
	}

	@Test
	public void testWriteNotifyException() {
		MysqlHealthCheckImpl mysqlhealthcheckimpl = new MysqlHealthCheckImpl(this.dataSourceConfigManager,
		      this.systemConfigManager, groupDataSourceManager);
		GroupDataSourceTarget dsTarget = new GroupDataSourceTarget("db1", 1, false);
		SQLException e = new SQLException();
		int i = mysqlhealthcheckimpl.getMaxErrorTimes() + 1;
		while (i > 0) {
			mysqlhealthcheckimpl.notifyException(dsTarget, e);
			i--;
		}
		Assert.assertEquals(this.dataSourceConfigManager.getAvailableDataSources().size(), 2);
		Assert.assertEquals(this.dataSourceConfigManager.getUnAvailableDataSources().size(), 0);
	}
}
