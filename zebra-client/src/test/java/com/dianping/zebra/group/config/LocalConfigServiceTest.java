package com.dianping.zebra.group.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.junit.Before;

import com.dianping.zebra.group.exception.GroupConfigException;

public class LocalConfigServiceTest {
	
	private String resourceId = "zebra.system";
	
	private ConfigService localConfigService;
	@Before
	public void setup(){
		localConfigService = new LocalConfigService(resourceId);
		
		localConfigService.init();
	}
	
	public void testGetKey(){
		
	}

	private Properties loadProperties(String resourceId) throws IOException {
		String resourceFileName = resourceId + ".properties";
		Properties prop = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(resourceFileName);
			prop.load(inputStream);
		} catch (Throwable e) {
			throw new GroupConfigException(String.format("properties file[%s] doesn't exists.", resourceFileName), e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		return prop;
	}

	private void saveProperties(String resourceId, Properties properties) throws IOException {
		String tempDir = System.getProperty("java.io.tmpdir");
		String resourceFileName = tempDir + File.separator + resourceId + ".properties";

		FileWriter writer = null;
		try {
			writer = new FileWriter(resourceFileName);
			properties.store(writer, "New Properties");
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
}
