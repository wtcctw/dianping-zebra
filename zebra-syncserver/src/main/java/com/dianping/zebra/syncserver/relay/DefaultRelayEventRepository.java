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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dianping.zebra.syncserver.bo.RelayEvent;
import com.dianping.zebra.syncserver.lifecycle.LifeCycleException;
import com.dianping.zebra.syncserver.relay.storage.RelayEventStorage;

/**
 * @author Leo Liang
 * 
 */
public class DefaultRelayEventRepository implements RelayEventRepository {

	private static final Logger				log	= Logger.getLogger(DefaultRelayEventRepository.class);

	private Map<String, RelayEventStorage>	storages;

	/**
	 * @return the storages
	 */
	public Map<String, RelayEventStorage> getStorages() {
		return storages;
	}

	/**
	 * @param storages
	 *            the storages to set
	 */
	public void setStorages(Map<String, RelayEventStorage> storages) {
		this.storages = storages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dianping.zebra.syncserver.lifecycle.LifeCycle#init()
	 */
	@Override
	public void init() throws LifeCycleException {
		if (storages == null) {
			throw new LifeCycleException("Storage mapping not set");
		}
		log.info("DefaultRelayEventRepository inited.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dianping.zebra.syncserver.lifecycle.LifeCycle#start()
	 */
	@Override
	public void start() throws LifeCycleException {
		// do nothing
		log.info("DefaultRelayEventRepository started.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dianping.zebra.syncserver.lifecycle.LifeCycle#stop()
	 */
	@Override
	public void stop() throws LifeCycleException {

		List<Throwable> innerExceptions = new ArrayList<Throwable>();
		for (Map.Entry<String, RelayEventStorage> entry : storages.entrySet()) {
			try {
				entry.getValue().close();
			} catch (Exception e) {
				log.error("Stop storage failed. StorageKey: " + entry.getKey(), e);
				innerExceptions.add(new LifeCycleException("Stop storage failed. StorageKey: " + entry.getKey(), e));
			}
		}

		throwLifeCycleExceptionIfNeeded(innerExceptions);
		log.info("DefaultRelayEventRepository stopped.");

	}

	@Override
	public RelayEvent get(String key) throws RelayEventRepositoryException {
		if (!storages.containsKey(key)) {
			log.error("No storage for key: " + key);
			throw new RelayEventRepositoryException("No storage for key: " + key);
		} else {
			return storages.get(key).get();
		}
	}

	@Override
	public void add(String key, RelayEvent relayEvent) throws RelayEventRepositoryException {
		if (!storages.containsKey(key)) {
			log.error("No storage for key: " + key);
			throw new RelayEventRepositoryException("No storage for key: " + key);
		} else {
			try {
				storages.get(key).add(relayEvent);
			} catch (Exception e) {
				log.error("Storage error.", e);
				throw new RelayEventRepositoryException(e);
			}
		}
	}

	private void throwLifeCycleExceptionIfNeeded(List<Throwable> exceptionList) throws LifeCycleException {

		if (exceptionList != null && !exceptionList.isEmpty()) {
			StringWriter buffer = new StringWriter();
			PrintWriter out = null;
			try {
				out = new PrintWriter(buffer);

				for (Throwable exception : exceptionList) {
					exception.printStackTrace(out);
				}
			} finally {
				if (out != null) {
					out.close();
				}
			}

			throw new LifeCycleException(buffer.toString());
		}
	}
}
