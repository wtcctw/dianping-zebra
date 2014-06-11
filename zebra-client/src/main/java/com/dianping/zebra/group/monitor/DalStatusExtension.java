package com.dianping.zebra.group.monitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.dianping.cat.status.StatusExtension;
import com.dianping.zebra.group.Constants;

public class DalStatusExtension implements StatusExtension {

	private GroupDataSourceMBean groupDataSourceBean;

	public DalStatusExtension(GroupDataSourceMBean dataSource) {
		this.groupDataSourceBean = dataSource;
	}

	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder(1024 * 3);

		sb.append("version:" + Constants.VERSION + "<br>");
		SingleDataSourceMBean writeBean = groupDataSourceBean.getWriteSingleDataSourceMBean();
		if (writeBean != null) {
			sb.append("currentWriter:" + writeBean.getId() + " running at state[" + writeBean.getCurrentState() + "]<br>");
		} else {
			sb.append("currentWriter: No available writer<br>");
		}
		Map<String, SingleDataSourceMBean> readerBeans = groupDataSourceBean.getReaderSingleDataSourceMBean();

		if (readerBeans != null) {
			sb.append("currentReader:<br>");
			for (SingleDataSourceMBean bean : readerBeans.values()) {
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;" + bean.getId() + " running at state[" + bean.getCurrentState()
				      + "]<br>");
			}
		} else {
			sb.append("currentReader: No available readers<br>");
		}

		sb.append("dataSourceConfig:<br>");
		sb.append(groupDataSourceBean.getConfig());

		return sb.toString();
	}

	@Override
	public String getId() {
		return "dal";
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> status = new HashMap<String, String>();

		Map<String, SingleDataSourceMBean> beans = groupDataSourceBean.getReaderSingleDataSourceMBean();
		if (beans != null) {
			for (Entry<String, SingleDataSourceMBean> entry : beans.entrySet()) {
				putProperty(status, entry.getValue());
			}
		}

		SingleDataSourceMBean bean = groupDataSourceBean.getWriteSingleDataSourceMBean();

		if (bean != null) {
			putProperty(status, bean);
		}

		return status;
	}

	private void putProperty(Map<String, String> status, SingleDataSourceMBean bean) {
		String id = bean.getId();
		status.put(id + "-Connections", Integer.toString(bean.getNumConnections()));
		status.put(id + "-BusyConnection", Integer.toString(bean.getNumBusyConnection()));
		status.put(id + "-IdleConnection", Integer.toString(bean.getNumIdleConnection()));
		status.put(id + "-UnClosedOrphanedConnections", Integer.toString(bean.getNumUnClosedOrphanedConnections()));
		status.put(id + "-FailedCheckins", Long.toString(bean.getNumFailedCheckins()));
		status.put(id + "-FailedCheckouts", Long.toString(bean.getNumFailedCheckouts()));
		status.put(id + "-ThreadPoolSize", Integer.toString(bean.getThreadPoolSize()));
		status.put(id + "-ThreadPoolNumActiveThreads", Integer.toString(bean.getThreadPoolNumActiveThreads()));
		status.put(id + "-ThreadPoolNumIdleThreads", Integer.toString(bean.getThreadPoolNumIdleThreads()));
		status.put(id + "-ThreadPoolNumTasksPending", Integer.toString(bean.getThreadPoolNumTasksPending()));
	}
}
