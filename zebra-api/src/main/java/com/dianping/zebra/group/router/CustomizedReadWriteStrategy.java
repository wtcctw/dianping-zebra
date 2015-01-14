/**
 * Project: zebra-client
 *
 * File Created at Mar 10, 2014
 *
 */
package com.dianping.zebra.group.router;

import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

/**
 * @author Leo Liang
 */
public interface CustomizedReadWriteStrategy {
	boolean forceReadFromMaster();
	void setGroupDataSourceConfig(GroupDataSourceConfig config);
}
