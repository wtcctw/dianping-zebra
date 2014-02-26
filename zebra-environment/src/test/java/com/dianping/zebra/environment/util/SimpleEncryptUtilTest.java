package com.dianping.zebra.environment.util;

import org.junit.Assert;
import org.junit.Test;

public class SimpleEncryptUtilTest {
	@Test
	public void testNormal() throws Exception {
		String text = "abcdef";
		String encryptedText = SimpleEncryptUtil.encrypt(text);
		System.out.println(encryptedText);
		Assert.assertEquals(text, SimpleEncryptUtil.decrypt(encryptedText));
	}

	@Test
	public void testUnNormal1() throws Exception {
		String text = "abcdef";
		String encryptText = SimpleEncryptUtil.encrypt(text);

		String errorEncryText = encryptText + "-";
		try {
			SimpleEncryptUtil.decrypt(errorEncryText);
			throw new RuntimeException("Should not reach here.");
		} catch (Exception e) {
		}
	}

}
