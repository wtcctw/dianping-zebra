package com.dianping.zebra.admin.service;

import org.junit.Before;
import org.junit.Test;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.admin.admin.service.DatabaseRealtimeService;

public class DatabaseRealtimeServiceTest  extends ComponentTestCase {
	private DatabaseRealtimeService m_databaseRealtimeService;

	@Before
	public void setup() {
		m_databaseRealtimeService = lookup(DatabaseRealtimeService.class);
	}
	
	@Test
	public void test1(){
		System.out.println(m_databaseRealtimeService.getConnectedIps("dianpingac"));
	}
	
	@Test
	public void test2(){
		System.out.println(m_databaseRealtimeService.getAllConnectedIps());
	}
}
