package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/2/14.
 */
public interface JdbcFilter {
	void closeSingleDataSourceAfter(JdbcMetaData metaData);

	void closeSingleDataSourceBefore(JdbcMetaData metaData);

	void closeSingleDataSourceError(JdbcMetaData metaData, Exception exp);

	void closeSingleDataSourceSuccess(JdbcMetaData metaData);

	void findMasterFailOverDataSourceAfter(JdbcMetaData metaData);

	void findMasterFailOverDataSourceBefore(JdbcMetaData metaData);

	void findMasterFailOverDataSourceError(JdbcMetaData metaData, Exception exp);

	void findMasterFailOverDataSourceSuccess(JdbcMetaData metaData);

	void getGroupConnectionAfter(JdbcMetaData metaData);

	void getGroupConnectionBefore(JdbcMetaData metaData);

	void getGroupConnectionError(JdbcMetaData metaData);

	void getGroupConnectionSuccess(JdbcMetaData metaData);

	void initGroupDataSourceAfter(JdbcMetaData metaData);

	void initGroupDataSourceBefore(JdbcMetaData metaData);

	void initGroupDataSourceError(JdbcMetaData metaData, Exception exp);

	void initGroupDataSourceSuccess(JdbcMetaData metaData);

	void initSingleDataSourceAfter(JdbcMetaData metaData);

	void initSingleDataSourceBefore(JdbcMetaData metaData);

	void initSingleDataSourceError(JdbcMetaData metaData, Exception exp);

	void initSingleDataSourceSuccess(JdbcMetaData metaData);

	void refreshGroupDataSourceAfter(JdbcMetaData metaData, String propertiesName);

	void refreshGroupDataSourceBefore(JdbcMetaData metaData, String propertiesName);

	void refreshGroupDataSourceError(JdbcMetaData metaData, String propertiesName, Exception exp);

	void refreshGroupDataSourceSuccess(JdbcMetaData metaData, String propertiesName);

	void switchFailOverDataSourceAfter(JdbcMetaData metaData);

	void switchFailOverDataSourceBefore(JdbcMetaData metaData);

	void switchFailOverDataSourceError(JdbcMetaData metaData, Exception exp);

	void switchFailOverDataSourceSuccess(JdbcMetaData metaData);
}
