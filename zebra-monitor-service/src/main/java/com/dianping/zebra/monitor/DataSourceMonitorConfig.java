package com.dianping.zebra.monitor;

public class DataSourceMonitorConfig {

	private volatile String testSql;

	private volatile int pingFailLimit;

	private volatile int pingIntervalSeconds;

	private volatile long validPeriod;

	private int delayTime;

	private boolean isAutoMarkUp;
	
	private boolean isAutoMarkDown;

	public int getDelayTime() {
		return delayTime;
	}

	public int getPingFailLimit() {
		return pingFailLimit;
	}

	public int getPingIntervalSeconds() {
		return pingIntervalSeconds;
	}

	public String getTestSql() {
		return testSql;
	}

	public long getValidPeriod() {
		return validPeriod;
	}

	public boolean isAutoMarkUp() {
		return isAutoMarkUp;
	}

	public void setAutoMarkUp(boolean isAutoMarkUp) {
		this.isAutoMarkUp = isAutoMarkUp;
	}

	public boolean isAutoMarkDown() {
		return isAutoMarkDown;
	}

	public void setAutoMarkDown(boolean isAutoMarkDown) {
		this.isAutoMarkDown = isAutoMarkDown;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public void setPingFailLimit(int pingFailLimit) {
		this.pingFailLimit = pingFailLimit;
	}

	public void setPingIntervalSeconds(int pingIntervalSeconds) {
		this.pingIntervalSeconds = pingIntervalSeconds;
	}

	public void setTestSql(String testSql) {
		this.testSql = testSql;
	}

	public void setValidPeriod(long validPeriod) {
		this.validPeriod = validPeriod;
	}
}
