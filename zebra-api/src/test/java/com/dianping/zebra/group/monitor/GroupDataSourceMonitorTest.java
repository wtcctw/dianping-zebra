package com.dianping.zebra.group.monitor;

import java.util.HashMap;
import java.util.Map;

import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import org.junit.*;

import static org.mockito.Mockito.*;

public class GroupDataSourceMonitorTest {
	@Test
	public void test_get_properties() {
		GroupDataSourceMBean bean = mock(GroupDataSourceMBean.class);
		when(bean.getConfig()).thenReturn(new GroupDataSourceConfig());
		SingleDataSourceMBean single = mock(SingleDataSourceMBean.class);

		when(bean.getWriteSingleDataSourceMBean()).thenReturn(single);

		Map<String, SingleDataSourceMBean> readers = new HashMap<String, SingleDataSourceMBean>();

		readers.put("db1", single);

		when(bean.getReaderSingleDataSourceMBean()).thenReturn(readers);

		GroupDataSourceMonitor monitor = new GroupDataSourceMonitor(bean);

		System.out.println(monitor.getDescription());
	}
}
