package com.dianping.zebra.group.exception;

public class DalException extends RuntimeException {

	private static final long serialVersionUID = -8628442877335107998L;

	public DalException() {
		super();
	}

	public DalException(String message) {
		super(message);
	}

	public DalException(String message, Throwable cause) {
		super(message, cause);
	}

	public DalException(Throwable cause) {
		super(cause);
	}
}
