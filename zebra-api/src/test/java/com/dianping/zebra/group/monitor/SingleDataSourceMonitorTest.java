package com.dianping.zebra.group.monitor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.dianping.zebra.group.util.DataSourceState;

public class SingleDataSourceMonitorTest {
	@Test
	public void test_get_properties() {
		SingleDataSourceMBean single = mock(SingleDataSourceMBean.class);

		when(single.getState()).thenReturn(DataSourceState.UP);

		SingleDataSourceMonitor monitor = new SingleDataSourceMonitor(single);

		System.out.println(monitor.getDescription());
	}
}
