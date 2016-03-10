package com.dianping.zebra.shard.parser;

import java.util.Collections;
import java.util.Map;

import com.alibaba.druid.sql.dialect.mysql.parser.MySqlLexer;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.parser.Lexer.CommentHandler;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.parser.Token;
import com.dianping.zebra.shard.exception.ZebraParseException;
import com.dianping.zebra.shard.util.LRUCache;

public class MySQLParser {

	private final static Map<String, MySQLParserResult> parsedSqlCache = Collections
			.synchronizedMap(new LRUCache<String, MySQLParserResult>(1000));

	public static MySQLParserResult parse(String sql) throws ZebraParseException {
		if (!parsedSqlCache.containsKey(sql)) {
			MySqlLexer lexer = new MySqlLexer(sql);
			HintCommentHandler commentHandler = new HintCommentHandler();
			lexer.setCommentHandler(commentHandler);
			lexer.nextToken();

			SQLStatementParser parser = new MySqlStatementParser(lexer);
			MySQLParserResult parserResult = new MySQLParserResult(parser.parseStatement());
			parserResult.setHintComment(commentHandler.getHintComment());

			parsedSqlCache.put(sql, parserResult);
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
