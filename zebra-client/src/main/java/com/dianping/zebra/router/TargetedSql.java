/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-14
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.router;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author danson.liu
 * 
 */
public class TargetedSql implements Serializable {

	private static final long		serialVersionUID	= 7793984197425884451L;

	private String					dataSourceName;

	private transient DataSource	dataSource;

	private List<String>			sqls;

	public TargetedSql(String dataSourceName, DataSource dataSource, List<String> sqls) {
		this.dataSourceName = dataSourceName;
		this.dataSource = dataSource;
		this.sqls = sqls;
	}

	public TargetedSql(String dataSourceName, DataSource dataSource, String sql) {
		this.dataSourceName = dataSourceName;
		this.dataSource = dataSource;
		this.sqls = Arrays.asList(sql);
	}

	public TargetedSql(NamedDataSource namedDataSource) {
		this(namedDataSource, new ArrayList<String>(100));
	}

	public TargetedSql(NamedDataSource namedDataSource, List<String> sqls) {
		this.dataSourceName = namedDataSource.identity;
		this.dataSource = namedDataSource.dataSource;
		this.sqls = sqls;
	}

	public TargetedSql(NamedDataSource namedDataSource, String sql) {
		this.dataSourceName = namedDataSource.identity;
		this.dataSource = namedDataSource.dataSource;
		this.sqls = Arrays.asList(sql);
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<String> getSqls() {
		return sqls;
	}

	public void setSqls(List<String> sqls) {
		this.sqls = sqls;
	}

	public void addSql(String sql) {
		if (this.sqls == null) {
			this.sqls = new ArrayList<String>();
		}
		this.sqls.add(sql);
	}

}
