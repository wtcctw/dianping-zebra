package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.jdbc.InnerSingleDatabaseTestCase;

public class SingleDataSourceManagerTest extends InnerSingleDatabaseTestCase {

	private static SingleDataSourceManager manager;

	@BeforeClass
	public static void setup() {
		manager = SingleDataSourceManagerFactory.getDataSourceManager();
	}

	@Test
	@Ignore
	// 已经不会重用 datasource 了
	public void test() throws Exception {
		DataSource single = getDataSource();

		final InnerSingleDataSource dataSource = (InnerSingleDataSource) single;

		Assert.assertEquals(DataSourceState.INITIAL, dataSource.getState());
		execute(new ConnectionCallback() {

			@Override
			public Object doInConnection(Connection conn) throws Exception {
				Assert.assertEquals(DataSourceState.UP, dataSource.getState());
				return null;
			}
		});

		manager.destoryDataSource(dataSource);

		TimeUnit.SECONDS.sleep(1);
		Assert.assertEquals(DataSourceState.CLOSED, dataSource.getState());
	}

	public DataSourceConfig getDataSourceConfig() {
		DataSourceConfig config = new DataSourceConfig();

		config.setJdbcUrl(JDBC_URL);
		config.setDriverClass(JDBC_DRIVER);
		config.setUser(USER);
		config.setPassword(PASSWORD);
		config.setId("ds1");
		config.setActive(true);

		return config;
	}

	protected DataSource getDataSource() {
		return manager.createDataSource(getDataSourceConfig());
	}

	@Override
	protected String getConfigManagerType() {
		return Constants.CONFIG_MANAGER_TYPE_LOCAL;
	}

	@Override
	protected String getDataSets() {
		return "datasets.xml";
	}

	@Override
	protected String getResourceId() {
		return "sample.mysql.v3";
	}

	@Override
	protected String getSchema() {
		return getClass().getResource("/schema.sql").getPath();
	}
}
