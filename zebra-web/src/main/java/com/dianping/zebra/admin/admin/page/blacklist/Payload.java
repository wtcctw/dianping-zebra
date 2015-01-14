package com.dianping.zebra.admin.admin.page.blacklist;

import com.dianping.zebra.admin.admin.AdminPage;
import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.ActionPayload;
import org.unidal.web.mvc.payload.annotation.FieldMeta;

public class Payload implements ActionPayload<AdminPage, Action> {
	private AdminPage m_page;

	@FieldMeta("op")
	private Action m_action;

	@FieldMeta("key")
	private String m_key;

	@FieldMeta("comment")
	private String m_comment;

	@FieldMeta("ip")
	private String m_ip;

	@FieldMeta("env")
	private String m_env;

	@FieldMeta("id")
	private String m_id;

	public String getId() {
		return m_id;
	}

	public void setId(String id) {
		this.m_id = id;
	}

	public String getEnv() {
		return m_env;
	}

	public void setEnv(String env) {
		this.m_env = env;
	}

	public String getComment() {
		return m_comment;
	}

	public void setComment(String comment) {
		this.m_comment = comment;
	}

	@Override
	public Action getAction() {
		return m_action;
	}

	public void setAction(String action) {
		m_action = Action.getByName(action, Action.VIEW);
	}

	public String getIp() {
		return m_ip;
	}

	public void setIp(String ip) {
		this.m_ip = ip;
	}

	public String getKey() {
		return this.m_key;
	}

	public void setKey(String key) {
		this.m_key = key;
	}

	@Override
	public AdminPage getPage() {
		return m_page;
	}

	@Override
	public void setPage(String page) {
		m_page = AdminPage.getByName(page, AdminPage.BLACKLIST);
	}

	@Override
	public void validate(ActionContext<?> ctx) {
		if (m_action == null) {
			m_action = Action.VIEW;
		}
	}
}
