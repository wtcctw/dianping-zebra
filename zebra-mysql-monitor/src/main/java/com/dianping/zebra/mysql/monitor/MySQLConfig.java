package com.dianping.zebra.mysql.monitor;

public class MySQLConfig {
	
	private String jdbcRef;
	
	private String username;
	
	private String password;
	
	private String testSql;
	
	private int pingFailLimit = 10;
	
	private int pingIntervalSeconds = 1;
	
	private boolean isAutoFailover = true;
	
	public int getPingFailLimit() {
		return pingFailLimit;
	}

	public void setPingFailLimit(int pingFailLimit) {
		this.pingFailLimit = pingFailLimit;
	}

	public int getPingIntervalSeconds() {
		return pingIntervalSeconds;
	}

	public void setPingIntervalSeconds(int pingIntervalSeconds) {
		this.pingIntervalSeconds = pingIntervalSeconds;
	}

	public void setJdbcRef(String jdbcRef) {
		this.jdbcRef = jdbcRef;
	}

	public String getJdbcRef() {
		return jdbcRef;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTestSql() {
		return testSql;
	}

	public void setTestSql(String testSql) {
		this.testSql = testSql;
	}

	public boolean isAutoFailover() {
		return isAutoFailover;
	}

	public void setAutoFailover(boolean isAutoFailover) {
		this.isAutoFailover = isAutoFailover;
	}
}
