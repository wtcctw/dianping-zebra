package com.dianping.zebra.shard.parser.sqlParser.groupFunction;

public class Min extends CommonOneColumnFunction implements GroupFunction {
	@Override
	public String getFunctionName() {
		return "min";
	}
}
