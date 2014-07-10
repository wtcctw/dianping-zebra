package com.dianping.zebra.admin.tools;

import java.io.File;
import java.io.InputStream;

import org.junit.Test;
import org.unidal.webres.helper.Files;

import com.dianping.zebra.admin.query.entity.Hits;
import com.dianping.zebra.admin.query.entity.Query;
import com.dianping.zebra.admin.query.transform.DefaultJsonParser;
import com.dianping.zebra.admin.sqlMap.entity.Insert;
import com.dianping.zebra.admin.sqlMap.entity.SqlMap;

public class SqlAnalyzer {

	@Test
	public void analyzer() throws Exception {
		InputStream is = getClass().getClassLoader().getResourceAsStream("query.result");
		Query query = DefaultJsonParser.parse(Query.class, is);

		File insertFile = new File("src/test/resources/com/dianping/zebra/admin/tools/insert.sql");
		File updateFile = new File("src/test/resources/com/dianping/zebra/admin/tools/update.sql");
		File deleteFile = new File("src/test/resources/com/dianping/zebra/admin/tools/delete.sql");
		File selectFile = new File("src/test/resources/com/dianping/zebra/admin/tools/select.sql");

		StringBuilder insertSb = new StringBuilder(1024 * 10);
		StringBuilder updateSb = new StringBuilder(1024 * 10);
		StringBuilder deleteSb = new StringBuilder(1024 * 10);
		StringBuilder selectSb = new StringBuilder(1024 * 10);
		for (Hits hits : query.getHits()) {
			String code = hits.getFields().getCode();

			if (!code.contains("sqlMapConfig")) {
				try {
					SqlMap parse = com.dianping.zebra.admin.sqlMap.transform.DefaultSaxParser.parse(code);

					for (Insert insert : parse.getInserts()) {
						if (insert.getDynamicElements().size() > 1) {
							insertSb.append(insert);
						}
					}

					for (String update : parse.getUpdates()) {
						updateSb.append(update);
					}

					for (String delete : parse.getDeletes()) {
						deleteSb.append(delete);
					}

					for (String select : parse.getSelects()) {
						selectSb.append(select);
					}
				} catch (Throwable e) {
				}
			}
		}

		Files.forIO().writeTo(insertFile, insertSb.toString());
		Files.forIO().writeTo(updateFile, updateSb.toString());
		Files.forIO().writeTo(deleteFile, deleteSb.toString());
		Files.forIO().writeTo(selectFile, selectSb.toString());
	}
}
