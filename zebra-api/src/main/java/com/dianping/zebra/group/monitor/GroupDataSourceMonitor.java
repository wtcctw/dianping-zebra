package com.dianping.zebra.group.monitor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.dianping.cat.status.StatusExtension;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.datasources.DataSourceState;

public class GroupDataSourceMonitor implements StatusExtension {

	private GroupDataSourceMBean groupDataSourceBean;

	public GroupDataSourceMonitor(GroupDataSourceMBean dataSource) {
		this.groupDataSourceBean = dataSource;
	}

	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder(1024 * 3);

		SingleDataSourceMBean writeBean = groupDataSourceBean.getWriteSingleDataSourceMBean();
		if (writeBean != null) {
			sb.append("currentWriter:" + writeBean.getId() + " running at state:" + writeBean.getCurrentState() + "\n");
		} else {
			sb.append("currentWriter: No available writer\n");
		}
		Map<String, SingleDataSourceMBean> readerBeans = groupDataSourceBean.getReaderSingleDataSourceMBean();

		if (readerBeans != null) {
			sb.append("currentReader:");
			for (SingleDataSourceMBean bean : readerBeans.values()) {
				sb.append(bean.getId() + " running at state:" + bean.getCurrentState() + "\n");
			}
		} else {
			sb.append("currentReader: No available readers<br>");
		}

		sb.append("\ndataSourceConfig:\n");

		GroupDataSourceConfig groupDataSourceConfig = new GroupDataSourceConfig();
		HidePasswordVisitor visitor = new HidePasswordVisitor(groupDataSourceConfig);
		groupDataSourceBean.getConfig().accept(visitor);

		sb.append(groupDataSourceConfig);

		return sb.toString();
	}

	@Override
	public String getId() {
		return "dal";
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> status = new LinkedHashMap<String, String>();

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
		if (bean.getState() != DataSourceState.INITIAL) {
			String id = bean.getId();
			status.put(id + "-TotalConnection", Integer.toString(bean.getNumConnections()));
			status.put(id + "-BusyConnection", Integer.toString(bean.getNumBusyConnection()));
			status.put(id + "-IdleConnection", Integer.toString(bean.getNumIdleConnection()));
			status.put(id + "-UnClosedOrphanedConnections", Integer.toString(bean.getNumUnClosedOrphanedConnections()));
		}
	}
}
