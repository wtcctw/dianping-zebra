/**
 * Project: zebra-client
 *
 * File Created at Feb 17, 2014
 *
 */
package com.dianping.zebra.group;

/**
 * @author Leo Liang, Dozer
 *
 */
public final class Constants {
	public static final String ZEBRA_VERSION = "2.5.7";

	public static final String SQL_FORCE_WRITE_HINT = "/*+zebra:w*/";

	public static final String CONNECTION_POOL_TYPE_C3P0 = "c3p0";

	public static final String CONFIG_MANAGER_TYPE_REMOTE = "remote";

	public static final String CONFIG_MANAGER_TYPE_LOCAL = "local";

	public static final String DEFAULT_DATASOURCE_GROUP_PRFIX = "groupds";
	
	public static final String DEFAULT_DATASOURCE_ZEBRA_PRFIX = "zebra";

	public static final String DEFAULT_DATASOURCE_SINGLE_PRFIX = "ds";

	public static final String DEFAULT_SYSTEM_RESOURCE_ID = "zebra.v2.system";

	// DataSource
	public static final String ELEMENT_APP_REFRESH_FLAG = "appkey.refresh.time";

	public static final String ELEMENT_ACTIVE = "active";

	public static final String ELEMENT_TEST_READONLY_SQL = "testReadOnlySql";

	public static final String ELEMENT_PUNISH_LIMIT = "punishLimit";

	public static final String ELEMENT_TIME_WINDOW = "timeWindow";

	public static final String ELEMENT_RETRY_TIMES = "retryTimes";

	public static final String ELEMENT_DRIVER_CLASS = "driverClass";

	public static final String ELEMENT_JDBC_URL = "url";

	public static final String ELEMENT_PASSWORD = "password";

	public static final String ELEMENT_WARMUP_TIME = "warmupTime";

	public static final String ELEMENT_USER = "username";

	public static final String ELEMENT_ROUTER_STRATEGY = "routerStrategy";

	public static final String ELEMENT_TRANSACTION_FORCE_WREITE = "transactionForceWrite";

	public static final String ELEMENT_WRITE_FIRST = "writeFirst";

	public static final String ELEMENT_PROPERTIES = "properties";

	// System
	public static final String ELEMENT_COOKIE_DOMAIN = "cookieDomain";

	public static final String ELEMENT_HEALTH_CHECK_INTERVAL = "healthCheckInterval";

	public static final String ELEMENT_MAX_ERROR_COUNTER = "maxErrorCounter";

	public static final String ELEMENT_COOKIE_NAME = "cookieName";

	public static final String ELEMENT_ENCRYPT_SEED = "encryptSeed";

	public static final String ELEMENT_COOKIE_EXPIRED_TIME = "cookieExpiredTime";

	// router
	public static final String ROUTER_STRATEGY_ROUNDROBIN = "roundrobin";

	// phoenix
	public static final String PHOENIX_APP_NO_NAME = "noname";
}
