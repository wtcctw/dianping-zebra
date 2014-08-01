package com.dianping.zebra.admin.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.admin.admin.service.DalService;

public class DalServiceTest extends ComponentTestCase {

	private DalService m_dalService;

	@Before
	public void setup() {
		m_dalService = lookup(DalService.class);
	}

	@Test
	@Ignore
	public void testMarkDown() {
		m_dalService.markDown("dev", "127.0.0.2", "3306", null);
	}
	
	@Test
	@Ignore
	public void testMarkUp() {
		m_dalService.markUp("dev", "127.0.0.2", "3316", null);
	}
}
