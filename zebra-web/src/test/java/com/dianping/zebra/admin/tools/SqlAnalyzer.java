package com.dianping.zebra.admin.tools;

import java.io.IOException;
import java.io.InputStream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Test;
import org.unidal.webres.helper.Files;
import org.xml.sax.SAXException;

import com.dianping.zebra.admin.query.entity.Query;
import com.dianping.zebra.admin.query.transform.DefaultJsonBuilder;
import com.dianping.zebra.admin.query.transform.DefaultJsonParser;
import com.dianping.zebra.admin.query.transform.DefaultSaxParser;
import com.dianping.zebra.admin.sqlMap.entity.SqlMap;

public class SqlAnalyzer {

	@Test
	public void analyzer() throws IOException, SAXException {
		Query parse = DefaultSaxParser.parse(getClass().getClassLoader().getResourceAsStream("query.xml"));

		System.out.println(parse);

		DefaultJsonBuilder jb = new DefaultJsonBuilder();

		System.out.println(jb.build(parse));
	}
	
	@Test
	public void test() throws Exception {
		InputStream is = getClass().getClassLoader().getResourceAsStream("query.result");
		Query query = DefaultJsonParser.parse(Query.class, is);
		
		System.out.println(query.getHits().get(0).getFields().getCode());
		SqlMap parse = com.dianping.zebra.admin.sqlMap.transform.DefaultSaxParser.parse(query.getHits().get(0).getFields().getCode());
		
		System.out.println(parse);
	}

	@Test
	public void analyzer2() throws Exception {
		InputStream is = getClass().getClassLoader().getResourceAsStream("query.result");
		String json = Files.forIO().readFrom(is, "utf-8");

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByExtension("js");
		String script = String.format(
		      "var o=%s; var codes=[]; for (var i in o.hits) { codes.push(o.hits[i].fields.code) }; codes;", json);
		Object result = engine.eval(script);
		
		System.out.println(result);
	}
}
