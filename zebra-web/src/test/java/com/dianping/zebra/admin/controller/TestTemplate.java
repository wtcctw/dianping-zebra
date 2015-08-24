package com.dianping.zebra.admin.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:config/spring/local/appcontext-*.xml",
		"classpath*:config/spring/common/appcontext-*.xml" })
public class TestTemplate {

	@Autowired
	MonitorController testMC;

	@Test
	public void TestMC() throws IOException {

		Set<String> jdbcRefs = testMC.getJdbcRefSet("qa");

		for (String jdbcRef : jdbcRefs) {
			DataSourceConfigManager dsCongfigManager = DataSourceConfigManagerFactory.getConfigManager("remote",
					jdbcRef);
			GroupDataSourceConfig groupDsConfig = dsCongfigManager.getGroupDataSourceConfig();
			dsCongfigManager.close();

			Map<String, DataSourceConfig> dsConfMap = groupDsConfig.getDataSourceConfigs();

			for (Map.Entry<String, DataSourceConfig> entry : dsConfMap.entrySet()) {
				DataSourceConfig dsConfig = entry.getValue();

				Pattern p = Pattern.compile("\\.\\d+\\:");
				
				Matcher m = p.matcher(dsConfig.getJdbcUrl());
				
				if(m.find()) {
					if(dsConfig.getJdbcUrl().substring(m.start(), m.end()).equals(".20:")) {
						System.out.println(entry.getKey());
					}
				}
			}
		}
	}
}
