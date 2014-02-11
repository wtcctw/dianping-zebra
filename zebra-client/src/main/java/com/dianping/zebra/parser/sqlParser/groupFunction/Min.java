package com.dianping.zebra.parser.sqlParser.groupFunction;

public class Min extends CommonOneColumnFunction implements GroupFunction {
	@Override
	public String getFunctionName() {
		return "min";
	}
}
