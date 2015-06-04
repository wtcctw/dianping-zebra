// $ANTLR 3.5.2 MySQLParser.g 2015-06-04 10:14:38

package com.dianping.zebra.shard.parser.qlParser;

import java.util.Map;
import java.util.HashMap;

import com.dianping.zebra.shard.parser.sqlParser.groupFunction.GroupFunction;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.GroupFunctionRegister;
import com.dianping.zebra.shard.parser.valueObject.FunctionConvertor;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class MySQLParserParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALIAS", "AND", "ARROW", "AS", 
		"ASC", "ASTERISK", "BETWEEN", "COLUMN", "COLUMNS", "COL_TAB", "COMMA", 
		"CONDITION_LEFT", "CONDITION_OR", "CONDITION_OR_NOT", "COUNTCOLUMN", "DELETE", 
		"DESC", "DISPLAYED_COLUMN", "DISPLAYED_COUNT_COLUMN", "DIVIDE", "DOT", 
		"DOUBLEQUOTED_STRING", "DOUBLEVERTBAR", "EQ", "EXPONENT", "EXPR", "GEQ", 
		"GROUPBY", "GTH", "ID", "IGNORE", "IN", "INFINITE", "INSERT", "INSERT_VAL", 
		"IN_LISTS", "IS", "ISNOT", "JOINTYPE", "LEQ", "LIKE", "LPAREN", "LTH", 
		"MINUS", "MOD", "N", "NAN", "NOT", "NOT_BETWEEN", "NOT_EQ", "NOT_LIKE", 
		"NULL", "NUMBER", "OR", "ORDERBY", "PLUS", "POINT", "PRIORITY", "QUOTED_STRING", 
		"QUTED_STR", "RANGE", "RPAREN", "SELECT", "SELECT_LIST", "SET", "SET_ELE", 
		"SKIP", "SUBQUERY", "TABLENAME", "TABLENAMES", "UPDATE", "WHERE", "WS", 
		"'?'", "'AND'", "'AS'", "'BETWEEN'", "'BY'", "'CONCAT'", "'COUNT'", "'DELETE'", 
		"'DISTINCT'", "'FALSE'", "'FORCE'", "'FROM'", "'GROUP BY'", "'IGNORE'", 
		"'IN'", "'INDEX'", "'INFINITE'", "'INNER JOIN'", "'INSERT'", "'INTO'", 
		"'IS'", "'JOIN'", "'LEFT JOIN'", "'LIKE'", "'LIMIT'", "'NAN'", "'NOT'", 
		"'NULL'", "'ON'", "'OR'", "'ORDER'", "'RIGHT JOIN'", "'ROWNUM'", "'SELECT'", 
		"'SET'", "'TRUE'", "'UPDATE'", "'VALUES'", "'WHERE'"
	};
	public static final int EOF=-1;
	public static final int T__77=77;
	public static final int T__78=78;
	public static final int T__79=79;
	public static final int T__80=80;
	public static final int T__81=81;
	public static final int T__82=82;
	public static final int T__83=83;
	public static final int T__84=84;
	public static final int T__85=85;
	public static final int T__86=86;
	public static final int T__87=87;
	public static final int T__88=88;
	public static final int T__89=89;
	public static final int T__90=90;
	public static final int T__91=91;
	public static final int T__92=92;
	public static final int T__93=93;
	public static final int T__94=94;
	public static final int T__95=95;
	public static final int T__96=96;
	public static final int T__97=97;
	public static final int T__98=98;
	public static final int T__99=99;
	public static final int T__100=100;
	public static final int T__101=101;
	public static final int T__102=102;
	public static final int T__103=103;
	public static final int T__104=104;
	public static final int T__105=105;
	public static final int T__106=106;
	public static final int T__107=107;
	public static final int T__108=108;
	public static final int T__109=109;
	public static final int T__110=110;
	public static final int T__111=111;
	public static final int T__112=112;
	public static final int T__113=113;
	public static final int T__114=114;
	public static final int T__115=115;
	public static final int ALIAS=4;
	public static final int AND=5;
	public static final int ARROW=6;
	public static final int AS=7;
	public static final int ASC=8;
	public static final int ASTERISK=9;
	public static final int BETWEEN=10;
	public static final int COLUMN=11;
	public static final int COLUMNS=12;
	public static final int COL_TAB=13;
	public static final int COMMA=14;
	public static final int CONDITION_LEFT=15;
	public static final int CONDITION_OR=16;
	public static final int CONDITION_OR_NOT=17;
	public static final int COUNTCOLUMN=18;
	public static final int DELETE=19;
	public static final int DESC=20;
	public static final int DISPLAYED_COLUMN=21;
	public static final int DISPLAYED_COUNT_COLUMN=22;
	public static final int DIVIDE=23;
	public static final int DOT=24;
	public static final int DOUBLEQUOTED_STRING=25;
	public static final int DOUBLEVERTBAR=26;
	public static final int EQ=27;
	public static final int EXPONENT=28;
	public static final int EXPR=29;
	public static final int GEQ=30;
	public static final int GROUPBY=31;
	public static final int GTH=32;
	public static final int ID=33;
	public static final int IGNORE=34;
	public static final int IN=35;
	public static final int INFINITE=36;
	public static final int INSERT=37;
	public static final int INSERT_VAL=38;
	public static final int IN_LISTS=39;
	public static final int IS=40;
	public static final int ISNOT=41;
	public static final int JOINTYPE=42;
	public static final int LEQ=43;
	public static final int LIKE=44;
	public static final int LPAREN=45;
	public static final int LTH=46;
	public static final int MINUS=47;
	public static final int MOD=48;
	public static final int N=49;
	public static final int NAN=50;
	public static final int NOT=51;
	public static final int NOT_BETWEEN=52;
	public static final int NOT_EQ=53;
	public static final int NOT_LIKE=54;
	public static final int NULL=55;
	public static final int NUMBER=56;
	public static final int OR=57;
	public static final int ORDERBY=58;
	public static final int PLUS=59;
	public static final int POINT=60;
	public static final int PRIORITY=61;
	public static final int QUOTED_STRING=62;
	public static final int QUTED_STR=63;
	public static final int RANGE=64;
	public static final int RPAREN=65;
	public static final int SELECT=66;
	public static final int SELECT_LIST=67;
	public static final int SET=68;
	public static final int SET_ELE=69;
	public static final int SKIP=70;
	public static final int SUBQUERY=71;
	public static final int TABLENAME=72;
	public static final int TABLENAMES=73;
	public static final int UPDATE=74;
	public static final int WHERE=75;
	public static final int WS=76;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public MySQLParserParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public MySQLParserParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return MySQLParserParser.tokenNames; }
	@Override public String getGrammarFileName() { return "MySQLParser.g"; }


		protected  Map<String, FunctionConvertor> functionMap;
		public void setFunctionMap(Map<String, FunctionConvertor> functionMap){
		this.functionMap=functionMap;
		}
		public Map<String, FunctionConvertor> getFunctionMap(){
		return functionMap;
		}
		
			protected  GroupFunctionRegister groupFunc;
			public void setGroupFunc(GroupFunctionRegister groupFunc){
			this.groupFunc=groupFunc;
			}
			public GroupFunctionRegister getGroupFunc(){
			return groupFunc;
			}
		protected void mismatch(IntStream input, int ttype, BitSet follow)
				throws RecognitionException {
			throw new MismatchedTokenException(ttype, input);
		}
		public boolean mismatchIsMissingToken(IntStream arg0, BitSet follow) {
			if ( follow==null ) {
			throw new IllegalArgumentException("can't know what's wrong...");
			}
			throw new IllegalArgumentException("LT(1)=="+((TokenStream)input).LT(1)+" is consistent with what follows; inserting...viable tokens="+follow.toString(getTokenNames())+"LT(1)="+((TokenStream)input).LT(1));
		}

		public boolean mismatchIsUnwantedToken(IntStream input, int ttype) {
	    		throw new IllegalArgumentException(""+ttype);
	    	}
	// Alter code generation so catch-clauses get replace with
	// this action.



	public static class beg_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "beg"
	// MySQLParser.g:110:1: beg : start_rule ;
	public final MySQLParserParser.beg_return beg() throws RecognitionException {
		MySQLParserParser.beg_return retval = new MySQLParserParser.beg_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope start_rule1 =null;


		try {
			// MySQLParser.g:110:5: ( start_rule )
			// MySQLParser.g:111:1: start_rule
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_start_rule_in_beg246);
			start_rule1=start_rule();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, start_rule1.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "beg"


	public static class start_rule_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "start_rule"
	// MySQLParser.g:114:1: start_rule : ( select_command | update_command | insert_command | delete_command );
	public final MySQLParserParser.start_rule_return start_rule() throws RecognitionException {
		MySQLParserParser.start_rule_return retval = new MySQLParserParser.start_rule_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope select_command2 =null;
		ParserRuleReturnScope update_command3 =null;
		ParserRuleReturnScope insert_command4 =null;
		ParserRuleReturnScope delete_command5 =null;


		try {
			// MySQLParser.g:115:2: ( select_command | update_command | insert_command | delete_command )
			int alt1=4;
			switch ( input.LA(1) ) {
			case 110:
				{
				alt1=1;
				}
				break;
			case 113:
				{
				alt1=2;
				}
				break;
			case 95:
				{
				alt1=3;
				}
				break;
			case 84:
				{
				alt1=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// MySQLParser.g:115:3: select_command
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_select_command_in_start_rule258);
					select_command2=select_command();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, select_command2.getTree());

					}
					break;
				case 2 :
					// MySQLParser.g:116:3: update_command
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_update_command_in_start_rule262);
					update_command3=update_command();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, update_command3.getTree());

					}
					break;
				case 3 :
					// MySQLParser.g:117:3: insert_command
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insert_command_in_start_rule266);
					insert_command4=insert_command();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, insert_command4.getTree());

					}
					break;
				case 4 :
					// MySQLParser.g:118:3: delete_command
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_delete_command_in_start_rule270);
					delete_command5=delete_command();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, delete_command5.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "start_rule"


	public static class setclause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "setclause"
	// MySQLParser.g:122:1: setclause : 'SET' updateColumnSpecs -> ^( SET updateColumnSpecs ) ;
	public final MySQLParserParser.setclause_return setclause() throws RecognitionException {
		MySQLParserParser.setclause_return retval = new MySQLParserParser.setclause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal6=null;
		ParserRuleReturnScope updateColumnSpecs7 =null;

		CommonTree string_literal6_tree=null;
		RewriteRuleTokenStream stream_111=new RewriteRuleTokenStream(adaptor,"token 111");
		RewriteRuleSubtreeStream stream_updateColumnSpecs=new RewriteRuleSubtreeStream(adaptor,"rule updateColumnSpecs");

		try {
			// MySQLParser.g:123:2: ( 'SET' updateColumnSpecs -> ^( SET updateColumnSpecs ) )
			// MySQLParser.g:123:3: 'SET' updateColumnSpecs
			{
			string_literal6=(Token)match(input,111,FOLLOW_111_in_setclause282); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_111.add(string_literal6);

			pushFollow(FOLLOW_updateColumnSpecs_in_setclause284);
			updateColumnSpecs7=updateColumnSpecs();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_updateColumnSpecs.add(updateColumnSpecs7.getTree());
			// AST REWRITE
			// elements: updateColumnSpecs
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 123:26: -> ^( SET updateColumnSpecs )
			{
				// MySQLParser.g:123:28: ^( SET updateColumnSpecs )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SET, "SET"), root_1);
				adaptor.addChild(root_1, stream_updateColumnSpecs.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "setclause"


	public static class updateColumnSpecs_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "updateColumnSpecs"
	// MySQLParser.g:126:1: updateColumnSpecs : updateColumnSpec ( COMMA updateColumnSpec )* -> ( ^( SET_ELE updateColumnSpec ) )+ ;
	public final MySQLParserParser.updateColumnSpecs_return updateColumnSpecs() throws RecognitionException {
		MySQLParserParser.updateColumnSpecs_return retval = new MySQLParserParser.updateColumnSpecs_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA9=null;
		ParserRuleReturnScope updateColumnSpec8 =null;
		ParserRuleReturnScope updateColumnSpec10 =null;

		CommonTree COMMA9_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_updateColumnSpec=new RewriteRuleSubtreeStream(adaptor,"rule updateColumnSpec");

		try {
			// MySQLParser.g:127:2: ( updateColumnSpec ( COMMA updateColumnSpec )* -> ( ^( SET_ELE updateColumnSpec ) )+ )
			// MySQLParser.g:127:3: updateColumnSpec ( COMMA updateColumnSpec )*
			{
			pushFollow(FOLLOW_updateColumnSpec_in_updateColumnSpecs301);
			updateColumnSpec8=updateColumnSpec();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_updateColumnSpec.add(updateColumnSpec8.getTree());
			// MySQLParser.g:127:20: ( COMMA updateColumnSpec )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==COMMA) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// MySQLParser.g:127:21: COMMA updateColumnSpec
					{
					COMMA9=(Token)match(input,COMMA,FOLLOW_COMMA_in_updateColumnSpecs304); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA9);

					pushFollow(FOLLOW_updateColumnSpec_in_updateColumnSpecs306);
					updateColumnSpec10=updateColumnSpec();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_updateColumnSpec.add(updateColumnSpec10.getTree());
					}
					break;

				default :
					break loop2;
				}
			}

			// AST REWRITE
			// elements: updateColumnSpec
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 127:45: -> ( ^( SET_ELE updateColumnSpec ) )+
			{
				if ( !(stream_updateColumnSpec.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_updateColumnSpec.hasNext() ) {
					// MySQLParser.g:127:47: ^( SET_ELE updateColumnSpec )
					{
					CommonTree root_1 = (CommonTree)adaptor.nil();
					root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SET_ELE, "SET_ELE"), root_1);
					adaptor.addChild(root_1, stream_updateColumnSpec.nextTree());
					adaptor.addChild(root_0, root_1);
					}

				}
				stream_updateColumnSpec.reset();

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "updateColumnSpecs"


	public static class updateColumnSpec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "updateColumnSpec"
	// MySQLParser.g:129:1: updateColumnSpec : columnNameInUpdate EQ ^ expr ;
	public final MySQLParserParser.updateColumnSpec_return updateColumnSpec() throws RecognitionException {
		MySQLParserParser.updateColumnSpec_return retval = new MySQLParserParser.updateColumnSpec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EQ12=null;
		ParserRuleReturnScope columnNameInUpdate11 =null;
		ParserRuleReturnScope expr13 =null;

		CommonTree EQ12_tree=null;

		try {
			// MySQLParser.g:130:2: ( columnNameInUpdate EQ ^ expr )
			// MySQLParser.g:130:3: columnNameInUpdate EQ ^ expr
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_columnNameInUpdate_in_updateColumnSpec324);
			columnNameInUpdate11=columnNameInUpdate();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, columnNameInUpdate11.getTree());

			EQ12=(Token)match(input,EQ,FOLLOW_EQ_in_updateColumnSpec326); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EQ12_tree = (CommonTree)adaptor.create(EQ12);
			root_0 = (CommonTree)adaptor.becomeRoot(EQ12_tree, root_0);
			}

			pushFollow(FOLLOW_expr_in_updateColumnSpec329);
			expr13=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expr13.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "updateColumnSpec"


	public static class insert_command_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insert_command"
	// MySQLParser.g:132:1: insert_command : 'INSERT' ( 'IGNORE' )? 'INTO' selected_table ( LPAREN column_specs RPAREN )? ( 'VALUES' LPAREN values RPAREN ) -> ^( INSERT selected_table column_specs values ) ;
	public final MySQLParserParser.insert_command_return insert_command() throws RecognitionException {
		MySQLParserParser.insert_command_return retval = new MySQLParserParser.insert_command_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal14=null;
		Token string_literal15=null;
		Token string_literal16=null;
		Token LPAREN18=null;
		Token RPAREN20=null;
		Token string_literal21=null;
		Token LPAREN22=null;
		Token RPAREN24=null;
		ParserRuleReturnScope selected_table17 =null;
		ParserRuleReturnScope column_specs19 =null;
		ParserRuleReturnScope values23 =null;

		CommonTree string_literal14_tree=null;
		CommonTree string_literal15_tree=null;
		CommonTree string_literal16_tree=null;
		CommonTree LPAREN18_tree=null;
		CommonTree RPAREN20_tree=null;
		CommonTree string_literal21_tree=null;
		CommonTree LPAREN22_tree=null;
		CommonTree RPAREN24_tree=null;
		RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
		RewriteRuleTokenStream stream_114=new RewriteRuleTokenStream(adaptor,"token 114");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_selected_table=new RewriteRuleSubtreeStream(adaptor,"rule selected_table");
		RewriteRuleSubtreeStream stream_values=new RewriteRuleSubtreeStream(adaptor,"rule values");
		RewriteRuleSubtreeStream stream_column_specs=new RewriteRuleSubtreeStream(adaptor,"rule column_specs");

		try {
			// MySQLParser.g:133:2: ( 'INSERT' ( 'IGNORE' )? 'INTO' selected_table ( LPAREN column_specs RPAREN )? ( 'VALUES' LPAREN values RPAREN ) -> ^( INSERT selected_table column_specs values ) )
			// MySQLParser.g:133:4: 'INSERT' ( 'IGNORE' )? 'INTO' selected_table ( LPAREN column_specs RPAREN )? ( 'VALUES' LPAREN values RPAREN )
			{
			string_literal14=(Token)match(input,95,FOLLOW_95_in_insert_command339); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_95.add(string_literal14);

			// MySQLParser.g:133:13: ( 'IGNORE' )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==90) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// MySQLParser.g:133:14: 'IGNORE'
					{
					string_literal15=(Token)match(input,90,FOLLOW_90_in_insert_command342); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_90.add(string_literal15);

					}
					break;

			}

			string_literal16=(Token)match(input,96,FOLLOW_96_in_insert_command346); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_96.add(string_literal16);

			pushFollow(FOLLOW_selected_table_in_insert_command348);
			selected_table17=selected_table();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_selected_table.add(selected_table17.getTree());
			// MySQLParser.g:134:3: ( LPAREN column_specs RPAREN )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==LPAREN) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// MySQLParser.g:134:5: LPAREN column_specs RPAREN
					{
					LPAREN18=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_insert_command354); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN18);

					pushFollow(FOLLOW_column_specs_in_insert_command356);
					column_specs19=column_specs();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_column_specs.add(column_specs19.getTree());
					RPAREN20=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_insert_command359); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN20);

					}
					break;

			}

			// MySQLParser.g:135:3: ( 'VALUES' LPAREN values RPAREN )
			// MySQLParser.g:135:4: 'VALUES' LPAREN values RPAREN
			{
			string_literal21=(Token)match(input,114,FOLLOW_114_in_insert_command367); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_114.add(string_literal21);

			LPAREN22=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_insert_command369); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN22);

			pushFollow(FOLLOW_values_in_insert_command371);
			values23=values();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_values.add(values23.getTree());
			RPAREN24=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_insert_command373); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN24);

			}

			// AST REWRITE
			// elements: values, selected_table, column_specs
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 136:4: -> ^( INSERT selected_table column_specs values )
			{
				// MySQLParser.g:136:6: ^( INSERT selected_table column_specs values )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(INSERT, "INSERT"), root_1);
				adaptor.addChild(root_1, stream_selected_table.nextTree());
				adaptor.addChild(root_1, stream_column_specs.nextTree());
				adaptor.addChild(root_1, stream_values.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insert_command"


	public static class orderByClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "orderByClause"
	// MySQLParser.g:140:1: orderByClause : 'ORDER' 'BY' columnNamesAfterWhere -> ^( ORDERBY columnNamesAfterWhere ) ;
	public final MySQLParserParser.orderByClause_return orderByClause() throws RecognitionException {
		MySQLParserParser.orderByClause_return retval = new MySQLParserParser.orderByClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal25=null;
		Token string_literal26=null;
		ParserRuleReturnScope columnNamesAfterWhere27 =null;

		CommonTree string_literal25_tree=null;
		CommonTree string_literal26_tree=null;
		RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleSubtreeStream stream_columnNamesAfterWhere=new RewriteRuleSubtreeStream(adaptor,"rule columnNamesAfterWhere");

		try {
			// MySQLParser.g:141:2: ( 'ORDER' 'BY' columnNamesAfterWhere -> ^( ORDERBY columnNamesAfterWhere ) )
			// MySQLParser.g:141:3: 'ORDER' 'BY' columnNamesAfterWhere
			{
			string_literal25=(Token)match(input,107,FOLLOW_107_in_orderByClause402); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_107.add(string_literal25);

			string_literal26=(Token)match(input,81,FOLLOW_81_in_orderByClause404); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_81.add(string_literal26);

			pushFollow(FOLLOW_columnNamesAfterWhere_in_orderByClause406);
			columnNamesAfterWhere27=columnNamesAfterWhere();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_columnNamesAfterWhere.add(columnNamesAfterWhere27.getTree());
			// AST REWRITE
			// elements: columnNamesAfterWhere
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 141:37: -> ^( ORDERBY columnNamesAfterWhere )
			{
				// MySQLParser.g:141:39: ^( ORDERBY columnNamesAfterWhere )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ORDERBY, "ORDERBY"), root_1);
				adaptor.addChild(root_1, stream_columnNamesAfterWhere.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "orderByClause"


	public static class columnNamesAfterWhere_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnNamesAfterWhere"
	// MySQLParser.g:145:1: columnNamesAfterWhere : columnNameAfterWhere ( COMMA ! columnNameAfterWhere )* ;
	public final MySQLParserParser.columnNamesAfterWhere_return columnNamesAfterWhere() throws RecognitionException {
		MySQLParserParser.columnNamesAfterWhere_return retval = new MySQLParserParser.columnNamesAfterWhere_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA29=null;
		ParserRuleReturnScope columnNameAfterWhere28 =null;
		ParserRuleReturnScope columnNameAfterWhere30 =null;

		CommonTree COMMA29_tree=null;

		try {
			// MySQLParser.g:146:5: ( columnNameAfterWhere ( COMMA ! columnNameAfterWhere )* )
			// MySQLParser.g:146:6: columnNameAfterWhere ( COMMA ! columnNameAfterWhere )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere426);
			columnNameAfterWhere28=columnNameAfterWhere();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, columnNameAfterWhere28.getTree());

			// MySQLParser.g:146:27: ( COMMA ! columnNameAfterWhere )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==COMMA) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// MySQLParser.g:146:28: COMMA ! columnNameAfterWhere
					{
					COMMA29=(Token)match(input,COMMA,FOLLOW_COMMA_in_columnNamesAfterWhere429); if (state.failed) return retval;
					pushFollow(FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere432);
					columnNameAfterWhere30=columnNameAfterWhere();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, columnNameAfterWhere30.getTree());

					}
					break;

				default :
					break loop5;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnNamesAfterWhere"


	public static class selectClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectClause"
	// MySQLParser.g:148:1: selectClause : 'SELECT' ( distinct )? ( LPAREN )? select_list ( RPAREN )? -> ^( SELECT ( distinct )? select_list ) ;
	public final MySQLParserParser.selectClause_return selectClause() throws RecognitionException {
		MySQLParserParser.selectClause_return retval = new MySQLParserParser.selectClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal31=null;
		Token LPAREN33=null;
		Token RPAREN35=null;
		ParserRuleReturnScope distinct32 =null;
		ParserRuleReturnScope select_list34 =null;

		CommonTree string_literal31_tree=null;
		CommonTree LPAREN33_tree=null;
		CommonTree RPAREN35_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_select_list=new RewriteRuleSubtreeStream(adaptor,"rule select_list");
		RewriteRuleSubtreeStream stream_distinct=new RewriteRuleSubtreeStream(adaptor,"rule distinct");

		try {
			// MySQLParser.g:149:5: ( 'SELECT' ( distinct )? ( LPAREN )? select_list ( RPAREN )? -> ^( SELECT ( distinct )? select_list ) )
			// MySQLParser.g:149:6: 'SELECT' ( distinct )? ( LPAREN )? select_list ( RPAREN )?
			{
			string_literal31=(Token)match(input,110,FOLLOW_110_in_selectClause450); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_110.add(string_literal31);

			// MySQLParser.g:149:15: ( distinct )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==85) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// MySQLParser.g:149:15: distinct
					{
					pushFollow(FOLLOW_distinct_in_selectClause452);
					distinct32=distinct();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_distinct.add(distinct32.getTree());
					}
					break;

			}

			// MySQLParser.g:149:25: ( LPAREN )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==LPAREN) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// MySQLParser.g:149:25: LPAREN
					{
					LPAREN33=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_selectClause455); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN33);

					}
					break;

			}

			pushFollow(FOLLOW_select_list_in_selectClause458);
			select_list34=select_list();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_select_list.add(select_list34.getTree());
			// MySQLParser.g:149:45: ( RPAREN )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==RPAREN) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// MySQLParser.g:149:45: RPAREN
					{
					RPAREN35=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_selectClause460); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN35);

					}
					break;

			}

			// AST REWRITE
			// elements: select_list, distinct
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 149:53: -> ^( SELECT ( distinct )? select_list )
			{
				// MySQLParser.g:149:55: ^( SELECT ( distinct )? select_list )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SELECT, "SELECT"), root_1);
				// MySQLParser.g:149:64: ( distinct )?
				if ( stream_distinct.hasNext() ) {
					adaptor.addChild(root_1, stream_distinct.nextTree());
				}
				stream_distinct.reset();

				adaptor.addChild(root_1, stream_select_list.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectClause"


	public static class whereClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "whereClause"
	// MySQLParser.g:151:1: whereClause : 'WHERE' sqlCondition -> ^( WHERE sqlCondition ) ;
	public final MySQLParserParser.whereClause_return whereClause() throws RecognitionException {
		MySQLParserParser.whereClause_return retval = new MySQLParserParser.whereClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal36=null;
		ParserRuleReturnScope sqlCondition37 =null;

		CommonTree string_literal36_tree=null;
		RewriteRuleTokenStream stream_115=new RewriteRuleTokenStream(adaptor,"token 115");
		RewriteRuleSubtreeStream stream_sqlCondition=new RewriteRuleSubtreeStream(adaptor,"rule sqlCondition");

		try {
			// MySQLParser.g:152:2: ( 'WHERE' sqlCondition -> ^( WHERE sqlCondition ) )
			// MySQLParser.g:152:3: 'WHERE' sqlCondition
			{
			string_literal36=(Token)match(input,115,FOLLOW_115_in_whereClause485); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_115.add(string_literal36);

			pushFollow(FOLLOW_sqlCondition_in_whereClause487);
			sqlCondition37=sqlCondition();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_sqlCondition.add(sqlCondition37.getTree());
			// AST REWRITE
			// elements: sqlCondition
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 152:23: -> ^( WHERE sqlCondition )
			{
				// MySQLParser.g:152:25: ^( WHERE sqlCondition )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(WHERE, "WHERE"), root_1);
				adaptor.addChild(root_1, stream_sqlCondition.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "whereClause"


	public static class sqlCondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "sqlCondition"
	// MySQLParser.g:155:1: sqlCondition : ( 'NOT' condition_or -> ^( CONDITION_OR_NOT condition_or ) | condition_or -> ^( CONDITION_OR condition_or ) );
	public final MySQLParserParser.sqlCondition_return sqlCondition() throws RecognitionException {
		MySQLParserParser.sqlCondition_return retval = new MySQLParserParser.sqlCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal38=null;
		ParserRuleReturnScope condition_or39 =null;
		ParserRuleReturnScope condition_or40 =null;

		CommonTree string_literal38_tree=null;
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleSubtreeStream stream_condition_or=new RewriteRuleSubtreeStream(adaptor,"rule condition_or");

		try {
			// MySQLParser.g:156:2: ( 'NOT' condition_or -> ^( CONDITION_OR_NOT condition_or ) | condition_or -> ^( CONDITION_OR condition_or ) )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==103) ) {
				alt9=1;
			}
			else if ( (LA9_0==ASTERISK||LA9_0==DOUBLEQUOTED_STRING||LA9_0==ID||LA9_0==LPAREN||LA9_0==MINUS||LA9_0==N||LA9_0==NUMBER||LA9_0==PLUS||LA9_0==QUOTED_STRING||LA9_0==77||LA9_0==86||LA9_0==104||LA9_0==109||LA9_0==112) ) {
				alt9=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// MySQLParser.g:156:3: 'NOT' condition_or
					{
					string_literal38=(Token)match(input,103,FOLLOW_103_in_sqlCondition503); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_103.add(string_literal38);

					pushFollow(FOLLOW_condition_or_in_sqlCondition505);
					condition_or39=condition_or();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_condition_or.add(condition_or39.getTree());
					// AST REWRITE
					// elements: condition_or
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 156:21: -> ^( CONDITION_OR_NOT condition_or )
					{
						// MySQLParser.g:156:23: ^( CONDITION_OR_NOT condition_or )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CONDITION_OR_NOT, "CONDITION_OR_NOT"), root_1);
						adaptor.addChild(root_1, stream_condition_or.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// MySQLParser.g:157:3: condition_or
					{
					pushFollow(FOLLOW_condition_or_in_sqlCondition516);
					condition_or40=condition_or();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_condition_or.add(condition_or40.getTree());
					// AST REWRITE
					// elements: condition_or
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 157:16: -> ^( CONDITION_OR condition_or )
					{
						// MySQLParser.g:157:18: ^( CONDITION_OR condition_or )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CONDITION_OR, "CONDITION_OR"), root_1);
						adaptor.addChild(root_1, stream_condition_or.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sqlCondition"


	public static class condition_or_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "condition_or"
	// MySQLParser.g:161:1: condition_or : condition_and ( 'OR' ^ condition_and )* ;
	public final MySQLParserParser.condition_or_return condition_or() throws RecognitionException {
		MySQLParserParser.condition_or_return retval = new MySQLParserParser.condition_or_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal42=null;
		ParserRuleReturnScope condition_and41 =null;
		ParserRuleReturnScope condition_and43 =null;

		CommonTree string_literal42_tree=null;

		try {
			// MySQLParser.g:162:2: ( condition_and ( 'OR' ^ condition_and )* )
			// MySQLParser.g:162:3: condition_and ( 'OR' ^ condition_and )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_condition_and_in_condition_or534);
			condition_and41=condition_and();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_and41.getTree());

			// MySQLParser.g:162:17: ( 'OR' ^ condition_and )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==106) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// MySQLParser.g:162:19: 'OR' ^ condition_and
					{
					string_literal42=(Token)match(input,106,FOLLOW_106_in_condition_or538); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal42_tree = (CommonTree)adaptor.create(string_literal42);
					root_0 = (CommonTree)adaptor.becomeRoot(string_literal42_tree, root_0);
					}

					pushFollow(FOLLOW_condition_and_in_condition_or541);
					condition_and43=condition_and();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_and43.getTree());

					}
					break;

				default :
					break loop10;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "condition_or"


	public static class condition_and_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "condition_and"
	// MySQLParser.g:165:1: condition_and : condition_PAREN ( 'AND' ^ condition_PAREN )* ;
	public final MySQLParserParser.condition_and_return condition_and() throws RecognitionException {
		MySQLParserParser.condition_and_return retval = new MySQLParserParser.condition_and_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal45=null;
		ParserRuleReturnScope condition_PAREN44 =null;
		ParserRuleReturnScope condition_PAREN46 =null;

		CommonTree string_literal45_tree=null;

		try {
			// MySQLParser.g:166:2: ( condition_PAREN ( 'AND' ^ condition_PAREN )* )
			// MySQLParser.g:166:3: condition_PAREN ( 'AND' ^ condition_PAREN )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_condition_PAREN_in_condition_and554);
			condition_PAREN44=condition_PAREN();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_PAREN44.getTree());

			// MySQLParser.g:166:19: ( 'AND' ^ condition_PAREN )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==78) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// MySQLParser.g:166:21: 'AND' ^ condition_PAREN
					{
					string_literal45=(Token)match(input,78,FOLLOW_78_in_condition_and558); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal45_tree = (CommonTree)adaptor.create(string_literal45);
					root_0 = (CommonTree)adaptor.becomeRoot(string_literal45_tree, root_0);
					}

					pushFollow(FOLLOW_condition_PAREN_in_condition_and561);
					condition_PAREN46=condition_PAREN();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_PAREN46.getTree());

					}
					break;

				default :
					break loop11;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "condition_and"


	public static class condition_PAREN_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "condition_PAREN"
	// MySQLParser.g:168:1: condition_PAREN : ( ( LPAREN condition_or RPAREN )=> LPAREN condition_or RPAREN -> ^( PRIORITY condition_or ) | condition_expr );
	public final MySQLParserParser.condition_PAREN_return condition_PAREN() throws RecognitionException {
		MySQLParserParser.condition_PAREN_return retval = new MySQLParserParser.condition_PAREN_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LPAREN47=null;
		Token RPAREN49=null;
		ParserRuleReturnScope condition_or48 =null;
		ParserRuleReturnScope condition_expr50 =null;

		CommonTree LPAREN47_tree=null;
		CommonTree RPAREN49_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_condition_or=new RewriteRuleSubtreeStream(adaptor,"rule condition_or");

		try {
			// MySQLParser.g:169:2: ( ( LPAREN condition_or RPAREN )=> LPAREN condition_or RPAREN -> ^( PRIORITY condition_or ) | condition_expr )
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==LPAREN) ) {
				int LA12_1 = input.LA(2);
				if ( (synpred1_MySQLParser()) ) {
					alt12=1;
				}
				else if ( (true) ) {
					alt12=2;
				}

			}
			else if ( (LA12_0==ASTERISK||LA12_0==DOUBLEQUOTED_STRING||LA12_0==ID||LA12_0==MINUS||LA12_0==N||LA12_0==NUMBER||LA12_0==PLUS||LA12_0==QUOTED_STRING||LA12_0==77||LA12_0==86||LA12_0==104||LA12_0==109||LA12_0==112) ) {
				alt12=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// MySQLParser.g:169:3: ( LPAREN condition_or RPAREN )=> LPAREN condition_or RPAREN
					{
					LPAREN47=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_condition_PAREN581); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN47);

					pushFollow(FOLLOW_condition_or_in_condition_PAREN583);
					condition_or48=condition_or();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_condition_or.add(condition_or48.getTree());
					RPAREN49=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_condition_PAREN585); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN49);

					// AST REWRITE
					// elements: condition_or
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 169:59: -> ^( PRIORITY condition_or )
					{
						// MySQLParser.g:169:61: ^( PRIORITY condition_or )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PRIORITY, "PRIORITY"), root_1);
						adaptor.addChild(root_1, stream_condition_or.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// MySQLParser.g:170:3: condition_expr
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_condition_expr_in_condition_PAREN595);
					condition_expr50=condition_expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_expr50.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "condition_PAREN"


	public static class condition_expr_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "condition_expr"
	// MySQLParser.g:172:1: condition_expr : condition_left ^ ( comparisonCondition ^| inCondition ^| isCondition ^| likeCondition ^| betweenCondition ^) ;
	public final MySQLParserParser.condition_expr_return condition_expr() throws RecognitionException {
		MySQLParserParser.condition_expr_return retval = new MySQLParserParser.condition_expr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope condition_left51 =null;
		ParserRuleReturnScope comparisonCondition52 =null;
		ParserRuleReturnScope inCondition53 =null;
		ParserRuleReturnScope isCondition54 =null;
		ParserRuleReturnScope likeCondition55 =null;
		ParserRuleReturnScope betweenCondition56 =null;


		try {
			// MySQLParser.g:173:2: ( condition_left ^ ( comparisonCondition ^| inCondition ^| isCondition ^| likeCondition ^| betweenCondition ^) )
			// MySQLParser.g:173:4: condition_left ^ ( comparisonCondition ^| inCondition ^| isCondition ^| likeCondition ^| betweenCondition ^)
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_condition_left_in_condition_expr605);
			condition_left51=condition_left();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(condition_left51.getTree(), root_0);
			// MySQLParser.g:174:2: ( comparisonCondition ^| inCondition ^| isCondition ^| likeCondition ^| betweenCondition ^)
			int alt13=5;
			switch ( input.LA(1) ) {
			case EQ:
			case GEQ:
			case GTH:
			case LEQ:
			case LTH:
			case NOT_EQ:
				{
				alt13=1;
				}
				break;
			case 103:
				{
				switch ( input.LA(2) ) {
				case 100:
					{
					alt13=4;
					}
					break;
				case 80:
					{
					alt13=5;
					}
					break;
				case 91:
					{
					alt13=2;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 13, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 91:
				{
				alt13=2;
				}
				break;
			case 97:
				{
				alt13=3;
				}
				break;
			case 100:
				{
				alt13=4;
				}
				break;
			case 80:
				{
				alt13=5;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}
			switch (alt13) {
				case 1 :
					// MySQLParser.g:174:3: comparisonCondition ^
					{
					pushFollow(FOLLOW_comparisonCondition_in_condition_expr610);
					comparisonCondition52=comparisonCondition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(comparisonCondition52.getTree(), root_0);
					}
					break;
				case 2 :
					// MySQLParser.g:175:4: inCondition ^
					{
					pushFollow(FOLLOW_inCondition_in_condition_expr616);
					inCondition53=inCondition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(inCondition53.getTree(), root_0);
					}
					break;
				case 3 :
					// MySQLParser.g:176:4: isCondition ^
					{
					pushFollow(FOLLOW_isCondition_in_condition_expr622);
					isCondition54=isCondition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(isCondition54.getTree(), root_0);
					}
					break;
				case 4 :
					// MySQLParser.g:177:4: likeCondition ^
					{
					pushFollow(FOLLOW_likeCondition_in_condition_expr628);
					likeCondition55=likeCondition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(likeCondition55.getTree(), root_0);
					}
					break;
				case 5 :
					// MySQLParser.g:178:4: betweenCondition ^
					{
					pushFollow(FOLLOW_betweenCondition_in_condition_expr634);
					betweenCondition56=betweenCondition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(betweenCondition56.getTree(), root_0);
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "condition_expr"


	public static class condition_left_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "condition_left"
	// MySQLParser.g:181:1: condition_left : expr -> ^( CONDITION_LEFT expr ) ;
	public final MySQLParserParser.condition_left_return condition_left() throws RecognitionException {
		MySQLParserParser.condition_left_return retval = new MySQLParserParser.condition_left_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expr57 =null;

		RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");

		try {
			// MySQLParser.g:182:2: ( expr -> ^( CONDITION_LEFT expr ) )
			// MySQLParser.g:182:3: expr
			{
			pushFollow(FOLLOW_expr_in_condition_left648);
			expr57=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expr.add(expr57.getTree());
			// AST REWRITE
			// elements: expr
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 182:7: -> ^( CONDITION_LEFT expr )
			{
				// MySQLParser.g:182:9: ^( CONDITION_LEFT expr )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CONDITION_LEFT, "CONDITION_LEFT"), root_1);
				adaptor.addChild(root_1, stream_expr.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "condition_left"


	public static class betweenCondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "betweenCondition"
	// MySQLParser.g:184:1: betweenCondition : ( 'NOT' 'BETWEEN' between_and -> ^( NOT_BETWEEN between_and ) | 'BETWEEN' between_and -> ^( BETWEEN between_and ) );
	public final MySQLParserParser.betweenCondition_return betweenCondition() throws RecognitionException {
		MySQLParserParser.betweenCondition_return retval = new MySQLParserParser.betweenCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal58=null;
		Token string_literal59=null;
		Token string_literal61=null;
		ParserRuleReturnScope between_and60 =null;
		ParserRuleReturnScope between_and62 =null;

		CommonTree string_literal58_tree=null;
		CommonTree string_literal59_tree=null;
		CommonTree string_literal61_tree=null;
		RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleSubtreeStream stream_between_and=new RewriteRuleSubtreeStream(adaptor,"rule between_and");

		try {
			// MySQLParser.g:185:2: ( 'NOT' 'BETWEEN' between_and -> ^( NOT_BETWEEN between_and ) | 'BETWEEN' between_and -> ^( BETWEEN between_and ) )
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==103) ) {
				alt14=1;
			}
			else if ( (LA14_0==80) ) {
				alt14=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}

			switch (alt14) {
				case 1 :
					// MySQLParser.g:185:4: 'NOT' 'BETWEEN' between_and
					{
					string_literal58=(Token)match(input,103,FOLLOW_103_in_betweenCondition664); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_103.add(string_literal58);

					string_literal59=(Token)match(input,80,FOLLOW_80_in_betweenCondition666); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_80.add(string_literal59);

					pushFollow(FOLLOW_between_and_in_betweenCondition668);
					between_and60=between_and();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_between_and.add(between_and60.getTree());
					// AST REWRITE
					// elements: between_and
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 185:31: -> ^( NOT_BETWEEN between_and )
					{
						// MySQLParser.g:185:33: ^( NOT_BETWEEN between_and )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NOT_BETWEEN, "NOT_BETWEEN"), root_1);
						adaptor.addChild(root_1, stream_between_and.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// MySQLParser.g:186:4: 'BETWEEN' between_and
					{
					string_literal61=(Token)match(input,80,FOLLOW_80_in_betweenCondition679); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_80.add(string_literal61);

					pushFollow(FOLLOW_between_and_in_betweenCondition681);
					between_and62=between_and();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_between_and.add(between_and62.getTree());
					// AST REWRITE
					// elements: between_and
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 186:25: -> ^( BETWEEN between_and )
					{
						// MySQLParser.g:186:27: ^( BETWEEN between_and )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BETWEEN, "BETWEEN"), root_1);
						adaptor.addChild(root_1, stream_between_and.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "betweenCondition"


	public static class between_and_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "between_and"
	// MySQLParser.g:189:1: between_and : a= between_and_expression 'AND' b= between_and_expression -> ^( $a $b) ;
	public final MySQLParserParser.between_and_return between_and() throws RecognitionException {
		MySQLParserParser.between_and_return retval = new MySQLParserParser.between_and_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal63=null;
		ParserRuleReturnScope a =null;
		ParserRuleReturnScope b =null;

		CommonTree string_literal63_tree=null;
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleSubtreeStream stream_between_and_expression=new RewriteRuleSubtreeStream(adaptor,"rule between_and_expression");

		try {
			// MySQLParser.g:190:2: (a= between_and_expression 'AND' b= between_and_expression -> ^( $a $b) )
			// MySQLParser.g:190:3: a= between_and_expression 'AND' b= between_and_expression
			{
			pushFollow(FOLLOW_between_and_expression_in_between_and700);
			a=between_and_expression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_between_and_expression.add(a.getTree());
			string_literal63=(Token)match(input,78,FOLLOW_78_in_between_and702); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_78.add(string_literal63);

			pushFollow(FOLLOW_between_and_expression_in_between_and706);
			b=between_and_expression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_between_and_expression.add(b.getTree());
			// AST REWRITE
			// elements: a, b
			// token labels: 
			// rule labels: retval, b, a
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.getTree():null);
			RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 190:58: -> ^( $a $b)
			{
				// MySQLParser.g:190:60: ^( $a $b)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_a.nextNode(), root_1);
				adaptor.addChild(root_1, stream_b.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "between_and"


	public static class between_and_expression_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "between_and_expression"
	// MySQLParser.g:193:1: between_and_expression : ( quoted_string | expr_add );
	public final MySQLParserParser.between_and_expression_return between_and_expression() throws RecognitionException {
		MySQLParserParser.between_and_expression_return retval = new MySQLParserParser.between_and_expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope quoted_string64 =null;
		ParserRuleReturnScope expr_add65 =null;


		try {
			// MySQLParser.g:194:2: ( quoted_string | expr_add )
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==DOUBLEQUOTED_STRING||LA15_0==QUOTED_STRING) ) {
				alt15=1;
			}
			else if ( (LA15_0==ASTERISK||LA15_0==ID||LA15_0==LPAREN||LA15_0==MINUS||LA15_0==N||LA15_0==NUMBER||LA15_0==PLUS||LA15_0==77||LA15_0==86||LA15_0==104||LA15_0==109||LA15_0==112) ) {
				alt15=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}

			switch (alt15) {
				case 1 :
					// MySQLParser.g:194:4: quoted_string
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_quoted_string_in_between_and_expression726);
					quoted_string64=quoted_string();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, quoted_string64.getTree());

					}
					break;
				case 2 :
					// MySQLParser.g:195:4: expr_add
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_expr_add_in_between_and_expression731);
					expr_add65=expr_add();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_add65.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "between_and_expression"


	public static class isCondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "isCondition"
	// MySQLParser.g:198:1: isCondition : ( 'IS' 'NOT' condition_is_valobject -> ^( ISNOT condition_is_valobject ) | 'IS' condition_is_valobject -> ^( IS condition_is_valobject ) );
	public final MySQLParserParser.isCondition_return isCondition() throws RecognitionException {
		MySQLParserParser.isCondition_return retval = new MySQLParserParser.isCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal66=null;
		Token string_literal67=null;
		Token string_literal69=null;
		ParserRuleReturnScope condition_is_valobject68 =null;
		ParserRuleReturnScope condition_is_valobject70 =null;

		CommonTree string_literal66_tree=null;
		CommonTree string_literal67_tree=null;
		CommonTree string_literal69_tree=null;
		RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleSubtreeStream stream_condition_is_valobject=new RewriteRuleSubtreeStream(adaptor,"rule condition_is_valobject");

		try {
			// MySQLParser.g:199:2: ( 'IS' 'NOT' condition_is_valobject -> ^( ISNOT condition_is_valobject ) | 'IS' condition_is_valobject -> ^( IS condition_is_valobject ) )
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==97) ) {
				int LA16_1 = input.LA(2);
				if ( (LA16_1==103) ) {
					alt16=1;
				}
				else if ( (LA16_1==93||LA16_1==102||LA16_1==104) ) {
					alt16=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 16, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// MySQLParser.g:199:4: 'IS' 'NOT' condition_is_valobject
					{
					string_literal66=(Token)match(input,97,FOLLOW_97_in_isCondition743); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_97.add(string_literal66);

					string_literal67=(Token)match(input,103,FOLLOW_103_in_isCondition745); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_103.add(string_literal67);

					pushFollow(FOLLOW_condition_is_valobject_in_isCondition747);
					condition_is_valobject68=condition_is_valobject();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_condition_is_valobject.add(condition_is_valobject68.getTree());
					// AST REWRITE
					// elements: condition_is_valobject
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 199:37: -> ^( ISNOT condition_is_valobject )
					{
						// MySQLParser.g:199:39: ^( ISNOT condition_is_valobject )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ISNOT, "ISNOT"), root_1);
						adaptor.addChild(root_1, stream_condition_is_valobject.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// MySQLParser.g:200:3: 'IS' condition_is_valobject
					{
					string_literal69=(Token)match(input,97,FOLLOW_97_in_isCondition757); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_97.add(string_literal69);

					pushFollow(FOLLOW_condition_is_valobject_in_isCondition759);
					condition_is_valobject70=condition_is_valobject();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_condition_is_valobject.add(condition_is_valobject70.getTree());
					// AST REWRITE
					// elements: condition_is_valobject
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 200:30: -> ^( IS condition_is_valobject )
					{
						// MySQLParser.g:200:32: ^( IS condition_is_valobject )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(IS, "IS"), root_1);
						adaptor.addChild(root_1, stream_condition_is_valobject.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "isCondition"


	public static class condition_is_valobject_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "condition_is_valobject"
	// MySQLParser.g:204:1: condition_is_valobject : ( 'NAN' -> NAN | 'INFINITE' -> INFINITE | 'NULL' -> NULL );
	public final MySQLParserParser.condition_is_valobject_return condition_is_valobject() throws RecognitionException {
		MySQLParserParser.condition_is_valobject_return retval = new MySQLParserParser.condition_is_valobject_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal71=null;
		Token string_literal72=null;
		Token string_literal73=null;

		CommonTree string_literal71_tree=null;
		CommonTree string_literal72_tree=null;
		CommonTree string_literal73_tree=null;
		RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
		RewriteRuleTokenStream stream_104=new RewriteRuleTokenStream(adaptor,"token 104");
		RewriteRuleTokenStream stream_102=new RewriteRuleTokenStream(adaptor,"token 102");

		try {
			// MySQLParser.g:205:2: ( 'NAN' -> NAN | 'INFINITE' -> INFINITE | 'NULL' -> NULL )
			int alt17=3;
			switch ( input.LA(1) ) {
			case 102:
				{
				alt17=1;
				}
				break;
			case 93:
				{
				alt17=2;
				}
				break;
			case 104:
				{
				alt17=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// MySQLParser.g:205:4: 'NAN'
					{
					string_literal71=(Token)match(input,102,FOLLOW_102_in_condition_is_valobject777); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_102.add(string_literal71);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 205:10: -> NAN
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(NAN, "NAN"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// MySQLParser.g:206:4: 'INFINITE'
					{
					string_literal72=(Token)match(input,93,FOLLOW_93_in_condition_is_valobject785); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_93.add(string_literal72);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 206:15: -> INFINITE
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INFINITE, "INFINITE"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// MySQLParser.g:207:4: 'NULL'
					{
					string_literal73=(Token)match(input,104,FOLLOW_104_in_condition_is_valobject793); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_104.add(string_literal73);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 207:11: -> NULL
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(NULL, "NULL"));
					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "condition_is_valobject"


	public static class inCondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "inCondition"
	// MySQLParser.g:210:1: inCondition : (not= 'NOT' )? 'IN' ( subquery | ( LPAREN inCondition_expr_adds RPAREN ) ) -> ^( IN ( $not)? ( subquery )? ( inCondition_expr_adds )? ) ;
	public final MySQLParserParser.inCondition_return inCondition() throws RecognitionException {
		MySQLParserParser.inCondition_return retval = new MySQLParserParser.inCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token not=null;
		Token string_literal74=null;
		Token LPAREN76=null;
		Token RPAREN78=null;
		ParserRuleReturnScope subquery75 =null;
		ParserRuleReturnScope inCondition_expr_adds77 =null;

		CommonTree not_tree=null;
		CommonTree string_literal74_tree=null;
		CommonTree LPAREN76_tree=null;
		CommonTree RPAREN78_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_inCondition_expr_adds=new RewriteRuleSubtreeStream(adaptor,"rule inCondition_expr_adds");
		RewriteRuleSubtreeStream stream_subquery=new RewriteRuleSubtreeStream(adaptor,"rule subquery");

		try {
			// MySQLParser.g:211:2: ( (not= 'NOT' )? 'IN' ( subquery | ( LPAREN inCondition_expr_adds RPAREN ) ) -> ^( IN ( $not)? ( subquery )? ( inCondition_expr_adds )? ) )
			// MySQLParser.g:211:3: (not= 'NOT' )? 'IN' ( subquery | ( LPAREN inCondition_expr_adds RPAREN ) )
			{
			// MySQLParser.g:211:3: (not= 'NOT' )?
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==103) ) {
				alt18=1;
			}
			switch (alt18) {
				case 1 :
					// MySQLParser.g:211:4: not= 'NOT'
					{
					not=(Token)match(input,103,FOLLOW_103_in_inCondition809); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_103.add(not);

					}
					break;

			}

			string_literal74=(Token)match(input,91,FOLLOW_91_in_inCondition813); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_91.add(string_literal74);

			// MySQLParser.g:211:21: ( subquery | ( LPAREN inCondition_expr_adds RPAREN ) )
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==LPAREN) ) {
				int LA19_1 = input.LA(2);
				if ( (LA19_1==110) ) {
					alt19=1;
				}
				else if ( (LA19_1==ASTERISK||LA19_1==DOUBLEQUOTED_STRING||LA19_1==ID||LA19_1==LPAREN||LA19_1==MINUS||LA19_1==N||LA19_1==NUMBER||LA19_1==PLUS||LA19_1==QUOTED_STRING||LA19_1==77||LA19_1==86||LA19_1==104||LA19_1==109||LA19_1==112) ) {
					alt19=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}

			switch (alt19) {
				case 1 :
					// MySQLParser.g:211:22: subquery
					{
					pushFollow(FOLLOW_subquery_in_inCondition816);
					subquery75=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_subquery.add(subquery75.getTree());
					}
					break;
				case 2 :
					// MySQLParser.g:212:3: ( LPAREN inCondition_expr_adds RPAREN )
					{
					// MySQLParser.g:212:3: ( LPAREN inCondition_expr_adds RPAREN )
					// MySQLParser.g:212:5: LPAREN inCondition_expr_adds RPAREN
					{
					LPAREN76=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_inCondition822); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN76);

					pushFollow(FOLLOW_inCondition_expr_adds_in_inCondition824);
					inCondition_expr_adds77=inCondition_expr_adds();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_inCondition_expr_adds.add(inCondition_expr_adds77.getTree());
					RPAREN78=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_inCondition826); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN78);

					}

					}
					break;

			}

			// AST REWRITE
			// elements: inCondition_expr_adds, not, subquery
			// token labels: not
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleTokenStream stream_not=new RewriteRuleTokenStream(adaptor,"token not",not);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 212:42: -> ^( IN ( $not)? ( subquery )? ( inCondition_expr_adds )? )
			{
				// MySQLParser.g:212:44: ^( IN ( $not)? ( subquery )? ( inCondition_expr_adds )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(IN, "IN"), root_1);
				// MySQLParser.g:212:50: ( $not)?
				if ( stream_not.hasNext() ) {
					adaptor.addChild(root_1, stream_not.nextNode());
				}
				stream_not.reset();

				// MySQLParser.g:212:55: ( subquery )?
				if ( stream_subquery.hasNext() ) {
					adaptor.addChild(root_1, stream_subquery.nextTree());
				}
				stream_subquery.reset();

				// MySQLParser.g:212:65: ( inCondition_expr_adds )?
				if ( stream_inCondition_expr_adds.hasNext() ) {
					adaptor.addChild(root_1, stream_inCondition_expr_adds.nextTree());
				}
				stream_inCondition_expr_adds.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "inCondition"


	public static class likeCondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "likeCondition"
	// MySQLParser.g:216:1: likeCondition : ( 'NOT' 'LIKE' value -> ^( NOT_LIKE value ) | 'LIKE' value -> ^( LIKE value ) );
	public final MySQLParserParser.likeCondition_return likeCondition() throws RecognitionException {
		MySQLParserParser.likeCondition_return retval = new MySQLParserParser.likeCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal79=null;
		Token string_literal80=null;
		Token string_literal82=null;
		ParserRuleReturnScope value81 =null;
		ParserRuleReturnScope value83 =null;

		CommonTree string_literal79_tree=null;
		CommonTree string_literal80_tree=null;
		CommonTree string_literal82_tree=null;
		RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
		RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
		RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");

		try {
			// MySQLParser.g:217:2: ( 'NOT' 'LIKE' value -> ^( NOT_LIKE value ) | 'LIKE' value -> ^( LIKE value ) )
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==103) ) {
				alt20=1;
			}
			else if ( (LA20_0==100) ) {
				alt20=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}

			switch (alt20) {
				case 1 :
					// MySQLParser.g:217:3: 'NOT' 'LIKE' value
					{
					string_literal79=(Token)match(input,103,FOLLOW_103_in_likeCondition853); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_103.add(string_literal79);

					string_literal80=(Token)match(input,100,FOLLOW_100_in_likeCondition854); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_100.add(string_literal80);

					pushFollow(FOLLOW_value_in_likeCondition857);
					value81=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_value.add(value81.getTree());
					// AST REWRITE
					// elements: value
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 217:21: -> ^( NOT_LIKE value )
					{
						// MySQLParser.g:217:23: ^( NOT_LIKE value )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NOT_LIKE, "NOT_LIKE"), root_1);
						adaptor.addChild(root_1, stream_value.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// MySQLParser.g:218:3: 'LIKE' value
					{
					string_literal82=(Token)match(input,100,FOLLOW_100_in_likeCondition867); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_100.add(string_literal82);

					pushFollow(FOLLOW_value_in_likeCondition869);
					value83=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_value.add(value83.getTree());
					// AST REWRITE
					// elements: value
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 218:15: -> ^( LIKE value )
					{
						// MySQLParser.g:218:17: ^( LIKE value )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LIKE, "LIKE"), root_1);
						adaptor.addChild(root_1, stream_value.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "likeCondition"


	public static class inCondition_expr_adds_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "inCondition_expr_adds"
	// MySQLParser.g:222:1: inCondition_expr_adds : expr_add ( COMMA expr_add )* -> ^( IN_LISTS ( expr_add )+ ) ;
	public final MySQLParserParser.inCondition_expr_adds_return inCondition_expr_adds() throws RecognitionException {
		MySQLParserParser.inCondition_expr_adds_return retval = new MySQLParserParser.inCondition_expr_adds_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA85=null;
		ParserRuleReturnScope expr_add84 =null;
		ParserRuleReturnScope expr_add86 =null;

		CommonTree COMMA85_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_expr_add=new RewriteRuleSubtreeStream(adaptor,"rule expr_add");

		try {
			// MySQLParser.g:222:22: ( expr_add ( COMMA expr_add )* -> ^( IN_LISTS ( expr_add )+ ) )
			// MySQLParser.g:223:2: expr_add ( COMMA expr_add )*
			{
			pushFollow(FOLLOW_expr_add_in_inCondition_expr_adds886);
			expr_add84=expr_add();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expr_add.add(expr_add84.getTree());
			// MySQLParser.g:223:10: ( COMMA expr_add )*
			loop21:
			while (true) {
				int alt21=2;
				int LA21_0 = input.LA(1);
				if ( (LA21_0==COMMA) ) {
					alt21=1;
				}

				switch (alt21) {
				case 1 :
					// MySQLParser.g:223:11: COMMA expr_add
					{
					COMMA85=(Token)match(input,COMMA,FOLLOW_COMMA_in_inCondition_expr_adds888); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA85);

					pushFollow(FOLLOW_expr_add_in_inCondition_expr_adds890);
					expr_add86=expr_add();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr_add.add(expr_add86.getTree());
					}
					break;

				default :
					break loop21;
				}
			}

			// AST REWRITE
			// elements: expr_add
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 223:27: -> ^( IN_LISTS ( expr_add )+ )
			{
				// MySQLParser.g:223:29: ^( IN_LISTS ( expr_add )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(IN_LISTS, "IN_LISTS"), root_1);
				if ( !(stream_expr_add.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_expr_add.hasNext() ) {
					adaptor.addChild(root_1, stream_expr_add.nextTree());
				}
				stream_expr_add.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "inCondition_expr_adds"


	public static class identifiers_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "identifiers"
	// MySQLParser.g:226:1: identifiers : columnNameAfterWhere ( COMMA identifier )* ;
	public final MySQLParserParser.identifiers_return identifiers() throws RecognitionException {
		MySQLParserParser.identifiers_return retval = new MySQLParserParser.identifiers_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA88=null;
		ParserRuleReturnScope columnNameAfterWhere87 =null;
		ParserRuleReturnScope identifier89 =null;

		CommonTree COMMA88_tree=null;

		try {
			// MySQLParser.g:227:2: ( columnNameAfterWhere ( COMMA identifier )* )
			// MySQLParser.g:227:3: columnNameAfterWhere ( COMMA identifier )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_columnNameAfterWhere_in_identifiers910);
			columnNameAfterWhere87=columnNameAfterWhere();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, columnNameAfterWhere87.getTree());

			// MySQLParser.g:227:24: ( COMMA identifier )*
			loop22:
			while (true) {
				int alt22=2;
				int LA22_0 = input.LA(1);
				if ( (LA22_0==COMMA) ) {
					alt22=1;
				}

				switch (alt22) {
				case 1 :
					// MySQLParser.g:227:25: COMMA identifier
					{
					COMMA88=(Token)match(input,COMMA,FOLLOW_COMMA_in_identifiers913); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA88_tree = (CommonTree)adaptor.create(COMMA88);
					adaptor.addChild(root_0, COMMA88_tree);
					}

					pushFollow(FOLLOW_identifier_in_identifiers915);
					identifier89=identifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier89.getTree());

					}
					break;

				default :
					break loop22;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identifiers"


	public static class comparisonCondition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "comparisonCondition"
	// MySQLParser.g:230:1: comparisonCondition : relational_op expr -> ^( relational_op expr ) ;
	public final MySQLParserParser.comparisonCondition_return comparisonCondition() throws RecognitionException {
		MySQLParserParser.comparisonCondition_return retval = new MySQLParserParser.comparisonCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope relational_op90 =null;
		ParserRuleReturnScope expr91 =null;

		RewriteRuleSubtreeStream stream_relational_op=new RewriteRuleSubtreeStream(adaptor,"rule relational_op");
		RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");

		try {
			// MySQLParser.g:231:2: ( relational_op expr -> ^( relational_op expr ) )
			// MySQLParser.g:231:3: relational_op expr
			{
			pushFollow(FOLLOW_relational_op_in_comparisonCondition927);
			relational_op90=relational_op();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_relational_op.add(relational_op90.getTree());
			pushFollow(FOLLOW_expr_in_comparisonCondition929);
			expr91=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expr.add(expr91.getTree());
			// AST REWRITE
			// elements: expr, relational_op
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 231:21: -> ^( relational_op expr )
			{
				// MySQLParser.g:231:23: ^( relational_op expr )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_relational_op.nextNode(), root_1);
				adaptor.addChild(root_1, stream_expr.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comparisonCondition"


	public static class expr_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expr"
	// MySQLParser.g:235:1: expr : ( expr_add | subquery ) ;
	public final MySQLParserParser.expr_return expr() throws RecognitionException {
		MySQLParserParser.expr_return retval = new MySQLParserParser.expr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope expr_add92 =null;
		ParserRuleReturnScope subquery93 =null;


		try {
			// MySQLParser.g:235:6: ( ( expr_add | subquery ) )
			// MySQLParser.g:235:7: ( expr_add | subquery )
			{
			root_0 = (CommonTree)adaptor.nil();


			// MySQLParser.g:235:7: ( expr_add | subquery )
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==ASTERISK||LA23_0==DOUBLEQUOTED_STRING||LA23_0==ID||LA23_0==MINUS||LA23_0==N||LA23_0==NUMBER||LA23_0==PLUS||LA23_0==QUOTED_STRING||LA23_0==77||LA23_0==86||LA23_0==104||LA23_0==109||LA23_0==112) ) {
				alt23=1;
			}
			else if ( (LA23_0==LPAREN) ) {
				int LA23_2 = input.LA(2);
				if ( (LA23_2==ASTERISK||LA23_2==DOUBLEQUOTED_STRING||LA23_2==ID||LA23_2==LPAREN||LA23_2==MINUS||LA23_2==N||LA23_2==NUMBER||LA23_2==PLUS||LA23_2==QUOTED_STRING||LA23_2==77||LA23_2==86||LA23_2==104||LA23_2==109||LA23_2==112) ) {
					alt23=1;
				}
				else if ( (LA23_2==110) ) {
					alt23=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 23, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}

			switch (alt23) {
				case 1 :
					// MySQLParser.g:235:8: expr_add
					{
					pushFollow(FOLLOW_expr_add_in_expr948);
					expr_add92=expr_add();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_add92.getTree());

					}
					break;
				case 2 :
					// MySQLParser.g:236:4: subquery
					{
					pushFollow(FOLLOW_subquery_in_expr953);
					subquery93=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, subquery93.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr"


	public static class subquery_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "subquery"
	// MySQLParser.g:240:1: subquery : LPAREN select_command RPAREN -> ^( SUBQUERY select_command ) ;
	public final MySQLParserParser.subquery_return subquery() throws RecognitionException {
		MySQLParserParser.subquery_return retval = new MySQLParserParser.subquery_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LPAREN94=null;
		Token RPAREN96=null;
		ParserRuleReturnScope select_command95 =null;

		CommonTree LPAREN94_tree=null;
		CommonTree RPAREN96_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_select_command=new RewriteRuleSubtreeStream(adaptor,"rule select_command");

		try {
			// MySQLParser.g:240:9: ( LPAREN select_command RPAREN -> ^( SUBQUERY select_command ) )
			// MySQLParser.g:241:2: LPAREN select_command RPAREN
			{
			LPAREN94=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_subquery969); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN94);

			pushFollow(FOLLOW_select_command_in_subquery971);
			select_command95=select_command();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_select_command.add(select_command95.getTree());
			RPAREN96=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_subquery973); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN96);

			// AST REWRITE
			// elements: select_command
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 241:30: -> ^( SUBQUERY select_command )
			{
				// MySQLParser.g:241:32: ^( SUBQUERY select_command )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SUBQUERY, "SUBQUERY"), root_1);
				adaptor.addChild(root_1, stream_select_command.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subquery"


	public static class expr_add_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expr_add"
	// MySQLParser.g:243:1: expr_add : expr_mul ( ( PLUS ^| MINUS ^| DOUBLEVERTBAR ^) ( expr_mul ) )* ;
	public final MySQLParserParser.expr_add_return expr_add() throws RecognitionException {
		MySQLParserParser.expr_add_return retval = new MySQLParserParser.expr_add_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PLUS98=null;
		Token MINUS99=null;
		Token DOUBLEVERTBAR100=null;
		ParserRuleReturnScope expr_mul97 =null;
		ParserRuleReturnScope expr_mul101 =null;

		CommonTree PLUS98_tree=null;
		CommonTree MINUS99_tree=null;
		CommonTree DOUBLEVERTBAR100_tree=null;

		try {
			// MySQLParser.g:244:2: ( expr_mul ( ( PLUS ^| MINUS ^| DOUBLEVERTBAR ^) ( expr_mul ) )* )
			// MySQLParser.g:244:3: expr_mul ( ( PLUS ^| MINUS ^| DOUBLEVERTBAR ^) ( expr_mul ) )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expr_mul_in_expr_add989);
			expr_mul97=expr_mul();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_mul97.getTree());

			// MySQLParser.g:244:12: ( ( PLUS ^| MINUS ^| DOUBLEVERTBAR ^) ( expr_mul ) )*
			loop25:
			while (true) {
				int alt25=2;
				int LA25_0 = input.LA(1);
				if ( (LA25_0==DOUBLEVERTBAR||LA25_0==MINUS||LA25_0==PLUS) ) {
					alt25=1;
				}

				switch (alt25) {
				case 1 :
					// MySQLParser.g:244:14: ( PLUS ^| MINUS ^| DOUBLEVERTBAR ^) ( expr_mul )
					{
					// MySQLParser.g:244:14: ( PLUS ^| MINUS ^| DOUBLEVERTBAR ^)
					int alt24=3;
					switch ( input.LA(1) ) {
					case PLUS:
						{
						alt24=1;
						}
						break;
					case MINUS:
						{
						alt24=2;
						}
						break;
					case DOUBLEVERTBAR:
						{
						alt24=3;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 24, 0, input);
						throw nvae;
					}
					switch (alt24) {
						case 1 :
							// MySQLParser.g:244:16: PLUS ^
							{
							PLUS98=(Token)match(input,PLUS,FOLLOW_PLUS_in_expr_add995); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							PLUS98_tree = (CommonTree)adaptor.create(PLUS98);
							root_0 = (CommonTree)adaptor.becomeRoot(PLUS98_tree, root_0);
							}

							}
							break;
						case 2 :
							// MySQLParser.g:244:24: MINUS ^
							{
							MINUS99=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr_add1000); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							MINUS99_tree = (CommonTree)adaptor.create(MINUS99);
							root_0 = (CommonTree)adaptor.becomeRoot(MINUS99_tree, root_0);
							}

							}
							break;
						case 3 :
							// MySQLParser.g:244:33: DOUBLEVERTBAR ^
							{
							DOUBLEVERTBAR100=(Token)match(input,DOUBLEVERTBAR,FOLLOW_DOUBLEVERTBAR_in_expr_add1005); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							DOUBLEVERTBAR100_tree = (CommonTree)adaptor.create(DOUBLEVERTBAR100);
							root_0 = (CommonTree)adaptor.becomeRoot(DOUBLEVERTBAR100_tree, root_0);
							}

							}
							break;

					}

					// MySQLParser.g:244:50: ( expr_mul )
					// MySQLParser.g:244:51: expr_mul
					{
					pushFollow(FOLLOW_expr_mul_in_expr_add1011);
					expr_mul101=expr_mul();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_mul101.getTree());

					}

					}
					break;

				default :
					break loop25;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr_add"


	public static class expr_mul_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expr_mul"
	// MySQLParser.g:250:1: expr_mul : expr_sign ( ( ASTERISK ^| DIVIDE ^| MOD ^) expr_sign )* ;
	public final MySQLParserParser.expr_mul_return expr_mul() throws RecognitionException {
		MySQLParserParser.expr_mul_return retval = new MySQLParserParser.expr_mul_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ASTERISK103=null;
		Token DIVIDE104=null;
		Token MOD105=null;
		ParserRuleReturnScope expr_sign102 =null;
		ParserRuleReturnScope expr_sign106 =null;

		CommonTree ASTERISK103_tree=null;
		CommonTree DIVIDE104_tree=null;
		CommonTree MOD105_tree=null;

		try {
			// MySQLParser.g:251:2: ( expr_sign ( ( ASTERISK ^| DIVIDE ^| MOD ^) expr_sign )* )
			// MySQLParser.g:251:4: expr_sign ( ( ASTERISK ^| DIVIDE ^| MOD ^) expr_sign )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expr_sign_in_expr_mul1031);
			expr_sign102=expr_sign();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_sign102.getTree());

			// MySQLParser.g:251:14: ( ( ASTERISK ^| DIVIDE ^| MOD ^) expr_sign )*
			loop27:
			while (true) {
				int alt27=2;
				int LA27_0 = input.LA(1);
				if ( (LA27_0==ASTERISK) ) {
					int LA27_2 = input.LA(2);
					if ( (LA27_2==ASTERISK||LA27_2==DOUBLEQUOTED_STRING||LA27_2==ID||LA27_2==LPAREN||LA27_2==MINUS||LA27_2==N||LA27_2==NUMBER||LA27_2==PLUS||LA27_2==QUOTED_STRING||LA27_2==77||LA27_2==86||LA27_2==104||LA27_2==109||LA27_2==112) ) {
						alt27=1;
					}

				}
				else if ( (LA27_0==DIVIDE||LA27_0==MOD) ) {
					alt27=1;
				}

				switch (alt27) {
				case 1 :
					// MySQLParser.g:251:16: ( ASTERISK ^| DIVIDE ^| MOD ^) expr_sign
					{
					// MySQLParser.g:251:16: ( ASTERISK ^| DIVIDE ^| MOD ^)
					int alt26=3;
					switch ( input.LA(1) ) {
					case ASTERISK:
						{
						alt26=1;
						}
						break;
					case DIVIDE:
						{
						alt26=2;
						}
						break;
					case MOD:
						{
						alt26=3;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 26, 0, input);
						throw nvae;
					}
					switch (alt26) {
						case 1 :
							// MySQLParser.g:251:18: ASTERISK ^
							{
							ASTERISK103=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_expr_mul1037); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							ASTERISK103_tree = (CommonTree)adaptor.create(ASTERISK103);
							root_0 = (CommonTree)adaptor.becomeRoot(ASTERISK103_tree, root_0);
							}

							}
							break;
						case 2 :
							// MySQLParser.g:251:30: DIVIDE ^
							{
							DIVIDE104=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_expr_mul1042); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							DIVIDE104_tree = (CommonTree)adaptor.create(DIVIDE104);
							root_0 = (CommonTree)adaptor.becomeRoot(DIVIDE104_tree, root_0);
							}

							}
							break;
						case 3 :
							// MySQLParser.g:251:40: MOD ^
							{
							MOD105=(Token)match(input,MOD,FOLLOW_MOD_in_expr_mul1047); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							MOD105_tree = (CommonTree)adaptor.create(MOD105);
							root_0 = (CommonTree)adaptor.becomeRoot(MOD105_tree, root_0);
							}

							}
							break;

					}

					pushFollow(FOLLOW_expr_sign_in_expr_mul1052);
					expr_sign106=expr_sign();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_sign106.getTree());

					}
					break;

				default :
					break loop27;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr_mul"


	public static class expr_sign_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expr_sign"
	// MySQLParser.g:253:1: expr_sign : ( PLUS ^| MINUS ^)? expr_pow ;
	public final MySQLParserParser.expr_sign_return expr_sign() throws RecognitionException {
		MySQLParserParser.expr_sign_return retval = new MySQLParserParser.expr_sign_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PLUS107=null;
		Token MINUS108=null;
		ParserRuleReturnScope expr_pow109 =null;

		CommonTree PLUS107_tree=null;
		CommonTree MINUS108_tree=null;

		try {
			// MySQLParser.g:254:2: ( ( PLUS ^| MINUS ^)? expr_pow )
			// MySQLParser.g:254:4: ( PLUS ^| MINUS ^)? expr_pow
			{
			root_0 = (CommonTree)adaptor.nil();


			// MySQLParser.g:254:4: ( PLUS ^| MINUS ^)?
			int alt28=3;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==PLUS) ) {
				alt28=1;
			}
			else if ( (LA28_0==MINUS) ) {
				alt28=2;
			}
			switch (alt28) {
				case 1 :
					// MySQLParser.g:254:6: PLUS ^
					{
					PLUS107=(Token)match(input,PLUS,FOLLOW_PLUS_in_expr_sign1067); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PLUS107_tree = (CommonTree)adaptor.create(PLUS107);
					root_0 = (CommonTree)adaptor.becomeRoot(PLUS107_tree, root_0);
					}

					}
					break;
				case 2 :
					// MySQLParser.g:254:14: MINUS ^
					{
					MINUS108=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr_sign1072); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					MINUS108_tree = (CommonTree)adaptor.create(MINUS108);
					root_0 = (CommonTree)adaptor.becomeRoot(MINUS108_tree, root_0);
					}

					}
					break;

			}

			pushFollow(FOLLOW_expr_pow_in_expr_sign1078);
			expr_pow109=expr_pow();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_pow109.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr_sign"


	public static class expr_pow_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expr_pow"
	// MySQLParser.g:256:1: expr_pow : expr_expr ( EXPONENT ^ expr_expr )* ;
	public final MySQLParserParser.expr_pow_return expr_pow() throws RecognitionException {
		MySQLParserParser.expr_pow_return retval = new MySQLParserParser.expr_pow_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EXPONENT111=null;
		ParserRuleReturnScope expr_expr110 =null;
		ParserRuleReturnScope expr_expr112 =null;

		CommonTree EXPONENT111_tree=null;

		try {
			// MySQLParser.g:257:2: ( expr_expr ( EXPONENT ^ expr_expr )* )
			// MySQLParser.g:257:4: expr_expr ( EXPONENT ^ expr_expr )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expr_expr_in_expr_pow1088);
			expr_expr110=expr_expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_expr110.getTree());

			// MySQLParser.g:257:14: ( EXPONENT ^ expr_expr )*
			loop29:
			while (true) {
				int alt29=2;
				int LA29_0 = input.LA(1);
				if ( (LA29_0==EXPONENT) ) {
					alt29=1;
				}

				switch (alt29) {
				case 1 :
					// MySQLParser.g:257:16: EXPONENT ^ expr_expr
					{
					EXPONENT111=(Token)match(input,EXPONENT,FOLLOW_EXPONENT_in_expr_pow1092); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXPONENT111_tree = (CommonTree)adaptor.create(EXPONENT111);
					root_0 = (CommonTree)adaptor.becomeRoot(EXPONENT111_tree, root_0);
					}

					pushFollow(FOLLOW_expr_expr_in_expr_pow1095);
					expr_expr112=expr_expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_expr112.getTree());

					}
					break;

				default :
					break loop29;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr_pow"


	public static class expr_expr_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expr_expr"
	// MySQLParser.g:260:1: expr_expr : ( value | boolean_literal | 'NULL' | 'ROWNUM' |{...}? ID ( ( LPAREN ( values_func )? RPAREN ) |) -> ^( ID ( values_func )? ) );
	public final MySQLParserParser.expr_expr_return expr_expr() throws RecognitionException {
		MySQLParserParser.expr_expr_return retval = new MySQLParserParser.expr_expr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal115=null;
		Token string_literal116=null;
		Token ID117=null;
		Token LPAREN118=null;
		Token RPAREN120=null;
		ParserRuleReturnScope value113 =null;
		ParserRuleReturnScope boolean_literal114 =null;
		ParserRuleReturnScope values_func119 =null;

		CommonTree string_literal115_tree=null;
		CommonTree string_literal116_tree=null;
		CommonTree ID117_tree=null;
		CommonTree LPAREN118_tree=null;
		CommonTree RPAREN120_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_values_func=new RewriteRuleSubtreeStream(adaptor,"rule values_func");

		try {
			// MySQLParser.g:261:2: ( value | boolean_literal | 'NULL' | 'ROWNUM' |{...}? ID ( ( LPAREN ( values_func )? RPAREN ) |) -> ^( ID ( values_func )? ) )
			int alt32=5;
			switch ( input.LA(1) ) {
			case ASTERISK:
			case DOUBLEQUOTED_STRING:
			case LPAREN:
			case N:
			case NUMBER:
			case QUOTED_STRING:
			case 77:
				{
				alt32=1;
				}
				break;
			case ID:
				{
				int LA32_2 = input.LA(2);
				if ( (!(((functionMap.containsKey(input.LT(1).getText().toUpperCase()))))) ) {
					alt32=1;
				}
				else if ( ((functionMap.containsKey(input.LT(1).getText().toUpperCase()))) ) {
					alt32=5;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 32, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 86:
			case 112:
				{
				alt32=2;
				}
				break;
			case 104:
				{
				alt32=3;
				}
				break;
			case 109:
				{
				alt32=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}
			switch (alt32) {
				case 1 :
					// MySQLParser.g:261:3: value
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_value_in_expr_expr1108);
					value113=value();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, value113.getTree());

					}
					break;
				case 2 :
					// MySQLParser.g:262:3: boolean_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_boolean_literal_in_expr_expr1112);
					boolean_literal114=boolean_literal();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, boolean_literal114.getTree());

					}
					break;
				case 3 :
					// MySQLParser.g:263:3: 'NULL'
					{
					root_0 = (CommonTree)adaptor.nil();


					string_literal115=(Token)match(input,104,FOLLOW_104_in_expr_expr1116); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal115_tree = (CommonTree)adaptor.create(string_literal115);
					adaptor.addChild(root_0, string_literal115_tree);
					}

					}
					break;
				case 4 :
					// MySQLParser.g:264:3: 'ROWNUM'
					{
					root_0 = (CommonTree)adaptor.nil();


					string_literal116=(Token)match(input,109,FOLLOW_109_in_expr_expr1120); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal116_tree = (CommonTree)adaptor.create(string_literal116);
					adaptor.addChild(root_0, string_literal116_tree);
					}

					}
					break;
				case 5 :
					// MySQLParser.g:267:3: {...}? ID ( ( LPAREN ( values_func )? RPAREN ) |)
					{
					if ( !((functionMap.containsKey(input.LT(1).getText().toUpperCase()))) ) {
						if (state.backtracking>0) {state.failed=true; return retval;}
						throw new FailedPredicateException(input, "expr_expr", "functionMap.containsKey(input.LT(1).getText().toUpperCase())");
					}
					ID117=(Token)match(input,ID,FOLLOW_ID_in_expr_expr1128); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ID.add(ID117);

					// MySQLParser.g:267:70: ( ( LPAREN ( values_func )? RPAREN ) |)
					int alt31=2;
					int LA31_0 = input.LA(1);
					if ( (LA31_0==LPAREN) ) {
						alt31=1;
					}
					else if ( (LA31_0==EOF||LA31_0==ASTERISK||LA31_0==COMMA||LA31_0==DIVIDE||(LA31_0 >= DOUBLEVERTBAR && LA31_0 <= EXPONENT)||LA31_0==GEQ||(LA31_0 >= GTH && LA31_0 <= ID)||LA31_0==LEQ||(LA31_0 >= LTH && LA31_0 <= MOD)||LA31_0==NOT_EQ||LA31_0==PLUS||LA31_0==RPAREN||(LA31_0 >= 78 && LA31_0 <= 80)||(LA31_0 >= 87 && LA31_0 <= 91)||LA31_0==94||(LA31_0 >= 97 && LA31_0 <= 101)||LA31_0==103||(LA31_0 >= 106 && LA31_0 <= 108)||LA31_0==115) ) {
						alt31=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 31, 0, input);
						throw nvae;
					}

					switch (alt31) {
						case 1 :
							// MySQLParser.g:267:71: ( LPAREN ( values_func )? RPAREN )
							{
							// MySQLParser.g:267:71: ( LPAREN ( values_func )? RPAREN )
							// MySQLParser.g:267:72: LPAREN ( values_func )? RPAREN
							{
							LPAREN118=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_expr_expr1132); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN118);

							// MySQLParser.g:267:79: ( values_func )?
							int alt30=2;
							int LA30_0 = input.LA(1);
							if ( (LA30_0==ASTERISK||LA30_0==DOUBLEQUOTED_STRING||LA30_0==ID||LA30_0==LPAREN||LA30_0==MINUS||LA30_0==N||LA30_0==NUMBER||LA30_0==PLUS||LA30_0==QUOTED_STRING||LA30_0==77||LA30_0==86||LA30_0==104||LA30_0==109||LA30_0==112) ) {
								alt30=1;
							}
							switch (alt30) {
								case 1 :
									// MySQLParser.g:267:79: values_func
									{
									pushFollow(FOLLOW_values_func_in_expr_expr1134);
									values_func119=values_func();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) stream_values_func.add(values_func119.getTree());
									}
									break;

							}

							RPAREN120=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_expr_expr1137); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN120);

							}

							}
							break;
						case 2 :
							// MySQLParser.g:267:100: 
							{
							}
							break;

					}

					// AST REWRITE
					// elements: values_func, ID
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 267:101: -> ^( ID ( values_func )? )
					{
						// MySQLParser.g:267:103: ^( ID ( values_func )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_ID.nextNode(), root_1);
						// MySQLParser.g:267:108: ( values_func )?
						if ( stream_values_func.hasNext() ) {
							adaptor.addChild(root_1, stream_values_func.nextTree());
						}
						stream_values_func.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr_expr"


	public static class sql_condition_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "sql_condition"
	// MySQLParser.g:272:1: sql_condition : condition_or ;
	public final MySQLParserParser.sql_condition_return sql_condition() throws RecognitionException {
		MySQLParserParser.sql_condition_return retval = new MySQLParserParser.sql_condition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope condition_or121 =null;


		try {
			// MySQLParser.g:273:2: ( condition_or )
			// MySQLParser.g:273:4: condition_or
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_condition_or_in_sql_condition1159);
			condition_or121=condition_or();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_or121.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sql_condition"


	public static class relational_op_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "relational_op"
	// MySQLParser.g:275:1: relational_op : ( EQ | LTH | GTH | NOT_EQ | LEQ | GEQ );
	public final MySQLParserParser.relational_op_return relational_op() throws RecognitionException {
		MySQLParserParser.relational_op_return retval = new MySQLParserParser.relational_op_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set122=null;

		CommonTree set122_tree=null;

		try {
			// MySQLParser.g:276:2: ( EQ | LTH | GTH | NOT_EQ | LEQ | GEQ )
			// MySQLParser.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set122=input.LT(1);
			if ( input.LA(1)==EQ||input.LA(1)==GEQ||input.LA(1)==GTH||input.LA(1)==LEQ||input.LA(1)==LTH||input.LA(1)==NOT_EQ ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set122));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "relational_op"


	public static class fromClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fromClause"
	// MySQLParser.g:279:1: fromClause : 'FROM' ! selected_table ;
	public final MySQLParserParser.fromClause_return fromClause() throws RecognitionException {
		MySQLParserParser.fromClause_return retval = new MySQLParserParser.fromClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal123=null;
		ParserRuleReturnScope selected_table124 =null;

		CommonTree string_literal123_tree=null;

		try {
			// MySQLParser.g:280:2: ( 'FROM' ! selected_table )
			// MySQLParser.g:280:3: 'FROM' ! selected_table
			{
			root_0 = (CommonTree)adaptor.nil();


			string_literal123=(Token)match(input,88,FOLLOW_88_in_fromClause1199); if (state.failed) return retval;
			pushFollow(FOLLOW_selected_table_in_fromClause1202);
			selected_table124=selected_table();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, selected_table124.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fromClause"


	public static class select_list_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "select_list"
	// MySQLParser.g:283:1: select_list : displayed_column ( COMMA displayed_column )* -> ^( SELECT_LIST ( displayed_column )+ ) ;
	public final MySQLParserParser.select_list_return select_list() throws RecognitionException {
		MySQLParserParser.select_list_return retval = new MySQLParserParser.select_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA126=null;
		ParserRuleReturnScope displayed_column125 =null;
		ParserRuleReturnScope displayed_column127 =null;

		CommonTree COMMA126_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_displayed_column=new RewriteRuleSubtreeStream(adaptor,"rule displayed_column");

		try {
			// MySQLParser.g:284:2: ( displayed_column ( COMMA displayed_column )* -> ^( SELECT_LIST ( displayed_column )+ ) )
			// MySQLParser.g:284:4: displayed_column ( COMMA displayed_column )*
			{
			pushFollow(FOLLOW_displayed_column_in_select_list1213);
			displayed_column125=displayed_column();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_displayed_column.add(displayed_column125.getTree());
			// MySQLParser.g:284:21: ( COMMA displayed_column )*
			loop33:
			while (true) {
				int alt33=2;
				int LA33_0 = input.LA(1);
				if ( (LA33_0==COMMA) ) {
					alt33=1;
				}

				switch (alt33) {
				case 1 :
					// MySQLParser.g:284:23: COMMA displayed_column
					{
					COMMA126=(Token)match(input,COMMA,FOLLOW_COMMA_in_select_list1217); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA126);

					pushFollow(FOLLOW_displayed_column_in_select_list1219);
					displayed_column127=displayed_column();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_displayed_column.add(displayed_column127.getTree());
					}
					break;

				default :
					break loop33;
				}
			}

			// AST REWRITE
			// elements: displayed_column
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 284:48: -> ^( SELECT_LIST ( displayed_column )+ )
			{
				// MySQLParser.g:284:50: ^( SELECT_LIST ( displayed_column )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SELECT_LIST, "SELECT_LIST"), root_1);
				if ( !(stream_displayed_column.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_displayed_column.hasNext() ) {
					adaptor.addChild(root_1, stream_displayed_column.nextTree());
				}
				stream_displayed_column.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "select_list"


	public static class displayed_column_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "displayed_column"
	// MySQLParser.g:287:1: displayed_column : ( quoted_string ( alias )? -> ^( quoted_string ( alias )? ) |{...}? concat LPAREN identifiedOrQuotedString ( COMMA identifiedOrQuotedString )* RPAREN ( alias )? -> ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? ) |{...}? count LPAREN ( distinct )? ( LPAREN )? countColumn ( RPAREN )? RPAREN ( alias )? -> ^( count ( distinct )? countColumn ( alias )? ) | expr_add ( alias )? -> ^( EXPR expr_add ( alias )? ) |{...}? ID ( ( LPAREN ( table_alias )? ( column )? RPAREN ) |) ( alias )? -> ^( ID ( table_alias )? ( column )? ( alias )? ) );
	public final MySQLParserParser.displayed_column_return displayed_column() throws RecognitionException {
		MySQLParserParser.displayed_column_return retval = new MySQLParserParser.displayed_column_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LPAREN131=null;
		Token COMMA133=null;
		Token RPAREN135=null;
		Token LPAREN138=null;
		Token LPAREN140=null;
		Token RPAREN142=null;
		Token RPAREN143=null;
		Token ID147=null;
		Token LPAREN148=null;
		Token RPAREN151=null;
		ParserRuleReturnScope quoted_string128 =null;
		ParserRuleReturnScope alias129 =null;
		ParserRuleReturnScope concat130 =null;
		ParserRuleReturnScope identifiedOrQuotedString132 =null;
		ParserRuleReturnScope identifiedOrQuotedString134 =null;
		ParserRuleReturnScope alias136 =null;
		ParserRuleReturnScope count137 =null;
		ParserRuleReturnScope distinct139 =null;
		ParserRuleReturnScope countColumn141 =null;
		ParserRuleReturnScope alias144 =null;
		ParserRuleReturnScope expr_add145 =null;
		ParserRuleReturnScope alias146 =null;
		ParserRuleReturnScope table_alias149 =null;
		ParserRuleReturnScope column150 =null;
		ParserRuleReturnScope alias152 =null;

		CommonTree LPAREN131_tree=null;
		CommonTree COMMA133_tree=null;
		CommonTree RPAREN135_tree=null;
		CommonTree LPAREN138_tree=null;
		CommonTree LPAREN140_tree=null;
		CommonTree RPAREN142_tree=null;
		CommonTree RPAREN143_tree=null;
		CommonTree ID147_tree=null;
		CommonTree LPAREN148_tree=null;
		CommonTree RPAREN151_tree=null;
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleSubtreeStream stream_quoted_string=new RewriteRuleSubtreeStream(adaptor,"rule quoted_string");
		RewriteRuleSubtreeStream stream_count=new RewriteRuleSubtreeStream(adaptor,"rule count");
		RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias");
		RewriteRuleSubtreeStream stream_identifiedOrQuotedString=new RewriteRuleSubtreeStream(adaptor,"rule identifiedOrQuotedString");
		RewriteRuleSubtreeStream stream_column=new RewriteRuleSubtreeStream(adaptor,"rule column");
		RewriteRuleSubtreeStream stream_table_alias=new RewriteRuleSubtreeStream(adaptor,"rule table_alias");
		RewriteRuleSubtreeStream stream_countColumn=new RewriteRuleSubtreeStream(adaptor,"rule countColumn");
		RewriteRuleSubtreeStream stream_concat=new RewriteRuleSubtreeStream(adaptor,"rule concat");
		RewriteRuleSubtreeStream stream_expr_add=new RewriteRuleSubtreeStream(adaptor,"rule expr_add");
		RewriteRuleSubtreeStream stream_distinct=new RewriteRuleSubtreeStream(adaptor,"rule distinct");

		try {
			// MySQLParser.g:288:2: ( quoted_string ( alias )? -> ^( quoted_string ( alias )? ) |{...}? concat LPAREN identifiedOrQuotedString ( COMMA identifiedOrQuotedString )* RPAREN ( alias )? -> ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? ) |{...}? count LPAREN ( distinct )? ( LPAREN )? countColumn ( RPAREN )? RPAREN ( alias )? -> ^( count ( distinct )? countColumn ( alias )? ) | expr_add ( alias )? -> ^( EXPR expr_add ( alias )? ) |{...}? ID ( ( LPAREN ( table_alias )? ( column )? RPAREN ) |) ( alias )? -> ^( ID ( table_alias )? ( column )? ( alias )? ) )
			int alt46=5;
			switch ( input.LA(1) ) {
			case DOUBLEQUOTED_STRING:
			case QUOTED_STRING:
				{
				alt46=1;
				}
				break;
			case 82:
				{
				alt46=2;
				}
				break;
			case 83:
				{
				alt46=3;
				}
				break;
			case ASTERISK:
			case LPAREN:
			case MINUS:
			case N:
			case NUMBER:
			case PLUS:
			case 77:
			case 86:
			case 104:
			case 109:
			case 112:
				{
				alt46=4;
				}
				break;
			case ID:
				{
				int LA46_5 = input.LA(2);
				if ( (!(((groupFunc.containsKey(input.LT(1).getText().toUpperCase()))))) ) {
					alt46=4;
				}
				else if ( ((groupFunc.containsKey(input.LT(1).getText().toUpperCase()))) ) {
					alt46=5;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 46, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 46, 0, input);
				throw nvae;
			}
			switch (alt46) {
				case 1 :
					// MySQLParser.g:289:2: quoted_string ( alias )?
					{
					pushFollow(FOLLOW_quoted_string_in_displayed_column1242);
					quoted_string128=quoted_string();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_quoted_string.add(quoted_string128.getTree());
					// MySQLParser.g:289:16: ( alias )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==ASTERISK||LA34_0==ID||LA34_0==79) ) {
						alt34=1;
					}
					switch (alt34) {
						case 1 :
							// MySQLParser.g:289:16: alias
							{
							pushFollow(FOLLOW_alias_in_displayed_column1244);
							alias129=alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_alias.add(alias129.getTree());
							}
							break;

					}

					// AST REWRITE
					// elements: quoted_string, alias
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 289:22: -> ^( quoted_string ( alias )? )
					{
						// MySQLParser.g:289:24: ^( quoted_string ( alias )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_quoted_string.nextNode(), root_1);
						// MySQLParser.g:289:40: ( alias )?
						if ( stream_alias.hasNext() ) {
							adaptor.addChild(root_1, stream_alias.nextTree());
						}
						stream_alias.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// MySQLParser.g:290:3: {...}? concat LPAREN identifiedOrQuotedString ( COMMA identifiedOrQuotedString )* RPAREN ( alias )?
					{
					if ( !((input.LT(1).getText().toUpperCase().equals("CONCAT"))) ) {
						if (state.backtracking>0) {state.failed=true; return retval;}
						throw new FailedPredicateException(input, "displayed_column", "input.LT(1).getText().toUpperCase().equals(\"CONCAT\")");
					}
					pushFollow(FOLLOW_concat_in_displayed_column1258);
					concat130=concat();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_concat.add(concat130.getTree());
					LPAREN131=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_displayed_column1260); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN131);

					pushFollow(FOLLOW_identifiedOrQuotedString_in_displayed_column1262);
					identifiedOrQuotedString132=identifiedOrQuotedString();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_identifiedOrQuotedString.add(identifiedOrQuotedString132.getTree());
					// MySQLParser.g:290:98: ( COMMA identifiedOrQuotedString )*
					loop35:
					while (true) {
						int alt35=2;
						int LA35_0 = input.LA(1);
						if ( (LA35_0==COMMA) ) {
							alt35=1;
						}

						switch (alt35) {
						case 1 :
							// MySQLParser.g:290:99: COMMA identifiedOrQuotedString
							{
							COMMA133=(Token)match(input,COMMA,FOLLOW_COMMA_in_displayed_column1265); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_COMMA.add(COMMA133);

							pushFollow(FOLLOW_identifiedOrQuotedString_in_displayed_column1267);
							identifiedOrQuotedString134=identifiedOrQuotedString();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_identifiedOrQuotedString.add(identifiedOrQuotedString134.getTree());
							}
							break;

						default :
							break loop35;
						}
					}

					RPAREN135=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_displayed_column1271); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN135);

					// MySQLParser.g:290:139: ( alias )?
					int alt36=2;
					int LA36_0 = input.LA(1);
					if ( (LA36_0==ASTERISK||LA36_0==ID||LA36_0==79) ) {
						alt36=1;
					}
					switch (alt36) {
						case 1 :
							// MySQLParser.g:290:139: alias
							{
							pushFollow(FOLLOW_alias_in_displayed_column1273);
							alias136=alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_alias.add(alias136.getTree());
							}
							break;

					}

					// AST REWRITE
					// elements: alias, concat, identifiedOrQuotedString, identifiedOrQuotedString
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 290:145: -> ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? )
					{
						// MySQLParser.g:290:147: ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_concat.nextNode(), root_1);
						adaptor.addChild(root_1, stream_identifiedOrQuotedString.nextTree());
						// MySQLParser.g:290:181: ( identifiedOrQuotedString )*
						while ( stream_identifiedOrQuotedString.hasNext() ) {
							adaptor.addChild(root_1, stream_identifiedOrQuotedString.nextTree());
						}
						stream_identifiedOrQuotedString.reset();

						// MySQLParser.g:290:209: ( alias )?
						if ( stream_alias.hasNext() ) {
							adaptor.addChild(root_1, stream_alias.nextTree());
						}
						stream_alias.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// MySQLParser.g:291:3: {...}? count LPAREN ( distinct )? ( LPAREN )? countColumn ( RPAREN )? RPAREN ( alias )?
					{
					if ( !((input.LT(1).getText().toUpperCase().equals("COUNT"))) ) {
						if (state.backtracking>0) {state.failed=true; return retval;}
						throw new FailedPredicateException(input, "displayed_column", "input.LT(1).getText().toUpperCase().equals(\"COUNT\")");
					}
					pushFollow(FOLLOW_count_in_displayed_column1294);
					count137=count();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_count.add(count137.getTree());
					LPAREN138=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_displayed_column1296); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN138);

					// MySQLParser.g:291:71: ( distinct )?
					int alt37=2;
					int LA37_0 = input.LA(1);
					if ( (LA37_0==85) ) {
						alt37=1;
					}
					switch (alt37) {
						case 1 :
							// MySQLParser.g:291:71: distinct
							{
							pushFollow(FOLLOW_distinct_in_displayed_column1298);
							distinct139=distinct();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_distinct.add(distinct139.getTree());
							}
							break;

					}

					// MySQLParser.g:291:81: ( LPAREN )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==LPAREN) ) {
						alt38=1;
					}
					switch (alt38) {
						case 1 :
							// MySQLParser.g:291:81: LPAREN
							{
							LPAREN140=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_displayed_column1301); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN140);

							}
							break;

					}

					pushFollow(FOLLOW_countColumn_in_displayed_column1304);
					countColumn141=countColumn();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_countColumn.add(countColumn141.getTree());
					// MySQLParser.g:291:101: ( RPAREN )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==RPAREN) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==RPAREN) ) {
							alt39=1;
						}
					}
					switch (alt39) {
						case 1 :
							// MySQLParser.g:291:101: RPAREN
							{
							RPAREN142=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_displayed_column1306); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN142);

							}
							break;

					}

					RPAREN143=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_displayed_column1309); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN143);

					// MySQLParser.g:291:116: ( alias )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==ASTERISK||LA40_0==ID||LA40_0==79) ) {
						alt40=1;
					}
					switch (alt40) {
						case 1 :
							// MySQLParser.g:291:116: alias
							{
							pushFollow(FOLLOW_alias_in_displayed_column1311);
							alias144=alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_alias.add(alias144.getTree());
							}
							break;

					}

					// AST REWRITE
					// elements: countColumn, distinct, alias, count
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 291:122: -> ^( count ( distinct )? countColumn ( alias )? )
					{
						// MySQLParser.g:291:124: ^( count ( distinct )? countColumn ( alias )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_count.nextNode(), root_1);
						// MySQLParser.g:291:132: ( distinct )?
						if ( stream_distinct.hasNext() ) {
							adaptor.addChild(root_1, stream_distinct.nextTree());
						}
						stream_distinct.reset();

						adaptor.addChild(root_1, stream_countColumn.nextTree());
						// MySQLParser.g:291:154: ( alias )?
						if ( stream_alias.hasNext() ) {
							adaptor.addChild(root_1, stream_alias.nextTree());
						}
						stream_alias.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// MySQLParser.g:292:3: expr_add ( alias )?
					{
					pushFollow(FOLLOW_expr_add_in_displayed_column1328);
					expr_add145=expr_add();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr_add.add(expr_add145.getTree());
					// MySQLParser.g:292:12: ( alias )?
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==ASTERISK||LA41_0==ID||LA41_0==79) ) {
						alt41=1;
					}
					switch (alt41) {
						case 1 :
							// MySQLParser.g:292:12: alias
							{
							pushFollow(FOLLOW_alias_in_displayed_column1330);
							alias146=alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_alias.add(alias146.getTree());
							}
							break;

					}

					// AST REWRITE
					// elements: expr_add, alias
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 292:18: -> ^( EXPR expr_add ( alias )? )
					{
						// MySQLParser.g:292:20: ^( EXPR expr_add ( alias )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EXPR, "EXPR"), root_1);
						adaptor.addChild(root_1, stream_expr_add.nextTree());
						// MySQLParser.g:292:36: ( alias )?
						if ( stream_alias.hasNext() ) {
							adaptor.addChild(root_1, stream_alias.nextTree());
						}
						stream_alias.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 5 :
					// MySQLParser.g:294:3: {...}? ID ( ( LPAREN ( table_alias )? ( column )? RPAREN ) |) ( alias )?
					{
					if ( !((groupFunc.containsKey(input.LT(1).getText().toUpperCase()))) ) {
						if (state.backtracking>0) {state.failed=true; return retval;}
						throw new FailedPredicateException(input, "displayed_column", "groupFunc.containsKey(input.LT(1).getText().toUpperCase())");
					}
					ID147=(Token)match(input,ID,FOLLOW_ID_in_displayed_column1348); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ID.add(ID147);

					// MySQLParser.g:294:68: ( ( LPAREN ( table_alias )? ( column )? RPAREN ) |)
					int alt44=2;
					int LA44_0 = input.LA(1);
					if ( (LA44_0==LPAREN) ) {
						alt44=1;
					}
					else if ( (LA44_0==EOF||LA44_0==ASTERISK||LA44_0==COMMA||LA44_0==ID||LA44_0==RPAREN||LA44_0==79||(LA44_0 >= 87 && LA44_0 <= 90)||LA44_0==94||(LA44_0 >= 98 && LA44_0 <= 99)||LA44_0==101||(LA44_0 >= 107 && LA44_0 <= 108)||LA44_0==115) ) {
						alt44=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 44, 0, input);
						throw nvae;
					}

					switch (alt44) {
						case 1 :
							// MySQLParser.g:294:69: ( LPAREN ( table_alias )? ( column )? RPAREN )
							{
							// MySQLParser.g:294:69: ( LPAREN ( table_alias )? ( column )? RPAREN )
							// MySQLParser.g:294:70: LPAREN ( table_alias )? ( column )? RPAREN
							{
							LPAREN148=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_displayed_column1352); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN148);

							// MySQLParser.g:294:77: ( table_alias )?
							int alt42=2;
							int LA42_0 = input.LA(1);
							if ( (LA42_0==ASTERISK) ) {
								int LA42_1 = input.LA(2);
								if ( (LA42_1==DOT) ) {
									alt42=1;
								}
							}
							else if ( (LA42_0==ID) ) {
								int LA42_2 = input.LA(2);
								if ( (LA42_2==DOT) ) {
									alt42=1;
								}
							}
							switch (alt42) {
								case 1 :
									// MySQLParser.g:294:77: table_alias
									{
									pushFollow(FOLLOW_table_alias_in_displayed_column1354);
									table_alias149=table_alias();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) stream_table_alias.add(table_alias149.getTree());
									}
									break;

							}

							// MySQLParser.g:294:90: ( column )?
							int alt43=2;
							int LA43_0 = input.LA(1);
							if ( (LA43_0==ASTERISK||LA43_0==ID) ) {
								alt43=1;
							}
							switch (alt43) {
								case 1 :
									// MySQLParser.g:294:90: column
									{
									pushFollow(FOLLOW_column_in_displayed_column1357);
									column150=column();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) stream_column.add(column150.getTree());
									}
									break;

							}

							RPAREN151=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_displayed_column1360); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN151);

							}

							}
							break;
						case 2 :
							// MySQLParser.g:294:106: 
							{
							}
							break;

					}

					// MySQLParser.g:294:108: ( alias )?
					int alt45=2;
					int LA45_0 = input.LA(1);
					if ( (LA45_0==ASTERISK||LA45_0==ID||LA45_0==79) ) {
						alt45=1;
					}
					switch (alt45) {
						case 1 :
							// MySQLParser.g:294:108: alias
							{
							pushFollow(FOLLOW_alias_in_displayed_column1365);
							alias152=alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_alias.add(alias152.getTree());
							}
							break;

					}

					// AST REWRITE
					// elements: alias, table_alias, ID, column
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 294:114: -> ^( ID ( table_alias )? ( column )? ( alias )? )
					{
						// MySQLParser.g:294:116: ^( ID ( table_alias )? ( column )? ( alias )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_ID.nextNode(), root_1);
						// MySQLParser.g:294:121: ( table_alias )?
						if ( stream_table_alias.hasNext() ) {
							adaptor.addChild(root_1, stream_table_alias.nextTree());
						}
						stream_table_alias.reset();

						// MySQLParser.g:294:134: ( column )?
						if ( stream_column.hasNext() ) {
							adaptor.addChild(root_1, stream_column.nextTree());
						}
						stream_column.reset();

						// MySQLParser.g:294:142: ( alias )?
						if ( stream_alias.hasNext() ) {
							adaptor.addChild(root_1, stream_alias.nextTree());
						}
						stream_alias.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "displayed_column"


	public static class columnNameAfterWhere_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnNameAfterWhere"
	// MySQLParser.g:297:1: columnNameAfterWhere : ( ( table_alias )? identifier -> ^( ASC identifier ( table_alias )? ) | ( table_alias )? identifier ASC -> ^( ASC identifier ( table_alias )? ) | ( table_alias )? identifier DESC -> ^( DESC identifier ( table_alias )? ) );
	public final MySQLParserParser.columnNameAfterWhere_return columnNameAfterWhere() throws RecognitionException {
		MySQLParserParser.columnNameAfterWhere_return retval = new MySQLParserParser.columnNameAfterWhere_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ASC157=null;
		Token DESC160=null;
		ParserRuleReturnScope table_alias153 =null;
		ParserRuleReturnScope identifier154 =null;
		ParserRuleReturnScope table_alias155 =null;
		ParserRuleReturnScope identifier156 =null;
		ParserRuleReturnScope table_alias158 =null;
		ParserRuleReturnScope identifier159 =null;

		CommonTree ASC157_tree=null;
		CommonTree DESC160_tree=null;
		RewriteRuleTokenStream stream_DESC=new RewriteRuleTokenStream(adaptor,"token DESC");
		RewriteRuleTokenStream stream_ASC=new RewriteRuleTokenStream(adaptor,"token ASC");
		RewriteRuleSubtreeStream stream_table_alias=new RewriteRuleSubtreeStream(adaptor,"rule table_alias");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// MySQLParser.g:298:2: ( ( table_alias )? identifier -> ^( ASC identifier ( table_alias )? ) | ( table_alias )? identifier ASC -> ^( ASC identifier ( table_alias )? ) | ( table_alias )? identifier DESC -> ^( DESC identifier ( table_alias )? ) )
			int alt50=3;
			int LA50_0 = input.LA(1);
			if ( (LA50_0==ASTERISK||LA50_0==ID) ) {
				switch ( input.LA(2) ) {
				case DOT:
					{
					int LA50_2 = input.LA(3);
					if ( (LA50_2==ASTERISK||LA50_2==ID) ) {
						switch ( input.LA(4) ) {
						case EOF:
						case COMMA:
						case RPAREN:
						case 87:
						case 90:
						case 101:
							{
							alt50=1;
							}
							break;
						case ASC:
							{
							alt50=2;
							}
							break;
						case DESC:
							{
							alt50=3;
							}
							break;
						default:
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 50, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 50, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case EOF:
				case COMMA:
				case RPAREN:
				case 87:
				case 90:
				case 101:
					{
					alt50=1;
					}
					break;
				case ASC:
					{
					alt50=2;
					}
					break;
				case DESC:
					{
					alt50=3;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 50, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 50, 0, input);
				throw nvae;
			}

			switch (alt50) {
				case 1 :
					// MySQLParser.g:298:3: ( table_alias )? identifier
					{
					// MySQLParser.g:298:3: ( table_alias )?
					int alt47=2;
					int LA47_0 = input.LA(1);
					if ( (LA47_0==ASTERISK||LA47_0==ID) ) {
						int LA47_1 = input.LA(2);
						if ( (LA47_1==DOT) ) {
							alt47=1;
						}
					}
					switch (alt47) {
						case 1 :
							// MySQLParser.g:298:3: table_alias
							{
							pushFollow(FOLLOW_table_alias_in_columnNameAfterWhere1390);
							table_alias153=table_alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_table_alias.add(table_alias153.getTree());
							}
							break;

					}

					pushFollow(FOLLOW_identifier_in_columnNameAfterWhere1393);
					identifier154=identifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_identifier.add(identifier154.getTree());
					// AST REWRITE
					// elements: identifier, table_alias
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 298:28: -> ^( ASC identifier ( table_alias )? )
					{
						// MySQLParser.g:298:30: ^( ASC identifier ( table_alias )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ASC, "ASC"), root_1);
						adaptor.addChild(root_1, stream_identifier.nextTree());
						// MySQLParser.g:298:47: ( table_alias )?
						if ( stream_table_alias.hasNext() ) {
							adaptor.addChild(root_1, stream_table_alias.nextTree());
						}
						stream_table_alias.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// MySQLParser.g:299:3: ( table_alias )? identifier ASC
					{
					// MySQLParser.g:299:3: ( table_alias )?
					int alt48=2;
					int LA48_0 = input.LA(1);
					if ( (LA48_0==ASTERISK||LA48_0==ID) ) {
						int LA48_1 = input.LA(2);
						if ( (LA48_1==DOT) ) {
							alt48=1;
						}
					}
					switch (alt48) {
						case 1 :
							// MySQLParser.g:299:3: table_alias
							{
							pushFollow(FOLLOW_table_alias_in_columnNameAfterWhere1408);
							table_alias155=table_alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_table_alias.add(table_alias155.getTree());
							}
							break;

					}

					pushFollow(FOLLOW_identifier_in_columnNameAfterWhere1411);
					identifier156=identifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_identifier.add(identifier156.getTree());
					ASC157=(Token)match(input,ASC,FOLLOW_ASC_in_columnNameAfterWhere1414); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ASC.add(ASC157);

					// AST REWRITE
					// elements: table_alias, identifier, ASC
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 299:33: -> ^( ASC identifier ( table_alias )? )
					{
						// MySQLParser.g:299:35: ^( ASC identifier ( table_alias )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_ASC.nextNode(), root_1);
						adaptor.addChild(root_1, stream_identifier.nextTree());
						// MySQLParser.g:299:52: ( table_alias )?
						if ( stream_table_alias.hasNext() ) {
							adaptor.addChild(root_1, stream_table_alias.nextTree());
						}
						stream_table_alias.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// MySQLParser.g:300:3: ( table_alias )? identifier DESC
					{
					// MySQLParser.g:300:3: ( table_alias )?
					int alt49=2;
					int LA49_0 = input.LA(1);
					if ( (LA49_0==ASTERISK||LA49_0==ID) ) {
						int LA49_1 = input.LA(2);
						if ( (LA49_1==DOT) ) {
							alt49=1;
						}
					}
					switch (alt49) {
						case 1 :
							// MySQLParser.g:300:3: table_alias
							{
							pushFollow(FOLLOW_table_alias_in_columnNameAfterWhere1429);
							table_alias158=table_alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_table_alias.add(table_alias158.getTree());
							}
							break;

					}

					pushFollow(FOLLOW_identifier_in_columnNameAfterWhere1432);
					identifier159=identifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_identifier.add(identifier159.getTree());
					DESC160=(Token)match(input,DESC,FOLLOW_DESC_in_columnNameAfterWhere1435); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DESC.add(DESC160);

					// AST REWRITE
					// elements: DESC, table_alias, identifier
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 300:33: -> ^( DESC identifier ( table_alias )? )
					{
						// MySQLParser.g:300:35: ^( DESC identifier ( table_alias )? )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot(stream_DESC.nextNode(), root_1);
						adaptor.addChild(root_1, stream_identifier.nextTree());
						// MySQLParser.g:300:53: ( table_alias )?
						if ( stream_table_alias.hasNext() ) {
							adaptor.addChild(root_1, stream_table_alias.nextTree());
						}
						stream_table_alias.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnNameAfterWhere"


	public static class columnNameInUpdate_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnNameInUpdate"
	// MySQLParser.g:303:1: columnNameInUpdate : ( table_alias )? identifier ;
	public final MySQLParserParser.columnNameInUpdate_return columnNameInUpdate() throws RecognitionException {
		MySQLParserParser.columnNameInUpdate_return retval = new MySQLParserParser.columnNameInUpdate_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope table_alias161 =null;
		ParserRuleReturnScope identifier162 =null;


		try {
			// MySQLParser.g:304:2: ( ( table_alias )? identifier )
			// MySQLParser.g:304:3: ( table_alias )? identifier
			{
			root_0 = (CommonTree)adaptor.nil();


			// MySQLParser.g:304:3: ( table_alias )?
			int alt51=2;
			int LA51_0 = input.LA(1);
			if ( (LA51_0==ASTERISK||LA51_0==ID) ) {
				int LA51_1 = input.LA(2);
				if ( (LA51_1==DOT) ) {
					alt51=1;
				}
			}
			switch (alt51) {
				case 1 :
					// MySQLParser.g:304:3: table_alias
					{
					pushFollow(FOLLOW_table_alias_in_columnNameInUpdate1456);
					table_alias161=table_alias();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, table_alias161.getTree());

					}
					break;

			}

			pushFollow(FOLLOW_identifier_in_columnNameInUpdate1459);
			identifier162=identifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier162.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnNameInUpdate"


	public static class table_alias_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "table_alias"
	// MySQLParser.g:306:1: table_alias : identifier DOT -> ^( COL_TAB identifier ) ;
	public final MySQLParserParser.table_alias_return table_alias() throws RecognitionException {
		MySQLParserParser.table_alias_return retval = new MySQLParserParser.table_alias_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DOT164=null;
		ParserRuleReturnScope identifier163 =null;

		CommonTree DOT164_tree=null;
		RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// MySQLParser.g:307:2: ( identifier DOT -> ^( COL_TAB identifier ) )
			// MySQLParser.g:307:3: identifier DOT
			{
			pushFollow(FOLLOW_identifier_in_table_alias1469);
			identifier163=identifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_identifier.add(identifier163.getTree());
			DOT164=(Token)match(input,DOT,FOLLOW_DOT_in_table_alias1471); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_DOT.add(DOT164);

			// AST REWRITE
			// elements: identifier
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 307:17: -> ^( COL_TAB identifier )
			{
				// MySQLParser.g:307:19: ^( COL_TAB identifier )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(COL_TAB, "COL_TAB"), root_1);
				adaptor.addChild(root_1, stream_identifier.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "table_alias"


	public static class column_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "column"
	// MySQLParser.g:309:1: column : ( ASTERISK | identifier );
	public final MySQLParserParser.column_return column() throws RecognitionException {
		MySQLParserParser.column_return retval = new MySQLParserParser.column_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ASTERISK165=null;
		ParserRuleReturnScope identifier166 =null;

		CommonTree ASTERISK165_tree=null;

		try {
			// MySQLParser.g:310:2: ( ASTERISK | identifier )
			int alt52=2;
			int LA52_0 = input.LA(1);
			if ( (LA52_0==ASTERISK) ) {
				alt52=1;
			}
			else if ( (LA52_0==ID) ) {
				alt52=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 52, 0, input);
				throw nvae;
			}

			switch (alt52) {
				case 1 :
					// MySQLParser.g:310:3: ASTERISK
					{
					root_0 = (CommonTree)adaptor.nil();


					ASTERISK165=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_column1487); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASTERISK165_tree = (CommonTree)adaptor.create(ASTERISK165);
					adaptor.addChild(root_0, ASTERISK165_tree);
					}

					}
					break;
				case 2 :
					// MySQLParser.g:311:3: identifier
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_identifier_in_column1491);
					identifier166=identifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier166.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column"


	public static class values_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "values"
	// MySQLParser.g:313:1: values : expr ( COMMA expr )* -> ^( INSERT_VAL ( expr )* ) ;
	public final MySQLParserParser.values_return values() throws RecognitionException {
		MySQLParserParser.values_return retval = new MySQLParserParser.values_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA168=null;
		ParserRuleReturnScope expr167 =null;
		ParserRuleReturnScope expr169 =null;

		CommonTree COMMA168_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");

		try {
			// MySQLParser.g:314:2: ( expr ( COMMA expr )* -> ^( INSERT_VAL ( expr )* ) )
			// MySQLParser.g:314:3: expr ( COMMA expr )*
			{
			pushFollow(FOLLOW_expr_in_values1500);
			expr167=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expr.add(expr167.getTree());
			// MySQLParser.g:314:8: ( COMMA expr )*
			loop53:
			while (true) {
				int alt53=2;
				int LA53_0 = input.LA(1);
				if ( (LA53_0==COMMA) ) {
					alt53=1;
				}

				switch (alt53) {
				case 1 :
					// MySQLParser.g:314:10: COMMA expr
					{
					COMMA168=(Token)match(input,COMMA,FOLLOW_COMMA_in_values1504); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA168);

					pushFollow(FOLLOW_expr_in_values1506);
					expr169=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_expr.add(expr169.getTree());
					}
					break;

				default :
					break loop53;
				}
			}

			// AST REWRITE
			// elements: expr
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 314:23: -> ^( INSERT_VAL ( expr )* )
			{
				// MySQLParser.g:314:25: ^( INSERT_VAL ( expr )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(INSERT_VAL, "INSERT_VAL"), root_1);
				// MySQLParser.g:314:38: ( expr )*
				while ( stream_expr.hasNext() ) {
					adaptor.addChild(root_1, stream_expr.nextTree());
				}
				stream_expr.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "values"


	public static class values_func_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "values_func"
	// MySQLParser.g:316:1: values_func : expr ( COMMA ! expr )* ;
	public final MySQLParserParser.values_func_return values_func() throws RecognitionException {
		MySQLParserParser.values_func_return retval = new MySQLParserParser.values_func_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA171=null;
		ParserRuleReturnScope expr170 =null;
		ParserRuleReturnScope expr172 =null;

		CommonTree COMMA171_tree=null;

		try {
			// MySQLParser.g:317:2: ( expr ( COMMA ! expr )* )
			// MySQLParser.g:317:3: expr ( COMMA ! expr )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_expr_in_values_func1525);
			expr170=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expr170.getTree());

			// MySQLParser.g:317:8: ( COMMA ! expr )*
			loop54:
			while (true) {
				int alt54=2;
				int LA54_0 = input.LA(1);
				if ( (LA54_0==COMMA) ) {
					alt54=1;
				}

				switch (alt54) {
				case 1 :
					// MySQLParser.g:317:10: COMMA ! expr
					{
					COMMA171=(Token)match(input,COMMA,FOLLOW_COMMA_in_values_func1529); if (state.failed) return retval;
					pushFollow(FOLLOW_expr_in_values_func1532);
					expr172=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr172.getTree());

					}
					break;

				default :
					break loop54;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "values_func"


	public static class value_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "value"
	// MySQLParser.g:319:1: value : ( N | NUMBER | '?' | LPAREN ! expr RPAREN !| quoted_string -> ^( QUTED_STR quoted_string ) | column_spec );
	public final MySQLParserParser.value_return value() throws RecognitionException {
		MySQLParserParser.value_return retval = new MySQLParserParser.value_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token N173=null;
		Token NUMBER174=null;
		Token char_literal175=null;
		Token LPAREN176=null;
		Token RPAREN178=null;
		ParserRuleReturnScope expr177 =null;
		ParserRuleReturnScope quoted_string179 =null;
		ParserRuleReturnScope column_spec180 =null;

		CommonTree N173_tree=null;
		CommonTree NUMBER174_tree=null;
		CommonTree char_literal175_tree=null;
		CommonTree LPAREN176_tree=null;
		CommonTree RPAREN178_tree=null;
		RewriteRuleSubtreeStream stream_quoted_string=new RewriteRuleSubtreeStream(adaptor,"rule quoted_string");

		try {
			// MySQLParser.g:319:7: ( N | NUMBER | '?' | LPAREN ! expr RPAREN !| quoted_string -> ^( QUTED_STR quoted_string ) | column_spec )
			int alt55=6;
			switch ( input.LA(1) ) {
			case N:
				{
				alt55=1;
				}
				break;
			case NUMBER:
				{
				alt55=2;
				}
				break;
			case 77:
				{
				alt55=3;
				}
				break;
			case LPAREN:
				{
				alt55=4;
				}
				break;
			case DOUBLEQUOTED_STRING:
			case QUOTED_STRING:
				{
				alt55=5;
				}
				break;
			case ASTERISK:
			case ID:
				{
				alt55=6;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 55, 0, input);
				throw nvae;
			}
			switch (alt55) {
				case 1 :
					// MySQLParser.g:320:2: N
					{
					root_0 = (CommonTree)adaptor.nil();


					N173=(Token)match(input,N,FOLLOW_N_in_value1546); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					N173_tree = (CommonTree)adaptor.create(N173);
					adaptor.addChild(root_0, N173_tree);
					}

					}
					break;
				case 2 :
					// MySQLParser.g:321:3: NUMBER
					{
					root_0 = (CommonTree)adaptor.nil();


					NUMBER174=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value1550); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER174_tree = (CommonTree)adaptor.create(NUMBER174);
					adaptor.addChild(root_0, NUMBER174_tree);
					}

					}
					break;
				case 3 :
					// MySQLParser.g:322:3: '?'
					{
					root_0 = (CommonTree)adaptor.nil();


					char_literal175=(Token)match(input,77,FOLLOW_77_in_value1554); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal175_tree = (CommonTree)adaptor.create(char_literal175);
					adaptor.addChild(root_0, char_literal175_tree);
					}

					}
					break;
				case 4 :
					// MySQLParser.g:323:3: LPAREN ! expr RPAREN !
					{
					root_0 = (CommonTree)adaptor.nil();


					LPAREN176=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_value1558); if (state.failed) return retval;
					pushFollow(FOLLOW_expr_in_value1561);
					expr177=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr177.getTree());

					RPAREN178=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_value1563); if (state.failed) return retval;
					}
					break;
				case 5 :
					// MySQLParser.g:324:3: quoted_string
					{
					pushFollow(FOLLOW_quoted_string_in_value1568);
					quoted_string179=quoted_string();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_quoted_string.add(quoted_string179.getTree());
					// AST REWRITE
					// elements: quoted_string
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 324:17: -> ^( QUTED_STR quoted_string )
					{
						// MySQLParser.g:324:19: ^( QUTED_STR quoted_string )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(QUTED_STR, "QUTED_STR"), root_1);
						adaptor.addChild(root_1, stream_quoted_string.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// MySQLParser.g:325:3: column_spec
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_column_spec_in_value1579);
					column_spec180=column_spec();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, column_spec180.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "value"


	public static class value_simple_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "value_simple"
	// MySQLParser.g:327:1: value_simple : ( N | NUMBER | '?' | LPAREN expr RPAREN | quoted_string -> ^( QUTED_STR quoted_string ) | column_spec );
	public final MySQLParserParser.value_simple_return value_simple() throws RecognitionException {
		MySQLParserParser.value_simple_return retval = new MySQLParserParser.value_simple_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token N181=null;
		Token NUMBER182=null;
		Token char_literal183=null;
		Token LPAREN184=null;
		Token RPAREN186=null;
		ParserRuleReturnScope expr185 =null;
		ParserRuleReturnScope quoted_string187 =null;
		ParserRuleReturnScope column_spec188 =null;

		CommonTree N181_tree=null;
		CommonTree NUMBER182_tree=null;
		CommonTree char_literal183_tree=null;
		CommonTree LPAREN184_tree=null;
		CommonTree RPAREN186_tree=null;
		RewriteRuleSubtreeStream stream_quoted_string=new RewriteRuleSubtreeStream(adaptor,"rule quoted_string");

		try {
			// MySQLParser.g:327:14: ( N | NUMBER | '?' | LPAREN expr RPAREN | quoted_string -> ^( QUTED_STR quoted_string ) | column_spec )
			int alt56=6;
			switch ( input.LA(1) ) {
			case N:
				{
				alt56=1;
				}
				break;
			case NUMBER:
				{
				alt56=2;
				}
				break;
			case 77:
				{
				alt56=3;
				}
				break;
			case LPAREN:
				{
				alt56=4;
				}
				break;
			case DOUBLEQUOTED_STRING:
			case QUOTED_STRING:
				{
				alt56=5;
				}
				break;
			case ASTERISK:
			case ID:
				{
				alt56=6;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 56, 0, input);
				throw nvae;
			}
			switch (alt56) {
				case 1 :
					// MySQLParser.g:328:2: N
					{
					root_0 = (CommonTree)adaptor.nil();


					N181=(Token)match(input,N,FOLLOW_N_in_value_simple1589); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					N181_tree = (CommonTree)adaptor.create(N181);
					adaptor.addChild(root_0, N181_tree);
					}

					}
					break;
				case 2 :
					// MySQLParser.g:329:3: NUMBER
					{
					root_0 = (CommonTree)adaptor.nil();


					NUMBER182=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value_simple1593); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER182_tree = (CommonTree)adaptor.create(NUMBER182);
					adaptor.addChild(root_0, NUMBER182_tree);
					}

					}
					break;
				case 3 :
					// MySQLParser.g:330:3: '?'
					{
					root_0 = (CommonTree)adaptor.nil();


					char_literal183=(Token)match(input,77,FOLLOW_77_in_value_simple1597); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal183_tree = (CommonTree)adaptor.create(char_literal183);
					adaptor.addChild(root_0, char_literal183_tree);
					}

					}
					break;
				case 4 :
					// MySQLParser.g:331:3: LPAREN expr RPAREN
					{
					root_0 = (CommonTree)adaptor.nil();


					LPAREN184=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_value_simple1601); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LPAREN184_tree = (CommonTree)adaptor.create(LPAREN184);
					adaptor.addChild(root_0, LPAREN184_tree);
					}

					pushFollow(FOLLOW_expr_in_value_simple1603);
					expr185=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr185.getTree());

					RPAREN186=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_value_simple1605); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RPAREN186_tree = (CommonTree)adaptor.create(RPAREN186);
					adaptor.addChild(root_0, RPAREN186_tree);
					}

					}
					break;
				case 5 :
					// MySQLParser.g:332:3: quoted_string
					{
					pushFollow(FOLLOW_quoted_string_in_value_simple1609);
					quoted_string187=quoted_string();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_quoted_string.add(quoted_string187.getTree());
					// AST REWRITE
					// elements: quoted_string
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 332:17: -> ^( QUTED_STR quoted_string )
					{
						// MySQLParser.g:332:19: ^( QUTED_STR quoted_string )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(QUTED_STR, "QUTED_STR"), root_1);
						adaptor.addChild(root_1, stream_quoted_string.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// MySQLParser.g:333:3: column_spec
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_column_spec_in_value_simple1620);
					column_spec188=column_spec();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, column_spec188.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "value_simple"


	public static class column_specs_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "column_specs"
	// MySQLParser.g:336:1: column_specs : column_spec ( COMMA column_spec )* -> ^( COLUMNS ( column_spec )+ ) ;
	public final MySQLParserParser.column_specs_return column_specs() throws RecognitionException {
		MySQLParserParser.column_specs_return retval = new MySQLParserParser.column_specs_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA190=null;
		ParserRuleReturnScope column_spec189 =null;
		ParserRuleReturnScope column_spec191 =null;

		CommonTree COMMA190_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_column_spec=new RewriteRuleSubtreeStream(adaptor,"rule column_spec");

		try {
			// MySQLParser.g:337:2: ( column_spec ( COMMA column_spec )* -> ^( COLUMNS ( column_spec )+ ) )
			// MySQLParser.g:337:4: column_spec ( COMMA column_spec )*
			{
			pushFollow(FOLLOW_column_spec_in_column_specs1631);
			column_spec189=column_spec();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_column_spec.add(column_spec189.getTree());
			// MySQLParser.g:337:16: ( COMMA column_spec )*
			loop57:
			while (true) {
				int alt57=2;
				int LA57_0 = input.LA(1);
				if ( (LA57_0==COMMA) ) {
					alt57=1;
				}

				switch (alt57) {
				case 1 :
					// MySQLParser.g:337:18: COMMA column_spec
					{
					COMMA190=(Token)match(input,COMMA,FOLLOW_COMMA_in_column_specs1635); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA190);

					pushFollow(FOLLOW_column_spec_in_column_specs1637);
					column_spec191=column_spec();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_column_spec.add(column_spec191.getTree());
					}
					break;

				default :
					break loop57;
				}
			}

			// AST REWRITE
			// elements: column_spec
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 337:38: -> ^( COLUMNS ( column_spec )+ )
			{
				// MySQLParser.g:337:40: ^( COLUMNS ( column_spec )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(COLUMNS, "COLUMNS"), root_1);
				if ( !(stream_column_spec.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_column_spec.hasNext() ) {
					adaptor.addChild(root_1, stream_column_spec.nextTree());
				}
				stream_column_spec.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column_specs"


	public static class selected_table_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selected_table"
	// MySQLParser.g:340:1: selected_table : a_table ( COMMA a_table )* -> ^( TABLENAMES ( a_table )+ ) ;
	public final MySQLParserParser.selected_table_return selected_table() throws RecognitionException {
		MySQLParserParser.selected_table_return retval = new MySQLParserParser.selected_table_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA193=null;
		ParserRuleReturnScope a_table192 =null;
		ParserRuleReturnScope a_table194 =null;

		CommonTree COMMA193_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_a_table=new RewriteRuleSubtreeStream(adaptor,"rule a_table");

		try {
			// MySQLParser.g:341:2: ( a_table ( COMMA a_table )* -> ^( TABLENAMES ( a_table )+ ) )
			// MySQLParser.g:341:3: a_table ( COMMA a_table )*
			{
			pushFollow(FOLLOW_a_table_in_selected_table1658);
			a_table192=a_table();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_a_table.add(a_table192.getTree());
			// MySQLParser.g:341:11: ( COMMA a_table )*
			loop58:
			while (true) {
				int alt58=2;
				int LA58_0 = input.LA(1);
				if ( (LA58_0==COMMA) ) {
					alt58=1;
				}

				switch (alt58) {
				case 1 :
					// MySQLParser.g:341:12: COMMA a_table
					{
					COMMA193=(Token)match(input,COMMA,FOLLOW_COMMA_in_selected_table1661); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA193);

					pushFollow(FOLLOW_a_table_in_selected_table1663);
					a_table194=a_table();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_a_table.add(a_table194.getTree());
					}
					break;

				default :
					break loop58;
				}
			}

			// AST REWRITE
			// elements: a_table
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 341:27: -> ^( TABLENAMES ( a_table )+ )
			{
				// MySQLParser.g:341:29: ^( TABLENAMES ( a_table )+ )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TABLENAMES, "TABLENAMES"), root_1);
				if ( !(stream_a_table.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_a_table.hasNext() ) {
					adaptor.addChild(root_1, stream_a_table.nextTree());
				}
				stream_a_table.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selected_table"


	public static class a_table_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "a_table"
	// MySQLParser.g:343:1: a_table : table_spec ( alias )? -> ^( TABLENAME table_spec ( alias )? ) ;
	public final MySQLParserParser.a_table_return a_table() throws RecognitionException {
		MySQLParserParser.a_table_return retval = new MySQLParserParser.a_table_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope table_spec195 =null;
		ParserRuleReturnScope alias196 =null;

		RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias");
		RewriteRuleSubtreeStream stream_table_spec=new RewriteRuleSubtreeStream(adaptor,"rule table_spec");

		try {
			// MySQLParser.g:344:2: ( table_spec ( alias )? -> ^( TABLENAME table_spec ( alias )? ) )
			// MySQLParser.g:344:3: table_spec ( alias )?
			{
			pushFollow(FOLLOW_table_spec_in_a_table1681);
			table_spec195=table_spec();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_table_spec.add(table_spec195.getTree());
			// MySQLParser.g:344:15: ( alias )?
			int alt59=2;
			int LA59_0 = input.LA(1);
			if ( (LA59_0==ASTERISK||LA59_0==ID||LA59_0==79) ) {
				alt59=1;
			}
			switch (alt59) {
				case 1 :
					// MySQLParser.g:344:15: alias
					{
					pushFollow(FOLLOW_alias_in_a_table1684);
					alias196=alias();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_alias.add(alias196.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: alias, table_spec
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 344:21: -> ^( TABLENAME table_spec ( alias )? )
			{
				// MySQLParser.g:344:23: ^( TABLENAME table_spec ( alias )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TABLENAME, "TABLENAME"), root_1);
				adaptor.addChild(root_1, stream_table_spec.nextTree());
				// MySQLParser.g:344:46: ( alias )?
				if ( stream_alias.hasNext() ) {
					adaptor.addChild(root_1, stream_alias.nextTree());
				}
				stream_alias.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "a_table"


	public static class table_spec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "table_spec"
	// MySQLParser.g:346:1: table_spec : ( ( schema_name DOT !)? table_name | subquery );
	public final MySQLParserParser.table_spec_return table_spec() throws RecognitionException {
		MySQLParserParser.table_spec_return retval = new MySQLParserParser.table_spec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DOT198=null;
		ParserRuleReturnScope schema_name197 =null;
		ParserRuleReturnScope table_name199 =null;
		ParserRuleReturnScope subquery200 =null;

		CommonTree DOT198_tree=null;

		try {
			// MySQLParser.g:347:2: ( ( schema_name DOT !)? table_name | subquery )
			int alt61=2;
			int LA61_0 = input.LA(1);
			if ( (LA61_0==ASTERISK||LA61_0==ID) ) {
				alt61=1;
			}
			else if ( (LA61_0==LPAREN) ) {
				alt61=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 61, 0, input);
				throw nvae;
			}

			switch (alt61) {
				case 1 :
					// MySQLParser.g:347:4: ( schema_name DOT !)? table_name
					{
					root_0 = (CommonTree)adaptor.nil();


					// MySQLParser.g:347:4: ( schema_name DOT !)?
					int alt60=2;
					int LA60_0 = input.LA(1);
					if ( (LA60_0==ASTERISK||LA60_0==ID) ) {
						int LA60_1 = input.LA(2);
						if ( (LA60_1==DOT) ) {
							alt60=1;
						}
					}
					switch (alt60) {
						case 1 :
							// MySQLParser.g:347:6: schema_name DOT !
							{
							pushFollow(FOLLOW_schema_name_in_table_spec1706);
							schema_name197=schema_name();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, schema_name197.getTree());

							DOT198=(Token)match(input,DOT,FOLLOW_DOT_in_table_spec1708); if (state.failed) return retval;
							}
							break;

					}

					pushFollow(FOLLOW_table_name_in_table_spec1713);
					table_name199=table_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, table_name199.getTree());

					}
					break;
				case 2 :
					// MySQLParser.g:348:4: subquery
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_subquery_in_table_spec1719);
					subquery200=subquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, subquery200.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "table_spec"


	public static class table_name_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "table_name"
	// MySQLParser.g:351:1: table_name : identifier ;
	public final MySQLParserParser.table_name_return table_name() throws RecognitionException {
		MySQLParserParser.table_name_return retval = new MySQLParserParser.table_name_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope identifier201 =null;


		try {
			// MySQLParser.g:352:2: ( identifier )
			// MySQLParser.g:352:3: identifier
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_identifier_in_table_name1730);
			identifier201=identifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier201.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "table_name"


	public static class column_spec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "column_spec"
	// MySQLParser.g:355:1: column_spec : ( table_name DOT )? identifier -> ^( COLUMN identifier ( table_name )? ) ;
	public final MySQLParserParser.column_spec_return column_spec() throws RecognitionException {
		MySQLParserParser.column_spec_return retval = new MySQLParserParser.column_spec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DOT203=null;
		ParserRuleReturnScope table_name202 =null;
		ParserRuleReturnScope identifier204 =null;

		CommonTree DOT203_tree=null;
		RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
		RewriteRuleSubtreeStream stream_table_name=new RewriteRuleSubtreeStream(adaptor,"rule table_name");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// MySQLParser.g:356:2: ( ( table_name DOT )? identifier -> ^( COLUMN identifier ( table_name )? ) )
			// MySQLParser.g:356:3: ( table_name DOT )? identifier
			{
			// MySQLParser.g:356:3: ( table_name DOT )?
			int alt62=2;
			int LA62_0 = input.LA(1);
			if ( (LA62_0==ASTERISK||LA62_0==ID) ) {
				int LA62_1 = input.LA(2);
				if ( (LA62_1==DOT) ) {
					alt62=1;
				}
			}
			switch (alt62) {
				case 1 :
					// MySQLParser.g:356:4: table_name DOT
					{
					pushFollow(FOLLOW_table_name_in_column_spec1742);
					table_name202=table_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_table_name.add(table_name202.getTree());
					DOT203=(Token)match(input,DOT,FOLLOW_DOT_in_column_spec1744); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DOT.add(DOT203);

					}
					break;

			}

			pushFollow(FOLLOW_identifier_in_column_spec1748);
			identifier204=identifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_identifier.add(identifier204.getTree());
			// AST REWRITE
			// elements: identifier, table_name
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 356:32: -> ^( COLUMN identifier ( table_name )? )
			{
				// MySQLParser.g:356:34: ^( COLUMN identifier ( table_name )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(COLUMN, "COLUMN"), root_1);
				adaptor.addChild(root_1, stream_identifier.nextTree());
				// MySQLParser.g:356:54: ( table_name )?
				if ( stream_table_name.hasNext() ) {
					adaptor.addChild(root_1, stream_table_name.nextTree());
				}
				stream_table_name.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column_spec"


	public static class schema_name_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "schema_name"
	// MySQLParser.g:359:1: schema_name : identifier ;
	public final MySQLParserParser.schema_name_return schema_name() throws RecognitionException {
		MySQLParserParser.schema_name_return retval = new MySQLParserParser.schema_name_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope identifier205 =null;


		try {
			// MySQLParser.g:360:2: ( identifier )
			// MySQLParser.g:360:3: identifier
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_identifier_in_schema_name1768);
			identifier205=identifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier205.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "schema_name"


	public static class boolean_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "boolean_literal"
	// MySQLParser.g:363:1: boolean_literal : ( 'TRUE' | 'FALSE' );
	public final MySQLParserParser.boolean_literal_return boolean_literal() throws RecognitionException {
		MySQLParserParser.boolean_literal_return retval = new MySQLParserParser.boolean_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set206=null;

		CommonTree set206_tree=null;

		try {
			// MySQLParser.g:364:2: ( 'TRUE' | 'FALSE' )
			// MySQLParser.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set206=input.LT(1);
			if ( input.LA(1)==86||input.LA(1)==112 ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set206));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "boolean_literal"


	public static class alias_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "alias"
	// MySQLParser.g:367:1: alias : ( 'AS' )? identifier -> ^( AS identifier ) ;
	public final MySQLParserParser.alias_return alias() throws RecognitionException {
		MySQLParserParser.alias_return retval = new MySQLParserParser.alias_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal207=null;
		ParserRuleReturnScope identifier208 =null;

		CommonTree string_literal207_tree=null;
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// MySQLParser.g:368:2: ( ( 'AS' )? identifier -> ^( AS identifier ) )
			// MySQLParser.g:368:4: ( 'AS' )? identifier
			{
			// MySQLParser.g:368:4: ( 'AS' )?
			int alt63=2;
			int LA63_0 = input.LA(1);
			if ( (LA63_0==79) ) {
				alt63=1;
			}
			switch (alt63) {
				case 1 :
					// MySQLParser.g:368:6: 'AS'
					{
					string_literal207=(Token)match(input,79,FOLLOW_79_in_alias1798); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_79.add(string_literal207);

					}
					break;

			}

			pushFollow(FOLLOW_identifier_in_alias1802);
			identifier208=identifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_identifier.add(identifier208.getTree());
			// AST REWRITE
			// elements: identifier
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 368:23: -> ^( AS identifier )
			{
				// MySQLParser.g:368:25: ^( AS identifier )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AS, "AS"), root_1);
				adaptor.addChild(root_1, stream_identifier.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "alias"


	public static class identifier_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "identifier"
	// MySQLParser.g:371:1: identifier : ( ID | ASTERISK );
	public final MySQLParserParser.identifier_return identifier() throws RecognitionException {
		MySQLParserParser.identifier_return retval = new MySQLParserParser.identifier_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set209=null;

		CommonTree set209_tree=null;

		try {
			// MySQLParser.g:372:2: ( ID | ASTERISK )
			// MySQLParser.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set209=input.LT(1);
			if ( input.LA(1)==ASTERISK||input.LA(1)==ID ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set209));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identifier"


	public static class quoted_string_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "quoted_string"
	// MySQLParser.g:456:1: quoted_string : ( QUOTED_STRING | DOUBLEQUOTED_STRING );
	public final MySQLParserParser.quoted_string_return quoted_string() throws RecognitionException {
		MySQLParserParser.quoted_string_return retval = new MySQLParserParser.quoted_string_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set210=null;

		CommonTree set210_tree=null;

		try {
			// MySQLParser.g:457:2: ( QUOTED_STRING | DOUBLEQUOTED_STRING )
			// MySQLParser.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set210=input.LT(1);
			if ( input.LA(1)==DOUBLEQUOTED_STRING||input.LA(1)==QUOTED_STRING ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set210));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "quoted_string"


	public static class select_command_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "select_command"
	// MySQLParser.g:483:1: select_command : selectClause ( fromClause )? ( joinClause )* ( whereClause )? ( groupByClause )? ( orderByClause )? ( limitClause )? ( indexClause )? ;
	public final MySQLParserParser.select_command_return select_command() throws RecognitionException {
		MySQLParserParser.select_command_return retval = new MySQLParserParser.select_command_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope selectClause211 =null;
		ParserRuleReturnScope fromClause212 =null;
		ParserRuleReturnScope joinClause213 =null;
		ParserRuleReturnScope whereClause214 =null;
		ParserRuleReturnScope groupByClause215 =null;
		ParserRuleReturnScope orderByClause216 =null;
		ParserRuleReturnScope limitClause217 =null;
		ParserRuleReturnScope indexClause218 =null;


		try {
			// MySQLParser.g:484:6: ( selectClause ( fromClause )? ( joinClause )* ( whereClause )? ( groupByClause )? ( orderByClause )? ( limitClause )? ( indexClause )? )
			// MySQLParser.g:484:8: selectClause ( fromClause )? ( joinClause )* ( whereClause )? ( groupByClause )? ( orderByClause )? ( limitClause )? ( indexClause )?
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_selectClause_in_select_command2351);
			selectClause211=selectClause();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, selectClause211.getTree());

			// MySQLParser.g:484:21: ( fromClause )?
			int alt64=2;
			int LA64_0 = input.LA(1);
			if ( (LA64_0==88) ) {
				alt64=1;
			}
			switch (alt64) {
				case 1 :
					// MySQLParser.g:484:22: fromClause
					{
					pushFollow(FOLLOW_fromClause_in_select_command2354);
					fromClause212=fromClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, fromClause212.getTree());

					}
					break;

			}

			// MySQLParser.g:484:35: ( joinClause )*
			loop65:
			while (true) {
				int alt65=2;
				int LA65_0 = input.LA(1);
				if ( (LA65_0==94||(LA65_0 >= 98 && LA65_0 <= 99)||LA65_0==108) ) {
					alt65=1;
				}

				switch (alt65) {
				case 1 :
					// MySQLParser.g:484:36: joinClause
					{
					pushFollow(FOLLOW_joinClause_in_select_command2359);
					joinClause213=joinClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, joinClause213.getTree());

					}
					break;

				default :
					break loop65;
				}
			}

			// MySQLParser.g:484:49: ( whereClause )?
			int alt66=2;
			int LA66_0 = input.LA(1);
			if ( (LA66_0==115) ) {
				alt66=1;
			}
			switch (alt66) {
				case 1 :
					// MySQLParser.g:484:50: whereClause
					{
					pushFollow(FOLLOW_whereClause_in_select_command2364);
					whereClause214=whereClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, whereClause214.getTree());

					}
					break;

			}

			// MySQLParser.g:484:64: ( groupByClause )?
			int alt67=2;
			int LA67_0 = input.LA(1);
			if ( (LA67_0==89) ) {
				alt67=1;
			}
			switch (alt67) {
				case 1 :
					// MySQLParser.g:484:65: groupByClause
					{
					pushFollow(FOLLOW_groupByClause_in_select_command2369);
					groupByClause215=groupByClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, groupByClause215.getTree());

					}
					break;

			}

			// MySQLParser.g:484:81: ( orderByClause )?
			int alt68=2;
			int LA68_0 = input.LA(1);
			if ( (LA68_0==107) ) {
				alt68=1;
			}
			switch (alt68) {
				case 1 :
					// MySQLParser.g:484:82: orderByClause
					{
					pushFollow(FOLLOW_orderByClause_in_select_command2374);
					orderByClause216=orderByClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, orderByClause216.getTree());

					}
					break;

			}

			// MySQLParser.g:484:98: ( limitClause )?
			int alt69=2;
			int LA69_0 = input.LA(1);
			if ( (LA69_0==101) ) {
				alt69=1;
			}
			switch (alt69) {
				case 1 :
					// MySQLParser.g:484:99: limitClause
					{
					pushFollow(FOLLOW_limitClause_in_select_command2379);
					limitClause217=limitClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, limitClause217.getTree());

					}
					break;

			}

			// MySQLParser.g:484:113: ( indexClause )?
			int alt70=2;
			int LA70_0 = input.LA(1);
			if ( (LA70_0==87||LA70_0==90) ) {
				alt70=1;
			}
			switch (alt70) {
				case 1 :
					// MySQLParser.g:484:113: indexClause
					{
					pushFollow(FOLLOW_indexClause_in_select_command2383);
					indexClause218=indexClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, indexClause218.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "select_command"


	public static class groupByClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "groupByClause"
	// MySQLParser.g:486:1: groupByClause : 'GROUP BY' groupByColumns -> ^( GROUPBY groupByColumns ) ;
	public final MySQLParserParser.groupByClause_return groupByClause() throws RecognitionException {
		MySQLParserParser.groupByClause_return retval = new MySQLParserParser.groupByClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal219=null;
		ParserRuleReturnScope groupByColumns220 =null;

		CommonTree string_literal219_tree=null;
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleSubtreeStream stream_groupByColumns=new RewriteRuleSubtreeStream(adaptor,"rule groupByColumns");

		try {
			// MySQLParser.g:487:2: ( 'GROUP BY' groupByColumns -> ^( GROUPBY groupByColumns ) )
			// MySQLParser.g:487:4: 'GROUP BY' groupByColumns
			{
			string_literal219=(Token)match(input,89,FOLLOW_89_in_groupByClause2398); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_89.add(string_literal219);

			pushFollow(FOLLOW_groupByColumns_in_groupByClause2400);
			groupByColumns220=groupByColumns();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_groupByColumns.add(groupByColumns220.getTree());
			// AST REWRITE
			// elements: groupByColumns
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 487:29: -> ^( GROUPBY groupByColumns )
			{
				// MySQLParser.g:487:31: ^( GROUPBY groupByColumns )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(GROUPBY, "GROUPBY"), root_1);
				adaptor.addChild(root_1, stream_groupByColumns.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "groupByClause"


	public static class groupByColumns_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "groupByColumns"
	// MySQLParser.g:489:1: groupByColumns : groupByColumn ( COMMA ! groupByColumn )* ;
	public final MySQLParserParser.groupByColumns_return groupByColumns() throws RecognitionException {
		MySQLParserParser.groupByColumns_return retval = new MySQLParserParser.groupByColumns_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COMMA222=null;
		ParserRuleReturnScope groupByColumn221 =null;
		ParserRuleReturnScope groupByColumn223 =null;

		CommonTree COMMA222_tree=null;

		try {
			// MySQLParser.g:490:2: ( groupByColumn ( COMMA ! groupByColumn )* )
			// MySQLParser.g:490:4: groupByColumn ( COMMA ! groupByColumn )*
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_groupByColumn_in_groupByColumns2416);
			groupByColumn221=groupByColumn();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, groupByColumn221.getTree());

			// MySQLParser.g:490:18: ( COMMA ! groupByColumn )*
			loop71:
			while (true) {
				int alt71=2;
				int LA71_0 = input.LA(1);
				if ( (LA71_0==COMMA) ) {
					alt71=1;
				}

				switch (alt71) {
				case 1 :
					// MySQLParser.g:490:19: COMMA ! groupByColumn
					{
					COMMA222=(Token)match(input,COMMA,FOLLOW_COMMA_in_groupByColumns2419); if (state.failed) return retval;
					pushFollow(FOLLOW_groupByColumn_in_groupByColumns2422);
					groupByColumn223=groupByColumn();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, groupByColumn223.getTree());

					}
					break;

				default :
					break loop71;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "groupByColumns"


	public static class groupByColumn_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "groupByColumn"
	// MySQLParser.g:492:1: groupByColumn : identifier ;
	public final MySQLParserParser.groupByColumn_return groupByColumn() throws RecognitionException {
		MySQLParserParser.groupByColumn_return retval = new MySQLParserParser.groupByColumn_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope identifier224 =null;


		try {
			// MySQLParser.g:493:2: ( identifier )
			// MySQLParser.g:493:4: identifier
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_identifier_in_groupByColumn2434);
			identifier224=identifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier224.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "groupByColumn"


	public static class indexClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "indexClause"
	// MySQLParser.g:495:2: indexClause : ( 'FORCE' 'INDEX' LPAREN select_list RPAREN | 'IGNORE' 'INDEX' LPAREN select_list RPAREN );
	public final MySQLParserParser.indexClause_return indexClause() throws RecognitionException {
		MySQLParserParser.indexClause_return retval = new MySQLParserParser.indexClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal225=null;
		Token string_literal226=null;
		Token LPAREN227=null;
		Token RPAREN229=null;
		Token string_literal230=null;
		Token string_literal231=null;
		Token LPAREN232=null;
		Token RPAREN234=null;
		ParserRuleReturnScope select_list228 =null;
		ParserRuleReturnScope select_list233 =null;

		CommonTree string_literal225_tree=null;
		CommonTree string_literal226_tree=null;
		CommonTree LPAREN227_tree=null;
		CommonTree RPAREN229_tree=null;
		CommonTree string_literal230_tree=null;
		CommonTree string_literal231_tree=null;
		CommonTree LPAREN232_tree=null;
		CommonTree RPAREN234_tree=null;

		try {
			// MySQLParser.g:496:3: ( 'FORCE' 'INDEX' LPAREN select_list RPAREN | 'IGNORE' 'INDEX' LPAREN select_list RPAREN )
			int alt72=2;
			int LA72_0 = input.LA(1);
			if ( (LA72_0==87) ) {
				alt72=1;
			}
			else if ( (LA72_0==90) ) {
				alt72=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 72, 0, input);
				throw nvae;
			}

			switch (alt72) {
				case 1 :
					// MySQLParser.g:496:4: 'FORCE' 'INDEX' LPAREN select_list RPAREN
					{
					root_0 = (CommonTree)adaptor.nil();


					string_literal225=(Token)match(input,87,FOLLOW_87_in_indexClause2445); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal225_tree = (CommonTree)adaptor.create(string_literal225);
					adaptor.addChild(root_0, string_literal225_tree);
					}

					string_literal226=(Token)match(input,92,FOLLOW_92_in_indexClause2447); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal226_tree = (CommonTree)adaptor.create(string_literal226);
					adaptor.addChild(root_0, string_literal226_tree);
					}

					LPAREN227=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_indexClause2449); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LPAREN227_tree = (CommonTree)adaptor.create(LPAREN227);
					adaptor.addChild(root_0, LPAREN227_tree);
					}

					pushFollow(FOLLOW_select_list_in_indexClause2451);
					select_list228=select_list();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, select_list228.getTree());

					RPAREN229=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_indexClause2454); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RPAREN229_tree = (CommonTree)adaptor.create(RPAREN229);
					adaptor.addChild(root_0, RPAREN229_tree);
					}

					}
					break;
				case 2 :
					// MySQLParser.g:497:4: 'IGNORE' 'INDEX' LPAREN select_list RPAREN
					{
					root_0 = (CommonTree)adaptor.nil();


					string_literal230=(Token)match(input,90,FOLLOW_90_in_indexClause2459); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal230_tree = (CommonTree)adaptor.create(string_literal230);
					adaptor.addChild(root_0, string_literal230_tree);
					}

					string_literal231=(Token)match(input,92,FOLLOW_92_in_indexClause2461); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal231_tree = (CommonTree)adaptor.create(string_literal231);
					adaptor.addChild(root_0, string_literal231_tree);
					}

					LPAREN232=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_indexClause2463); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LPAREN232_tree = (CommonTree)adaptor.create(LPAREN232);
					adaptor.addChild(root_0, LPAREN232_tree);
					}

					pushFollow(FOLLOW_select_list_in_indexClause2465);
					select_list233=select_list();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, select_list233.getTree());

					RPAREN234=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_indexClause2467); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RPAREN234_tree = (CommonTree)adaptor.create(RPAREN234);
					adaptor.addChild(root_0, RPAREN234_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "indexClause"


	public static class delete_command_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "delete_command"
	// MySQLParser.g:499:1: delete_command : 'DELETE' fromClause ( whereClause )? ( orderByClause )? ( limitClause )? -> ^( DELETE fromClause ( whereClause )? ( orderByClause )? ( limitClause )? ) ;
	public final MySQLParserParser.delete_command_return delete_command() throws RecognitionException {
		MySQLParserParser.delete_command_return retval = new MySQLParserParser.delete_command_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal235=null;
		ParserRuleReturnScope fromClause236 =null;
		ParserRuleReturnScope whereClause237 =null;
		ParserRuleReturnScope orderByClause238 =null;
		ParserRuleReturnScope limitClause239 =null;

		CommonTree string_literal235_tree=null;
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleSubtreeStream stream_whereClause=new RewriteRuleSubtreeStream(adaptor,"rule whereClause");
		RewriteRuleSubtreeStream stream_limitClause=new RewriteRuleSubtreeStream(adaptor,"rule limitClause");
		RewriteRuleSubtreeStream stream_orderByClause=new RewriteRuleSubtreeStream(adaptor,"rule orderByClause");
		RewriteRuleSubtreeStream stream_fromClause=new RewriteRuleSubtreeStream(adaptor,"rule fromClause");

		try {
			// MySQLParser.g:500:2: ( 'DELETE' fromClause ( whereClause )? ( orderByClause )? ( limitClause )? -> ^( DELETE fromClause ( whereClause )? ( orderByClause )? ( limitClause )? ) )
			// MySQLParser.g:500:3: 'DELETE' fromClause ( whereClause )? ( orderByClause )? ( limitClause )?
			{
			string_literal235=(Token)match(input,84,FOLLOW_84_in_delete_command2477); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_84.add(string_literal235);

			pushFollow(FOLLOW_fromClause_in_delete_command2479);
			fromClause236=fromClause();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_fromClause.add(fromClause236.getTree());
			// MySQLParser.g:500:23: ( whereClause )?
			int alt73=2;
			int LA73_0 = input.LA(1);
			if ( (LA73_0==115) ) {
				alt73=1;
			}
			switch (alt73) {
				case 1 :
					// MySQLParser.g:500:23: whereClause
					{
					pushFollow(FOLLOW_whereClause_in_delete_command2481);
					whereClause237=whereClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_whereClause.add(whereClause237.getTree());
					}
					break;

			}

			// MySQLParser.g:500:36: ( orderByClause )?
			int alt74=2;
			int LA74_0 = input.LA(1);
			if ( (LA74_0==107) ) {
				alt74=1;
			}
			switch (alt74) {
				case 1 :
					// MySQLParser.g:500:36: orderByClause
					{
					pushFollow(FOLLOW_orderByClause_in_delete_command2484);
					orderByClause238=orderByClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_orderByClause.add(orderByClause238.getTree());
					}
					break;

			}

			// MySQLParser.g:500:52: ( limitClause )?
			int alt75=2;
			int LA75_0 = input.LA(1);
			if ( (LA75_0==101) ) {
				alt75=1;
			}
			switch (alt75) {
				case 1 :
					// MySQLParser.g:500:53: limitClause
					{
					pushFollow(FOLLOW_limitClause_in_delete_command2489);
					limitClause239=limitClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_limitClause.add(limitClause239.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: whereClause, orderByClause, fromClause, limitClause
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 500:66: -> ^( DELETE fromClause ( whereClause )? ( orderByClause )? ( limitClause )? )
			{
				// MySQLParser.g:500:68: ^( DELETE fromClause ( whereClause )? ( orderByClause )? ( limitClause )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DELETE, "DELETE"), root_1);
				adaptor.addChild(root_1, stream_fromClause.nextTree());
				// MySQLParser.g:500:88: ( whereClause )?
				if ( stream_whereClause.hasNext() ) {
					adaptor.addChild(root_1, stream_whereClause.nextTree());
				}
				stream_whereClause.reset();

				// MySQLParser.g:500:101: ( orderByClause )?
				if ( stream_orderByClause.hasNext() ) {
					adaptor.addChild(root_1, stream_orderByClause.nextTree());
				}
				stream_orderByClause.reset();

				// MySQLParser.g:500:116: ( limitClause )?
				if ( stream_limitClause.hasNext() ) {
					adaptor.addChild(root_1, stream_limitClause.nextTree());
				}
				stream_limitClause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "delete_command"


	public static class update_command_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "update_command"
	// MySQLParser.g:502:1: update_command : 'UPDATE' selected_table setclause ( whereClause )? ( limitClause )? -> ^( UPDATE selected_table setclause ( whereClause )? ( limitClause )? ) ;
	public final MySQLParserParser.update_command_return update_command() throws RecognitionException {
		MySQLParserParser.update_command_return retval = new MySQLParserParser.update_command_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal240=null;
		ParserRuleReturnScope selected_table241 =null;
		ParserRuleReturnScope setclause242 =null;
		ParserRuleReturnScope whereClause243 =null;
		ParserRuleReturnScope limitClause244 =null;

		CommonTree string_literal240_tree=null;
		RewriteRuleTokenStream stream_113=new RewriteRuleTokenStream(adaptor,"token 113");
		RewriteRuleSubtreeStream stream_whereClause=new RewriteRuleSubtreeStream(adaptor,"rule whereClause");
		RewriteRuleSubtreeStream stream_selected_table=new RewriteRuleSubtreeStream(adaptor,"rule selected_table");
		RewriteRuleSubtreeStream stream_limitClause=new RewriteRuleSubtreeStream(adaptor,"rule limitClause");
		RewriteRuleSubtreeStream stream_setclause=new RewriteRuleSubtreeStream(adaptor,"rule setclause");

		try {
			// MySQLParser.g:503:2: ( 'UPDATE' selected_table setclause ( whereClause )? ( limitClause )? -> ^( UPDATE selected_table setclause ( whereClause )? ( limitClause )? ) )
			// MySQLParser.g:503:3: 'UPDATE' selected_table setclause ( whereClause )? ( limitClause )?
			{
			string_literal240=(Token)match(input,113,FOLLOW_113_in_update_command2515); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_113.add(string_literal240);

			pushFollow(FOLLOW_selected_table_in_update_command2517);
			selected_table241=selected_table();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_selected_table.add(selected_table241.getTree());
			pushFollow(FOLLOW_setclause_in_update_command2520);
			setclause242=setclause();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_setclause.add(setclause242.getTree());
			// MySQLParser.g:503:38: ( whereClause )?
			int alt76=2;
			int LA76_0 = input.LA(1);
			if ( (LA76_0==115) ) {
				alt76=1;
			}
			switch (alt76) {
				case 1 :
					// MySQLParser.g:503:38: whereClause
					{
					pushFollow(FOLLOW_whereClause_in_update_command2522);
					whereClause243=whereClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_whereClause.add(whereClause243.getTree());
					}
					break;

			}

			// MySQLParser.g:503:52: ( limitClause )?
			int alt77=2;
			int LA77_0 = input.LA(1);
			if ( (LA77_0==101) ) {
				alt77=1;
			}
			switch (alt77) {
				case 1 :
					// MySQLParser.g:503:53: limitClause
					{
					pushFollow(FOLLOW_limitClause_in_update_command2527);
					limitClause244=limitClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_limitClause.add(limitClause244.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: selected_table, whereClause, limitClause, setclause
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 503:66: -> ^( UPDATE selected_table setclause ( whereClause )? ( limitClause )? )
			{
				// MySQLParser.g:503:68: ^( UPDATE selected_table setclause ( whereClause )? ( limitClause )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UPDATE, "UPDATE"), root_1);
				adaptor.addChild(root_1, stream_selected_table.nextTree());
				adaptor.addChild(root_1, stream_setclause.nextTree());
				// MySQLParser.g:503:102: ( whereClause )?
				if ( stream_whereClause.hasNext() ) {
					adaptor.addChild(root_1, stream_whereClause.nextTree());
				}
				stream_whereClause.reset();

				// MySQLParser.g:503:115: ( limitClause )?
				if ( stream_limitClause.hasNext() ) {
					adaptor.addChild(root_1, stream_limitClause.nextTree());
				}
				stream_limitClause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "update_command"


	public static class limitClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "limitClause"
	// MySQLParser.g:505:1: limitClause : 'LIMIT' ( skip COMMA )? range -> ^( 'LIMIT' ( skip )? range ) ;
	public final MySQLParserParser.limitClause_return limitClause() throws RecognitionException {
		MySQLParserParser.limitClause_return retval = new MySQLParserParser.limitClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal245=null;
		Token COMMA247=null;
		ParserRuleReturnScope skip246 =null;
		ParserRuleReturnScope range248 =null;

		CommonTree string_literal245_tree=null;
		CommonTree COMMA247_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_101=new RewriteRuleTokenStream(adaptor,"token 101");
		RewriteRuleSubtreeStream stream_range=new RewriteRuleSubtreeStream(adaptor,"rule range");
		RewriteRuleSubtreeStream stream_skip=new RewriteRuleSubtreeStream(adaptor,"rule skip");

		try {
			// MySQLParser.g:506:2: ( 'LIMIT' ( skip COMMA )? range -> ^( 'LIMIT' ( skip )? range ) )
			// MySQLParser.g:506:3: 'LIMIT' ( skip COMMA )? range
			{
			string_literal245=(Token)match(input,101,FOLLOW_101_in_limitClause2552); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_101.add(string_literal245);

			// MySQLParser.g:506:11: ( skip COMMA )?
			int alt78=2;
			int LA78_0 = input.LA(1);
			if ( (LA78_0==N) ) {
				int LA78_1 = input.LA(2);
				if ( (LA78_1==COMMA) ) {
					alt78=1;
				}
			}
			else if ( (LA78_0==77) ) {
				int LA78_2 = input.LA(2);
				if ( (LA78_2==COMMA) ) {
					alt78=1;
				}
			}
			switch (alt78) {
				case 1 :
					// MySQLParser.g:506:12: skip COMMA
					{
					pushFollow(FOLLOW_skip_in_limitClause2555);
					skip246=skip();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_skip.add(skip246.getTree());
					COMMA247=(Token)match(input,COMMA,FOLLOW_COMMA_in_limitClause2557); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA247);

					}
					break;

			}

			pushFollow(FOLLOW_range_in_limitClause2562);
			range248=range();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_range.add(range248.getTree());
			// AST REWRITE
			// elements: range, skip, 101
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 506:31: -> ^( 'LIMIT' ( skip )? range )
			{
				// MySQLParser.g:506:33: ^( 'LIMIT' ( skip )? range )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_101.nextNode(), root_1);
				// MySQLParser.g:506:43: ( skip )?
				if ( stream_skip.hasNext() ) {
					adaptor.addChild(root_1, stream_skip.nextTree());
				}
				stream_skip.reset();

				adaptor.addChild(root_1, stream_range.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "limitClause"


	public static class skip_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "skip"
	// MySQLParser.g:508:1: skip : ( N -> ^( SKIP N ) | '?' -> ^( SKIP '?' ) );
	public final MySQLParserParser.skip_return skip() throws RecognitionException {
		MySQLParserParser.skip_return retval = new MySQLParserParser.skip_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token N249=null;
		Token char_literal250=null;

		CommonTree N249_tree=null;
		CommonTree char_literal250_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_N=new RewriteRuleTokenStream(adaptor,"token N");

		try {
			// MySQLParser.g:509:2: ( N -> ^( SKIP N ) | '?' -> ^( SKIP '?' ) )
			int alt79=2;
			int LA79_0 = input.LA(1);
			if ( (LA79_0==N) ) {
				alt79=1;
			}
			else if ( (LA79_0==77) ) {
				alt79=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 79, 0, input);
				throw nvae;
			}

			switch (alt79) {
				case 1 :
					// MySQLParser.g:509:3: N
					{
					N249=(Token)match(input,N,FOLLOW_N_in_skip2581); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_N.add(N249);

					// AST REWRITE
					// elements: N
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 509:5: -> ^( SKIP N )
					{
						// MySQLParser.g:509:7: ^( SKIP N )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SKIP, "SKIP"), root_1);
						adaptor.addChild(root_1, stream_N.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// MySQLParser.g:510:3: '?'
					{
					char_literal250=(Token)match(input,77,FOLLOW_77_in_skip2592); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_77.add(char_literal250);

					// AST REWRITE
					// elements: 77
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 510:6: -> ^( SKIP '?' )
					{
						// MySQLParser.g:510:8: ^( SKIP '?' )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SKIP, "SKIP"), root_1);
						adaptor.addChild(root_1, stream_77.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "skip"


	public static class range_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "range"
	// MySQLParser.g:512:1: range : ( N -> ^( RANGE N ) | '?' -> ^( RANGE '?' ) );
	public final MySQLParserParser.range_return range() throws RecognitionException {
		MySQLParserParser.range_return retval = new MySQLParserParser.range_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token N251=null;
		Token char_literal252=null;

		CommonTree N251_tree=null;
		CommonTree char_literal252_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_N=new RewriteRuleTokenStream(adaptor,"token N");

		try {
			// MySQLParser.g:512:7: ( N -> ^( RANGE N ) | '?' -> ^( RANGE '?' ) )
			int alt80=2;
			int LA80_0 = input.LA(1);
			if ( (LA80_0==N) ) {
				alt80=1;
			}
			else if ( (LA80_0==77) ) {
				alt80=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 80, 0, input);
				throw nvae;
			}

			switch (alt80) {
				case 1 :
					// MySQLParser.g:512:8: N
					{
					N251=(Token)match(input,N,FOLLOW_N_in_range2606); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_N.add(N251);

					// AST REWRITE
					// elements: N
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 512:9: -> ^( RANGE N )
					{
						// MySQLParser.g:512:11: ^( RANGE N )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(RANGE, "RANGE"), root_1);
						adaptor.addChild(root_1, stream_N.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// MySQLParser.g:513:3: '?'
					{
					char_literal252=(Token)match(input,77,FOLLOW_77_in_range2616); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_77.add(char_literal252);

					// AST REWRITE
					// elements: 77
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 513:6: -> ^( RANGE '?' )
					{
						// MySQLParser.g:513:8: ^( RANGE '?' )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(RANGE, "RANGE"), root_1);
						adaptor.addChild(root_1, stream_77.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "range"


	public static class joinClause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "joinClause"
	// MySQLParser.g:515:1: joinClause : joinType table_spec ( alias )? 'ON' sqlCondition -> ^( joinType table_spec ( alias )? 'ON' sqlCondition ) ;
	public final MySQLParserParser.joinClause_return joinClause() throws RecognitionException {
		MySQLParserParser.joinClause_return retval = new MySQLParserParser.joinClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal256=null;
		ParserRuleReturnScope joinType253 =null;
		ParserRuleReturnScope table_spec254 =null;
		ParserRuleReturnScope alias255 =null;
		ParserRuleReturnScope sqlCondition257 =null;

		CommonTree string_literal256_tree=null;
		RewriteRuleTokenStream stream_105=new RewriteRuleTokenStream(adaptor,"token 105");
		RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias");
		RewriteRuleSubtreeStream stream_sqlCondition=new RewriteRuleSubtreeStream(adaptor,"rule sqlCondition");
		RewriteRuleSubtreeStream stream_joinType=new RewriteRuleSubtreeStream(adaptor,"rule joinType");
		RewriteRuleSubtreeStream stream_table_spec=new RewriteRuleSubtreeStream(adaptor,"rule table_spec");

		try {
			// MySQLParser.g:516:2: ( joinType table_spec ( alias )? 'ON' sqlCondition -> ^( joinType table_spec ( alias )? 'ON' sqlCondition ) )
			// MySQLParser.g:516:4: joinType table_spec ( alias )? 'ON' sqlCondition
			{
			pushFollow(FOLLOW_joinType_in_joinClause2632);
			joinType253=joinType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_joinType.add(joinType253.getTree());
			pushFollow(FOLLOW_table_spec_in_joinClause2634);
			table_spec254=table_spec();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_table_spec.add(table_spec254.getTree());
			// MySQLParser.g:516:24: ( alias )?
			int alt81=2;
			int LA81_0 = input.LA(1);
			if ( (LA81_0==ASTERISK||LA81_0==ID||LA81_0==79) ) {
				alt81=1;
			}
			switch (alt81) {
				case 1 :
					// MySQLParser.g:516:24: alias
					{
					pushFollow(FOLLOW_alias_in_joinClause2636);
					alias255=alias();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_alias.add(alias255.getTree());
					}
					break;

			}

			string_literal256=(Token)match(input,105,FOLLOW_105_in_joinClause2639); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_105.add(string_literal256);

			pushFollow(FOLLOW_sqlCondition_in_joinClause2641);
			sqlCondition257=sqlCondition();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_sqlCondition.add(sqlCondition257.getTree());
			// AST REWRITE
			// elements: sqlCondition, alias, 105, joinType, table_spec
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 516:48: -> ^( joinType table_spec ( alias )? 'ON' sqlCondition )
			{
				// MySQLParser.g:516:50: ^( joinType table_spec ( alias )? 'ON' sqlCondition )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot(stream_joinType.nextNode(), root_1);
				adaptor.addChild(root_1, stream_table_spec.nextTree());
				// MySQLParser.g:516:72: ( alias )?
				if ( stream_alias.hasNext() ) {
					adaptor.addChild(root_1, stream_alias.nextTree());
				}
				stream_alias.reset();

				adaptor.addChild(root_1, stream_105.nextNode());
				adaptor.addChild(root_1, stream_sqlCondition.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "joinClause"


	public static class joinType_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "joinType"
	// MySQLParser.g:518:1: joinType : ( 'INNER JOIN' | 'LEFT JOIN' | 'RIGHT JOIN' | 'JOIN' );
	public final MySQLParserParser.joinType_return joinType() throws RecognitionException {
		MySQLParserParser.joinType_return retval = new MySQLParserParser.joinType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set258=null;

		CommonTree set258_tree=null;

		try {
			// MySQLParser.g:519:2: ( 'INNER JOIN' | 'LEFT JOIN' | 'RIGHT JOIN' | 'JOIN' )
			// MySQLParser.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set258=input.LT(1);
			if ( input.LA(1)==94||(input.LA(1) >= 98 && input.LA(1) <= 99)||input.LA(1)==108 ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set258));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "joinType"


	public static class concat_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "concat"
	// MySQLParser.g:521:1: concat : 'CONCAT' ;
	public final MySQLParserParser.concat_return concat() throws RecognitionException {
		MySQLParserParser.concat_return retval = new MySQLParserParser.concat_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal259=null;

		CommonTree string_literal259_tree=null;

		try {
			// MySQLParser.g:522:2: ( 'CONCAT' )
			// MySQLParser.g:522:4: 'CONCAT'
			{
			root_0 = (CommonTree)adaptor.nil();


			string_literal259=(Token)match(input,82,FOLLOW_82_in_concat2686); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal259_tree = (CommonTree)adaptor.create(string_literal259);
			adaptor.addChild(root_0, string_literal259_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "concat"


	public static class identifiedOrQuotedString_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "identifiedOrQuotedString"
	// MySQLParser.g:524:1: identifiedOrQuotedString : ( ( ( table_alias )? identifier ) | quoted_string ) ;
	public final MySQLParserParser.identifiedOrQuotedString_return identifiedOrQuotedString() throws RecognitionException {
		MySQLParserParser.identifiedOrQuotedString_return retval = new MySQLParserParser.identifiedOrQuotedString_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope table_alias260 =null;
		ParserRuleReturnScope identifier261 =null;
		ParserRuleReturnScope quoted_string262 =null;


		try {
			// MySQLParser.g:525:2: ( ( ( ( table_alias )? identifier ) | quoted_string ) )
			// MySQLParser.g:525:4: ( ( ( table_alias )? identifier ) | quoted_string )
			{
			root_0 = (CommonTree)adaptor.nil();


			// MySQLParser.g:525:4: ( ( ( table_alias )? identifier ) | quoted_string )
			int alt83=2;
			int LA83_0 = input.LA(1);
			if ( (LA83_0==ASTERISK||LA83_0==ID) ) {
				alt83=1;
			}
			else if ( (LA83_0==DOUBLEQUOTED_STRING||LA83_0==QUOTED_STRING) ) {
				alt83=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 83, 0, input);
				throw nvae;
			}

			switch (alt83) {
				case 1 :
					// MySQLParser.g:525:6: ( ( table_alias )? identifier )
					{
					// MySQLParser.g:525:6: ( ( table_alias )? identifier )
					// MySQLParser.g:525:7: ( table_alias )? identifier
					{
					// MySQLParser.g:525:7: ( table_alias )?
					int alt82=2;
					int LA82_0 = input.LA(1);
					if ( (LA82_0==ASTERISK||LA82_0==ID) ) {
						int LA82_1 = input.LA(2);
						if ( (LA82_1==DOT) ) {
							alt82=1;
						}
					}
					switch (alt82) {
						case 1 :
							// MySQLParser.g:525:7: table_alias
							{
							pushFollow(FOLLOW_table_alias_in_identifiedOrQuotedString2699);
							table_alias260=table_alias();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, table_alias260.getTree());

							}
							break;

					}

					pushFollow(FOLLOW_identifier_in_identifiedOrQuotedString2702);
					identifier261=identifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier261.getTree());

					}

					}
					break;
				case 2 :
					// MySQLParser.g:525:34: quoted_string
					{
					pushFollow(FOLLOW_quoted_string_in_identifiedOrQuotedString2707);
					quoted_string262=quoted_string();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, quoted_string262.getTree());

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identifiedOrQuotedString"


	public static class distinct_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "distinct"
	// MySQLParser.g:527:1: distinct : 'DISTINCT' ;
	public final MySQLParserParser.distinct_return distinct() throws RecognitionException {
		MySQLParserParser.distinct_return retval = new MySQLParserParser.distinct_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal263=null;

		CommonTree string_literal263_tree=null;

		try {
			// MySQLParser.g:528:2: ( 'DISTINCT' )
			// MySQLParser.g:528:4: 'DISTINCT'
			{
			root_0 = (CommonTree)adaptor.nil();


			string_literal263=(Token)match(input,85,FOLLOW_85_in_distinct2719); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal263_tree = (CommonTree)adaptor.create(string_literal263);
			adaptor.addChild(root_0, string_literal263_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "distinct"


	public static class count_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "count"
	// MySQLParser.g:530:1: count : 'COUNT' ;
	public final MySQLParserParser.count_return count() throws RecognitionException {
		MySQLParserParser.count_return retval = new MySQLParserParser.count_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token string_literal264=null;

		CommonTree string_literal264_tree=null;

		try {
			// MySQLParser.g:531:2: ( 'COUNT' )
			// MySQLParser.g:531:4: 'COUNT'
			{
			root_0 = (CommonTree)adaptor.nil();


			string_literal264=(Token)match(input,83,FOLLOW_83_in_count2729); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal264_tree = (CommonTree)adaptor.create(string_literal264);
			adaptor.addChild(root_0, string_literal264_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "count"


	public static class identifierOrN_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "identifierOrN"
	// MySQLParser.g:533:1: identifierOrN : ( identifier | N ) ;
	public final MySQLParserParser.identifierOrN_return identifierOrN() throws RecognitionException {
		MySQLParserParser.identifierOrN_return retval = new MySQLParserParser.identifierOrN_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token N266=null;
		ParserRuleReturnScope identifier265 =null;

		CommonTree N266_tree=null;

		try {
			// MySQLParser.g:534:2: ( ( identifier | N ) )
			// MySQLParser.g:535:2: ( identifier | N )
			{
			root_0 = (CommonTree)adaptor.nil();


			// MySQLParser.g:535:2: ( identifier | N )
			int alt84=2;
			int LA84_0 = input.LA(1);
			if ( (LA84_0==ASTERISK||LA84_0==ID) ) {
				alt84=1;
			}
			else if ( (LA84_0==N) ) {
				alt84=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 84, 0, input);
				throw nvae;
			}

			switch (alt84) {
				case 1 :
					// MySQLParser.g:535:3: identifier
					{
					pushFollow(FOLLOW_identifier_in_identifierOrN2741);
					identifier265=identifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier265.getTree());

					}
					break;
				case 2 :
					// MySQLParser.g:535:16: N
					{
					N266=(Token)match(input,N,FOLLOW_N_in_identifierOrN2745); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					N266_tree = (CommonTree)adaptor.create(N266);
					adaptor.addChild(root_0, N266_tree);
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identifierOrN"


	public static class countColumn_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "countColumn"
	// MySQLParser.g:537:1: countColumn : ( identifier DOT )? identifierOrN -> ^( COUNTCOLUMN ( identifier )? identifierOrN ) ;
	public final MySQLParserParser.countColumn_return countColumn() throws RecognitionException {
		MySQLParserParser.countColumn_return retval = new MySQLParserParser.countColumn_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DOT268=null;
		ParserRuleReturnScope identifier267 =null;
		ParserRuleReturnScope identifierOrN269 =null;

		CommonTree DOT268_tree=null;
		RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
		RewriteRuleSubtreeStream stream_identifierOrN=new RewriteRuleSubtreeStream(adaptor,"rule identifierOrN");
		RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");

		try {
			// MySQLParser.g:538:2: ( ( identifier DOT )? identifierOrN -> ^( COUNTCOLUMN ( identifier )? identifierOrN ) )
			// MySQLParser.g:538:4: ( identifier DOT )? identifierOrN
			{
			// MySQLParser.g:538:4: ( identifier DOT )?
			int alt85=2;
			int LA85_0 = input.LA(1);
			if ( (LA85_0==ASTERISK||LA85_0==ID) ) {
				int LA85_1 = input.LA(2);
				if ( (LA85_1==DOT) ) {
					alt85=1;
				}
			}
			switch (alt85) {
				case 1 :
					// MySQLParser.g:538:5: identifier DOT
					{
					pushFollow(FOLLOW_identifier_in_countColumn2757);
					identifier267=identifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_identifier.add(identifier267.getTree());
					DOT268=(Token)match(input,DOT,FOLLOW_DOT_in_countColumn2759); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DOT.add(DOT268);

					}
					break;

			}

			pushFollow(FOLLOW_identifierOrN_in_countColumn2763);
			identifierOrN269=identifierOrN();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_identifierOrN.add(identifierOrN269.getTree());
			// AST REWRITE
			// elements: identifier, identifierOrN
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 538:35: -> ^( COUNTCOLUMN ( identifier )? identifierOrN )
			{
				// MySQLParser.g:538:37: ^( COUNTCOLUMN ( identifier )? identifierOrN )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(COUNTCOLUMN, "COUNTCOLUMN"), root_1);
				// MySQLParser.g:538:51: ( identifier )?
				if ( stream_identifier.hasNext() ) {
					adaptor.addChild(root_1, stream_identifier.nextTree());
				}
				stream_identifier.reset();

				adaptor.addChild(root_1, stream_identifierOrN.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException e) {
		throw e;
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "countColumn"

	// $ANTLR start synpred1_MySQLParser
	public final void synpred1_MySQLParser_fragment() throws RecognitionException {
		// MySQLParser.g:169:3: ( LPAREN condition_or RPAREN )
		// MySQLParser.g:169:4: LPAREN condition_or RPAREN
		{
		match(input,LPAREN,FOLLOW_LPAREN_in_synpred1_MySQLParser574); if (state.failed) return;

		pushFollow(FOLLOW_condition_or_in_synpred1_MySQLParser576);
		condition_or();
		state._fsp--;
		if (state.failed) return;

		match(input,RPAREN,FOLLOW_RPAREN_in_synpred1_MySQLParser578); if (state.failed) return;

		}

	}
	// $ANTLR end synpred1_MySQLParser

	// Delegated rules

	public final boolean synpred1_MySQLParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_MySQLParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}



	public static final BitSet FOLLOW_start_rule_in_beg246 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_select_command_in_start_rule258 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_update_command_in_start_rule262 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insert_command_in_start_rule266 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_command_in_start_rule270 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_111_in_setclause282 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_updateColumnSpecs_in_setclause284 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_updateColumnSpec_in_updateColumnSpecs301 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_COMMA_in_updateColumnSpecs304 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_updateColumnSpec_in_updateColumnSpecs306 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_columnNameInUpdate_in_updateColumnSpec324 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_EQ_in_updateColumnSpec326 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_in_updateColumnSpec329 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_95_in_insert_command339 = new BitSet(new long[]{0x0000000000000000L,0x0000000104000000L});
	public static final BitSet FOLLOW_90_in_insert_command342 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_insert_command346 = new BitSet(new long[]{0x0000200200000200L});
	public static final BitSet FOLLOW_selected_table_in_insert_command348 = new BitSet(new long[]{0x0000200000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_LPAREN_in_insert_command354 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_column_specs_in_insert_command356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_insert_command359 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_114_in_insert_command367 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_insert_command369 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_values_in_insert_command371 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_insert_command373 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_107_in_orderByClause402 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_orderByClause404 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_columnNamesAfterWhere_in_orderByClause406 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere426 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_COMMA_in_columnNamesAfterWhere429 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere432 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_110_in_selectClause450 = new BitSet(new long[]{0x4902A00202000200L,0x00012100006C2000L});
	public static final BitSet FOLLOW_distinct_in_selectClause452 = new BitSet(new long[]{0x4902A00202000200L,0x00012100004C2000L});
	public static final BitSet FOLLOW_LPAREN_in_selectClause455 = new BitSet(new long[]{0x4902A00202000200L,0x00012100004C2000L});
	public static final BitSet FOLLOW_select_list_in_selectClause458 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_selectClause460 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_115_in_whereClause485 = new BitSet(new long[]{0x4902A00202000200L,0x0001218000402000L});
	public static final BitSet FOLLOW_sqlCondition_in_whereClause487 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_103_in_sqlCondition503 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_condition_or_in_sqlCondition505 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_condition_or_in_sqlCondition516 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_condition_and_in_condition_or534 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});
	public static final BitSet FOLLOW_106_in_condition_or538 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_condition_and_in_condition_or541 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});
	public static final BitSet FOLLOW_condition_PAREN_in_condition_and554 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
	public static final BitSet FOLLOW_78_in_condition_and558 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_condition_PAREN_in_condition_and561 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
	public static final BitSet FOLLOW_LPAREN_in_condition_PAREN581 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_condition_or_in_condition_PAREN583 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_condition_PAREN585 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_condition_expr_in_condition_PAREN595 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_condition_left_in_condition_expr605 = new BitSet(new long[]{0x0020480148000000L,0x0000009208010000L});
	public static final BitSet FOLLOW_comparisonCondition_in_condition_expr610 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inCondition_in_condition_expr616 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_isCondition_in_condition_expr622 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_likeCondition_in_condition_expr628 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_betweenCondition_in_condition_expr634 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_condition_left648 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_103_in_betweenCondition664 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_betweenCondition666 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_between_and_in_betweenCondition668 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_80_in_betweenCondition679 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_between_and_in_betweenCondition681 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_between_and_expression_in_between_and700 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_78_in_between_and702 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_between_and_expression_in_between_and706 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quoted_string_in_between_and_expression726 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_add_in_between_and_expression731 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_97_in_isCondition743 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_isCondition745 = new BitSet(new long[]{0x0000000000000000L,0x0000014020000000L});
	public static final BitSet FOLLOW_condition_is_valobject_in_isCondition747 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_97_in_isCondition757 = new BitSet(new long[]{0x0000000000000000L,0x0000014020000000L});
	public static final BitSet FOLLOW_condition_is_valobject_in_isCondition759 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_102_in_condition_is_valobject777 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_condition_is_valobject785 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_104_in_condition_is_valobject793 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_103_in_inCondition809 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_inCondition813 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_subquery_in_inCondition816 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_inCondition822 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_inCondition_expr_adds_in_inCondition824 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_inCondition826 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_103_in_likeCondition853 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_likeCondition854 = new BitSet(new long[]{0x4102200202000200L,0x0000000000002000L});
	public static final BitSet FOLLOW_value_in_likeCondition857 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_100_in_likeCondition867 = new BitSet(new long[]{0x4102200202000200L,0x0000000000002000L});
	public static final BitSet FOLLOW_value_in_likeCondition869 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_add_in_inCondition_expr_adds886 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_COMMA_in_inCondition_expr_adds888 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_add_in_inCondition_expr_adds890 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_columnNameAfterWhere_in_identifiers910 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_COMMA_in_identifiers913 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_identifier_in_identifiers915 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_relational_op_in_comparisonCondition927 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_in_comparisonCondition929 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_add_in_expr948 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subquery_in_expr953 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_subquery969 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_select_command_in_subquery971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_subquery973 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_mul_in_expr_add989 = new BitSet(new long[]{0x0800800004000002L});
	public static final BitSet FOLLOW_PLUS_in_expr_add995 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_MINUS_in_expr_add1000 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_DOUBLEVERTBAR_in_expr_add1005 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_mul_in_expr_add1011 = new BitSet(new long[]{0x0800800004000002L});
	public static final BitSet FOLLOW_expr_sign_in_expr_mul1031 = new BitSet(new long[]{0x0001000000800202L});
	public static final BitSet FOLLOW_ASTERISK_in_expr_mul1037 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_DIVIDE_in_expr_mul1042 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_MOD_in_expr_mul1047 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_sign_in_expr_mul1052 = new BitSet(new long[]{0x0001000000800202L});
	public static final BitSet FOLLOW_PLUS_in_expr_sign1067 = new BitSet(new long[]{0x4102200202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_MINUS_in_expr_sign1072 = new BitSet(new long[]{0x4102200202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_pow_in_expr_sign1078 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_expr_in_expr_pow1088 = new BitSet(new long[]{0x0000000010000002L});
	public static final BitSet FOLLOW_EXPONENT_in_expr_pow1092 = new BitSet(new long[]{0x4102200202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_expr_in_expr_pow1095 = new BitSet(new long[]{0x0000000010000002L});
	public static final BitSet FOLLOW_value_in_expr_expr1108 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_boolean_literal_in_expr_expr1112 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_104_in_expr_expr1116 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_109_in_expr_expr1120 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_expr_expr1128 = new BitSet(new long[]{0x0000200000000002L});
	public static final BitSet FOLLOW_LPAREN_in_expr_expr1132 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402002L});
	public static final BitSet FOLLOW_values_func_in_expr_expr1134 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_expr_expr1137 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_condition_or_in_sql_condition1159 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_88_in_fromClause1199 = new BitSet(new long[]{0x0000200200000200L});
	public static final BitSet FOLLOW_selected_table_in_fromClause1202 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_displayed_column_in_select_list1213 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_COMMA_in_select_list1217 = new BitSet(new long[]{0x4902A00202000200L,0x00012100004C2000L});
	public static final BitSet FOLLOW_displayed_column_in_select_list1219 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_quoted_string_in_displayed_column1242 = new BitSet(new long[]{0x0000000200000202L,0x0000000000008000L});
	public static final BitSet FOLLOW_alias_in_displayed_column1244 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_concat_in_displayed_column1258 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_displayed_column1260 = new BitSet(new long[]{0x4000000202000200L});
	public static final BitSet FOLLOW_identifiedOrQuotedString_in_displayed_column1262 = new BitSet(new long[]{0x0000000000004000L,0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_displayed_column1265 = new BitSet(new long[]{0x4000000202000200L});
	public static final BitSet FOLLOW_identifiedOrQuotedString_in_displayed_column1267 = new BitSet(new long[]{0x0000000000004000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_displayed_column1271 = new BitSet(new long[]{0x0000000200000202L,0x0000000000008000L});
	public static final BitSet FOLLOW_alias_in_displayed_column1273 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_count_in_displayed_column1294 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_displayed_column1296 = new BitSet(new long[]{0x0002200200000200L,0x0000000000200000L});
	public static final BitSet FOLLOW_distinct_in_displayed_column1298 = new BitSet(new long[]{0x0002200200000200L});
	public static final BitSet FOLLOW_LPAREN_in_displayed_column1301 = new BitSet(new long[]{0x0002000200000200L});
	public static final BitSet FOLLOW_countColumn_in_displayed_column1304 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_displayed_column1306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_displayed_column1309 = new BitSet(new long[]{0x0000000200000202L,0x0000000000008000L});
	public static final BitSet FOLLOW_alias_in_displayed_column1311 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_add_in_displayed_column1328 = new BitSet(new long[]{0x0000000200000202L,0x0000000000008000L});
	public static final BitSet FOLLOW_alias_in_displayed_column1330 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_displayed_column1348 = new BitSet(new long[]{0x0000200200000202L,0x0000000000008000L});
	public static final BitSet FOLLOW_LPAREN_in_displayed_column1352 = new BitSet(new long[]{0x0000000200000200L,0x0000000000000002L});
	public static final BitSet FOLLOW_table_alias_in_displayed_column1354 = new BitSet(new long[]{0x0000000200000200L,0x0000000000000002L});
	public static final BitSet FOLLOW_column_in_displayed_column1357 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_displayed_column1360 = new BitSet(new long[]{0x0000000200000202L,0x0000000000008000L});
	public static final BitSet FOLLOW_alias_in_displayed_column1365 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_alias_in_columnNameAfterWhere1390 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_identifier_in_columnNameAfterWhere1393 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_alias_in_columnNameAfterWhere1408 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_identifier_in_columnNameAfterWhere1411 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ASC_in_columnNameAfterWhere1414 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_alias_in_columnNameAfterWhere1429 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_identifier_in_columnNameAfterWhere1432 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_DESC_in_columnNameAfterWhere1435 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_alias_in_columnNameInUpdate1456 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_identifier_in_columnNameInUpdate1459 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_table_alias1469 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_DOT_in_table_alias1471 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ASTERISK_in_column1487 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_column1491 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_values1500 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_COMMA_in_values1504 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_in_values1506 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_expr_in_values_func1525 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_COMMA_in_values_func1529 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_in_values_func1532 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_N_in_value1546 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_value1550 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_value1554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_value1558 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_in_value1561 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_value1563 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quoted_string_in_value1568 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_spec_in_value1579 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_N_in_value_simple1589 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_value_simple1593 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_value_simple1597 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_value_simple1601 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_in_value_simple1603 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_value_simple1605 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quoted_string_in_value_simple1609 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_spec_in_value_simple1620 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_spec_in_column_specs1631 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_COMMA_in_column_specs1635 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_column_spec_in_column_specs1637 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_a_table_in_selected_table1658 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_COMMA_in_selected_table1661 = new BitSet(new long[]{0x0000200200000200L});
	public static final BitSet FOLLOW_a_table_in_selected_table1663 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_table_spec_in_a_table1681 = new BitSet(new long[]{0x0000000200000202L,0x0000000000008000L});
	public static final BitSet FOLLOW_alias_in_a_table1684 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_schema_name_in_table_spec1706 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_DOT_in_table_spec1708 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_table_name_in_table_spec1713 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subquery_in_table_spec1719 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_table_name1730 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_name_in_column_spec1742 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_DOT_in_column_spec1744 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_identifier_in_column_spec1748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_schema_name1768 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_79_in_alias1798 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_identifier_in_alias1802 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selectClause_in_select_command2351 = new BitSet(new long[]{0x0000000000000002L,0x0008182C47800000L});
	public static final BitSet FOLLOW_fromClause_in_select_command2354 = new BitSet(new long[]{0x0000000000000002L,0x0008182C46800000L});
	public static final BitSet FOLLOW_joinClause_in_select_command2359 = new BitSet(new long[]{0x0000000000000002L,0x0008182C46800000L});
	public static final BitSet FOLLOW_whereClause_in_select_command2364 = new BitSet(new long[]{0x0000000000000002L,0x0000082006800000L});
	public static final BitSet FOLLOW_groupByClause_in_select_command2369 = new BitSet(new long[]{0x0000000000000002L,0x0000082004800000L});
	public static final BitSet FOLLOW_orderByClause_in_select_command2374 = new BitSet(new long[]{0x0000000000000002L,0x0000002004800000L});
	public static final BitSet FOLLOW_limitClause_in_select_command2379 = new BitSet(new long[]{0x0000000000000002L,0x0000000004800000L});
	public static final BitSet FOLLOW_indexClause_in_select_command2383 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_89_in_groupByClause2398 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_groupByColumns_in_groupByClause2400 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_groupByColumn_in_groupByColumns2416 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_COMMA_in_groupByColumns2419 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_groupByColumn_in_groupByColumns2422 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_identifier_in_groupByColumn2434 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_87_in_indexClause2445 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_indexClause2447 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_indexClause2449 = new BitSet(new long[]{0x4902A00202000200L,0x00012100004C2000L});
	public static final BitSet FOLLOW_select_list_in_indexClause2451 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_indexClause2454 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_90_in_indexClause2459 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_indexClause2461 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_LPAREN_in_indexClause2463 = new BitSet(new long[]{0x4902A00202000200L,0x00012100004C2000L});
	public static final BitSet FOLLOW_select_list_in_indexClause2465 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_indexClause2467 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_84_in_delete_command2477 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_fromClause_in_delete_command2479 = new BitSet(new long[]{0x0000000000000002L,0x0008082000000000L});
	public static final BitSet FOLLOW_whereClause_in_delete_command2481 = new BitSet(new long[]{0x0000000000000002L,0x0000082000000000L});
	public static final BitSet FOLLOW_orderByClause_in_delete_command2484 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_limitClause_in_delete_command2489 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_113_in_update_command2515 = new BitSet(new long[]{0x0000200200000200L});
	public static final BitSet FOLLOW_selected_table_in_update_command2517 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_setclause_in_update_command2520 = new BitSet(new long[]{0x0000000000000002L,0x0008002000000000L});
	public static final BitSet FOLLOW_whereClause_in_update_command2522 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_limitClause_in_update_command2527 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_101_in_limitClause2552 = new BitSet(new long[]{0x0002000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_skip_in_limitClause2555 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_COMMA_in_limitClause2557 = new BitSet(new long[]{0x0002000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_range_in_limitClause2562 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_N_in_skip2581 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_skip2592 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_N_in_range2606 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_range2616 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_joinType_in_joinClause2632 = new BitSet(new long[]{0x0000200200000200L});
	public static final BitSet FOLLOW_table_spec_in_joinClause2634 = new BitSet(new long[]{0x0000000200000200L,0x0000020000008000L});
	public static final BitSet FOLLOW_alias_in_joinClause2636 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_joinClause2639 = new BitSet(new long[]{0x4902A00202000200L,0x0001218000402000L});
	public static final BitSet FOLLOW_sqlCondition_in_joinClause2641 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_concat2686 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_alias_in_identifiedOrQuotedString2699 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_identifier_in_identifiedOrQuotedString2702 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quoted_string_in_identifiedOrQuotedString2707 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_85_in_distinct2719 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_count2729 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_identifierOrN2741 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_N_in_identifierOrN2745 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_countColumn2757 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_DOT_in_countColumn2759 = new BitSet(new long[]{0x0002000200000200L});
	public static final BitSet FOLLOW_identifierOrN_in_countColumn2763 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_synpred1_MySQLParser574 = new BitSet(new long[]{0x4902A00202000200L,0x0001210000402000L});
	public static final BitSet FOLLOW_condition_or_in_synpred1_MySQLParser576 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_RPAREN_in_synpred1_MySQLParser578 = new BitSet(new long[]{0x0000000000000002L});
}
