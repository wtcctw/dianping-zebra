package com.dianping.zebra.shard.parser.sqlParser.groupFunction;

public class Sum extends CommonOneColumnFunction implements GroupFunction {
	@Override
	public String getFunctionName() {
		return "sum";
	}

}
