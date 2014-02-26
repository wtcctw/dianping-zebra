package com.dianping.zebra.environment.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class ZebraUtil {

	public static long decryptTimestamp(String value) throws NumberFormatException, UnsupportedEncodingException {
		long changedHighLow = Long.valueOf(SimpleEncryptUtil.decrypt(value));
		// 高低交换
		long high = changedHighLow >>> 32;
		long timestamp = (changedHighLow << 32) | high;

		return timestamp;
	}

	public static String encryptTimestamp(long timestamp) throws UnsupportedEncodingException {
		// 高低交换
		long high = timestamp >>> 32;
		long changedHighLow = (timestamp << 32) | high;

		return SimpleEncryptUtil.encrypt(String.valueOf(changedHighLow));
	}

	public static void main(String args[]) throws UnsupportedEncodingException {
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		String entrypt = ZebraUtil.encryptTimestamp(timestamp);
		System.out.println(entrypt);
		long dec = ZebraUtil.decryptTimestamp(entrypt);
		System.out.println(dec);
		System.out.println(new Date(dec));
	}
}
