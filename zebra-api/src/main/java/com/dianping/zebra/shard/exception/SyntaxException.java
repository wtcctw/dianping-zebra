package com.dianping.zebra.shard.exception;

public class SyntaxException extends Exception {

	/**
	 * 
	 */
   private static final long serialVersionUID = -1814311695297681608L;

	public SyntaxException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 */
	public SyntaxException(String message) {
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message and
	 * cause.
	 */
	public SyntaxException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new runtime exception with the specified cause.
	 */
	public SyntaxException(Throwable cause) {
		super(cause);
	}
	
}
