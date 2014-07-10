/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-10-28
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

import java.util.List;

/**
 * @author danson.liu
 *
 */
public interface SqlMonitor {

	void beforeSqlExecute(String sql);
	
	void beforeSqlExecute(String sql, List<Object> params);
	
	void beforeSqlExecute(String sql, List<Object> params, int count);
	
	void afterSqlExecute();

	void finalizeSqlExecute();

}
