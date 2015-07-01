package com.dianping.zebra.biz.dto;

public class InstanceStatusDto {
	
	private String dsId;
	
	private long lastUpdateTime;
	
	private int delay;
	
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

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
