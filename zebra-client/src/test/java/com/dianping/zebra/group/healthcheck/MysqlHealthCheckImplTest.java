package com.dianping.zebra.group.healthcheck;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.group.config1.GroupConfigManager;
import com.dianping.zebra.group.config1.LocalGroupConfigManager;
import com.dianping.zebra.group.router.GroupDataSourceTarget;

public class MysqlHealthCheckImplTest {
	private GroupConfigManager configManager;

	@Before
	public void init() {
		String resourceId = "datasources.xml";
		this.configManager = new LocalGroupConfigManager(resourceId);
		configManager.init();
	}
	
	@Test
	public void testReadNotifyException(){
		MysqlHealthCheckImpl mysqlhealthcheckimpl = new MysqlHealthCheckImpl(this.configManager);
		GroupDataSourceTarget dsTarget = new GroupDataSourceTarget("db1", 1, true);
		SQLException e = new SQLException();
		int i = mysqlhealthcheckimpl.getMaxErrorTimes() + 1;
		while(i > 0 ){
			mysqlhealthcheckimpl.notifyException(dsTarget, e);
			i--;
		}
		Assert.assertEquals(this.configManager.getAvailableDataSources().size(), 1);
		Assert.assertEquals(this.configManager.getUnAvailableDataSources().size(), 1);
	}
	
	@Test
	public void testWriteNotifyException(){
		MysqlHealthCheckImpl mysqlhealthcheckimpl = new MysqlHealthCheckImpl(this.configManager);
		GroupDataSourceTarget dsTarget = new GroupDataSourceTarget("db1", 1, false);
		SQLException e = new SQLException();
		int i = mysqlhealthcheckimpl.getMaxErrorTimes() + 1;
		while(i > 0 ){
			mysqlhealthcheckimpl.notifyException(dsTarget, e);
			i--;
		}
		Assert.assertEquals(this.configManager.getAvailableDataSources().size(), 2);
		Assert.assertEquals(this.configManager.getUnAvailableDataSources().size(), 0);
	}
}
