package com.dianping.zebra.admin.monitor;

public enum Status {

	ALIVE("alive"),

	DEAD("dead"),

	MHA_MARK_DOWN("mha_mark_down");

	private String status;

	private Status(String state) {
		this.status = state;
	}

	public String getStatus() {
		return status;
	}
}
