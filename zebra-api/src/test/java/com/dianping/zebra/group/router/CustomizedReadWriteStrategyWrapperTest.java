package com.dianping.zebra.group.router;

import org.junit.*;

public class CustomizedReadWriteStrategyWrapperTest {
	@Test
	public void test_wrapper_true(){
		CustomizedReadWriteStrategyWrapper wrapper = new CustomizedReadWriteStrategyWrapper();
		wrapper.addStrategy(new CustomizedReadWriteStrategy() {
			@Override
			public boolean forceReadFromMaster() {
				return false;
			}
		});
		
		wrapper.addStrategy(new CustomizedReadWriteStrategy() {
			@Override
			public boolean forceReadFromMaster() {
				return true;
			}
		});
		
		Assert.assertTrue(wrapper.forceReadFromMaster());
	}
	
	@Test
	public void test_wrapper_false(){
		CustomizedReadWriteStrategyWrapper wrapper = new CustomizedReadWriteStrategyWrapper();
		wrapper.addStrategy(new CustomizedReadWriteStrategy() {
			@Override
			public boolean forceReadFromMaster() {
				return false;
			}
		});
		
		wrapper.addStrategy(new CustomizedReadWriteStrategy() {
			@Override
			public boolean forceReadFromMaster() {
				return false;
			}
		});
		
		Assert.assertTrue(!wrapper.forceReadFromMaster());
	}
}
