package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.datasources.SingleDataSource;
import com.dianping.zebra.group.jdbc.GroupConnection;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.jdbc.GroupResultSet;
import com.dianping.zebra.group.jdbc.GroupStatement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dozer on 9/2/14.
 */
public interface JdbcFilter {
	int DEFAULT_ORDER = 0;

	int MAX_ORDER = Integer.MAX_VALUE;

	int MIN_ORDER = Integer.MIN_VALUE;

	void closeGroupConnection(GroupConnection source, JdbcFilter chain) throws SQLException;

	void closeGroupDataSource(GroupDataSource source, JdbcFilter chain) throws SQLException;

	void closeSingleConnection(SingleConnection source, JdbcFilter chain) throws SQLException;

	void closeSingleDataSource(SingleDataSource source, JdbcFilter chain) throws SQLException;

	<T> T execute(GroupStatement source, Connection conn, String sql, List<String> batchedSql, boolean isBatched,
	      boolean autoCommit, Object params, JdbcFilter chain) throws SQLException;

	FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(
	      FailOverDataSource.MasterDataSourceMonitor source, JdbcFilter chain);

	GroupConnection getGroupConnection(GroupDataSource source, JdbcFilter chain) throws SQLException;

	/**
	 * filter_with_order_3_start filter_with_order_2_start filter_with_order_1_start targer_start filter_with_order_1_finish
	 * filter_with_order_2_finish filter_with_order_3_finish
	 *
	 * @return the order of execute
	 */
	int getOrder();

	SingleConnection getSingleConnection(SingleDataSource source, JdbcFilter chain) throws SQLException;

	void init();

	void initGroupDataSource(GroupDataSource source, JdbcFilter chain);

	DataSource initSingleDataSource(SingleDataSource source, JdbcFilter chain);

	void refreshGroupDataSource(GroupDataSource source, String propertiesName, JdbcFilter chain);

	boolean resultSetNext(GroupResultSet source, JdbcFilter chain) throws SQLException;

	String sql(SingleConnection conn, String sql, boolean isPreparedStmt, JdbcFilter chain) throws SQLException;

	void switchFailOverDataSource(FailOverDataSource source, JdbcFilter chain);
}
