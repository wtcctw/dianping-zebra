package com.dianping.zebra.shard.parser;

public interface SQLRewrite{
	
	public String rewrite(MySQLParseResult pr,String physicalTableName);
	
}
