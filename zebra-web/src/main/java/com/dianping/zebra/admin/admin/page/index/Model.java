package com.dianping.zebra.admin.admin.page.index;

import org.unidal.web.mvc.ViewModel;

import com.dianping.zebra.admin.admin.AdminPage;
import com.dianping.zebra.admin.admin.service.ConnectionServiceImpl.ConnectionStatus;
import com.dianping.zebra.admin.report.entity.App;
import com.dianping.zebra.admin.report.entity.Database;
import com.dianping.zebra.admin.report.entity.Report;

public class Model extends ViewModel<AdminPage, Action, Context> {
	private Report m_report;

	private Database m_database;

	private App m_app;
	
	private ConnectionStatus m_connectionStatus;

	public Model(Context ctx) {
		super(ctx);
	}

	@Override
	public Action getDefaultAction() {
		return Action.VIEW;
	}

	public void setReport(Report report) {
		m_report = report;
	}

	public Report getReport() {
		return m_report;
	}

	public void setDatabase(Database database) {
		m_database = database;
	}

	public Database getDatabase() {
		return m_database;
	}

	public void setApp(App app) {
		m_app = app;
	}

	public App getApp() {
		return m_app;
	}

	public void setConnectionStatus(ConnectionStatus connectionStatus){
		m_connectionStatus = connectionStatus;
	}
	
	public ConnectionStatus getConnectionStatus(){
		return m_connectionStatus;
	}
}
