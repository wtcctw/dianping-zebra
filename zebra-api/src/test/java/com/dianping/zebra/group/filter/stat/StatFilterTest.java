package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.jdbc.MultiDatabaseTestCase;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dozer on 9/29/14.
 */
public class StatFilterTest extends MultiDatabaseTestCase {

	@Before
	public void createTable() throws SQLException {
		RunScript.execute("jdbc:h2:mem:test;MVCC=TRUE;DB_CLOSE_DELAY=-1", USER, PASSWORD, getSchema(), "UTF8", false);
		RunScript.execute("jdbc:h2:mem:test1;MVCC=TRUE;DB_CLOSE_DELAY=-1", USER, PASSWORD, getSchema(), "UTF8", false);
		RunScript.execute("jdbc:h2:mem:test2;MVCC=TRUE;DB_CLOSE_DELAY=-1", USER, PASSWORD, getSchema(), "UTF8", false);
	}

	@Override protected String getConfigManagerType() {
		return Constants.CONFIG_MANAGER_TYPE_LOCAL;
	}

	@Override
	protected DataSourceEntry[] getDataSourceEntryArray() {
		DataSourceEntry[] entries = new DataSourceEntry[0];
		return entries;
	}

	@Override
	protected String getResourceId() {
		return "sample.ds.v2";
	}

	@Override
	protected String getSchema() {
		return getClass().getResource("/schema.sql").getPath();
	}

	@Test
	public void test_insert_with_generate_key() throws Exception {
		StatContext.reset();

		final AtomicInteger key1 = new AtomicInteger();
		final AtomicInteger key2 = new AtomicInteger();

		long insertCount = StatContext.getExecuteSummary().getInsertRow().get();

		execute(new StatementCallback() {
			@Override public Object doInStatement(Statement stmt) throws Exception {
				Object result = stmt.executeUpdate("insert into PERSON (NAME,LAST_NAME,AGE) values ('a','b',1)", 1);
				ResultSet key = stmt.getGeneratedKeys();
				key.next();
				key1.set(key.getInt(1));
				return result;
			}
		});

		execute(new StatementCallback() {
			@Override public Object doInStatement(Statement stmt) throws Exception {
				Object result = stmt.executeUpdate("insert into PERSON (NAME,LAST_NAME,AGE) values ('a','b',1)", 1);
				ResultSet key = stmt.getGeneratedKeys();
				key.next();
				key2.set(key.getInt(1));
				return result;
			}
		});

		Assert.assertEquals(key2.get() - key1.get(), 1);
		Assert.assertEquals(StatContext.getExecuteSummary().getInsertRow().get() - insertCount, 2);
	}

	@Test
	public void test_preparedstatment__with_batch() throws Exception {
		StatContext.reset();
		execute(new ConnectionCallback() {
			@Override public Object doInConnection(Connection conn) throws Exception {
				PreparedStatement pre = conn.prepareStatement("insert into PERSON (NAME,LAST_NAME,AGE) values (?,?,?)");
				pre.setString(1, "a");
				pre.setString(2, "v");
				pre.setInt(3, 1);
				pre.addBatch();

				pre.setString(1, "aa");
				pre.setString(2, "vv");
				pre.setInt(3, 3);
				pre.addBatch();

				pre.executeBatch();
				Assert.assertEquals(2, StatContext.getExecuteSummary().getInsertRow().get());
				Assert.assertEquals(1, StatContext.getExecute().size());
				Assert.assertEquals(2, Lists.newArrayList(StatContext.getExecute().values()).get(0).getInsertRow().get());
				return null;
			}
		});
	}

	@Test
	public void test_statment_with_batch() throws Exception {
		execute(new StatementCallback() {
			@Override public Object doInStatement(Statement stmt) throws Exception {
				stmt.executeUpdate("delete from PERSON");
				StatContext.reset();
				stmt.addBatch("insert into PERSON (NAME,LAST_NAME,AGE) values ('a','b',1)");
				stmt.addBatch("insert into PERSON (NAME,LAST_NAME,AGE) values ('a','b',1)");
				stmt.addBatch("update PERSON set Name = 'ccc'");
				stmt.addBatch("update PERSON set Name = 'ccc'");
				stmt.addBatch("delete from PERSON");

				int[] result = stmt.executeBatch();

				Assert.assertEquals(2, StatContext.getExecuteSummary().getInsertRow().get());
				Assert.assertEquals(4, StatContext.getExecuteSummary().getUpdateRow().get());
				Assert.assertEquals(2, StatContext.getExecuteSummary().getDeleteRow().get());
				Assert.assertEquals(1, StatContext.getExecute().size());
				Assert.assertEquals(2, Lists.newArrayList(StatContext.getExecute().values()).get(0).getInsertRow().get());
				Assert.assertEquals(4, Lists.newArrayList(StatContext.getExecute().values()).get(0).getUpdateRow().get());
				Assert.assertEquals(2, Lists.newArrayList(StatContext.getExecute().values()).get(0).getDeleteRow().get());
				return result;
			}
		});
	}
}
