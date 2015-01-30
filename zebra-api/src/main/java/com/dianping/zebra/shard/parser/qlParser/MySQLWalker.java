// $ANTLR 3.3 Nov 30, 2010 12:45:30 MySQLWalker.g 2011-11-11 11:16:26

package com.dianping.zebra.shard.parser.qlParser;

import static com.dianping.zebra.shard.parser.util.DbFunctions.add;
import static com.dianping.zebra.shard.parser.util.DbFunctions.column;
import static com.dianping.zebra.shard.parser.util.DbFunctions.divide;
import static com.dianping.zebra.shard.parser.util.DbFunctions.mod;
import static com.dianping.zebra.shard.parser.util.DbFunctions.multiply;
import static com.dianping.zebra.shard.parser.util.DbFunctions.subtract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;
import org.antlr.runtime.tree.TreeRuleReturnScope;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.AngleBracketTemplateLexer;

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
public class MySQLWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "EXPR", "GROUPBY", "COUNTCOLUMN", "JOINTYPE", "ALIAS", "TABLENAME", "TABLENAMES", "SUBQUERY", "COLUMN", "AS", "SELECT", "DISPLAYED_COUNT_COLUMN", "DISPLAYED_COLUMN", "IN", "NOT", "SELECT_LIST", "QUTED_STR", "WHERE", "CONDITION_OR", "CONDITION_LEFT", "IN_LISTS", "CONDITION_OR_NOT", "AND", "OR", "ISNOT", "IS", "NULL", "NAN", "INFINITE", "LIKE", "NOT_LIKE", "NOT_BETWEEN", "BETWEEN", "ORDERBY", "INSERT", "INSERT_VAL", "PRIORITY", "COLUMNS", "UPDATE", "SET", "SET_ELE", "COL_TAB", "DELETE", "SKIP", "RANGE", "COMMA", "EQ", "LPAREN", "RPAREN", "PLUS", "MINUS", "DOUBLEVERTBAR", "ASTERISK", "DIVIDE", "MOD", "EXPONENT", "ID", "LTH", "GTH", "NOT_EQ", "LEQ", "GEQ", "ASC", "DESC", "DOT", "N", "NUMBER", "POINT", "ARROW", "QUOTED_STRING", "DOUBLEQUOTED_STRING", "WS", "'SET'", "'INSERT'", "'INTO'", "'VALUES'", "'ORDER'", "'BY'", "'SELECT'", "'WHERE'", "'NOT'", "'OR'", "'AND'", "'BETWEEN'", "'IS'", "'NAN'", "'INFINITE'", "'NULL'", "'IN'", "'LIKE'", "'ROWNUM'", "'FROM'", "'?'", "'TRUE'", "'FALSE'", "'AS'", "'GROUP BY'", "'FORCE'", "'INDEX'", "'IGNORE'", "'DELETE'", "'UPDATE'", "'LIMIT'", "'ON'", "'INNER JOIN'", "'LEFT JOIN'", "'RIGHT JOIN'", "'JOIN'", "'CONCAT'", "'DISTINCT'", "'COUNT'"
    };
    public static final int EOF=-1;
    public static final int T__76=76;
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
    public static final int EXPR=4;
    public static final int GROUPBY=5;
    public static final int COUNTCOLUMN=6;
    public static final int JOINTYPE=7;
    public static final int ALIAS=8;
    public static final int TABLENAME=9;
    public static final int TABLENAMES=10;
    public static final int SUBQUERY=11;
    public static final int COLUMN=12;
    public static final int AS=13;
    public static final int SELECT=14;
    public static final int DISPLAYED_COUNT_COLUMN=15;
    public static final int DISPLAYED_COLUMN=16;
    public static final int IN=17;
    public static final int NOT=18;
    public static final int SELECT_LIST=19;
    public static final int QUTED_STR=20;
    public static final int WHERE=21;
    public static final int CONDITION_OR=22;
    public static final int CONDITION_LEFT=23;
    public static final int IN_LISTS=24;
    public static final int CONDITION_OR_NOT=25;
    public static final int AND=26;
    public static final int OR=27;
    public static final int ISNOT=28;
    public static final int IS=29;
    public static final int NULL=30;
    public static final int NAN=31;
    public static final int INFINITE=32;
    public static final int LIKE=33;
    public static final int NOT_LIKE=34;
    public static final int NOT_BETWEEN=35;
    public static final int BETWEEN=36;
    public static final int ORDERBY=37;
    public static final int INSERT=38;
    public static final int INSERT_VAL=39;
    public static final int PRIORITY=40;
    public static final int COLUMNS=41;
    public static final int UPDATE=42;
    public static final int SET=43;
    public static final int SET_ELE=44;
    public static final int COL_TAB=45;
    public static final int DELETE=46;
    public static final int SKIP=47;
    public static final int RANGE=48;
    public static final int COMMA=49;
    public static final int EQ=50;
    public static final int LPAREN=51;
    public static final int RPAREN=52;
    public static final int PLUS=53;
    public static final int MINUS=54;
    public static final int DOUBLEVERTBAR=55;
    public static final int ASTERISK=56;
    public static final int DIVIDE=57;
    public static final int MOD=58;
    public static final int EXPONENT=59;
    public static final int ID=60;
    public static final int LTH=61;
    public static final int GTH=62;
    public static final int NOT_EQ=63;
    public static final int LEQ=64;
    public static final int GEQ=65;
    public static final int ASC=66;
    public static final int DESC=67;
    public static final int DOT=68;
    public static final int N=69;
    public static final int NUMBER=70;
    public static final int POINT=71;
    public static final int ARROW=72;
    public static final int QUOTED_STRING=73;
    public static final int DOUBLEQUOTED_STRING=74;
    public static final int WS=75;

    // delegates
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
    public static class STAttrMap extends HashMap {
      public STAttrMap put(String attrName, Object value) {
        super.put(attrName, value);
        return this;
      }
      public STAttrMap put(String attrName, int value) {
        super.put(attrName, new Integer(value));
        return this;
      }
    }

    public String[] getTokenNames() { return MySQLWalker.tokenNames; }
    public String getGrammarFileName() { return "MySQLWalker.g"; }


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
    // MySQLWalker.g:72:1: beg returns [DMLCommon obj] : start_rule ;
    public final MySQLWalker.beg_return beg() throws RecognitionException {
        MySQLWalker.beg_return retval = new MySQLWalker.beg_return();
        retval.start = input.LT(1);

        MySQLWalker.start_rule_return start_rule1 = null;


        try {
            // MySQLWalker.g:72:27: ( start_rule )
            // MySQLWalker.g:73:1: start_rule
            {
            pushFollow(FOLLOW_start_rule_in_beg53);
            start_rule1=start_rule();

            state._fsp--;

            retval.obj =(start_rule1!=null?start_rule1.obj:null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:76:1: start_rule returns [DMLCommon obj] : ( select_command | insert_command | update_command | delete_command );
    public final MySQLWalker.start_rule_return start_rule() throws RecognitionException {
        MySQLWalker.start_rule_return retval = new MySQLWalker.start_rule_return();
        retval.start = input.LT(1);

        MySQLWalker.select_command_return select_command2 = null;

        MySQLWalker.insert_command_return insert_command3 = null;

        MySQLWalker.update_command_return update_command4 = null;

        MySQLWalker.delete_command_return delete_command5 = null;


        try {
            // MySQLWalker.g:77:2: ( select_command | insert_command | update_command | delete_command )
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
                    // MySQLWalker.g:77:3: select_command
                    {
                    pushFollow(FOLLOW_select_command_in_start_rule70);
                    select_command2=select_command();

                    state._fsp--;

                    retval.obj =(select_command2!=null?select_command2.select:null);

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:78:3: insert_command
                    {
                    pushFollow(FOLLOW_insert_command_in_start_rule75);
                    insert_command3=insert_command();

                    state._fsp--;

                    retval.obj =(insert_command3!=null?insert_command3.ins:null);

                    }
                    break;
                case 3 :
                    // MySQLWalker.g:79:3: update_command
                    {
                    pushFollow(FOLLOW_update_command_in_start_rule80);
                    update_command4=update_command();

                    state._fsp--;

                    retval.obj =(update_command4!=null?update_command4.update:null);

                    }
                    break;
                case 4 :
                    // MySQLWalker.g:80:3: delete_command
                    {
                    pushFollow(FOLLOW_delete_command_in_start_rule85);
                    delete_command5=delete_command();

                    state._fsp--;

                    retval.obj =(delete_command5!=null?delete_command5.del:null);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:83:1: setclause[Update update] : ^( SET ( updateColumnSpecs[$update] )+ ) ;
    public final MySQLWalker.setclause_return setclause(Update update) throws RecognitionException {
        MySQLWalker.setclause_return retval = new MySQLWalker.setclause_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:84:2: ( ^( SET ( updateColumnSpecs[$update] )+ ) )
            // MySQLWalker.g:84:3: ^( SET ( updateColumnSpecs[$update] )+ )
            {
            match(input,SET,FOLLOW_SET_in_setclause98); 

            match(input, Token.DOWN, null); 
            // MySQLWalker.g:84:9: ( updateColumnSpecs[$update] )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==SET_ELE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // MySQLWalker.g:84:9: updateColumnSpecs[$update]
            	    {
            	    pushFollow(FOLLOW_updateColumnSpecs_in_setclause100);
            	    updateColumnSpecs(update);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:86:1: updateColumnSpecs[Update update] : ^( SET_ELE updateColumnSpec[$update] ) ;
    public final MySQLWalker.updateColumnSpecs_return updateColumnSpecs(Update update) throws RecognitionException {
        MySQLWalker.updateColumnSpecs_return retval = new MySQLWalker.updateColumnSpecs_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:87:2: ( ^( SET_ELE updateColumnSpec[$update] ) )
            // MySQLWalker.g:87:3: ^( SET_ELE updateColumnSpec[$update] )
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
        return retval;
    }
    // $ANTLR end "updateColumnSpecs"

    public static class updateColumnSpec_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "updateColumnSpec"
    // MySQLWalker.g:89:1: updateColumnSpec[Update update] : ^( EQ ( table_alias )? identifier expr[$update] ) ;
    public final MySQLWalker.updateColumnSpec_return updateColumnSpec(Update update) throws RecognitionException {
        MySQLWalker.updateColumnSpec_return retval = new MySQLWalker.updateColumnSpec_return();
        retval.start = input.LT(1);

        MySQLWalker.identifier_return identifier6 = null;

        MySQLWalker.table_alias_return table_alias7 = null;

        MySQLWalker.expr_return expr8 = null;


        try {
            // MySQLWalker.g:90:2: ( ^( EQ ( table_alias )? identifier expr[$update] ) )
            // MySQLWalker.g:90:3: ^( EQ ( table_alias )? identifier expr[$update] )
            {
            match(input,EQ,FOLLOW_EQ_in_updateColumnSpec131); 

            match(input, Token.DOWN, null); 
            // MySQLWalker.g:90:8: ( table_alias )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==COL_TAB) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // MySQLWalker.g:90:8: table_alias
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

            	update.addSetElement((identifier6!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(identifier6.start),
              input.getTreeAdaptor().getTokenStopIndex(identifier6.start))):null),(table_alias7!=null?table_alias7.aText:null),(expr8!=null?expr8.valueObj:null));
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:96:1: insert_command returns [Insert ins] : ^( INSERT tables[$ins, true] ( column_specs[$ins] )* values[$ins] ) ;
    public final MySQLWalker.insert_command_return insert_command() throws RecognitionException {
        MySQLWalker.insert_command_return retval = new MySQLWalker.insert_command_return();
        retval.start = input.LT(1);

        retval.ins =new Insert();
        try {
            // MySQLWalker.g:98:2: ( ^( INSERT tables[$ins, true] ( column_specs[$ins] )* values[$ins] ) )
            // MySQLWalker.g:98:3: ^( INSERT tables[$ins, true] ( column_specs[$ins] )* values[$ins] )
            {
            match(input,INSERT,FOLLOW_INSERT_in_insert_command161); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_tables_in_insert_command163);
            tables(retval.ins, true);

            state._fsp--;

            // MySQLWalker.g:98:31: ( column_specs[$ins] )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==COLUMNS) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // MySQLWalker.g:98:31: column_specs[$ins]
            	    {
            	    pushFollow(FOLLOW_column_specs_in_insert_command166);
            	    column_specs(retval.ins);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            pushFollow(FOLLOW_values_in_insert_command170);
            values(retval.ins);

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:100:1: values[Insert ins] : ^( INSERT_VAL ( expr[$ins] )* ) ;
    public final MySQLWalker.values_return values(Insert ins) throws RecognitionException {
        MySQLWalker.values_return retval = new MySQLWalker.values_return();
        retval.start = input.LT(1);

        MySQLWalker.expr_return expr9 = null;


        try {
            // MySQLWalker.g:100:20: ( ^( INSERT_VAL ( expr[$ins] )* ) )
            // MySQLWalker.g:100:21: ^( INSERT_VAL ( expr[$ins] )* )
            {
            match(input,INSERT_VAL,FOLLOW_INSERT_VAL_in_values182); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // MySQLWalker.g:100:34: ( expr[$ins] )*
                loop5:
                do {
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( ((LA5_0>=SUBQUERY && LA5_0<=COLUMN)||LA5_0==QUTED_STR||(LA5_0>=PLUS && LA5_0<=MINUS)||(LA5_0>=ASTERISK && LA5_0<=MOD)||LA5_0==ID||(LA5_0>=N && LA5_0<=NUMBER)||LA5_0==91||LA5_0==94||(LA5_0>=96 && LA5_0<=98)) ) {
                        alt5=1;
                    }


                    switch (alt5) {
                	case 1 :
                	    // MySQLWalker.g:100:35: expr[$ins]
                	    {
                	    pushFollow(FOLLOW_expr_in_values185);
                	    expr9=expr(ins);

                	    state._fsp--;

                	    ins.addValue((expr9!=null?expr9.valueObj:null));

                	    }
                	    break;

                	default :
                	    break loop5;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:102:1: column_specs[Insert ins] : ^( COLUMNS ( column_spec[$ins] )+ ) ;
    public final MySQLWalker.column_specs_return column_specs(Insert ins) throws RecognitionException {
        MySQLWalker.column_specs_return retval = new MySQLWalker.column_specs_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:103:2: ( ^( COLUMNS ( column_spec[$ins] )+ ) )
            // MySQLWalker.g:103:3: ^( COLUMNS ( column_spec[$ins] )+ )
            {
            match(input,COLUMNS,FOLLOW_COLUMNS_in_column_specs201); 

            match(input, Token.DOWN, null); 
            // MySQLWalker.g:103:13: ( column_spec[$ins] )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COLUMN) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // MySQLWalker.g:103:13: column_spec[$ins]
            	    {
            	    pushFollow(FOLLOW_column_spec_in_column_specs203);
            	    column_spec(ins);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:105:1: column_spec[Insert ins] : ^( COLUMN identifier ( table_name[false] )? ) ;
    public final MySQLWalker.column_spec_return column_spec(Insert ins) throws RecognitionException {
        MySQLWalker.column_spec_return retval = new MySQLWalker.column_spec_return();
        retval.start = input.LT(1);

        MySQLWalker.table_name_return table_name10 = null;

        MySQLWalker.identifier_return identifier11 = null;


        try {
            // MySQLWalker.g:106:2: ( ^( COLUMN identifier ( table_name[false] )? ) )
            // MySQLWalker.g:106:3: ^( COLUMN identifier ( table_name[false] )? )
            {
            match(input,COLUMN,FOLLOW_COLUMN_in_column_spec217); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_identifier_in_column_spec219);
            identifier11=identifier();

            state._fsp--;

            // MySQLWalker.g:106:23: ( table_name[false] )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==ASTERISK||LA7_0==ID) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // MySQLWalker.g:106:23: table_name[false]
                    {
                    pushFollow(FOLLOW_table_name_in_column_spec221);
                    table_name10=table_name(false);

                    state._fsp--;


                    }
                    break;

            }


            match(input, Token.UP, null); 

            		ins.addColumnTandC((table_name10!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(table_name10.start),
              input.getTreeAdaptor().getTokenStopIndex(table_name10.start))):null),(identifier11!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(identifier11.start),
              input.getTreeAdaptor().getTokenStopIndex(identifier11.start))):null));
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:114:1: whereClause[WhereCondition where] : ^( WHERE sqlCondition[$where] ) ;
    public final MySQLWalker.whereClause_return whereClause(WhereCondition where) throws RecognitionException {
        MySQLWalker.whereClause_return retval = new MySQLWalker.whereClause_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:115:2: ( ^( WHERE sqlCondition[$where] ) )
            // MySQLWalker.g:115:3: ^( WHERE sqlCondition[$where] )
            {
            match(input,WHERE,FOLLOW_WHERE_in_whereClause241); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_sqlCondition_in_whereClause243);
            sqlCondition(where);

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:118:1: orderByClause[WhereCondition where] : ^( ORDERBY columnNamesAfterWhere[$where] ) ;
    public final MySQLWalker.orderByClause_return orderByClause(WhereCondition where) throws RecognitionException {
        MySQLWalker.orderByClause_return retval = new MySQLWalker.orderByClause_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:119:2: ( ^( ORDERBY columnNamesAfterWhere[$where] ) )
            // MySQLWalker.g:119:3: ^( ORDERBY columnNamesAfterWhere[$where] )
            {
            match(input,ORDERBY,FOLLOW_ORDERBY_in_orderByClause258); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_columnNamesAfterWhere_in_orderByClause260);
            columnNamesAfterWhere(where);

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:121:1: columnNamesAfterWhere[WhereCondition where] : ( columnNameAfterWhere[$where] )+ ;
    public final MySQLWalker.columnNamesAfterWhere_return columnNamesAfterWhere(WhereCondition where) throws RecognitionException {
        MySQLWalker.columnNamesAfterWhere_return retval = new MySQLWalker.columnNamesAfterWhere_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:122:2: ( ( columnNameAfterWhere[$where] )+ )
            // MySQLWalker.g:122:3: ( columnNameAfterWhere[$where] )+
            {
            // MySQLWalker.g:122:3: ( columnNameAfterWhere[$where] )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=ASC && LA8_0<=DESC)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // MySQLWalker.g:122:4: columnNameAfterWhere[$where]
            	    {
            	    pushFollow(FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere273);
            	    columnNameAfterWhere(where);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:124:1: columnNameAfterWhere[WhereCondition where] : ( ^( ASC identifier ( table_alias )? ) | ^( DESC identifier ( table_alias )? ) );
    public final MySQLWalker.columnNameAfterWhere_return columnNameAfterWhere(WhereCondition where) throws RecognitionException {
        MySQLWalker.columnNameAfterWhere_return retval = new MySQLWalker.columnNameAfterWhere_return();
        retval.start = input.LT(1);

        MySQLWalker.table_alias_return table_alias12 = null;

        MySQLWalker.identifier_return identifier13 = null;

        MySQLWalker.table_alias_return table_alias14 = null;

        MySQLWalker.identifier_return identifier15 = null;


        try {
            // MySQLWalker.g:125:2: ( ^( ASC identifier ( table_alias )? ) | ^( DESC identifier ( table_alias )? ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==ASC) ) {
                alt11=1;
            }
            else if ( (LA11_0==DESC) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // MySQLWalker.g:125:3: ^( ASC identifier ( table_alias )? )
                    {
                    match(input,ASC,FOLLOW_ASC_in_columnNameAfterWhere287); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_identifier_in_columnNameAfterWhere290);
                    identifier13=identifier();

                    state._fsp--;

                    // MySQLWalker.g:125:21: ( table_alias )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==COL_TAB) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // MySQLWalker.g:125:21: table_alias
                            {
                            pushFollow(FOLLOW_table_alias_in_columnNameAfterWhere292);
                            table_alias12=table_alias();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		where.addOrderByColumn((table_alias12!=null?table_alias12.aText:null),(identifier13!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(identifier13.start),
                      input.getTreeAdaptor().getTokenStopIndex(identifier13.start))):null),true);
                    	

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:128:3: ^( DESC identifier ( table_alias )? )
                    {
                    match(input,DESC,FOLLOW_DESC_in_columnNameAfterWhere300); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_identifier_in_columnNameAfterWhere303);
                    identifier15=identifier();

                    state._fsp--;

                    // MySQLWalker.g:128:22: ( table_alias )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==COL_TAB) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // MySQLWalker.g:128:22: table_alias
                            {
                            pushFollow(FOLLOW_table_alias_in_columnNameAfterWhere305);
                            table_alias14=table_alias();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		where.addOrderByColumn((table_alias14!=null?table_alias14.aText:null),(identifier15!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(identifier15.start),
                      input.getTreeAdaptor().getTokenStopIndex(identifier15.start))):null),false);
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:132:1: selectClause[Select select] : ^( SELECT ( distinct )? select_list[$select] ) ;
    public final MySQLWalker.selectClause_return selectClause(Select select) throws RecognitionException {
        MySQLWalker.selectClause_return retval = new MySQLWalker.selectClause_return();
        retval.start = input.LT(1);

        MySQLWalker.distinct_return distinct16 = null;


        try {
            // MySQLWalker.g:133:5: ( ^( SELECT ( distinct )? select_list[$select] ) )
            // MySQLWalker.g:133:6: ^( SELECT ( distinct )? select_list[$select] )
            {
            match(input,SELECT,FOLLOW_SELECT_in_selectClause322); 

            match(input, Token.DOWN, null); 
            // MySQLWalker.g:133:15: ( distinct )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==113) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // MySQLWalker.g:133:15: distinct
                    {
                    pushFollow(FOLLOW_distinct_in_selectClause324);
                    distinct16=distinct();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_select_list_in_selectClause327);
            select_list(select);

            state._fsp--;


            match(input, Token.UP, null); 

                	select.setHasDistinct((distinct16!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(distinct16.start),
              input.getTreeAdaptor().getTokenStopIndex(distinct16.start))):null) != null);
                

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:138:1: sqlCondition[WhereCondition where] : ( ^( CONDITION_OR_NOT condition[where.getHolder(),where.getExpGroup(),false] ) | ^( CONDITION_OR condition[where.getHolder(),where.getExpGroup(),false] ) );
    public final MySQLWalker.sqlCondition_return sqlCondition(WhereCondition where) throws RecognitionException {
        MySQLWalker.sqlCondition_return retval = new MySQLWalker.sqlCondition_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:140:2: ( ^( CONDITION_OR_NOT condition[where.getHolder(),where.getExpGroup(),false] ) | ^( CONDITION_OR condition[where.getHolder(),where.getExpGroup(),false] ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CONDITION_OR_NOT) ) {
                alt13=1;
            }
            else if ( (LA13_0==CONDITION_OR) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // MySQLWalker.g:140:3: ^( CONDITION_OR_NOT condition[where.getHolder(),where.getExpGroup(),false] )
                    {
                    match(input,CONDITION_OR_NOT,FOLLOW_CONDITION_OR_NOT_in_sqlCondition350); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_condition_in_sqlCondition352);
                    condition(where.getHolder(), where.getExpGroup(), false);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:141:3: ^( CONDITION_OR condition[where.getHolder(),where.getExpGroup(),false] )
                    {
                    match(input,CONDITION_OR,FOLLOW_CONDITION_OR_in_sqlCondition359); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_condition_in_sqlCondition361);
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
        return retval;
    }
    // $ANTLR end "sqlCondition"

    public static class condition_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "condition"
    // MySQLWalker.g:144:1: condition[BindIndexHolder where,ExpressionGroup eg,boolean isPriority] : ( ^( 'OR' (s1= condition[where,orExp,$isPriority] )+ ) | ^( 'AND' ( condition[where,andExp,$isPriority] )+ ) | condition_PAREN[where,$eg] | ^( PRIORITY condition[where,$eg,true] ) );
    public final MySQLWalker.condition_return condition(BindIndexHolder where, ExpressionGroup eg, boolean isPriority) throws RecognitionException {
        MySQLWalker.condition_return retval = new MySQLWalker.condition_return();
        retval.start = input.LT(1);

        MySQLWalker.condition_return s1 = null;


        try {
            // MySQLWalker.g:145:2: ( ^( 'OR' (s1= condition[where,orExp,$isPriority] )+ ) | ^( 'AND' ( condition[where,andExp,$isPriority] )+ ) | condition_PAREN[where,$eg] | ^( PRIORITY condition[where,$eg,true] ) )
            int alt16=4;
            switch ( input.LA(1) ) {
            case 85:
                {
                alt16=1;
                }
                break;
            case 86:
                {
                alt16=2;
                }
                break;
            case IN:
            case ISNOT:
            case IS:
            case LIKE:
            case NOT_LIKE:
            case NOT_BETWEEN:
            case BETWEEN:
            case EQ:
            case LTH:
            case GTH:
            case NOT_EQ:
            case LEQ:
            case GEQ:
                {
                alt16=3;
                }
                break;
            case PRIORITY:
                {
                alt16=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // MySQLWalker.g:146:2: ^( 'OR' (s1= condition[where,orExp,$isPriority] )+ )
                    {

                    		OrExpressionGroup orExp=new OrExpressionGroup();
                    		eg.addExpressionGroup(orExp);
                    		orExp.setPriorty(isPriority);
                    	
                    match(input,85,FOLLOW_85_in_condition379); 

                    match(input, Token.DOWN, null); 
                    // MySQLWalker.g:150:12: (s1= condition[where,orExp,$isPriority] )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==IN||(LA14_0>=ISNOT && LA14_0<=IS)||(LA14_0>=LIKE && LA14_0<=BETWEEN)||LA14_0==PRIORITY||LA14_0==EQ||(LA14_0>=LTH && LA14_0<=GEQ)||(LA14_0>=85 && LA14_0<=86)) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // MySQLWalker.g:150:12: s1= condition[where,orExp,$isPriority]
                    	    {
                    	    pushFollow(FOLLOW_condition_in_condition383);
                    	    s1=condition(where, orExp, isPriority);

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt14 >= 1 ) break loop14;
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:152:2: ^( 'AND' ( condition[where,andExp,$isPriority] )+ )
                    {

                    		ExpressionGroup andExp=new ExpressionGroup();
                    		eg.addExpressionGroup(andExp);
                    		andExp.setPriorty(isPriority);
                    	
                    match(input,86,FOLLOW_86_in_condition395); 

                    match(input, Token.DOWN, null); 
                    // MySQLWalker.g:156:11: ( condition[where,andExp,$isPriority] )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==IN||(LA15_0>=ISNOT && LA15_0<=IS)||(LA15_0>=LIKE && LA15_0<=BETWEEN)||LA15_0==PRIORITY||LA15_0==EQ||(LA15_0>=LTH && LA15_0<=GEQ)||(LA15_0>=85 && LA15_0<=86)) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // MySQLWalker.g:156:11: condition[where,andExp,$isPriority]
                    	    {
                    	    pushFollow(FOLLOW_condition_in_condition397);
                    	    condition(where, andExp, isPriority);

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // MySQLWalker.g:157:3: condition_PAREN[where,$eg]
                    {
                    pushFollow(FOLLOW_condition_PAREN_in_condition404);
                    condition_PAREN(where, eg);

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // MySQLWalker.g:158:3: ^( PRIORITY condition[where,$eg,true] )
                    {
                    match(input,PRIORITY,FOLLOW_PRIORITY_in_condition410); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_condition_in_condition412);
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
        return retval;
    }
    // $ANTLR end "condition"

    public static class condition_PAREN_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "condition_PAREN"
    // MySQLWalker.g:178:1: condition_PAREN[BindIndexHolder where,ExpressionGroup exp] : ( condition_expr[$where,$exp] )+ ;
    public final MySQLWalker.condition_PAREN_return condition_PAREN(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
        MySQLWalker.condition_PAREN_return retval = new MySQLWalker.condition_PAREN_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:179:2: ( ( condition_expr[$where,$exp] )+ )
            // MySQLWalker.g:179:3: ( condition_expr[$where,$exp] )+
            {
            // MySQLWalker.g:179:3: ( condition_expr[$where,$exp] )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                alt17 = dfa17.predict(input);
                switch (alt17) {
            	case 1 :
            	    // MySQLWalker.g:179:3: condition_expr[$where,$exp]
            	    {
            	    pushFollow(FOLLOW_condition_expr_in_condition_PAREN427);
            	    condition_expr(where, exp);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:181:1: condition_expr[BindIndexHolder where,ExpressionGroup exp] : ( comparisonCondition[$where,$exp] | inCondition[$where,$exp] | isCondition[$where,$exp] | likeCondition[$where,$exp] | betweenCondition[$where,$exp] );
    public final MySQLWalker.condition_expr_return condition_expr(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
        MySQLWalker.condition_expr_return retval = new MySQLWalker.condition_expr_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:182:2: ( comparisonCondition[$where,$exp] | inCondition[$where,$exp] | isCondition[$where,$exp] | likeCondition[$where,$exp] | betweenCondition[$where,$exp] )
            int alt18=5;
            switch ( input.LA(1) ) {
            case EQ:
            case LTH:
            case GTH:
            case NOT_EQ:
            case LEQ:
            case GEQ:
                {
                alt18=1;
                }
                break;
            case IN:
                {
                alt18=2;
                }
                break;
            case ISNOT:
            case IS:
                {
                alt18=3;
                }
                break;
            case LIKE:
            case NOT_LIKE:
                {
                alt18=4;
                }
                break;
            case NOT_BETWEEN:
            case BETWEEN:
                {
                alt18=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // MySQLWalker.g:182:4: comparisonCondition[$where,$exp]
                    {
                    pushFollow(FOLLOW_comparisonCondition_in_condition_expr440);
                    comparisonCondition(where, exp);

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // MySQLWalker.g:183:4: inCondition[$where,$exp]
                    {
                    pushFollow(FOLLOW_inCondition_in_condition_expr446);
                    inCondition(where, exp);

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // MySQLWalker.g:184:4: isCondition[$where,$exp]
                    {
                    pushFollow(FOLLOW_isCondition_in_condition_expr453);
                    isCondition(where, exp);

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // MySQLWalker.g:185:4: likeCondition[$where,$exp]
                    {
                    pushFollow(FOLLOW_likeCondition_in_condition_expr460);
                    likeCondition(where, exp);

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // MySQLWalker.g:186:4: betweenCondition[$where,$exp]
                    {
                    pushFollow(FOLLOW_betweenCondition_in_condition_expr466);
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
        return retval;
    }
    // $ANTLR end "condition_expr"

    public static class betweenCondition_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "betweenCondition"
    // MySQLWalker.g:188:1: betweenCondition[BindIndexHolder where,ExpressionGroup exp] : ( ^( NOT_BETWEEN between_and[$where] left_cond[$where] ) | ^( BETWEEN between_and[$where] left_cond[$where] ) );
    public final MySQLWalker.betweenCondition_return betweenCondition(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
        MySQLWalker.betweenCondition_return retval = new MySQLWalker.betweenCondition_return();
        retval.start = input.LT(1);

        MySQLWalker.left_cond_return left_cond17 = null;

        MySQLWalker.between_and_return between_and18 = null;

        MySQLWalker.left_cond_return left_cond19 = null;

        MySQLWalker.between_and_return between_and20 = null;


        try {
            // MySQLWalker.g:189:2: ( ^( NOT_BETWEEN between_and[$where] left_cond[$where] ) | ^( BETWEEN between_and[$where] left_cond[$where] ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==NOT_BETWEEN) ) {
                alt19=1;
            }
            else if ( (LA19_0==BETWEEN) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // MySQLWalker.g:189:3: ^( NOT_BETWEEN between_and[$where] left_cond[$where] )
                    {
                    match(input,NOT_BETWEEN,FOLLOW_NOT_BETWEEN_in_betweenCondition478); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_between_and_in_betweenCondition480);
                    between_and18=between_and(where);

                    state._fsp--;

                    pushFollow(FOLLOW_left_cond_in_betweenCondition483);
                    left_cond17=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		exp.addNotBetween((left_cond17!=null?left_cond17.ret:null), (between_and18!=null?between_and18.pair:null));
                    	

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:192:3: ^( BETWEEN between_and[$where] left_cond[$where] )
                    {
                    match(input,BETWEEN,FOLLOW_BETWEEN_in_betweenCondition492); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_between_and_in_betweenCondition494);
                    between_and20=between_and(where);

                    state._fsp--;

                    pushFollow(FOLLOW_left_cond_in_betweenCondition497);
                    left_cond19=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		exp.addBetween((left_cond19!=null?left_cond19.ret:null), (between_and20!=null?between_and20.pair:null));
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:196:1: between_and[BindIndexHolder where] returns [BetweenPair pair] : ^(start= between_and_expression[$where] end= between_and_expression[$where] ) ;
    public final MySQLWalker.between_and_return between_and(BindIndexHolder where) throws RecognitionException {
        MySQLWalker.between_and_return retval = new MySQLWalker.between_and_return();
        retval.start = input.LT(1);

        MySQLWalker.between_and_expression_return start = null;

        MySQLWalker.between_and_expression_return end = null;


        try {
            // MySQLWalker.g:197:2: ( ^(start= between_and_expression[$where] end= between_and_expression[$where] ) )
            // MySQLWalker.g:197:3: ^(start= between_and_expression[$where] end= between_and_expression[$where] )
            {
            pushFollow(FOLLOW_between_and_expression_in_between_and517);
            start=between_and_expression(where);

            state._fsp--;


            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_between_and_expression_in_between_and522);
            end=between_and_expression(where);

            state._fsp--;


            match(input, Token.UP, null); 

            		retval.pair = new BetweenPair((start!=null?start.valueObj:null), (end!=null?end.valueObj:null));
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:201:1: likeCondition[BindIndexHolder where,ExpressionGroup exp] : ( ^( NOT_LIKE expr[$where] left_cond[$where] ) | ^( LIKE expr[$where] left_cond[$where] ) );
    public final MySQLWalker.likeCondition_return likeCondition(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
        MySQLWalker.likeCondition_return retval = new MySQLWalker.likeCondition_return();
        retval.start = input.LT(1);

        MySQLWalker.left_cond_return left_cond21 = null;

        MySQLWalker.expr_return expr22 = null;

        MySQLWalker.left_cond_return left_cond23 = null;

        MySQLWalker.expr_return expr24 = null;


        try {
            // MySQLWalker.g:202:2: ( ^( NOT_LIKE expr[$where] left_cond[$where] ) | ^( LIKE expr[$where] left_cond[$where] ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==NOT_LIKE) ) {
                alt20=1;
            }
            else if ( (LA20_0==LIKE) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // MySQLWalker.g:202:3: ^( NOT_LIKE expr[$where] left_cond[$where] )
                    {
                    match(input,NOT_LIKE,FOLLOW_NOT_LIKE_in_likeCondition538); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_likeCondition540);
                    expr22=expr(where);

                    state._fsp--;

                    pushFollow(FOLLOW_left_cond_in_likeCondition543);
                    left_cond21=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		exp.addNotLike((left_cond21!=null?left_cond21.ret:null),(expr22!=null?expr22.valueObj:null));
                    	

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:205:3: ^( LIKE expr[$where] left_cond[$where] )
                    {
                    match(input,LIKE,FOLLOW_LIKE_in_likeCondition551); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_likeCondition553);
                    expr24=expr(where);

                    state._fsp--;

                    pushFollow(FOLLOW_left_cond_in_likeCondition556);
                    left_cond23=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		exp.addLike((left_cond23!=null?left_cond23.ret:null),(expr24!=null?expr24.valueObj:null));
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:210:1: isCondition[BindIndexHolder where,ExpressionGroup exp] : ( ^( ISNOT NULL left_cond[$where] ) | ^( IS NULL left_cond[$where] ) );
    public final MySQLWalker.isCondition_return isCondition(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
        MySQLWalker.isCondition_return retval = new MySQLWalker.isCondition_return();
        retval.start = input.LT(1);

        MySQLWalker.left_cond_return left_cond25 = null;

        MySQLWalker.left_cond_return left_cond26 = null;


        try {
            // MySQLWalker.g:211:2: ( ^( ISNOT NULL left_cond[$where] ) | ^( IS NULL left_cond[$where] ) )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==ISNOT) ) {
                alt21=1;
            }
            else if ( (LA21_0==IS) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // MySQLWalker.g:211:3: ^( ISNOT NULL left_cond[$where] )
                    {
                    match(input,ISNOT,FOLLOW_ISNOT_in_isCondition572); 

                    match(input, Token.DOWN, null); 
                    match(input,NULL,FOLLOW_NULL_in_isCondition574); 
                    pushFollow(FOLLOW_left_cond_in_isCondition576);
                    left_cond25=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    	exp.addIsNotNull((left_cond25!=null?left_cond25.ret:null));
                    	

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:214:3: ^( IS NULL left_cond[$where] )
                    {
                    match(input,IS,FOLLOW_IS_in_isCondition584); 

                    match(input, Token.DOWN, null); 
                    match(input,NULL,FOLLOW_NULL_in_isCondition586); 
                    pushFollow(FOLLOW_left_cond_in_isCondition588);
                    left_cond26=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    	exp.addIsNull((left_cond26!=null?left_cond26.ret:null));
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:220:1: comparisonCondition[BindIndexHolder where,ExpressionGroup exp] : ( ^( EQ expr[$where] left_cond[$where] ) | ^( NOT_EQ expr[$where] left_cond[$where] ) | ^( LTH expr[$where] left_cond[$where] ) | ^( GTH expr[$where] left_cond[$where] ) | ^( LEQ expr[$where] left_cond[$where] ) | ^( GEQ expr[$where] left_cond[$where] ) );
    public final MySQLWalker.comparisonCondition_return comparisonCondition(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
        MySQLWalker.comparisonCondition_return retval = new MySQLWalker.comparisonCondition_return();
        retval.start = input.LT(1);

        MySQLWalker.left_cond_return left_cond27 = null;

        MySQLWalker.expr_return expr28 = null;

        MySQLWalker.left_cond_return left_cond29 = null;

        MySQLWalker.expr_return expr30 = null;

        MySQLWalker.left_cond_return left_cond31 = null;

        MySQLWalker.expr_return expr32 = null;

        MySQLWalker.left_cond_return left_cond33 = null;

        MySQLWalker.expr_return expr34 = null;

        MySQLWalker.left_cond_return left_cond35 = null;

        MySQLWalker.expr_return expr36 = null;

        MySQLWalker.left_cond_return left_cond37 = null;

        MySQLWalker.expr_return expr38 = null;


        try {
            // MySQLWalker.g:221:2: ( ^( EQ expr[$where] left_cond[$where] ) | ^( NOT_EQ expr[$where] left_cond[$where] ) | ^( LTH expr[$where] left_cond[$where] ) | ^( GTH expr[$where] left_cond[$where] ) | ^( LEQ expr[$where] left_cond[$where] ) | ^( GEQ expr[$where] left_cond[$where] ) )
            int alt22=6;
            switch ( input.LA(1) ) {
            case EQ:
                {
                alt22=1;
                }
                break;
            case NOT_EQ:
                {
                alt22=2;
                }
                break;
            case LTH:
                {
                alt22=3;
                }
                break;
            case GTH:
                {
                alt22=4;
                }
                break;
            case LEQ:
                {
                alt22=5;
                }
                break;
            case GEQ:
                {
                alt22=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // MySQLWalker.g:221:3: ^( EQ expr[$where] left_cond[$where] )
                    {
                    match(input,EQ,FOLLOW_EQ_in_comparisonCondition605); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_comparisonCondition607);
                    expr28=expr(where);

                    state._fsp--;

                    pushFollow(FOLLOW_left_cond_in_comparisonCondition610);
                    left_cond27=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		exp.addEqual((left_cond27!=null?left_cond27.ret:null),(expr28!=null?expr28.valueObj:null));
                    	

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:225:3: ^( NOT_EQ expr[$where] left_cond[$where] )
                    {
                    match(input,NOT_EQ,FOLLOW_NOT_EQ_in_comparisonCondition620); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_comparisonCondition622);
                    expr30=expr(where);

                    state._fsp--;

                    pushFollow(FOLLOW_left_cond_in_comparisonCondition625);
                    left_cond29=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		exp.addNotEqual((left_cond29!=null?left_cond29.ret:null),(expr30!=null?expr30.valueObj:null));
                    	

                    }
                    break;
                case 3 :
                    // MySQLWalker.g:229:3: ^( LTH expr[$where] left_cond[$where] )
                    {
                    match(input,LTH,FOLLOW_LTH_in_comparisonCondition635); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_comparisonCondition637);
                    expr32=expr(where);

                    state._fsp--;

                    pushFollow(FOLLOW_left_cond_in_comparisonCondition640);
                    left_cond31=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		exp.addLessThan((left_cond31!=null?left_cond31.ret:null),(expr32!=null?expr32.valueObj:null));
                    	

                    }
                    break;
                case 4 :
                    // MySQLWalker.g:233:3: ^( GTH expr[$where] left_cond[$where] )
                    {
                    match(input,GTH,FOLLOW_GTH_in_comparisonCondition650); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_comparisonCondition652);
                    expr34=expr(where);

                    state._fsp--;

                    pushFollow(FOLLOW_left_cond_in_comparisonCondition655);
                    left_cond33=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		exp.addGreaterThan((left_cond33!=null?left_cond33.ret:null),(expr34!=null?expr34.valueObj:null));
                    	

                    }
                    break;
                case 5 :
                    // MySQLWalker.g:237:3: ^( LEQ expr[$where] left_cond[$where] )
                    {
                    match(input,LEQ,FOLLOW_LEQ_in_comparisonCondition665); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_comparisonCondition667);
                    expr36=expr(where);

                    state._fsp--;

                    pushFollow(FOLLOW_left_cond_in_comparisonCondition670);
                    left_cond35=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		exp.addLessOrEqual((left_cond35!=null?left_cond35.ret:null),(expr36!=null?expr36.valueObj:null));
                    	

                    }
                    break;
                case 6 :
                    // MySQLWalker.g:241:3: ^( GEQ expr[$where] left_cond[$where] )
                    {
                    match(input,GEQ,FOLLOW_GEQ_in_comparisonCondition680); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_comparisonCondition682);
                    expr38=expr(where);

                    state._fsp--;

                    pushFollow(FOLLOW_left_cond_in_comparisonCondition685);
                    left_cond37=left_cond(where);

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		exp.addGreaterOrEqual((left_cond37!=null?left_cond37.ret:null),(expr38!=null?expr38.valueObj:null));
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:270:1: left_cond[BindIndexHolder where] returns [Object ret] : ^( CONDITION_LEFT expr[$where] ) ;
    public final MySQLWalker.left_cond_return left_cond(BindIndexHolder where) throws RecognitionException {
        MySQLWalker.left_cond_return retval = new MySQLWalker.left_cond_return();
        retval.start = input.LT(1);

        MySQLWalker.expr_return expr39 = null;


        try {
            // MySQLWalker.g:272:2: ( ^( CONDITION_LEFT expr[$where] ) )
            // MySQLWalker.g:272:3: ^( CONDITION_LEFT expr[$where] )
            {
            match(input,CONDITION_LEFT,FOLLOW_CONDITION_LEFT_in_left_cond708); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expr_in_left_cond710);
            expr39=expr(where);

            state._fsp--;


            match(input, Token.UP, null); 

            		retval.ret =(expr39!=null?expr39.valueObj:null);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:277:1: in_list[BindIndexHolder where] returns [List list] : ^( IN_LISTS inCondition_expr_adds[$where] ) ;
    public final MySQLWalker.in_list_return in_list(BindIndexHolder where) throws RecognitionException {
        MySQLWalker.in_list_return retval = new MySQLWalker.in_list_return();
        retval.start = input.LT(1);

        MySQLWalker.inCondition_expr_adds_return inCondition_expr_adds40 = null;


        try {
            // MySQLWalker.g:278:2: ( ^( IN_LISTS inCondition_expr_adds[$where] ) )
            // MySQLWalker.g:278:3: ^( IN_LISTS inCondition_expr_adds[$where] )
            {
            match(input,IN_LISTS,FOLLOW_IN_LISTS_in_in_list729); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_inCondition_expr_adds_in_in_list731);
            inCondition_expr_adds40=inCondition_expr_adds(where);

            state._fsp--;


            match(input, Token.UP, null); 

            		retval.list =(inCondition_expr_adds40!=null?inCondition_expr_adds40.list:null);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:283:1: inCondition[BindIndexHolder where,ExpressionGroup exp] : ^( IN (not= 'NOT' )? ( subquery )? ( in_list[$where] )? left_cond[$where] ) ;
    public final MySQLWalker.inCondition_return inCondition(BindIndexHolder where, ExpressionGroup exp) throws RecognitionException {
        MySQLWalker.inCondition_return retval = new MySQLWalker.inCondition_return();
        retval.start = input.LT(1);

        CommonTree not=null;
        MySQLWalker.subquery_return subquery41 = null;

        MySQLWalker.in_list_return in_list42 = null;

        MySQLWalker.left_cond_return left_cond43 = null;


        try {
            // MySQLWalker.g:284:2: ( ^( IN (not= 'NOT' )? ( subquery )? ( in_list[$where] )? left_cond[$where] ) )
            // MySQLWalker.g:284:3: ^( IN (not= 'NOT' )? ( subquery )? ( in_list[$where] )? left_cond[$where] )
            {
            match(input,IN,FOLLOW_IN_in_inCondition749); 

            match(input, Token.DOWN, null); 
            // MySQLWalker.g:284:11: (not= 'NOT' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==84) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // MySQLWalker.g:284:11: not= 'NOT'
                    {
                    not=(CommonTree)match(input,84,FOLLOW_84_in_inCondition753); 

                    }
                    break;

            }

            // MySQLWalker.g:284:19: ( subquery )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==SUBQUERY) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // MySQLWalker.g:284:19: subquery
                    {
                    pushFollow(FOLLOW_subquery_in_inCondition756);
                    subquery41=subquery();

                    state._fsp--;


                    }
                    break;

            }

            // MySQLWalker.g:284:29: ( in_list[$where] )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==IN_LISTS) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // MySQLWalker.g:284:29: in_list[$where]
                    {
                    pushFollow(FOLLOW_in_list_in_inCondition759);
                    in_list42=in_list(where);

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_left_cond_in_inCondition764);
            left_cond43=left_cond(where);

            state._fsp--;


            match(input, Token.UP, null); 

            		if((not!=null?not.getText():null)!=null){
            			if((subquery41!=null?subquery41.subselect:null)!=null){
            				/*exp.addNotIn($columnNameAfterWhere.text,(subquery41!=null?subquery41.subselect:null));*/
            			}
            			else if((in_list42!=null?in_list42.list:null)!=null){
            				exp.addNotIn((left_cond43!=null?left_cond43.ret:null),(in_list42!=null?in_list42.list:null));
            			}
            		}else{
            			if((subquery41!=null?subquery41.subselect:null)!=null){
            				/*exp.addIn($columnNameAfterWhere.text,(subquery41!=null?subquery41.subselect:null));*/
            			}
            			else if((in_list42!=null?in_list42.list:null)!=null){
            				exp.addIn((left_cond43!=null?left_cond43.ret:null),(in_list42!=null?in_list42.list:null));
            			}
            		}
            		;
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:309:1: inCondition_expr_adds[BindIndexHolder where] returns [List list] : ( expr_add[$where] )+ ;
    public final MySQLWalker.inCondition_expr_adds_return inCondition_expr_adds(BindIndexHolder where) throws RecognitionException {
        MySQLWalker.inCondition_expr_adds_return retval = new MySQLWalker.inCondition_expr_adds_return();
        retval.start = input.LT(1);

        MySQLWalker.expr_add_return expr_add44 = null;


        retval.list =new ArrayList();
        try {
            // MySQLWalker.g:310:31: ( ( expr_add[$where] )+ )
            // MySQLWalker.g:311:2: ( expr_add[$where] )+
            {
            // MySQLWalker.g:311:2: ( expr_add[$where] )+
            int cnt26=0;
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==COLUMN||LA26_0==QUTED_STR||(LA26_0>=PLUS && LA26_0<=MINUS)||(LA26_0>=ASTERISK && LA26_0<=MOD)||LA26_0==ID||(LA26_0>=N && LA26_0<=NUMBER)||LA26_0==91||LA26_0==94||(LA26_0>=96 && LA26_0<=98)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // MySQLWalker.g:311:3: expr_add[$where]
            	    {
            	    pushFollow(FOLLOW_expr_add_in_inCondition_expr_adds798);
            	    expr_add44=expr_add(where);

            	    state._fsp--;


            	    		retval.list.add((expr_add44!=null?expr_add44.valueObjExpr:null));
            	    	

            	    }
            	    break;

            	default :
            	    if ( cnt26 >= 1 ) break loop26;
                        EarlyExitException eee =
                            new EarlyExitException(26, input);
                        throw eee;
                }
                cnt26++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:318:1: expr[BindIndexHolder where] returns [Object valueObj] : ( expr_add[$where] | subquery ) ;
    public final MySQLWalker.expr_return expr(BindIndexHolder where) throws RecognitionException {
        MySQLWalker.expr_return retval = new MySQLWalker.expr_return();
        retval.start = input.LT(1);

        MySQLWalker.expr_add_return expr_add45 = null;

        MySQLWalker.subquery_return subquery46 = null;


        try {
            // MySQLWalker.g:319:2: ( ( expr_add[$where] | subquery ) )
            // MySQLWalker.g:319:3: ( expr_add[$where] | subquery )
            {
            // MySQLWalker.g:319:3: ( expr_add[$where] | subquery )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==COLUMN||LA27_0==QUTED_STR||(LA27_0>=PLUS && LA27_0<=MINUS)||(LA27_0>=ASTERISK && LA27_0<=MOD)||LA27_0==ID||(LA27_0>=N && LA27_0<=NUMBER)||LA27_0==91||LA27_0==94||(LA27_0>=96 && LA27_0<=98)) ) {
                alt27=1;
            }
            else if ( (LA27_0==SUBQUERY) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // MySQLWalker.g:319:4: expr_add[$where]
                    {
                    pushFollow(FOLLOW_expr_add_in_expr823);
                    expr_add45=expr_add(where);

                    state._fsp--;

                    retval.valueObj =(expr_add45!=null?expr_add45.valueObjExpr:null);

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:320:3: subquery
                    {
                    pushFollow(FOLLOW_subquery_in_expr830);
                    subquery46=subquery();

                    state._fsp--;

                    retval.valueObj =(subquery46!=null?subquery46.subselect:null);

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:324:1: expr_add[BindIndexHolder where] returns [Object valueObjExpr] : ( ^( PLUS s1= expr_add[$where] s2= expr_add[$where] ) | ^( MINUS s1= expr_add[$where] s2= expr_add[$where] ) | ^( ASTERISK s1= expr_add[$where] s2= expr_add[$where] ) | ^( DIVIDE s1= expr_add[$where] s2= expr_add[$where] ) | ^( MOD s1= expr_add[$where] s2= expr_add[$where] ) | N | NUMBER | boolean_literal | 'NULL' | 'ROWNUM' | '?' | ^( QUTED_STR quoted_string ) | ^( COLUMN identifier ( table_name[false] )? ) | ^( ID ( expr[$where] )* ) );
    public final MySQLWalker.expr_add_return expr_add(BindIndexHolder where) throws RecognitionException {
        MySQLWalker.expr_add_return retval = new MySQLWalker.expr_add_return();
        retval.start = input.LT(1);

        CommonTree N47=null;
        CommonTree NUMBER48=null;
        CommonTree ID53=null;
        MySQLWalker.expr_add_return s1 = null;

        MySQLWalker.expr_add_return s2 = null;

        MySQLWalker.quoted_string_return quoted_string49 = null;

        MySQLWalker.identifier_return identifier50 = null;

        MySQLWalker.table_name_return table_name51 = null;

        MySQLWalker.expr_return expr52 = null;



        List<Object> list=new ArrayList<Object>();

        try {
            // MySQLWalker.g:328:2: ( ^( PLUS s1= expr_add[$where] s2= expr_add[$where] ) | ^( MINUS s1= expr_add[$where] s2= expr_add[$where] ) | ^( ASTERISK s1= expr_add[$where] s2= expr_add[$where] ) | ^( DIVIDE s1= expr_add[$where] s2= expr_add[$where] ) | ^( MOD s1= expr_add[$where] s2= expr_add[$where] ) | N | NUMBER | boolean_literal | 'NULL' | 'ROWNUM' | '?' | ^( QUTED_STR quoted_string ) | ^( COLUMN identifier ( table_name[false] )? ) | ^( ID ( expr[$where] )* ) )
            int alt30=14;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt30=1;
                }
                break;
            case MINUS:
                {
                alt30=2;
                }
                break;
            case ASTERISK:
                {
                alt30=3;
                }
                break;
            case DIVIDE:
                {
                alt30=4;
                }
                break;
            case MOD:
                {
                alt30=5;
                }
                break;
            case N:
                {
                alt30=6;
                }
                break;
            case NUMBER:
                {
                alt30=7;
                }
                break;
            case 97:
            case 98:
                {
                alt30=8;
                }
                break;
            case 91:
                {
                alt30=9;
                }
                break;
            case 94:
                {
                alt30=10;
                }
                break;
            case 96:
                {
                alt30=11;
                }
                break;
            case QUTED_STR:
                {
                alt30=12;
                }
                break;
            case COLUMN:
                {
                alt30=13;
                }
                break;
            case ID:
                {
                alt30=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // MySQLWalker.g:328:3: ^( PLUS s1= expr_add[$where] s2= expr_add[$where] )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_expr_add857); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_add_in_expr_add861);
                    s1=expr_add(where);

                    state._fsp--;

                    pushFollow(FOLLOW_expr_add_in_expr_add866);
                    s2=expr_add(where);

                    state._fsp--;


                    match(input, Token.UP, null); 
                    retval.valueObjExpr =add((s1!=null?s1.valueObjExpr:null),(s2!=null?s2.valueObjExpr:null));

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:329:3: ^( MINUS s1= expr_add[$where] s2= expr_add[$where] )
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_expr_add875); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_add_in_expr_add879);
                    s1=expr_add(where);

                    state._fsp--;

                    pushFollow(FOLLOW_expr_add_in_expr_add884);
                    s2=expr_add(where);

                    state._fsp--;


                    match(input, Token.UP, null); 
                    retval.valueObjExpr =subtract((s1!=null?s1.valueObjExpr:null),(s2!=null?s2.valueObjExpr:null));

                    }
                    break;
                case 3 :
                    // MySQLWalker.g:330:3: ^( ASTERISK s1= expr_add[$where] s2= expr_add[$where] )
                    {
                    match(input,ASTERISK,FOLLOW_ASTERISK_in_expr_add892); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_add_in_expr_add896);
                    s1=expr_add(where);

                    state._fsp--;

                    pushFollow(FOLLOW_expr_add_in_expr_add901);
                    s2=expr_add(where);

                    state._fsp--;


                    match(input, Token.UP, null); 
                    retval.valueObjExpr =multiply((s1!=null?s1.valueObjExpr:null),(s2!=null?s2.valueObjExpr:null));

                    }
                    break;
                case 4 :
                    // MySQLWalker.g:331:3: ^( DIVIDE s1= expr_add[$where] s2= expr_add[$where] )
                    {
                    match(input,DIVIDE,FOLLOW_DIVIDE_in_expr_add909); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_add_in_expr_add913);
                    s1=expr_add(where);

                    state._fsp--;

                    pushFollow(FOLLOW_expr_add_in_expr_add918);
                    s2=expr_add(where);

                    state._fsp--;


                    match(input, Token.UP, null); 
                    retval.valueObjExpr =divide((s1!=null?s1.valueObjExpr:null),(s2!=null?s2.valueObjExpr:null));

                    }
                    break;
                case 5 :
                    // MySQLWalker.g:332:3: ^( MOD s1= expr_add[$where] s2= expr_add[$where] )
                    {
                    match(input,MOD,FOLLOW_MOD_in_expr_add926); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_add_in_expr_add930);
                    s1=expr_add(where);

                    state._fsp--;

                    pushFollow(FOLLOW_expr_add_in_expr_add935);
                    s2=expr_add(where);

                    state._fsp--;


                    match(input, Token.UP, null); 
                    retval.valueObjExpr =mod((s1!=null?s1.valueObjExpr:null),(s2!=null?s2.valueObjExpr:null));

                    }
                    break;
                case 6 :
                    // MySQLWalker.g:333:3: N
                    {
                    N47=(CommonTree)match(input,N,FOLLOW_N_in_expr_add942); 
                    retval.valueObjExpr =new BigDecimal((N47!=null?N47.getText():null));

                    }
                    break;
                case 7 :
                    // MySQLWalker.g:334:3: NUMBER
                    {
                    NUMBER48=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_expr_add948); 
                    retval.valueObjExpr =new BigDecimal((NUMBER48!=null?NUMBER48.getText():null));

                    }
                    break;
                case 8 :
                    // MySQLWalker.g:335:3: boolean_literal
                    {
                    pushFollow(FOLLOW_boolean_literal_in_expr_add953);
                    boolean_literal();

                    state._fsp--;


                    }
                    break;
                case 9 :
                    // MySQLWalker.g:336:3: 'NULL'
                    {
                    match(input,91,FOLLOW_91_in_expr_add957); 
                    retval.valueObjExpr =DbFunctions.NULL;

                    }
                    break;
                case 10 :
                    // MySQLWalker.g:337:3: 'ROWNUM'
                    {
                    match(input,94,FOLLOW_94_in_expr_add963); 

                    }
                    break;
                case 11 :
                    // MySQLWalker.g:338:3: '?'
                    {
                    match(input,96,FOLLOW_96_in_expr_add967); 
                    retval.valueObjExpr =DbFunctions.bindVar(where.selfAddAndGet());

                    }
                    break;
                case 12 :
                    // MySQLWalker.g:339:3: ^( QUTED_STR quoted_string )
                    {
                    match(input,QUTED_STR,FOLLOW_QUTED_STR_in_expr_add973); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_quoted_string_in_expr_add975);
                    quoted_string49=quoted_string();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    retval.valueObjExpr =(quoted_string49!=null?quoted_string49.aText:null);

                    }
                    break;
                case 13 :
                    // MySQLWalker.g:340:3: ^( COLUMN identifier ( table_name[false] )? )
                    {
                    match(input,COLUMN,FOLLOW_COLUMN_in_expr_add982); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_identifier_in_expr_add984);
                    identifier50=identifier();

                    state._fsp--;

                    // MySQLWalker.g:340:23: ( table_name[false] )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==ASTERISK||LA28_0==ID) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // MySQLWalker.g:340:23: table_name[false]
                            {
                            pushFollow(FOLLOW_table_name_in_expr_add986);
                            table_name51=table_name(false);

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 
                    retval.valueObjExpr =column((identifier50!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(identifier50.start),
                      input.getTreeAdaptor().getTokenStopIndex(identifier50.start))):null),(table_name51!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(table_name51.start),
                      input.getTreeAdaptor().getTokenStopIndex(table_name51.start))):null));

                    }
                    break;
                case 14 :
                    // MySQLWalker.g:342:3: ^( ID ( expr[$where] )* )
                    {
                    ID53=(CommonTree)match(input,ID,FOLLOW_ID_in_expr_add997); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // MySQLWalker.g:343:2: ( expr[$where] )*
                        loop29:
                        do {
                            int alt29=2;
                            int LA29_0 = input.LA(1);

                            if ( ((LA29_0>=SUBQUERY && LA29_0<=COLUMN)||LA29_0==QUTED_STR||(LA29_0>=PLUS && LA29_0<=MINUS)||(LA29_0>=ASTERISK && LA29_0<=MOD)||LA29_0==ID||(LA29_0>=N && LA29_0<=NUMBER)||LA29_0==91||LA29_0==94||(LA29_0>=96 && LA29_0<=98)) ) {
                                alt29=1;
                            }


                            switch (alt29) {
                        	case 1 :
                        	    // MySQLWalker.g:343:3: expr[$where]
                        	    {
                        	    pushFollow(FOLLOW_expr_in_expr_add1002);
                        	    expr52=expr(where);

                        	    state._fsp--;

                        	    list.add((expr52!=null?expr52.valueObj:null));

                        	    }
                        	    break;

                        	default :
                        	    break loop29;
                            }
                        } while (true);


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
        return retval;
    }
    // $ANTLR end "expr_add"

    public static class value_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "value"
    // MySQLWalker.g:350:1: value : ( N | NUMBER | '?' | ^( QUTED_STR quoted_string ) );
    public final MySQLWalker.value_return value() throws RecognitionException {
        MySQLWalker.value_return retval = new MySQLWalker.value_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:350:7: ( N | NUMBER | '?' | ^( QUTED_STR quoted_string ) )
            int alt31=4;
            switch ( input.LA(1) ) {
            case N:
                {
                alt31=1;
                }
                break;
            case NUMBER:
                {
                alt31=2;
                }
                break;
            case 96:
                {
                alt31=3;
                }
                break;
            case QUTED_STR:
                {
                alt31=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // MySQLWalker.g:351:2: N
                    {
                    match(input,N,FOLLOW_N_in_value1020); 

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:352:3: NUMBER
                    {
                    match(input,NUMBER,FOLLOW_NUMBER_in_value1024); 

                    }
                    break;
                case 3 :
                    // MySQLWalker.g:353:3: '?'
                    {
                    match(input,96,FOLLOW_96_in_value1028); 

                    }
                    break;
                case 4 :
                    // MySQLWalker.g:354:3: ^( QUTED_STR quoted_string )
                    {
                    match(input,QUTED_STR,FOLLOW_QUTED_STR_in_value1033); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_quoted_string_in_value1035);
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
    // MySQLWalker.g:356:1: boolean_literal returns [Object valueObj] : (s1= 'TRUE' | s1= 'FALSE' );
    public final MySQLWalker.boolean_literal_return boolean_literal() throws RecognitionException {
        MySQLWalker.boolean_literal_return retval = new MySQLWalker.boolean_literal_return();
        retval.start = input.LT(1);

        CommonTree s1=null;

        try {
            // MySQLWalker.g:357:2: (s1= 'TRUE' | s1= 'FALSE' )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==97) ) {
                alt32=1;
            }
            else if ( (LA32_0==98) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // MySQLWalker.g:357:3: s1= 'TRUE'
                    {
                    s1=(CommonTree)match(input,97,FOLLOW_97_in_boolean_literal1050); 
                    retval.valueObj =Boolean.parseBoolean((s1!=null?s1.getText():null));

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:358:4: s1= 'FALSE'
                    {
                    s1=(CommonTree)match(input,98,FOLLOW_98_in_boolean_literal1059); 
                    retval.valueObj =Boolean.parseBoolean((s1!=null?s1.getText():null));

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:362:1: select_list[Select select] : ^( SELECT_LIST ( displayed_column[$select] )+ ) ;
    public final MySQLWalker.select_list_return select_list(Select select) throws RecognitionException {
        MySQLWalker.select_list_return retval = new MySQLWalker.select_list_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:363:2: ( ^( SELECT_LIST ( displayed_column[$select] )+ ) )
            // MySQLWalker.g:363:3: ^( SELECT_LIST ( displayed_column[$select] )+ )
            {
            match(input,SELECT_LIST,FOLLOW_SELECT_LIST_in_select_list1075); 

            match(input, Token.DOWN, null); 
            // MySQLWalker.g:363:17: ( displayed_column[$select] )+
            int cnt33=0;
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==EXPR||LA33_0==ID||(LA33_0>=QUOTED_STRING && LA33_0<=DOUBLEQUOTED_STRING)||LA33_0==112||LA33_0==114) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // MySQLWalker.g:363:17: displayed_column[$select]
            	    {
            	    pushFollow(FOLLOW_displayed_column_in_select_list1077);
            	    displayed_column(select);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt33 >= 1 ) break loop33;
                        EarlyExitException eee =
                            new EarlyExitException(33, input);
                        throw eee;
                }
                cnt33++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:365:1: fromClause[Select select] : ^( TABLENAMES ( table[$select, true] )+ ) ;
    public final MySQLWalker.fromClause_return fromClause(Select select) throws RecognitionException {
        MySQLWalker.fromClause_return retval = new MySQLWalker.fromClause_return();
        retval.start = input.LT(1);

        needToRewriteTableName = true;
        try {
            // MySQLWalker.g:368:2: ( ^( TABLENAMES ( table[$select, true] )+ ) )
            // MySQLWalker.g:368:3: ^( TABLENAMES ( table[$select, true] )+ )
            {
            match(input,TABLENAMES,FOLLOW_TABLENAMES_in_fromClause1102); 

            match(input, Token.DOWN, null); 
            // MySQLWalker.g:368:16: ( table[$select, true] )+
            int cnt34=0;
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==TABLENAME) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // MySQLWalker.g:368:16: table[$select, true]
            	    {
            	    pushFollow(FOLLOW_table_in_fromClause1104);
            	    table(select, true);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt34 >= 1 ) break loop34;
                        EarlyExitException eee =
                            new EarlyExitException(34, input);
                        throw eee;
                }
                cnt34++;
            } while (true);


            match(input, Token.UP, null); 

            }

            needToRewriteTableName = false;
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:371:1: table[DMLCommon common, boolean needToRewriteTableName] : ^( TABLENAME table_spec[$common, $needToRewriteTableName] ) ;
    public final MySQLWalker.table_return table(DMLCommon common, boolean needToRewriteTableName) throws RecognitionException {
        MySQLWalker.table_return retval = new MySQLWalker.table_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:372:2: ( ^( TABLENAME table_spec[$common, $needToRewriteTableName] ) )
            // MySQLWalker.g:372:3: ^( TABLENAME table_spec[$common, $needToRewriteTableName] )
            {
            match(input,TABLENAME,FOLLOW_TABLENAME_in_table1119); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_table_spec_in_table1121);
            table_spec(common, needToRewriteTableName);

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:374:1: tables[DMLCommon common, boolean needToRewriteTableName] : ^( TABLENAMES ( table[$common, $needToRewriteTableName] )+ ) ;
    public final MySQLWalker.tables_return tables(DMLCommon common, boolean needToRewriteTableName) throws RecognitionException {
        MySQLWalker.tables_return retval = new MySQLWalker.tables_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:375:2: ( ^( TABLENAMES ( table[$common, $needToRewriteTableName] )+ ) )
            // MySQLWalker.g:375:3: ^( TABLENAMES ( table[$common, $needToRewriteTableName] )+ )
            {
            match(input,TABLENAMES,FOLLOW_TABLENAMES_in_tables1134); 

            match(input, Token.DOWN, null); 
            // MySQLWalker.g:375:16: ( table[$common, $needToRewriteTableName] )+
            int cnt35=0;
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==TABLENAME) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // MySQLWalker.g:375:16: table[$common, $needToRewriteTableName]
            	    {
            	    pushFollow(FOLLOW_table_in_tables1136);
            	    table(common, needToRewriteTableName);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt35 >= 1 ) break loop35;
                        EarlyExitException eee =
                            new EarlyExitException(35, input);
                        throw eee;
                }
                cnt35++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:377:1: table_spec[DMLCommon common, boolean needToRewriteTableName] : ( ( schema_name )? table_name[$needToRewriteTableName] ( alias )? | subquery ( alias )? );
    public final MySQLWalker.table_spec_return table_spec(DMLCommon common, boolean needToRewriteTableName) throws RecognitionException {
        MySQLWalker.table_spec_return retval = new MySQLWalker.table_spec_return();
        retval.start = input.LT(1);

        MySQLWalker.table_name_return table_name54 = null;

        MySQLWalker.schema_name_return schema_name55 = null;

        MySQLWalker.alias_return alias56 = null;

        MySQLWalker.subquery_return subquery57 = null;

        MySQLWalker.alias_return alias58 = null;


        try {
            // MySQLWalker.g:378:2: ( ( schema_name )? table_name[$needToRewriteTableName] ( alias )? | subquery ( alias )? )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==ASTERISK||LA39_0==ID) ) {
                alt39=1;
            }
            else if ( (LA39_0==SUBQUERY) ) {
                alt39=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // MySQLWalker.g:378:3: ( schema_name )? table_name[$needToRewriteTableName] ( alias )?
                    {
                    // MySQLWalker.g:378:3: ( schema_name )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==ASTERISK||LA36_0==ID) ) {
                        int LA36_1 = input.LA(2);

                        if ( (LA36_1==ASTERISK||LA36_1==ID) ) {
                            alt36=1;
                        }
                    }
                    switch (alt36) {
                        case 1 :
                            // MySQLWalker.g:378:5: schema_name
                            {
                            pushFollow(FOLLOW_schema_name_in_table_spec1151);
                            schema_name55=schema_name();

                            state._fsp--;


                            }
                            break;

                    }

                    pushFollow(FOLLOW_table_name_in_table_spec1155);
                    table_name54=table_name(needToRewriteTableName);

                    state._fsp--;

                    // MySQLWalker.g:378:56: ( alias )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==AS) ) {
                        int LA37_1 = input.LA(2);

                        if ( (LA37_1==DOWN) ) {
                            int LA37_3 = input.LA(3);

                            if ( (LA37_3==ASTERISK||LA37_3==ID) ) {
                                int LA37_4 = input.LA(4);

                                if ( (LA37_4==UP) ) {
                                    alt37=1;
                                }
                            }
                        }
                    }
                    switch (alt37) {
                        case 1 :
                            // MySQLWalker.g:378:56: alias
                            {
                            pushFollow(FOLLOW_alias_in_table_spec1159);
                            alias56=alias();

                            state._fsp--;


                            }
                            break;

                    }


                    		common.addTableNameAndSchemaName((table_name54!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(table_name54.start),
                      input.getTreeAdaptor().getTokenStopIndex(table_name54.start))):null),(schema_name55!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(schema_name55.start),
                      input.getTreeAdaptor().getTokenStopIndex(schema_name55.start))):null),(alias56!=null?alias56.aliText:null));
                    	

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:382:3: subquery ( alias )?
                    {
                    pushFollow(FOLLOW_subquery_in_table_spec1167);
                    subquery57=subquery();

                    state._fsp--;

                    // MySQLWalker.g:382:12: ( alias )?
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
                            // MySQLWalker.g:382:12: alias
                            {
                            pushFollow(FOLLOW_alias_in_table_spec1169);
                            alias58=alias();

                            state._fsp--;


                            }
                            break;

                    }


                    		common.addTableSubQuery((subquery57!=null?subquery57.subselect:null),(alias58!=null?alias58.aliText:null));

                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:389:1: schema_name : identifier ;
    public final MySQLWalker.schema_name_return schema_name() throws RecognitionException {
        MySQLWalker.schema_name_return retval = new MySQLWalker.schema_name_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:390:2: ( identifier )
            // MySQLWalker.g:390:3: identifier
            {
            pushFollow(FOLLOW_identifier_in_schema_name1184);
            identifier();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:392:1: subquery returns [Select subselect] : ^( SUBQUERY select_command ) ;
    public final MySQLWalker.subquery_return subquery() throws RecognitionException {
        MySQLWalker.subquery_return retval = new MySQLWalker.subquery_return();
        retval.start = input.LT(1);

        MySQLWalker.select_command_return select_command59 = null;


        try {
            // MySQLWalker.g:392:36: ( ^( SUBQUERY select_command ) )
            // MySQLWalker.g:393:2: ^( SUBQUERY select_command )
            {
            match(input,SUBQUERY,FOLLOW_SUBQUERY_in_subquery1198); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_select_command_in_subquery1200);
            select_command59=select_command();

            state._fsp--;


            match(input, Token.UP, null); 

            	retval.subselect =(select_command59!=null?select_command59.select:null);
            	retval.subselect.setSubSelect(true);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:399:1: table_name[boolean localNeedToRewriteTableName] : identifier ;
    public final MySQLWalker.table_name_return table_name(boolean localNeedToRewriteTableName) throws RecognitionException {
        MySQLWalker.table_name_return retval = new MySQLWalker.table_name_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:400:2: ( identifier )
            // MySQLWalker.g:400:3: identifier
            {
            pushFollow(FOLLOW_identifier_in_table_name1217);
            identifier();

            state._fsp--;


            		if(localNeedToRewriteTableName || needToRewriteTableName){
            			pos2TableName.put(((CommonTree)retval.start).getCharPositionInLine(), input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(retval.start),
              input.getTreeAdaptor().getTokenStopIndex(retval.start)));}
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:406:1: displayed_column[Select select] : ( ^( quoted_string ( alias )? ) | ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? ) | ^( count ( distinct )? countColumn ( alias )? ) | ^( ID ( table_alias )? ( columnAnt )? ( alias )? ) | ^( EXPR expr_add[new BindIndexHolder()] ( alias )? ) );
    public final MySQLWalker.displayed_column_return displayed_column(Select select) throws RecognitionException {
        MySQLWalker.displayed_column_return retval = new MySQLWalker.displayed_column_return();
        retval.start = input.LT(1);

        CommonTree ID66=null;
        MySQLWalker.quoted_string_return quoted_string60 = null;

        MySQLWalker.alias_return alias61 = null;

        MySQLWalker.alias_return alias62 = null;

        MySQLWalker.countColumn_return countColumn63 = null;

        MySQLWalker.distinct_return distinct64 = null;

        MySQLWalker.alias_return alias65 = null;

        MySQLWalker.alias_return alias67 = null;

        MySQLWalker.table_alias_return table_alias68 = null;

        MySQLWalker.columnAnt_return columnAnt69 = null;

        MySQLWalker.expr_add_return expr_add70 = null;

        MySQLWalker.alias_return alias71 = null;


        try {
            // MySQLWalker.g:407:2: ( ^( quoted_string ( alias )? ) | ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? ) | ^( count ( distinct )? countColumn ( alias )? ) | ^( ID ( table_alias )? ( columnAnt )? ( alias )? ) | ^( EXPR expr_add[new BindIndexHolder()] ( alias )? ) )
            int alt49=5;
            switch ( input.LA(1) ) {
            case QUOTED_STRING:
            case DOUBLEQUOTED_STRING:
                {
                alt49=1;
                }
                break;
            case 112:
                {
                alt49=2;
                }
                break;
            case 114:
                {
                alt49=3;
                }
                break;
            case ID:
                {
                alt49=4;
                }
                break;
            case EXPR:
                {
                alt49=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }

            switch (alt49) {
                case 1 :
                    // MySQLWalker.g:408:2: ^( quoted_string ( alias )? )
                    {
                    pushFollow(FOLLOW_quoted_string_in_displayed_column1235);
                    quoted_string60=quoted_string();

                    state._fsp--;


                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // MySQLWalker.g:408:18: ( alias )?
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==AS) ) {
                            alt40=1;
                        }
                        switch (alt40) {
                            case 1 :
                                // MySQLWalker.g:408:18: alias
                                {
                                pushFollow(FOLLOW_alias_in_displayed_column1237);
                                alias61=alias();

                                state._fsp--;


                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }
                    select.addColumn("", (quoted_string60!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(quoted_string60.start),
                      input.getTreeAdaptor().getTokenStopIndex(quoted_string60.start))):null), (alias61!=null?alias61.aliText:null));

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:409:3: ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? )
                    {
                    pushFollow(FOLLOW_concat_in_displayed_column1246);
                    concat();

                    state._fsp--;


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_identifiedOrQuotedString_in_displayed_column1248);
                    identifiedOrQuotedString();

                    state._fsp--;

                    // MySQLWalker.g:409:37: ( identifiedOrQuotedString )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==COL_TAB||LA41_0==ASTERISK||LA41_0==ID||(LA41_0>=QUOTED_STRING && LA41_0<=DOUBLEQUOTED_STRING)) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // MySQLWalker.g:409:38: identifiedOrQuotedString
                    	    {
                    	    pushFollow(FOLLOW_identifiedOrQuotedString_in_displayed_column1251);
                    	    identifiedOrQuotedString();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop41;
                        }
                    } while (true);

                    // MySQLWalker.g:409:65: ( alias )?
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==AS) ) {
                        alt42=1;
                    }
                    switch (alt42) {
                        case 1 :
                            // MySQLWalker.g:409:65: alias
                            {
                            pushFollow(FOLLOW_alias_in_displayed_column1255);
                            alias62=alias();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    			Concat concat = new Concat();
                    			concat.setAlias((alias62!=null?alias62.aliText:null));
                    			select.addColumn(concat);
                    		

                    }
                    break;
                case 3 :
                    // MySQLWalker.g:415:3: ^( count ( distinct )? countColumn ( alias )? )
                    {
                    pushFollow(FOLLOW_count_in_displayed_column1266);
                    count();

                    state._fsp--;


                    match(input, Token.DOWN, null); 
                    // MySQLWalker.g:415:11: ( distinct )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==113) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // MySQLWalker.g:415:11: distinct
                            {
                            pushFollow(FOLLOW_distinct_in_displayed_column1268);
                            distinct64=distinct();

                            state._fsp--;


                            }
                            break;

                    }

                    pushFollow(FOLLOW_countColumn_in_displayed_column1271);
                    countColumn63=countColumn();

                    state._fsp--;

                    // MySQLWalker.g:415:33: ( alias )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==AS) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // MySQLWalker.g:415:33: alias
                            {
                            pushFollow(FOLLOW_alias_in_displayed_column1273);
                            alias65=alias();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		Count count = new Count();
                    		count.setTable((countColumn63!=null?countColumn63.infos:null).get(0).toString());
                    		count.setColumn((countColumn63!=null?countColumn63.infos:null).get(1).toString());
                    		count.setHasDistinct((distinct64!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(distinct64.start),
                      input.getTreeAdaptor().getTokenStopIndex(distinct64.start))):null)!=null);
                    		count.setAlias((alias65!=null?alias65.aliText:null));
                    		select.addColumn(count);
                    	

                    }
                    break;
                case 4 :
                    // MySQLWalker.g:423:3: ^( ID ( table_alias )? ( columnAnt )? ( alias )? )
                    {
                    ID66=(CommonTree)match(input,ID,FOLLOW_ID_in_displayed_column1282); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // MySQLWalker.g:423:8: ( table_alias )?
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==COL_TAB) ) {
                            alt45=1;
                        }
                        switch (alt45) {
                            case 1 :
                                // MySQLWalker.g:423:8: table_alias
                                {
                                pushFollow(FOLLOW_table_alias_in_displayed_column1284);
                                table_alias68=table_alias();

                                state._fsp--;


                                }
                                break;

                        }

                        // MySQLWalker.g:423:21: ( columnAnt )?
                        int alt46=2;
                        int LA46_0 = input.LA(1);

                        if ( (LA46_0==ASTERISK||LA46_0==ID) ) {
                            alt46=1;
                        }
                        switch (alt46) {
                            case 1 :
                                // MySQLWalker.g:423:21: columnAnt
                                {
                                pushFollow(FOLLOW_columnAnt_in_displayed_column1287);
                                columnAnt69=columnAnt();

                                state._fsp--;


                                }
                                break;

                        }

                        // MySQLWalker.g:423:32: ( alias )?
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==AS) ) {
                            alt47=1;
                        }
                        switch (alt47) {
                            case 1 :
                                // MySQLWalker.g:423:32: alias
                                {
                                pushFollow(FOLLOW_alias_in_displayed_column1290);
                                alias67=alias();

                                state._fsp--;


                                }
                                break;

                        }


                        match(input, Token.UP, null); 
                    }

                    		GroupFunction func=groupFunc.get((ID66!=null?ID66.getText():null));
                    		func.setAlias((alias67!=null?alias67.aliText:null));
                    		func.setTable((table_alias68!=null?table_alias68.aText:null));
                    		func.setColumn((columnAnt69!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(columnAnt69.start),
                      input.getTreeAdaptor().getTokenStopIndex(columnAnt69.start))):null));
                    		select.addColumn(func);
                    	

                    }
                    break;
                case 5 :
                    // MySQLWalker.g:433:3: ^( EXPR expr_add[new BindIndexHolder()] ( alias )? )
                    {
                    match(input,EXPR,FOLLOW_EXPR_in_displayed_column1303); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_add_in_displayed_column1305);
                    expr_add70=expr_add(new BindIndexHolder());

                    state._fsp--;

                    // MySQLWalker.g:433:42: ( alias )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==AS) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // MySQLWalker.g:433:42: alias
                            {
                            pushFollow(FOLLOW_alias_in_displayed_column1308);
                            alias71=alias();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		if((expr_add70!=null?expr_add70.valueObjExpr:null) instanceof ColumnObject) {
                    			ColumnObject cobj = (ColumnObject)(expr_add70!=null?expr_add70.valueObjExpr:null);
                    			select.addColumn(cobj.getTable(), cobj.getColumnName(), (alias71!=null?alias71.aliText:null));
                    		} else {
                    			select.addColumn("", (expr_add70!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(expr_add70.start),
                      input.getTreeAdaptor().getTokenStopIndex(expr_add70.start))):null), (alias71!=null?alias71.aliText:null));
                    		}
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:444:1: columnAnt returns [String aText] : ( ASTERISK | identifier );
    public final MySQLWalker.columnAnt_return columnAnt() throws RecognitionException {
        MySQLWalker.columnAnt_return retval = new MySQLWalker.columnAnt_return();
        retval.start = input.LT(1);

        CommonTree ASTERISK72=null;
        MySQLWalker.identifier_return identifier73 = null;


        try {
            // MySQLWalker.g:445:2: ( ASTERISK | identifier )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==ASTERISK) ) {
                alt50=1;
            }
            else if ( (LA50_0==ID) ) {
                alt50=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }
            switch (alt50) {
                case 1 :
                    // MySQLWalker.g:445:3: ASTERISK
                    {
                    ASTERISK72=(CommonTree)match(input,ASTERISK,FOLLOW_ASTERISK_in_columnAnt1331); 
                    retval.aText =(ASTERISK72!=null?ASTERISK72.getText():null);

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:446:3: identifier
                    {
                    pushFollow(FOLLOW_identifier_in_columnAnt1337);
                    identifier73=identifier();

                    state._fsp--;

                    retval.aText =(identifier73!=null?(input.getTokenStream().toString(
                      input.getTreeAdaptor().getTokenStartIndex(identifier73.start),
                      input.getTreeAdaptor().getTokenStopIndex(identifier73.start))):null);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:449:1: quoted_string returns [String aText] : ( QUOTED_STRING | DOUBLEQUOTED_STRING );
    public final MySQLWalker.quoted_string_return quoted_string() throws RecognitionException {
        MySQLWalker.quoted_string_return retval = new MySQLWalker.quoted_string_return();
        retval.start = input.LT(1);

        CommonTree QUOTED_STRING74=null;
        CommonTree DOUBLEQUOTED_STRING75=null;

        try {
            // MySQLWalker.g:450:2: ( QUOTED_STRING | DOUBLEQUOTED_STRING )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==QUOTED_STRING) ) {
                alt51=1;
            }
            else if ( (LA51_0==DOUBLEQUOTED_STRING) ) {
                alt51=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // MySQLWalker.g:450:4: QUOTED_STRING
                    {
                    QUOTED_STRING74=(CommonTree)match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_quoted_string1353); 
                    retval.aText = (QUOTED_STRING74!=null?QUOTED_STRING74.getText():null).substring(1, (QUOTED_STRING74!=null?QUOTED_STRING74.getText():null).length() - 1);

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:451:4: DOUBLEQUOTED_STRING
                    {
                    DOUBLEQUOTED_STRING75=(CommonTree)match(input,DOUBLEQUOTED_STRING,FOLLOW_DOUBLEQUOTED_STRING_in_quoted_string1360); 
                    retval.aText = (DOUBLEQUOTED_STRING75!=null?DOUBLEQUOTED_STRING75.getText():null).substring(1, (DOUBLEQUOTED_STRING75!=null?DOUBLEQUOTED_STRING75.getText():null).length() - 1);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:454:1: identifier : ( ID | ASTERISK );
    public final MySQLWalker.identifier_return identifier() throws RecognitionException {
        MySQLWalker.identifier_return retval = new MySQLWalker.identifier_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:455:2: ( ID | ASTERISK )
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
    // MySQLWalker.g:458:1: table_alias returns [String aText] : ^( COL_TAB identifier ) ;
    public final MySQLWalker.table_alias_return table_alias() throws RecognitionException {
        MySQLWalker.table_alias_return retval = new MySQLWalker.table_alias_return();
        retval.start = input.LT(1);

        MySQLWalker.identifier_return identifier76 = null;


        try {
            // MySQLWalker.g:459:2: ( ^( COL_TAB identifier ) )
            // MySQLWalker.g:459:3: ^( COL_TAB identifier )
            {
            match(input,COL_TAB,FOLLOW_COL_TAB_in_table_alias1393); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_identifier_in_table_alias1395);
            identifier76=identifier();

            state._fsp--;

            retval.aText =(identifier76!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(identifier76.start),
              input.getTreeAdaptor().getTokenStopIndex(identifier76.start))):null);

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:462:1: alias returns [String aliText] : ^( AS identifier ) ;
    public final MySQLWalker.alias_return alias() throws RecognitionException {
        MySQLWalker.alias_return retval = new MySQLWalker.alias_return();
        retval.start = input.LT(1);

        MySQLWalker.identifier_return identifier77 = null;


        try {
            // MySQLWalker.g:463:2: ( ^( AS identifier ) )
            // MySQLWalker.g:463:3: ^( AS identifier )
            {
            match(input,AS,FOLLOW_AS_in_alias1414); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_identifier_in_alias1416);
            identifier77=identifier();

            state._fsp--;


            match(input, Token.UP, null); 
            retval.aliText =(identifier77!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(identifier77.start),
              input.getTreeAdaptor().getTokenStopIndex(identifier77.start))):null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:470:1: select_command returns [Select select] : selectClause[$select] ( fromClause[$select] )? ( joinClause[$select] )* ( whereClause[$select.getWhere()] )? ( groupByClause[$select.getWhere()] )? ( orderByClause[$select.getWhere()] )? ( limitClause[(MyWhereCondition)$select.getWhere()] )? ;
    public final MySQLWalker.select_command_return select_command() throws RecognitionException {
        MySQLWalker.select_command_return retval = new MySQLWalker.select_command_return();
        retval.start = input.LT(1);

        retval.select =new MySelect();
        try {
            // MySQLWalker.g:472:6: ( selectClause[$select] ( fromClause[$select] )? ( joinClause[$select] )* ( whereClause[$select.getWhere()] )? ( groupByClause[$select.getWhere()] )? ( orderByClause[$select.getWhere()] )? ( limitClause[(MyWhereCondition)$select.getWhere()] )? )
            // MySQLWalker.g:472:8: selectClause[$select] ( fromClause[$select] )? ( joinClause[$select] )* ( whereClause[$select.getWhere()] )? ( groupByClause[$select.getWhere()] )? ( orderByClause[$select.getWhere()] )? ( limitClause[(MyWhereCondition)$select.getWhere()] )?
            {
            pushFollow(FOLLOW_selectClause_in_select_command1445);
            selectClause(retval.select);

            state._fsp--;

            // MySQLWalker.g:472:30: ( fromClause[$select] )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==TABLENAMES) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // MySQLWalker.g:472:31: fromClause[$select]
                    {
                    pushFollow(FOLLOW_fromClause_in_select_command1449);
                    fromClause(retval.select);

                    state._fsp--;


                    }
                    break;

            }

            // MySQLWalker.g:472:53: ( joinClause[$select] )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( ((LA53_0>=108 && LA53_0<=111)) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // MySQLWalker.g:472:54: joinClause[$select]
            	    {
            	    pushFollow(FOLLOW_joinClause_in_select_command1455);
            	    joinClause(retval.select);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);

            // MySQLWalker.g:472:76: ( whereClause[$select.getWhere()] )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==WHERE) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // MySQLWalker.g:472:77: whereClause[$select.getWhere()]
                    {
                    pushFollow(FOLLOW_whereClause_in_select_command1461);
                    whereClause(retval.select.getWhere());

                    state._fsp--;


                    }
                    break;

            }

            // MySQLWalker.g:472:111: ( groupByClause[$select.getWhere()] )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==GROUPBY) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // MySQLWalker.g:472:112: groupByClause[$select.getWhere()]
                    {
                    pushFollow(FOLLOW_groupByClause_in_select_command1467);
                    groupByClause(retval.select.getWhere());

                    state._fsp--;


                    }
                    break;

            }

            // MySQLWalker.g:472:148: ( orderByClause[$select.getWhere()] )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==ORDERBY) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // MySQLWalker.g:472:149: orderByClause[$select.getWhere()]
                    {
                    pushFollow(FOLLOW_orderByClause_in_select_command1473);
                    orderByClause(retval.select.getWhere());

                    state._fsp--;


                    }
                    break;

            }

            // MySQLWalker.g:472:186: ( limitClause[(MyWhereCondition)$select.getWhere()] )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==106) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // MySQLWalker.g:472:187: limitClause[(MyWhereCondition)$select.getWhere()]
                    {
                    pushFollow(FOLLOW_limitClause_in_select_command1480);
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
        return retval;
    }
    // $ANTLR end "select_command"

    public static class groupByClause_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "groupByClause"
    // MySQLWalker.g:474:1: groupByClause[WhereCondition where] : ^( GROUPBY groupByColumns[$where] ) ;
    public final MySQLWalker.groupByClause_return groupByClause(WhereCondition where) throws RecognitionException {
        MySQLWalker.groupByClause_return retval = new MySQLWalker.groupByClause_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:475:2: ( ^( GROUPBY groupByColumns[$where] ) )
            // MySQLWalker.g:475:4: ^( GROUPBY groupByColumns[$where] )
            {
            match(input,GROUPBY,FOLLOW_GROUPBY_in_groupByClause1500); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_groupByColumns_in_groupByClause1502);
            groupByColumns(where);

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:477:1: groupByColumns[WhereCondition where] : ( groupByColumn[$where] )+ ;
    public final MySQLWalker.groupByColumns_return groupByColumns(WhereCondition where) throws RecognitionException {
        MySQLWalker.groupByColumns_return retval = new MySQLWalker.groupByColumns_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:478:2: ( ( groupByColumn[$where] )+ )
            // MySQLWalker.g:478:4: ( groupByColumn[$where] )+
            {
            // MySQLWalker.g:478:4: ( groupByColumn[$where] )+
            int cnt58=0;
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==ASTERISK||LA58_0==ID) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // MySQLWalker.g:478:5: groupByColumn[$where]
            	    {
            	    pushFollow(FOLLOW_groupByColumn_in_groupByColumns1517);
            	    groupByColumn(where);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt58 >= 1 ) break loop58;
                        EarlyExitException eee =
                            new EarlyExitException(58, input);
                        throw eee;
                }
                cnt58++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:480:1: groupByColumn[WhereCondition where] : identifier ;
    public final MySQLWalker.groupByColumn_return groupByColumn(WhereCondition where) throws RecognitionException {
        MySQLWalker.groupByColumn_return retval = new MySQLWalker.groupByColumn_return();
        retval.start = input.LT(1);

        MySQLWalker.identifier_return identifier78 = null;


        try {
            // MySQLWalker.g:481:2: ( identifier )
            // MySQLWalker.g:481:4: identifier
            {
            pushFollow(FOLLOW_identifier_in_groupByColumn1532);
            identifier78=identifier();

            state._fsp--;

            where.addGroupByColumn((identifier78!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(identifier78.start),
              input.getTreeAdaptor().getTokenStopIndex(identifier78.start))):null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:483:6: delete_command returns [Delete del] : ^( DELETE tables[$del, true] ( whereClause[$del.getWhere()] )? ( orderByClause[$del.getWhere()] )? ( limitClause[(MyWhereCondition)$del.getWhere()] )? ) ;
    public final MySQLWalker.delete_command_return delete_command() throws RecognitionException {
        MySQLWalker.delete_command_return retval = new MySQLWalker.delete_command_return();
        retval.start = input.LT(1);

        retval.del =new MyDelete();
        try {
            // MySQLWalker.g:485:2: ( ^( DELETE tables[$del, true] ( whereClause[$del.getWhere()] )? ( orderByClause[$del.getWhere()] )? ( limitClause[(MyWhereCondition)$del.getWhere()] )? ) )
            // MySQLWalker.g:485:3: ^( DELETE tables[$del, true] ( whereClause[$del.getWhere()] )? ( orderByClause[$del.getWhere()] )? ( limitClause[(MyWhereCondition)$del.getWhere()] )? )
            {
            match(input,DELETE,FOLLOW_DELETE_in_delete_command1556); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_tables_in_delete_command1558);
            tables(retval.del, true);

            state._fsp--;

            // MySQLWalker.g:485:31: ( whereClause[$del.getWhere()] )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==WHERE) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // MySQLWalker.g:485:31: whereClause[$del.getWhere()]
                    {
                    pushFollow(FOLLOW_whereClause_in_delete_command1561);
                    whereClause(retval.del.getWhere());

                    state._fsp--;


                    }
                    break;

            }

            // MySQLWalker.g:485:61: ( orderByClause[$del.getWhere()] )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==ORDERBY) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // MySQLWalker.g:485:61: orderByClause[$del.getWhere()]
                    {
                    pushFollow(FOLLOW_orderByClause_in_delete_command1565);
                    orderByClause(retval.del.getWhere());

                    state._fsp--;


                    }
                    break;

            }

            // MySQLWalker.g:485:93: ( limitClause[(MyWhereCondition)$del.getWhere()] )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==106) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // MySQLWalker.g:485:93: limitClause[(MyWhereCondition)$del.getWhere()]
                    {
                    pushFollow(FOLLOW_limitClause_in_delete_command1569);
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
    // MySQLWalker.g:487:1: update_command returns [Update update] : ^( UPDATE tables[$update, true] setclause[$update] ( whereClause[$update.getWhere()] )? ( limitClause[(MyWhereCondition)$update.getWhere()] )? ) ;
    public final MySQLWalker.update_command_return update_command() throws RecognitionException {
        MySQLWalker.update_command_return retval = new MySQLWalker.update_command_return();
        retval.start = input.LT(1);

        retval.update =new MyUpdate();
        try {
            // MySQLWalker.g:489:2: ( ^( UPDATE tables[$update, true] setclause[$update] ( whereClause[$update.getWhere()] )? ( limitClause[(MyWhereCondition)$update.getWhere()] )? ) )
            // MySQLWalker.g:489:3: ^( UPDATE tables[$update, true] setclause[$update] ( whereClause[$update.getWhere()] )? ( limitClause[(MyWhereCondition)$update.getWhere()] )? )
            {
            match(input,UPDATE,FOLLOW_UPDATE_in_update_command1589); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_tables_in_update_command1591);
            tables(retval.update, true);

            state._fsp--;

            pushFollow(FOLLOW_setclause_in_update_command1594);
            setclause(retval.update);

            state._fsp--;

            // MySQLWalker.g:489:53: ( whereClause[$update.getWhere()] )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==WHERE) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // MySQLWalker.g:489:53: whereClause[$update.getWhere()]
                    {
                    pushFollow(FOLLOW_whereClause_in_update_command1597);
                    whereClause(retval.update.getWhere());

                    state._fsp--;


                    }
                    break;

            }

            // MySQLWalker.g:489:86: ( limitClause[(MyWhereCondition)$update.getWhere()] )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==106) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // MySQLWalker.g:489:86: limitClause[(MyWhereCondition)$update.getWhere()]
                    {
                    pushFollow(FOLLOW_limitClause_in_update_command1601);
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
        return retval;
    }
    // $ANTLR end "update_command"

    public static class limitClause_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "limitClause"
    // MySQLWalker.g:492:1: limitClause[MyWhereCondition where] : ^( 'LIMIT' ( skip[$where] )? range[$where] ) ;
    public final MySQLWalker.limitClause_return limitClause(MyWhereCondition where) throws RecognitionException {
        MySQLWalker.limitClause_return retval = new MySQLWalker.limitClause_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:493:2: ( ^( 'LIMIT' ( skip[$where] )? range[$where] ) )
            // MySQLWalker.g:493:3: ^( 'LIMIT' ( skip[$where] )? range[$where] )
            {
            match(input,106,FOLLOW_106_in_limitClause1617); 

            match(input, Token.DOWN, null); 
            // MySQLWalker.g:493:13: ( skip[$where] )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==SKIP) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // MySQLWalker.g:493:13: skip[$where]
                    {
                    pushFollow(FOLLOW_skip_in_limitClause1619);
                    skip(where);

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_range_in_limitClause1623);
            range(where);

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:495:1: skip[MyWhereCondition where] : ( ^( SKIP N ) | ^( SKIP a= '?' ) );
    public final MySQLWalker.skip_return skip(MyWhereCondition where) throws RecognitionException {
        MySQLWalker.skip_return retval = new MySQLWalker.skip_return();
        retval.start = input.LT(1);

        CommonTree a=null;
        CommonTree N79=null;

        try {
            // MySQLWalker.g:496:2: ( ^( SKIP N ) | ^( SKIP a= '?' ) )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==SKIP) ) {
                int LA65_1 = input.LA(2);

                if ( (LA65_1==DOWN) ) {
                    int LA65_2 = input.LA(3);

                    if ( (LA65_2==N) ) {
                        alt65=1;
                    }
                    else if ( (LA65_2==96) ) {
                        alt65=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 65, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 65, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }
            switch (alt65) {
                case 1 :
                    // MySQLWalker.g:496:3: ^( SKIP N )
                    {
                    match(input,SKIP,FOLLOW_SKIP_in_skip1637); 

                    match(input, Token.DOWN, null); 
                    N79=(CommonTree)match(input,N,FOLLOW_N_in_skip1639); 

                    match(input, Token.UP, null); 

                    		where.setStart(Integer.valueOf((N79!=null?N79.getText():null)));
                    		where.limitInfo.skipIdx=(N79!=null?N79.getCharPositionInLine():0);
                    		where.limitInfo.skip=(N79!=null?N79.getText():null);
                    	

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:501:3: ^( SKIP a= '?' )
                    {
                    match(input,SKIP,FOLLOW_SKIP_in_skip1646); 

                    match(input, Token.DOWN, null); 
                    a=(CommonTree)match(input,96,FOLLOW_96_in_skip1650); 

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
        return retval;
    }
    // $ANTLR end "skip"

    public static class range_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "range"
    // MySQLWalker.g:508:1: range[MyWhereCondition where] : ( ^( RANGE N ) | ^( RANGE a= '?' ) );
    public final MySQLWalker.range_return range(MyWhereCondition where) throws RecognitionException {
        MySQLWalker.range_return retval = new MySQLWalker.range_return();
        retval.start = input.LT(1);

        CommonTree a=null;
        CommonTree N80=null;

        try {
            // MySQLWalker.g:508:31: ( ^( RANGE N ) | ^( RANGE a= '?' ) )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==RANGE) ) {
                int LA66_1 = input.LA(2);

                if ( (LA66_1==DOWN) ) {
                    int LA66_2 = input.LA(3);

                    if ( (LA66_2==N) ) {
                        alt66=1;
                    }
                    else if ( (LA66_2==96) ) {
                        alt66=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 66, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 66, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }
            switch (alt66) {
                case 1 :
                    // MySQLWalker.g:508:32: ^( RANGE N )
                    {
                    match(input,RANGE,FOLLOW_RANGE_in_range1665); 

                    match(input, Token.DOWN, null); 
                    N80=(CommonTree)match(input,N,FOLLOW_N_in_range1667); 

                    match(input, Token.UP, null); 

                    		where.setRange(Integer.valueOf((N80!=null?N80.getText():null)));
                    		where.limitInfo.rangeIdx=(N80!=null?N80.getCharPositionInLine():0);
                    		where.limitInfo.range=(N80!=null?N80.getText():null);
                    	

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:513:2: ^( RANGE a= '?' )
                    {
                    match(input,RANGE,FOLLOW_RANGE_in_range1673); 

                    match(input, Token.DOWN, null); 
                    a=(CommonTree)match(input,96,FOLLOW_96_in_range1677); 

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
        return retval;
    }
    // $ANTLR end "range"

    protected static class joinClause_scope {
        MyWhereCondition fakeCondition;
    }
    protected Stack joinClause_stack = new Stack();

    public static class joinClause_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "joinClause"
    // MySQLWalker.g:519:1: joinClause[Select select] : ^( joinType table_spec[$select, true] ( alias )? 'ON' sqlCondition[$joinClause::fakeCondition] ) ;
    public final MySQLWalker.joinClause_return joinClause(Select select) throws RecognitionException {
        joinClause_stack.push(new joinClause_scope());
        MySQLWalker.joinClause_return retval = new MySQLWalker.joinClause_return();
        retval.start = input.LT(1);

        ((joinClause_scope)joinClause_stack.peek()).fakeCondition = new MyWhereCondition();
        try {
            // MySQLWalker.g:522:2: ( ^( joinType table_spec[$select, true] ( alias )? 'ON' sqlCondition[$joinClause::fakeCondition] ) )
            // MySQLWalker.g:522:4: ^( joinType table_spec[$select, true] ( alias )? 'ON' sqlCondition[$joinClause::fakeCondition] )
            {
            pushFollow(FOLLOW_joinType_in_joinClause1701);
            joinType();

            state._fsp--;


            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_table_spec_in_joinClause1703);
            table_spec(select, true);

            state._fsp--;

            // MySQLWalker.g:522:41: ( alias )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==AS) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // MySQLWalker.g:522:41: alias
                    {
                    pushFollow(FOLLOW_alias_in_joinClause1706);
                    alias();

                    state._fsp--;


                    }
                    break;

            }

            match(input,107,FOLLOW_107_in_joinClause1709); 
            pushFollow(FOLLOW_sqlCondition_in_joinClause1711);
            sqlCondition(((joinClause_scope)joinClause_stack.peek()).fakeCondition);

            state._fsp--;


            match(input, Token.UP, null); 
            select.incrementBindIndex(((joinClause_scope)joinClause_stack.peek()).fakeCondition.selfAddAndGet());

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
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
    // MySQLWalker.g:524:1: joinType : ( 'INNER JOIN' | 'LEFT JOIN' | 'RIGHT JOIN' | 'JOIN' );
    public final MySQLWalker.joinType_return joinType() throws RecognitionException {
        MySQLWalker.joinType_return retval = new MySQLWalker.joinType_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:525:2: ( 'INNER JOIN' | 'LEFT JOIN' | 'RIGHT JOIN' | 'JOIN' )
            // MySQLWalker.g:
            {
            if ( (input.LA(1)>=108 && input.LA(1)<=111) ) {
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
        return retval;
    }
    // $ANTLR end "joinType"

    public static class concat_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "concat"
    // MySQLWalker.g:527:1: concat : 'CONCAT' ;
    public final MySQLWalker.concat_return concat() throws RecognitionException {
        MySQLWalker.concat_return retval = new MySQLWalker.concat_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:528:2: ( 'CONCAT' )
            // MySQLWalker.g:528:4: 'CONCAT'
            {
            match(input,112,FOLLOW_112_in_concat1747); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:530:1: identifiedOrQuotedString : ( ( ( table_alias )? identifier ) | quoted_string ) ;
    public final MySQLWalker.identifiedOrQuotedString_return identifiedOrQuotedString() throws RecognitionException {
        MySQLWalker.identifiedOrQuotedString_return retval = new MySQLWalker.identifiedOrQuotedString_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:531:2: ( ( ( ( table_alias )? identifier ) | quoted_string ) )
            // MySQLWalker.g:531:4: ( ( ( table_alias )? identifier ) | quoted_string )
            {
            // MySQLWalker.g:531:4: ( ( ( table_alias )? identifier ) | quoted_string )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==COL_TAB||LA69_0==ASTERISK||LA69_0==ID) ) {
                alt69=1;
            }
            else if ( ((LA69_0>=QUOTED_STRING && LA69_0<=DOUBLEQUOTED_STRING)) ) {
                alt69=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }
            switch (alt69) {
                case 1 :
                    // MySQLWalker.g:531:6: ( ( table_alias )? identifier )
                    {
                    // MySQLWalker.g:531:6: ( ( table_alias )? identifier )
                    // MySQLWalker.g:531:7: ( table_alias )? identifier
                    {
                    // MySQLWalker.g:531:7: ( table_alias )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==COL_TAB) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // MySQLWalker.g:531:7: table_alias
                            {
                            pushFollow(FOLLOW_table_alias_in_identifiedOrQuotedString1760);
                            table_alias();

                            state._fsp--;


                            }
                            break;

                    }

                    pushFollow(FOLLOW_identifier_in_identifiedOrQuotedString1763);
                    identifier();

                    state._fsp--;


                    }


                    }
                    break;
                case 2 :
                    // MySQLWalker.g:531:34: quoted_string
                    {
                    pushFollow(FOLLOW_quoted_string_in_identifiedOrQuotedString1768);
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
        return retval;
    }
    // $ANTLR end "identifiedOrQuotedString"

    public static class distinct_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "distinct"
    // MySQLWalker.g:533:1: distinct : 'DISTINCT' ;
    public final MySQLWalker.distinct_return distinct() throws RecognitionException {
        MySQLWalker.distinct_return retval = new MySQLWalker.distinct_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:534:2: ( 'DISTINCT' )
            // MySQLWalker.g:534:4: 'DISTINCT'
            {
            match(input,113,FOLLOW_113_in_distinct1780); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:536:1: count : 'COUNT' ;
    public final MySQLWalker.count_return count() throws RecognitionException {
        MySQLWalker.count_return retval = new MySQLWalker.count_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:537:2: ( 'COUNT' )
            // MySQLWalker.g:537:4: 'COUNT'
            {
            match(input,114,FOLLOW_114_in_count1790); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:539:1: between_and_expression[BindIndexHolder where] returns [Object valueObj] : ( expr_add[$where] | quoted_string );
    public final MySQLWalker.between_and_expression_return between_and_expression(BindIndexHolder where) throws RecognitionException {
        MySQLWalker.between_and_expression_return retval = new MySQLWalker.between_and_expression_return();
        retval.start = input.LT(1);

        MySQLWalker.expr_add_return expr_add81 = null;

        MySQLWalker.quoted_string_return quoted_string82 = null;


        try {
            // MySQLWalker.g:540:2: ( expr_add[$where] | quoted_string )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==COLUMN||LA70_0==QUTED_STR||(LA70_0>=PLUS && LA70_0<=MINUS)||(LA70_0>=ASTERISK && LA70_0<=MOD)||LA70_0==ID||(LA70_0>=N && LA70_0<=NUMBER)||LA70_0==91||LA70_0==94||(LA70_0>=96 && LA70_0<=98)) ) {
                alt70=1;
            }
            else if ( ((LA70_0>=QUOTED_STRING && LA70_0<=DOUBLEQUOTED_STRING)) ) {
                alt70=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }
            switch (alt70) {
                case 1 :
                    // MySQLWalker.g:540:3: expr_add[$where]
                    {
                    pushFollow(FOLLOW_expr_add_in_between_and_expression1804);
                    expr_add81=expr_add(where);

                    state._fsp--;

                    retval.valueObj =(expr_add81!=null?expr_add81.valueObjExpr:null);

                    }
                    break;
                case 2 :
                    // MySQLWalker.g:541:4: quoted_string
                    {
                    pushFollow(FOLLOW_quoted_string_in_between_and_expression1812);
                    quoted_string82=quoted_string();

                    state._fsp--;

                    retval.valueObj = (quoted_string82!=null?quoted_string82.aText:null);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:543:1: identifierOrN : ( identifier | N ) ;
    public final MySQLWalker.identifierOrN_return identifierOrN() throws RecognitionException {
        MySQLWalker.identifierOrN_return retval = new MySQLWalker.identifierOrN_return();
        retval.start = input.LT(1);

        try {
            // MySQLWalker.g:544:2: ( ( identifier | N ) )
            // MySQLWalker.g:545:2: ( identifier | N )
            {
            // MySQLWalker.g:545:2: ( identifier | N )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==ASTERISK||LA71_0==ID) ) {
                alt71=1;
            }
            else if ( (LA71_0==N) ) {
                alt71=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }
            switch (alt71) {
                case 1 :
                    // MySQLWalker.g:545:3: identifier
                    {
                    pushFollow(FOLLOW_identifier_in_identifierOrN1826);
                    identifier();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // MySQLWalker.g:545:16: N
                    {
                    match(input,N,FOLLOW_N_in_identifierOrN1830); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // MySQLWalker.g:547:1: countColumn returns [List infos] : ^( COUNTCOLUMN ( identifier )? identifierOrN ) ;
    public final MySQLWalker.countColumn_return countColumn() throws RecognitionException {
        MySQLWalker.countColumn_return retval = new MySQLWalker.countColumn_return();
        retval.start = input.LT(1);

        MySQLWalker.identifier_return identifier83 = null;

        MySQLWalker.identifierOrN_return identifierOrN84 = null;


        try {
            // MySQLWalker.g:548:2: ( ^( COUNTCOLUMN ( identifier )? identifierOrN ) )
            // MySQLWalker.g:548:4: ^( COUNTCOLUMN ( identifier )? identifierOrN )
            {
            match(input,COUNTCOLUMN,FOLLOW_COUNTCOLUMN_in_countColumn1846); 

            match(input, Token.DOWN, null); 
            // MySQLWalker.g:548:18: ( identifier )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==ASTERISK||LA72_0==ID) ) {
                int LA72_1 = input.LA(2);

                if ( (LA72_1==ASTERISK||LA72_1==ID||LA72_1==N) ) {
                    alt72=1;
                }
            }
            switch (alt72) {
                case 1 :
                    // MySQLWalker.g:548:18: identifier
                    {
                    pushFollow(FOLLOW_identifier_in_countColumn1848);
                    identifier83=identifier();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_identifierOrN_in_countColumn1851);
            identifierOrN84=identifierOrN();

            state._fsp--;


            match(input, Token.UP, null); 

            		retval.infos = new ArrayList(2);
            		retval.infos.add((identifier83!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(identifier83.start),
              input.getTreeAdaptor().getTokenStopIndex(identifier83.start))):null)==null?"":(identifier83!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(identifier83.start),
              input.getTreeAdaptor().getTokenStopIndex(identifier83.start))):null));
            		retval.infos.add((identifierOrN84!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(identifierOrN84.start),
              input.getTreeAdaptor().getTokenStopIndex(identifierOrN84.start))):null)==null?"":(identifierOrN84!=null?(input.getTokenStream().toString(
              input.getTreeAdaptor().getTokenStartIndex(identifierOrN84.start),
              input.getTreeAdaptor().getTokenStopIndex(identifierOrN84.start))):null));
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        return retval;
    }
    // $ANTLR end "countColumn"

    // Delegated rules


    protected DFA17 dfa17 = new DFA17(this);
    static final String DFA17_eotS =
        "\17\uffff";
    static final String DFA17_eofS =
        "\17\uffff";
    static final String DFA17_minS =
        "\1\3\16\uffff";
    static final String DFA17_maxS =
        "\1\126\16\uffff";
    static final String DFA17_acceptS =
        "\1\uffff\1\2\15\1";
    static final String DFA17_specialS =
        "\17\uffff}>";
    static final String[] DFA17_transitionS = {
            "\1\1\15\uffff\1\10\12\uffff\1\11\1\12\3\uffff\1\14\1\13\1\15"+
            "\1\16\3\uffff\1\1\11\uffff\1\2\12\uffff\1\4\1\5\1\3\1\6\1\7"+
            "\23\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "()+ loopback of 179:3: ( condition_expr[$where,$exp] )+";
        }
    }
 

    public static final BitSet FOLLOW_start_rule_in_beg53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_command_in_start_rule70 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_insert_command_in_start_rule75 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_update_command_in_start_rule80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_delete_command_in_start_rule85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SET_in_setclause98 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_updateColumnSpecs_in_setclause100 = new BitSet(new long[]{0x0000100000000008L});
    public static final BitSet FOLLOW_SET_ELE_in_updateColumnSpecs115 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_updateColumnSpec_in_updateColumnSpecs117 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_updateColumnSpec131 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_table_alias_in_updateColumnSpec133 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_identifier_in_updateColumnSpec136 = new BitSet(new long[]{0x1760000000101800L,0x0000000748000060L});
    public static final BitSet FOLLOW_expr_in_updateColumnSpec138 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INSERT_in_insert_command161 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tables_in_insert_command163 = new BitSet(new long[]{0x0000028000000000L});
    public static final BitSet FOLLOW_column_specs_in_insert_command166 = new BitSet(new long[]{0x0000028000000000L});
    public static final BitSet FOLLOW_values_in_insert_command170 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INSERT_VAL_in_values182 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_values185 = new BitSet(new long[]{0x1760000000101808L,0x0000000748000060L});
    public static final BitSet FOLLOW_COLUMNS_in_column_specs201 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_column_spec_in_column_specs203 = new BitSet(new long[]{0x0000000000001008L});
    public static final BitSet FOLLOW_COLUMN_in_column_spec217 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_identifier_in_column_spec219 = new BitSet(new long[]{0x1100000000000008L});
    public static final BitSet FOLLOW_table_name_in_column_spec221 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHERE_in_whereClause241 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_sqlCondition_in_whereClause243 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ORDERBY_in_orderByClause258 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_columnNamesAfterWhere_in_orderByClause260 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere273 = new BitSet(new long[]{0x0000000000000002L,0x000000000000000CL});
    public static final BitSet FOLLOW_ASC_in_columnNameAfterWhere287 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_identifier_in_columnNameAfterWhere290 = new BitSet(new long[]{0x0000200000000008L});
    public static final BitSet FOLLOW_table_alias_in_columnNameAfterWhere292 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DESC_in_columnNameAfterWhere300 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_identifier_in_columnNameAfterWhere303 = new BitSet(new long[]{0x0000200000000008L});
    public static final BitSet FOLLOW_table_alias_in_columnNameAfterWhere305 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SELECT_in_selectClause322 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_distinct_in_selectClause324 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_select_list_in_selectClause327 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONDITION_OR_NOT_in_sqlCondition350 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_condition_in_sqlCondition352 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONDITION_OR_in_sqlCondition359 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_condition_in_sqlCondition361 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_85_in_condition379 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_condition_in_condition383 = new BitSet(new long[]{0xE004011E30020008L,0x0000000000600003L});
    public static final BitSet FOLLOW_86_in_condition395 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_condition_in_condition397 = new BitSet(new long[]{0xE004011E30020008L,0x0000000000600003L});
    public static final BitSet FOLLOW_condition_PAREN_in_condition404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIORITY_in_condition410 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_condition_in_condition412 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_condition_expr_in_condition_PAREN427 = new BitSet(new long[]{0xE004001E30020002L,0x0000000000000003L});
    public static final BitSet FOLLOW_comparisonCondition_in_condition_expr440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inCondition_in_condition_expr446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isCondition_in_condition_expr453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_likeCondition_in_condition_expr460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_betweenCondition_in_condition_expr466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_BETWEEN_in_betweenCondition478 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_between_and_in_betweenCondition480 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_betweenCondition483 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BETWEEN_in_betweenCondition492 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_between_and_in_betweenCondition494 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_betweenCondition497 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_between_and_expression_in_between_and517 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_between_and_expression_in_between_and522 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOT_LIKE_in_likeCondition538 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_likeCondition540 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_likeCondition543 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LIKE_in_likeCondition551 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_likeCondition553 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_likeCondition556 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ISNOT_in_isCondition572 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NULL_in_isCondition574 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_isCondition576 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IS_in_isCondition584 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NULL_in_isCondition586 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_isCondition588 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_comparisonCondition605 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_comparisonCondition607 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_comparisonCondition610 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOT_EQ_in_comparisonCondition620 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_comparisonCondition622 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_comparisonCondition625 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTH_in_comparisonCondition635 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_comparisonCondition637 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_comparisonCondition640 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTH_in_comparisonCondition650 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_comparisonCondition652 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_comparisonCondition655 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LEQ_in_comparisonCondition665 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_comparisonCondition667 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_comparisonCondition670 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GEQ_in_comparisonCondition680 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_comparisonCondition682 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_comparisonCondition685 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONDITION_LEFT_in_left_cond708 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_left_cond710 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IN_LISTS_in_in_list729 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_inCondition_expr_adds_in_in_list731 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IN_in_inCondition749 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_84_in_inCondition753 = new BitSet(new long[]{0x1760000001901800L,0x0000000748000060L});
    public static final BitSet FOLLOW_subquery_in_inCondition756 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_in_list_in_inCondition759 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_left_cond_in_inCondition764 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_expr_add_in_inCondition_expr_adds798 = new BitSet(new long[]{0x1760000000101002L,0x0000000748000060L});
    public static final BitSet FOLLOW_expr_add_in_expr823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subquery_in_expr830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_expr_add857 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_add_in_expr_add861 = new BitSet(new long[]{0x1760000000101000L,0x0000000748000060L});
    public static final BitSet FOLLOW_expr_add_in_expr_add866 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINUS_in_expr_add875 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_add_in_expr_add879 = new BitSet(new long[]{0x1760000000101000L,0x0000000748000060L});
    public static final BitSet FOLLOW_expr_add_in_expr_add884 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASTERISK_in_expr_add892 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_add_in_expr_add896 = new BitSet(new long[]{0x1760000000101000L,0x0000000748000060L});
    public static final BitSet FOLLOW_expr_add_in_expr_add901 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIVIDE_in_expr_add909 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_add_in_expr_add913 = new BitSet(new long[]{0x1760000000101000L,0x0000000748000060L});
    public static final BitSet FOLLOW_expr_add_in_expr_add918 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MOD_in_expr_add926 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_add_in_expr_add930 = new BitSet(new long[]{0x1760000000101000L,0x0000000748000060L});
    public static final BitSet FOLLOW_expr_add_in_expr_add935 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_N_in_expr_add942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_expr_add948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_literal_in_expr_add953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_expr_add957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_expr_add963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_expr_add967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUTED_STR_in_expr_add973 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_quoted_string_in_expr_add975 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_COLUMN_in_expr_add982 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_identifier_in_expr_add984 = new BitSet(new long[]{0x1100000000000008L});
    public static final BitSet FOLLOW_table_name_in_expr_add986 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_expr_add997 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr_add1002 = new BitSet(new long[]{0x1760000000101808L,0x0000000748000060L});
    public static final BitSet FOLLOW_N_in_value1020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_value1024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_value1028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUTED_STR_in_value1033 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_quoted_string_in_value1035 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_97_in_boolean_literal1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_boolean_literal1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECT_LIST_in_select_list1075 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_displayed_column_in_select_list1077 = new BitSet(new long[]{0x1000000000000018L,0x0005000000000600L});
    public static final BitSet FOLLOW_TABLENAMES_in_fromClause1102 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_table_in_fromClause1104 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_TABLENAME_in_table1119 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_table_spec_in_table1121 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TABLENAMES_in_tables1134 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_table_in_tables1136 = new BitSet(new long[]{0x0000000000000208L});
    public static final BitSet FOLLOW_schema_name_in_table_spec1151 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_table_name_in_table_spec1155 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_alias_in_table_spec1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subquery_in_table_spec1167 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_alias_in_table_spec1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_schema_name1184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBQUERY_in_subquery1198 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_select_command_in_subquery1200 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_identifier_in_table_name1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quoted_string_in_displayed_column1235 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_alias_in_displayed_column1237 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_concat_in_displayed_column1246 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_identifiedOrQuotedString_in_displayed_column1248 = new BitSet(new long[]{0x1100200000002008L,0x0000000000000600L});
    public static final BitSet FOLLOW_identifiedOrQuotedString_in_displayed_column1251 = new BitSet(new long[]{0x1100200000002008L,0x0000000000000600L});
    public static final BitSet FOLLOW_alias_in_displayed_column1255 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_count_in_displayed_column1266 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_distinct_in_displayed_column1268 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_countColumn_in_displayed_column1271 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_alias_in_displayed_column1273 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_displayed_column1282 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_table_alias_in_displayed_column1284 = new BitSet(new long[]{0x1100000000002008L});
    public static final BitSet FOLLOW_columnAnt_in_displayed_column1287 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_alias_in_displayed_column1290 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXPR_in_displayed_column1303 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_add_in_displayed_column1305 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_alias_in_displayed_column1308 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASTERISK_in_columnAnt1331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_columnAnt1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTED_STRING_in_quoted_string1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLEQUOTED_STRING_in_quoted_string1360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_identifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COL_TAB_in_table_alias1393 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_identifier_in_table_alias1395 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AS_in_alias1414 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_identifier_in_alias1416 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_selectClause_in_select_command1445 = new BitSet(new long[]{0x0000002000200422L,0x0000F40000000000L});
    public static final BitSet FOLLOW_fromClause_in_select_command1449 = new BitSet(new long[]{0x0000002000200022L,0x0000F40000000000L});
    public static final BitSet FOLLOW_joinClause_in_select_command1455 = new BitSet(new long[]{0x0000002000200022L,0x0000F40000000000L});
    public static final BitSet FOLLOW_whereClause_in_select_command1461 = new BitSet(new long[]{0x0000002000000022L,0x0000040000000000L});
    public static final BitSet FOLLOW_groupByClause_in_select_command1467 = new BitSet(new long[]{0x0000002000000002L,0x0000040000000000L});
    public static final BitSet FOLLOW_orderByClause_in_select_command1473 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});
    public static final BitSet FOLLOW_limitClause_in_select_command1480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GROUPBY_in_groupByClause1500 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_groupByColumns_in_groupByClause1502 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_groupByColumn_in_groupByColumns1517 = new BitSet(new long[]{0x1100000000000002L});
    public static final BitSet FOLLOW_identifier_in_groupByColumn1532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DELETE_in_delete_command1556 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tables_in_delete_command1558 = new BitSet(new long[]{0x0000002000200008L,0x0000040000000000L});
    public static final BitSet FOLLOW_whereClause_in_delete_command1561 = new BitSet(new long[]{0x0000002000000008L,0x0000040000000000L});
    public static final BitSet FOLLOW_orderByClause_in_delete_command1565 = new BitSet(new long[]{0x0000000000000008L,0x0000040000000000L});
    public static final BitSet FOLLOW_limitClause_in_delete_command1569 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UPDATE_in_update_command1589 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tables_in_update_command1591 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_setclause_in_update_command1594 = new BitSet(new long[]{0x0000000000200008L,0x0000040000000000L});
    public static final BitSet FOLLOW_whereClause_in_update_command1597 = new BitSet(new long[]{0x0000000000000008L,0x0000040000000000L});
    public static final BitSet FOLLOW_limitClause_in_update_command1601 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_106_in_limitClause1617 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_skip_in_limitClause1619 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_range_in_limitClause1623 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SKIP_in_skip1637 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_N_in_skip1639 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SKIP_in_skip1646 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_96_in_skip1650 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RANGE_in_range1665 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_N_in_range1667 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RANGE_in_range1673 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_96_in_range1677 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_joinType_in_joinClause1701 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_table_spec_in_joinClause1703 = new BitSet(new long[]{0x0000000000002000L,0x0000080000000000L});
    public static final BitSet FOLLOW_alias_in_joinClause1706 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_107_in_joinClause1709 = new BitSet(new long[]{0x0000000002400000L});
    public static final BitSet FOLLOW_sqlCondition_in_joinClause1711 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_joinType0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_112_in_concat1747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_alias_in_identifiedOrQuotedString1760 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_identifier_in_identifiedOrQuotedString1763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quoted_string_in_identifiedOrQuotedString1768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_distinct1780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_count1790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_add_in_between_and_expression1804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quoted_string_in_between_and_expression1812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_identifierOrN1826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_N_in_identifierOrN1830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNTCOLUMN_in_countColumn1846 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_identifier_in_countColumn1848 = new BitSet(new long[]{0x1100000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_identifierOrN_in_countColumn1851 = new BitSet(new long[]{0x0000000000000008L});

}