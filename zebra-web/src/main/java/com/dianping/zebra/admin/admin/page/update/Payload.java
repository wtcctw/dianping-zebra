package com.dianping.zebra.admin.admin.page.update;

import com.dianping.zebra.admin.admin.AdminPage;
import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.ActionPayload;
import org.unidal.web.mvc.payload.annotation.FieldMeta;

public class Payload implements ActionPayload<AdminPage, Action> {
	private AdminPage m_page;

	@FieldMeta("op")
	private Action m_action;

	@FieldMeta("app")
	private String m_app;

	@FieldMeta("database")
	private String m_database;

	public void setAction(String action) {
		m_action = Action.getByName(action, Action.VIEW);
	}

	@Override
	public Action getAction() {
		return m_action;
	}

	@Override
	public AdminPage getPage() {
		return m_page;
	}

	public void setApp(String app) {
		m_app = app;
	}

	public String getApp() {
		return m_app;
	}

	public void setDatabase(String database) {
		m_database = database;
	}

	public String getDatabase() {
		return m_database;
	}

	@Override
	public void setPage(String page) {
		m_page = AdminPage.getByName(page, AdminPage.UPDATE);
	}

	@Override
	public void validate(ActionContext<?> ctx) {
		if (m_action == null) {
			m_action = Action.VIEW;
		}
	}
}
