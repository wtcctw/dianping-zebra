/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-7
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.monitor.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author danson.liu
 *
 */
public class StringUtilTest {

	@Test
	public void testStripComments() {
		String sql = "select * /*Test comments*/ from person";
		assertEquals("select *  from person", StringUtil.stripComments(sql, "'\"", "'\"", true, false, true, true));
	}

	@Test
	public void testFormatSql() {
		String sql = "select \t \n \r * from person";
		assertEquals("select * from person", StringUtil.formatSql(sql));
	}

}
