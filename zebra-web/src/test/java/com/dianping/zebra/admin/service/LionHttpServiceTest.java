package com.dianping.zebra.admin.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.admin.admin.service.LionHttpService;

@Ignore
public class LionHttpServiceTest extends ComponentTestCase {

	private LionHttpService m_lionHttpService;

	@Before
	public void setup() {
		m_lionHttpService = lookup(LionHttpService.class);
	}

	@Test
	public void testSetConfig() {
		boolean isSuccess = m_lionHttpService.setConfig("dev", "lion-test.lion-m1-write.jdbc.properties", "true");

		System.out.println(isSuccess);
	}

	@Test
	public void testGetConfig() throws IOException {
		String content = m_lionHttpService.getConfig("dev", "lion-test.lion-m1-write.jdbc.properties");

		// jdbc:mysql://192.168.8.44:3306/TuanGou_Migrate?characterEncoding=UTF8
		System.out.println(content);

		//boolean isSuccess = m_lionHttpService.setConfig("dev", "lion-test.lion-m1-write.jdbc.properties", content);

		//System.out.println(isSuccess);
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
		String env = "dev";

		HashMap<String, String> result = m_lionHttpService.getConfigByProject(env, "ds");

		for (Entry<String, String> entry : result.entrySet()) {
			m_lionHttpService.setConfig(env, entry.getKey(), entry.getValue());
			System.out.println("finish key : " + entry.getKey() + "=" + entry.getValue());
		}
	}

	@Test
	public void getAllPassowrds() throws IOException {
		HashMap<String, String> result = m_lionHttpService.getConfigByProject("performance", "ds");

		for (Entry<String, String> entry : result.entrySet()) {
			if (entry.getKey().contains("password")) {
				System.out.println("finish key : " + entry.getKey() + "=" + entry.getValue());
			}
		}
	}
	
	@Test
	public void testCreateKey() throws IOException{
		boolean success = m_lionHttpService.createKey("lion-test", "lionapi-test");
		
		System.out.println(success);
	}
}
