package com.dianping.zebra.group.exception;

public class SimpleDataSourceException extends RuntimeException {

	private static final long serialVersionUID = 6890440268267847181L;

	public SimpleDataSourceException() {
		super();
	}

	public SimpleDataSourceException(String message) {
		super(message);
	}

	public SimpleDataSourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SimpleDataSourceException(Throwable cause) {
		super(cause);
	}
}
