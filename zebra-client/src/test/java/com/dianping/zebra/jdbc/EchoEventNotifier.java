/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-7-6
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
package com.dianping.zebra.jdbc;

import java.util.List;
import java.util.Map;

import com.dianping.zebra.event.SyncEventNotifier;
import com.dianping.zebra.event.SBRSyncEvent;
import com.dianping.zebra.event.SyncEvent;

/**
 * TODO Comment of EchoEventNotifier
 * 
 * @author Leo Liang
 * 
 */
public class EchoEventNotifier implements SyncEventNotifier {

	/*
	 * (non-Javadoc)
	 */
	@Override
	public void notifyEvent(SyncEvent event) {
		if (event instanceof SBRSyncEvent) {
			SBRSyncEvent sbrSyncEvent = (SBRSyncEvent) event;
			System.out.print("Pub event:");
			System.out.print(" Params: " + sbrSyncEvent.getParams());
			for (Map.Entry<String, List<String>> entry : sbrSyncEvent.getIdentitySqlsMapping().entrySet()) {
				System.out.print(" DataSource: " + entry.getKey() + " SQLs:" + entry.getValue());
				System.out.println();
			}
		}
	}
}
