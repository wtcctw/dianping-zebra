package com.dianping.zebra.admin.admin.page.merge;

public enum JspFile {
	VIEW("/jsp/admin/merge.jsp"),

	;

	private String m_path;

	private JspFile(String path) {
		m_path = path;
	}

	public String getPath() {
		return m_path;
	}
}
