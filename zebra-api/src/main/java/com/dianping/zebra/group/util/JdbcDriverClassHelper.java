package com.dianping.zebra.group.util;

import com.dianping.zebra.group.exception.IllegalConfigException;

/**
 * Created by Dozer on 8/1/14.
 */
public class JdbcDriverClassHelper {
	private JdbcDriverClassHelper() {
	}

	public static String getDriverClassNameByJdbcUrl(String url) {
		if (url.startsWith("jdbc:mysql:")) {
			return "com.mysql.jdbc.Driver";
		} else if (url.startsWith("jdbc:postgresql:")) {
			return "org.postgresql.Driver";
		} else if (url.startsWith("jdbc:sqlserver:")) {
			return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		} else if (url.startsWith("jdbc:jtds:sqlserver:")) {
			return "net.sourceforge.jtds.jdbc.Driver";
		} else if (url.startsWith("jdbc:h2:")) {
			return "org.h2.Driver";
		} else {
			return "";
		}
	}

	public static void loadDriverClass(String driverClass, String url) throws IllegalConfigException {
		try {
			Class.forName(StringUtils.isEmpty(driverClass) ? getDriverClassNameByJdbcUrl(url) : driverClass);
		} catch (ClassNotFoundException e) {
			throw new IllegalConfigException("Cannot find driver class : " + url, e);
		}
	}
}
