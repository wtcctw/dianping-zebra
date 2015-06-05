package com.dianping.zebra.admin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dianping.zebra.admin.service.CatSQLDataCrawler.DatabaseSql;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config/spring/appcontext-*.xml")
public class CatDataCrawlerTest {
	
	@Autowired
	private CatSQLDataCrawler crawler;
	
	@Test
	public void test1(){
		DatabaseSql crawler2 = crawler.crawler("DianPing");
		
		System.out.println(crawler2);
		
		
	}

}
