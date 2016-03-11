package com.dianping.zebra.shard.parser;

import java.util.Collections;
import java.util.Map;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlLexer;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.parser.Lexer.CommentHandler;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.parser.Token;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.dianping.zebra.shard.exception.ZebraParseException;
import com.dianping.zebra.shard.parser.visitor.MySQLDeleteASTVisitor;
import com.dianping.zebra.shard.parser.visitor.MySQLInsertASTVisitor;
import com.dianping.zebra.shard.parser.visitor.MySQLSelectASTVisitor;
import com.dianping.zebra.shard.parser.visitor.MySQLUpdateASTVisitor;
import com.dianping.zebra.shard.util.LRUCache;
import com.dianping.zebra.util.SqlType;

public class MySQLParser {

	private final static Map<String, MySQLParseResult> parsedSqlCache = Collections
			.synchronizedMap(new LRUCache<String, MySQLParseResult>(1000));

	public static MySQLParseResult parse(String sql) throws ZebraParseException {
		if (!parsedSqlCache.containsKey(sql)) {
			MySqlLexer lexer = new MySqlLexer(sql);
			HintCommentHandler commentHandler = new HintCommentHandler();
			lexer.setCommentHandler(commentHandler);
			lexer.nextToken();

			SQLStatementParser parser = new MySqlStatementParser(lexer);
			SQLStatement stmt = parser.parseStatement();
			MySQLParseResult result = null;

			if (stmt instanceof SQLSelectStatement) {
				result = new MySQLParseResult(SqlType.SELECT, stmt);
				SQLASTVisitor visitor = new MySQLSelectASTVisitor(result);
				stmt.accept(visitor);
			} else if (stmt instanceof SQLInsertStatement) {
				result = new MySQLParseResult(SqlType.INSERT, stmt);
				SQLASTVisitor visitor = new MySQLInsertASTVisitor(result);
				stmt.accept(visitor);
			} else if (stmt instanceof SQLUpdateStatement) {
				result = new MySQLParseResult(SqlType.UPDATE, stmt);
				SQLASTVisitor visitor = new MySQLUpdateASTVisitor(result);
				stmt.accept(visitor);
			} else if (stmt instanceof SQLDeleteStatement) {
				result = new MySQLParseResult(SqlType.DELETE, stmt);
				SQLASTVisitor visitor = new MySQLDeleteASTVisitor(result);
				stmt.accept(visitor);
			} else {
				throw new ZebraParseException("UnSupported sql type in sharding jdbc.");
			}

			result.getRouterContext().setHintComment(commentHandler.getHintComment());

			parsedSqlCache.put(sql, result);
		}

		return parsedSqlCache.get(sql);
	}

	public static class HintCommentHandler implements CommentHandler {

		private String hintComment = null;

		@Override
		public boolean handle(Token lastToken, String comment) {
			if (lastToken == null && comment.contains("zebra")) {
				hintComment = comment;
			}
			return false;
		}

		public String getHintComment() {
			return hintComment;
		}
	}
}
