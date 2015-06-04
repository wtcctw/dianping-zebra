package com.dianping.zebra.admin.service;

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

		public static Env getEnv(String env) {
			if (env == null || env.length() == 0) {
				return null;
			}

			if (env.equals("dev")) {
				return DEV;
			} else if (env.equals("alpha")) {
				return ALPHA;
			} else if (env.equals("qa")) {
				return BETA;
			} else if (env.equals("prelease")) {
				return PRELEASE;
			} else if (env.equals("product")) {
				return PRODUCT;
			} else if (env.endsWith("performance")) {
				return Env.PERFORMANCE;
			} else if (env.endsWith("product-hm")) {
				return Env.PRODUCT_HM;
			} else {
				return null;
			}
		}

		public String getStringEnv() {
			if (this == DEV) {
				return "dev";
			} else if (this == ALPHA) {
				return "alpha";
			} else if (this == BETA) {
				return "qa";
			} else if (this == PERFORMANCE) {
				return "performance";
			} else if (this == PRELEASE) {
				return "prelease";
			} else if (this == PRODUCT) {
				return "product";
			} else if (this == PRODUCT_HM) {
				return "product-hm";
			} else {
				return null;
			}
		}
	}
}
