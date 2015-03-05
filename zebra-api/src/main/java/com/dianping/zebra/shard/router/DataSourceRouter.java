/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-6-7 $Id$
 * 
 * Copyright 2010 dianping.com. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.shard.router;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * 数据源路由
 * 
 * @author Leo Liang
 * @author danson.liu
 * 
 */
public interface DataSourceRouter {

	RouterTarget getTarget(String sql, List<Object> params) throws DataSourceRouteException;

	void setDataSourcePool(Map<String, DataSource> dataSourcePool);

	void init();

}
