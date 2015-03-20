package com.dianping.zebra.admin.monitor;

public class InstanceStatus {
	
	private String dsId;
	
	private long lastUpdateTime;
	
	private String status;
	
	public String getDsId(){
		return dsId;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
