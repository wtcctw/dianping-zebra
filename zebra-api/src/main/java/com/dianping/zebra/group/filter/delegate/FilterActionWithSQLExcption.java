package com.dianping.zebra.group.filter.delegate;

import java.sql.SQLException;

/**
 * Created by Dozer on 9/23/14.
 */
public interface FilterActionWithSQLExcption<S> {
	void execute(S source) throws SQLException;
}