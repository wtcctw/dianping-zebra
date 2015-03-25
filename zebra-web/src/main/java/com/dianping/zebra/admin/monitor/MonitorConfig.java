package com.dianping.zebra.admin.monitor;

public class MonitorConfig {

	private volatile String testSql;

	private volatile int pingFailLimit;

	private volatile int pingIntervalSeconds;

	private int queryTimeout = 1;

	private volatile long validPeriod;

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
