package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/10/14.
 */

import static org.mockito.Mockito.mock;

public class MockFilterHelper {
	private static JdbcFilter mockedFilter = mock(JdbcFilter.class);

	public static JdbcFilter getMockedFilter() {
		return mockedFilter;
	}

	public static void injectMockFilter() {
		FilterManager manager = FilterManagerFactory.getFilterManager();
		manager.addFilter("mock", mockedFilter);
	}
}