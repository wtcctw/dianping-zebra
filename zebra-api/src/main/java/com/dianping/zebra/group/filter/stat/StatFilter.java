package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.AbstractJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;

/**
 * Created by Dozer on 9/2/14.
 */
public class StatFilter extends AbstractJdbcFilter {
	@Override public void closeSingleDataSourceAfter(JdbcMetaData metaData) {

	}

	@Override public void closeSingleDataSourceBefore(JdbcMetaData metaData) {

	}

	@Override public void closeSingleDataSourceError(JdbcMetaData metaData, Exception exp) {

	}

	@Override public void closeSingleDataSourceSuccess(JdbcMetaData metaData) {

	}

	@Override public void executeAfter(JdbcMetaData metaData) {

	}

	@Override public void executeBefore(JdbcMetaData metaData) {

	}

	@Override public void executeError(JdbcMetaData metaData, Exception exp) {

	}

	@Override public void executeSuccess(JdbcMetaData metaData) {

	}

	@Override public void findMasterFailOverDataSourceAfter(JdbcMetaData metaData) {

	}

	@Override public void findMasterFailOverDataSourceBefore(JdbcMetaData metaData) {

	}

	@Override public void findMasterFailOverDataSourceError(JdbcMetaData metaData, Exception exp) {

	}

	@Override public void findMasterFailOverDataSourceSuccess(JdbcMetaData metaData) {
		System.out.println(metaData.toString());
	}

	@Override public void getGroupConnectionAfter(JdbcMetaData metaData) {

	}

	@Override public void getGroupConnectionBefore(JdbcMetaData metaData) {

	}

	@Override public void getGroupConnectionError(JdbcMetaData metaData) {

	}

	@Override public void getGroupConnectionSuccess(JdbcMetaData metaData) {

	}

	@Override public void initGroupDataSourceAfter(JdbcMetaData metaData) {

	}

	@Override public void initGroupDataSourceBefore(JdbcMetaData metaData) {

	}

	@Override public void initGroupDataSourceError(JdbcMetaData metaData, Exception exp) {

	}

	@Override public void initGroupDataSourceSuccess(JdbcMetaData metaData) {

	}

	@Override public void initSingleDataSourceAfter(JdbcMetaData metaData) {

	}

	@Override public void initSingleDataSourceBefore(JdbcMetaData metaData) {

	}

	@Override public void initSingleDataSourceError(JdbcMetaData metaData, Exception exp) {

	}

	@Override public void initSingleDataSourceSuccess(JdbcMetaData metaData) {

	}

	@Override public void refreshGroupDataSourceAfter(JdbcMetaData metaData, String propertiesName) {

	}

	@Override public void refreshGroupDataSourceBefore(JdbcMetaData metaData, String propertiesName) {

	}

	@Override public void refreshGroupDataSourceError(JdbcMetaData metaData, String propertiesName, Exception exp) {

	}

	@Override public void refreshGroupDataSourceSuccess(JdbcMetaData metaData, String propertiesName) {

	}

	@Override public void switchFailOverDataSourceAfter(JdbcMetaData metaData) {

	}

	@Override public void switchFailOverDataSourceBefore(JdbcMetaData metaData) {

	}

	@Override public void switchFailOverDataSourceError(JdbcMetaData metaData, Exception exp) {

	}

	@Override public void switchFailOverDataSourceSuccess(JdbcMetaData metaData) {
	}
}
