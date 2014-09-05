package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.AbstractJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Dozer on 9/2/14.
 */
public class StatFilter extends AbstractJdbcFilter {
	@Override public void getConnectionAfter(JdbcMetaData metaData) {

	}

	@Override public void getConnectionBefore(JdbcMetaData metaData) {

	}

	@Override public void getConnectionError(JdbcMetaData metaData) {

	}

	@Override public void getConnectionSuccess(JdbcMetaData metaData, Connection connection) {

	}
}
