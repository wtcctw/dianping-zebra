/**
 * Project: zebra-client
 * 
 * File Created at Feb 20, 2014
 * 
 */
package com.dianping.zebra.group.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Leo Liang
 * 
 */
public final class JDBCExceptionUtils {

	// ERROR 1290 (HY000): The MySQL server is running with the --read-only option so it cannot execute this statement
	private final static int READ_ONLY_ERROR_CODE = 1290;

	private final static String READ_ONLY_ERROR_MESSAGE = "read-only";

	public static void throwSQLExceptionIfNeeded(List<SQLException> exceptions) throws SQLException {
		if (exceptions != null && !exceptions.isEmpty()) {
			StringWriter buffer = new StringWriter();
			PrintWriter out = null;
			try {
				out = new PrintWriter(buffer);

				for (SQLException exception : exceptions) {
					exception.printStackTrace(out);
				}
			} finally {
				if (out != null) {
					out.close();
				}
			}

			throw new SQLException(buffer.toString());
		}
	}

	public static void throwWrappedSQLException(SQLException e) throws SQLException {
		if (isReadOnlyException(e)) {
			throw new SQLException("Write dataSource is currently in the maintaining stage. ", e);
		} else {
			throw e;
		}
	}

	public static boolean isReadOnlyException(SQLException e) {
		return e.getErrorCode() == READ_ONLY_ERROR_CODE && e.getMessage().contains(READ_ONLY_ERROR_MESSAGE);
	}
}
