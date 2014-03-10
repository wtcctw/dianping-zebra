/**
 * Project: zebra-environment
 * 
 * File Created at Mar 10, 2014
 * 
 */
package com.dianping.zebra.environment.filter;

import com.dianping.zebra.group.router.CustomizedReadWriteStrategy;

/**
 * @author Leo Liang
 *
 */
public class CustomizedReadWriteStrategyImpl implements CustomizedReadWriteStrategy {

	/* (non-Javadoc)
	 * @see com.dianping.zebra.group.router.CustomizedReadWriteStrategy#forceReadFromMaster()
	 */
	@Override
	public boolean forceReadFromMaster() {
		// TODO Auto-generated method stub
		return false;
	}

}
