package com.dianping.zebra.group.router;

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.avatar.tracker.TrackerContext;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class DpdlReadWriteStrategyImplTest {

	@Test
	public void test_set_auth_no_config() throws InterruptedException {
		TrackerContext trackerContext = new TrackerContext();
		trackerContext.setAuthenticated(true);
		trackerContext.setExtension(new HashMap<String, Object>());
		ExecutionContextHolder.setTrackerContext(trackerContext);

		DpdlReadWriteStrategyImpl target = new DpdlReadWriteStrategyImpl();
		Assert.assertFalse(target.forceReadFromMaster());
	}

	@Test
	public void test_set_auth_with_config() throws InterruptedException {
		TrackerContext trackerContext = new TrackerContext();
		trackerContext.setAuthenticated(true);
		trackerContext.setExtension(new HashMap<String, Object>());
		ExecutionContextHolder.setTrackerContext(trackerContext);

		DpdlReadWriteStrategyImpl target = new DpdlReadWriteStrategyImpl();
		target.setGroupDataSourceConfig(new GroupDataSourceConfig());

		Assert.assertTrue(target.forceReadFromMaster());
	}

	@Test
	public void test_not_auth() throws InterruptedException {
		System.out.println(Thread.currentThread().getId());

		DpdlReadWriteStrategyImpl target = new DpdlReadWriteStrategyImpl();
		Assert.assertFalse(target.forceReadFromMaster());
	}
}
