package com.dianping.zebra.group.router;

import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
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

			@Override public void setGroupDataSourceConfig(GroupDataSourceConfig config) {

			}
		});
		
		wrapper.addStrategy(new CustomizedReadWriteStrategy() {
			@Override
			public boolean forceReadFromMaster() {
				return true;
			}

			@Override public void setGroupDataSourceConfig(GroupDataSourceConfig config) {

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

			@Override public void setGroupDataSourceConfig(GroupDataSourceConfig config) {

			}
		});
		
		wrapper.addStrategy(new CustomizedReadWriteStrategy() {
			@Override
			public boolean forceReadFromMaster() {
				return false;
			}

			@Override public void setGroupDataSourceConfig(GroupDataSourceConfig config) {

			}
		});
		
		Assert.assertTrue(!wrapper.forceReadFromMaster());
	}
}
