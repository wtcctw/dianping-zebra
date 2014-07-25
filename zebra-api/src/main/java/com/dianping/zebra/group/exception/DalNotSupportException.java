package com.dianping.zebra.group.exception;

import java.sql.SQLException;

public class DalNotSupportException extends SQLException {
	private static final long serialVersionUID = -3693653625327328721L;

	public DalNotSupportException() {
		super("Zebra Not Supported");
	}
}
