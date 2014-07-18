package com.dianping.zebra.group.monitor;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.cat.status.StatusExtension;
import com.dianping.zebra.group.datasources.DataSourceState;

public class SingleDataSourceStatusExtension implements StatusExtension {

	private SingleDataSourceMBean bean;

	public SingleDataSourceStatusExtension(SingleDataSourceMBean bean) {
		this.bean = bean;
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public String getId() {
		return "dal";
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> status = new LinkedHashMap<String, String>();

		if (bean.getState() != DataSourceState.INITIAL) {
			String id = bean.getId();
			status.put(id + "-BusyConnection", Integer.toString(bean.getNumBusyConnection()));
			status.put(id + "-IdleConnection", Integer.toString(bean.getNumIdleConnection()));
			status.put(id + "-FailedCheckouts", Long.toString(bean.getNumFailedCheckouts()));
		}

		return status;
	}
}
