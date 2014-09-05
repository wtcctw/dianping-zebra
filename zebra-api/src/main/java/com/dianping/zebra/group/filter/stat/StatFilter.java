package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.AbstractJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Dozer on 9/2/14.
 */
public class StatFilter extends AbstractJdbcFilter {
	@Override public void getConnectionAfter(JdbcMetaData metaData, Properties properties) {

	}

	@Override public Properties getConnectionBefore(JdbcMetaData metaData) {
		return null;
	}

	@Override public void getConnectionError(JdbcMetaData metaData, Properties properties) {

	}

	@Override public void getConnectionSuccess(JdbcMetaData metaData, Properties properties, Connection connection) {

	}
}
