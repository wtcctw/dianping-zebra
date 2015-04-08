package com.dianping.zebra.group.exception;

public class SlaveDsDisConnectedException extends DalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2417999991648922583L;

	public SlaveDsDisConnectedException() {
		super();
	}

	public SlaveDsDisConnectedException(String message) {
		super(message);
	}

	public SlaveDsDisConnectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public SlaveDsDisConnectedException(Throwable cause) {
		super(cause);
	}
}
