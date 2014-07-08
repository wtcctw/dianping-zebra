package com.dianping.zebra.group.router;

import java.util.HashMap;

import org.junit.*;

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.avatar.tracker.TrackerContext;

public class DpdlReadWriteStrategyImplTest {
	@Test
	public void test_not_set_auth(){
		DpdlReadWriteStrategyImpl target = new DpdlReadWriteStrategyImpl();
		Assert.assertTrue(!target.forceReadFromMaster());
	}
	
	@Test
	public void test_set_auth(){
		TrackerContext trackerContext = new TrackerContext();
		trackerContext.setAuthenticated(true);
		trackerContext.setExtension(new HashMap<String, Object>());
		ExecutionContextHolder.setTrackerContext(trackerContext);
		
		DpdlReadWriteStrategyImpl target = new DpdlReadWriteStrategyImpl();
		Assert.assertTrue(target.forceReadFromMaster());
	}
}
