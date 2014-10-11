package com.dianping.zebra.group.config;

import com.dianping.zebra.group.config.DefaultDataSourceConfigManager.GroupDataSourceConfigBuilder;
import com.dianping.zebra.group.config.DefaultDataSourceConfigManager.ReadOrWriteRole;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.config.datasource.transform.DefaultSaxParser;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Map;

public class DataSourceConfigManagerTest {

	public void testAssert(String config, String expected) {
		DefaultDataSourceConfigManager dataSourceConfigManager = (DefaultDataSourceConfigManager) DataSourceConfigManagerFactory
				.getConfigManager("local", "sample.ds.v2");

		GroupDataSourceConfigBuilder groupDataSourceConfigBuilder = dataSourceConfigManager.new GroupDataSourceConfigBuilder();

		Map<String, ReadOrWriteRole> parseConfig = ReadOrWriteRole.parseConfig(config);

		System.out.println(parseConfig);
		Assert.assertEquals(expected, parseConfig.toString());
	}

	@Test
	public void testConfig() throws SAXException, IOException {
		DataSourceConfigManager dataSourceConfigManager = DataSourceConfigManagerFactory.getConfigManager("local",
				"sample.ds.v2");
		Map<String, DataSourceConfig> config = dataSourceConfigManager.getGroupDataSourceConfig().getDataSourceConfigs();

		Map<String, DataSourceConfig> dataSourceConfigs = DefaultSaxParser.parse(
				getClass().getClassLoader().getResourceAsStream("model/datasources.xml")).getDataSourceConfigs();

		for (DataSourceConfig entry : config.values()) {
			DataSourceConfig dataSourceConfig = dataSourceConfigs.get(entry.getId());

			Assert.assertEquals(dataSourceConfig.toString(), entry.toString());
		}

		System.out.println(dataSourceConfigManager.getGroupDataSourceConfig());

		GroupDataSourceConfig groupDataSourceConfig = new GroupDataSourceConfig();
		HidePasswordVisitor visitor = new HidePasswordVisitor(groupDataSourceConfig);
		dataSourceConfigManager.getGroupDataSourceConfig().accept(visitor);
		System.out.println("=====after hide the password=======");
		System.out.println(groupDataSourceConfig);

	}

	@Test
	public void testParseConfig() {
		testAssert("(dianpingmobile-s1-read:1,dianpingmobile-s2-read:2),(dianpingmobile-m1-write)" //
				, "{dianpingmobile-s1-read=r1, dianpingmobile-s2-read=r2, dianpingmobile-m1-write=w}");
		testAssert(
				"(dianpingmobile-s1-read:1,    dianpingmobile-s2-read:1,   dianpingmobile-m1-write:1), (dianpingmobile-m1-write)"
				//
				, "{dianpingmobile-s1-read=r1, dianpingmobile-s2-read=r1, dianpingmobile-m1-write=wr1}");
	}
}
