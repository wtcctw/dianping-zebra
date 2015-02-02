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
package com.dianping.zebra.shard.jdbc.data;

import com.dianping.zebra.shard.jdbc.data.processor.*;
import com.dianping.zebra.shard.parser.sqlParser.Column;
import com.dianping.zebra.shard.parser.sqlParser.OrderBy;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.Count;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.Max;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.Min;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.Sum;
import com.dianping.zebra.shard.router.RouterTarget;

import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.util.*;

/**
 * <p>
 * 默认的数据合并器
 * </p>
 *
 * @author Leo Liang
 */
public class DefaultDataMerger implements DataMerger {
	private static Map<Class<?>, AggregateDataProcessor> aggregateFunctionProcessors;

	static {
		aggregateFunctionProcessors = new HashMap<Class<?>, AggregateDataProcessor>();
		aggregateFunctionProcessors.put(Max.class, new MaxDataProcessor());
		aggregateFunctionProcessors.put(Min.class, new MinDataProcessor());
		aggregateFunctionProcessors.put(Count.class, new CountDataProcessor());
		aggregateFunctionProcessors.put(Sum.class, new SumDataProcessor());
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
	public void merge(DataPool dataPool, RouterTarget routerTarget, List<ResultSet> actualResultSets)
		throws SQLException {
		if (routerTarget.getTargetedSqls() == null || routerTarget.getTargetedSqls().size() == 0) {
			throw new SQLException("Can not proc merge, since no router result.");
		}

		if (routerTarget.getTargetedSqls().size() == 1 && routerTarget.getTargetedSqls().get(0).getSqls().size() == 1) {
			dataPool.setResultSets(actualResultSets);
			dataPool.setInMemory(false);
		} else if (
			(routerTarget.getTargetedSqls().size() > 1 || routerTarget.getTargetedSqls().get(0).getSqls().size() > 1)
				&& (routerTarget.getOrderBys() == null || routerTarget.getOrderBys().size() == 0)
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

	private List<RowData> procDistinct(List<RowData> sourceData, RouterTarget routerTarget) {
		Set<RowData> distinctRowSet = new HashSet<RowData>();
		for (RowData row : sourceData) {
			distinctRowSet.add(row);
		}
		return new ArrayList<RowData>(distinctRowSet);
	}

	private List<RowData> procOrderBy(List<RowData> sourceData, final RouterTarget routerTarget) throws SQLException {
		if (routerTarget.getOrderBys() != null && routerTarget.getOrderBys().size() > 0) {

			Collections.sort(sourceData, new Comparator<RowData>() {

				@Override
				public int compare(RowData o1, RowData o2) {
					try {
						for (OrderBy orderByEle : routerTarget.getOrderBys()) {
							Object value1 = o1.get(orderByEle.getName()).getValue();
							Class<?> type1 = o1.get(orderByEle.getName()).getType();
							Object value2 = o2.get(orderByEle.getName()).getValue();
							Class<?> type2 = o2.get(orderByEle.getName()).getType();

							if (!type1.equals(type2)) {
								throw new SQLException("Invalid data");
							}

							if (!Comparable.class.isAssignableFrom(type1)) {
								throw new SQLException("Can not orderBy column : " + orderByEle.getName()
									+ " which isn't comparable.");
							}

							@SuppressWarnings({ "unchecked" })
							int compareRes = ((Comparable) value1).compareTo((Comparable) value2);

							if (orderByEle.isASC()) {
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

	private List<RowData> procAggregateFunction(List<RowData> sourceData, RouterTarget routerTarget)
		throws SQLException {
		List<RowData> processedDatas = new ArrayList<RowData>();

		// 没有group by并且没有聚合函数，则直接返回源数据
		if ((routerTarget.getGroupBys() == null || routerTarget.getGroupBys().size() <= 0)
			&& !hasGroupByFunctionColumns(routerTarget)) {
			return sourceData;
		}

		Map<String, Class<?>> columnNameFunctionMapping = new HashMap<String, Class<?>>();
		for (Column column : routerTarget.getColumns().getColumnsList()) {
			columnNameFunctionMapping.put(column.getAlias() == null ? column.getColumn() : column.getAlias(),
				column.getClass());
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

	private void procGroupBy(List<RowData> sourceData, RouterTarget routerTarget, List<RowData> processedDatas,
		Map<String, Class<?>> columnNameFunctionMapping) throws SQLException {
		// 因为group by后面跟的只能是列名，但是如果select中包含别名，则ColumnData中存放的是别名
		// 所以先获得列名和别名的map
		Map<String, String> columnNameAliasMapping = new HashMap<String, String>();
		for (Column column : routerTarget.getColumns().getColumnsList()) {
			int pos = -1;
			if ((pos = routerTarget.getGroupBys().indexOf(column.getColumn())) >= 0) {
				if (column.getAlias() != null && column.getAlias().trim().length() != 0) {
					columnNameAliasMapping.put(routerTarget.getGroupBys().get(pos), column.getAlias());
				}
			}
		}

		List<Integer> groupByColumnIndexes = new ArrayList<Integer>();
		for (String columnName : routerTarget.getGroupBys()) {
			groupByColumnIndexes.add(sourceData
				.get(0)
				.get(columnNameAliasMapping.containsKey(columnName) ? columnNameAliasMapping.get(columnName)
					: columnName).getColumnIndex());
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

	private void calAggregateFunctionValue(Map<String, Class<?>> columnNameFunctionMapping, RowData row,
		RowData newRowData, List<Integer> ignoreColumnList) throws SQLException {
		try {
			for (ColumnData col : row.getColumnDatas()) {
				if (ignoreColumnList != null && ignoreColumnList.contains(col.getColumnIndex())) {
					continue;
				}
				Class<?> columnType = columnNameFunctionMapping.get(col.getColumnName());
				AggregateDataProcessor dataProcessor = aggregateFunctionProcessors.get(columnType);
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

	private List<RowData> popResultSets(List<ResultSet> actualResultSets, RouterTarget routerTarget)
		throws SQLException {

		List<RowData> rows = new ArrayList<RowData>();

		for (int resultSetIndex = 0; resultSetIndex < actualResultSets.size(); resultSetIndex++) {
			while (actualResultSets.get(resultSetIndex).next()) {

				RowData row = new RowData(actualResultSets.get(resultSetIndex));

				for (Column col : routerTarget.getColumns().getColumnsList()) {
					String columnName = col.getAlias() == null ? col.getColumn() : col.getAlias();
					int columnIndex = actualResultSets.get(resultSetIndex).findColumn(columnName);
					Object value = actualResultSets.get(resultSetIndex).getObject(columnIndex);
					boolean wasNull = actualResultSets.get(resultSetIndex).wasNull();
					RowId rowId = null;
					try {
						rowId = actualResultSets.get(resultSetIndex).getRowId(columnIndex);
					} catch (Throwable e) {
						// ignore
					}

					ColumnData columnData = new ColumnData(columnIndex, columnName, value, value == null ? null
						: value.getClass(), rowId, wasNull);
					row.addColumn(columnData);

				}

				rows.add(row);
			}
		}

		return rows;

	}

	private boolean hasGroupByFunctionColumns(RouterTarget routerTarget) {
		for (Column col : routerTarget.getColumns().getColumns()) {
			if (col instanceof Sum || col instanceof Count || col instanceof Max || col instanceof Min) {
				return true;
			}

		}
		return false;
	}
}
