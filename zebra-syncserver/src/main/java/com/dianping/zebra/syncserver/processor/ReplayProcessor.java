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
package com.dianping.zebra.syncserver.processor;

import com.dianping.zebra.syncserver.bo.RelayEvent;
import com.dianping.zebra.syncserver.lifecycle.LifeCycle;
import com.dianping.zebra.syncserver.relay.RelayEventRepository;

/**
 * <p>
 * 同步事件回放Processor
 * </p>
 * 
 * @author Leo Liang
 * 
 */
public interface ReplayProcessor extends LifeCycle {

	public void replay(RelayEvent relayEvent) throws Exception;

	public void setRelayEventRepository(RelayEventRepository relayEventRepository);
}
