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
		boolean isSuccess = m_lionHttpService.setConfig("dev", "lion-test", "button.test", "true");

		System.out.println(isSuccess);
	}

	@Test
	public void testGetKeyValuesByPrefix() throws IOException {
		HashMap<String, String> result = m_lionHttpService.getKeyValuesByPrefix("dev", "zebra.v2");

		for (Entry<String, String> entry : result.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

}
