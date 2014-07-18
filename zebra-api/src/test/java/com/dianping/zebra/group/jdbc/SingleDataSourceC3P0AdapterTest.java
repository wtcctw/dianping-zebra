package com.dianping.zebra.group.jdbc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.dianping.zebra.group.jdbc.SingleDataSource;
import com.google.common.collect.Lists;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class SingleDataSourceC3P0AdapterTest {
	// C3P0文档上的标准属性
	public static List<String> PROPERTIES = Lists.newArrayList("acquireIncrement", "acquireRetryAttempts",
	      "acquireRetryDelay", "autoCommitOnClose", "automaticTestTable", "breakAfterAcquireFailure", "checkoutTimeout",
	      "connectionCustomizerClassName", "connectionTesterClassName", "contextClassLoaderSource", "dataSourceName",
	      "debugUnreturnedConnectionStackTraces", "driverClass", "extensions", "factoryClassLocation",
	      "forceIgnoreUnresolvedTransactions", "forceUseNamedDriverClass", "idleConnectionTestPeriod",
	      "initialPoolSize", "jdbcUrl", "maxAdministrativeTaskTime", "maxConnectionAge", "maxIdleTime",
	      "maxIdleTimeExcessConnections", "maxPoolSize", "maxStatements", "maxStatementsPerConnection", "minPoolSize",
	      "numHelperThreads", "overrideDefaultUser", "overrideDefaultPassword", "password", "preferredTestQuery",
	      "privilegeSpawnedThreads", "propertyCycle", "statementCacheNumDeferredCloseThreads",
	      "testConnectionOnCheckin", "testConnectionOnCheckout", "unreturnedConnectionTimeout", "user");

	@Test
	public void test_atomich_refresh() {
		SingleDataSource.AtomicRefresh ar = new SingleDataSource.AtomicRefresh();

		ar.setUser("user");
		Assert.assertFalse(ar.needToRefresh());
		ar.setUser("user1");
		Assert.assertFalse(ar.needToRefresh());

		ar.setPassword("password");
		Assert.assertTrue(ar.needToRefresh());

		Assert.assertEquals("user1", ar.getNewUser());
		Assert.assertEquals("password", ar.getNewPassword());

		ar.reset();

		ar.setPassword("password3");
		Assert.assertFalse(ar.needToRefresh());
		ar.setUser("user3");
		Assert.assertTrue(ar.needToRefresh());
	}

	@Test
	public void test_atomich_refresh_with_null() {
		SingleDataSource.AtomicRefresh ar = new SingleDataSource.AtomicRefresh();
		ar.setUser(null);
		Assert.assertFalse(ar.needToRefresh());
		ar.setPassword(null);
		Assert.assertFalse(ar.needToRefresh());
		ar.setUser("user");
		Assert.assertFalse(ar.needToRefresh());
		ar.setPassword("password");
		Assert.assertTrue(ar.needToRefresh());

		ar.reset();

		ar.setUser(null);
		Assert.assertFalse(ar.needToRefresh());

		ar.setPassword(null);
		Assert.assertTrue(ar.needToRefresh());
	}

	@Test
	public void test_properties() {
		Map<String, Method> currentMethods = new HashMap<String, Method>();
		for (Method m : SingleDataSource.class.getMethods()) {
			currentMethods.put(m.getName(), m);
		}

		Map<String, Method> c3p0Methods = new HashMap<String, Method>();
		for (Method m : ComboPooledDataSource.class.getMethods()) {
			c3p0Methods.put(m.getName(), m);
		}

		for (String name : PROPERTIES) {
			name = "set" + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
			Method c3p0Method = c3p0Methods.get(name);

			// c3p0 中没有这个方法，忽略
			if (c3p0Method == null) {
				System.out.println(name + " not exits in c3p0Methods!");
				continue;
			}

			Method currentMethod = currentMethods.get(name);
			Assert.assertNotNull(name + " not exist!", currentMethod);

			Assert.assertEquals(name + " too many argument", currentMethod.getParameterTypes().length, 1);
			Assert.assertEquals(name + " arguments type not match", currentMethod.getParameterTypes()[0],
			      c3p0Method.getParameterTypes()[0]);
		}
	}
}
