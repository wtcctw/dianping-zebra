package com.dianping.zebra.group.datasources;

import javax.sql.DataSource;

public interface MarkableDataSource extends DataSource{

	DataSourceState getState();
	
	void markClosed();
	
	void markDown();
	
	void markUp();
}
