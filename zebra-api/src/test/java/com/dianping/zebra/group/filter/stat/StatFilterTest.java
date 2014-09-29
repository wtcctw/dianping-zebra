package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.jdbc.MultiDatabaseTestCase;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dozer on 9/29/14.
 */
public class StatFilterTest extends MultiDatabaseTestCase {

	@Override protected String getConfigManagerType() {
		return Constants.CONFIG_MANAGER_TYPE_LOCAL;
	}

	@Override
	protected DataSourceEntry[] getDataSourceEntryArray() {
		DataSourceEntry[] entries = new DataSourceEntry[3];

		entries[0] = new DataSourceEntry("jdbc:h2:mem:test;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets.xml", true);
		entries[1] = new DataSourceEntry("jdbc:h2:mem:test1;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets1.xml", false);
		entries[2] = new DataSourceEntry("jdbc:h2:mem:test2;MVCC=TRUE;DB_CLOSE_DELAY=-1", "datasets2.xml", false);

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
	public void test_insert_with_batch() throws Exception {
		final long insertCount = StatContext.getExecuteSummary().getInsertRow().get();

		execute(new StatementCallback() {
			@Override public Object doInStatement(Statement stmt) throws Exception {
				stmt.addBatch("insert into PERSON (NAME,LAST_NAME,AGE) values ('a','b',1)");
				stmt.addBatch("insert into PERSON (NAME,LAST_NAME,AGE) values ('a','b',1)");

				int[] result = stmt.executeBatch();

				Assert.assertEquals(2, StatContext.getExecuteSummary().getInsertRow().get() - insertCount);

				return result;
			}
		});
	}

	@Test
	public void test_insert_with_generate_key() throws Exception {
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
}
