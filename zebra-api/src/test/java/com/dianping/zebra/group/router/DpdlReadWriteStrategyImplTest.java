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

		DpdlReadWriteStrategy target = new DpdlReadWriteStrategy();
		Assert.assertFalse(target.shouldReadFromMaster());
	}

	@Test
	public void test_set_auth_with_config() throws InterruptedException {
		TrackerContext trackerContext = new TrackerContext();
		trackerContext.setAuthenticated(true);
		trackerContext.setExtension(new HashMap<String, Object>());
		ExecutionContextHolder.setTrackerContext(trackerContext);

		DpdlReadWriteStrategy target = new DpdlReadWriteStrategy();
		target.setGroupDataSourceConfig(new GroupDataSourceConfig());

		Assert.assertTrue(target.shouldReadFromMaster());
	}

	@Test
	public void test_not_auth() throws InterruptedException {
		System.out.println(Thread.currentThread().getId());

		DpdlReadWriteStrategy target = new DpdlReadWriteStrategy();
		Assert.assertFalse(target.shouldReadFromMaster());
	}
	
	@Test
	public void test_set_auth(){
		TrackerContext trackerContext = new TrackerContext();
		trackerContext.setAuthenticated(false);
		trackerContext.setExtension(new HashMap<String, Object>());
		ExecutionContextHolder.setTrackerContext(trackerContext);
		
		DpdlReadWriteStrategy.setReadFromMaster();
		
		DpdlReadWriteStrategy target = new DpdlReadWriteStrategy();
		target.setGroupDataSourceConfig(new GroupDataSourceConfig());

		System.out.println(target.shouldReadFromMaster());
		Assert.assertTrue(target.shouldReadFromMaster());
	}
}
