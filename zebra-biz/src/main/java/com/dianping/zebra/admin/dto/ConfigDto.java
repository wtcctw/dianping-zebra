package com.dianping.zebra.admin.dto;

public class ConfigDto {
	
	private String jdbcRef;
	
	private String value;
	
	private boolean isAutoReplaced;
	
	private boolean shouldAlert;

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

	public boolean isShouldAlert() {
		return shouldAlert;
	}

	public void setShouldAlert(boolean shouldAlert) {
		this.shouldAlert = shouldAlert;
	}
}
