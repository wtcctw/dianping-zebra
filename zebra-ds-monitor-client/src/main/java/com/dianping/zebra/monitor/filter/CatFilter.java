package com.dianping.zebra.monitor.filter;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.status.StatusExtensionRegister;
import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.filter.AbstractJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;
import com.dianping.zebra.group.monitor.GroupDataSourceMBean;
import com.dianping.zebra.group.util.SqlUtils;
import com.dianping.zebra.monitor.monitor.GroupDataSourceMonitor;
import org.unidal.helper.Stringizers;

/**
 * Created by Dozer on 9/5/14.
 */
public class CatFilter extends AbstractJdbcFilter {
	private static String BATCH = "batch";

	private static String DAL_CAT_TYPE = "DAL";

	private ThreadLocal<Transaction> executeTransaction = null;

	private ThreadLocal<Transaction> refreshGroupDataSourceTransaction = null;

	private ThreadLocal<Transaction> switchFailOverDataSourceTransaction = null;

	@Override public void closeSingleDataSourceAfter(JdbcMetaData metaData) {

	}

	@Override public void closeSingleDataSourceBefore(JdbcMetaData metaData) {

	}

	@Override public void closeSingleDataSourceError(JdbcMetaData metaData, Exception exp) {

	}

	@Override public void closeSingleDataSourceSuccess(JdbcMetaData metaData) {
		Cat.logEvent("DataSource.Destoryed", metaData.getDataSourceId());
	}

	@Override public void executeAfter(JdbcMetaData metaData) {
		if (executeTransaction != null) {
			executeTransaction.get().complete();
		}
	}

	@Override public void executeBefore(JdbcMetaData metaData) {
		executeTransaction = newTransaction("SQL", metaData.getSql());
		if (metaData.getSql().equals(BATCH)) {
			executeTransaction.get().addData(Stringizers.forJson().compact().from(metaData.getBatchedSqls()));
		} else {
			executeTransaction.get().addData(metaData.getSql());
		}
	}

	@Override public void executeError(JdbcMetaData metaData, Exception exp) {
		if (executeTransaction != null) {
			Cat.logError(exp);
			executeTransaction.get().setStatus(exp);
		}
	}

	@Override public void executeSuccess(JdbcMetaData metaData) {
		if (executeTransaction != null) {

			if (metaData.getConnection() instanceof SingleConnection) {
				SingleConnection conn = (SingleConnection) metaData.getConnection();

				Cat.logEvent("SQL.Database", conn.getJdbcMetaData().getJdbcUrl(), Event.SUCCESS,
						conn.getJdbcMetaData().getDataSourceId());
				Cat.logEvent("SQL.Method", SqlUtils.buildSqlType(metaData.getSql()), Transaction.SUCCESS,
						Stringizers.forJson().compact()
								.from(metaData.getParams(), CatConstants.MAX_LENGTH, CatConstants.MAX_ITEM_LENGTH));
			}

			executeTransaction.get().setStatus(Transaction.SUCCESS);
		}
	}

	@Override public void findMasterFailOverDataSourceAfter(JdbcMetaData metaData) {

	}

	@Override public void findMasterFailOverDataSourceBefore(JdbcMetaData metaData) {

	}

	@Override public void findMasterFailOverDataSourceError(JdbcMetaData metaData, Exception exp) {

	}

	@Override public void findMasterFailOverDataSourceSuccess(JdbcMetaData metaData) {
		Cat.logEvent("DAL.Master", "Found-" + metaData.getDataSourceId());
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
		if (metaData.getDataSource() instanceof GroupDataSourceMBean) {
			StatusExtensionRegister.getInstance()
					.register(new GroupDataSourceMonitor((GroupDataSourceMBean) metaData.getDataSource()));
		}
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
		Cat.logEvent("DataSource.Created", metaData.getDataSourceId());
	}

	private ThreadLocal<Transaction> newTransaction(String type, String name) {
		ThreadLocal<Transaction> temp = new ThreadLocal<Transaction>();
		temp.set(Cat.newTransaction(type, name));
		return temp;
	}

	@Override public void refreshGroupDataSourceAfter(JdbcMetaData metaData, String propertiesName) {
		switchFailOverDataSourceTransaction.get().complete();
		refreshGroupDataSourceTransaction = null;
	}

	@Override public void refreshGroupDataSourceBefore(JdbcMetaData metaData, String propertiesName) {
		refreshGroupDataSourceTransaction = newTransaction(DAL_CAT_TYPE, "DataSource.Refresh-" + metaData.getJdbcRef());
		Cat.logEvent("DAL.Refresh.Property", propertiesName);
	}

	@Override public void refreshGroupDataSourceError(JdbcMetaData metaData, String propertiesName, Exception exp) {
		if (refreshGroupDataSourceTransaction != null) {
			refreshGroupDataSourceTransaction.get().setStatus(exp);
		}
	}

	@Override public void refreshGroupDataSourceSuccess(JdbcMetaData metaData, String propertiesName) {
		if (refreshGroupDataSourceTransaction != null) {
			refreshGroupDataSourceTransaction.get().setStatus(Message.SUCCESS);
		}
	}

	@Override public void switchFailOverDataSourceAfter(JdbcMetaData metaData) {
		if (switchFailOverDataSourceTransaction != null) {
			switchFailOverDataSourceTransaction.get().complete();
			switchFailOverDataSourceTransaction = null;
		}
	}

	@Override public void switchFailOverDataSourceBefore(JdbcMetaData metaData) {
		switchFailOverDataSourceTransaction = newTransaction(DAL_CAT_TYPE, "FailOver");
	}

	@Override public void switchFailOverDataSourceError(JdbcMetaData metaData, Exception exp) {
		if (switchFailOverDataSourceTransaction != null) {
			Cat.logEvent("DAL.FailOver", "Failed");
			switchFailOverDataSourceTransaction.get().setStatus("Fail to find any master database");
		}
	}

	@Override public void switchFailOverDataSourceSuccess(JdbcMetaData metaData) {
		if (switchFailOverDataSourceTransaction != null) {
			Cat.logEvent("DAL.FailOver", "Success");
			switchFailOverDataSourceTransaction.get().setStatus(Message.SUCCESS);
		}
	}

}
