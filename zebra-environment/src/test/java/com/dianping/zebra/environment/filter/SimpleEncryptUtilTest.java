package com.dianping.zebra.environment.filter;

import org.junit.Assert;
import org.junit.Test;

import com.dianping.zebra.environment.filter.SimpleEncrypt;

public class SimpleEncryptUtilTest {
	private SimpleEncrypt simpleEncrypt = new SimpleEncrypt(412341241L);

	@Test
	public void testNormal() throws Exception {
		long timestamp = System.currentTimeMillis();
		String encryptedText = simpleEncrypt.encryptTimestamp(timestamp);
		System.out.println(encryptedText);
		Assert.assertEquals(timestamp, simpleEncrypt.decryptTimestamp(encryptedText));
	}

	@Test
	public void testUnNormal1() throws Exception {
		long timestamp = System.currentTimeMillis();
		String encryptText = simpleEncrypt.encryptTimestamp(timestamp);

		String errorEncryText = encryptText + "-";
		try {
			simpleEncrypt.decryptTimestamp(errorEncryText);
			throw new RuntimeException("Should not reach here.");
		} catch (Exception e) {
		}
	}

}
