package com.dianping.zebra.admin.tools;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.dianping.zebra.admin.query.entity.Hits;
import com.dianping.zebra.admin.query.entity.Query;
import com.dianping.zebra.admin.query.transform.DefaultJsonBuilder;
import com.dianping.zebra.admin.query.transform.DefaultJsonParser;
import com.dianping.zebra.admin.query.transform.DefaultSaxParser;

public class SqlAnalyzer {

	@Test
	public void analyzer() throws IOException, SAXException {

		Query parse = DefaultSaxParser.parse(getClass().getClassLoader().getResourceAsStream("query.xml"));

		System.out.println(parse);

		DefaultJsonBuilder jb = new DefaultJsonBuilder();

		System.out.println(jb.build(parse));
	}

	@Test
	public void analyzer2() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("query.result");

		Query parse = DefaultJsonParser.parse(Query.class, is);

		for (Hits hits : parse.getHits()) {
			String code = hits.getFields().getCode();
			System.out.println(code);

			// try {
			// SqlMap parse2 = com.dianping.zebra.admin.sqlMap.transform.DefaultSaxParser.parse(code);
			//
			// } catch (SAXException e) {
			// }
		}
	}
}
