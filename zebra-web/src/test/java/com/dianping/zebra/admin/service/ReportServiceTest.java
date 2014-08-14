package com.dianping.zebra.admin.service;

import org.junit.Before;
import org.junit.Test;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.admin.admin.service.ReportService;
import com.dianping.zebra.admin.report.entity.Report;
import com.dianping.zebra.web.dal.stat.Heartbeat;
import com.dianping.zebra.web.dal.stat.HeartbeatDao;

public class ReportServiceTest extends ComponentTestCase {

	private ReportService m_reportService;
	
	private HeartbeatDao m_heartbeatDao;

	@Before
	public void setup() {
		m_reportService = lookup(ReportService.class);
		m_heartbeatDao = lookup(HeartbeatDao.class);
	}
	
	@Test
	public void testCreateOrUpdate(){
		Heartbeat h = m_heartbeatDao.createLocal();
		
		h.setAppName("group-service");
		h.setDatabaseName("tuangou2010");
		h.setDatabaseType("mysql");
		h.setUsername("asgen");
		h.setDatasourceBeanClass("com.dianping.dpdl.sql.DPDataSource");
//		h.setDatasourceBeanClass("com.dianping.zebra.group.jdbc.GroupDataSource");
		h.setDatasourceBeanName("tuangou-master");
		h.setInitPoolSize(2);
		h.setIp("192.168.1.4");
		h.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		h.setMaxPoolSize(5);
		h.setMinPoolSize(2);
		h.setReplaced(true);
		h.setVersion("zebra-2.5.5");

		m_reportService.createOrUpdate(h);
	}
	
	@Test
	public void testGetReport(){
		Report report = m_reportService.getReport();
		
		System.out.println(report);
	}
}
