/**
 * Project: ${zebra-syncserver.aid}
 * 
 * File Created at 2011-7-26
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
package com.dianping.zebra.syncserver.relay;

import com.dianping.zebra.syncserver.bo.RelayEvent;
import com.dianping.zebra.syncserver.lifecycle.LifeCycle;

/**
 * <p>
 * Relay Event的存储仓库
 * </p>
 * <p>
 * 一个仓库可以包含底层多个相同或者不同的存储引擎，存储引擎通过<tt>key</tt>
 * 隔离。可以把不同目标数据源的同步事件分别存储到不同key对应的存储引擎中并通过多个回放Processor进行并行回放以提高性能。
 * </p>
 * 
 * @author Leo Liang
 * 
 */
public interface RelayEventRepository extends LifeCycle {

	public RelayEvent get(String key) throws RelayEventRepositoryException;

	public void add(String key, RelayEvent relayEvent) throws RelayEventRepositoryException;

}
