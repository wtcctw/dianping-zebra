package com.dianping.zebra.group.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBCOperationCallback<T> {
	T doAction(Connection conn) throws SQLException;
}