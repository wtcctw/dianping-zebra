package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import org.junit.Test;

public class ConfigManagerTest {

	@Test
	public void testManager() throws IOException, InterruptedException {
		SystemConfigManager systemConfigManager = SystemConfigManagerFactory.getConfigManger("local", "zebra.v2.system");
		
		System.out.println(systemConfigManager.getSystemConfig());
		
		systemConfigManager.addListerner(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(String.format("Property %s changed from [%s] to [%s]", evt.getPropertyName(),evt.getOldValue(),evt.getNewValue()));
			}
		});
		
		
		DataSourceConfigManager dataSourceConfigManager = DataSourceConfigManagerFactory.getConfigManager("local","sample.ds.v2");

		System.out.println(dataSourceConfigManager.getDataSourceConfigs());
		dataSourceConfigManager.addListerner(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(String.format("Property %s changed from [%s] to [%s]", evt.getPropertyName(),evt.getOldValue(),evt.getNewValue()));
			}
		});
	
		//System.in.read();
	}
}
