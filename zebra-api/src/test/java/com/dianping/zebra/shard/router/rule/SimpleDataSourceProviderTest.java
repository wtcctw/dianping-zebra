package com.dianping.zebra.shard.router.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.dianping.zebra.shard.router.rule.mapping.DBTablesMapping;
import com.dianping.zebra.shard.router.rule.mapping.SimpleDBTablesMappingManager;

import junit.framework.Assert;

public class SimpleDataSourceProviderTest {
	@Test
	public void test_split_db() throws Exception {
		SimpleDBTablesMappingManager target = new SimpleDBTablesMappingManager("UOD_Order","a,b_[2-4]","alldb:[_Operation0,_Operation31]","0");
		Set<String> hashSet = new HashSet<String>();
		hashSet.add("a");
		hashSet.add("b_2");
		hashSet.add("b_3");
		hashSet.add("b_4");
		Assert.assertEquals(hashSet, target.getAllMappings().keySet());
	}
	
	@Test
	public void test_split_db1() throws Exception {
		SimpleDBTablesMappingManager target = new SimpleDBTablesMappingManager("UOD_Order","a","alldb:[_Operation,_Operation]","0");
		Set<String> hashSet = new HashSet<String>();
		hashSet.add("a");
		Assert.assertEquals(hashSet, target.getAllMappings().keySet());
	}
	
	@Test
	public void test_split_table() throws Exception {
		SimpleDBTablesMappingManager target = new SimpleDBTablesMappingManager("UOD_Order","unifiedorder_operation","alldb:[_Operation0,_Operation0]","0");
	
		Map<String, Set<String>> allDBAndTables = target.getAllMappings();
		Assert.assertEquals(1, allDBAndTables.size());
		Assert.assertEquals(1, allDBAndTables.get("unifiedorder_operation").size());
		Assert.assertEquals(true, allDBAndTables.get("unifiedorder_operation").contains("UOD_Order_Operation0"));
		
		DBTablesMapping dataSource = target.getMappingByIndex(0);
		Assert.assertEquals(1, dataSource.getTables().size());
	}
	
	@Test
	public void test_rule_parser_alldb() throws Exception {
		SimpleDBTablesMappingManager target = new SimpleDBTablesMappingManager("UOD_Order","a,b_[2-4]","alldb:[_Operation0,_Operation31]","0");
	
		Map<String, Set<String>> allDBAndTables = target.getAllMappings();
		Assert.assertEquals(4, allDBAndTables.size());
		Assert.assertEquals(8, allDBAndTables.get("a").size());
		Assert.assertEquals(8, allDBAndTables.get("b_2").size());
		Assert.assertEquals(8, allDBAndTables.get("b_3").size());
		Assert.assertEquals(8, allDBAndTables.get("b_4").size());
		
		DBTablesMapping dataSource = target.getMappingByIndex(0);
		Assert.assertEquals(8, dataSource.getTables().size());
		Assert.assertTrue(dataSource.getTables().contains("UOD_Order_Operation0"));
		Assert.assertTrue(dataSource.getTables().contains("UOD_Order_Operation7"));
		Assert.assertFalse(dataSource.getTables().contains("UOD_Order_Operation8"));
	}
	
	@Test
	public void test_rule_parser_everydb() throws Exception {
		SimpleDBTablesMappingManager target = new SimpleDBTablesMappingManager("UOD_Order","a,b","everydb:[_Operation0,_Operation7]","0");
	
		Map<String, Set<String>> allDBAndTables = target.getAllMappings();
		Assert.assertEquals(2, allDBAndTables.size());
		Assert.assertEquals(8, allDBAndTables.get("a").size());
		Assert.assertEquals(8, allDBAndTables.get("b").size());
		
		DBTablesMapping dataSource = target.getMappingByIndex(0);
		Assert.assertEquals(8, dataSource.getTables().size());
		Assert.assertTrue(dataSource.getTables().contains("UOD_Order_Operation0"));
		Assert.assertTrue(dataSource.getTables().contains("UOD_Order_Operation7"));
		
		dataSource = target.getMappingByIndex(1);
		Assert.assertEquals(8, dataSource.getTables().size());
		Assert.assertTrue(dataSource.getTables().contains("UOD_Order_Operation0"));
		Assert.assertTrue(dataSource.getTables().contains("UOD_Order_Operation7"));
	}
}