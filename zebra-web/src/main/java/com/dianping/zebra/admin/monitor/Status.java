package com.dianping.zebra.admin.monitor;

public enum Status {

	ALIVE("alive"),
	
	DEAD("dead");
	
	private String status;
	
	private Status(String state){
		this.status = state;
	}

	public String getStatus() {
		return status;
	}
}
