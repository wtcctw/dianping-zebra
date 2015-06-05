package com.dianping.zebra.admin.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.dto.DmlResultDto;
import com.dianping.zebra.admin.dto.DmlResultDto.DataRow;
import com.dianping.zebra.admin.dto.DmlResultDto.RouterItem;
import com.dianping.zebra.shard.jdbc.ShardDataSource;
import com.dianping.zebra.shard.parser.qlParser.DPMySQLParser;
import com.dianping.zebra.shard.parser.sqlParser.DMLCommon;
import com.dianping.zebra.shard.router.ShardRouterException;
import com.dianping.zebra.shard.router.ShardRouter;
import com.dianping.zebra.shard.router.RouterResult;
import com.dianping.zebra.shard.router.RouterTarget;
import com.dianping.zebra.shard.router.rule.DimensionRule;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.TableShardRule;
import com.dianping.zebra.util.SqlType;
import com.dianping.zebra.util.SqlUtils;

@Controller
@RequestMapping(value = "/dml")
public class DMLController extends BasicController {

	@RequestMapping(value = "/analyze", method = RequestMethod.POST)
	@ResponseBody
	public Object analyze(@RequestBody DmlResultDto dto) {
		ShardDataSource ds = new ShardDataSource();
		ds.setRuleName(dto.getRuleName());
		ds.init();

		ShardRouter router = ds.getRouter();
		RouterResult target = null;

		try {
			target = router.router(dto.getSql(), new ArrayList<Object>());
		} catch (Exception e) {
			dto.setSuccess(false);
			dto.setErrorMsg(e.getCause().getMessage());

			return dto;
		}

		int targetSqlCount = 0;
		int totalTableCount = 0;
		boolean isCrossDb = false;
		boolean isFullTableScan = false;

		for (RouterTarget targetedSql : target.getTargetedSqls()) {
			targetSqlCount += targetedSql.getSqls().size();
		}

		if (target.getTargetedSqls().size() > 1) {
			isCrossDb = true;
		}

		RouterRule routerRule = router.getRouterRule();
      try {
      	DMLCommon dml = DPMySQLParser.parse(dto.getSql()).obj;
      	Set<String> relatedTables = dml.getRelatedTables();
      	TableShardRule tableShardRule = getAppliedShardRule(routerRule,relatedTables);
      	
      	if(tableShardRule != null){
      		DimensionRule dimensionRule = tableShardRule.getDimensionRules().get(0);
      		for(Entry<String,Set<String>> shard : dimensionRule.getAllDBAndTables().entrySet()){
      			totalTableCount += shard.getValue().size();
      		}
      	}else{
      		dto.setSuccess(false);
      		dto.setErrorMsg("Cannot found any sharding rule for this sql");
      		
      		return dto;
      	}
      } catch (Exception e) {
      	dto.setSuccess(false);
      	dto.setErrorMsg(e.getCause().getMessage());
      	
      	return dto;
      }
		
		if (targetSqlCount == totalTableCount) {
			isFullTableScan = true;
		}

		dto.setTargetSQLCount(targetSqlCount);
		dto.setCrossDb(isCrossDb);
		dto.setFullTableScan(isFullTableScan);
		dto.setPk(target.getShardResult().getBasedColumn());

		List<RouterTarget> targetedSqls = target.getTargetedSqls();

		for (RouterTarget targetedSql : targetedSqls) {
			for (String _sql : targetedSql.getSqls()) {
				try {
					DMLCommon parsedResult = DPMySQLParser.parse(_sql).obj;
					String table = parsedResult.getTableName();

					dto.addRouterItem(new RouterItem(targetedSql.getDataSourceName(), table, _sql));
				} catch (Exception e) {
				}
			}
		}

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();

			SqlType sqlType = SqlUtils.getSqlType(dto.getSql());

			if (sqlType == SqlType.SELECT) {
				ResultSet rs = stmt.executeQuery(dto.getSql());

				int columnCount = rs.getMetaData().getColumnCount();

				for(int i = 1 ; i <= columnCount ; i++ ){
					dto.addDataHeader(rs.getMetaData().getColumnName(i));
				}
				
				while (rs.next()) {
					DataRow dataRow = new DataRow();

					for (int i = 1; i <= columnCount; i++) {
						dataRow.addObject(rs.getString(i));
					}

					dto.addDataRow(dataRow);
				}
				
			} else {
				int executeUpdate = stmt.executeUpdate(dto.getSql());

				dto.addDataRow(new DataRow(executeUpdate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			stmt.close();
			conn.close();
	      ds.close();
      } catch (SQLException e) {
	      e.printStackTrace();
      }

		return dto;
	}
	
	private TableShardRule getAppliedShardRule(RouterRule routerRule, Set<String> relatedTables) throws ShardRouterException {
		Map<String, TableShardRule> shardRules = new HashMap<String, TableShardRule>(5);
		Map<String, TableShardRule> tableShardRules = routerRule.getTableShardRules();
		for (String relatedTable : relatedTables) {
			TableShardRule tableShardRule = tableShardRules.get(relatedTable);
			if (tableShardRule != null) {
				shardRules.put(relatedTable, tableShardRule);
			}
		}
		if (shardRules.size() > 1) {
			throw new ShardRouterException("Sql contains more than one shard-related table is not supported now.");
		}
		return !shardRules.isEmpty() ? shardRules.values().iterator().next() : null;
	}
}
