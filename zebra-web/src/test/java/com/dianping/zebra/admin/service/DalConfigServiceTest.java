package com.dianping.zebra.admin.service;

import org.junit.Before;
import org.junit.Test;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.admin.admin.service.DalConfigService;

public class DalConfigServiceTest extends ComponentTestCase {
	private DalConfigService m_dalConfigService;

	@Before
	public void setup() {
		m_dalConfigService = lookup(DalConfigService.class);
		m_dalConfigService.setProject("lion-test");
	}
	
	
	@Test
	public void addDataSource(){
		m_dalConfigService.generateConfig("lion-m1-write");
	}
}
