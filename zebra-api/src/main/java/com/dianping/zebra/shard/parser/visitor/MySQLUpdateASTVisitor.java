package com.dianping.zebra.shard.parser.visitor;

import com.dianping.zebra.shard.parser.MySQLParseResult;

public class MySQLUpdateASTVisitor extends AbstractMySQLASTVisitor {

	public MySQLUpdateASTVisitor(MySQLParseResult result) {
		super(result);
	}

}
