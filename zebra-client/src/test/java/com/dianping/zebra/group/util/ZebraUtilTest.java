package com.dianping.zebra.group.util;

import org.junit.Assert;
import org.junit.Test;

public class ZebraUtilTest {
	@Test
	public void testNormal() throws Exception {
		String text = System.currentTimeMillis() + "";
		String encryptText = ZebraUtil.encrypt(text);
		Assert.assertEquals(text, ZebraUtil.decrypt(encryptText));
	}

	@Test
	public void testUnNormal1() throws Exception {
		String text = System.currentTimeMillis() + "";
		String encryptText = ZebraUtil.encrypt(text);
		String errorEncryText = encryptText + "-";
		try {
			ZebraUtil.decrypt(errorEncryText);
			throw new RuntimeException("Should not reach here.");
		} catch (Exception e) {
		}
	}

}
