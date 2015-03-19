package com.dianping.zebra.admin.monitor;

public class MonitorConfig {
	
	private String testSql = "SELECT 1";
	
	private int pingFailLimit = 10;
	
	private int pingIntervalSeconds = 1;
	
	private int queryTimeout = 1;
	
	private long validPeriod = 30 * 1000; //30 seconds
	
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

	public int getQueryTimeout() {
		return queryTimeout;
	}

	public void setQueryTimeout(int queryTimeout) {
		this.queryTimeout = queryTimeout;
	}

	public long getValidPeriod() {
		return validPeriod;
	}

	public void setValidPeriod(long validPeriod) {
		this.validPeriod = validPeriod;
	}
}
