/**
 * Project: zebra-common
 * 
 * File Created at 2011-6-27
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
package com.dianping.zebra.event;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dianping.zebra.jdbc.param.ParamContext;

/**
 * 基于Statement的同步事件
 * 
 * @author Leo Liang
 * 
 */
public class SBRSyncEvent extends SyncEvent implements Serializable {

	private static final long			serialVersionUID	= 1141593270920972073L;

	private Map<String, List<String>>	identitySqlsMapping	= new HashMap<String, List<String>>();
	private List<ParamContext>			params;

	/**
	 * @return the identitySqlsMapping
	 */
	public Map<String, List<String>> getIdentitySqlsMapping() {
		return identitySqlsMapping;
	}

	/**
	 * @param identitySqlsMapping
	 *            the identitySqlsMapping to set
	 */
	public void setIdentitySqlsMapping(Map<String, List<String>> identitySqlsMapping) {
		this.identitySqlsMapping = identitySqlsMapping;
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

	public void addIdSqlsPair(String id, List<String> sqls) {
		identitySqlsMapping.put(id, sqls);
	}
}
