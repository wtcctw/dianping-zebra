/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-15
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
package com.dianping.zebra.router.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dianping.zebra.jdbc.util.StringUtils;
import com.dianping.zebra.router.config.RouterConfigException;
import com.dianping.zebra.router.rule.engine.GroovyRuleEngine;
import com.dianping.zebra.router.rule.engine.RuleEngine;

/**
 * @author danson.liu
 * 
 */
public class SimpleDataSourceProvider implements DataSourceProvider {

	public static final String	TB_SUFFIX_STYLE_ALL		= "alldb";
	public static final String	TB_SUFFIX_STYLE_EVERY	= "everydb";

	private final String		tableName;
	private final String		dbIndexes;
	private final String		tbSuffix;
	private final String		tbRule;

	private List<DataSourceBO>	dataSourceBOs			= new ArrayList<DataSourceBO>(6);

	public SimpleDataSourceProvider(String tableName, String dbIndexes, String tbSuffix, String tbRule) {
		this.tableName = tableName;
		this.dbIndexes = dbIndexes;
		this.tbSuffix = tbSuffix;
		this.tbRule = tbRule;
		this.init();
	}

	private void init() {
		String[] dbs = dbIndexes.split(",");
		RuleEngine tableRuleEngine = new GroovyRuleEngine(tbRule);
		for (String db : dbs) {
			DataSourceBO dataSourceBO = new DataSourceBO(db);
			dataSourceBO.setTableRuleEngine(tableRuleEngine);
			dataSourceBOs.add(dataSourceBO);
		}
		setTables2DataSource(dataSourceBOs);
	}

	private void setTables2DataSource(List<DataSourceBO> dataSourceBOs) {
		String tbSuffixStyle = StringUtils.substringBefore(tbSuffix, ":");
		String tbSuffixRange = StringUtils.substringAfter(tbSuffix, ":");
		tbSuffixRange = StringUtils.substringBetween(tbSuffixRange, "[", "]");
		String[] ranges = tbSuffixRange.split(",");
		int numPartLen = getNumberPartLength(ranges[1]);
		int startNumPartLen = getNumberPartLength(ranges[0]);
		String suffix = getTableSuffix(ranges[0]);
		int startNum = Integer.parseInt(StringUtils.substring(ranges[0], -1 * startNumPartLen));
		int endNum = Integer.parseInt(StringUtils.substring(ranges[1], -1 * numPartLen));
		int dsSize = dataSourceBOs.size();
		if (TB_SUFFIX_STYLE_ALL.equals(tbSuffixStyle)) {
			int tableNum = endNum - startNum + 1;
			int tablesEachDB = (tableNum % dsSize == 0) ? tableNum / dsSize : tableNum / dsSize + 1;
			for (int i = 0; i < dataSourceBOs.size(); i++) {
				for (int j = 0; j < tablesEachDB; j++) {
					dataSourceBOs.get(i).addPhysicalTables(tableName + suffix + zeroPadding(startNum++, numPartLen));
				}
			}
		} else if (TB_SUFFIX_STYLE_EVERY.equals(tbSuffixStyle)) {
			for (int i = 0; i < dataSourceBOs.size(); i++) {
				for (int j = startNum; j <= endNum; j++) {
					dataSourceBOs.get(i).addPhysicalTables(tableName + suffix + zeroPadding(j, numPartLen));
				}
			}
		} else {
			throw new RouterConfigException("TbSuffix property can only be 'alldb' or 'everydb'.");
		}
	}

	private String zeroPadding(int num, int numPartLen) {
		// return StringUtils.leftPad(String.valueOf(num), numPartLen, '0');
		// no padding temporarily
		return String.valueOf(num);
	}

	private String getTableSuffix(String string) {
		int i = string.length() - 1;
		for (; i >= 0; i--) {
			if (!Character.isDigit(string.charAt(i))) {
				break;
			}
		}
		return string.substring(0, i + 1);
	}

	private int getNumberPartLength(String string) {
		int len = 0;
		char[] charArray = string.toCharArray();
		for (int i = charArray.length - 1; i >= 0; i--) {
			if (Character.isDigit(charArray[i])) {
				len++;
			} else {
				break;
			}
		}
		return len;
	}

	@Override
	public DataSourceBO getDataSource(int dbPos) {
		return dataSourceBOs.get(dbPos);
	}

	@Override
	public Map<String, Set<String>> getAllDBAndTables() {
		Map<String, Set<String>> dbAndTables = new HashMap<String, Set<String>>();
		for (DataSourceBO dataSourceBO : dataSourceBOs) {
			String db = dataSourceBO.getDbIndex();
			if (!dbAndTables.containsKey(db)) {
				dbAndTables.put(db, new HashSet<String>());
			}
			Set<String> tableSet = dbAndTables.get(db);
			for (String physicalTable : dataSourceBO.getPhysicalTables()) {
				tableSet.add(physicalTable);
			}
		}
		return dbAndTables;
	}

}
