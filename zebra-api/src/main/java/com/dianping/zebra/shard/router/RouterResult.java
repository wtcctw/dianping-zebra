/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-6-7
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
package com.dianping.zebra.shard.router;

import java.util.List;

import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;

/**
 * 
 * @author hao.zhu
 *
 */
public class RouterResult {

	public static final int NO_SKIP = Integer.MIN_VALUE;

	public static final int NO_MAX = Integer.MAX_VALUE;

	private List<RouterTarget> targetedSqls;

	private List<Object> newParams;

	private int skip = NO_SKIP;

	private int max = NO_MAX;

	private List<SQLSelectItem> selectLists;

	private SQLOrderBy orderBy;

	private String generatedPK;

	private boolean acrossTable;

	private List<String> groupBys;

	private boolean hasDistinct;


	public List<RouterTarget> getTargetedSqls() {
		return targetedSqls;
	}

	public void setTargetedSqls(List<RouterTarget> targetedSqls) {
		this.targetedSqls = targetedSqls;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public List<Object> getNewParams() {
		return newParams;
	}

	public List<SQLSelectItem> getSelectLists() {
		return selectLists;
	}

	public void setSelectLists(List<SQLSelectItem> selectLists) {
		this.selectLists = selectLists;
	}

	public SQLOrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(SQLOrderBy orderBy) {
		this.orderBy = orderBy;
	}

	public void setNewParams(List<Object> newParams) {
		this.newParams = newParams;
	}

	public List<String> getGroupBys() {
		return groupBys;
	}

	public void setGroupBys(List<String> groupBys) {
		this.groupBys = groupBys;
	}

	public boolean isAcrossTable() {
		return acrossTable;
	}

	public void setAcrossTable(boolean acrossTable) {
		this.acrossTable = acrossTable;
	}

	public boolean isHasDistinct() {
		return hasDistinct;
	}

	public void setHasDistinct(boolean hasDistinct) {
		this.hasDistinct = hasDistinct;
	}

	public String getGeneratedPK() {
		return generatedPK;
	}

	public void setGeneratedPK(String generatedPK) {
		this.generatedPK = generatedPK;
	}
}
