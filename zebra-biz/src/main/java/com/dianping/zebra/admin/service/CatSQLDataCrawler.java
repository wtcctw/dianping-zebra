package com.dianping.zebra.admin.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.input.CharSequenceInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.dianping.cat.Cat;

@Service
public class CatSQLDataCrawler {

	@Autowired
	private HttpService httpService;

	private static String urlTemplate = "http://cat.qa.dianpingoa.com/cat/r/storage?op=view&type=SQL&id=%s&ip=All&forceDownload=xml";

	public DatabaseSql crawler(String database) {
		String url = String.format(urlTemplate, database);
		String sendGet = httpService.sendGet(url);
		DatabaseSql databaseSql = new DatabaseSql(database);

		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();

			InputStream ins = new CharSequenceInputStream(sendGet, "utf-8");

			saxParser.parse(ins, new ElementHandler(databaseSql));
		} catch (Exception e) {
			System.out.println(e);
			Cat.logError(e);
		}

		return databaseSql;
	}

	public static class ElementHandler extends DefaultHandler {

		private DatabaseSql databaseSql;

		private String qName;

		private String domain;

		public ElementHandler(DatabaseSql databaseSql) {
			this.databaseSql = databaseSql;
		}

		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			this.qName = qName;

			if (this.qName.equalsIgnoreCase("domain")) {
				domain = attributes.getValue("id");
			}

			if (this.qName.equalsIgnoreCase("sql")) {
				SqlEntity entity = new SqlEntity();

				String sqlName = attributes.getValue("id");
				String sql = attributes.getValue("statement");
				sql = sql.replace("&gt;", ">");
				sql = sql.replace("&lt;", "<");
				sql = sql.trim();

				entity.setDomain(domain);
				entity.setSqlName(sqlName);
				entity.setSql(sql);
				entity.setCount(Integer.parseInt(attributes.getValue("count")));

				this.databaseSql.getSqls().put(sqlName, entity);
			}
		}

		public void characters(char ch[], int start, int length) throws SAXException {
			if (this.qName.equalsIgnoreCase("id")) {
				String db = new String(ch, start, length).trim();

				if (db != null && db.length() > 0) {
					this.databaseSql.getDatabases().add(db);
				}
			}
		}
	}

	public static class DatabaseSql {
		private String database;

		private Set<String> databases = new HashSet<String>();

		// <database, <sqlName, sqlentity>>
		private Map<String, SqlEntity> sqls = new HashMap<String, SqlEntity>();

		public DatabaseSql(String database) {
			this.database = database;
		}

		public String getDatabase() {
			return database;
		}

		public void setDatabase(String database) {
			this.database = database;
		}

		public Set<String> getDatabases() {
			return databases;
		}

		public void setDatabases(Set<String> databases) {
			this.databases = databases;
		}

		public Map<String, SqlEntity> getSqls() {
			return sqls;
		}

		public void setSqls(Map<String, SqlEntity> sqls) {
			this.sqls = sqls;
		}

		@Override
		public String toString() {
			return "DatabaseSql [database=" + database + ", databases=" + databases + ", sqls=" + sqls + "]";
		}
	}

	public static class SqlEntity {
		private String sqlName;

		private String sql;

		private int count;

		private String domain;

		public String getSqlName() {
			return sqlName;
		}

		public void setSqlName(String sqlName) {
			this.sqlName = sqlName;
		}

		public String getSql() {
			return sql;
		}

		public void setSql(String sql) {
			this.sql = sql;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}

		@Override
		public String toString() {
			return "SqlEntity [sqlName=" + sqlName + ", sql=" + sql + ", count=" + count + ", domain=" + domain + "]";
		}
	}

}
