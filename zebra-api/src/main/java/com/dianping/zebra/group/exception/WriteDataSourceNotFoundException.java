package com.dianping.zebra.group.exception;

public class WriteDataSourceNotFoundException extends RuntimeException {

   private static final long serialVersionUID = -1726616148252641312L;

	public WriteDataSourceNotFoundException() {
		super();
	}

	public WriteDataSourceNotFoundException(String message) {
		super(message);
	}

	public WriteDataSourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public WriteDataSourceNotFoundException(Throwable cause) {
		super(cause);
	}
}
