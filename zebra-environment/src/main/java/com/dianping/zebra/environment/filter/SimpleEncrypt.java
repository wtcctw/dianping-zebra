package com.dianping.zebra.environment.filter;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Date;

public class SimpleEncrypt {

	private static final int RADIX = 16;

	private final BigInteger bigIntSeed;

	public SimpleEncrypt(long seed) {
		this.bigIntSeed = BigInteger.valueOf(seed);
	}

	public long decryptTimestamp(String value) throws NumberFormatException, UnsupportedEncodingException {
		BigInteger encryptedBigInt = new BigInteger(value, RADIX);
		BigInteger decrytedBigInt = encryptedBigInt.xor(bigIntSeed);

		long changedHighLow = Long.valueOf(new String(decrytedBigInt.toByteArray(), "UTF-8"));
		// 高低交换
		long high = changedHighLow >>> 32;
		long timestamp = (changedHighLow << 32) | high;

		return timestamp;
	}

	public String encryptTimestamp(long timestamp) throws UnsupportedEncodingException {
		// 高低交换
		long high = timestamp >>> 32;
		long changedHighLow = (timestamp << 32) | high;

		String text = String.valueOf(changedHighLow);
		BigInteger textBigInt = new BigInteger(text.getBytes("UTF-8"));

		BigInteger encrypted = bigIntSeed.xor(textBigInt);

		return encrypted.toString(RADIX);
	}

	public static void main(String args[]) throws UnsupportedEncodingException {
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		SimpleEncrypt simpleEncrypt = new SimpleEncrypt(312989518543887L);
		String entrypt = simpleEncrypt.encryptTimestamp(timestamp);
		System.out.println(entrypt);
		long dec = simpleEncrypt.decryptTimestamp(entrypt);
		System.out.println(dec);
		System.out.println(new Date(dec));

		dec = simpleEncrypt.decryptTimestamp("373930333336373738373531322f91bc230437");
		System.out.println(dec);
		System.out.println(new Date(dec));
	}
}