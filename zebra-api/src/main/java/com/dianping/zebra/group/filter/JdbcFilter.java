package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/2/14.
 */
public interface JdbcFilter {
	void getGroupConnectionAfter(JdbcMetaData metaData);

	void getGroupConnectionBefore(JdbcMetaData metaData);

	void getGroupConnectionError(JdbcMetaData metaData);

	void getGroupConnectionSuccess(JdbcMetaData metaData);

	void initGroupDataSourceAfter(JdbcMetaData metaData);

	void initGroupDataSourceBefore(JdbcMetaData metaData);

	void initGroupDataSourceError(JdbcMetaData metaData);

	void initGroupDataSourceSuccess(JdbcMetaData metaData);

	void refreshGroupDataSourceAfter(JdbcMetaData metaData, String propertiesName);

	void refreshGroupDataSourceBefore(JdbcMetaData metaData, String propertiesName);

	void refreshGroupDataSourceError(JdbcMetaData metaData, String propertiesName, Exception exp);

	void refreshGroupDataSourceSuccess(JdbcMetaData metaData, String propertiesName);
}
