package com.dianping.zebra.group.config;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public class ConfigManagerTest {

	@Test
	public void testManager() throws IOException, InterruptedException {
		LocalXmlConfigManager manager = new LocalXmlConfigManager("datasources.xml");

		manager.addListerner(new GroupConfigChangeListener() {

			@Override
			public void onChange(ActiveDataSourceChangeEvent event) {
				System.out.println("changed");
				System.out.println(event);
			}

			@Override
			public String getName() {
				return "mock-listener";
			}

			@Override
         public void onActiveDataSourceChange(Map<String, DataSourceConfig> configs) {
				//do nothing
			}
		});

		manager.init();

		System.in.read();
	}
}
