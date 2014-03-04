package com.dianping.zebra.group.config;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.group.Constants;

public class LocalConfigServiceTest {

	private String resourceId = "zebra.system";

	private ConfigService localConfigService;

	private Properties props;

	@Before
	public void setup() throws IOException {
		props = loadProperties(resourceId);
		localConfigService = new LocalConfigService(resourceId);
		localConfigService.init();
	}

	@Test
	public void testGetKey() {
		Assert.assertEquals("10", localConfigService.getProperty(getKey(Constants.ELEMENT_HEALTH_CHECK_INTERVAL)));
		Assert.assertEquals("3", localConfigService.getProperty(getKey(Constants.ELEMENT_MAX_ERROR_COUNTER)));
		Assert.assertEquals("2", localConfigService.getProperty(getKey(Constants.ELEMENT_RETRY_TIMES)));
		Assert.assertEquals(".dianping.com", localConfigService.getProperty(getKey(Constants.ELEMENT_COOKIE_DOMAIN)));
		Assert.assertEquals("zebra", localConfigService.getProperty(getKey(Constants.ELEMENT_COOKIE_NAME)));
		Assert.assertEquals("xaxd", localConfigService.getProperty(getKey(Constants.ELEMENT_ENCRYPT_SEED)));
	}

	@Test
	public void testChangeKeyAndGet() throws IOException, InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		Properties prop = loadProperties(resourceId);
		prop.setProperty(getKey(Constants.ELEMENT_HEALTH_CHECK_INTERVAL), "5");
		saveProperties(resourceId, prop);

//		 System.in.read();
		TimeUnit.SECONDS.sleep(5);

		Assert.assertEquals("5", localConfigService.getProperty(getKey(Constants.ELEMENT_HEALTH_CHECK_INTERVAL)));
	}

	private String getKey(String key) {
		return resourceId + "." + key;
	}

	@After
	public void recover() throws IOException {
		saveProperties(resourceId, props);
	}

	private Properties loadProperties(String resourceId) throws IOException {
		String resourceFileName = resourceId + ".properties";
		Properties prop = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(resourceFileName);
			prop.load(inputStream);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		return prop;
	}

	private void saveProperties(String resourceId, Properties properties) throws IOException {
		URL url = getClass().getClassLoader().getResource(resourceId + ".properties");

		FileWriter writer = null;
		try {
			writer = new FileWriter(url.getPath());
			properties.store(writer, "New Properties");
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
}
