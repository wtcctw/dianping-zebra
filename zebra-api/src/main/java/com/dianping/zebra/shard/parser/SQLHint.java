package com.dianping.zebra.shard.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dianping.zebra.Constants;
import com.dianping.zebra.util.StringUtils;

public class SQLHint {

	private boolean forceMaster = false;

	private String shardColumn = null;

	private Map<String, List<String>> columns;

	public String getForceMasterComment() {
		if (forceMaster) {
			return Constants.SQL_FORCE_WRITE_HINT;
		} else {
			return null;
		}
	}

	public static SQLHint parseHint(String hint) {
		if (StringUtils.isNotBlank(hint)) {
			SQLHint sqlHint = new SQLHint();

			int pos = hint.trim().lastIndexOf("*/");
			hint = hint.substring(2, pos);
			StringBuilder sb = new StringBuilder(64);
			String key = null;
			boolean startHint = false;
			for (int i = 0; i < hint.length(); i++) {
				char c = hint.charAt(i);

				if (c == ':') {
					startHint = true;
				} else if (c == '|') {
					if (key == null) {
						key = sb.toString();
						if (key.equalsIgnoreCase("w")) {
							sqlHint.setForceMaster(true);
							sb.setLength(0);
						}
					} else {
						if (key.equalsIgnoreCase("skv")) {
							parseSubHintShardingKeyValue(sqlHint, sb.toString());
							sb.setLength(0);
							key = null;
						} else if (key.equalsIgnoreCase("sk")) {
							sqlHint.setShardColumn(sb.toString());
							sb.setLength(0);
							key = null;
						}
					}
				} else if (c == '=') {
					key = sb.toString();
					sb.setLength(0);
				} else if (startHint) {
					sb.append(c);
				} else {
					// skip prefix : +zebra
				}
			}

			if (sb.toString().equalsIgnoreCase("w")) {
				sqlHint.setForceMaster(true);
			}

			if (key != null) {
				if (key.equalsIgnoreCase("skv")) {
					parseSubHintShardingKeyValue(sqlHint, sb.toString());
					sb.setLength(0);
				} else if (key.equalsIgnoreCase("sk")) {
					sqlHint.setShardColumn(sb.toString());
					sb.setLength(0);
				}
			}

			return sqlHint;
		} else {
			return new SQLHint();
		}
	}

	private static void parseSubHintShardingKeyValue(SQLHint sqlHint, String subHint) {
		Map<String, List<String>> columns = new HashMap<String, List<String>>();

		StringBuilder sb = new StringBuilder(64);
		String key = null;
		for (int i = 0; i < subHint.length(); i++) {
			char c = subHint.charAt(i);

			if (c == '(') {
				key = sb.toString();
				sb.setLength(0);

				List<String> data = columns.get(key);
				if (data == null) {
					data = new ArrayList<String>();

					columns.put(key, data);
				}
			} else if (c == ',' || c == ')') {
				List<String> data = columns.get(key);

				data.add(sb.toString());
				sb.setLength(0);
			} else if (c == '&') {
				// ignore
			} else {
				sb.append(c);
			}
		}

		sqlHint.setColumns(columns);
	}

	public void setForceMaster(boolean forceMaster) {
		this.forceMaster = forceMaster;
	}

	public void setColumns(Map<String, List<String>> columns) {
		this.columns = columns;
	}

	public boolean isForceMaster() {
		return forceMaster;
	}

	public Map<String, List<String>> getColumns() {
		return columns;
	}

	public String getShardColumn() {
		return shardColumn;
	}

	public void setShardColumn(String shardColumn) {
		this.shardColumn = shardColumn;
	}
}
