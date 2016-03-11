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
import com.dianping.zebra.shard.merge.processor.AggregateDataProcessException;
import com.dianping.zebra.shard.merge.processor.AggregateDataProcessor;
import com.dianping.zebra.shard.merge.processor.CountDataProcessor;
import com.dianping.zebra.shard.merge.processor.MaxDataProcessor;
import com.dianping.zebra.shard.merge.processor.MinDataProcessor;
import com.dianping.zebra.shard.merge.processor.SumDataProcessor;
import com.dianping.zebra.shard.router.RouterResult;

/**
 * <p>
 * 默认的数据合并器
 * </p>
 *
 * @author Leo Liang
 */
public class DefaultDataMerger implements DataMerger {
	private static Map<String, AggregateDataProcessor> aggregateFunctionProcessors;

	static {
		aggregateFunctionProcessors = new HashMap<String, AggregateDataProcessor>();
		aggregateFunctionProcessors.put("MAX", new MaxDataProcessor());
		aggregateFunctionProcessors.put("MIN", new MinDataProcessor());
		aggregateFunctionProcessors.put("COUNT", new CountDataProcessor());
		aggregateFunctionProcessors.put("SUM", new SumDataProcessor());
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
		if (routerTarget.getTargetedSqls() == null || routerTarget.getTargetedSqls().size() == 0) {
			throw new SQLException("Can not proc merge, since no router result.");
		}

		if (routerTarget.getTargetedSqls().size() == 1 && routerTarget.getTargetedSqls().get(0).getSqls().size() == 1) {
			dataPool.setResultSets(actualResultSets);
			dataPool.setInMemory(false);
		} else if ((routerTarget.getTargetedSqls().size() > 1
				|| routerTarget.getTargetedSqls().get(0).getSqls().size() > 1) && (routerTarget.getOrderBy() == null)
				&& !hasGroupByFunctionColumns(routerTarget) && !routerTarget.isHasDistinct()) {
			dataPool.setResultSets(actualResultSets);
			dataPool.setInMemory(false);
		} else {
			dataPool.setInMemory(true);
			dataPool.setResultSets(actualResultSets);
			List<RowData> rowDatas = popResultSets(actualResultSets, routerTarget);

			if (rowDatas == null || rowDatas.size() == 0) {
				dataPool.setMemoryData(rowDatas);
				return;
			}

			List<RowData> afterDistinctDatas = rowDatas;

			if (routerTarget.isHasDistinct()) {
				afterDistinctDatas = procDistinct(rowDatas, routerTarget);
			}
			List<RowData> afterGroupByDatas = procAggregateFunction(afterDistinctDatas, routerTarget);

			List<RowData> afterOrderByDatas = procOrderBy(afterGroupByDatas, routerTarget);

			dataPool.setMemoryData(afterOrderByDatas);
		}

		if (routerTarget.getTargetedSqls().size() > 1 || routerTarget.getTargetedSqls().get(0).getSqls().size() > 1) {
			dataPool.setMax(routerTarget.getMax());
			dataPool.setSkip(routerTarget.getSkip());
			dataPool.procLimit();
		}

	}

	private List<RowData> procDistinct(List<RowData> sourceData, RouterResult routerTarget) {
		Set<RowData> distinctRowSet = new HashSet<RowData>();
		for (RowData row : sourceData) {
			distinctRowSet.add(row);
		}
		return new ArrayList<RowData>(distinctRowSet);
	}

	private List<RowData> procOrderBy(List<RowData> sourceData, final RouterResult routerTarget) throws SQLException {
		if (routerTarget.getOrderBy() != null) {
			SQLOrderBy orderBy = routerTarget.getOrderBy();
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
							// if (orderByEle.getType() != null &&
							// orderByEle.getType().equals("ASC")) {
							// if (compareRes != 0) {
							// return compareRes;
							// }
							// } else {
							// if (compareRes != 0) {
							// return compareRes < 0 ? 1 : -1;
							// }
							// }
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

	private List<RowData> procAggregateFunction(List<RowData> sourceData, RouterResult routerTarget)
			throws SQLException {
		List<RowData> processedDatas = new ArrayList<RowData>();

		// 没有group by并且没有聚合函数，则直接返回源数据
		if ((routerTarget.getGroupBys() == null || routerTarget.getGroupBys().size() <= 0)
				&& !hasGroupByFunctionColumns(routerTarget)) {
			return sourceData;
		}

		Map<String, SQLSelectItem> columnNameFunctionMapping = new HashMap<String, SQLSelectItem>();
		for (SQLSelectItem column : routerTarget.getSelectLists()) {
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

		if (routerTarget.getGroupBys() == null || routerTarget.getGroupBys().size() <= 0) {
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
			procGroupBy(sourceData, routerTarget, processedDatas, columnNameFunctionMapping);
		}

		return processedDatas;
	}

	private void procGroupBy(List<RowData> sourceData, RouterResult routerTarget, List<RowData> processedDatas,
			Map<String, SQLSelectItem> columnNameFunctionMapping) throws SQLException {
		// 因为group by后面跟的只能是列名，但是如果select中包含别名，则ColumnData中存放的是别名
		// 所以先获得列名和别名的map
		Map<String, String> columnNameAliasMapping = new HashMap<String, String>();
		for (SQLSelectItem column : routerTarget.getSelectLists()) {
			// int pos = -1;
			// String simpleName = ((SQLName)column.getExpr()).getSimpleName();
			// if ((pos = routerTarget.getGroupBys().indexOf(simpleName)) >= 0)
			// {
			// if (column.getAlias() != null &&
			// column.getAlias().trim().length() != 0) {
			// columnNameAliasMapping.put(routerTarget.getGroupBys().get(pos),
			// column.getAlias());
			// }
			// }
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
		for (String columnName : routerTarget.getGroupBys()) {
			groupByColumnIndexes.add(sourceData.get(0).get(columnNameAliasMapping.containsKey(columnName)
					? columnNameAliasMapping.get(columnName) : columnName).getColumnIndex());
		}

		Map<MultiKey, RowData> tmpMap = new LinkedHashMap<MultiKey, RowData>();
		for (RowData row : sourceData) {
			List<Object> groupByValues = new ArrayList<Object>(routerTarget.getGroupBys().size());

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
				AggregateDataProcessor dataProcessor = aggregateFunctionProcessors.get(aggregateExpr.getMethodName());
				if (dataProcessor != null) {
					newRowData.get(col.getColumnIndex()).setValue(
							dataProcessor.process(newRowData.get(col.getColumnIndex()).getValue(), col.getValue()));

				} else {
					throw new SQLException("Zebra unsupported groupby function exists");
				}
			}
		} catch (AggregateDataProcessException e) {
			throw new SQLException("Proc aggregate merge failed.");
		}
	}

	private List<RowData> popResultSets(List<ResultSet> actualResultSets, RouterResult routerTarget)
			throws SQLException {

		List<RowData> rows = new ArrayList<RowData>();

		for (int resultSetIndex = 0; resultSetIndex < actualResultSets.size(); resultSetIndex++) {
			while (actualResultSets.get(resultSetIndex).next()) {

				RowData row = new RowData(actualResultSets.get(resultSetIndex));

				for (SQLSelectItem col : routerTarget.getSelectLists()) {
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

	private boolean hasGroupByFunctionColumns(RouterResult routerTarget) {
		for (SQLSelectItem col : routerTarget.getSelectLists()) {
			if (col.getExpr() instanceof SQLAggregateExpr) {
				return true;
			}
			// if (col.get instanceof Sum || col instanceof Count || col
			// instanceof Max || col instanceof Min) {
			// return true;
			// }
		}

		return false;
	}
}
