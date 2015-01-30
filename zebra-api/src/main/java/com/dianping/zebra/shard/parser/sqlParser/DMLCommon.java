package com.dianping.zebra.shard.parser.sqlParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.dianping.zebra.shard.parser.Constant;
import com.dianping.zebra.shard.parser.condition.BindIndexHolder;
import com.dianping.zebra.shard.parser.tableObject.TableName;
import com.dianping.zebra.shard.parser.tableObject.imp.TableNameImp;
import com.dianping.zebra.shard.parser.tableObject.imp.TableNameSubQueryImp;
import com.dianping.zebra.shard.parser.valueObject.Value;

public abstract class DMLCommon extends BindIndexHolder implements Value {
	public final static int DEFAULT_SKIP_MAX=-1000;
	Lock repMapLock = new ReentrantLock();
	protected List<TableName> tbNames = new ArrayList<TableName>();
//	protected volatile ColumnMapRepo rep = new ColumnMapRepo();
	protected Map<Integer, String> pos2TableName = new LinkedHashMap<Integer, String>();
	
	private ConcurrentMap<String, List<Integer>> table2PosMap = new ConcurrentHashMap<String, List<Integer>>();

	public Map<Integer, String> getPos2TableName() {
		return pos2TableName;
	}
	
	public void setPos2TableName(Map<Integer, String> pos2TableName) {
		this.pos2TableName = pos2TableName;
	}
	
	public List<Integer> getTablePos(String table) {
		List<Integer> posList = table2PosMap.get(table);
		if (posList != null) {
			return posList;
		}
		posList = new ArrayList<Integer>();
		for (Entry<Integer, String> posEntry : pos2TableName.entrySet()) {
			if (posEntry.getValue().equals(table)) {
				posList.add(posEntry.getKey());
			}
		}
		List<Integer> oldPosList = table2PosMap.putIfAbsent(table, posList);
		if (oldPosList != null) {
			return oldPosList;
		}
		return posList;
	}

//	private class ColumnMapRepo {
//		volatile Map<String, Comparative> repMap;
//		/**
//		 * only used by DMLCommon himself so it don't need to be any valid at
//		 * all
//		 * 
//		 * @see java.lang.Object#clone()
//		 */
//		protected Object evaluate(List<Object> arguments) {
//			Map<String, Comparative> copied = new HashMap<String, Comparative>();
//			for (Entry<String, Comparative> ent : repMap.entrySet()) {
//				copied.put(ent.getKey(), (Comparative) ent.getValue().clone(
//						arguments));
//			}
//			return copied;
//		}
//	}

	
	public void addTableNameAndSchemaName(String tableName, String schemaName,
			String alias) {
		TableNameImp temp = new TableNameImp();
		tbNames.add(temp);
		temp.setTablename(tableName);
		temp.setSchemaName(schemaName);
		temp.setAlias(alias);
	}

	public List<TableName> getTbNames() {
		return tbNames;
	}
	
	public Set<String> getRelatedTables() {
		Set<String> relatedTables = new HashSet<String>();
		for (TableName tbName : tbNames) {
			relatedTables.addAll(getRelatedTables(tbName));
		}
		return relatedTables;
	}
	
	public Set<String> getRelatedTables(TableName tableName) {
		Set<String> relatedTables = new HashSet<String>();
		if (tableName instanceof TableNameSubQueryImp) {
			TableNameSubQueryImp subQueryTableName = (TableNameSubQueryImp) tableName;
			List<TableName> subTbNames = subQueryTableName.getSubSelect().getTbNames();
			for (TableName subTbName : subTbNames) {
				relatedTables.addAll(getRelatedTables(subTbName));
			}
		} else if (tableName instanceof TableNameImp) {
			relatedTables.add(tableName.getTableName());
		}
		return relatedTables;
	}
	
	public Set<String> getTableAliases(String table) {
		Set<String> aliasSet = new HashSet<String>(3);
		for (TableName tbName : tbNames) {
			aliasSet.addAll(getTableAliases(tbName, table));
		}
		return aliasSet;
	}
	
	private Set<String> getTableAliases(TableName tableName, String table) {
		Set<String> aliasSet = new HashSet<String>(3);
		if (tableName instanceof TableNameImp) {
			if (table.equals(tableName.getTableName())) {
				aliasSet.add(tableName.getAlias());
			}
		} else if (tableName instanceof TableNameSubQueryImp) {
			List<TableName> subTbNames = ((TableNameSubQueryImp) tableName).getSubSelect().getTbNames();
			Set<String> subAlias = new HashSet<String>(3);
			for (TableName subTbName : subTbNames) {
				subAlias.addAll(getTableAliases(subTbName, table));
			}
			if (!subAlias.isEmpty()) {
				subAlias.add(tableName.getAlias());
			}
			aliasSet.addAll(subAlias);
		}
		return aliasSet;
	}
	
	public void addTableSubQuery(Select select, String alias) {
		TableNameSubQueryImp tab = new TableNameSubQueryImp();
		tab.setSubSelect(select);
		tab.setAlias(alias);
		this.tbNames.add(tab);
	}

//	@SuppressWarnings("unchecked")
//	public Map<String, Comparative> getColumnsMap(List<Object> arguments) {
//
//		if (rep.repMap == null) {
//			repMapLock.lock();
//			try {
//
//				if(rep.repMap==null){
//				Map<String, Comparative> retMap = Collections.EMPTY_MAP;
//				if (hasSubTableNameSelect()) {
//					for (TableName name : tbNames) {
//						if (name instanceof TableNameSubQueryImp) {
//							TableNameSubQueryImp subSql = (TableNameSubQueryImp) name;
//							retMap = subSql.getSubSelect().getWhere().eval();
//						}
//					}
//				} else {
//					retMap = getSubColumnsMap();
//				}
//				rep.repMap = retMap;
//			}
//			} finally {
//				repMapLock.unlock();
//			}
//
//		}
//		return (Map<String, Comparative>) rep.evaluate(arguments);
//	}
//
//	public abstract Map<String, Comparative> getSubColumnsMap();

	public void appendParams(List<Object> params) {
		for (TableName tbName : tbNames) {
			tbName.appendParams(params);
		}
	}

	public boolean hasSubTableNameSelect() {
		boolean snapshot = false;
		for (TableName tbName : tbNames) {
			if (tbName instanceof TableNameSubQueryImp) {
				if (snapshot) {
					throw new IllegalStateException("在select的tableName部分有多个"
							+ "subSelect,目前还不能够支持");
				}
				snapshot = true;
			}
		}
		return snapshot;
	}

	public String getTableName() {
		String snap = null;
		for (TableName tbName : tbNames) {
			if (snap == null) {
				snap = tbName.getTableName().toLowerCase();
			} else {
				if (!snap.equals(tbName.getTableName().toLowerCase())) {
					throw new IllegalArgumentException(
							"sql语句中的表名不同，请保证所有sql语句的表名"
							+ "以及他们的schemaName相同，包括内嵌sql");
				}
			}
		}
		return snap;
	}

	public int getMax(List<Object> param){
		return DEFAULT_SKIP_MAX;
	}
	
	public int getSkip(List<Object> param){
		return DEFAULT_SKIP_MAX;
	}
	
	public void appendSQL(StringBuilder sb) {
		boolean comma = false;
		for (TableName tbName : tbNames) {
			if (comma) {
				sb.append(",");
			}
			comma = true;
			tbName.appendSQL(sb);
		}
		sb.append(" ");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean comma = false;
		for (TableName tbName : tbNames) {
			if (comma) {
				sb.append(",");
			}
			comma = true;
			if (Constant.useToString(tbName)) {
				sb.append(tbName.toString());
			} else {
				tbName.appendSQL(sb);
			}
		}
		sb.append(" ");
		return sb.toString();
	}

	public abstract List<OrderByEle> getOrderByList();
	// public TableName getTbName() {
	// return tbName;
	// }

}
