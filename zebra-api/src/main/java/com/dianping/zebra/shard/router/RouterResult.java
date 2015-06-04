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

import com.dianping.zebra.shard.parser.sqlParser.Columns;
import com.dianping.zebra.shard.parser.sqlParser.DMLCommon;
import com.dianping.zebra.shard.parser.sqlParser.OrderBy;
import com.dianping.zebra.shard.router.rule.ShardMatchResult;

/**
 * @author danson.liu
 *
 */
public class RouterResult {

	/**
	 * 无需考虑skip时的返回值，a: sql中确实没有, b: 只有一条select sql，无需考虑result sets merge
	 */
	public static final int NO_SKIP = DMLCommon.DEFAULT_SKIP_MAX;

	public static final int NO_MAX = DMLCommon.DEFAULT_SKIP_MAX;

	private List<RouterTarget> targetedSqls;
	
	private List<OrderBy> orderBys;
	
	private int skip = NO_SKIP;
	
	private int max = NO_MAX;
	
	private Columns columns;
	
	/**
	 * 路由涉及到的拆分表的自增主键名
	 */
	private String generatedPK;
	
	private List<String> groupBys;
	
	private boolean hasDistinct;
	
	private List<Object> newParams;
	
	private ShardMatchResult shardResult;
	
	public ShardMatchResult getShardResult() {
		return shardResult;
	}

	public void setShardResult(ShardMatchResult shardResult) {
		this.shardResult = shardResult;
	}

	public List<RouterTarget> getTargetedSqls() {
		return targetedSqls;
	}

	public void setTargetedSqls(List<RouterTarget> targetedSqls) {
		this.targetedSqls = targetedSqls;
	}

	public List<OrderBy> getOrderBys() {
		return orderBys;
	}

	public void setOrderBys(List<OrderBy> orderBys) {
		this.orderBys = orderBys;
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

	public Columns getColumns() {
		return columns;
	}

	public void setColumns(Columns columns) {
		this.columns = columns;
	}

	public List<Object> getNewParams() {
		return newParams;
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
