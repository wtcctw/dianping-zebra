package com.dianping.zebra.admin.admin.page.index;

public enum JspFile {
	VIEW("/jsp/admin/index.jsp"),

	DATABASE("/jsp/admin/database.jsp"),

	APP("/jsp/admin/app.jsp"),
	
	CONNECTION("/jsp/admin/connection.jsp"),

	;

	private String m_path;

	private JspFile(String path) {
		m_path = path;
	}

	public String getPath() {
		return m_path;
	}
}
