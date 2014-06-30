package com.dianping.zebra.group.datasources;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.WriteDsNotFoundException;
import com.dianping.zebra.group.jdbc.MultiDatabaseTestCase;

public class FailoverDataSourceTest extends MultiDatabaseTestCase {
	
	private FailOverDataSource ds;
	
	private Map<String,Boolean> readOnlyMap = new HashMap<String,Boolean>();

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
			setReadOnly(config);
			if (config.isActive() && config.isCanWrite()) {
				failoverConfigMap.put(key, config);
			}
		}

		ds = new FailOverDataSource(failoverConfigMap);
		ds.initFailFast();

		return ds;
	}
	
	private void setReadOnly(DataSourceConfig config){
		if(readOnlyMap.containsKey(config.getId()) &&
				readOnlyMap.get(config.getId())!= null &&
				readOnlyMap.get(config.getId()).booleanValue()){
			config.setJdbcUrl(config.getJdbcUrl() + ";ACCESS_MODE_DATA=r");
		}
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
		readOnlyMap.put("db1",true);
		test_read_only("db2");
	}

	@Ignore
	public void test_db3_for_write() throws Exception {
		readOnlyMap.put("db1",true);
		readOnlyMap.put("db2",true);
		test_read_only("db3");
	}

	@Test(expected=WriteDsNotFoundException.class)
	public void test_no_write_database_at_init() {
		readOnlyMap.put("db1",true);
		readOnlyMap.put("db2",true);
		readOnlyMap.put("db3",true);
		getDataSource();
		
	}

	public void test_read_only(String targetDb) throws Exception {
		getDataSource();
		
		TimeUnit.SECONDS.sleep(1);
		SingleConnection conn = (SingleConnection) ds.getConnection();

		Assert.assertEquals(targetDb, conn.getDataSource().getId());

		conn.close();
	}
}
