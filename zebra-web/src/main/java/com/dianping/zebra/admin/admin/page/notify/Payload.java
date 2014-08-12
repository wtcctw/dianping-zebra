package com.dianping.zebra.admin.admin.page.notify;

import com.dianping.zebra.admin.admin.AdminPage;
import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.ActionPayload;
import org.unidal.web.mvc.payload.annotation.FieldMeta;

public class Payload implements ActionPayload<AdminPage, Action> {
	private AdminPage m_page;

	@FieldMeta("op")
	private Action m_action;

	@FieldMeta("app")
	private String app;

	@FieldMeta("ip")
	private String ip;

	@FieldMeta("dataSourceBeanName")
	private String dataSourceBeanName;

	@FieldMeta("dataSourceBeanClass")
	private String dataSourceBeanClass;

	@FieldMeta("database")
	private String database;

	@FieldMeta("url")
	private String url;

	@FieldMeta("username")
	private String username;

	@FieldMeta("replaced")
	private boolean replaced;

	@FieldMeta("initPoolSize")
	private int initPoolSize;

	@FieldMeta("maxPoolSize")
	private int maxPoolSize;

	@FieldMeta("minPoolSize")
	private int minPoolSize;

	@FieldMeta("version")
	private String version;

	@Override
	public Action getAction() {
		return m_action;
	}

	public String getApp() {
		return app;
	}

	public String getDatabase() {
		return database;
	}

	public String getDataSourceBeanClass() {
		return dataSourceBeanClass;
	}

	public String getDataSourceBeanName() {
		return dataSourceBeanName;
	}

	public int getInitPoolSize() {
		return initPoolSize;
	}

	public String getIp() {
		return ip;
	}

	public Action getM_action() {
		return m_action;
	}

	public AdminPage getM_page() {
		return m_page;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	@Override
	public AdminPage getPage() {
		return m_page;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getVersion() {
		return version;
	}

	public boolean isReplaced() {
		return replaced;
	}

	public void setAction(String action) {
		m_action = Action.getByName(action, Action.VIEW);
	}

	public void setApp(String app) {
		this.app = app;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setDataSourceBeanClass(String dataSourceBeanClass) {
		this.dataSourceBeanClass = dataSourceBeanClass;
	}

	public void setDataSourceBeanName(String dataSourceBeanName) {
		this.dataSourceBeanName = dataSourceBeanName;
	}

	public void setInitPoolSize(int initPoolSize) {
		this.initPoolSize = initPoolSize;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setM_action(Action m_action) {
		this.m_action = m_action;
	}

	public void setM_page(AdminPage m_page) {
		this.m_page = m_page;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	@Override
	public void setPage(String page) {
		m_page = AdminPage.getByName(page, AdminPage.NOTIFY);
	}

	public void setReplaced(boolean replaced) {
		this.replaced = replaced;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public void validate(ActionContext<?> ctx) {
		if (m_action == null) {
			m_action = Action.VIEW;
		}
	}
}
