package com.dianping.zebra.admin.service;

import org.junit.Before;
import org.junit.Test;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.admin.admin.service.CmdbService;

public class CmdbServiceTest  extends ComponentTestCase {
	
	private CmdbService m_cmdbService;

	@Before
	public void setup() {
		m_cmdbService = lookup(CmdbService.class);
	}
	
	@Test
	public void test(){
		System.out.println(m_cmdbService.getAppName("192.168.211.190"));
	}
}
