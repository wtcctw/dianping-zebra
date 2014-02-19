package com.dianping.zebra.group.util;

public class ZebraCookieUtil {

	public static long decryptTimestamp(String value) {
		return Long.valueOf(SimpleEncryptUtil.decrypt(value));
	}

	public static String encryptTimestamp(long timestamp) {
		return SimpleEncryptUtil.encrypt(String.valueOf(timestamp));
	}

}
