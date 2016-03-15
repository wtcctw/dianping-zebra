/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * <p/>
 * File Created at 2011-6-15
 * $Id$
 * <p/>
 * Copyright 2010 dianping.com.
 * All rights reserved.
 * <p/>
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.shard.router.rule;

import com.dianping.zebra.shard.exception.RouterConfigException;
import com.dianping.zebra.shard.router.rule.engine.GroovyRuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngine;
import com.dianping.zebra.util.StringUtils;

import java.util.*;

/**
 * @author danson.liu
 */
public class SimpleDataSourceProvider implements DataSourceProvider {

	public static final String TB_SUFFIX_STYLE_ALL = "alldb";

	public static final String TB_SUFFIX_STYLE_EVERY = "everydb";

	private final String tableName;

	private final String dbIndexes;

	private final String tbSuffix;

	private final String tbRule;

	private List<DataSourceBO> dataSourceBOs = new ArrayList<DataSourceBO>(6);

	protected SimpleDataSourceProvider() {
		this.tableName = null;
		this.dbIndexes = null;
		this.tbSuffix = null;
		this.tbRule = null;
	}

	public SimpleDataSourceProvider(String tableName, String dbIndexes, String tbSuffix, String tbRule) {
		this.tableName = tableName;
		this.dbIndexes = dbIndexes;
		this.tbSuffix = tbSuffix;
		this.tbRule = tbRule;
		this.init();
	}

	private void init() {
		List<String> dbs = splitDb(dbIndexes);
		RuleEngine tableRuleEngine = new GroovyRuleEngine(tbRule);
		for (String db : dbs) {
			String jdbcRef = db.trim();

			DataSourceBO dataSourceBO = new DataSourceBO(jdbcRef);
			dataSourceBO.setTableRuleEngine(tableRuleEngine);
			dataSourceBOs.add(dataSourceBO);
		}

		setTables2DataSource(dataSourceBOs);
	}

	protected List<String> splitDb(String dbIndexes) {
		List<String> result = new ArrayList<String>();
		String[] dbConfig = dbIndexes.split(",");
		for (String config : dbConfig) {
			if (config.contains("[") && config.contains("]") && config.contains("-")) {
				String suffix = config.substring(0, config.indexOf("["));
				String tbSuffixRange = StringUtils.substringBetween(config, "[", "]");
				String[] ranges = tbSuffixRange.split("-");
				int startNum = Integer.parseInt(ranges[0]);
				int endNum = Integer.parseInt(ranges[1]);
				for (int k = startNum; k <= endNum; k++) {
					result.add(suffix + k);
				}
			} else {
				result.add(config);
			}
		}
		return result;
	}

	private void setTables2DataSource(List<DataSourceBO> dataSourceBOs) {
		String tbSuffixStyle = StringUtils.substringBefore(tbSuffix, ":");
		String tbSuffixRange = StringUtils.substringAfter(tbSuffix, ":");
		tbSuffixRange = StringUtils.substringBetween(tbSuffixRange, "[", "]");
		String[] ranges = tbSuffixRange.split(",");
		if (ranges.length == 2) {
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
						dataSourceBOs.get(i)
								.addPhysicalTables(tableName + suffix + zeroPadding(startNum++, numPartLen));
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
		} else {
			if (TB_SUFFIX_STYLE_ALL.equals(tbSuffixStyle)) {
				if (dataSourceBOs.size() > 1) {
					throw new RouterConfigException("'alldb' cannot support only one table for multiple database");
				}
				dataSourceBOs.get(0).addPhysicalTables(tableName);
			} else if (TB_SUFFIX_STYLE_EVERY.equals(tbSuffixStyle)) {
				for (int i = 0; i < dataSourceBOs.size(); i++) {
					dataSourceBOs.get(i).addPhysicalTables(tableName);
				}
			}
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
