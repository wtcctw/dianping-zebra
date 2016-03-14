package com.dianping.zebra.shard.router.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.dianping.zebra.shard.router.rule.datasource.DataSourceBO;
import com.dianping.zebra.shard.router.rule.datasource.SimpleDataSourceProvider;

import junit.framework.Assert;

public class SimpleDataSourceProviderTest {
	@Test
	public void test_split_db() throws Exception {
		SimpleDataSourceProvider target = new SimpleDataSourceProvider("UOD_Order","a,b_[2-4]","alldb:[_Operation0,_Operation31]","0");
		Set<String> hashSet = new HashSet<String>();
		hashSet.add("a");
		hashSet.add("b_2");
		hashSet.add("b_3");
		hashSet.add("b_4");
		Assert.assertEquals(hashSet, target.getAllDBAndTables().keySet());
	}
	
	@Test
	public void test_split_table() throws Exception {
		SimpleDataSourceProvider target = new SimpleDataSourceProvider("UOD_Order","unifiedorder_operation","alldb:[_Operation0,_Operation0]","0");
	
		Map<String, Set<String>> allDBAndTables = target.getAllDBAndTables();
		Assert.assertEquals(1, allDBAndTables.size());
		Assert.assertEquals(1, allDBAndTables.get("unifiedorder_operation").size());
		Assert.assertEquals(true, allDBAndTables.get("unifiedorder_operation").contains("UOD_Order_Operation0"));
		
		DataSourceBO dataSource = target.getDataSource(0);
		Assert.assertEquals(1, dataSource.getPhysicalTables().size());
	}
	
	@Test
	public void test_rule_parser_alldb() throws Exception {
		SimpleDataSourceProvider target = new SimpleDataSourceProvider("UOD_Order","a,b_[2-4]","alldb:[_Operation0,_Operation31]","0");
	
		Map<String, Set<String>> allDBAndTables = target.getAllDBAndTables();
		Assert.assertEquals(4, allDBAndTables.size());
		Assert.assertEquals(8, allDBAndTables.get("a").size());
		Assert.assertEquals(8, allDBAndTables.get("b_2").size());
		Assert.assertEquals(8, allDBAndTables.get("b_3").size());
		Assert.assertEquals(8, allDBAndTables.get("b_4").size());
		
		DataSourceBO dataSource = target.getDataSource(0);
		Assert.assertEquals(8, dataSource.getPhysicalTables().size());
		Assert.assertTrue(dataSource.getPhysicalTables().contains("UOD_Order_Operation0"));
		Assert.assertTrue(dataSource.getPhysicalTables().contains("UOD_Order_Operation7"));
		Assert.assertFalse(dataSource.getPhysicalTables().contains("UOD_Order_Operation8"));
	}
	
	@Test
	public void test_rule_parser_everydb() throws Exception {
		SimpleDataSourceProvider target = new SimpleDataSourceProvider("UOD_Order","a,b","everydb:[_Operation0,_Operation7]","0");
	
		Map<String, Set<String>> allDBAndTables = target.getAllDBAndTables();
		Assert.assertEquals(2, allDBAndTables.size());
		Assert.assertEquals(8, allDBAndTables.get("a").size());
		Assert.assertEquals(8, allDBAndTables.get("b").size());
		
		DataSourceBO dataSource = target.getDataSource(0);
		Assert.assertEquals(8, dataSource.getPhysicalTables().size());
		Assert.assertTrue(dataSource.getPhysicalTables().contains("UOD_Order_Operation0"));
		Assert.assertTrue(dataSource.getPhysicalTables().contains("UOD_Order_Operation7"));
		
		dataSource = target.getDataSource(1);
		Assert.assertEquals(8, dataSource.getPhysicalTables().size());
		Assert.assertTrue(dataSource.getPhysicalTables().contains("UOD_Order_Operation0"));
		Assert.assertTrue(dataSource.getPhysicalTables().contains("UOD_Order_Operation7"));
	}
}