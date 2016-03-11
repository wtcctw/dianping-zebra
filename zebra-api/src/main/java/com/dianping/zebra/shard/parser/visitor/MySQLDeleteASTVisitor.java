package com.dianping.zebra.shard.parser.visitor;

import com.dianping.zebra.shard.parser.MySQLParseResult;

public class MySQLDeleteASTVisitor extends AbstractMySQLASTVisitor {

	public MySQLDeleteASTVisitor(MySQLParseResult result) {
		super(result);
	}

}
