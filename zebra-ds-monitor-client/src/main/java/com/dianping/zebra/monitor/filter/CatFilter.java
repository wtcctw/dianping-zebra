package com.dianping.zebra.monitor.filter;

import com.dianping.zebra.group.filter.JdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;

import java.sql.Connection;

/**
 * Created by Dozer on 9/5/14.
 */
public class CatFilter implements JdbcFilter {
	@Override public void getConnectionAfter(JdbcMetaData metaData) {

	}

	@Override public void getConnectionBefore(JdbcMetaData metaData) {

	}

	@Override public void getConnectionError(JdbcMetaData metaData) {

	}

	@Override public void getConnectionSuccess(JdbcMetaData metaData, Connection connection) {

	}
}
