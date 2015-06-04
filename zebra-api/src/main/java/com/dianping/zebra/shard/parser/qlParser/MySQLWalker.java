// $ANTLR 3.5.2 MySQLWalker.g 2015-06-04 10:27:52

package com.dianping.zebra.shard.parser.qlParser;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Stack;

import static com.dianping.zebra.shard.parser.util.DbFunctions.add;
import static com.dianping.zebra.shard.parser.util.DbFunctions.column;
import static com.dianping.zebra.shard.parser.util.DbFunctions.divide;
import static com.dianping.zebra.shard.parser.util.DbFunctions.mod;
import static com.dianping.zebra.shard.parser.util.DbFunctions.multiply;
import static com.dianping.zebra.shard.parser.util.DbFunctions.subtract;

import com.dianping.zebra.shard.parser.condition.BetweenPair;
import com.dianping.zebra.shard.parser.condition.BindIndexHolder;
import com.dianping.zebra.shard.parser.condition.ExpressionGroup;
import com.dianping.zebra.shard.parser.condition.OrExpressionGroup;
import com.dianping.zebra.shard.parser.condition.WhereCondition;
import com.dianping.zebra.shard.parser.sqlParser.DMLCommon;
import com.dianping.zebra.shard.parser.sqlParser.Delete;
import com.dianping.zebra.shard.parser.sqlParser.Insert;
import com.dianping.zebra.shard.parser.sqlParser.Select;
import com.dianping.zebra.shard.parser.sqlParser.Update;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.Concat;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.Count;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.GroupFunction;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.GroupFunctionRegister;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MyDelete;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MySelect;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MyUpdate;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MyWhereCondition;
import com.dianping.zebra.shard.parser.util.DbFunctions;
import com.dianping.zebra.shard.parser.valueObject.ColumnObject;
import com.dianping.zebra.shard.parser.valueObject.FunctionConvertor;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;
import java.util.HashMap;
@SuppressWarnings("all")
public class MySQLWalker extends TreeParser {
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
	public TreeParser[] getDelegates() {
		return new TreeParser[] {};
	}

	// delegators


	public MySQLWalker(TreeNodeStream input) {
		this(input, new RecognizerSharedState());
	}
	public MySQLWalker(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected StringTemplateGroup templateLib =
	  new StringTemplateGroup("MySQLWalkerTemplates", AngleBracketTemplateLexer.class);

	public void setTemplateLib(StringTemplateGroup templateLib) {
	  this.templateLib = templateLib;
	}
	public StringTemplateGroup getTemplateLib() {
	  return templateLib;
	}
	/** allows convenient multi-value initialization:
	 *  "new STAttrMap().put(...).put(...)"
	 */
	@SuppressWarnings("serial")
	public static class STAttrMap extends HashMap<String, Object> {
		public STAttrMap put(String attrName, Object value) {
			super.put(attrName, value);
			return this;
		}
	}
	@Override public String[] getTokenNames() { return MySQLWalker.tokenNames; }
	@Override public String getGrammarFileName() { return "MySQLWalker.g"; }


			protected  GroupFunctionRegister groupFunc;
			public void setGroupFunc(GroupFunctionRegister groupFunc){
			this.groupFunc=groupFunc;
			}
			public GroupFunctionRegister getGroupFunc(){
			return groupFunc;
			}
		protected  Map<String, FunctionConvertor> functionMap;
		public void setFunctionMap(Map<String, FunctionConvertor> functionMap){
		this.functionMap=functionMap;
		}
		public Map<String, FunctionConvertor> getFunctionMap(){
		return functionMap;
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
	    public String mq(String name) {
	    	return name + "0";
	    }
	    private boolean needToRewriteTableName = false;
	    public Map<Integer, String> pos2TableName = new LinkedHashMap<Integer, String>();


	public static class beg_return extends TreeRuleReturnScope {
		public DMLCommon obj;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "beg"
	// MySQLWalker.g:79:1: beg returns [DMLCommon obj] : start_rule ;
	public final MySQLWalker.beg_return beg() throws RecognitionException {
		MySQLWalker.beg_return retval = new MySQLWalker.beg_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope start_rule1 =null;

		try {
			// MySQLWalker.g:79:27: ( start_rule )
			// MySQLWalker.g:80:1: start_rule
			{
			pushFollow(FOLLOW_start_rule_in_beg53);
			start_rule1=start_rule();
			state._fsp--;

			retval.obj =(start_rule1!=null?((MySQLWalker.start_rule_return)start_rule1).obj:null);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "beg"


	public static class start_rule_return extends TreeRuleReturnScope {
		public DMLCommon obj;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "start_rule"
	// MySQLWalker.g:83:1: start_rule returns [DMLCommon obj] : ( select_command | insert_command | update_command | delete_command );
	public final MySQLWalker.start_rule_return start_rule() throws RecognitionException {
		MySQLWalker.start_rule_return retval = new MySQLWalker.start_rule_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope select_command2 =null;
		TreeRuleReturnScope insert_command3 =null;
		TreeRuleReturnScope update_command4 =null;
		TreeRuleReturnScope delete_command5 =null;

		try {
			// MySQLWalker.g:84:2: ( select_command | insert_command | update_command | delete_command )
			int alt1=4;
			switch ( input.LA(1) ) {
			case SELECT:
				{
				alt1=1;
				}
				break;
			case INSERT:
				{
				alt1=2;
				}
				break;
			case UPDATE:
				{
				alt1=3;
				}
				break;
			case DELETE:
				{
				alt1=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// MySQLWalker.g:84:3: select_command
					{
					pushFollow(FOLLOW_select_command_in_start_rule70);
					select_command2=select_command();
					state._fsp--;

					retval.obj =(select_command2!=null?((MySQLWalker.select_command_return)select_command2).select:null);
					}
					break;
				case 2 :
					// MySQLWalker.g:85:3: insert_command
					{
					pushFollow(FOLLOW_insert_command_in_start_rule75);
					insert_command3=insert_command();
					state._fsp--;

					retval.obj =(insert_command3!=null?((MySQLWalker.insert_command_return)insert_command3).ins:null);
					}
					break;
				case 3 :
					// MySQLWalker.g:86:3: update_command
					{
					pushFollow(FOLLOW_update_command_in_start_rule80);
					update_command4=update_command();
					state._fsp--;

					retval.obj =(update_command4!=null?((MySQLWalker.update_command_return)update_command4).update:null);
					}
					break;
				case 4 :
					// MySQLWalker.g:87:3: delete_command
					{
					pushFollow(FOLLOW_delete_command_in_start_rule85);
					delete_command5=delete_command();
					state._fsp--;

					retval.obj =(delete_command5!=null?((MySQLWalker.delete_command_return)delete_command5).del:null);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "start_rule"


	public static class setclause_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "setclause"
	// MySQLWalker.g:90:1: setclause[Update update] : ^( SET ( updateColumnSpecs[$update] )+ ) ;
	public final MySQLWalker.setclause_return setclause(Update update) throws RecognitionException {
		MySQLWalker.setclause_return retval = new MySQLWalker.setclause_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:91:2: ( ^( SET ( updateColumnSpecs[$update] )+ ) )
			// MySQLWalker.g:91:3: ^( SET ( updateColumnSpecs[$update] )+ )
			{
			match(input,SET,FOLLOW_SET_in_setclause98); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:91:9: ( updateColumnSpecs[$update] )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==SET_ELE) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// MySQLWalker.g:91:9: updateColumnSpecs[$update]
					{
					pushFollow(FOLLOW_updateColumnSpecs_in_setclause100);
					updateColumnSpecs(update);
					state._fsp--;

					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "setclause"


	public static class updateColumnSpecs_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "updateColumnSpecs"
	// MySQLWalker.g:93:1: updateColumnSpecs[Update update] : ^( SET_ELE updateColumnSpec[$update] ) ;
	public final MySQLWalker.updateColumnSpecs_return updateColumnSpecs(Update update) throws RecognitionException {
		MySQLWalker.updateColumnSpecs_return retval = new MySQLWalker.updateColumnSpecs_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:94:2: ( ^( SET_ELE updateColumnSpec[$update] ) )
			// MySQLWalker.g:94:3: ^( SET_ELE updateColumnSpec[$update] )
			{
			match(input,SET_ELE,FOLLOW_SET_ELE_in_updateColumnSpecs115); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_updateColumnSpec_in_updateColumnSpecs117);
			updateColumnSpec(update);
			state._fsp--;

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "updateColumnSpecs"


	public static class updateColumnSpec_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "updateColumnSpec"
	// MySQLWalker.g:96:1: updateColumnSpec[Update update] : ^( EQ ( table_alias )? identifier expr[$update] ) ;
	public final MySQLWalker.updateColumnSpec_return updateColumnSpec(Update update) throws RecognitionException {
		MySQLWalker.updateColumnSpec_return retval = new MySQLWalker.updateColumnSpec_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope identifier6 =null;
		TreeRuleReturnScope table_alias7 =null;
		TreeRuleReturnScope expr8 =null;

		try {
			// MySQLWalker.g:97:2: ( ^( EQ ( table_alias )? identifier expr[$update] ) )
			// MySQLWalker.g:97:3: ^( EQ ( table_alias )? identifier expr[$update] )
			{
			match(input,EQ,FOLLOW_EQ_in_updateColumnSpec131); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:97:8: ( table_alias )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==COL_TAB) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// MySQLWalker.g:97:8: table_alias
					{
					pushFollow(FOLLOW_table_alias_in_updateColumnSpec133);
					table_alias7=table_alias();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_identifier_in_updateColumnSpec136);
			identifier6=identifier();
			state._fsp--;

			pushFollow(FOLLOW_expr_in_updateColumnSpec138);
			expr8=expr(update);
			state._fsp--;

			match(input, Token.UP, null); 


				update.addSetElement((identifier6!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier6.start),input.getTreeAdaptor().getTokenStopIndex(identifier6.start))):null),(table_alias7!=null?((MySQLWalker.table_alias_return)table_alias7).aText:null),(expr8!=null?((MySQLWalker.expr_return)expr8).valueObj:null));
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "updateColumnSpec"


	public static class insert_command_return extends TreeRuleReturnScope {
		public Insert ins;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "insert_command"
	// MySQLWalker.g:103:1: insert_command returns [Insert ins] : ^( INSERT ( IGNORE )? tables[$ins, true] ( column_specs[$ins] )* values[$ins] ) ;
	public final MySQLWalker.insert_command_return insert_command() throws RecognitionException {
		MySQLWalker.insert_command_return retval = new MySQLWalker.insert_command_return();
		retval.start = input.LT(1);

		retval.ins =new Insert();
		try {
			// MySQLWalker.g:105:2: ( ^( INSERT ( IGNORE )? tables[$ins, true] ( column_specs[$ins] )* values[$ins] ) )
			// MySQLWalker.g:105:3: ^( INSERT ( IGNORE )? tables[$ins, true] ( column_specs[$ins] )* values[$ins] )
			{
			match(input,INSERT,FOLLOW_INSERT_in_insert_command161); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:105:12: ( IGNORE )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==IGNORE) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// MySQLWalker.g:105:13: IGNORE
					{
					match(input,IGNORE,FOLLOW_IGNORE_in_insert_command164); 
					}
					break;

			}

			pushFollow(FOLLOW_tables_in_insert_command168);
			tables(retval.ins, true);
			state._fsp--;

			// MySQLWalker.g:105:41: ( column_specs[$ins] )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==COLUMNS) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// MySQLWalker.g:105:41: column_specs[$ins]
					{
					pushFollow(FOLLOW_column_specs_in_insert_command171);
					column_specs(retval.ins);
					state._fsp--;

					}
					break;

				default :
					break loop5;
				}
			}

			pushFollow(FOLLOW_values_in_insert_command175);
			values(retval.ins);
			state._fsp--;

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insert_command"


	public static class values_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "values"
	// MySQLWalker.g:107:1: values[Insert ins] : ^( INSERT_VAL ( expr[$ins] )* ) ;
	public final MySQLWalker.values_return values(Insert ins) throws RecognitionException {
		MySQLWalker.values_return retval = new MySQLWalker.values_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope expr9 =null;

		try {
			// MySQLWalker.g:107:20: ( ^( INSERT_VAL ( expr[$ins] )* ) )
			// MySQLWalker.g:107:21: ^( INSERT_VAL ( expr[$ins] )* )
			{
			match(input,INSERT_VAL,FOLLOW_INSERT_VAL_in_values187); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// MySQLWalker.g:107:34: ( expr[$ins] )*
				loop6:
				while (true) {
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( (LA6_0==ASTERISK||LA6_0==COLUMN||LA6_0==DIVIDE||LA6_0==ID||(LA6_0 >= MINUS && LA6_0 <= N)||LA6_0==NUMBER||LA6_0==PLUS||LA6_0==QUTED_STR||LA6_0==SUBQUERY||LA6_0==77||LA6_0==86||LA6_0==104||LA6_0==109||LA6_0==112) ) {
						alt6=1;
					}

					switch (alt6) {
					case 1 :
						// MySQLWalker.g:107:35: expr[$ins]
						{
						pushFollow(FOLLOW_expr_in_values190);
						expr9=expr(ins);
						state._fsp--;

						ins.addValue((expr9!=null?((MySQLWalker.expr_return)expr9).valueObj:null));
						}
						break;

					default :
						break loop6;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "values"


	public static class column_specs_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "column_specs"
	// MySQLWalker.g:109:1: column_specs[Insert ins] : ^( COLUMNS ( column_spec[$ins] )+ ) ;
	public final MySQLWalker.column_specs_return column_specs(Insert ins) throws RecognitionException {
		MySQLWalker.column_specs_return retval = new MySQLWalker.column_specs_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:110:2: ( ^( COLUMNS ( column_spec[$ins] )+ ) )
			// MySQLWalker.g:110:3: ^( COLUMNS ( column_spec[$ins] )+ )
			{
			match(input,COLUMNS,FOLLOW_COLUMNS_in_column_specs206); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:110:13: ( column_spec[$ins] )+
			int cnt7=0;
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==COLUMN) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// MySQLWalker.g:110:13: column_spec[$ins]
					{
					pushFollow(FOLLOW_column_spec_in_column_specs208);
					column_spec(ins);
					state._fsp--;

					}
					break;

				default :
					if ( cnt7 >= 1 ) break loop7;
					EarlyExitException eee = new EarlyExitException(7, input);
					throw eee;
				}
				cnt7++;
			}

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column_specs"


	public static class column_spec_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "column_spec"
	// MySQLWalker.g:112:1: column_spec[Insert ins] : ^( COLUMN identifier ( table_name[false] )? ) ;
	public final MySQLWalker.column_spec_return column_spec(Insert ins) throws RecognitionException {
		MySQLWalker.column_spec_return retval = new MySQLWalker.column_spec_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope table_name10 =null;
		TreeRuleReturnScope identifier11 =null;

		try {
			// MySQLWalker.g:113:2: ( ^( COLUMN identifier ( table_name[false] )? ) )
			// MySQLWalker.g:113:3: ^( COLUMN identifier ( table_name[false] )? )
			{
			match(input,COLUMN,FOLLOW_COLUMN_in_column_spec222); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_identifier_in_column_spec224);
			identifier11=identifier();
			state._fsp--;

			// MySQLWalker.g:113:23: ( table_name[false] )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==ASTERISK||LA8_0==ID) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// MySQLWalker.g:113:23: table_name[false]
					{
					pushFollow(FOLLOW_table_name_in_column_spec226);
					table_name10=table_name(false);
					state._fsp--;

					}
					break;

			}

			match(input, Token.UP, null); 


					ins.addColumnTandC((table_name10!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(table_name10.start),input.getTreeAdaptor().getTokenStopIndex(table_name10.start))):null),(identifier11!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier11.start),input.getTreeAdaptor().getTokenStopIndex(identifier11.start))):null));
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column_spec"


	public static class whereClause_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "whereClause"
	// MySQLWalker.g:121:1: whereClause[WhereCondition where] : ^( WHERE sqlCondition[$where] ) ;
	public final MySQLWalker.whereClause_return whereClause(WhereCondition where) throws RecognitionException {
		MySQLWalker.whereClause_return retval = new MySQLWalker.whereClause_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:122:2: ( ^( WHERE sqlCondition[$where] ) )
			// MySQLWalker.g:122:3: ^( WHERE sqlCondition[$where] )
			{
			match(input,WHERE,FOLLOW_WHERE_in_whereClause246); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_sqlCondition_in_whereClause248);
			sqlCondition(where);
			state._fsp--;

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "whereClause"


	public static class orderByClause_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "orderByClause"
	// MySQLWalker.g:125:1: orderByClause[WhereCondition where] : ^( ORDERBY columnNamesAfterWhere[$where] ) ;
	public final MySQLWalker.orderByClause_return orderByClause(WhereCondition where) throws RecognitionException {
		MySQLWalker.orderByClause_return retval = new MySQLWalker.orderByClause_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:126:2: ( ^( ORDERBY columnNamesAfterWhere[$where] ) )
			// MySQLWalker.g:126:3: ^( ORDERBY columnNamesAfterWhere[$where] )
			{
			match(input,ORDERBY,FOLLOW_ORDERBY_in_orderByClause263); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_columnNamesAfterWhere_in_orderByClause265);
			columnNamesAfterWhere(where);
			state._fsp--;

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "orderByClause"


	public static class columnNamesAfterWhere_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "columnNamesAfterWhere"
	// MySQLWalker.g:128:1: columnNamesAfterWhere[WhereCondition where] : ( columnNameAfterWhere[$where] )+ ;
	public final MySQLWalker.columnNamesAfterWhere_return columnNamesAfterWhere(WhereCondition where) throws RecognitionException {
		MySQLWalker.columnNamesAfterWhere_return retval = new MySQLWalker.columnNamesAfterWhere_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:129:2: ( ( columnNameAfterWhere[$where] )+ )
			// MySQLWalker.g:129:3: ( columnNameAfterWhere[$where] )+
			{
			// MySQLWalker.g:129:3: ( columnNameAfterWhere[$where] )+
			int cnt9=0;
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==ASC||LA9_0==DESC) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// MySQLWalker.g:129:4: columnNameAfterWhere[$where]
					{
					pushFollow(FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere278);
					columnNameAfterWhere(where);
					state._fsp--;

					}
					break;

				default :
					if ( cnt9 >= 1 ) break loop9;
					EarlyExitException eee = new EarlyExitException(9, input);
					throw eee;
				}
				cnt9++;
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnNamesAfterWhere"


	public static class columnNameAfterWhere_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "columnNameAfterWhere"
	// MySQLWalker.g:131:1: columnNameAfterWhere[WhereCondition where] : ( ^( ASC identifier ( table_alias )? ) | ^( DESC identifier ( table_alias )? ) );
	public final MySQLWalker.columnNameAfterWhere_return columnNameAfterWhere(WhereCondition where) throws RecognitionException {
		MySQLWalker.columnNameAfterWhere_return retval = new MySQLWalker.columnNameAfterWhere_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope table_alias12 =null;
		TreeRuleReturnScope identifier13 =null;
		TreeRuleReturnScope table_alias14 =null;
		TreeRuleReturnScope identifier15 =null;

		try {
			// MySQLWalker.g:132:2: ( ^( ASC identifier ( table_alias )? ) | ^( DESC identifier ( table_alias )? ) )
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==ASC) ) {
				alt12=1;
			}
			else if ( (LA12_0==DESC) ) {
				alt12=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// MySQLWalker.g:132:3: ^( ASC identifier ( table_alias )? )
					{
					match(input,ASC,FOLLOW_ASC_in_columnNameAfterWhere292); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_identifier_in_columnNameAfterWhere295);
					identifier13=identifier();
					state._fsp--;

					// MySQLWalker.g:132:21: ( table_alias )?
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( (LA10_0==COL_TAB) ) {
						alt10=1;
					}
					switch (alt10) {
						case 1 :
							// MySQLWalker.g:132:21: table_alias
							{
							pushFollow(FOLLOW_table_alias_in_columnNameAfterWhere297);
							table_alias12=table_alias();
							state._fsp--;

							}
							break;

					}

					match(input, Token.UP, null); 


							where.addOrderByColumn((table_alias12!=null?((MySQLWalker.table_alias_return)table_alias12).aText:null),(identifier13!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier13.start),input.getTreeAdaptor().getTokenStopIndex(identifier13.start))):null),true);
						
					}
					break;
				case 2 :
					// MySQLWalker.g:135:3: ^( DESC identifier ( table_alias )? )
					{
					match(input,DESC,FOLLOW_DESC_in_columnNameAfterWhere305); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_identifier_in_columnNameAfterWhere308);
					identifier15=identifier();
					state._fsp--;

					// MySQLWalker.g:135:22: ( table_alias )?
					int alt11=2;
					int LA11_0 = input.LA(1);
					if ( (LA11_0==COL_TAB) ) {
						alt11=1;
					}
					switch (alt11) {
						case 1 :
							// MySQLWalker.g:135:22: table_alias
							{
							pushFollow(FOLLOW_table_alias_in_columnNameAfterWhere310);
							table_alias14=table_alias();
							state._fsp--;

							}
							break;

					}

					match(input, Token.UP, null); 


							where.addOrderByColumn((table_alias14!=null?((MySQLWalker.table_alias_return)table_alias14).aText:null),(identifier15!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier15.start),input.getTreeAdaptor().getTokenStopIndex(identifier15.start))):null),false);
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnNameAfterWhere"


	public static class selectClause_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "selectClause"
	// MySQLWalker.g:139:1: selectClause[Select select] : ^( SELECT ( distinct )? select_list[$select] ) ;
	public final MySQLWalker.selectClause_return selectClause(Select select) throws RecognitionException {
		MySQLWalker.selectClause_return retval = new MySQLWalker.selectClause_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope distinct16 =null;

		try {
			// MySQLWalker.g:140:5: ( ^( SELECT ( distinct )? select_list[$select] ) )
			// MySQLWalker.g:140:6: ^( SELECT ( distinct )? select_list[$select] )
			{
			match(input,SELECT,FOLLOW_SELECT_in_selectClause327); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:140:15: ( distinct )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==85) ) {
				alt13=1;
			}
			switch (alt13) {
				case 1 :
					// MySQLWalker.g:140:15: distinct
					{
					pushFollow(FOLLOW_distinct_in_selectClause329);
					distinct16=distinct();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_select_list_in_selectClause332);
			select_list(select);
			state._fsp--;

			match(input, Token.UP, null); 


			    	select.setHasDistinct((distinct16!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(distinct16.start),input.getTreeAdaptor().getTokenStopIndex(distinct16.start))):null) != null);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectClause"


	public static class sqlCondition_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "sqlCondition"
	// MySQLWalker.g:145:1: sqlCondition[WhereCondition where] : ( ^( CONDITION_OR_NOT condition[where.getHolder(),where.getExpGroup(),false] ) | ^( CONDITION_OR condition[where.getHolder(),where.getExpGroup(),false] ) );
	public final MySQLWalker.sqlCondition_return sqlCondition(WhereCondition where) throws RecognitionException {
		MySQLWalker.sqlCondition_return retval = new MySQLWalker.sqlCondition_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:147:2: ( ^( CONDITION_OR_NOT condition[where.getHolder(),where.getExpGroup(),false] ) | ^( CONDITION_OR condition[where.getHolder(),where.getExpGroup(),false] ) )
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==CONDITION_OR_NOT) ) {
				alt14=1;
			}
			else if ( (LA14_0==CONDITION_OR) ) {
				alt14=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}

			switch (alt14) {
				case 1 :
					// MySQLWalker.g:147:3: ^( CONDITION_OR_NOT condition[where.getHolder(),where.getExpGroup(),false] )
					{
					match(input,CONDITION_OR_NOT,FOLLOW_CONDITION_OR_NOT_in_sqlCondition355); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_condition_in_sqlCondition357);
					condition(where.getHolder(), where.getExpGroup(), false);
					state._fsp--;

					match(input, Token.UP, null); 

					}
					break;
				case 2 :
					// MySQLWalker.g:148:3: ^( CONDITION_OR condition[where.getHolder(),where.getExpGroup(),false] )
					{
					match(input,CONDITION_OR,FOLLOW_CONDITION_OR_in_sqlCondition364); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_condition_in_sqlCondition366);
					condition(where.getHolder(), where.getExpGroup(), false);
					state._fsp--;

					match(input, Token.UP, null); 

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sqlCondition"


	public static class condition_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "condition"
	// MySQLWalker.g:151:1: condition[BindIndexHolder where,ExpressionGroup eg,boolean isPriority] : ( ^( 'OR' (s1= condition[where,orExp,$isPriority] )+ ) | ^( 'AND' ( condition[where,andExp,$isPriority] )+ ) | condition_PAREN[where,$eg] | ^( PRIORITY condition[where,$eg,true] ) );
	public final MySQLWalker.condition_return condition(BindIndexHolder where, ExpressionGroup eg, boolean isPriority) throws RecognitionException {
		MySQLWalker.condition_return retval = new MySQLWalker.condition_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope s1 =null;

		try {
			// MySQLWalker.g:152:2: ( ^( 'OR' (s1= condition[where,orExp,$isPriority] )+ ) | ^( 'AND' ( condition[where,andExp,$isPriority] )+ ) | condition_PAREN[where,$eg] | ^( PRIORITY condition[where,$eg,true] ) )
			int alt17=4;
			switch ( input.LA(1) ) {
			case 106:
				{
				alt17=1;
				}
				break;
			case 78:
				{
				alt17=2;
				}
				break;
			case BETWEEN:
			case EQ:
			case GEQ:
			case GTH:
			case IN:
			case IS:
			case ISNOT:
			case LEQ:
			case LIKE:
			case LTH:
			case NOT_BETWEEN:
			case NOT_EQ:
			case NOT_LIKE:
				{
				alt17=3;
				}
				break;
			case PRIORITY:
				{
				alt17=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// MySQLWalker.g:153:2: ^( 'OR' (s1= condition[where,orExp,$isPriority] )+ )
					{

							OrExpressionGroup orExp=new OrExpressionGroup();
							eg.addExpressionGroup(orExp);
							orExp.setPriorty(isPriority);
						
					match(input,106,FOLLOW_106_in_condition384); 
					match(input, Token.DOWN, null); 
					// MySQLWalker.g:157:12: (s1= condition[where,orExp,$isPriority] )+
					int cnt15=0;
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0==BETWEEN||LA15_0==EQ||LA15_0==GEQ||LA15_0==GTH||LA15_0==IN||(LA15_0 >= IS && LA15_0 <= ISNOT)||(LA15_0 >= LEQ && LA15_0 <= LIKE)||LA15_0==LTH||(LA15_0 >= NOT_BETWEEN && LA15_0 <= NOT_LIKE)||LA15_0==PRIORITY||LA15_0==78||LA15_0==106) ) {
							alt15=1;
						}

						switch (alt15) {
						case 1 :
							// MySQLWalker.g:157:12: s1= condition[where,orExp,$isPriority]
							{
							pushFollow(FOLLOW_condition_in_condition388);
							s1=condition(where, orExp, isPriority);
							state._fsp--;

							}
							break;

						default :
							if ( cnt15 >= 1 ) break loop15;
							EarlyExitException eee = new EarlyExitException(15, input);
							throw eee;
						}
						cnt15++;
					}

					match(input, Token.UP, null); 

					}
					break;
				case 2 :
					// MySQLWalker.g:159:2: ^( 'AND' ( condition[where,andExp,$isPriority] )+ )
					{

							ExpressionGroup andExp=new ExpressionGroup();
							eg.addExpressionGroup(andExp);
							andExp.setPriorty(isPriority);
						
					match(input,78,FOLLOW_78_in_condition400); 
					match(input, Token.DOWN, null); 
					// MySQLWalker.g:163:11: ( condition[where,andExp,$isPriority] )+
					int cnt16=0;
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==BETWEEN||LA16_0==EQ||LA16_0==GEQ||LA16_0==GTH||LA16_0==IN||(LA16_0 >= IS && LA16_0 <= ISNOT)||(LA16_0 >= LEQ && LA16_0 <= LIKE)||LA16_0==LTH||(LA16_0 >= NOT_BETWEEN && LA16_0 <= NOT_LIKE)||LA16_0==PRIORITY||LA16_0==78||LA16_0==106) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// MySQLWalker.g:163:11: condition[where,andExp,$isPriority]
							{
							pushFollow(FOLLOW_condition_in_condition402);
							condition(where, andExp, isPriority);
							state._fsp--;

							}
							break;

						default :
							if ( cnt16 >= 1 ) break loop16;
							EarlyExitException eee = new EarlyExitException(16, input);
							throw eee;
						}
						cnt16++;
					}

					match(input, Token.UP, null); 

					}
					break;
				case 3 :
					// MySQLWalker.g:164:3: condition_PAREN[where,$eg]
					{
					pushFollow(FOLLOW_condition_PAREN_in_condition409);
					condition_PAREN(where, eg);
					state._fsp--;

					}
					break;
				case 4 :
					// MySQLWalker.g:165:3: ^( PRIORITY condition[where,$eg,true] )
					{
					match(input,PRIORITY,FOLLOW_PRIORITY_in_condition415); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_condition_in_condition417);
					condition(where, eg, true);
					state._fsp--;

					match(input, Token.UP, null); 

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "condition"


	public static class condition_PAREN_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "condition_PAREN"
	// MySQLWalker.g:185:1: condition_PAREN[BindIndexHolder where,ExpressionGroup exp] : ( condition_expr[$where,$exp] )+ ;
	public final MySQLWalker.condition_PAREN_return condition_PAREN(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
		MySQLWalker.condition_PAREN_return retval = new MySQLWalker.condition_PAREN_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:186:2: ( ( condition_expr[$where,$exp] )+ )
			// MySQLWalker.g:186:3: ( condition_expr[$where,$exp] )+
			{
			// MySQLWalker.g:186:3: ( condition_expr[$where,$exp] )+
			int cnt18=0;
			loop18:
			while (true) {
				int alt18=2;
				switch ( input.LA(1) ) {
				case EQ:
					{
					alt18=1;
					}
					break;
				case NOT_EQ:
					{
					alt18=1;
					}
					break;
				case LTH:
					{
					alt18=1;
					}
					break;
				case GTH:
					{
					alt18=1;
					}
					break;
				case LEQ:
					{
					alt18=1;
					}
					break;
				case GEQ:
					{
					alt18=1;
					}
					break;
				case IN:
					{
					alt18=1;
					}
					break;
				case ISNOT:
					{
					alt18=1;
					}
					break;
				case IS:
					{
					alt18=1;
					}
					break;
				case NOT_LIKE:
					{
					alt18=1;
					}
					break;
				case LIKE:
					{
					alt18=1;
					}
					break;
				case NOT_BETWEEN:
					{
					alt18=1;
					}
					break;
				case BETWEEN:
					{
					alt18=1;
					}
					break;
				}
				switch (alt18) {
				case 1 :
					// MySQLWalker.g:186:3: condition_expr[$where,$exp]
					{
					pushFollow(FOLLOW_condition_expr_in_condition_PAREN432);
					condition_expr(where, exp);
					state._fsp--;

					}
					break;

				default :
					if ( cnt18 >= 1 ) break loop18;
					EarlyExitException eee = new EarlyExitException(18, input);
					throw eee;
				}
				cnt18++;
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "condition_PAREN"


	public static class condition_expr_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "condition_expr"
	// MySQLWalker.g:188:1: condition_expr[BindIndexHolder where,ExpressionGroup exp] : ( comparisonCondition[$where,$exp] | inCondition[$where,$exp] | isCondition[$where,$exp] | likeCondition[$where,$exp] | betweenCondition[$where,$exp] );
	public final MySQLWalker.condition_expr_return condition_expr(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
		MySQLWalker.condition_expr_return retval = new MySQLWalker.condition_expr_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:189:2: ( comparisonCondition[$where,$exp] | inCondition[$where,$exp] | isCondition[$where,$exp] | likeCondition[$where,$exp] | betweenCondition[$where,$exp] )
			int alt19=5;
			switch ( input.LA(1) ) {
			case EQ:
			case GEQ:
			case GTH:
			case LEQ:
			case LTH:
			case NOT_EQ:
				{
				alt19=1;
				}
				break;
			case IN:
				{
				alt19=2;
				}
				break;
			case IS:
			case ISNOT:
				{
				alt19=3;
				}
				break;
			case LIKE:
			case NOT_LIKE:
				{
				alt19=4;
				}
				break;
			case BETWEEN:
			case NOT_BETWEEN:
				{
				alt19=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// MySQLWalker.g:189:4: comparisonCondition[$where,$exp]
					{
					pushFollow(FOLLOW_comparisonCondition_in_condition_expr445);
					comparisonCondition(where, exp);
					state._fsp--;

					}
					break;
				case 2 :
					// MySQLWalker.g:190:4: inCondition[$where,$exp]
					{
					pushFollow(FOLLOW_inCondition_in_condition_expr451);
					inCondition(where, exp);
					state._fsp--;

					}
					break;
				case 3 :
					// MySQLWalker.g:191:4: isCondition[$where,$exp]
					{
					pushFollow(FOLLOW_isCondition_in_condition_expr458);
					isCondition(where, exp);
					state._fsp--;

					}
					break;
				case 4 :
					// MySQLWalker.g:192:4: likeCondition[$where,$exp]
					{
					pushFollow(FOLLOW_likeCondition_in_condition_expr465);
					likeCondition(where, exp);
					state._fsp--;

					}
					break;
				case 5 :
					// MySQLWalker.g:193:4: betweenCondition[$where,$exp]
					{
					pushFollow(FOLLOW_betweenCondition_in_condition_expr471);
					betweenCondition(where, exp);
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "condition_expr"


	public static class betweenCondition_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "betweenCondition"
	// MySQLWalker.g:195:1: betweenCondition[BindIndexHolder where,ExpressionGroup exp] : ( ^( NOT_BETWEEN between_and[$where] left_cond[$where] ) | ^( BETWEEN between_and[$where] left_cond[$where] ) );
	public final MySQLWalker.betweenCondition_return betweenCondition(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
		MySQLWalker.betweenCondition_return retval = new MySQLWalker.betweenCondition_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope left_cond17 =null;
		TreeRuleReturnScope between_and18 =null;
		TreeRuleReturnScope left_cond19 =null;
		TreeRuleReturnScope between_and20 =null;

		try {
			// MySQLWalker.g:196:2: ( ^( NOT_BETWEEN between_and[$where] left_cond[$where] ) | ^( BETWEEN between_and[$where] left_cond[$where] ) )
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==NOT_BETWEEN) ) {
				alt20=1;
			}
			else if ( (LA20_0==BETWEEN) ) {
				alt20=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}

			switch (alt20) {
				case 1 :
					// MySQLWalker.g:196:3: ^( NOT_BETWEEN between_and[$where] left_cond[$where] )
					{
					match(input,NOT_BETWEEN,FOLLOW_NOT_BETWEEN_in_betweenCondition483); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_between_and_in_betweenCondition485);
					between_and18=between_and(where);
					state._fsp--;

					pushFollow(FOLLOW_left_cond_in_betweenCondition488);
					left_cond17=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


							exp.addNotBetween((left_cond17!=null?((MySQLWalker.left_cond_return)left_cond17).ret:null), (between_and18!=null?((MySQLWalker.between_and_return)between_and18).pair:null));
						
					}
					break;
				case 2 :
					// MySQLWalker.g:199:3: ^( BETWEEN between_and[$where] left_cond[$where] )
					{
					match(input,BETWEEN,FOLLOW_BETWEEN_in_betweenCondition497); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_between_and_in_betweenCondition499);
					between_and20=between_and(where);
					state._fsp--;

					pushFollow(FOLLOW_left_cond_in_betweenCondition502);
					left_cond19=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


							exp.addBetween((left_cond19!=null?((MySQLWalker.left_cond_return)left_cond19).ret:null), (between_and20!=null?((MySQLWalker.between_and_return)between_and20).pair:null));
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "betweenCondition"


	public static class between_and_return extends TreeRuleReturnScope {
		public BetweenPair pair;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "between_and"
	// MySQLWalker.g:203:1: between_and[BindIndexHolder where] returns [BetweenPair pair] : ^(start= between_and_expression[$where] end= between_and_expression[$where] ) ;
	public final MySQLWalker.between_and_return between_and(BindIndexHolder where) throws RecognitionException {
		MySQLWalker.between_and_return retval = new MySQLWalker.between_and_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope start =null;
		TreeRuleReturnScope end =null;

		try {
			// MySQLWalker.g:204:2: ( ^(start= between_and_expression[$where] end= between_and_expression[$where] ) )
			// MySQLWalker.g:204:3: ^(start= between_and_expression[$where] end= between_and_expression[$where] )
			{
			pushFollow(FOLLOW_between_and_expression_in_between_and522);
			start=between_and_expression(where);
			state._fsp--;

			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_between_and_expression_in_between_and527);
			end=between_and_expression(where);
			state._fsp--;

			match(input, Token.UP, null); 


					retval.pair = new BetweenPair((start!=null?((MySQLWalker.between_and_expression_return)start).valueObj:null), (end!=null?((MySQLWalker.between_and_expression_return)end).valueObj:null));
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "between_and"


	public static class likeCondition_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "likeCondition"
	// MySQLWalker.g:208:1: likeCondition[BindIndexHolder where,ExpressionGroup exp] : ( ^( NOT_LIKE expr[$where] left_cond[$where] ) | ^( LIKE expr[$where] left_cond[$where] ) );
	public final MySQLWalker.likeCondition_return likeCondition(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
		MySQLWalker.likeCondition_return retval = new MySQLWalker.likeCondition_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope left_cond21 =null;
		TreeRuleReturnScope expr22 =null;
		TreeRuleReturnScope left_cond23 =null;
		TreeRuleReturnScope expr24 =null;

		try {
			// MySQLWalker.g:209:2: ( ^( NOT_LIKE expr[$where] left_cond[$where] ) | ^( LIKE expr[$where] left_cond[$where] ) )
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==NOT_LIKE) ) {
				alt21=1;
			}
			else if ( (LA21_0==LIKE) ) {
				alt21=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}

			switch (alt21) {
				case 1 :
					// MySQLWalker.g:209:3: ^( NOT_LIKE expr[$where] left_cond[$where] )
					{
					match(input,NOT_LIKE,FOLLOW_NOT_LIKE_in_likeCondition543); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_in_likeCondition545);
					expr22=expr(where);
					state._fsp--;

					pushFollow(FOLLOW_left_cond_in_likeCondition548);
					left_cond21=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


							exp.addNotLike((left_cond21!=null?((MySQLWalker.left_cond_return)left_cond21).ret:null),(expr22!=null?((MySQLWalker.expr_return)expr22).valueObj:null));
						
					}
					break;
				case 2 :
					// MySQLWalker.g:212:3: ^( LIKE expr[$where] left_cond[$where] )
					{
					match(input,LIKE,FOLLOW_LIKE_in_likeCondition556); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_in_likeCondition558);
					expr24=expr(where);
					state._fsp--;

					pushFollow(FOLLOW_left_cond_in_likeCondition561);
					left_cond23=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


							exp.addLike((left_cond23!=null?((MySQLWalker.left_cond_return)left_cond23).ret:null),(expr24!=null?((MySQLWalker.expr_return)expr24).valueObj:null));
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "likeCondition"


	public static class isCondition_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "isCondition"
	// MySQLWalker.g:217:1: isCondition[BindIndexHolder where,ExpressionGroup exp] : ( ^( ISNOT NULL left_cond[$where] ) | ^( IS NULL left_cond[$where] ) );
	public final MySQLWalker.isCondition_return isCondition(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
		MySQLWalker.isCondition_return retval = new MySQLWalker.isCondition_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope left_cond25 =null;
		TreeRuleReturnScope left_cond26 =null;

		try {
			// MySQLWalker.g:218:2: ( ^( ISNOT NULL left_cond[$where] ) | ^( IS NULL left_cond[$where] ) )
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==ISNOT) ) {
				alt22=1;
			}
			else if ( (LA22_0==IS) ) {
				alt22=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// MySQLWalker.g:218:3: ^( ISNOT NULL left_cond[$where] )
					{
					match(input,ISNOT,FOLLOW_ISNOT_in_isCondition577); 
					match(input, Token.DOWN, null); 
					match(input,NULL,FOLLOW_NULL_in_isCondition579); 
					pushFollow(FOLLOW_left_cond_in_isCondition581);
					left_cond25=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


						exp.addIsNotNull((left_cond25!=null?((MySQLWalker.left_cond_return)left_cond25).ret:null));
						
					}
					break;
				case 2 :
					// MySQLWalker.g:221:3: ^( IS NULL left_cond[$where] )
					{
					match(input,IS,FOLLOW_IS_in_isCondition589); 
					match(input, Token.DOWN, null); 
					match(input,NULL,FOLLOW_NULL_in_isCondition591); 
					pushFollow(FOLLOW_left_cond_in_isCondition593);
					left_cond26=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


						exp.addIsNull((left_cond26!=null?((MySQLWalker.left_cond_return)left_cond26).ret:null));
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "isCondition"


	public static class comparisonCondition_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "comparisonCondition"
	// MySQLWalker.g:227:1: comparisonCondition[BindIndexHolder where,ExpressionGroup exp] : ( ^( EQ expr[$where] left_cond[$where] ) | ^( NOT_EQ expr[$where] left_cond[$where] ) | ^( LTH expr[$where] left_cond[$where] ) | ^( GTH expr[$where] left_cond[$where] ) | ^( LEQ expr[$where] left_cond[$where] ) | ^( GEQ expr[$where] left_cond[$where] ) );
	public final MySQLWalker.comparisonCondition_return comparisonCondition(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
		MySQLWalker.comparisonCondition_return retval = new MySQLWalker.comparisonCondition_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope left_cond27 =null;
		TreeRuleReturnScope expr28 =null;
		TreeRuleReturnScope left_cond29 =null;
		TreeRuleReturnScope expr30 =null;
		TreeRuleReturnScope left_cond31 =null;
		TreeRuleReturnScope expr32 =null;
		TreeRuleReturnScope left_cond33 =null;
		TreeRuleReturnScope expr34 =null;
		TreeRuleReturnScope left_cond35 =null;
		TreeRuleReturnScope expr36 =null;
		TreeRuleReturnScope left_cond37 =null;
		TreeRuleReturnScope expr38 =null;

		try {
			// MySQLWalker.g:228:2: ( ^( EQ expr[$where] left_cond[$where] ) | ^( NOT_EQ expr[$where] left_cond[$where] ) | ^( LTH expr[$where] left_cond[$where] ) | ^( GTH expr[$where] left_cond[$where] ) | ^( LEQ expr[$where] left_cond[$where] ) | ^( GEQ expr[$where] left_cond[$where] ) )
			int alt23=6;
			switch ( input.LA(1) ) {
			case EQ:
				{
				alt23=1;
				}
				break;
			case NOT_EQ:
				{
				alt23=2;
				}
				break;
			case LTH:
				{
				alt23=3;
				}
				break;
			case GTH:
				{
				alt23=4;
				}
				break;
			case LEQ:
				{
				alt23=5;
				}
				break;
			case GEQ:
				{
				alt23=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}
			switch (alt23) {
				case 1 :
					// MySQLWalker.g:228:3: ^( EQ expr[$where] left_cond[$where] )
					{
					match(input,EQ,FOLLOW_EQ_in_comparisonCondition610); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_in_comparisonCondition612);
					expr28=expr(where);
					state._fsp--;

					pushFollow(FOLLOW_left_cond_in_comparisonCondition615);
					left_cond27=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


							exp.addEqual((left_cond27!=null?((MySQLWalker.left_cond_return)left_cond27).ret:null),(expr28!=null?((MySQLWalker.expr_return)expr28).valueObj:null));
						
					}
					break;
				case 2 :
					// MySQLWalker.g:232:3: ^( NOT_EQ expr[$where] left_cond[$where] )
					{
					match(input,NOT_EQ,FOLLOW_NOT_EQ_in_comparisonCondition625); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_in_comparisonCondition627);
					expr30=expr(where);
					state._fsp--;

					pushFollow(FOLLOW_left_cond_in_comparisonCondition630);
					left_cond29=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


							exp.addNotEqual((left_cond29!=null?((MySQLWalker.left_cond_return)left_cond29).ret:null),(expr30!=null?((MySQLWalker.expr_return)expr30).valueObj:null));
						
					}
					break;
				case 3 :
					// MySQLWalker.g:236:3: ^( LTH expr[$where] left_cond[$where] )
					{
					match(input,LTH,FOLLOW_LTH_in_comparisonCondition640); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_in_comparisonCondition642);
					expr32=expr(where);
					state._fsp--;

					pushFollow(FOLLOW_left_cond_in_comparisonCondition645);
					left_cond31=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


							exp.addLessThan((left_cond31!=null?((MySQLWalker.left_cond_return)left_cond31).ret:null),(expr32!=null?((MySQLWalker.expr_return)expr32).valueObj:null));
						
					}
					break;
				case 4 :
					// MySQLWalker.g:240:3: ^( GTH expr[$where] left_cond[$where] )
					{
					match(input,GTH,FOLLOW_GTH_in_comparisonCondition655); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_in_comparisonCondition657);
					expr34=expr(where);
					state._fsp--;

					pushFollow(FOLLOW_left_cond_in_comparisonCondition660);
					left_cond33=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


							exp.addGreaterThan((left_cond33!=null?((MySQLWalker.left_cond_return)left_cond33).ret:null),(expr34!=null?((MySQLWalker.expr_return)expr34).valueObj:null));
						
					}
					break;
				case 5 :
					// MySQLWalker.g:244:3: ^( LEQ expr[$where] left_cond[$where] )
					{
					match(input,LEQ,FOLLOW_LEQ_in_comparisonCondition670); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_in_comparisonCondition672);
					expr36=expr(where);
					state._fsp--;

					pushFollow(FOLLOW_left_cond_in_comparisonCondition675);
					left_cond35=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


							exp.addLessOrEqual((left_cond35!=null?((MySQLWalker.left_cond_return)left_cond35).ret:null),(expr36!=null?((MySQLWalker.expr_return)expr36).valueObj:null));
						
					}
					break;
				case 6 :
					// MySQLWalker.g:248:3: ^( GEQ expr[$where] left_cond[$where] )
					{
					match(input,GEQ,FOLLOW_GEQ_in_comparisonCondition685); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_in_comparisonCondition687);
					expr38=expr(where);
					state._fsp--;

					pushFollow(FOLLOW_left_cond_in_comparisonCondition690);
					left_cond37=left_cond(where);
					state._fsp--;

					match(input, Token.UP, null); 


							exp.addGreaterOrEqual((left_cond37!=null?((MySQLWalker.left_cond_return)left_cond37).ret:null),(expr38!=null?((MySQLWalker.expr_return)expr38).valueObj:null));
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comparisonCondition"


	public static class left_cond_return extends TreeRuleReturnScope {
		public Object ret;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "left_cond"
	// MySQLWalker.g:277:1: left_cond[BindIndexHolder where] returns [Object ret] : ^( CONDITION_LEFT expr[$where] ) ;
	public final MySQLWalker.left_cond_return left_cond(BindIndexHolder where) throws RecognitionException {
		MySQLWalker.left_cond_return retval = new MySQLWalker.left_cond_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope expr39 =null;

		try {
			// MySQLWalker.g:279:2: ( ^( CONDITION_LEFT expr[$where] ) )
			// MySQLWalker.g:279:3: ^( CONDITION_LEFT expr[$where] )
			{
			match(input,CONDITION_LEFT,FOLLOW_CONDITION_LEFT_in_left_cond713); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expr_in_left_cond715);
			expr39=expr(where);
			state._fsp--;

			match(input, Token.UP, null); 


					retval.ret =(expr39!=null?((MySQLWalker.expr_return)expr39).valueObj:null);
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "left_cond"


	public static class in_list_return extends TreeRuleReturnScope {
		public List list;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "in_list"
	// MySQLWalker.g:284:1: in_list[BindIndexHolder where] returns [List list] : ^( IN_LISTS inCondition_expr_adds[$where] ) ;
	public final MySQLWalker.in_list_return in_list(BindIndexHolder where) throws RecognitionException {
		MySQLWalker.in_list_return retval = new MySQLWalker.in_list_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope inCondition_expr_adds40 =null;

		try {
			// MySQLWalker.g:285:2: ( ^( IN_LISTS inCondition_expr_adds[$where] ) )
			// MySQLWalker.g:285:3: ^( IN_LISTS inCondition_expr_adds[$where] )
			{
			match(input,IN_LISTS,FOLLOW_IN_LISTS_in_in_list734); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_inCondition_expr_adds_in_in_list736);
			inCondition_expr_adds40=inCondition_expr_adds(where);
			state._fsp--;

			match(input, Token.UP, null); 


					retval.list =(inCondition_expr_adds40!=null?((MySQLWalker.inCondition_expr_adds_return)inCondition_expr_adds40).list:null);
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "in_list"


	public static class inCondition_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "inCondition"
	// MySQLWalker.g:290:1: inCondition[BindIndexHolder where,ExpressionGroup exp] : ^( IN (not= 'NOT' )? ( subquery )? ( in_list[$where] )? left_cond[$where] ) ;
	public final MySQLWalker.inCondition_return inCondition(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
		MySQLWalker.inCondition_return retval = new MySQLWalker.inCondition_return();
		retval.start = input.LT(1);

		CommonTree not=null;
		TreeRuleReturnScope subquery41 =null;
		TreeRuleReturnScope in_list42 =null;
		TreeRuleReturnScope left_cond43 =null;

		try {
			// MySQLWalker.g:291:2: ( ^( IN (not= 'NOT' )? ( subquery )? ( in_list[$where] )? left_cond[$where] ) )
			// MySQLWalker.g:291:3: ^( IN (not= 'NOT' )? ( subquery )? ( in_list[$where] )? left_cond[$where] )
			{
			match(input,IN,FOLLOW_IN_in_inCondition754); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:291:11: (not= 'NOT' )?
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==103) ) {
				alt24=1;
			}
			switch (alt24) {
				case 1 :
					// MySQLWalker.g:291:11: not= 'NOT'
					{
					not=(CommonTree)match(input,103,FOLLOW_103_in_inCondition758); 
					}
					break;

			}

			// MySQLWalker.g:291:19: ( subquery )?
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==SUBQUERY) ) {
				alt25=1;
			}
			switch (alt25) {
				case 1 :
					// MySQLWalker.g:291:19: subquery
					{
					pushFollow(FOLLOW_subquery_in_inCondition761);
					subquery41=subquery();
					state._fsp--;

					}
					break;

			}

			// MySQLWalker.g:291:29: ( in_list[$where] )?
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==IN_LISTS) ) {
				alt26=1;
			}
			switch (alt26) {
				case 1 :
					// MySQLWalker.g:291:29: in_list[$where]
					{
					pushFollow(FOLLOW_in_list_in_inCondition764);
					in_list42=in_list(where);
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_left_cond_in_inCondition769);
			left_cond43=left_cond(where);
			state._fsp--;

			match(input, Token.UP, null); 


					if((not!=null?not.getText():null)!=null){
						if((subquery41!=null?((MySQLWalker.subquery_return)subquery41).subselect:null)!=null){
							/*exp.addNotIn($columnNameAfterWhere.text,(subquery41!=null?((MySQLWalker.subquery_return)subquery41).subselect:null));*/
						}
						else if((in_list42!=null?((MySQLWalker.in_list_return)in_list42).list:null)!=null){
							exp.addNotIn((left_cond43!=null?((MySQLWalker.left_cond_return)left_cond43).ret:null),(in_list42!=null?((MySQLWalker.in_list_return)in_list42).list:null));
						}
					}else{
						if((subquery41!=null?((MySQLWalker.subquery_return)subquery41).subselect:null)!=null){
							/*exp.addIn($columnNameAfterWhere.text,(subquery41!=null?((MySQLWalker.subquery_return)subquery41).subselect:null));*/
						}
						else if((in_list42!=null?((MySQLWalker.in_list_return)in_list42).list:null)!=null){
							exp.addIn((left_cond43!=null?((MySQLWalker.left_cond_return)left_cond43).ret:null),(in_list42!=null?((MySQLWalker.in_list_return)in_list42).list:null));
						}
					}
					;
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "inCondition"


	public static class inCondition_expr_adds_return extends TreeRuleReturnScope {
		public List list;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "inCondition_expr_adds"
	// MySQLWalker.g:316:1: inCondition_expr_adds[BindIndexHolder where] returns [List list] : ( expr_add[$where] )+ ;
	public final MySQLWalker.inCondition_expr_adds_return inCondition_expr_adds(BindIndexHolder where) throws RecognitionException {
		MySQLWalker.inCondition_expr_adds_return retval = new MySQLWalker.inCondition_expr_adds_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope expr_add44 =null;

		retval.list =new ArrayList();
		try {
			// MySQLWalker.g:317:31: ( ( expr_add[$where] )+ )
			// MySQLWalker.g:318:2: ( expr_add[$where] )+
			{
			// MySQLWalker.g:318:2: ( expr_add[$where] )+
			int cnt27=0;
			loop27:
			while (true) {
				int alt27=2;
				int LA27_0 = input.LA(1);
				if ( (LA27_0==ASTERISK||LA27_0==COLUMN||LA27_0==DIVIDE||LA27_0==ID||(LA27_0 >= MINUS && LA27_0 <= N)||LA27_0==NUMBER||LA27_0==PLUS||LA27_0==QUTED_STR||LA27_0==77||LA27_0==86||LA27_0==104||LA27_0==109||LA27_0==112) ) {
					alt27=1;
				}

				switch (alt27) {
				case 1 :
					// MySQLWalker.g:318:3: expr_add[$where]
					{
					pushFollow(FOLLOW_expr_add_in_inCondition_expr_adds803);
					expr_add44=expr_add(where);
					state._fsp--;


							retval.list.add((expr_add44!=null?((MySQLWalker.expr_add_return)expr_add44).valueObjExpr:null));
						
					}
					break;

				default :
					if ( cnt27 >= 1 ) break loop27;
					EarlyExitException eee = new EarlyExitException(27, input);
					throw eee;
				}
				cnt27++;
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "inCondition_expr_adds"


	public static class expr_return extends TreeRuleReturnScope {
		public Object valueObj;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "expr"
	// MySQLWalker.g:325:1: expr[BindIndexHolder where] returns [Object valueObj] : ( expr_add[$where] | subquery ) ;
	public final MySQLWalker.expr_return expr(BindIndexHolder where) throws RecognitionException {
		MySQLWalker.expr_return retval = new MySQLWalker.expr_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope expr_add45 =null;
		TreeRuleReturnScope subquery46 =null;

		try {
			// MySQLWalker.g:326:2: ( ( expr_add[$where] | subquery ) )
			// MySQLWalker.g:326:3: ( expr_add[$where] | subquery )
			{
			// MySQLWalker.g:326:3: ( expr_add[$where] | subquery )
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==ASTERISK||LA28_0==COLUMN||LA28_0==DIVIDE||LA28_0==ID||(LA28_0 >= MINUS && LA28_0 <= N)||LA28_0==NUMBER||LA28_0==PLUS||LA28_0==QUTED_STR||LA28_0==77||LA28_0==86||LA28_0==104||LA28_0==109||LA28_0==112) ) {
				alt28=1;
			}
			else if ( (LA28_0==SUBQUERY) ) {
				alt28=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}

			switch (alt28) {
				case 1 :
					// MySQLWalker.g:326:4: expr_add[$where]
					{
					pushFollow(FOLLOW_expr_add_in_expr828);
					expr_add45=expr_add(where);
					state._fsp--;

					retval.valueObj =(expr_add45!=null?((MySQLWalker.expr_add_return)expr_add45).valueObjExpr:null);
					}
					break;
				case 2 :
					// MySQLWalker.g:327:3: subquery
					{
					pushFollow(FOLLOW_subquery_in_expr835);
					subquery46=subquery();
					state._fsp--;

					retval.valueObj =(subquery46!=null?((MySQLWalker.subquery_return)subquery46).subselect:null);
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr"


	public static class expr_add_return extends TreeRuleReturnScope {
		public Object valueObjExpr;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "expr_add"
	// MySQLWalker.g:331:1: expr_add[BindIndexHolder where] returns [Object valueObjExpr] : ( ^( PLUS s1= expr_add[$where] s2= expr_add[$where] ) | ^( MINUS s1= expr_add[$where] s2= expr_add[$where] ) | ^( ASTERISK s1= expr_add[$where] s2= expr_add[$where] ) | ^( DIVIDE s1= expr_add[$where] s2= expr_add[$where] ) | ^( MOD s1= expr_add[$where] s2= expr_add[$where] ) | N | NUMBER | boolean_literal | 'NULL' | 'ROWNUM' | '?' | ^( QUTED_STR quoted_string ) | ^( COLUMN identifier ( table_name[false] )? ) | ^( ID ( expr[$where] )* ) );
	public final MySQLWalker.expr_add_return expr_add(BindIndexHolder where) throws RecognitionException {
		MySQLWalker.expr_add_return retval = new MySQLWalker.expr_add_return();
		retval.start = input.LT(1);

		CommonTree N47=null;
		CommonTree NUMBER48=null;
		CommonTree ID53=null;
		TreeRuleReturnScope s1 =null;
		TreeRuleReturnScope s2 =null;
		TreeRuleReturnScope quoted_string49 =null;
		TreeRuleReturnScope identifier50 =null;
		TreeRuleReturnScope table_name51 =null;
		TreeRuleReturnScope expr52 =null;


		List<Object> list=new ArrayList<Object>();

		try {
			// MySQLWalker.g:335:2: ( ^( PLUS s1= expr_add[$where] s2= expr_add[$where] ) | ^( MINUS s1= expr_add[$where] s2= expr_add[$where] ) | ^( ASTERISK s1= expr_add[$where] s2= expr_add[$where] ) | ^( DIVIDE s1= expr_add[$where] s2= expr_add[$where] ) | ^( MOD s1= expr_add[$where] s2= expr_add[$where] ) | N | NUMBER | boolean_literal | 'NULL' | 'ROWNUM' | '?' | ^( QUTED_STR quoted_string ) | ^( COLUMN identifier ( table_name[false] )? ) | ^( ID ( expr[$where] )* ) )
			int alt31=14;
			switch ( input.LA(1) ) {
			case PLUS:
				{
				alt31=1;
				}
				break;
			case MINUS:
				{
				alt31=2;
				}
				break;
			case ASTERISK:
				{
				alt31=3;
				}
				break;
			case DIVIDE:
				{
				alt31=4;
				}
				break;
			case MOD:
				{
				alt31=5;
				}
				break;
			case N:
				{
				alt31=6;
				}
				break;
			case NUMBER:
				{
				alt31=7;
				}
				break;
			case 86:
			case 112:
				{
				alt31=8;
				}
				break;
			case 104:
				{
				alt31=9;
				}
				break;
			case 109:
				{
				alt31=10;
				}
				break;
			case 77:
				{
				alt31=11;
				}
				break;
			case QUTED_STR:
				{
				alt31=12;
				}
				break;
			case COLUMN:
				{
				alt31=13;
				}
				break;
			case ID:
				{
				alt31=14;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}
			switch (alt31) {
				case 1 :
					// MySQLWalker.g:335:3: ^( PLUS s1= expr_add[$where] s2= expr_add[$where] )
					{
					match(input,PLUS,FOLLOW_PLUS_in_expr_add862); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_add_in_expr_add866);
					s1=expr_add(where);
					state._fsp--;

					pushFollow(FOLLOW_expr_add_in_expr_add871);
					s2=expr_add(where);
					state._fsp--;

					match(input, Token.UP, null); 

					retval.valueObjExpr =add((s1!=null?((MySQLWalker.expr_add_return)s1).valueObjExpr:null),(s2!=null?((MySQLWalker.expr_add_return)s2).valueObjExpr:null));
					}
					break;
				case 2 :
					// MySQLWalker.g:336:3: ^( MINUS s1= expr_add[$where] s2= expr_add[$where] )
					{
					match(input,MINUS,FOLLOW_MINUS_in_expr_add880); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_add_in_expr_add884);
					s1=expr_add(where);
					state._fsp--;

					pushFollow(FOLLOW_expr_add_in_expr_add889);
					s2=expr_add(where);
					state._fsp--;

					match(input, Token.UP, null); 

					retval.valueObjExpr =subtract((s1!=null?((MySQLWalker.expr_add_return)s1).valueObjExpr:null),(s2!=null?((MySQLWalker.expr_add_return)s2).valueObjExpr:null));
					}
					break;
				case 3 :
					// MySQLWalker.g:337:3: ^( ASTERISK s1= expr_add[$where] s2= expr_add[$where] )
					{
					match(input,ASTERISK,FOLLOW_ASTERISK_in_expr_add897); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_add_in_expr_add901);
					s1=expr_add(where);
					state._fsp--;

					pushFollow(FOLLOW_expr_add_in_expr_add906);
					s2=expr_add(where);
					state._fsp--;

					match(input, Token.UP, null); 

					retval.valueObjExpr =multiply((s1!=null?((MySQLWalker.expr_add_return)s1).valueObjExpr:null),(s2!=null?((MySQLWalker.expr_add_return)s2).valueObjExpr:null));
					}
					break;
				case 4 :
					// MySQLWalker.g:338:3: ^( DIVIDE s1= expr_add[$where] s2= expr_add[$where] )
					{
					match(input,DIVIDE,FOLLOW_DIVIDE_in_expr_add914); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_add_in_expr_add918);
					s1=expr_add(where);
					state._fsp--;

					pushFollow(FOLLOW_expr_add_in_expr_add923);
					s2=expr_add(where);
					state._fsp--;

					match(input, Token.UP, null); 

					retval.valueObjExpr =divide((s1!=null?((MySQLWalker.expr_add_return)s1).valueObjExpr:null),(s2!=null?((MySQLWalker.expr_add_return)s2).valueObjExpr:null));
					}
					break;
				case 5 :
					// MySQLWalker.g:339:3: ^( MOD s1= expr_add[$where] s2= expr_add[$where] )
					{
					match(input,MOD,FOLLOW_MOD_in_expr_add931); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_add_in_expr_add935);
					s1=expr_add(where);
					state._fsp--;

					pushFollow(FOLLOW_expr_add_in_expr_add940);
					s2=expr_add(where);
					state._fsp--;

					match(input, Token.UP, null); 

					retval.valueObjExpr =mod((s1!=null?((MySQLWalker.expr_add_return)s1).valueObjExpr:null),(s2!=null?((MySQLWalker.expr_add_return)s2).valueObjExpr:null));
					}
					break;
				case 6 :
					// MySQLWalker.g:340:3: N
					{
					N47=(CommonTree)match(input,N,FOLLOW_N_in_expr_add947); 
					retval.valueObjExpr =new BigDecimal((N47!=null?N47.getText():null));
					}
					break;
				case 7 :
					// MySQLWalker.g:341:3: NUMBER
					{
					NUMBER48=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_expr_add953); 
					retval.valueObjExpr =new BigDecimal((NUMBER48!=null?NUMBER48.getText():null));
					}
					break;
				case 8 :
					// MySQLWalker.g:342:3: boolean_literal
					{
					pushFollow(FOLLOW_boolean_literal_in_expr_add958);
					boolean_literal();
					state._fsp--;

					}
					break;
				case 9 :
					// MySQLWalker.g:343:3: 'NULL'
					{
					match(input,104,FOLLOW_104_in_expr_add962); 
					retval.valueObjExpr =DbFunctions.NULL;
					}
					break;
				case 10 :
					// MySQLWalker.g:344:3: 'ROWNUM'
					{
					match(input,109,FOLLOW_109_in_expr_add968); 
					}
					break;
				case 11 :
					// MySQLWalker.g:345:3: '?'
					{
					match(input,77,FOLLOW_77_in_expr_add972); 
					retval.valueObjExpr =DbFunctions.bindVar(where.selfAddAndGet());
					}
					break;
				case 12 :
					// MySQLWalker.g:346:3: ^( QUTED_STR quoted_string )
					{
					match(input,QUTED_STR,FOLLOW_QUTED_STR_in_expr_add978); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_quoted_string_in_expr_add980);
					quoted_string49=quoted_string();
					state._fsp--;

					match(input, Token.UP, null); 

					retval.valueObjExpr =(quoted_string49!=null?((MySQLWalker.quoted_string_return)quoted_string49).aText:null);
					}
					break;
				case 13 :
					// MySQLWalker.g:347:3: ^( COLUMN identifier ( table_name[false] )? )
					{
					match(input,COLUMN,FOLLOW_COLUMN_in_expr_add987); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_identifier_in_expr_add989);
					identifier50=identifier();
					state._fsp--;

					// MySQLWalker.g:347:23: ( table_name[false] )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==ASTERISK||LA29_0==ID) ) {
						alt29=1;
					}
					switch (alt29) {
						case 1 :
							// MySQLWalker.g:347:23: table_name[false]
							{
							pushFollow(FOLLOW_table_name_in_expr_add991);
							table_name51=table_name(false);
							state._fsp--;

							}
							break;

					}

					match(input, Token.UP, null); 

					retval.valueObjExpr =column((identifier50!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier50.start),input.getTreeAdaptor().getTokenStopIndex(identifier50.start))):null),(table_name51!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(table_name51.start),input.getTreeAdaptor().getTokenStopIndex(table_name51.start))):null));
					}
					break;
				case 14 :
					// MySQLWalker.g:349:3: ^( ID ( expr[$where] )* )
					{
					ID53=(CommonTree)match(input,ID,FOLLOW_ID_in_expr_add1002); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// MySQLWalker.g:350:2: ( expr[$where] )*
						loop30:
						while (true) {
							int alt30=2;
							int LA30_0 = input.LA(1);
							if ( (LA30_0==ASTERISK||LA30_0==COLUMN||LA30_0==DIVIDE||LA30_0==ID||(LA30_0 >= MINUS && LA30_0 <= N)||LA30_0==NUMBER||LA30_0==PLUS||LA30_0==QUTED_STR||LA30_0==SUBQUERY||LA30_0==77||LA30_0==86||LA30_0==104||LA30_0==109||LA30_0==112) ) {
								alt30=1;
							}

							switch (alt30) {
							case 1 :
								// MySQLWalker.g:350:3: expr[$where]
								{
								pushFollow(FOLLOW_expr_in_expr_add1007);
								expr52=expr(where);
								state._fsp--;

								list.add((expr52!=null?((MySQLWalker.expr_return)expr52).valueObj:null));
								}
								break;

							default :
								break loop30;
							}
						}

						match(input, Token.UP, null); 
					}


						FunctionConvertor con=functionMap.get(((ID53!=null?ID53.getText():null)).toUpperCase());
						
						retval.valueObjExpr =con.handle(list.toArray());
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr_add"


	public static class value_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "value"
	// MySQLWalker.g:357:1: value : ( N | NUMBER | '?' | ^( QUTED_STR quoted_string ) );
	public final MySQLWalker.value_return value() throws RecognitionException {
		MySQLWalker.value_return retval = new MySQLWalker.value_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:357:7: ( N | NUMBER | '?' | ^( QUTED_STR quoted_string ) )
			int alt32=4;
			switch ( input.LA(1) ) {
			case N:
				{
				alt32=1;
				}
				break;
			case NUMBER:
				{
				alt32=2;
				}
				break;
			case 77:
				{
				alt32=3;
				}
				break;
			case QUTED_STR:
				{
				alt32=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}
			switch (alt32) {
				case 1 :
					// MySQLWalker.g:358:2: N
					{
					match(input,N,FOLLOW_N_in_value1025); 
					}
					break;
				case 2 :
					// MySQLWalker.g:359:3: NUMBER
					{
					match(input,NUMBER,FOLLOW_NUMBER_in_value1029); 
					}
					break;
				case 3 :
					// MySQLWalker.g:360:3: '?'
					{
					match(input,77,FOLLOW_77_in_value1033); 
					}
					break;
				case 4 :
					// MySQLWalker.g:361:3: ^( QUTED_STR quoted_string )
					{
					match(input,QUTED_STR,FOLLOW_QUTED_STR_in_value1038); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_quoted_string_in_value1040);
					quoted_string();
					state._fsp--;

					match(input, Token.UP, null); 

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "value"


	public static class boolean_literal_return extends TreeRuleReturnScope {
		public Object valueObj;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "boolean_literal"
	// MySQLWalker.g:363:1: boolean_literal returns [Object valueObj] : (s1= 'TRUE' |s1= 'FALSE' );
	public final MySQLWalker.boolean_literal_return boolean_literal() throws RecognitionException {
		MySQLWalker.boolean_literal_return retval = new MySQLWalker.boolean_literal_return();
		retval.start = input.LT(1);

		CommonTree s1=null;

		try {
			// MySQLWalker.g:364:2: (s1= 'TRUE' |s1= 'FALSE' )
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==112) ) {
				alt33=1;
			}
			else if ( (LA33_0==86) ) {
				alt33=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 33, 0, input);
				throw nvae;
			}

			switch (alt33) {
				case 1 :
					// MySQLWalker.g:364:3: s1= 'TRUE'
					{
					s1=(CommonTree)match(input,112,FOLLOW_112_in_boolean_literal1055); 
					retval.valueObj =Boolean.parseBoolean((s1!=null?s1.getText():null));
					}
					break;
				case 2 :
					// MySQLWalker.g:365:4: s1= 'FALSE'
					{
					s1=(CommonTree)match(input,86,FOLLOW_86_in_boolean_literal1064); 
					retval.valueObj =Boolean.parseBoolean((s1!=null?s1.getText():null));
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "boolean_literal"


	public static class select_list_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "select_list"
	// MySQLWalker.g:369:1: select_list[Select select] : ^( SELECT_LIST ( displayed_column[$select] )+ ) ;
	public final MySQLWalker.select_list_return select_list(Select select) throws RecognitionException {
		MySQLWalker.select_list_return retval = new MySQLWalker.select_list_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:370:2: ( ^( SELECT_LIST ( displayed_column[$select] )+ ) )
			// MySQLWalker.g:370:3: ^( SELECT_LIST ( displayed_column[$select] )+ )
			{
			match(input,SELECT_LIST,FOLLOW_SELECT_LIST_in_select_list1080); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:370:17: ( displayed_column[$select] )+
			int cnt34=0;
			loop34:
			while (true) {
				int alt34=2;
				int LA34_0 = input.LA(1);
				if ( (LA34_0==DOUBLEQUOTED_STRING||LA34_0==EXPR||LA34_0==ID||LA34_0==QUOTED_STRING||(LA34_0 >= 82 && LA34_0 <= 83)) ) {
					alt34=1;
				}

				switch (alt34) {
				case 1 :
					// MySQLWalker.g:370:17: displayed_column[$select]
					{
					pushFollow(FOLLOW_displayed_column_in_select_list1082);
					displayed_column(select);
					state._fsp--;

					}
					break;

				default :
					if ( cnt34 >= 1 ) break loop34;
					EarlyExitException eee = new EarlyExitException(34, input);
					throw eee;
				}
				cnt34++;
			}

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "select_list"


	public static class fromClause_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "fromClause"
	// MySQLWalker.g:372:1: fromClause[Select select] : ^( TABLENAMES ( table[$select, true] )+ ) ;
	public final MySQLWalker.fromClause_return fromClause(Select select) throws RecognitionException {
		MySQLWalker.fromClause_return retval = new MySQLWalker.fromClause_return();
		retval.start = input.LT(1);

		needToRewriteTableName = true;
		try {
			// MySQLWalker.g:375:2: ( ^( TABLENAMES ( table[$select, true] )+ ) )
			// MySQLWalker.g:375:3: ^( TABLENAMES ( table[$select, true] )+ )
			{
			match(input,TABLENAMES,FOLLOW_TABLENAMES_in_fromClause1107); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:375:16: ( table[$select, true] )+
			int cnt35=0;
			loop35:
			while (true) {
				int alt35=2;
				int LA35_0 = input.LA(1);
				if ( (LA35_0==TABLENAME) ) {
					alt35=1;
				}

				switch (alt35) {
				case 1 :
					// MySQLWalker.g:375:16: table[$select, true]
					{
					pushFollow(FOLLOW_table_in_fromClause1109);
					table(select, true);
					state._fsp--;

					}
					break;

				default :
					if ( cnt35 >= 1 ) break loop35;
					EarlyExitException eee = new EarlyExitException(35, input);
					throw eee;
				}
				cnt35++;
			}

			match(input, Token.UP, null); 

			}

			needToRewriteTableName = false;
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fromClause"


	public static class table_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "table"
	// MySQLWalker.g:378:1: table[DMLCommon common, boolean needToRewriteTableName] : ^( TABLENAME table_spec[$common, $needToRewriteTableName] ) ;
	public final MySQLWalker.table_return table(DMLCommon common, boolean needToRewriteTableName) throws RecognitionException {
		MySQLWalker.table_return retval = new MySQLWalker.table_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:379:2: ( ^( TABLENAME table_spec[$common, $needToRewriteTableName] ) )
			// MySQLWalker.g:379:3: ^( TABLENAME table_spec[$common, $needToRewriteTableName] )
			{
			match(input,TABLENAME,FOLLOW_TABLENAME_in_table1124); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_table_spec_in_table1126);
			table_spec(common, needToRewriteTableName);
			state._fsp--;

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "table"


	public static class tables_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "tables"
	// MySQLWalker.g:381:1: tables[DMLCommon common, boolean needToRewriteTableName] : ^( TABLENAMES ( table[$common, $needToRewriteTableName] )+ ) ;
	public final MySQLWalker.tables_return tables(DMLCommon common, boolean needToRewriteTableName) throws RecognitionException {
		MySQLWalker.tables_return retval = new MySQLWalker.tables_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:382:2: ( ^( TABLENAMES ( table[$common, $needToRewriteTableName] )+ ) )
			// MySQLWalker.g:382:3: ^( TABLENAMES ( table[$common, $needToRewriteTableName] )+ )
			{
			match(input,TABLENAMES,FOLLOW_TABLENAMES_in_tables1139); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:382:16: ( table[$common, $needToRewriteTableName] )+
			int cnt36=0;
			loop36:
			while (true) {
				int alt36=2;
				int LA36_0 = input.LA(1);
				if ( (LA36_0==TABLENAME) ) {
					alt36=1;
				}

				switch (alt36) {
				case 1 :
					// MySQLWalker.g:382:16: table[$common, $needToRewriteTableName]
					{
					pushFollow(FOLLOW_table_in_tables1141);
					table(common, needToRewriteTableName);
					state._fsp--;

					}
					break;

				default :
					if ( cnt36 >= 1 ) break loop36;
					EarlyExitException eee = new EarlyExitException(36, input);
					throw eee;
				}
				cnt36++;
			}

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tables"


	public static class table_spec_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "table_spec"
	// MySQLWalker.g:384:1: table_spec[DMLCommon common, boolean needToRewriteTableName] : ( ( schema_name )? table_name[$needToRewriteTableName] ( alias )? | subquery ( alias )? );
	public final MySQLWalker.table_spec_return table_spec(DMLCommon common, boolean needToRewriteTableName) throws RecognitionException {
		MySQLWalker.table_spec_return retval = new MySQLWalker.table_spec_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope table_name54 =null;
		TreeRuleReturnScope schema_name55 =null;
		TreeRuleReturnScope alias56 =null;
		TreeRuleReturnScope subquery57 =null;
		TreeRuleReturnScope alias58 =null;

		try {
			// MySQLWalker.g:385:2: ( ( schema_name )? table_name[$needToRewriteTableName] ( alias )? | subquery ( alias )? )
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==ASTERISK||LA40_0==ID) ) {
				alt40=1;
			}
			else if ( (LA40_0==SUBQUERY) ) {
				alt40=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 40, 0, input);
				throw nvae;
			}

			switch (alt40) {
				case 1 :
					// MySQLWalker.g:385:3: ( schema_name )? table_name[$needToRewriteTableName] ( alias )?
					{
					// MySQLWalker.g:385:3: ( schema_name )?
					int alt37=2;
					int LA37_0 = input.LA(1);
					if ( (LA37_0==ASTERISK||LA37_0==ID) ) {
						int LA37_1 = input.LA(2);
						if ( (LA37_1==ASTERISK||LA37_1==ID) ) {
							alt37=1;
						}
					}
					switch (alt37) {
						case 1 :
							// MySQLWalker.g:385:5: schema_name
							{
							pushFollow(FOLLOW_schema_name_in_table_spec1156);
							schema_name55=schema_name();
							state._fsp--;

							}
							break;

					}

					pushFollow(FOLLOW_table_name_in_table_spec1160);
					table_name54=table_name(needToRewriteTableName);
					state._fsp--;

					// MySQLWalker.g:385:56: ( alias )?
					int alt38=2;
					int LA38_0 = input.LA(1);
					if ( (LA38_0==AS) ) {
						int LA38_1 = input.LA(2);
						if ( (LA38_1==DOWN) ) {
							int LA38_3 = input.LA(3);
							if ( (LA38_3==ASTERISK||LA38_3==ID) ) {
								int LA38_4 = input.LA(4);
								if ( (LA38_4==UP) ) {
									alt38=1;
								}
							}
						}
					}
					switch (alt38) {
						case 1 :
							// MySQLWalker.g:385:56: alias
							{
							pushFollow(FOLLOW_alias_in_table_spec1164);
							alias56=alias();
							state._fsp--;

							}
							break;

					}


							common.addTableNameAndSchemaName((table_name54!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(table_name54.start),input.getTreeAdaptor().getTokenStopIndex(table_name54.start))):null),(schema_name55!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(schema_name55.start),input.getTreeAdaptor().getTokenStopIndex(schema_name55.start))):null),(alias56!=null?((MySQLWalker.alias_return)alias56).aliText:null));
						
					}
					break;
				case 2 :
					// MySQLWalker.g:389:3: subquery ( alias )?
					{
					pushFollow(FOLLOW_subquery_in_table_spec1172);
					subquery57=subquery();
					state._fsp--;

					// MySQLWalker.g:389:12: ( alias )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==AS) ) {
						int LA39_1 = input.LA(2);
						if ( (LA39_1==DOWN) ) {
							int LA39_3 = input.LA(3);
							if ( (LA39_3==ASTERISK||LA39_3==ID) ) {
								int LA39_4 = input.LA(4);
								if ( (LA39_4==UP) ) {
									alt39=1;
								}
							}
						}
					}
					switch (alt39) {
						case 1 :
							// MySQLWalker.g:389:12: alias
							{
							pushFollow(FOLLOW_alias_in_table_spec1174);
							alias58=alias();
							state._fsp--;

							}
							break;

					}


							common.addTableSubQuery((subquery57!=null?((MySQLWalker.subquery_return)subquery57).subselect:null),(alias58!=null?((MySQLWalker.alias_return)alias58).aliText:null));

						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "table_spec"


	public static class schema_name_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "schema_name"
	// MySQLWalker.g:396:1: schema_name : identifier ;
	public final MySQLWalker.schema_name_return schema_name() throws RecognitionException {
		MySQLWalker.schema_name_return retval = new MySQLWalker.schema_name_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:397:2: ( identifier )
			// MySQLWalker.g:397:3: identifier
			{
			pushFollow(FOLLOW_identifier_in_schema_name1189);
			identifier();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "schema_name"


	public static class subquery_return extends TreeRuleReturnScope {
		public Select subselect;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "subquery"
	// MySQLWalker.g:399:1: subquery returns [Select subselect] : ^( SUBQUERY select_command ) ;
	public final MySQLWalker.subquery_return subquery() throws RecognitionException {
		MySQLWalker.subquery_return retval = new MySQLWalker.subquery_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope select_command59 =null;

		try {
			// MySQLWalker.g:399:36: ( ^( SUBQUERY select_command ) )
			// MySQLWalker.g:400:2: ^( SUBQUERY select_command )
			{
			match(input,SUBQUERY,FOLLOW_SUBQUERY_in_subquery1203); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_select_command_in_subquery1205);
			select_command59=select_command();
			state._fsp--;

			match(input, Token.UP, null); 


				retval.subselect =(select_command59!=null?((MySQLWalker.select_command_return)select_command59).select:null);
				retval.subselect.setSubSelect(true);
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subquery"


	public static class table_name_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "table_name"
	// MySQLWalker.g:406:1: table_name[boolean localNeedToRewriteTableName] : identifier ;
	public final MySQLWalker.table_name_return table_name(boolean localNeedToRewriteTableName) throws RecognitionException {
		MySQLWalker.table_name_return retval = new MySQLWalker.table_name_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:407:2: ( identifier )
			// MySQLWalker.g:407:3: identifier
			{
			pushFollow(FOLLOW_identifier_in_table_name1222);
			identifier();
			state._fsp--;


					if(localNeedToRewriteTableName || needToRewriteTableName){
						pos2TableName.put(((CommonTree)retval.start).getCharPositionInLine(), input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(retval.start),input.getTreeAdaptor().getTokenStopIndex(retval.start)));}
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "table_name"


	public static class displayed_column_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "displayed_column"
	// MySQLWalker.g:413:1: displayed_column[Select select] : ( ^( quoted_string ( alias )? ) | ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? ) | ^( count ( distinct )? countColumn ( alias )? ) | ^( ID ( table_alias )? ( columnAnt )? ( alias )? ) | ^( EXPR expr_add[new BindIndexHolder()] ( alias )? ) );
	public final MySQLWalker.displayed_column_return displayed_column(Select select) throws RecognitionException {
		MySQLWalker.displayed_column_return retval = new MySQLWalker.displayed_column_return();
		retval.start = input.LT(1);

		CommonTree ID66=null;
		TreeRuleReturnScope quoted_string60 =null;
		TreeRuleReturnScope alias61 =null;
		TreeRuleReturnScope alias62 =null;
		TreeRuleReturnScope countColumn63 =null;
		TreeRuleReturnScope distinct64 =null;
		TreeRuleReturnScope alias65 =null;
		TreeRuleReturnScope alias67 =null;
		TreeRuleReturnScope table_alias68 =null;
		TreeRuleReturnScope columnAnt69 =null;
		TreeRuleReturnScope expr_add70 =null;
		TreeRuleReturnScope alias71 =null;

		try {
			// MySQLWalker.g:414:2: ( ^( quoted_string ( alias )? ) | ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? ) | ^( count ( distinct )? countColumn ( alias )? ) | ^( ID ( table_alias )? ( columnAnt )? ( alias )? ) | ^( EXPR expr_add[new BindIndexHolder()] ( alias )? ) )
			int alt50=5;
			switch ( input.LA(1) ) {
			case DOUBLEQUOTED_STRING:
			case QUOTED_STRING:
				{
				alt50=1;
				}
				break;
			case 82:
				{
				alt50=2;
				}
				break;
			case 83:
				{
				alt50=3;
				}
				break;
			case ID:
				{
				alt50=4;
				}
				break;
			case EXPR:
				{
				alt50=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 50, 0, input);
				throw nvae;
			}
			switch (alt50) {
				case 1 :
					// MySQLWalker.g:415:2: ^( quoted_string ( alias )? )
					{
					pushFollow(FOLLOW_quoted_string_in_displayed_column1240);
					quoted_string60=quoted_string();
					state._fsp--;

					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// MySQLWalker.g:415:18: ( alias )?
						int alt41=2;
						int LA41_0 = input.LA(1);
						if ( (LA41_0==AS) ) {
							alt41=1;
						}
						switch (alt41) {
							case 1 :
								// MySQLWalker.g:415:18: alias
								{
								pushFollow(FOLLOW_alias_in_displayed_column1242);
								alias61=alias();
								state._fsp--;

								}
								break;

						}

						match(input, Token.UP, null); 
					}

					select.addColumn("", (quoted_string60!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(quoted_string60.start),input.getTreeAdaptor().getTokenStopIndex(quoted_string60.start))):null), (alias61!=null?((MySQLWalker.alias_return)alias61).aliText:null));
					}
					break;
				case 2 :
					// MySQLWalker.g:416:3: ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? )
					{
					pushFollow(FOLLOW_concat_in_displayed_column1251);
					concat();
					state._fsp--;

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_identifiedOrQuotedString_in_displayed_column1253);
					identifiedOrQuotedString();
					state._fsp--;

					// MySQLWalker.g:416:37: ( identifiedOrQuotedString )*
					loop42:
					while (true) {
						int alt42=2;
						int LA42_0 = input.LA(1);
						if ( (LA42_0==ASTERISK||LA42_0==COL_TAB||LA42_0==DOUBLEQUOTED_STRING||LA42_0==ID||LA42_0==QUOTED_STRING) ) {
							alt42=1;
						}

						switch (alt42) {
						case 1 :
							// MySQLWalker.g:416:38: identifiedOrQuotedString
							{
							pushFollow(FOLLOW_identifiedOrQuotedString_in_displayed_column1256);
							identifiedOrQuotedString();
							state._fsp--;

							}
							break;

						default :
							break loop42;
						}
					}

					// MySQLWalker.g:416:65: ( alias )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==AS) ) {
						alt43=1;
					}
					switch (alt43) {
						case 1 :
							// MySQLWalker.g:416:65: alias
							{
							pushFollow(FOLLOW_alias_in_displayed_column1260);
							alias62=alias();
							state._fsp--;

							}
							break;

					}

					match(input, Token.UP, null); 


								Concat concat = new Concat();
								concat.setAlias((alias62!=null?((MySQLWalker.alias_return)alias62).aliText:null));
								select.addColumn(concat);
							
					}
					break;
				case 3 :
					// MySQLWalker.g:422:3: ^( count ( distinct )? countColumn ( alias )? )
					{
					pushFollow(FOLLOW_count_in_displayed_column1271);
					count();
					state._fsp--;

					match(input, Token.DOWN, null); 
					// MySQLWalker.g:422:11: ( distinct )?
					int alt44=2;
					int LA44_0 = input.LA(1);
					if ( (LA44_0==85) ) {
						alt44=1;
					}
					switch (alt44) {
						case 1 :
							// MySQLWalker.g:422:11: distinct
							{
							pushFollow(FOLLOW_distinct_in_displayed_column1273);
							distinct64=distinct();
							state._fsp--;

							}
							break;

					}

					pushFollow(FOLLOW_countColumn_in_displayed_column1276);
					countColumn63=countColumn();
					state._fsp--;

					// MySQLWalker.g:422:33: ( alias )?
					int alt45=2;
					int LA45_0 = input.LA(1);
					if ( (LA45_0==AS) ) {
						alt45=1;
					}
					switch (alt45) {
						case 1 :
							// MySQLWalker.g:422:33: alias
							{
							pushFollow(FOLLOW_alias_in_displayed_column1278);
							alias65=alias();
							state._fsp--;

							}
							break;

					}

					match(input, Token.UP, null); 


							Count count = new Count();
							count.setTable((countColumn63!=null?((MySQLWalker.countColumn_return)countColumn63).infos:null).get(0).toString());
							count.setColumn((countColumn63!=null?((MySQLWalker.countColumn_return)countColumn63).infos:null).get(1).toString());
							count.setHasDistinct((distinct64!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(distinct64.start),input.getTreeAdaptor().getTokenStopIndex(distinct64.start))):null)!=null);
							count.setAlias((alias65!=null?((MySQLWalker.alias_return)alias65).aliText:null));
							select.addColumn(count);
						
					}
					break;
				case 4 :
					// MySQLWalker.g:430:3: ^( ID ( table_alias )? ( columnAnt )? ( alias )? )
					{
					ID66=(CommonTree)match(input,ID,FOLLOW_ID_in_displayed_column1287); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// MySQLWalker.g:430:8: ( table_alias )?
						int alt46=2;
						int LA46_0 = input.LA(1);
						if ( (LA46_0==COL_TAB) ) {
							alt46=1;
						}
						switch (alt46) {
							case 1 :
								// MySQLWalker.g:430:8: table_alias
								{
								pushFollow(FOLLOW_table_alias_in_displayed_column1289);
								table_alias68=table_alias();
								state._fsp--;

								}
								break;

						}

						// MySQLWalker.g:430:21: ( columnAnt )?
						int alt47=2;
						int LA47_0 = input.LA(1);
						if ( (LA47_0==ASTERISK||LA47_0==ID) ) {
							alt47=1;
						}
						switch (alt47) {
							case 1 :
								// MySQLWalker.g:430:21: columnAnt
								{
								pushFollow(FOLLOW_columnAnt_in_displayed_column1292);
								columnAnt69=columnAnt();
								state._fsp--;

								}
								break;

						}

						// MySQLWalker.g:430:32: ( alias )?
						int alt48=2;
						int LA48_0 = input.LA(1);
						if ( (LA48_0==AS) ) {
							alt48=1;
						}
						switch (alt48) {
							case 1 :
								// MySQLWalker.g:430:32: alias
								{
								pushFollow(FOLLOW_alias_in_displayed_column1295);
								alias67=alias();
								state._fsp--;

								}
								break;

						}

						match(input, Token.UP, null); 
					}


							GroupFunction func=groupFunc.get((ID66!=null?ID66.getText():null));
							func.setAlias((alias67!=null?((MySQLWalker.alias_return)alias67).aliText:null));
							func.setTable((table_alias68!=null?((MySQLWalker.table_alias_return)table_alias68).aText:null));
							func.setColumn((columnAnt69!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(columnAnt69.start),input.getTreeAdaptor().getTokenStopIndex(columnAnt69.start))):null));
							select.addColumn(func);
						
					}
					break;
				case 5 :
					// MySQLWalker.g:440:3: ^( EXPR expr_add[new BindIndexHolder()] ( alias )? )
					{
					match(input,EXPR,FOLLOW_EXPR_in_displayed_column1308); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expr_add_in_displayed_column1310);
					expr_add70=expr_add(new BindIndexHolder());
					state._fsp--;

					// MySQLWalker.g:440:42: ( alias )?
					int alt49=2;
					int LA49_0 = input.LA(1);
					if ( (LA49_0==AS) ) {
						alt49=1;
					}
					switch (alt49) {
						case 1 :
							// MySQLWalker.g:440:42: alias
							{
							pushFollow(FOLLOW_alias_in_displayed_column1313);
							alias71=alias();
							state._fsp--;

							}
							break;

					}

					match(input, Token.UP, null); 


							if((expr_add70!=null?((MySQLWalker.expr_add_return)expr_add70).valueObjExpr:null) instanceof ColumnObject) {
								ColumnObject cobj = (ColumnObject)(expr_add70!=null?((MySQLWalker.expr_add_return)expr_add70).valueObjExpr:null);
								select.addColumn(cobj.getTable(), cobj.getColumnName(), (alias71!=null?((MySQLWalker.alias_return)alias71).aliText:null));
							} else {
								select.addColumn("", (expr_add70!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(expr_add70.start),input.getTreeAdaptor().getTokenStopIndex(expr_add70.start))):null), (alias71!=null?((MySQLWalker.alias_return)alias71).aliText:null));
							}
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "displayed_column"


	public static class columnAnt_return extends TreeRuleReturnScope {
		public String aText;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "columnAnt"
	// MySQLWalker.g:451:1: columnAnt returns [String aText] : ( ASTERISK | identifier );
	public final MySQLWalker.columnAnt_return columnAnt() throws RecognitionException {
		MySQLWalker.columnAnt_return retval = new MySQLWalker.columnAnt_return();
		retval.start = input.LT(1);

		CommonTree ASTERISK72=null;
		TreeRuleReturnScope identifier73 =null;

		try {
			// MySQLWalker.g:452:2: ( ASTERISK | identifier )
			int alt51=2;
			int LA51_0 = input.LA(1);
			if ( (LA51_0==ASTERISK) ) {
				alt51=1;
			}
			else if ( (LA51_0==ID) ) {
				alt51=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 51, 0, input);
				throw nvae;
			}

			switch (alt51) {
				case 1 :
					// MySQLWalker.g:452:3: ASTERISK
					{
					ASTERISK72=(CommonTree)match(input,ASTERISK,FOLLOW_ASTERISK_in_columnAnt1336); 
					retval.aText =(ASTERISK72!=null?ASTERISK72.getText():null);
					}
					break;
				case 2 :
					// MySQLWalker.g:453:3: identifier
					{
					pushFollow(FOLLOW_identifier_in_columnAnt1342);
					identifier73=identifier();
					state._fsp--;

					retval.aText =(identifier73!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier73.start),input.getTreeAdaptor().getTokenStopIndex(identifier73.start))):null);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnAnt"


	public static class quoted_string_return extends TreeRuleReturnScope {
		public String aText;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "quoted_string"
	// MySQLWalker.g:456:1: quoted_string returns [String aText] : ( QUOTED_STRING | DOUBLEQUOTED_STRING );
	public final MySQLWalker.quoted_string_return quoted_string() throws RecognitionException {
		MySQLWalker.quoted_string_return retval = new MySQLWalker.quoted_string_return();
		retval.start = input.LT(1);

		CommonTree QUOTED_STRING74=null;
		CommonTree DOUBLEQUOTED_STRING75=null;

		try {
			// MySQLWalker.g:457:2: ( QUOTED_STRING | DOUBLEQUOTED_STRING )
			int alt52=2;
			int LA52_0 = input.LA(1);
			if ( (LA52_0==QUOTED_STRING) ) {
				alt52=1;
			}
			else if ( (LA52_0==DOUBLEQUOTED_STRING) ) {
				alt52=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 52, 0, input);
				throw nvae;
			}

			switch (alt52) {
				case 1 :
					// MySQLWalker.g:457:4: QUOTED_STRING
					{
					QUOTED_STRING74=(CommonTree)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_quoted_string1358); 
					retval.aText = (QUOTED_STRING74!=null?QUOTED_STRING74.getText():null).substring(1, (QUOTED_STRING74!=null?QUOTED_STRING74.getText():null).length() - 1);
					}
					break;
				case 2 :
					// MySQLWalker.g:458:4: DOUBLEQUOTED_STRING
					{
					DOUBLEQUOTED_STRING75=(CommonTree)match(input,DOUBLEQUOTED_STRING,FOLLOW_DOUBLEQUOTED_STRING_in_quoted_string1365); 
					retval.aText = (DOUBLEQUOTED_STRING75!=null?DOUBLEQUOTED_STRING75.getText():null).substring(1, (DOUBLEQUOTED_STRING75!=null?DOUBLEQUOTED_STRING75.getText():null).length() - 1);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "quoted_string"


	public static class identifier_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "identifier"
	// MySQLWalker.g:461:1: identifier : ( ID | ASTERISK );
	public final MySQLWalker.identifier_return identifier() throws RecognitionException {
		MySQLWalker.identifier_return retval = new MySQLWalker.identifier_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:462:2: ( ID | ASTERISK )
			// MySQLWalker.g:
			{
			if ( input.LA(1)==ASTERISK||input.LA(1)==ID ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identifier"


	public static class table_alias_return extends TreeRuleReturnScope {
		public String aText;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "table_alias"
	// MySQLWalker.g:465:1: table_alias returns [String aText] : ^( COL_TAB identifier ) ;
	public final MySQLWalker.table_alias_return table_alias() throws RecognitionException {
		MySQLWalker.table_alias_return retval = new MySQLWalker.table_alias_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope identifier76 =null;

		try {
			// MySQLWalker.g:466:2: ( ^( COL_TAB identifier ) )
			// MySQLWalker.g:466:3: ^( COL_TAB identifier )
			{
			match(input,COL_TAB,FOLLOW_COL_TAB_in_table_alias1398); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_identifier_in_table_alias1400);
			identifier76=identifier();
			state._fsp--;

			retval.aText =(identifier76!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier76.start),input.getTreeAdaptor().getTokenStopIndex(identifier76.start))):null);
			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "table_alias"


	public static class alias_return extends TreeRuleReturnScope {
		public String aliText;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "alias"
	// MySQLWalker.g:469:1: alias returns [String aliText] : ^( AS identifier ) ;
	public final MySQLWalker.alias_return alias() throws RecognitionException {
		MySQLWalker.alias_return retval = new MySQLWalker.alias_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope identifier77 =null;

		try {
			// MySQLWalker.g:470:2: ( ^( AS identifier ) )
			// MySQLWalker.g:470:3: ^( AS identifier )
			{
			match(input,AS,FOLLOW_AS_in_alias1419); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_identifier_in_alias1421);
			identifier77=identifier();
			state._fsp--;

			match(input, Token.UP, null); 

			retval.aliText =(identifier77!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier77.start),input.getTreeAdaptor().getTokenStopIndex(identifier77.start))):null);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "alias"


	public static class select_command_return extends TreeRuleReturnScope {
		public Select select;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "select_command"
	// MySQLWalker.g:477:1: select_command returns [Select select] : selectClause[$select] ( fromClause[$select] )? ( joinClause[$select] )* ( whereClause[$select.getWhere()] )? ( groupByClause[$select.getWhere()] )? ( orderByClause[$select.getWhere()] )? ( limitClause[(MyWhereCondition)$select.getWhere()] )? ;
	public final MySQLWalker.select_command_return select_command() throws RecognitionException {
		MySQLWalker.select_command_return retval = new MySQLWalker.select_command_return();
		retval.start = input.LT(1);

		retval.select =new MySelect();
		try {
			// MySQLWalker.g:479:6: ( selectClause[$select] ( fromClause[$select] )? ( joinClause[$select] )* ( whereClause[$select.getWhere()] )? ( groupByClause[$select.getWhere()] )? ( orderByClause[$select.getWhere()] )? ( limitClause[(MyWhereCondition)$select.getWhere()] )? )
			// MySQLWalker.g:479:8: selectClause[$select] ( fromClause[$select] )? ( joinClause[$select] )* ( whereClause[$select.getWhere()] )? ( groupByClause[$select.getWhere()] )? ( orderByClause[$select.getWhere()] )? ( limitClause[(MyWhereCondition)$select.getWhere()] )?
			{
			pushFollow(FOLLOW_selectClause_in_select_command1450);
			selectClause(retval.select);
			state._fsp--;

			// MySQLWalker.g:479:30: ( fromClause[$select] )?
			int alt53=2;
			int LA53_0 = input.LA(1);
			if ( (LA53_0==TABLENAMES) ) {
				alt53=1;
			}
			switch (alt53) {
				case 1 :
					// MySQLWalker.g:479:31: fromClause[$select]
					{
					pushFollow(FOLLOW_fromClause_in_select_command1454);
					fromClause(retval.select);
					state._fsp--;

					}
					break;

			}

			// MySQLWalker.g:479:53: ( joinClause[$select] )*
			loop54:
			while (true) {
				int alt54=2;
				int LA54_0 = input.LA(1);
				if ( (LA54_0==94||(LA54_0 >= 98 && LA54_0 <= 99)||LA54_0==108) ) {
					alt54=1;
				}

				switch (alt54) {
				case 1 :
					// MySQLWalker.g:479:54: joinClause[$select]
					{
					pushFollow(FOLLOW_joinClause_in_select_command1460);
					joinClause(retval.select);
					state._fsp--;

					}
					break;

				default :
					break loop54;
				}
			}

			// MySQLWalker.g:479:76: ( whereClause[$select.getWhere()] )?
			int alt55=2;
			int LA55_0 = input.LA(1);
			if ( (LA55_0==WHERE) ) {
				alt55=1;
			}
			switch (alt55) {
				case 1 :
					// MySQLWalker.g:479:77: whereClause[$select.getWhere()]
					{
					pushFollow(FOLLOW_whereClause_in_select_command1466);
					whereClause(retval.select.getWhere());
					state._fsp--;

					}
					break;

			}

			// MySQLWalker.g:479:111: ( groupByClause[$select.getWhere()] )?
			int alt56=2;
			int LA56_0 = input.LA(1);
			if ( (LA56_0==GROUPBY) ) {
				alt56=1;
			}
			switch (alt56) {
				case 1 :
					// MySQLWalker.g:479:112: groupByClause[$select.getWhere()]
					{
					pushFollow(FOLLOW_groupByClause_in_select_command1472);
					groupByClause(retval.select.getWhere());
					state._fsp--;

					}
					break;

			}

			// MySQLWalker.g:479:148: ( orderByClause[$select.getWhere()] )?
			int alt57=2;
			int LA57_0 = input.LA(1);
			if ( (LA57_0==ORDERBY) ) {
				alt57=1;
			}
			switch (alt57) {
				case 1 :
					// MySQLWalker.g:479:149: orderByClause[$select.getWhere()]
					{
					pushFollow(FOLLOW_orderByClause_in_select_command1478);
					orderByClause(retval.select.getWhere());
					state._fsp--;

					}
					break;

			}

			// MySQLWalker.g:479:186: ( limitClause[(MyWhereCondition)$select.getWhere()] )?
			int alt58=2;
			int LA58_0 = input.LA(1);
			if ( (LA58_0==101) ) {
				alt58=1;
			}
			switch (alt58) {
				case 1 :
					// MySQLWalker.g:479:187: limitClause[(MyWhereCondition)$select.getWhere()]
					{
					pushFollow(FOLLOW_limitClause_in_select_command1485);
					limitClause((MyWhereCondition)retval.select.getWhere());
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "select_command"


	public static class groupByClause_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "groupByClause"
	// MySQLWalker.g:481:1: groupByClause[WhereCondition where] : ^( GROUPBY groupByColumns[$where] ) ;
	public final MySQLWalker.groupByClause_return groupByClause(WhereCondition where) throws RecognitionException {
		MySQLWalker.groupByClause_return retval = new MySQLWalker.groupByClause_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:482:2: ( ^( GROUPBY groupByColumns[$where] ) )
			// MySQLWalker.g:482:4: ^( GROUPBY groupByColumns[$where] )
			{
			match(input,GROUPBY,FOLLOW_GROUPBY_in_groupByClause1505); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_groupByColumns_in_groupByClause1507);
			groupByColumns(where);
			state._fsp--;

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "groupByClause"


	public static class groupByColumns_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "groupByColumns"
	// MySQLWalker.g:484:1: groupByColumns[WhereCondition where] : ( groupByColumn[$where] )+ ;
	public final MySQLWalker.groupByColumns_return groupByColumns(WhereCondition where) throws RecognitionException {
		MySQLWalker.groupByColumns_return retval = new MySQLWalker.groupByColumns_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:485:2: ( ( groupByColumn[$where] )+ )
			// MySQLWalker.g:485:4: ( groupByColumn[$where] )+
			{
			// MySQLWalker.g:485:4: ( groupByColumn[$where] )+
			int cnt59=0;
			loop59:
			while (true) {
				int alt59=2;
				int LA59_0 = input.LA(1);
				if ( (LA59_0==ASTERISK||LA59_0==ID) ) {
					alt59=1;
				}

				switch (alt59) {
				case 1 :
					// MySQLWalker.g:485:5: groupByColumn[$where]
					{
					pushFollow(FOLLOW_groupByColumn_in_groupByColumns1522);
					groupByColumn(where);
					state._fsp--;

					}
					break;

				default :
					if ( cnt59 >= 1 ) break loop59;
					EarlyExitException eee = new EarlyExitException(59, input);
					throw eee;
				}
				cnt59++;
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "groupByColumns"


	public static class groupByColumn_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "groupByColumn"
	// MySQLWalker.g:487:1: groupByColumn[WhereCondition where] : identifier ;
	public final MySQLWalker.groupByColumn_return groupByColumn(WhereCondition where) throws RecognitionException {
		MySQLWalker.groupByColumn_return retval = new MySQLWalker.groupByColumn_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope identifier78 =null;

		try {
			// MySQLWalker.g:488:2: ( identifier )
			// MySQLWalker.g:488:4: identifier
			{
			pushFollow(FOLLOW_identifier_in_groupByColumn1537);
			identifier78=identifier();
			state._fsp--;

			where.addGroupByColumn((identifier78!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier78.start),input.getTreeAdaptor().getTokenStopIndex(identifier78.start))):null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "groupByColumn"


	public static class delete_command_return extends TreeRuleReturnScope {
		public Delete del;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "delete_command"
	// MySQLWalker.g:490:6: delete_command returns [Delete del] : ^( DELETE tables[$del, true] ( whereClause[$del.getWhere()] )? ( orderByClause[$del.getWhere()] )? ( limitClause[(MyWhereCondition)$del.getWhere()] )? ) ;
	public final MySQLWalker.delete_command_return delete_command() throws RecognitionException {
		MySQLWalker.delete_command_return retval = new MySQLWalker.delete_command_return();
		retval.start = input.LT(1);

		retval.del =new MyDelete();
		try {
			// MySQLWalker.g:492:2: ( ^( DELETE tables[$del, true] ( whereClause[$del.getWhere()] )? ( orderByClause[$del.getWhere()] )? ( limitClause[(MyWhereCondition)$del.getWhere()] )? ) )
			// MySQLWalker.g:492:3: ^( DELETE tables[$del, true] ( whereClause[$del.getWhere()] )? ( orderByClause[$del.getWhere()] )? ( limitClause[(MyWhereCondition)$del.getWhere()] )? )
			{
			match(input,DELETE,FOLLOW_DELETE_in_delete_command1561); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_tables_in_delete_command1563);
			tables(retval.del, true);
			state._fsp--;

			// MySQLWalker.g:492:31: ( whereClause[$del.getWhere()] )?
			int alt60=2;
			int LA60_0 = input.LA(1);
			if ( (LA60_0==WHERE) ) {
				alt60=1;
			}
			switch (alt60) {
				case 1 :
					// MySQLWalker.g:492:31: whereClause[$del.getWhere()]
					{
					pushFollow(FOLLOW_whereClause_in_delete_command1566);
					whereClause(retval.del.getWhere());
					state._fsp--;

					}
					break;

			}

			// MySQLWalker.g:492:61: ( orderByClause[$del.getWhere()] )?
			int alt61=2;
			int LA61_0 = input.LA(1);
			if ( (LA61_0==ORDERBY) ) {
				alt61=1;
			}
			switch (alt61) {
				case 1 :
					// MySQLWalker.g:492:61: orderByClause[$del.getWhere()]
					{
					pushFollow(FOLLOW_orderByClause_in_delete_command1570);
					orderByClause(retval.del.getWhere());
					state._fsp--;

					}
					break;

			}

			// MySQLWalker.g:492:93: ( limitClause[(MyWhereCondition)$del.getWhere()] )?
			int alt62=2;
			int LA62_0 = input.LA(1);
			if ( (LA62_0==101) ) {
				alt62=1;
			}
			switch (alt62) {
				case 1 :
					// MySQLWalker.g:492:93: limitClause[(MyWhereCondition)$del.getWhere()]
					{
					pushFollow(FOLLOW_limitClause_in_delete_command1574);
					limitClause((MyWhereCondition)retval.del.getWhere());
					state._fsp--;

					}
					break;

			}

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "delete_command"


	public static class update_command_return extends TreeRuleReturnScope {
		public Update update;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "update_command"
	// MySQLWalker.g:494:1: update_command returns [Update update] : ^( UPDATE tables[$update, true] setclause[$update] ( whereClause[$update.getWhere()] )? ( limitClause[(MyWhereCondition)$update.getWhere()] )? ) ;
	public final MySQLWalker.update_command_return update_command() throws RecognitionException {
		MySQLWalker.update_command_return retval = new MySQLWalker.update_command_return();
		retval.start = input.LT(1);

		retval.update =new MyUpdate();
		try {
			// MySQLWalker.g:496:2: ( ^( UPDATE tables[$update, true] setclause[$update] ( whereClause[$update.getWhere()] )? ( limitClause[(MyWhereCondition)$update.getWhere()] )? ) )
			// MySQLWalker.g:496:3: ^( UPDATE tables[$update, true] setclause[$update] ( whereClause[$update.getWhere()] )? ( limitClause[(MyWhereCondition)$update.getWhere()] )? )
			{
			match(input,UPDATE,FOLLOW_UPDATE_in_update_command1594); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_tables_in_update_command1596);
			tables(retval.update, true);
			state._fsp--;

			pushFollow(FOLLOW_setclause_in_update_command1599);
			setclause(retval.update);
			state._fsp--;

			// MySQLWalker.g:496:53: ( whereClause[$update.getWhere()] )?
			int alt63=2;
			int LA63_0 = input.LA(1);
			if ( (LA63_0==WHERE) ) {
				alt63=1;
			}
			switch (alt63) {
				case 1 :
					// MySQLWalker.g:496:53: whereClause[$update.getWhere()]
					{
					pushFollow(FOLLOW_whereClause_in_update_command1602);
					whereClause(retval.update.getWhere());
					state._fsp--;

					}
					break;

			}

			// MySQLWalker.g:496:86: ( limitClause[(MyWhereCondition)$update.getWhere()] )?
			int alt64=2;
			int LA64_0 = input.LA(1);
			if ( (LA64_0==101) ) {
				alt64=1;
			}
			switch (alt64) {
				case 1 :
					// MySQLWalker.g:496:86: limitClause[(MyWhereCondition)$update.getWhere()]
					{
					pushFollow(FOLLOW_limitClause_in_update_command1606);
					limitClause((MyWhereCondition)retval.update.getWhere());
					state._fsp--;

					}
					break;

			}

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "update_command"


	public static class limitClause_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "limitClause"
	// MySQLWalker.g:499:1: limitClause[MyWhereCondition where] : ^( 'LIMIT' ( skip[$where] )? range[$where] ) ;
	public final MySQLWalker.limitClause_return limitClause(MyWhereCondition where) throws RecognitionException {
		MySQLWalker.limitClause_return retval = new MySQLWalker.limitClause_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:500:2: ( ^( 'LIMIT' ( skip[$where] )? range[$where] ) )
			// MySQLWalker.g:500:3: ^( 'LIMIT' ( skip[$where] )? range[$where] )
			{
			match(input,101,FOLLOW_101_in_limitClause1622); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:500:13: ( skip[$where] )?
			int alt65=2;
			int LA65_0 = input.LA(1);
			if ( (LA65_0==SKIP) ) {
				alt65=1;
			}
			switch (alt65) {
				case 1 :
					// MySQLWalker.g:500:13: skip[$where]
					{
					pushFollow(FOLLOW_skip_in_limitClause1624);
					skip(where);
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_range_in_limitClause1628);
			range(where);
			state._fsp--;

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "limitClause"


	public static class skip_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "skip"
	// MySQLWalker.g:502:1: skip[MyWhereCondition where] : ( ^( SKIP N ) | ^( SKIP a= '?' ) );
	public final MySQLWalker.skip_return skip(MyWhereCondition where) throws RecognitionException {
		MySQLWalker.skip_return retval = new MySQLWalker.skip_return();
		retval.start = input.LT(1);

		CommonTree a=null;
		CommonTree N79=null;

		try {
			// MySQLWalker.g:503:2: ( ^( SKIP N ) | ^( SKIP a= '?' ) )
			int alt66=2;
			int LA66_0 = input.LA(1);
			if ( (LA66_0==SKIP) ) {
				int LA66_1 = input.LA(2);
				if ( (LA66_1==DOWN) ) {
					int LA66_2 = input.LA(3);
					if ( (LA66_2==N) ) {
						alt66=1;
					}
					else if ( (LA66_2==77) ) {
						alt66=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 66, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 66, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 66, 0, input);
				throw nvae;
			}

			switch (alt66) {
				case 1 :
					// MySQLWalker.g:503:3: ^( SKIP N )
					{
					match(input,SKIP,FOLLOW_SKIP_in_skip1642); 
					match(input, Token.DOWN, null); 
					N79=(CommonTree)match(input,N,FOLLOW_N_in_skip1644); 
					match(input, Token.UP, null); 


							where.setStart(Integer.valueOf((N79!=null?N79.getText():null)));
							where.limitInfo.skipIdx=(N79!=null?N79.getCharPositionInLine():0);
							where.limitInfo.skip=(N79!=null?N79.getText():null);
						
					}
					break;
				case 2 :
					// MySQLWalker.g:508:3: ^( SKIP a= '?' )
					{
					match(input,SKIP,FOLLOW_SKIP_in_skip1651); 
					match(input, Token.DOWN, null); 
					a=(CommonTree)match(input,77,FOLLOW_77_in_skip1655); 
					match(input, Token.UP, null); 


							where.setStart(DbFunctions.bindVar(where.selfAddAndGet()));
							where.limitInfo.skipIdx=(a!=null?a.getCharPositionInLine():0);
							where.limitInfo.skip=(a!=null?a.getText():null);
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "skip"


	public static class range_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "range"
	// MySQLWalker.g:515:1: range[MyWhereCondition where] : ( ^( RANGE N ) | ^( RANGE a= '?' ) );
	public final MySQLWalker.range_return range(MyWhereCondition where) throws RecognitionException {
		MySQLWalker.range_return retval = new MySQLWalker.range_return();
		retval.start = input.LT(1);

		CommonTree a=null;
		CommonTree N80=null;

		try {
			// MySQLWalker.g:515:31: ( ^( RANGE N ) | ^( RANGE a= '?' ) )
			int alt67=2;
			int LA67_0 = input.LA(1);
			if ( (LA67_0==RANGE) ) {
				int LA67_1 = input.LA(2);
				if ( (LA67_1==DOWN) ) {
					int LA67_2 = input.LA(3);
					if ( (LA67_2==N) ) {
						alt67=1;
					}
					else if ( (LA67_2==77) ) {
						alt67=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 67, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 67, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 67, 0, input);
				throw nvae;
			}

			switch (alt67) {
				case 1 :
					// MySQLWalker.g:515:32: ^( RANGE N )
					{
					match(input,RANGE,FOLLOW_RANGE_in_range1670); 
					match(input, Token.DOWN, null); 
					N80=(CommonTree)match(input,N,FOLLOW_N_in_range1672); 
					match(input, Token.UP, null); 


							where.setRange(Integer.valueOf((N80!=null?N80.getText():null)));
							where.limitInfo.rangeIdx=(N80!=null?N80.getCharPositionInLine():0);
							where.limitInfo.range=(N80!=null?N80.getText():null);
						
					}
					break;
				case 2 :
					// MySQLWalker.g:520:2: ^( RANGE a= '?' )
					{
					match(input,RANGE,FOLLOW_RANGE_in_range1678); 
					match(input, Token.DOWN, null); 
					a=(CommonTree)match(input,77,FOLLOW_77_in_range1682); 
					match(input, Token.UP, null); 


							where.setRange(DbFunctions.bindVar(where.selfAddAndGet()));
							where.limitInfo.rangeIdx=(a!=null?a.getCharPositionInLine():0);
							where.limitInfo.range=(a!=null?a.getText():null);
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "range"


	protected static class joinClause_scope {
		MyWhereCondition fakeCondition;
	}
	protected Stack<joinClause_scope> joinClause_stack = new Stack<joinClause_scope>();

	public static class joinClause_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "joinClause"
	// MySQLWalker.g:526:1: joinClause[Select select] : ^( joinType table_spec[$select, true] ( alias )? 'ON' sqlCondition[$joinClause::fakeCondition] ) ;
	public final MySQLWalker.joinClause_return joinClause(Select select) throws RecognitionException {
		joinClause_stack.push(new joinClause_scope());
		MySQLWalker.joinClause_return retval = new MySQLWalker.joinClause_return();
		retval.start = input.LT(1);

		joinClause_stack.peek().fakeCondition = new MyWhereCondition();
		try {
			// MySQLWalker.g:529:2: ( ^( joinType table_spec[$select, true] ( alias )? 'ON' sqlCondition[$joinClause::fakeCondition] ) )
			// MySQLWalker.g:529:4: ^( joinType table_spec[$select, true] ( alias )? 'ON' sqlCondition[$joinClause::fakeCondition] )
			{
			pushFollow(FOLLOW_joinType_in_joinClause1706);
			joinType();
			state._fsp--;

			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_table_spec_in_joinClause1708);
			table_spec(select, true);
			state._fsp--;

			// MySQLWalker.g:529:41: ( alias )?
			int alt68=2;
			int LA68_0 = input.LA(1);
			if ( (LA68_0==AS) ) {
				alt68=1;
			}
			switch (alt68) {
				case 1 :
					// MySQLWalker.g:529:41: alias
					{
					pushFollow(FOLLOW_alias_in_joinClause1711);
					alias();
					state._fsp--;

					}
					break;

			}

			match(input,105,FOLLOW_105_in_joinClause1714); 
			pushFollow(FOLLOW_sqlCondition_in_joinClause1716);
			sqlCondition(joinClause_stack.peek().fakeCondition);
			state._fsp--;

			match(input, Token.UP, null); 

			select.incrementBindIndex(joinClause_stack.peek().fakeCondition.selfAddAndGet());
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
			joinClause_stack.pop();
		}
		return retval;
	}
	// $ANTLR end "joinClause"


	public static class joinType_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "joinType"
	// MySQLWalker.g:531:1: joinType : ( 'INNER JOIN' | 'LEFT JOIN' | 'RIGHT JOIN' | 'JOIN' );
	public final MySQLWalker.joinType_return joinType() throws RecognitionException {
		MySQLWalker.joinType_return retval = new MySQLWalker.joinType_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:532:2: ( 'INNER JOIN' | 'LEFT JOIN' | 'RIGHT JOIN' | 'JOIN' )
			// MySQLWalker.g:
			{
			if ( input.LA(1)==94||(input.LA(1) >= 98 && input.LA(1) <= 99)||input.LA(1)==108 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "joinType"


	public static class concat_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "concat"
	// MySQLWalker.g:534:1: concat : 'CONCAT' ;
	public final MySQLWalker.concat_return concat() throws RecognitionException {
		MySQLWalker.concat_return retval = new MySQLWalker.concat_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:535:2: ( 'CONCAT' )
			// MySQLWalker.g:535:4: 'CONCAT'
			{
			match(input,82,FOLLOW_82_in_concat1752); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "concat"


	public static class identifiedOrQuotedString_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "identifiedOrQuotedString"
	// MySQLWalker.g:537:1: identifiedOrQuotedString : ( ( ( table_alias )? identifier ) | quoted_string ) ;
	public final MySQLWalker.identifiedOrQuotedString_return identifiedOrQuotedString() throws RecognitionException {
		MySQLWalker.identifiedOrQuotedString_return retval = new MySQLWalker.identifiedOrQuotedString_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:538:2: ( ( ( ( table_alias )? identifier ) | quoted_string ) )
			// MySQLWalker.g:538:4: ( ( ( table_alias )? identifier ) | quoted_string )
			{
			// MySQLWalker.g:538:4: ( ( ( table_alias )? identifier ) | quoted_string )
			int alt70=2;
			int LA70_0 = input.LA(1);
			if ( (LA70_0==ASTERISK||LA70_0==COL_TAB||LA70_0==ID) ) {
				alt70=1;
			}
			else if ( (LA70_0==DOUBLEQUOTED_STRING||LA70_0==QUOTED_STRING) ) {
				alt70=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 70, 0, input);
				throw nvae;
			}

			switch (alt70) {
				case 1 :
					// MySQLWalker.g:538:6: ( ( table_alias )? identifier )
					{
					// MySQLWalker.g:538:6: ( ( table_alias )? identifier )
					// MySQLWalker.g:538:7: ( table_alias )? identifier
					{
					// MySQLWalker.g:538:7: ( table_alias )?
					int alt69=2;
					int LA69_0 = input.LA(1);
					if ( (LA69_0==COL_TAB) ) {
						alt69=1;
					}
					switch (alt69) {
						case 1 :
							// MySQLWalker.g:538:7: table_alias
							{
							pushFollow(FOLLOW_table_alias_in_identifiedOrQuotedString1765);
							table_alias();
							state._fsp--;

							}
							break;

					}

					pushFollow(FOLLOW_identifier_in_identifiedOrQuotedString1768);
					identifier();
					state._fsp--;

					}

					}
					break;
				case 2 :
					// MySQLWalker.g:538:34: quoted_string
					{
					pushFollow(FOLLOW_quoted_string_in_identifiedOrQuotedString1773);
					quoted_string();
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identifiedOrQuotedString"


	public static class distinct_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "distinct"
	// MySQLWalker.g:540:1: distinct : 'DISTINCT' ;
	public final MySQLWalker.distinct_return distinct() throws RecognitionException {
		MySQLWalker.distinct_return retval = new MySQLWalker.distinct_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:541:2: ( 'DISTINCT' )
			// MySQLWalker.g:541:4: 'DISTINCT'
			{
			match(input,85,FOLLOW_85_in_distinct1785); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "distinct"


	public static class count_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "count"
	// MySQLWalker.g:543:1: count : 'COUNT' ;
	public final MySQLWalker.count_return count() throws RecognitionException {
		MySQLWalker.count_return retval = new MySQLWalker.count_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:544:2: ( 'COUNT' )
			// MySQLWalker.g:544:4: 'COUNT'
			{
			match(input,83,FOLLOW_83_in_count1795); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "count"


	public static class between_and_expression_return extends TreeRuleReturnScope {
		public Object valueObj;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "between_and_expression"
	// MySQLWalker.g:546:1: between_and_expression[BindIndexHolder where] returns [Object valueObj] : ( expr_add[$where] | quoted_string );
	public final MySQLWalker.between_and_expression_return between_and_expression(BindIndexHolder where) throws RecognitionException {
		MySQLWalker.between_and_expression_return retval = new MySQLWalker.between_and_expression_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope expr_add81 =null;
		TreeRuleReturnScope quoted_string82 =null;

		try {
			// MySQLWalker.g:547:2: ( expr_add[$where] | quoted_string )
			int alt71=2;
			int LA71_0 = input.LA(1);
			if ( (LA71_0==ASTERISK||LA71_0==COLUMN||LA71_0==DIVIDE||LA71_0==ID||(LA71_0 >= MINUS && LA71_0 <= N)||LA71_0==NUMBER||LA71_0==PLUS||LA71_0==QUTED_STR||LA71_0==77||LA71_0==86||LA71_0==104||LA71_0==109||LA71_0==112) ) {
				alt71=1;
			}
			else if ( (LA71_0==DOUBLEQUOTED_STRING||LA71_0==QUOTED_STRING) ) {
				alt71=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 71, 0, input);
				throw nvae;
			}

			switch (alt71) {
				case 1 :
					// MySQLWalker.g:547:3: expr_add[$where]
					{
					pushFollow(FOLLOW_expr_add_in_between_and_expression1809);
					expr_add81=expr_add(where);
					state._fsp--;

					retval.valueObj =(expr_add81!=null?((MySQLWalker.expr_add_return)expr_add81).valueObjExpr:null);
					}
					break;
				case 2 :
					// MySQLWalker.g:548:4: quoted_string
					{
					pushFollow(FOLLOW_quoted_string_in_between_and_expression1817);
					quoted_string82=quoted_string();
					state._fsp--;

					retval.valueObj = (quoted_string82!=null?((MySQLWalker.quoted_string_return)quoted_string82).aText:null);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "between_and_expression"


	public static class identifierOrN_return extends TreeRuleReturnScope {
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "identifierOrN"
	// MySQLWalker.g:550:1: identifierOrN : ( identifier | N ) ;
	public final MySQLWalker.identifierOrN_return identifierOrN() throws RecognitionException {
		MySQLWalker.identifierOrN_return retval = new MySQLWalker.identifierOrN_return();
		retval.start = input.LT(1);

		try {
			// MySQLWalker.g:551:2: ( ( identifier | N ) )
			// MySQLWalker.g:552:2: ( identifier | N )
			{
			// MySQLWalker.g:552:2: ( identifier | N )
			int alt72=2;
			int LA72_0 = input.LA(1);
			if ( (LA72_0==ASTERISK||LA72_0==ID) ) {
				alt72=1;
			}
			else if ( (LA72_0==N) ) {
				alt72=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 72, 0, input);
				throw nvae;
			}

			switch (alt72) {
				case 1 :
					// MySQLWalker.g:552:3: identifier
					{
					pushFollow(FOLLOW_identifier_in_identifierOrN1831);
					identifier();
					state._fsp--;

					}
					break;
				case 2 :
					// MySQLWalker.g:552:16: N
					{
					match(input,N,FOLLOW_N_in_identifierOrN1835); 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identifierOrN"


	public static class countColumn_return extends TreeRuleReturnScope {
		public List infos;
		public StringTemplate st;
		public Object getTemplate() { return st; }
		public String toString() { return st==null?null:st.toString(); }
	};


	// $ANTLR start "countColumn"
	// MySQLWalker.g:554:1: countColumn returns [List infos] : ^( COUNTCOLUMN ( identifier )? identifierOrN ) ;
	public final MySQLWalker.countColumn_return countColumn() throws RecognitionException {
		MySQLWalker.countColumn_return retval = new MySQLWalker.countColumn_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope identifier83 =null;
		TreeRuleReturnScope identifierOrN84 =null;

		try {
			// MySQLWalker.g:555:2: ( ^( COUNTCOLUMN ( identifier )? identifierOrN ) )
			// MySQLWalker.g:555:4: ^( COUNTCOLUMN ( identifier )? identifierOrN )
			{
			match(input,COUNTCOLUMN,FOLLOW_COUNTCOLUMN_in_countColumn1851); 
			match(input, Token.DOWN, null); 
			// MySQLWalker.g:555:18: ( identifier )?
			int alt73=2;
			int LA73_0 = input.LA(1);
			if ( (LA73_0==ASTERISK||LA73_0==ID) ) {
				int LA73_1 = input.LA(2);
				if ( (LA73_1==ASTERISK||LA73_1==ID||LA73_1==N) ) {
					alt73=1;
				}
			}
			switch (alt73) {
				case 1 :
					// MySQLWalker.g:555:18: identifier
					{
					pushFollow(FOLLOW_identifier_in_countColumn1853);
					identifier83=identifier();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_identifierOrN_in_countColumn1856);
			identifierOrN84=identifierOrN();
			state._fsp--;

			match(input, Token.UP, null); 


					retval.infos = new ArrayList(2);
					retval.infos.add((identifier83!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier83.start),input.getTreeAdaptor().getTokenStopIndex(identifier83.start))):null)==null?"":(identifier83!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifier83.start),input.getTreeAdaptor().getTokenStopIndex(identifier83.start))):null));
					retval.infos.add((identifierOrN84!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifierOrN84.start),input.getTreeAdaptor().getTokenStopIndex(identifierOrN84.start))):null)==null?"":(identifierOrN84!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(identifierOrN84.start),input.getTreeAdaptor().getTokenStopIndex(identifierOrN84.start))):null));
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "countColumn"

	// Delegated rules



	public static final BitSet FOLLOW_start_rule_in_beg53 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_select_command_in_start_rule70 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insert_command_in_start_rule75 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_update_command_in_start_rule80 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_command_in_start_rule85 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SET_in_setclause98 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_updateColumnSpecs_in_setclause100 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000020L});
	public static final BitSet FOLLOW_SET_ELE_in_updateColumnSpecs115 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_updateColumnSpec_in_updateColumnSpecs117 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EQ_in_updateColumnSpec131 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_alias_in_updateColumnSpec133 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_identifier_in_updateColumnSpec136 = new BitSet(new long[]{0x8903800200800A00L,0x0001210000402080L});
	public static final BitSet FOLLOW_expr_in_updateColumnSpec138 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INSERT_in_insert_command161 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_IGNORE_in_insert_command164 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_tables_in_insert_command168 = new BitSet(new long[]{0x0000004000001000L});
	public static final BitSet FOLLOW_column_specs_in_insert_command171 = new BitSet(new long[]{0x0000004000001000L});
	public static final BitSet FOLLOW_values_in_insert_command175 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INSERT_VAL_in_values187 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_values190 = new BitSet(new long[]{0x8903800200800A08L,0x0001210000402080L});
	public static final BitSet FOLLOW_COLUMNS_in_column_specs206 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_column_spec_in_column_specs208 = new BitSet(new long[]{0x0000000000000808L});
	public static final BitSet FOLLOW_COLUMN_in_column_spec222 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identifier_in_column_spec224 = new BitSet(new long[]{0x0000000200000208L});
	public static final BitSet FOLLOW_table_name_in_column_spec226 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WHERE_in_whereClause246 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_sqlCondition_in_whereClause248 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ORDERBY_in_orderByClause263 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_columnNamesAfterWhere_in_orderByClause265 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere278 = new BitSet(new long[]{0x0000000000100102L});
	public static final BitSet FOLLOW_ASC_in_columnNameAfterWhere292 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identifier_in_columnNameAfterWhere295 = new BitSet(new long[]{0x0000000000002008L});
	public static final BitSet FOLLOW_table_alias_in_columnNameAfterWhere297 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DESC_in_columnNameAfterWhere305 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identifier_in_columnNameAfterWhere308 = new BitSet(new long[]{0x0000000000002008L});
	public static final BitSet FOLLOW_table_alias_in_columnNameAfterWhere310 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SELECT_in_selectClause327 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_distinct_in_selectClause329 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_select_list_in_selectClause332 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CONDITION_OR_NOT_in_sqlCondition355 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_condition_in_sqlCondition357 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CONDITION_OR_in_sqlCondition364 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_condition_in_sqlCondition366 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_106_in_condition384 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_condition_in_condition388 = new BitSet(new long[]{0x20705B0948000408L,0x0000040000004000L});
	public static final BitSet FOLLOW_78_in_condition400 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_condition_in_condition402 = new BitSet(new long[]{0x20705B0948000408L,0x0000040000004000L});
	public static final BitSet FOLLOW_condition_PAREN_in_condition409 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIORITY_in_condition415 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_condition_in_condition417 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_condition_expr_in_condition_PAREN432 = new BitSet(new long[]{0x00705B0948000402L});
	public static final BitSet FOLLOW_comparisonCondition_in_condition_expr445 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inCondition_in_condition_expr451 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_isCondition_in_condition_expr458 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_likeCondition_in_condition_expr465 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_betweenCondition_in_condition_expr471 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_BETWEEN_in_betweenCondition483 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_between_and_in_betweenCondition485 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_betweenCondition488 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_BETWEEN_in_betweenCondition497 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_between_and_in_betweenCondition499 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_betweenCondition502 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_between_and_expression_in_between_and522 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_between_and_expression_in_between_and527 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NOT_LIKE_in_likeCondition543 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_likeCondition545 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_likeCondition548 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_LIKE_in_likeCondition556 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_likeCondition558 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_likeCondition561 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ISNOT_in_isCondition577 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_NULL_in_isCondition579 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_isCondition581 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IS_in_isCondition589 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_NULL_in_isCondition591 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_isCondition593 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EQ_in_comparisonCondition610 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_comparisonCondition612 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_comparisonCondition615 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NOT_EQ_in_comparisonCondition625 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_comparisonCondition627 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_comparisonCondition630 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_LTH_in_comparisonCondition640 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_comparisonCondition642 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_comparisonCondition645 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_GTH_in_comparisonCondition655 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_comparisonCondition657 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_comparisonCondition660 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_LEQ_in_comparisonCondition670 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_comparisonCondition672 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_comparisonCondition675 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_GEQ_in_comparisonCondition685 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_comparisonCondition687 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_comparisonCondition690 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CONDITION_LEFT_in_left_cond713 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_left_cond715 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IN_LISTS_in_in_list734 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_inCondition_expr_adds_in_in_list736 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IN_in_inCondition754 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_103_in_inCondition758 = new BitSet(new long[]{0x0000008000008000L,0x0000000000000080L});
	public static final BitSet FOLLOW_subquery_in_inCondition761 = new BitSet(new long[]{0x0000008000008000L});
	public static final BitSet FOLLOW_in_list_in_inCondition764 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_left_cond_in_inCondition769 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_expr_add_in_inCondition_expr_adds803 = new BitSet(new long[]{0x8903800200800A02L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_add_in_expr828 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subquery_in_expr835 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PLUS_in_expr_add862 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_add_in_expr_add866 = new BitSet(new long[]{0x8903800200800A00L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_add_in_expr_add871 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MINUS_in_expr_add880 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_add_in_expr_add884 = new BitSet(new long[]{0x8903800200800A00L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_add_in_expr_add889 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ASTERISK_in_expr_add897 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_add_in_expr_add901 = new BitSet(new long[]{0x8903800200800A00L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_add_in_expr_add906 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DIVIDE_in_expr_add914 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_add_in_expr_add918 = new BitSet(new long[]{0x8903800200800A00L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_add_in_expr_add923 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MOD_in_expr_add931 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_add_in_expr_add935 = new BitSet(new long[]{0x8903800200800A00L,0x0001210000402000L});
	public static final BitSet FOLLOW_expr_add_in_expr_add940 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_N_in_expr_add947 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_expr_add953 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_boolean_literal_in_expr_add958 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_104_in_expr_add962 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_109_in_expr_add968 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_expr_add972 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUTED_STR_in_expr_add978 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_quoted_string_in_expr_add980 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_COLUMN_in_expr_add987 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identifier_in_expr_add989 = new BitSet(new long[]{0x0000000200000208L});
	public static final BitSet FOLLOW_table_name_in_expr_add991 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_expr_add1002 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_expr_add1007 = new BitSet(new long[]{0x8903800200800A08L,0x0001210000402080L});
	public static final BitSet FOLLOW_N_in_value1025 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_value1029 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_77_in_value1033 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUTED_STR_in_value1038 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_quoted_string_in_value1040 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_112_in_boolean_literal1055 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_86_in_boolean_literal1064 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SELECT_LIST_in_select_list1080 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_displayed_column_in_select_list1082 = new BitSet(new long[]{0x4000000222000008L,0x00000000000C0000L});
	public static final BitSet FOLLOW_TABLENAMES_in_fromClause1107 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_in_fromClause1109 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000100L});
	public static final BitSet FOLLOW_TABLENAME_in_table1124 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_spec_in_table1126 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TABLENAMES_in_tables1139 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_in_tables1141 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000100L});
	public static final BitSet FOLLOW_schema_name_in_table_spec1156 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_table_name_in_table_spec1160 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_alias_in_table_spec1164 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subquery_in_table_spec1172 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_alias_in_table_spec1174 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_schema_name1189 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SUBQUERY_in_subquery1203 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_select_command_in_subquery1205 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_identifier_in_table_name1222 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quoted_string_in_displayed_column1240 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_alias_in_displayed_column1242 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_concat_in_displayed_column1251 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identifiedOrQuotedString_in_displayed_column1253 = new BitSet(new long[]{0x4000000202002288L});
	public static final BitSet FOLLOW_identifiedOrQuotedString_in_displayed_column1256 = new BitSet(new long[]{0x4000000202002288L});
	public static final BitSet FOLLOW_alias_in_displayed_column1260 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_count_in_displayed_column1271 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_distinct_in_displayed_column1273 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_countColumn_in_displayed_column1276 = new BitSet(new long[]{0x0000000000000088L});
	public static final BitSet FOLLOW_alias_in_displayed_column1278 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_displayed_column1287 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_alias_in_displayed_column1289 = new BitSet(new long[]{0x0000000200000288L});
	public static final BitSet FOLLOW_columnAnt_in_displayed_column1292 = new BitSet(new long[]{0x0000000000000088L});
	public static final BitSet FOLLOW_alias_in_displayed_column1295 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EXPR_in_displayed_column1308 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_add_in_displayed_column1310 = new BitSet(new long[]{0x0000000000000088L});
	public static final BitSet FOLLOW_alias_in_displayed_column1313 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ASTERISK_in_columnAnt1336 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_columnAnt1342 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_STRING_in_quoted_string1358 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLEQUOTED_STRING_in_quoted_string1365 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COL_TAB_in_table_alias1398 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identifier_in_table_alias1400 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_AS_in_alias1419 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identifier_in_alias1421 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_selectClause_in_select_command1450 = new BitSet(new long[]{0x0400000080000002L,0x0000102C40000A00L});
	public static final BitSet FOLLOW_fromClause_in_select_command1454 = new BitSet(new long[]{0x0400000080000002L,0x0000102C40000800L});
	public static final BitSet FOLLOW_joinClause_in_select_command1460 = new BitSet(new long[]{0x0400000080000002L,0x0000102C40000800L});
	public static final BitSet FOLLOW_whereClause_in_select_command1466 = new BitSet(new long[]{0x0400000080000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_groupByClause_in_select_command1472 = new BitSet(new long[]{0x0400000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_orderByClause_in_select_command1478 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_limitClause_in_select_command1485 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GROUPBY_in_groupByClause1505 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_groupByColumns_in_groupByClause1507 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_groupByColumn_in_groupByColumns1522 = new BitSet(new long[]{0x0000000200000202L});
	public static final BitSet FOLLOW_identifier_in_groupByColumn1537 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DELETE_in_delete_command1561 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_tables_in_delete_command1563 = new BitSet(new long[]{0x0400000000000008L,0x0000002000000800L});
	public static final BitSet FOLLOW_whereClause_in_delete_command1566 = new BitSet(new long[]{0x0400000000000008L,0x0000002000000000L});
	public static final BitSet FOLLOW_orderByClause_in_delete_command1570 = new BitSet(new long[]{0x0000000000000008L,0x0000002000000000L});
	public static final BitSet FOLLOW_limitClause_in_delete_command1574 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_UPDATE_in_update_command1594 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_tables_in_update_command1596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_setclause_in_update_command1599 = new BitSet(new long[]{0x0000000000000008L,0x0000002000000800L});
	public static final BitSet FOLLOW_whereClause_in_update_command1602 = new BitSet(new long[]{0x0000000000000008L,0x0000002000000000L});
	public static final BitSet FOLLOW_limitClause_in_update_command1606 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_101_in_limitClause1622 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_skip_in_limitClause1624 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_range_in_limitClause1628 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SKIP_in_skip1642 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_N_in_skip1644 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SKIP_in_skip1651 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_77_in_skip1655 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RANGE_in_range1670 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_N_in_range1672 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RANGE_in_range1678 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_77_in_range1682 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_joinType_in_joinClause1706 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_spec_in_joinClause1708 = new BitSet(new long[]{0x0000000000000080L,0x0000020000000000L});
	public static final BitSet FOLLOW_alias_in_joinClause1711 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_joinClause1714 = new BitSet(new long[]{0x0000000000030000L});
	public static final BitSet FOLLOW_sqlCondition_in_joinClause1716 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_82_in_concat1752 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_table_alias_in_identifiedOrQuotedString1765 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_identifier_in_identifiedOrQuotedString1768 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quoted_string_in_identifiedOrQuotedString1773 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_85_in_distinct1785 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_83_in_count1795 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_add_in_between_and_expression1809 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quoted_string_in_between_and_expression1817 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_identifierOrN1831 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_N_in_identifierOrN1835 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COUNTCOLUMN_in_countColumn1851 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identifier_in_countColumn1853 = new BitSet(new long[]{0x0002000200000200L});
	public static final BitSet FOLLOW_identifierOrN_in_countColumn1856 = new BitSet(new long[]{0x0000000000000008L});
}
