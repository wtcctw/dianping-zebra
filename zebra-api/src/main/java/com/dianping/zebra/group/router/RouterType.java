package com.dianping.zebra.group.router;

public enum RouterType {

	ROUND_ROBIN("round-robin"),

	LOAD_BALANCE("load-balance"),

	FAIL_OVER("fail-over");

	public static RouterType getRouterType(String type) {
		if (type.equalsIgnoreCase("load-balance")) {
			return LOAD_BALANCE;
		} else if (type.equalsIgnoreCase("fail-over")) {
			return FAIL_OVER;
		} else {
			return ROUND_ROBIN;
		}
	}

	private String type;

	private RouterType(String type) {
		this.type = type;
	}

	public String getRouterType() {
		return type;
	}
}
