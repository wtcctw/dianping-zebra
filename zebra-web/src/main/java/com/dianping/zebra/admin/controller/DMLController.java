package com.dianping.zebra.admin.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import com.dianping.zebra.shard.router.DataSourceRouter;
import com.dianping.zebra.shard.router.RouterTarget;
import com.dianping.zebra.shard.router.TargetedSql;
import com.dianping.zebra.util.SqlType;
import com.dianping.zebra.util.SqlUtils;

@Controller
@RequestMapping(value = "/dml")
public class DMLController {

	@RequestMapping(value = "/analyze", method = RequestMethod.POST)
	@ResponseBody
	public Object analyze(@RequestBody DmlResultDto dto) {
		ShardDataSource ds = new ShardDataSource();
		ds.setRuleName(dto.getRuleName());
		ds.init();

		DataSourceRouter router = ds.getRouter();
		RouterTarget target = null;

		try {
			target = router.getTarget(dto.getSql(), new ArrayList<Object>());
		} catch (Exception e) {
			dto.setSuccess(false);
			dto.setErrorMsg(e.getCause().getMessage());

			return dto;
		}

		int targetSqlCount = 0;
		int totalTableCount = 0;
		boolean isCrossDb = false;
		boolean isFullTableScan = false;

		for (TargetedSql targetedSql : target.getTargetedSqls()) {
			targetSqlCount += targetedSql.getSqls().size();
		}

		if (target.getTargetedSqls().size() > 1) {
			isCrossDb = true;
		}

		for (Entry<String, Set<String>> entry : target.getShardResult().getDbAndTables().entrySet()) {
			totalTableCount += entry.getValue().size();
		}
		if (targetSqlCount == totalTableCount) {
			isFullTableScan = true;
		}

		dto.setTargetSQLCount(targetSqlCount);
		dto.setCrossDb(isCrossDb);
		dto.setFullTableScan(isFullTableScan);
		dto.setPk(target.getShardResult().getBasedColumn());

		List<TargetedSql> targetedSqls = target.getTargetedSqls();

		for (TargetedSql targetedSql : targetedSqls) {
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
		}

		ds.close();

		return dto;
	}
}
