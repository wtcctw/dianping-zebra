/**
 * Project: zebra-client
 *
 * File Created at 2011-6-22
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
package com.dianping.zebra.shard.merge;

import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.SQLOrderingSpecification;
import com.alibaba.druid.sql.ast.expr.SQLAggregateExpr;
import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectOrderByItem;
import com.dianping.zebra.shard.merge.aggregate.DataAggregateException;
import com.dianping.zebra.shard.merge.aggregate.DataAggregator;
import com.dianping.zebra.shard.merge.aggregate.CountDataAggregator;
import com.dianping.zebra.shard.merge.aggregate.MaxDataAggregator;
import com.dianping.zebra.shard.merge.aggregate.MinDataAggregator;
import com.dianping.zebra.shard.merge.aggregate.SumDataAggregator;
import com.dianping.zebra.shard.router.RouterResult;

/**
 * <p>
 * 默认的数据合并器
 * </p>
 *
 * @author Leo Liang
 */
public class DefaultDataMerger implements DataMerger {
	private static Map<String, DataAggregator> aggregateFunctionProcessors;

	static {
		aggregateFunctionProcessors = new HashMap<String, DataAggregator>();
		aggregateFunctionProcessors.put("MAX", new MaxDataAggregator());
		aggregateFunctionProcessors.put("MIN", new MinDataAggregator());
		aggregateFunctionProcessors.put("COUNT", new CountDataAggregator());
		aggregateFunctionProcessors.put("SUM", new SumDataAggregator());
	}

	/**
	 * <p>
	 * 处理步骤：
	 * <ol>
	 * <li>如果路由结果中仅包含一个数据源或者路由结果包含多个数据源但是SQL不包含order
	 * by子句和聚合函数，且没有distinct，则直接把真实ResultSet List进行limit处理后保存于dataPool中。</li>
	 * <li>非第一种情况，则需要从ResultSet
	 * List中弹出所有记录，进行distinct处理，聚合函数计算，排序，limit计算。并把结果保存于dataPool中。</li>
	 * </ol>
	 * </p>
	 */
	public void merge(DataPool dataPool, RouterResult routerTarget, List<ResultSet> actualResultSets)
			throws SQLException {
		if (routerTarget.getSqls() == null || routerTarget.getSqls().size() == 0) {
			throw new SQLException("Can not proc merge, since no router result.");
		}

		if (routerTarget.getSqls().size() == 1 && routerTarget.getSqls().get(0).getSqls().size() == 1) {
			dataPool.setResultSets(actualResultSets);
			dataPool.setInMemory(false);
		} else if ((routerTarget.getSqls().size() > 1 || routerTarget.getSqls().get(0).getSqls().size() > 1)
				&& (routerTarget.getMergeContext().getOrderBy() == null)
				&& !hasGroupByFunctionColumns(routerTarget.getMergeContext())
				&& !routerTarget.getMergeContext().isDistinct()) {
			dataPool.setResultSets(actualResultSets);
			dataPool.setInMemory(false);
		} else {
			dataPool.setInMemory(true);
			dataPool.setResultSets(actualResultSets);
			List<RowData> rowDatas = popResultSets(actualResultSets, routerTarget.getMergeContext());

			if (rowDatas == null || rowDatas.size() == 0) {
				dataPool.setMemoryData(rowDatas);
				return;
			}

			List<RowData> afterDistinctDatas = rowDatas;

			if (routerTarget.getMergeContext().isDistinct()) {
				afterDistinctDatas = procDistinct(rowDatas);
			}
			List<RowData> afterGroupByDatas = procAggregateFunction(afterDistinctDatas, routerTarget.getMergeContext());

			List<RowData> afterOrderByDatas = procOrderBy(afterGroupByDatas, routerTarget.getMergeContext());

			dataPool.setMemoryData(afterOrderByDatas);
		}

		if (routerTarget.getSqls().size() > 1 || routerTarget.getSqls().get(0).getSqls().size() > 1) {
			dataPool.setMax(routerTarget.getMergeContext().getLimit());
			dataPool.setSkip(routerTarget.getMergeContext().getOffset());
			dataPool.procLimit();
		}

	}

	private List<RowData> procDistinct(List<RowData> sourceData) {
		Set<RowData> distinctRowSet = new HashSet<RowData>();
		for (RowData row : sourceData) {
			distinctRowSet.add(row);
		}
		return new ArrayList<RowData>(distinctRowSet);
	}

	private List<RowData> procOrderBy(List<RowData> sourceData, final MergeContext mergeContext) throws SQLException {
		if (mergeContext.getOrderBy() != null) {
			SQLOrderBy orderBy = mergeContext.getOrderBy();
			final List<SQLSelectOrderByItem> items = orderBy.getItems();
			Collections.sort(sourceData, new Comparator<RowData>() {
				@Override
				public int compare(RowData o1, RowData o2) {
					try {
						for (SQLSelectOrderByItem orderByEle : items) {
							SQLName identifier = (SQLName) orderByEle.getExpr();

							Object value1 = o1.get(identifier.getSimpleName()).getValue();
							Class<?> type1 = o1.get(identifier.getSimpleName()).getType();
							Object value2 = o2.get(identifier.getSimpleName()).getValue();
							Class<?> type2 = o2.get(identifier.getSimpleName()).getType();

							if (!type1.equals(type2)) {
								throw new SQLException("Invalid data");
							}

							if (!Comparable.class.isAssignableFrom(type1)) {
								throw new SQLException(
										"Can not orderBy column : " + identifier + " which isn't comparable.");
							}

							@SuppressWarnings({ "unchecked", "rawtypes" })
							int compareRes = ((Comparable) value1).compareTo((Comparable) value2);

							if (orderByEle.getType() == null
									|| ((SQLOrderingSpecification) orderByEle.getType()).name().equals("ASC")) {
								if (compareRes != 0) {
									return compareRes;
								}
							} else {
								if (compareRes != 0) {
									return compareRes < 0 ? 1 : -1;
								}
							}
						}

						return 0;

					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}

			});
		}

		return sourceData;
	}

	private List<RowData> procAggregateFunction(List<RowData> sourceData, MergeContext mergeContext)
			throws SQLException {
		List<RowData> processedDatas = new ArrayList<RowData>();

		// 没有group by并且没有聚合函数，则直接返回源数据
		if ((mergeContext.getOrderBy() == null || mergeContext.getGroupByColumns().size() <= 0)
				&& !hasGroupByFunctionColumns(mergeContext)) {
			return sourceData;
		}

		Map<String, SQLSelectItem> columnNameFunctionMapping = new HashMap<String, SQLSelectItem>();
		for (SQLSelectItem column : mergeContext.getSelectLists()) {
			String name = null;
			if (column.getExpr() instanceof SQLAggregateExpr) {
				SQLAggregateExpr expr = (SQLAggregateExpr) column.getExpr();
				SQLExpr argument = expr.getArguments().get(0);
				if (argument instanceof SQLAllColumnExpr) {
					name = expr.getMethodName() + "(*)";
				} else {
					name = expr.getMethodName() + "(" + ((SQLName) argument).getSimpleName() + ")";
				}
			} else {
				name = ((SQLName) column.getExpr()).getSimpleName();
			}
			columnNameFunctionMapping.put(column.getAlias() == null ? name : column.getAlias(), column);
		}

		if (mergeContext.getGroupByColumns() == null || mergeContext.getGroupByColumns().size() <= 0) {
			RowData aggregateRow = null;
			for (RowData row : sourceData) {
				if (aggregateRow == null) {
					aggregateRow = new RowData(row);
				} else {
					calAggregateFunctionValue(columnNameFunctionMapping, row, aggregateRow, null);
				}
			}
			processedDatas.add(aggregateRow);
		} else {
			procGroupBy(sourceData, mergeContext, processedDatas, columnNameFunctionMapping);
		}

		return processedDatas;
	}

	private void procGroupBy(List<RowData> sourceData, MergeContext mergeContext, List<RowData> processedDatas,
			Map<String, SQLSelectItem> columnNameFunctionMapping) throws SQLException {
		// 因为group by后面跟的只能是列名，但是如果select中包含别名，则ColumnData中存放的是别名
		// 所以先获得列名和别名的map
		Map<String, String> columnNameAliasMapping = new HashMap<String, String>();
		for (SQLSelectItem column : mergeContext.getSelectLists()) {
			SQLExpr expr = column.getExpr();
			if (expr instanceof SQLAggregateExpr) {
				SQLAggregateExpr aggregateExpr = (SQLAggregateExpr) expr;
				SQLName identifier = (SQLName) aggregateExpr.getArguments().get(0);
				columnNameAliasMapping.put(identifier.getSimpleName(), column.getAlias());
			} else {
				if (column.getAlias() != null) {
					SQLName identifier = (SQLName) expr;
					columnNameAliasMapping.put(identifier.getSimpleName(), column.getAlias());
				}
			}
		}

		List<Integer> groupByColumnIndexes = new ArrayList<Integer>();
		for (String columnName : mergeContext.getGroupByColumns()) {
			groupByColumnIndexes.add(sourceData.get(0).get(columnNameAliasMapping.containsKey(columnName)
					? columnNameAliasMapping.get(columnName) : columnName).getColumnIndex());
		}

		Map<MultiKey, RowData> tmpMap = new LinkedHashMap<MultiKey, RowData>();
		for (RowData row : sourceData) {
			List<Object> groupByValues = new ArrayList<Object>(mergeContext.getGroupByColumns().size());

			for (Integer groupByColIndex : groupByColumnIndexes) {
				groupByValues.add(row.get(groupByColIndex).getValue());
			}

			// 多个group by的值作为一个MultiKey用于聚合Map
			MultiKey multiKey = new MultiKey(groupByValues);

			RowData groupByRowData = tmpMap.get(multiKey);

			if (groupByRowData == null) {
				groupByRowData = new RowData(row);
				tmpMap.put(multiKey, groupByRowData);
			} else {
				calAggregateFunctionValue(columnNameFunctionMapping, row, groupByRowData, groupByColumnIndexes);
			}
		}

		for (Map.Entry<MultiKey, RowData> entry : tmpMap.entrySet()) {
			processedDatas.add(entry.getValue());
		}
	}

	private void calAggregateFunctionValue(Map<String, SQLSelectItem> columnNameFunctionMapping, RowData row,
			RowData newRowData, List<Integer> ignoreColumnList) throws SQLException {
		try {
			for (ColumnData col : row.getColumnDatas()) {
				if (ignoreColumnList != null && ignoreColumnList.contains(col.getColumnIndex())) {
					continue;
				}
				SQLSelectItem columnType = columnNameFunctionMapping.get(col.getColumnName());
				SQLAggregateExpr aggregateExpr = (SQLAggregateExpr) columnType.getExpr();
				DataAggregator dataProcessor = aggregateFunctionProcessors.get(aggregateExpr.getMethodName());
				if (dataProcessor != null) {
					ColumnData oldCol = newRowData.get(col.getColumnIndex());
					Object value = dataProcessor.process(oldCol.getValue(), col.getValue());

					if (value != null) {
						oldCol.setWasNull(false);
					}
					oldCol.setValue(value);
				} else {
					throw new SQLException("Zebra unsupported groupby function exists");
				}
			}
		} catch (DataAggregateException e) {
			throw new SQLException("Proc aggregate merge failed.");
		}
	}

	private List<RowData> popResultSets(List<ResultSet> actualResultSets, MergeContext mergeContext)
			throws SQLException {
		List<RowData> rows = new ArrayList<RowData>();

		for (int resultSetIndex = 0; resultSetIndex < actualResultSets.size(); resultSetIndex++) {
			while (actualResultSets.get(resultSetIndex).next()) {

				RowData row = new RowData(actualResultSets.get(resultSetIndex));

				for (SQLSelectItem col : mergeContext.getSelectLists()) {
					String simpleName = null;
					if (col.getExpr() instanceof SQLIdentifierExpr || col.getExpr() instanceof SQLPropertyExpr) {
						simpleName = ((SQLName) col.getExpr()).getSimpleName();
					} else if (col.getExpr() instanceof SQLAggregateExpr) {
						SQLAggregateExpr expr = (SQLAggregateExpr) col.getExpr();
						SQLExpr argument = expr.getArguments().get(0);
						if (argument instanceof SQLAllColumnExpr) {
							simpleName = expr.getMethodName() + "(*)";
						} else {
							simpleName = expr.getMethodName() + "(" + ((SQLName) argument).getSimpleName() + ")";
						}
					}

					String columnName = col.getAlias() == null ? simpleName : col.getAlias();
					int columnIndex = actualResultSets.get(resultSetIndex).findColumn(columnName);// todo:count(id)
																									// 报错，此时没有字段名
					Object value = actualResultSets.get(resultSetIndex).getObject(columnIndex);
					boolean wasNull = actualResultSets.get(resultSetIndex).wasNull();
					RowId rowId = null;
					try {
						rowId = actualResultSets.get(resultSetIndex).getRowId(columnIndex);
					} catch (Throwable e) {
						// ignore
					}

					ColumnData columnData = new ColumnData(columnIndex, columnName, value,
							value == null ? null : value.getClass(), rowId, wasNull);
					row.addColumn(columnData);

				}

				rows.add(row);
			}
		}

		return rows;

	}

	private boolean hasGroupByFunctionColumns(MergeContext mergeContext) {
		for (SQLSelectItem col : mergeContext.getSelectLists()) {
			if (col.getExpr() instanceof SQLAggregateExpr) {
				return true;
			}
		}

		return false;
	}
}
