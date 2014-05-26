package com.dianping.zebra.group.exception;

public class IllegalConfigException extends RuntimeException {

	private static final long serialVersionUID = -7049590514431591540L;

	public IllegalConfigException() {
		super();
	}

	public IllegalConfigException(String message) {
		super(message);
	}

	public IllegalConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalConfigException(Throwable cause) {
		super(cause);
	}
}
