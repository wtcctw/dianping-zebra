package com.dianping.zebra.group.jdbc;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Before;

public abstract class BaseDatabaseTestCase {

	private static final String JDBC_DRIVER = org.h2.Driver.class.getName();

	private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

	private static final String PASSWORD = "";

	private static final String USER = "sa";

	private DPGroupDataSource dataSource;

	// private String configManagerType = "local";

	// private String resourceId = "sample.ds";

	// private String dataSets = "datasets.xml";

	// private String schema = "src/test/resources/schema.sql";

	private void cleanlyInsert(IDataSet dataSet) throws Exception {
		IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Before
	public void createTableAndImportDataSet() throws Exception {
		RunScript.execute(JDBC_URL, USER, PASSWORD, getSchema(), "UTF8", false);
		cleanlyInsert(readDataSet());
	}

	protected DataSource getDataSource() {
		if (this.dataSource == null) {
			this.dataSource = new DPGroupDataSource(getConfigManagerType(), getResourceId());
			this.dataSource.init();
		}

		return this.dataSource;
	}

	protected abstract String getConfigManagerType();

	protected abstract String getDataSets();

	protected abstract String getResourceId();

	protected abstract String getSchema();

	private IDataSet readDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResource(getDataSets()));
	}
}