package com.dianping.zebra.admin.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.admin.admin.service.LionHttpService;

public class LionHttpServiceTest extends ComponentTestCase {

	private LionHttpService m_lionHttpService;

	@Before
	public void setup() {
		m_lionHttpService = lookup(LionHttpService.class);
	}

	@Test
	public void testSetConfig() {
		boolean isSuccess = m_lionHttpService.setConfig("dev", "ds.tuangou_migrate-m1-write.jdbc.active", "true");

		System.out.println(isSuccess);
	}

	@Test
	public void testGetConfig() throws IOException {
		String content = m_lionHttpService.getConfig("dev", "ds.tuangou_migrate-m1-write.jdbc.active");

		// jdbc:mysql://192.168.8.44:3306/TuanGou_Migrate?characterEncoding=UTF8
		System.out.println(content);

		boolean isSuccess = m_lionHttpService.setConfig("dev", "ds.tuangou_migrate-m1-write.jdbc.active", content);

		System.out.println(isSuccess);
	}

	@Test
	public void testGetKeyValuesByPrefix() throws IOException {
		HashMap<String, String> result = m_lionHttpService.getConfigByProject("dev", "ds");

		for (Entry<String, String> entry : result.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println(result.size());
	}

	@Test
	public void resaveValues() throws IOException {
		String env = "prelease";

		HashMap<String, String> result = m_lionHttpService.getConfigByProject(env, "ds");

		for (Entry<String, String> entry : result.entrySet()) {
			m_lionHttpService.setConfig(env, entry.getKey(), entry.getValue());
			System.out.println("finish key : " + entry.getKey() + "=" + entry.getValue());
		}
	}

	@Test
	public void getAllPassowrds() throws IOException {
		HashMap<String, String> result = m_lionHttpService.getConfigByProject("alpha", "ds");

		for (Entry<String, String> entry : result.entrySet()) {
			if (entry.getKey().contains("password")) {
				System.out.println("finish key : " + entry.getKey() + "=" + entry.getValue());
			}
		}
	}
}
