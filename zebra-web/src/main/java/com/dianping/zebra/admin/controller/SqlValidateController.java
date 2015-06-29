package com.dianping.zebra.admin.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.service.CatSQLDataCrawler;
import com.dianping.zebra.admin.service.CatSQLDataCrawler.DatabaseSql;
import com.dianping.zebra.admin.service.CatSQLDataCrawler.SqlEntity;
import com.dianping.zebra.biz.dto.SQLValidateDto;
import com.dianping.zebra.shard.parser.qlParser.DPMySQLParser;
import com.dianping.zebra.shard.parser.sqlParser.DMLCommon;

@Controller
@RequestMapping(value = "/validate")
public class SqlValidateController {

	@Autowired
	private CatSQLDataCrawler catSqlDataCrawler;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object validate(String database, String table) {
		// crawler data from cat
		DatabaseSql databaseSql = catSqlDataCrawler.crawler(database);

		List<SQLValidateDto> results = new ArrayList<SQLValidateDto>();

		for (Entry<String, SqlEntity> sqlEntry : databaseSql.getSqls().entrySet()) {
			String sqlName = sqlEntry.getKey();
			SqlEntity sqlEntity = sqlEntry.getValue();
			SQLValidateDto dto = new SQLValidateDto();

			String sql = sqlEntity.getSql();

			if (sql != null && (sql.equalsIgnoreCase("null") || sql.equalsIgnoreCase("batched"))) {
				continue;
			}

			dto.setApp(sqlEntity.getDomain());
			dto.setSqlName(sqlName);
			dto.setSql(sql);

			DMLCommon parsedResult = null;
			try {
				parsedResult = DPMySQLParser.parse(sql).obj;
			} catch (Exception e) {
				dto.setSyntaxSupported(false);
				dto.setErrorMsg(e.getMessage());
			}

			if (parsedResult != null && parsedResult.getRelatedTables().contains(table)) {
				results.add(dto);
			}else if(parsedResult == null){
				results.add(dto);
			}
		}

		return results;
	}

	@RequestMapping(value = "/dbs", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllDatabases() {
		DatabaseSql databaseSql = catSqlDataCrawler.crawler("cat");

		return databaseSql.getDatabases();
	}

	@RequestMapping(value = "/tables", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllTables(String database) {
		DatabaseSql databaseSql = catSqlDataCrawler.crawler(database);

		Set<String> tables = new HashSet<String>();

		for (Entry<String, SqlEntity> sqlEntry : databaseSql.getSqls().entrySet()) {
			String sql = sqlEntry.getValue().getSql();

			try {
				DMLCommon dml = DPMySQLParser.parse(sql).obj;

				tables.addAll(dml.getRelatedTables());
			} catch (Exception ignore) {
			}
		}

		return tables;
	}
}
