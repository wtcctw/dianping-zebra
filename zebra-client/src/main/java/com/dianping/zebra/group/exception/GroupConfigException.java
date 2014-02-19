package com.dianping.zebra.group.exception;

public class GroupConfigException extends RuntimeException {

	private static final long serialVersionUID = -7049590514431591540L;

	public GroupConfigException() {
		super();
	}

	public GroupConfigException(String message) {
		super(message);
	}

	public GroupConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public GroupConfigException(Throwable cause) {
		super(cause);
	}
}
