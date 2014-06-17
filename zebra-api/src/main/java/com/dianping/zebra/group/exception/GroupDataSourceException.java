package com.dianping.zebra.group.exception;

public class GroupDataSourceException extends RuntimeException {

	private static final long serialVersionUID = 6890440268267847181L;

	public GroupDataSourceException() {
		super();
	}

	public GroupDataSourceException(String message) {
		super(message);
	}

	public GroupDataSourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public GroupDataSourceException(Throwable cause) {
		super(cause);
	}
}
