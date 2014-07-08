package com.dianping.zebra.group.router;

import java.util.HashMap;

import org.junit.*;

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.avatar.tracker.TrackerContext;

public class DpdlReadWriteStrategyImplTest {
	@Test
	public void test_set_auth() throws InterruptedException{
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getId());
				
				TrackerContext trackerContext = new TrackerContext();
				trackerContext.setAuthenticated(true);
				trackerContext.setExtension(new HashMap<String, Object>());
				ExecutionContextHolder.setTrackerContext(trackerContext);
				
				DpdlReadWriteStrategyImpl target = new DpdlReadWriteStrategyImpl();
				Assert.assertTrue(target.forceReadFromMaster());
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getId());
				
				DpdlReadWriteStrategyImpl target = new DpdlReadWriteStrategyImpl();
				Assert.assertTrue(!target.forceReadFromMaster());
			}
		}).start();
		
		Thread.sleep(1000);
	}
}
