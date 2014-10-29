package com.dianping.zebra.admin.admin.page.login;

import com.dianping.zebra.admin.admin.AdminPage;
import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.ActionPayload;
import org.unidal.web.mvc.payload.annotation.FieldMeta;

public class Payload implements ActionPayload<AdminPage, Action> {
	private AdminPage m_page;

	@FieldMeta("op")
	private Action m_action;

	@FieldMeta("password")
	private String m_password;

	@FieldMeta("username")
	private String m_username;

	public String getPassword() {
		return m_password;
	}

	public void setPassword(String m_password) {
		this.m_password = m_password;
	}

	public String getUsername() {
		return m_username;
	}

	public void setUsername(String m_username) {
		this.m_username = m_username;
	}

	@Override
	public Action getAction() {
		return m_action;
	}

	public void setAction(String action) {
		m_action = Action.getByName(action, Action.VIEW);
	}

	@Override
	public AdminPage getPage() {
		return m_page;
	}

	@Override
	public void setPage(String page) {
		m_page = AdminPage.getByName(page, AdminPage.LOGIN);
	}

	@Override
	public void validate(ActionContext<?> ctx) {
		if (m_action == null) {
			m_action = Action.VIEW;
		}
	}
}
