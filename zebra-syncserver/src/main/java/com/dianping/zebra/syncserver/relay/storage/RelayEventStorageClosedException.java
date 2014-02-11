package com.dianping.zebra.syncserver.relay.storage;

/**
 * 
 * @author Leo Liang
 */
public class RelayEventStorageClosedException extends Exception {

	private static final long	serialVersionUID	= 6780761235961823055L;

	public RelayEventStorageClosedException() {
		super();
	}

	public RelayEventStorageClosedException(String msg) {
		super(msg);
	}
}
