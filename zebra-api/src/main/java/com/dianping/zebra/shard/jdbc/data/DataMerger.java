/**
 * Project: zebra-client
 * 
 * File Created at 2011-6-24
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
package com.dianping.zebra.shard.jdbc.data;


import com.dianping.zebra.shard.router.RouterResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * <pre>
 * 数据合并器接口
 * </pre>
 * 
 * @author Leo Liang
 * 
 */
public interface DataMerger {

	/**
	 * 把数据合并并设定给定的<tt>DataPool</tt>
	 * 
	 * @param dataPool
	 * @param routerTarget
	 * @param actualResultSets
	 * @throws java.sql.SQLException
	 */
	public void merge(DataPool dataPool, RouterResult routerTarget, List<ResultSet> actualResultSets)
			throws SQLException;

}