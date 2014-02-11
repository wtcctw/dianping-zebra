package com.dianping.zebra.parser.sqlParser.groupFunction;

public class Sum extends CommonOneColumnFunction implements GroupFunction {
	@Override
	public String getFunctionName() {
		return "sum";
	}

}
