package com.dianping.zebra.group.jdbc;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.util.StringUtils;
import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dozer on 8/18/14.
 */
public class GroupDataSourceTest {
	private void assert_params(String expected, String actual) {
		expected = expected.split("\\?")[1];
		actual = actual.split("\\?")[1];

		Map<String, String> expectedMap = new HashMap<String, String>();
		Map<String, String> actualMap = new HashMap<String, String>();
		StringUtils.splitStringToMap(expectedMap, expected);
		StringUtils.splitStringToMap(actualMap, actual);

		Assert.assertEquals(expectedMap.size(), actualMap.size());

		for (Map.Entry<String, String> expectedEntity : expectedMap.entrySet()) {
			Assert.assertTrue(expectedEntity.getKey(), actualMap.containsKey(expectedEntity.getKey()));
			Assert.assertEquals(expectedEntity.getKey(), expectedEntity.getValue(),
					actualMap.get(expectedEntity.getKey()));
		}
	}

	private GroupDataSourceConfig initGroupDataSourceConfig(String url) {
		GroupDataSourceConfig groupConfig = new GroupDataSourceConfig();
		DataSourceConfig config = new DataSourceConfig();
		config.setJdbcUrl(url);
		config.setId("1");
		groupConfig.addDataSourceConfig(config);
		return groupConfig;
	}

	private void test_build_group_datasource_config_url_extra(GroupDataSourceConfig config, String extra)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		GroupDataSource target = new GroupDataSource();
		target.setJdbcUrlExtra(extra);
		Method method = GroupDataSource.class
				.getDeclaredMethod("buildGroupConfigJdbcUrlExtra", GroupDataSourceConfig.class);
		method.setAccessible(true);
		method.invoke(target, config);
	}

	@Test
	public void test_build_group_datasource_config_url_extra_with_all()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		GroupDataSourceConfig config = initGroupDataSourceConfig(
				"jdbc:mysql://192.168.8.44:3306/localhost-m1-write?autoReconnect=true&cc=2");
		test_build_group_datasource_config_url_extra(config, "encode=utf8&bb=1");
		assert_params("jdbc:mysql://192.168.8.44:3306/localhost-m1-write?autoReconnect=true&cc=2&encode=utf8&bb=1",
				config.getDataSourceConfigs().get("1").getJdbcUrl());
	}

	@Test
	public void test_build_group_datasource_config_url_extra_with_empty()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		GroupDataSourceConfig config = initGroupDataSourceConfig(
				"jdbc:mysql://192.168.8.44:3306/localhost-m1-write?");
		test_build_group_datasource_config_url_extra(config, null);
		Assert.assertEquals("jdbc:mysql://192.168.8.44:3306/localhost-m1-write",
				config.getDataSourceConfigs().get("1").getJdbcUrl());
	}

	@Test
	public void test_build_group_datasource_config_url_extra_with_empty_extra()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		GroupDataSourceConfig config = initGroupDataSourceConfig(
				"jdbc:mysql://192.168.8.44:3306/localhost-m1-write?autoReconnect=true&cc=2");
		test_build_group_datasource_config_url_extra(config, null);
		assert_params("jdbc:mysql://192.168.8.44:3306/localhost-m1-write?autoReconnect=true&cc=2",
				config.getDataSourceConfigs().get("1").getJdbcUrl());
	}

	@Test
	public void test_build_group_datasource_config_url_extra_with_empty_url()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		GroupDataSourceConfig config = initGroupDataSourceConfig(
				"jdbc:mysql://192.168.8.44:3306/localhost-m1-write");
		test_build_group_datasource_config_url_extra(config, "autoReconnect=true&cc=2");
		assert_params("jdbc:mysql://192.168.8.44:3306/localhost-m1-write?autoReconnect=true&cc=2",
				config.getDataSourceConfigs().get("1").getJdbcUrl());
	}

	@Test
	public void test_build_group_datasource_config_url_extra_with_same_key()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		GroupDataSourceConfig config = initGroupDataSourceConfig(
				"jdbc:mysql://192.168.8.44:3306/localhost-m1-write?autoReconnect=true&cc=1");
		test_build_group_datasource_config_url_extra(config, "autoReconnect=false");
		assert_params("jdbc:mysql://192.168.8.44:3306/localhost-m1-write?autoReconnect=false&cc=1",
				config.getDataSourceConfigs().get("1").getJdbcUrl());
	}
}
