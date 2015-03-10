package com.dianping.zebra.mysql;

import java.util.concurrent.TimeUnit;

import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.LionException;
import com.dianping.zebra.mysql.monitor.MySQLConfig;
import com.dianping.zebra.mysql.monitor.MySQLMonitor;

public class StartServer {

	private static final String key1 = "zebra.monitor.fail.limit";

	private static final String key2 = "zebra.monitor.sleep.interval";

	public static void main(String[] args) throws LionException {
		if (args == null || args.length != 2) {
			usage();
			return;
		}

		String jdbcRef = args[0];
		String testSql = args[1];

		MySQLConfig mysqlConfig = new MySQLConfig();
		mysqlConfig.setJdbcRef(jdbcRef);
		mysqlConfig.setTestSql(testSql);

		String limit = ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(key1);
		if (limit != null) {
			mysqlConfig.setPingFailLimit(Integer.parseInt(limit));
		}

		String interval = ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(key2);
		if (interval != null) {
			mysqlConfig.setPingIntervalSeconds(Integer.parseInt(interval));
		}

		MySQLMonitor monitor = new MySQLMonitor(mysqlConfig);

		try {
			monitor.connect();
		} catch (ClassNotFoundException e) {
		}

		while (true) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public static void usage() {
		System.out.println("---dal slave monitor usage---");
		System.out.println("com.dianping.zebra.mysql.StartServer {jdbcRef} {testSql}");
	}
}
