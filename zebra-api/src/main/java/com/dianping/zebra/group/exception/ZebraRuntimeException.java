package com.dianping.zebra.group.exception;

public class ZebraRuntimeException extends RuntimeException {

   private static final long serialVersionUID = -8628442877335107998L;

	public ZebraRuntimeException() {
		super();
	}

	public ZebraRuntimeException(String message) {
		super(message);
	}

	public ZebraRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZebraRuntimeException(Throwable cause) {
		super(cause);
	}
}
