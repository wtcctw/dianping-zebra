package com.dianping.zebra.group.jdbc;

import java.sql.SQLException;

public interface JDBCOperationCallback<T> {
	T doAction() throws SQLException;
}