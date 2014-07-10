/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-8
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
package com.dianping.zebra.monitor.sql;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;

/**
 * @author danson.liu
 *
 */
public interface PersonDao extends GenericDao {

	@DAOAction(action = DAOActionType.LOAD)
	public Person findPerson(@DAOParam("id") int id);
	
}
