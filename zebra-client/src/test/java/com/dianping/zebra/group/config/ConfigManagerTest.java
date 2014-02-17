package com.dianping.zebra.group.config;

import java.io.IOException;

import org.junit.Test;

public class ConfigManagerTest {

	@Test
	public void testManager() throws IOException, InterruptedException {
		LocalXmlConfigManager manager = new LocalXmlConfigManager(ConfigConstants.KEY_DATASOURCE_FILE);

		manager.addListerner(new GroupConfigChangeListener() {

			@Override
			public void onChange(BaseGroupConfigChangeEvent event) {
				System.out.println("changed");
				System.out.println(event);
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
