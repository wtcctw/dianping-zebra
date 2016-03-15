package com.dianping.zebra.shard.router.rule;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Dozer @ 7/3/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class SimpleDataSourceProviderTest {
	@Test
	public void test_split_db() throws Exception {
		SimpleDataSourceProvider target = new SimpleDataSourceProvider();
		assertArrayEquals(new String[] { "a", "b_2", "b_3", "b_4", "c" }, target.splitDb("a,b_[2-4],c").toArray());
	}
	
	@Test
	public void test_split_table() throws Exception {
		SimpleDataSourceProvider target = new SimpleDataSourceProvider("UOD_Order","unifiedorder_operation","alldb:[_Operation0,_Operation0]","0");
	
		Map<String, Set<String>> allDBAndTables = target.getAllDBAndTables();
		
		System.out.println(allDBAndTables);
	}
	
	@Test
	public void test_split_table1() throws Exception {
		SimpleDataSourceProvider target = new SimpleDataSourceProvider("DP_GroupFollowNote","mysqldianpinggroup_dbo","alldb:[]","0");
	
		Map<String, Set<String>> allDBAndTables = target.getAllDBAndTables();
		
		System.out.println(allDBAndTables);
	}
}