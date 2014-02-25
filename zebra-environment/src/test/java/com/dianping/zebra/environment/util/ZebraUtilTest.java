package com.dianping.zebra.environment.util;

import org.junit.Assert;
import org.junit.Test;

import com.dianping.zebra.environment.util.ZebraUtil;

public class ZebraUtilTest {
	@Test
	public void testNormal() throws Exception {
		long timestamp = System.currentTimeMillis();
		String encryptText = ZebraUtil.encryptTimestamp(timestamp);
		Assert.assertEquals(timestamp, ZebraUtil.decryptTimestamp(encryptText));
	}

	@Test
	public void testUnNormal1() throws Exception {
		long timestamp = System.currentTimeMillis();
		String encryptText = ZebraUtil.encryptTimestamp(timestamp);

		String errorEncryText = encryptText + "-";
		try {
			ZebraUtil.decryptTimestamp(errorEncryText);
			throw new RuntimeException("Should not reach here.");
		} catch (Exception e) {
		}
	}

}
