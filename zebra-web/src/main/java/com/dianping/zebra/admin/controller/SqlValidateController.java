package com.dianping.zebra.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.dto.SQLValidateDto;
import com.dianping.zebra.admin.service.CatSQLDataCrawler;
import com.dianping.zebra.admin.service.CatSQLDataCrawler.DatabaseSql;
import com.dianping.zebra.admin.service.CatSQLDataCrawler.SqlEntity;
import com.dianping.zebra.shard.router.DataSourceRouterFactory;
import com.dianping.zebra.shard.router.LionDataSourceRouterFactory;
import com.dianping.zebra.shard.router.ShardRouter;
import com.dianping.zebra.shard.router.ShardRouterException;
import com.dianping.zebra.shard.router.SyntaxException;

@Controller
@RequestMapping(value = "/validate")
public class SqlValidateController {

	@Autowired
	private CatSQLDataCrawler catSqlDataCrawler;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object validate(String database, String ruleName) {
		// crawler data from cat
		DatabaseSql databaseSql = catSqlDataCrawler.crawler(database);

		// init router
		DataSourceRouterFactory routerFactory = new LionDataSourceRouterFactory(ruleName);
		ShardRouter router = routerFactory.getRouter();
		router.init();

		List<SQLValidateDto> results = new ArrayList<SQLValidateDto>();

		for (Entry<String, SqlEntity> sqlEntry : databaseSql.getSqls().entrySet()) {
			String sqlName = sqlEntry.getKey();
			SqlEntity sqlEntity = sqlEntry.getValue();
			SQLValidateDto dto = new SQLValidateDto();

			String sql = sqlEntity.getSql();

			try {
				router.validate(sql);
			} catch (ShardRouterException e) {
				dto.setShardSupported(false);
				dto.setErrorMsg(e.getCause().getMessage());
			} catch (SyntaxException e) {
				dto.setSyntaxSupported(false);
				dto.setErrorMsg(e.getMessage());
			}

			dto.setApp(sqlEntity.getDomain());
			dto.setSqlName(sqlName);
			dto.setSql(sql);

			results.add(dto);
		}

		return results;
	}

	@RequestMapping(value = "/dbs", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllDatabases() {
		DatabaseSql databaseSql = catSqlDataCrawler.crawler("cat");

		return databaseSql.getDatabases();
	}
}
