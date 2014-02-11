package com.dianping.zebra.syncserver.relay.storage;

import com.dianping.zebra.syncserver.bo.RelayEvent;

/**
 * 存储引擎
 * 
 * @author Leo Liang
 */
public interface RelayEventStorage {

	public RelayEvent get();

	public void add(RelayEvent m) throws RelayEventStorageClosedException;

	public void close();
}
