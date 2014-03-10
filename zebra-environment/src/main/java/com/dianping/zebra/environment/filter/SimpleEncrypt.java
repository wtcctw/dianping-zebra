package com.dianping.zebra.environment.filter;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Date;

public class SimpleEncrypt {

	private static final int RADIX = 36;

	private final BigInteger bigIntSeed;

	public SimpleEncrypt(long seed) {
		this.bigIntSeed = BigInteger.valueOf(seed);
	}

	public long decryptTimestamp(String value) throws NumberFormatException, UnsupportedEncodingException {
		BigInteger encryptedBigInt = new BigInteger(value, RADIX);
		BigInteger decrytedBigInt = encryptedBigInt.xor(bigIntSeed);

		long changedHighLow = bytesToLong(decrytedBigInt.toByteArray());

		// 高低交换
		long timestamp = changeHighLow(changedHighLow);

		return timestamp;
	}

	private long changeHighLow(long changedHighLow) {
		long high = changedHighLow >>> 32;
		long timestamp = (changedHighLow << 32) | high;
		return timestamp;
	}

	public String encryptTimestamp(long timestamp) throws UnsupportedEncodingException {
		long changedHighLow = changeHighLow(timestamp);

		BigInteger textBigInt = new BigInteger(longToBytes(changedHighLow));

		BigInteger encrypted = bigIntSeed.xor(textBigInt);

		return encrypted.toString(RADIX);
	}

	private byte[] longToBytes(long v) {
		byte[] writeBuffer = new byte[12];

		writeBuffer[0] = (byte) (v >>> 56);
		writeBuffer[1] = (byte) (v >>> 48);
		// 用于校验的字节
		writeBuffer[2] = (byte) (writeBuffer[0] ^ writeBuffer[1]);

		writeBuffer[3] = (byte) (v >>> 40);
		writeBuffer[4] = (byte) (v >>> 32);
		// 用于校验的字节
		writeBuffer[5] = (byte) (writeBuffer[3] ^ writeBuffer[4]);

		writeBuffer[6] = (byte) (v >>> 24);
		writeBuffer[7] = (byte) (v >>> 16);
		// 用于校验的字节
		writeBuffer[8] = (byte) (writeBuffer[6] ^ writeBuffer[7]);

		writeBuffer[9] = (byte) (v >>> 8);
		writeBuffer[10] = (byte) (v >>> 0);
		// 用于校验的字节
		writeBuffer[11] = (byte) (writeBuffer[9] ^ writeBuffer[10]);

		return writeBuffer;
	}

	private long bytesToLong(byte[] byteArray) {
		long val = 0;
		byte[] checkBytes = new byte[2];
		for (int i = 0; i < byteArray.length; i++) {
			if (i % 3 == 2) {
				// 校验
				if (byteArray[i] != (byte) (checkBytes[0] ^ checkBytes[1])) {
					throw new IllegalArgumentException("Illegal encrypted value");
				}
			} else {
				val <<= 8;
				val |= (byteArray[i] & 0x00FF);

				checkBytes[i % 2] = byteArray[i];
			}
		}
		return val;
	}

	public static void main(String args[]) throws UnsupportedEncodingException {
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		SimpleEncrypt simpleEncrypt = new SimpleEncrypt(8543887L);
		String entrypt = simpleEncrypt.encryptTimestamp(timestamp);
		System.out.println(entrypt);
		long dec = simpleEncrypt.decryptTimestamp(entrypt);
		System.out.println(dec);
		System.out.println(new Date(dec));

		dec = simpleEncrypt.decryptTimestamp("-2if8vtdxg2x0ogjze46");
		System.out.println(dec);
		System.out.println(new Date(dec));
	}
}