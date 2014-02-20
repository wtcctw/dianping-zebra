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
public class JDBCExceptionUtils {

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
}
