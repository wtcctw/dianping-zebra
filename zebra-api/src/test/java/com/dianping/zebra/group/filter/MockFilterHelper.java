package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/10/14.
 */

import org.mockito.Mockito;

public class MockFilterHelper {
	private static JdbcFilter mockedFilter = Mockito.spy(new DefaultJdbcFilter());

	public static JdbcFilter getMockedFilter() {
		return mockedFilter;
	}

	public static void injectMockFilter() {
		FilterManager manager = FilterManagerFactory.getFilterManager();
		manager.addFilter("mock", mockedFilter);
	}
}