package com.dianping.zebra.shard.parser;

import org.junit.Assert;
import org.junit.Test;

import com.dianping.zebra.shard.parser.qlParser.DPMySQLParser;
import com.dianping.zebra.shard.parser.qlParser.MySQLWalker;
import com.dianping.zebra.shard.parser.sqlParser.Insert;

public class InsertTest {

	@Test
	public void testInsert1() throws Exception {
		String sql = "insert into welife_users(uid,bid) values (20,2127114697)";

		MySQLWalker.beg_return ret = DPMySQLParser.parse(sql);
		Insert insert = (Insert)ret.obj;

		Assert.assertEquals("welife_users", insert.getTableName());
	}
	
	//Not Support syntax like "insert ignore table(xx,xx) values (xx,xx)" when 'into' is missing
	@Test
	public void testInsert2() throws Exception {
		String sql = "insert IGNORE into TG_DealCurrentJoin(DealID, CurrentJoin) VALUES (?, 0)";
		
		MySQLWalker.beg_return ret = DPMySQLParser.parse(sql);
		Insert insert = (Insert)ret.obj;
		
		Assert.assertEquals("tg_dealcurrentjoin", insert.getTableName());
	}
	

}
