/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-6-16 $Id$
 * 
 * Copyright 2010 dianping.com. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.shard.parser.qlParser;

import java.io.IOException;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenRewriteStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.TreeNodeStream;
import org.apache.log4j.Logger;

import com.dianping.zebra.shard.parser.condition.WhereCondition;
import com.dianping.zebra.shard.parser.qlParser.MySQLWalker.beg_return;
import com.dianping.zebra.shard.parser.sqlParser.AntlrStringStream;
import com.dianping.zebra.shard.parser.sqlParser.Select;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.GroupFunctionRegister;
import com.dianping.zebra.shard.parser.valueObject.function.FunctionRegister;

public class DPMySQLParser {
	
	private static Logger logger = Logger.getLogger(DPMySQLParser.class);
	
	public static void main(String[] args) throws RecognitionException, IOException {
		beg_return parsed = parse("select * from Torder");
		WhereCondition where = ((Select) parsed.obj).getWhere();
		System.out.println(where);
	}

	public static MySQLWalker.beg_return parse(String sql) throws RecognitionException, IOException {
		
		sql = sql.replaceAll("\\n", " ");
		if(sql.endsWith(";")) {
			sql = sql.substring(0, sql.length()-1);
		}
		
		AntlrStringStream st = new AntlrStringStream(sql);

		MySQLParserLexer pl = new MySQLParserLexer(st);
		TokenRewriteStream tokens = new TokenRewriteStream(pl);
		MySQLParserParser pa = new MySQLParserParser(tokens);
		pa.setFunctionMap(FunctionRegister.funcReg);
		pa.setGroupFunc(GroupFunctionRegister.reg);
		MySQLParserParser.beg_return beg = null;
		beg = pa.beg();
		CommonTree tree = (CommonTree) beg.getTree();
		logger.debug(tree.toStringTree());

		CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
		nodes.setTokenStream(tokens);
		MySQLWalker walker = new MySQLWalker(nodes);
		walker.setGroupFunc(GroupFunctionRegister.reg);
		walker.setFunctionMap(FunctionRegister.funcReg);
		MySQLWalker.beg_return ret = walker.beg();
		TreeNodeStream tns = walker.getTreeNodeStream();


//		StringBuilder sb = new StringBuilder(sql);
//		int i = 0;
//		for (Map.Entry<Integer, String> entry : walker.pos2TableName.entrySet()) {
//			int pos = entry.getKey();
//			String tn = entry.getValue();
//			sb.replace(pos + i, pos + i + tn.length(), tn + "0");
//			i++;
//		}
//		System.out.println(sb);
		
		
		ret.obj.setPos2TableName(walker.pos2TableName);
		return ret;

	}
}
