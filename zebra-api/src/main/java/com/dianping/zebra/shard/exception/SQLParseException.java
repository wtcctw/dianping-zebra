package com.dianping.zebra.shard.exception;

public class SQLParseException extends Exception {

	/**
	 * 
	 */
   private static final long serialVersionUID = -1814311695297681608L;

	public SQLParseException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 */
	public SQLParseException(String message) {
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message and
	 * cause.
	 */
	public SQLParseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new runtime exception with the specified cause.
	 */
	public SQLParseException(Throwable cause) {
		super(cause);
	}
	
}
