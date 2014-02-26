package com.dianping.zebra.environment.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Date;

public class SimpleEncryptUtil {

	private static final int RADIX = 16;

	private static final String SEED = "6134903131719914204";

	private static final BigInteger SEED_BIT_INT = new BigInteger(SEED);

	public static final String encrypt(String text) throws UnsupportedEncodingException {
		if (text == null)
			return "";
		if (text.length() == 0)
			return "";

		BigInteger textBigInt = new BigInteger(text.getBytes("UTF-8"));

		BigInteger encrypted = SEED_BIT_INT.xor(textBigInt);

		return encrypted.toString(RADIX);
	}

	public static final String decrypt(String encrypted) throws UnsupportedEncodingException {
		if (encrypted == null)
			return "";
		if (encrypted.length() == 0)
			return "";

		BigInteger encryptedBigInt = new BigInteger(encrypted, RADIX);
		BigInteger decrytedBigInt = encryptedBigInt.xor(SEED_BIT_INT);

		return new String(decrytedBigInt.toByteArray(), "UTF-8");
	}

	public static void main(String args[]) throws UnsupportedEncodingException {
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		
		String enctryptedText = SimpleEncryptUtil.encrypt(timestamp + "");
		System.out.println(enctryptedText);
		
		String dec = SimpleEncryptUtil.decrypt(enctryptedText);
		System.out.println(dec);
		System.out.println(new Date(Long.parseLong(dec)));
	}
}