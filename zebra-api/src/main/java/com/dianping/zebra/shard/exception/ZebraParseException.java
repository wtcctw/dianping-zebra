package com.dianping.zebra.shard.exception;

public class ZebraParseException extends Exception {

	/**
	 * 
	 */
   private static final long serialVersionUID = -1814311695297681608L;

	public ZebraParseException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 */
	public ZebraParseException(String message) {
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message and
	 * cause.
	 */
	public ZebraParseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new runtime exception with the specified cause.
	 */
	public ZebraParseException(Throwable cause) {
		super(cause);
	}
	
}
