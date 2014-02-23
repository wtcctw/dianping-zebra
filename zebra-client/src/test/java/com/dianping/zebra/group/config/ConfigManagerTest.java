package com.dianping.zebra.group.config;

import java.io.IOException;

import org.junit.Test;

import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config1.BaseGroupConfigChangeEvent;
import com.dianping.zebra.group.config1.GroupConfigChangeListener;
import com.dianping.zebra.group.config1.LocalGroupConfigManager;

public class ConfigManagerTest {

	@Test
	public void testManager() throws IOException, InterruptedException {
		LocalGroupConfigManager manager = new LocalGroupConfigManager("datasources.xml");

		manager.addListerner(new GroupConfigChangeListener() {

			@Override
			public void onChange(BaseGroupConfigChangeEvent event) {
				System.out.println("changed");
				System.out.println(event);
				
				for(DataSourceConfig config : event.getDatasourceConfigs().values()){
					for(Any any : config.getProperties()){
						System.out.println(any.getName());
						System.out.println(any.getValue());
					}
				}
			}

			@Override
			public String getName() {
				return "mock-listener";
			}
		});

		manager.init();

		System.in.read();
	}
}
