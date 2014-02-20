package com.dianping.zebra.group.manager;

import java.sql.Connection;

import com.mchange.v2.c3p0.AbstractConnectionCustomizer;

public class DataSourceMonitorCustomizer extends AbstractConnectionCustomizer {

	public void onAcquire(Connection c, String parentDataSourceIdentityToken) throws Exception {
		System.out.println("-----------acquire-------------");
	}

	public void onDestroy(Connection c, String parentDataSourceIdentityToken) throws Exception {
		System.out.println("-----------destroy-------------");
	}

	public void onCheckOut(Connection c, String parentDataSourceIdentityToken) throws Exception {
		System.out.println("-----------checkout-------------");
	}

	public void onCheckIn(Connection c, String parentDataSourceIdentityToken) throws Exception {
		System.out.println("-----------checkin-------------");
	}
}
