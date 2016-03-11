package com.dianping.zebra.shard.parser.visitor;

import com.dianping.zebra.shard.parser.MySQLParseResult;

public class MySQLInsertASTVisitor extends AbstractMySQLASTVisitor {

	public MySQLInsertASTVisitor(MySQLParseResult result) {
		super(result);
	}

}
