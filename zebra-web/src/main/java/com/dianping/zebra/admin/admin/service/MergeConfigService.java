package com.dianping.zebra.admin.admin.service;

import java.util.List;

public interface MergeConfigService {

	/**
	 * 
	 * @param from
	 *           dianping-m1-write,dianping-s1-read,etc.
	 * @param to
	 *           dianping-m1-write,etc.
	 * @param envId
	 * @return true if success, false otherwise
	 */
	public boolean merge(List<String> from, String to, Env env);

	public static enum Env {

		DEV(1),

		ALPHA(2),

		BETA(3),

		PRELEASE(4),

		PRODUCT(5),

		PERFORMANCE(6),

		PRODUCT_HM(7);

		private int env;

		private Env(int env) {
			this.env = env;
		}

		public int getValue() {
			return env;
		}
	}
}
