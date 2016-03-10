package com.dianping.zebra.shard.parser;

public interface SQLRewrite{
	
	public String rewrite(MySQLParserResult pr,String physicalTableName);
	
}
