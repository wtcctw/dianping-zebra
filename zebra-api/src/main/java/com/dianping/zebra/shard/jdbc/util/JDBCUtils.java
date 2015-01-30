/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-6-10 $Id$
 * 
 * Copyright 2010 dianping.com. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.shard.jdbc.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * TODO Comment of JDBCExceptionUtils
 * 
 * @author Leo Liang
 * 
 */
public class JDBCUtils {
	public static void throwSQLExceptionIfNeeded(List<Throwable> exceptionList) throws SQLException {

		if (exceptionList != null && !exceptionList.isEmpty()) {
			StringWriter buffer = new StringWriter();
			PrintWriter out = null;
			try {
				out = new PrintWriter(buffer);

				for (Throwable exception : exceptionList) {
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
