package com.dianping.zebra.admin.dto;

public class SQLValidateDto {

	private String app;

	private String sqlName;

	private String sql;
	
	private boolean isSyntaxSupported = true;

	private boolean isShardSupported = true;

	private String errorMsg = "";

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public boolean isSyntaxSupported() {
		return isSyntaxSupported;
	}

	public void setSyntaxSupported(boolean isSyntaxSupported) {
		this.isSyntaxSupported = isSyntaxSupported;
	}

	public boolean isShardSupported() {
		return isShardSupported;
	}

	public void setShardSupported(boolean isShardSupported) {
		this.isShardSupported = isShardSupported;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
