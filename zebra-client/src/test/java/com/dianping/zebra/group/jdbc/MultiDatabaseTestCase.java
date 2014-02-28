package com.dianping.zebra.group.jdbc;

import org.h2.tools.RunScript;
import org.junit.Before;

public abstract class MultiDatabaseTestCase extends SingleDatabaseTestCase {

	private static final String JDBC_DRIVER = org.h2.Driver.class.getName();

	private static final String PASSWORD = "";

	private static final String USER = "sa";

	@Before
	public void createTableAndImportDataSet() throws Exception {
		for (DataSourceEntry entry : getDataSourceEntryArray()) {
			RunScript.execute(entry.getJdbcUrl(), USER, PASSWORD, getSchema(), "UTF8", false);
			cleanlyInsert(JDBC_DRIVER, entry.getJdbcUrl(), USER, PASSWORD, readDataSet(entry.getDataSets()));
		}
	}

	protected abstract DataSourceEntry[] getDataSourceEntryArray();

	protected String getDataSets(){
		return null;
	}

	static class DataSourceEntry {
		private String jdbcUrl;

		private String dataSets;

		public DataSourceEntry(String jdbcUrl, String dataSets) {
			this.jdbcUrl = jdbcUrl;
			this.dataSets = dataSets;
		}

		public String getJdbcUrl() {
			return jdbcUrl;
		}

		public String getDataSets() {
			return dataSets;
		}
	}
}
