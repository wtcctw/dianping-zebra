package com.dianping.zebra.group.jdbc;

import org.h2.tools.RunScript;
import org.junit.Before;

public abstract class MultiDatabaseTestCase extends SingleDatabaseTestCase {

	static class DataSourceEntry {
		private String dataSets;

		private String jdbcUrl;

		public DataSourceEntry(String jdbcUrl, String dataSets) {
			this.jdbcUrl = jdbcUrl;
			this.dataSets = dataSets;
		}

		public String getDataSets() {
			return dataSets;
		}

		public String getJdbcUrl() {
			return jdbcUrl;
		}
	}

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

	//please do not override it.
	protected String getDataSets(){
		return null;
	}

	protected abstract DataSourceEntry[] getDataSourceEntryArray();
}
