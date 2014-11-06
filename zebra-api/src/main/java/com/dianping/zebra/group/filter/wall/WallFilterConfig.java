package com.dianping.zebra.group.filter.wall;

/**
 * Created by Dozer on 10/15/14.
 */
public class WallFilterConfig {

	private static volatile String configManagerType;

	public static String getConfigManagerType() {
		return WallFilterConfig.configManagerType;
	}

	public static void setConfigManagerType(String configManagerType) {
		WallFilterConfig.configManagerType = configManagerType;
	}
}
