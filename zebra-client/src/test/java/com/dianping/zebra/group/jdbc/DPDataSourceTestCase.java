package com.dianping.zebra.group.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class DPDataSourceTestCase extends BaseDatabaseTestCase {

	@Test
	public void testSelect() throws Exception {
		Connection connection = getDataSource().getConnection();

		Statement statement = connection.createStatement();
		boolean result = statement.execute("select * from PERSON");

		System.out.println(result);
		if (result) {
			ResultSet rsSet = statement.getResultSet();

			while (rsSet.next()) {
				System.out.println(rsSet.getString(2));
			}
		}
	}

	@Override
	protected String getConfigManagerType() {
		return "local";
	}

	@Override
	protected String getDataSets() {
		return "datasets.xml";
	}

	@Override
	protected String getResourceId() {
		return "sample.ds";
	}

	@Override
	protected String getSchema() {
		return "src/test/resources/schema.sql";
	}

}
