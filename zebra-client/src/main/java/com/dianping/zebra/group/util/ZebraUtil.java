package com.dianping.zebra.group.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Hex;

public class ZebraUtil {

	private final static Key key;

	private static final String UTF8 = "UTF-8";

	static {
		try {
			key = AESCoder.generateSecretKey();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String decrypt(String encryptText) throws Exception {
		return new String(AESCoder.decrypt(Hex.decodeHex(encryptText.toCharArray()), key), UTF8);
	}

	public static String encrypt(String text) throws UnsupportedEncodingException, InvalidKeyException,
	      NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		return Hex.encodeHexString(AESCoder.encrypt(text.getBytes(UTF8), key));
	}

}
