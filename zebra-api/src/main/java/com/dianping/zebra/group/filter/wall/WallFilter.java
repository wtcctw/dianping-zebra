package com.dianping.zebra.group.filter.wall;

import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;
import com.dianping.zebra.group.filter.delegate.FilterFunction;

/**
 * Created by Dozer on 9/24/14.
 */
public class WallFilter extends DefaultJdbcFilter {
	@Override public int getOrder() {
		return MIN_ORDER;
	}

	@Override public <S> String sql(JdbcMetaData metaData, S source, FilterFunction<S, String> action) {
		String result = super.sql(metaData, source, action);

		System.out.println(result);

		return result;
	}
}