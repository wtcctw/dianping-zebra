/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-10-30
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
package com.dianping.zebra.monitor.sql;

import java.sql.Connection;

/**
 * @author danson.liu
 *
 */
public interface ConnectionMetaAnalyzer {

	int getDSIdentity(Connection innerConnection);

	String[] getDbAndSchema(Connection innerConnection);

	int DS_UNKNOWN 		= 1;
	int DS_C3P0 		= 2;
	int DS_DBCP 		= 4;
	int DS_DPDL 		= 8;
	int DS_ZEBRA 		= 16;

}
