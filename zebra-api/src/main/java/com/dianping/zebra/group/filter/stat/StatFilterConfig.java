package com.dianping.zebra.group.filter.stat;

/**
 * Created by Dozer on 10/30/14.
 */
public class StatFilterConfig {
	private static volatile int serverPort = 4088;

	private static volatile boolean serverEnable = true;

	public static int getServerPort() {
		return serverPort;
	}

	public static void setServerPort(int serverPort) {
		StatFilterConfig.serverPort = serverPort;
	}

	public static boolean isServerEnable() {
		return serverEnable;
	}

	public static void setServerEnable(boolean serverEnable) {
		StatFilterConfig.serverEnable = serverEnable;
	}
}
