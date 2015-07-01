package com.dianping.zebra.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.zebra.biz.service.LionService;

@Service
public class DataSourceMonitorConfigBuilder {

	private static final String MAX_FAIL = "zebra.monitorservice.failLimit";

	private static final String SLEEP_INTERVAL = "zebra.monitorservice.sleepInterval";

	private static final String VALID_PERIOD = "zebra.monitorservice.validPeriod";
	
	private static final String DELAY = "zebra.monitorservice.delay";
	
	private static final String USERNAME = "zebra.monitorservice.jdbc.username";
	
	private static final String PASSWORD = "zebra.monitorservice.jdbc.password";

	@Autowired
	private LionService lionService;

	public DataSourceMonitorConfig buildMonitorConfig(String dsId) {
		DataSourceMonitorConfig config = new DataSourceMonitorConfig();

		config.setPingFailLimit(Integer.parseInt(lionService.getConfigFromZk(MAX_FAIL)));
		config.setPingIntervalSeconds(Integer.parseInt(lionService.getConfigFromZk(SLEEP_INTERVAL)));
		config.setValidPeriod(Integer.parseInt(lionService.getConfigFromZk(VALID_PERIOD)));
		config.setDelayTime(Integer.parseInt(lionService.getConfigFromZk(DELAY)));
		config.setUsername(lionService.getConfigFromZk(USERNAME));
		config.setPassword(lionService.getConfigFromZk(PASSWORD));

		config.setAutoMarkDown(true);
		config.setAutoMarkUp(false);

		return config;
	}
}
