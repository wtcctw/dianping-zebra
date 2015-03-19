package com.dianping.zebra.admin.dto;

public class ConfigDto {
	
	private String jdbcRef;
	
	private String value;
	
	private boolean isAutoReplaced;

	public String getJdbcRef() {
		return jdbcRef;
	}

	public void setJdbcRef(String jdbcRef) {
		this.jdbcRef = jdbcRef;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isAutoReplaced() {
		return isAutoReplaced;
	}

	public void setAutoReplaced(boolean isAutoReplaced) {
		this.isAutoReplaced = isAutoReplaced;
	}
}
