package com.dianping.zebra.group.config;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.transform.DefaultSaxParser;

public class DataSourceConfigManagerTest {
	
	@Test
	public void testConfig() throws SAXException, IOException{
		DataSourceConfigManager dataSourceConfigManager = DataSourceConfigManagerFactory.getConfigManager("local", "sample.ds.v2");
		Map<String, DataSourceConfig> config = dataSourceConfigManager.getDataSourceConfigs();
	
		Map<String, DataSourceConfig> dataSourceConfigs = DefaultSaxParser.parse(getClass().getClassLoader().getResourceAsStream("model/datasources.xml")).getDataSourceConfigs();
		
		for(DataSourceConfig entry : config.values()){
			DataSourceConfig dataSourceConfig = dataSourceConfigs.get(entry.getId());
			
			Assert.assertEquals(dataSourceConfig.toString(),entry.toString());
		}
		
		System.out.println(dataSourceConfigManager.getGroupDataSourceConfig());
	}
}
