package com.dianping.zebra.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.admin.admin.service.CmdbService;

public class CmdbServiceTest extends ComponentTestCase {

	private CmdbService m_cmdbService;

	@Before
	public void setup() {
		m_cmdbService = lookup(CmdbService.class);
	}

	@Test
	public void test() {
		System.out.println(m_cmdbService.getAppName("192.168.211.190"));
	}

	@Test
	public void test_batch() {
		List<String> ips = new ArrayList<String>();
		ips.add("127.0.0.1");
		for (int i = 1; i < 255; i++) {
			ips.add("10.1.102." + i);
		}

		System.out.println(m_cmdbService.getMultiAppName(ips));
	}
}
