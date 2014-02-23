package com.dianping.zebra.group.manager;

import java.sql.Connection;

import com.mchange.v2.c3p0.AbstractConnectionCustomizer;

public class DataSourceMonitorCustomizer extends AbstractConnectionCustomizer {

	public void onCheckOut(Connection c, String parentDataSourceIdentityToken) throws Exception {
		C3P0DataSourceRuntimeMonitor.INSTANCE.incCheckedOutCount(parentDataSourceIdentityToken);
	}

	public void onCheckIn(Connection c, String parentDataSourceIdentityToken) throws Exception {
		C3P0DataSourceRuntimeMonitor.INSTANCE.descCheckedOutCount(parentDataSourceIdentityToken);
	}
}