package com.dianping.zebra.environment.util;

import java.math.BigInteger;

public class SimpleEncryptUtil {

	private static final int RADIX = 16;

	private static final String SEED = "6134903131719914204";

	public static final String encrypt(String text) {
		if (text == null)
			return "";
		if (text.length() == 0)
			return "";

		BigInteger bi_passwd = new BigInteger(text.getBytes());

		BigInteger bi_r0 = new BigInteger(SEED);
		BigInteger bi_r1 = bi_r0.xor(bi_passwd);

		return bi_r1.toString(RADIX);
	}

	public static final String decrypt(String encrypted) {
		if (encrypted == null)
			return "";
		if (encrypted.length() == 0)
			return "";

		BigInteger bi_confuse = new BigInteger(SEED);

		try {
			BigInteger bi_r1 = new BigInteger(encrypted, RADIX);
			BigInteger bi_r0 = bi_r1.xor(bi_confuse);

			return new String(bi_r0.toByteArray());
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String args[]) {
		System.out.println(SimpleEncryptUtil.encrypt(System.currentTimeMillis() + ""));
		System.out.println(SimpleEncryptUtil.decrypt("313339f7fb59a7a1ae07c268ce"));
	}

}