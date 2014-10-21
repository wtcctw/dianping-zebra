package com.dianping.zebra.group.filter.delegate;

/**
 * Created by Dozer on 9/23/14.
 */
public interface FilterAction<S> {
	void execute(S source);
}
