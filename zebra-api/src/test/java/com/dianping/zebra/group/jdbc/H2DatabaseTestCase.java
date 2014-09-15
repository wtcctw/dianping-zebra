package com.dianping.zebra.group.jdbc;

import com.dianping.zebra.group.filter.JdbcFilter;
import com.dianping.zebra.group.filter.MockFilterHelper;
import com.dianping.zebra.group.filter.stat.StatContext;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;

public abstract class H2DatabaseTestCase {
	protected static final String JDBC_DRIVER = org.h2.Driver.class.getName();

	protected static final String JDBC_URL = "jdbc:h2:mem:test;MVCC=TRUE;DB_CLOSE_DELAY=-1";

	protected static final String PASSWORD = "";

	protected static final String USER = "sa";

	protected JdbcFilter mockedFilter;

	protected void cleanlyInsert(String driver, String jdbcUrl, String user, String password, IDataSet dataSet)
			throws Exception {
		IDatabaseTester databaseTester = new JdbcDatabaseTester(driver, jdbcUrl, user, password);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Before
	public void createTableAndImportDataSet() throws Exception {
		RunScript.execute(JDBC_URL, USER, PASSWORD, getSchema(), "UTF8", false);
		cleanlyInsert(JDBC_DRIVER, JDBC_URL, USER, PASSWORD, readDataSet());
	}

	protected abstract String getDataSets();

	protected abstract String getSchema();

	@Before
	public void mockFilter() {
		MockFilterHelper.injectMockFilter();
		mockedFilter = MockFilterHelper.getMockedFilter();
	}

	@After
	public void printStat() {
		System.out.println("==================Stat==================");
		System.out.println(
				String.format("Get GroupConnection Success Times:%d", StatContext.getGroupConnectionSuccessCount.get()));
		System.out.println(
				String.format("Get GroupConnection Error Times:%d", StatContext.getGroupConnectionErrorCount.get()));
		System.out.println(String.format("Close GroupConnection Success Times:%d",
				StatContext.closeGroupConnectionSuccessCount.get()));
		System.out.println(
				String.format("Close GroupConnection Error Times:%d", StatContext.closeGroupConnectionErrorCount.get()));
		System.out.println(String.format("Execute Select Success Times:%d", StatContext.selectSuccessCount.get()));
		System.out.println(String.format("Execute Select Error Times:%d", StatContext.selectErrorCount.get()));
		System.out.println(String.format("Execute Update Success Times:%d", StatContext.updateSuccessCount.get()));
		System.out.println(String.format("Execute Update Error Times:%d", StatContext.updateErrorCount.get()));
		System.out.println(String.format("Execute Insert Success Times:%d", StatContext.insertSuccessCount.get()));
		System.out.println(String.format("Execute Insert Error Times:%d", StatContext.insertErrorCount.get()));
		System.out.println(String.format("Execute Delete Success Times:%d", StatContext.deleteSuccessCount.get()));
		System.out.println(String.format("Execute Delete Error Times:%d", StatContext.deleteErrorCount.get()));

		System.out.println("========================================");
	}

	private IDataSet readDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResource(getDataSets()));
	}
}
