package com.dianping.zebra.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.zebra.biz.service.LionService;

@Service
public class DataSourceMonitorConfigBuilder {

	private static final String TEST_SQL = "zebra.monitor.testSql";

	private static final String MAX_FAIL = "zebra.monitor.fail.limit";

	private static final String SLEEP_INTERVAL = "zebra.monitor.sleep.interval";

	private static final String VALID_PERIOD = "zebra.monitor.validPeriod";

	@Autowired
	private LionService lionService;

	public DataSourceMonitorConfig buildMonitorConfig(String dsId) {
		DataSourceMonitorConfig config = new DataSourceMonitorConfig();

		config.setTestSql(lionService.getConfigFromZk(TEST_SQL));
		config.setPingFailLimit(Integer.parseInt(lionService.getConfigFromZk(MAX_FAIL)));
		config.setPingIntervalSeconds(Integer.parseInt(lionService.getConfigFromZk(SLEEP_INTERVAL)));
		config.setValidPeriod(Integer.parseInt(lionService.getConfigFromZk(VALID_PERIOD)));

		config.setAutoMarkDown(true);
		config.setAutoMarkUp(false);
		config.setDelayTime(60);

		return config;
	}
}
