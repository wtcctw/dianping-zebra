package com.dianping.zebra.admin.admin.page.service;

import com.dianping.zebra.admin.admin.AdminPage;
import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.ActionPayload;
import org.unidal.web.mvc.payload.annotation.FieldMeta;

public class Payload implements ActionPayload<AdminPage, Action> {
	private AdminPage m_page;

	@FieldMeta("op")
	private Action m_action;

	@FieldMeta("ip")
	private String m_ip;

	@FieldMeta("port")
	private String m_port;
	
	@FieldMeta("database")
	private String m_database;

	@FieldMeta("user")
	private String m_user;
	
	@FieldMeta("env")
	private String m_env;

	@FieldMeta("ips")
	private String[] m_ips;
	
	@Override
	public Action getAction() {
		return m_action;
	}

	public String getDatabase() {
		return m_database;
	}

	public String getEnv(){
		return m_env;
	}

	public String getIp() {
		return m_ip;
	}

	@Override
	public AdminPage getPage() {
		return m_page;
	}
	
	public String getPort() {
		return m_port;
	}

	public String getUser() {
		return m_user;
	}

	public void setAction(String action) {
		m_action = Action.getByName(action, Action.VIEW);
	}

	public void setDatabase(String database) {
		m_database = database;
	}

	public void setEnv(String env){
		m_env = env;
	}

	public void setIp(String ip) {
		m_ip = ip;
	}

	public String[] getIps() {
		return m_ips;
	}

	public void setIps(String[] ips) {
		m_ips = ips;
	}

	@Override
	public void setPage(String page) {
		m_page = AdminPage.getByName(page, AdminPage.SERVICE);
	}

	public void setPort(String port) {
		m_port = port;
	}

	public void setUser(String user) {
		m_user = user;
	}
	
	@Override
	public void validate(ActionContext<?> ctx) {
		if (m_action == null) {
			m_action = Action.VIEW;
		}
	}
}
