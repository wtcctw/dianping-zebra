package com.dianping.zebra.admin.admin.service;

import com.dianping.zebra.admin.report.entity.App;
import com.dianping.zebra.admin.report.entity.Database;
import com.dianping.zebra.admin.report.entity.Report;
import com.dianping.zebra.web.dal.stat.Heartbeat;

public interface ReportService {

	public void createOrUpdate(Heartbeat heartbeat);

	public Report getReport();
	
	public App getApp(String app);
	
	public Database getDatabase(String database);

}
