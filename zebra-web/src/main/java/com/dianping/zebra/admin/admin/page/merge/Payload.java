package com.dianping.zebra.admin.admin.page.merge;

import com.dianping.zebra.admin.admin.AdminPage;
import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.ActionPayload;
import org.unidal.web.mvc.payload.annotation.FieldMeta;

public class Payload implements ActionPayload<AdminPage, Action> {
	private AdminPage m_page;

	@FieldMeta("op")
	private Action m_action;

	/**
	 * 多个from用逗号分隔
	 */
	@FieldMeta("from")
	private String m_from;

	@FieldMeta("to")
	private String m_to;
	
	@FieldMeta("env")
	private String m_env;

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

	public String getFrom() {
		return m_from;
	}

	public void setFrom(String from) {
		m_from = from;
	}

	public String getTo() {
		return m_to;
	}

	public void setTo(String to) {
		m_to = to;
	}
	
	public String getEnv(){
		return m_env;
	}

	public void setEnv(String env){
		m_env = env;
	}
	
	@Override
	public void setPage(String page) {
		m_page = AdminPage.getByName(page, AdminPage.MERGE);
	}

	@Override
	public void validate(ActionContext<?> ctx) {
		if (m_action == null) {
			m_action = Action.VIEW;
		}
	}
}
