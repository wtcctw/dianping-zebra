package com.dianping.zebra.group.exception;

public class ConfigException extends RuntimeException {

	private static final long serialVersionUID = -7049590514431591540L;

	public ConfigException() {
		super();
	}

	public ConfigException(String message) {
		super(message);
	}

	public ConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigException(Throwable cause) {
		super(cause);
	}
}
