package com.dianping.zebra.group.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class DPDataSourceTestCase extends BaseDatabaseTestCase{

	@Test
	public void testSelect() throws Exception {
		Connection connection = dataSource().getConnection();

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

}
