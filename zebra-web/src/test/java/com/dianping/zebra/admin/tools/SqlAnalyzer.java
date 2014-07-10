package com.dianping.zebra.admin.tools;

import java.io.IOException;
import java.io.InputStream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Ignore;
import org.junit.Test;
import org.unidal.webres.helper.Files;
import org.xml.sax.SAXException;

import com.dianping.zebra.admin.query.entity.Hits;
import com.dianping.zebra.admin.query.entity.Query;
import com.dianping.zebra.admin.query.transform.DefaultJsonBuilder;
import com.dianping.zebra.admin.query.transform.DefaultJsonParser;
import com.dianping.zebra.admin.query.transform.DefaultSaxParser;
import com.dianping.zebra.admin.sqlMap.entity.Insert;
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
		

//		int index = 3;
		//String code = query.getHits().get(8).getFields().getCode();
		//System.out.println(code);
		
		//SqlMap parse = com.dianping.zebra.admin.sqlMap.transform.DefaultSaxParser.parse(is);

		//System.out.println(parse);
//		int index = 0;
		for (Hits hits : query.getHits()) {
			String code = hits.getFields().getCode();

			if (!code.contains("sqlMapConfig")) {
				try{
					SqlMap parse = com.dianping.zebra.admin.sqlMap.transform.DefaultSaxParser.parse(code);
					
					
					for(Insert insert : parse.getInserts()){
						if(insert.getSelectKey() != null){
							System.out.println(insert);
						}
					}
				}catch(Throwable e){
					System.out.println(code);
					return;
				}
			}
		}

	}

	@Test
	@Ignore
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
