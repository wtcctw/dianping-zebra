package com.dianping.zebra.group.datasources;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.jdbc.MultiDatabaseTestCase;

public class FailoverDataSourceTest extends MultiDatabaseTestCase {

	@BeforeClass
	public static void setup() {
		new File("/tmp/test.h2.db").setWritable(true);
		new File("/tmp/test1.h2.db").setWritable(true);
		new File("/tmp/test2.h2.db").setWritable(true);
	}

	private FailOverDataSource ds;

	@After
	public void closeDs() throws SQLException {
		if (ds != null) {
			ds.close();
		}
	}

	@Override
	protected String getConfigManagerType() {
		return Constants.CONFIG_MANAGER_TYPE_LOCAL;
	}

	@Override
	protected DataSource getDataSource() {
		DataSourceConfigManager configManager = DataSourceConfigManagerFactory.getConfigManager(getConfigManagerType(),
		      getResourceId());

		Map<String, DataSourceConfig> failoverConfigMap = new HashMap<String, DataSourceConfig>();

		for (Entry<String, DataSourceConfig> entry : configManager.getDataSourceConfigs().entrySet()) {
			String key = entry.getKey();
			DataSourceConfig config = entry.getValue();

			if (config.isActive() && config.isCanWrite()) {
				failoverConfigMap.put(key, config);
			}
		}

		ds = new FailOverDataSource(failoverConfigMap);
		ds.init();

		return ds;
	}

	@Override
	protected DataSourceEntry[] getDataSourceEntryArray() {
		DataSourceEntry[] entries = new DataSourceEntry[3];

		DataSourceEntry entry1 = new DataSourceEntry("jdbc:h2:file:/tmp/test;MVCC=TRUE", "datasets.xml", true);
		DataSourceEntry entry2 = new DataSourceEntry("jdbc:h2:file:/tmp/test1;MVCC=TRUE", "datasets1.xml", false);
		DataSourceEntry entry3 = new DataSourceEntry("jdbc:h2:file:/tmp/test2;MVCC=TRUE", "datasets2.xml", false);

		entries[0] = entry1;
		entries[1] = entry2;
		entries[2] = entry3;

		return entries;
	}

	@Override
	protected String getResourceId() {
		return "sample.ds.v3";
	}

	@Override
	protected String getSchema() {
		return getClass().getResource("/schema.sql").getPath();
	}

	@Test
	public void test_db1_for_write() throws Exception {
		test_read_only("db1");
	}

	@Test
	public void test_db2_for_write() throws Exception {
		File file = new File("/tmp/test.h2.db");
		file.setReadOnly();

		test_read_only("db2");

		file.setWritable(true);
	}

	@Ignore
	public void test_db3_for_write() throws Exception {
		File file = new File("/tmp/test.h2.db");
		file.setReadOnly();
		File file1 = new File("/tmp/test1.h2.db");
		file1.setReadOnly();

		test_read_only("db3");

		file.setWritable(true);
		file1.setWritable(true);
	}

	@Test
	public void test_no_write_database_at_first_loop() {
		File file = new File("/tmp/test.h2.db");
		File file1 = new File("/tmp/test1.h2.db");
		File file2 = new File("/tmp/test2.h2.db");
		file.setReadOnly();
		file1.setReadOnly();
		file2.setReadOnly();

		try {
			getDataSource().getConnection();
		} catch (SQLException e) {
			Assert.assertTrue(e.getMessage().contains("Write dataSource is currently in the maintaining stage"));
		}

		file.setWritable(true);
		file1.setWritable(true);
		file2.setWritable(true);
	}

	public void test_read_only(String targetDb) throws Exception {
		getDataSource();
		
		TimeUnit.SECONDS.sleep(1);
		SingleConnection conn = (SingleConnection) ds.getConnection();

		Assert.assertEquals(targetDb, conn.getDataSource().getId());

		conn.close();
	}
}
