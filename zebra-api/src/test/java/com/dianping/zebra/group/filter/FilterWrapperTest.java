package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/24/14.
 */

import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class FilterWrapperTest {
	@Test
	public void test_execute_with_order() throws SQLException {
		final AtomicInteger counter = new AtomicInteger();

		JdbcFilter filter1 = new DefaultJdbcFilter() {
			@Override public <S, T> T execute(JdbcContext metaData, S source, FilterFunctionWithSQLException<S, T> action)
					throws SQLException {
				Assert.assertEquals(3, counter.incrementAndGet());
				T executeResult = super.execute(metaData, source, action);
				Assert.assertEquals(5, counter.incrementAndGet());
				return executeResult;
			}

			@Override public int getOrder() {
				return 1;
			}
		};

		JdbcFilter filter2 = new DefaultJdbcFilter() {
			@Override public <S, T> T execute(JdbcContext metaData, S source, FilterFunctionWithSQLException<S, T> action)
					throws SQLException {
				Assert.assertEquals(2, counter.incrementAndGet());
				T executeResult = super.execute(metaData, source, action);
				Assert.assertEquals(6, counter.incrementAndGet());
				return executeResult;
			}

			@Override public int getOrder() {
				return 2;
			}
		};

		JdbcFilter filter3 = new DefaultJdbcFilter() {
			@Override public <S, T> T execute(JdbcContext metaData, S source, FilterFunctionWithSQLException<S, T> action)
					throws SQLException {
				Assert.assertEquals(1, counter.incrementAndGet());
				T executeResult = super.execute(metaData, source, action);
				Assert.assertEquals(7, counter.incrementAndGet());
				return executeResult;
			}

			@Override public int getOrder() {
				return 3;
			}
		};

		JdbcFilter filter = new FilterWrapper(Lists.newArrayList(filter2, filter3, filter1));
		filter.execute(null, null, new FilterFunctionWithSQLException<Object, Object>() {
			@Override public Object execute(Object source) throws SQLException {
				Assert.assertEquals(4, counter.incrementAndGet());
				return null;
			}
		});
	}
}
