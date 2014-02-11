/**
 * Project: ${zebra-syncserver.aid}
 * 
 * File Created at 2011-7-26
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
package com.dianping.zebra.syncserver.bo;

import java.io.Serializable;
import java.util.List;

import com.dianping.zebra.jdbc.param.ParamContext;

/**
 * Relay Log事件
 * 
 * @author Leo Liang
 * 
 */
public class RelayEvent implements Serializable {

	private static final long	serialVersionUID	= -2913942431808230253L;

	private String				identity;
	private String				sql;
	private List<ParamContext>	params;

	public RelayEvent() {

	}

	public RelayEvent(String identity, String sql, List<ParamContext> params) {
		this.identity = identity;
		this.sql = sql;
		this.params = params;
	}

	/**
	 * @return the params
	 */
	public List<ParamContext> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(List<ParamContext> params) {
		this.params = params;
	}

	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql
	 *            the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RelayEvent [identity=" + identity + ", sql=" + sql + ", params=" + params + "]";
	}

}
