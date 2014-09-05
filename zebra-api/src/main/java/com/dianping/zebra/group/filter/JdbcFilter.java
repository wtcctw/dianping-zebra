package com.dianping.zebra.group.filter;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Dozer on 9/2/14.
 */
public interface JdbcFilter {
	void getConnectionAfter(JdbcMetaData metaData, Properties properties);

	Properties getConnectionBefore(JdbcMetaData metaData);

	void getConnectionError(JdbcMetaData metaData, Properties properties);

	void getConnectionSuccess(JdbcMetaData metaData, Properties properties,Connection connection);
}
