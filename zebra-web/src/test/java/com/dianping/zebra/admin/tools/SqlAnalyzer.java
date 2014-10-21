package com.dianping.zebra.admin.tools;

import com.dianping.zebra.admin.query.entity.Hits;
import com.dianping.zebra.admin.query.entity.Query;
import com.dianping.zebra.admin.query.transform.DefaultJsonParser;
import com.dianping.zebra.admin.sqlMap.entity.Insert;
import com.dianping.zebra.admin.sqlMap.entity.SqlMap;
import com.dianping.zebra.group.util.SqlUtils;
import org.junit.Test;
import org.unidal.webres.helper.Files;

import java.io.File;
import java.io.InputStream;

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
							try {
								SqlUtils.getSqlType(insert.toString());
							} catch (Exception t) {
								// System.err.println(insert);
							}
							insertSb.append(insert);
						}
					}

					for (String update : parse.getUpdates()) {
						try {
							SqlUtils.getSqlType(update);
						} catch (Exception t) {
							// System.err.println(update);
						}
						updateSb.append(update);
					}

					for (String delete : parse.getDeletes()) {
						try {
							SqlUtils.getSqlType(delete.trim());
						} catch (Exception t) {
							// System.err.println(delete);
						}
						deleteSb.append(delete);
					}

					for (String select : parse.getSelects()) {
						try {
							if (!select.trim().toLowerCase().startsWith("order by")) {
								SqlUtils.getSqlType(select.trim());
							}
						} catch (Exception t) {
							System.err.println(select.trim());
						}
						selectSb.append(select);
					}
				} catch (Exception e) {
				}
			}
		}

		Files.forIO().writeTo(insertFile, insertSb.toString());
		Files.forIO().writeTo(updateFile, updateSb.toString());
		Files.forIO().writeTo(deleteFile, deleteSb.toString());
		Files.forIO().writeTo(selectFile, selectSb.toString());
	}
}
