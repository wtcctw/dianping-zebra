// $ANTLR 3.3 Nov 30, 2010 12:45:30 MySQLParser.g 2011-11-11 11:16:22

package com.dianping.zebra.shard.parser.qlParser;

//import com.taobao.tddl.client.valueobject.FunctionConvertor;
import java.util.Map;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.RewriteEarlyExitException;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.RewriteRuleTokenStream;
import org.antlr.runtime.tree.TreeAdaptor;

import com.dianping.zebra.shard.parser.sqlParser.groupFunction.GroupFunctionRegister;
import com.dianping.zebra.shard.parser.valueObject.FunctionConvertor;

@SuppressWarnings("unused")
public class MySQLParserParser extends Parser {
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

    public String[] getTokenNames() { return MySQLParserParser.tokenNames; }
    public String getGrammarFileName() { return "MySQLParser.g"; }


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
        public Object getTree() { return tree; }
    };

    // $ANTLR start "beg"
    // MySQLParser.g:109:1: beg : start_rule ;
    public final MySQLParserParser.beg_return beg() throws RecognitionException {
        MySQLParserParser.beg_return retval = new MySQLParserParser.beg_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.start_rule_return start_rule1 = null;



        try {
            // MySQLParser.g:109:5: ( start_rule )
            // MySQLParser.g:110:1: start_rule
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_start_rule_in_beg242);
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
        return retval;
    }
    // $ANTLR end "beg"

    public static class start_rule_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "start_rule"
    // MySQLParser.g:113:1: start_rule : ( select_command | update_command | insert_command | delete_command );
    public final MySQLParserParser.start_rule_return start_rule() throws RecognitionException {
        MySQLParserParser.start_rule_return retval = new MySQLParserParser.start_rule_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.select_command_return select_command2 = null;

        MySQLParserParser.update_command_return update_command3 = null;

        MySQLParserParser.insert_command_return insert_command4 = null;

        MySQLParserParser.delete_command_return delete_command5 = null;



        try {
            // MySQLParser.g:114:2: ( select_command | update_command | insert_command | delete_command )
            int alt1=4;
            switch ( input.LA(1) ) {
            case 82:
                {
                alt1=1;
                }
                break;
            case 105:
                {
                alt1=2;
                }
                break;
            case 77:
                {
                alt1=3;
                }
                break;
            case 104:
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
                    // MySQLParser.g:114:3: select_command
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_select_command_in_start_rule254);
                    select_command2=select_command();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, select_command2.getTree());

                    }
                    break;
                case 2 :
                    // MySQLParser.g:115:3: update_command
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_update_command_in_start_rule258);
                    update_command3=update_command();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, update_command3.getTree());

                    }
                    break;
                case 3 :
                    // MySQLParser.g:116:3: insert_command
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_insert_command_in_start_rule262);
                    insert_command4=insert_command();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, insert_command4.getTree());

                    }
                    break;
                case 4 :
                    // MySQLParser.g:117:3: delete_command
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_delete_command_in_start_rule266);
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
        return retval;
    }
    // $ANTLR end "start_rule"

    public static class setclause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "setclause"
    // MySQLParser.g:121:1: setclause : 'SET' updateColumnSpecs -> ^( SET updateColumnSpecs ) ;
    public final MySQLParserParser.setclause_return setclause() throws RecognitionException {
        MySQLParserParser.setclause_return retval = new MySQLParserParser.setclause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal6=null;
        MySQLParserParser.updateColumnSpecs_return updateColumnSpecs7 = null;


        CommonTree string_literal6_tree=null;
        RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
        RewriteRuleSubtreeStream stream_updateColumnSpecs=new RewriteRuleSubtreeStream(adaptor,"rule updateColumnSpecs");
        try {
            // MySQLParser.g:122:2: ( 'SET' updateColumnSpecs -> ^( SET updateColumnSpecs ) )
            // MySQLParser.g:122:3: 'SET' updateColumnSpecs
            {
            string_literal6=(Token)match(input,76,FOLLOW_76_in_setclause278); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_76.add(string_literal6);

            pushFollow(FOLLOW_updateColumnSpecs_in_setclause280);
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
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 122:26: -> ^( SET updateColumnSpecs )
            {
                // MySQLParser.g:122:28: ^( SET updateColumnSpecs )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SET, "SET"), root_1);

                adaptor.addChild(root_1, stream_updateColumnSpecs.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "setclause"

    public static class updateColumnSpecs_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "updateColumnSpecs"
    // MySQLParser.g:125:1: updateColumnSpecs : updateColumnSpec ( COMMA updateColumnSpec )* -> ( ^( SET_ELE updateColumnSpec ) )+ ;
   public final MySQLParserParser.updateColumnSpecs_return updateColumnSpecs() throws RecognitionException {
        MySQLParserParser.updateColumnSpecs_return retval = new MySQLParserParser.updateColumnSpecs_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA9=null;
        MySQLParserParser.updateColumnSpec_return updateColumnSpec8 = null;

        MySQLParserParser.updateColumnSpec_return updateColumnSpec10 = null;


        CommonTree COMMA9_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_updateColumnSpec=new RewriteRuleSubtreeStream(adaptor,"rule updateColumnSpec");
        try {
            // MySQLParser.g:126:2: ( updateColumnSpec ( COMMA updateColumnSpec )* -> ( ^( SET_ELE updateColumnSpec ) )+ )
            // MySQLParser.g:126:3: updateColumnSpec ( COMMA updateColumnSpec )*
            {
            pushFollow(FOLLOW_updateColumnSpec_in_updateColumnSpecs297);
            updateColumnSpec8=updateColumnSpec();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_updateColumnSpec.add(updateColumnSpec8.getTree());
            // MySQLParser.g:126:20: ( COMMA updateColumnSpec )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==COMMA) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // MySQLParser.g:126:21: COMMA updateColumnSpec
            	    {
            	    COMMA9=(Token)match(input,COMMA,FOLLOW_COMMA_in_updateColumnSpecs300); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA9);

            	    pushFollow(FOLLOW_updateColumnSpec_in_updateColumnSpecs302);
            	    updateColumnSpec10=updateColumnSpec();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_updateColumnSpec.add(updateColumnSpec10.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);



            // AST REWRITE
            // elements: updateColumnSpec
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 126:45: -> ( ^( SET_ELE updateColumnSpec ) )+
            {
                if ( !(stream_updateColumnSpec.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_updateColumnSpec.hasNext() ) {
                    // MySQLParser.g:126:47: ^( SET_ELE updateColumnSpec )
                    {
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SET_ELE, "SET_ELE"), root_1);

                    adaptor.addChild(root_1, stream_updateColumnSpec.nextTree());

                    adaptor.addChild(root_0, root_1);
                    }

                }
                stream_updateColumnSpec.reset();

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "updateColumnSpecs"

    public static class updateColumnSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "updateColumnSpec"
    // MySQLParser.g:128:1: updateColumnSpec : columnNameInUpdate EQ expr ;
    public final MySQLParserParser.updateColumnSpec_return updateColumnSpec() throws RecognitionException {
        MySQLParserParser.updateColumnSpec_return retval = new MySQLParserParser.updateColumnSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EQ12=null;
        MySQLParserParser.columnNameInUpdate_return columnNameInUpdate11 = null;

        MySQLParserParser.expr_return expr13 = null;


        CommonTree EQ12_tree=null;

        try {
            // MySQLParser.g:129:2: ( columnNameInUpdate EQ expr )
            // MySQLParser.g:129:3: columnNameInUpdate EQ expr
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_columnNameInUpdate_in_updateColumnSpec320);
            columnNameInUpdate11=columnNameInUpdate();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, columnNameInUpdate11.getTree());
            EQ12=(Token)match(input,EQ,FOLLOW_EQ_in_updateColumnSpec322); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EQ12_tree = (CommonTree)adaptor.create(EQ12);
            root_0 = (CommonTree)adaptor.becomeRoot(EQ12_tree, root_0);
            }
            pushFollow(FOLLOW_expr_in_updateColumnSpec325);
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
        return retval;
    }
    // $ANTLR end "updateColumnSpec"

    public static class insert_command_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "insert_command"
    // MySQLParser.g:131:1: insert_command : 'INSERT' 'INTO' selected_table ( LPAREN column_specs RPAREN )? ( 'VALUES' LPAREN values RPAREN ) -> ^( INSERT selected_table column_specs values ) ;
    public final MySQLParserParser.insert_command_return insert_command() throws RecognitionException {
        MySQLParserParser.insert_command_return retval = new MySQLParserParser.insert_command_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal14=null;
        Token string_literal15=null;
        Token LPAREN17=null;
        Token RPAREN19=null;
        Token string_literal20=null;
        Token LPAREN21=null;
        Token RPAREN23=null;
        MySQLParserParser.selected_table_return selected_table16 = null;

        MySQLParserParser.column_specs_return column_specs18 = null;

        MySQLParserParser.values_return values22 = null;


        CommonTree string_literal14_tree=null;
        CommonTree string_literal15_tree=null;
        CommonTree LPAREN17_tree=null;
        CommonTree RPAREN19_tree=null;
        CommonTree string_literal20_tree=null;
        CommonTree LPAREN21_tree=null;
        CommonTree RPAREN23_tree=null;
        RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_selected_table=new RewriteRuleSubtreeStream(adaptor,"rule selected_table");
        RewriteRuleSubtreeStream stream_values=new RewriteRuleSubtreeStream(adaptor,"rule values");
        RewriteRuleSubtreeStream stream_column_specs=new RewriteRuleSubtreeStream(adaptor,"rule column_specs");
        try {
            // MySQLParser.g:132:2: ( 'INSERT' 'INTO' selected_table ( LPAREN column_specs RPAREN )? ( 'VALUES' LPAREN values RPAREN ) -> ^( INSERT selected_table column_specs values ) )
            // MySQLParser.g:132:4: 'INSERT' 'INTO' selected_table ( LPAREN column_specs RPAREN )? ( 'VALUES' LPAREN values RPAREN )
            {
            string_literal14=(Token)match(input,77,FOLLOW_77_in_insert_command335); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_77.add(string_literal14);

            string_literal15=(Token)match(input,78,FOLLOW_78_in_insert_command337); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_78.add(string_literal15);

            pushFollow(FOLLOW_selected_table_in_insert_command339);
            selected_table16=selected_table();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_selected_table.add(selected_table16.getTree());
            // MySQLParser.g:133:3: ( LPAREN column_specs RPAREN )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==LPAREN) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // MySQLParser.g:133:5: LPAREN column_specs RPAREN
                    {
                    LPAREN17=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_insert_command345); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN17);

                    pushFollow(FOLLOW_column_specs_in_insert_command347);
                    column_specs18=column_specs();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_column_specs.add(column_specs18.getTree());
                    RPAREN19=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_insert_command350); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN19);


                    }
                    break;

            }

            // MySQLParser.g:134:3: ( 'VALUES' LPAREN values RPAREN )
            // MySQLParser.g:134:4: 'VALUES' LPAREN values RPAREN
            {
            string_literal20=(Token)match(input,79,FOLLOW_79_in_insert_command358); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_79.add(string_literal20);

            LPAREN21=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_insert_command360); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN21);

            pushFollow(FOLLOW_values_in_insert_command362);
            values22=values();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_values.add(values22.getTree());
            RPAREN23=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_insert_command364); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN23);


            }



            // AST REWRITE
            // elements: column_specs, values, selected_table
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 135:4: -> ^( INSERT selected_table column_specs values )
            {
                // MySQLParser.g:135:6: ^( INSERT selected_table column_specs values )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(INSERT, "INSERT"), root_1);

                adaptor.addChild(root_1, stream_selected_table.nextTree());
                adaptor.addChild(root_1, stream_column_specs.nextTree());
                adaptor.addChild(root_1, stream_values.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "insert_command"

    public static class orderByClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "orderByClause"
    // MySQLParser.g:139:1: orderByClause : 'ORDER' 'BY' columnNamesAfterWhere -> ^( ORDERBY columnNamesAfterWhere ) ;
    public final MySQLParserParser.orderByClause_return orderByClause() throws RecognitionException {
        MySQLParserParser.orderByClause_return retval = new MySQLParserParser.orderByClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal24=null;
        Token string_literal25=null;
        MySQLParserParser.columnNamesAfterWhere_return columnNamesAfterWhere26 = null;


        CommonTree string_literal24_tree=null;
        CommonTree string_literal25_tree=null;
        RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
        RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
        RewriteRuleSubtreeStream stream_columnNamesAfterWhere=new RewriteRuleSubtreeStream(adaptor,"rule columnNamesAfterWhere");
        try {
            // MySQLParser.g:140:2: ( 'ORDER' 'BY' columnNamesAfterWhere -> ^( ORDERBY columnNamesAfterWhere ) )
            // MySQLParser.g:140:3: 'ORDER' 'BY' columnNamesAfterWhere
            {
            string_literal24=(Token)match(input,80,FOLLOW_80_in_orderByClause393); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_80.add(string_literal24);

            string_literal25=(Token)match(input,81,FOLLOW_81_in_orderByClause395); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_81.add(string_literal25);

            pushFollow(FOLLOW_columnNamesAfterWhere_in_orderByClause397);
            columnNamesAfterWhere26=columnNamesAfterWhere();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_columnNamesAfterWhere.add(columnNamesAfterWhere26.getTree());


            // AST REWRITE
            // elements: columnNamesAfterWhere
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 140:37: -> ^( ORDERBY columnNamesAfterWhere )
            {
                // MySQLParser.g:140:39: ^( ORDERBY columnNamesAfterWhere )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ORDERBY, "ORDERBY"), root_1);

                adaptor.addChild(root_1, stream_columnNamesAfterWhere.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "orderByClause"

    public static class columnNamesAfterWhere_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "columnNamesAfterWhere"
    // MySQLParser.g:144:1: columnNamesAfterWhere : columnNameAfterWhere ( COMMA columnNameAfterWhere )* ;
    public final MySQLParserParser.columnNamesAfterWhere_return columnNamesAfterWhere() throws RecognitionException {
        MySQLParserParser.columnNamesAfterWhere_return retval = new MySQLParserParser.columnNamesAfterWhere_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA28=null;
        MySQLParserParser.columnNameAfterWhere_return columnNameAfterWhere27 = null;

        MySQLParserParser.columnNameAfterWhere_return columnNameAfterWhere29 = null;


        CommonTree COMMA28_tree=null;

        try {
            // MySQLParser.g:145:5: ( columnNameAfterWhere ( COMMA columnNameAfterWhere )* )
            // MySQLParser.g:145:6: columnNameAfterWhere ( COMMA columnNameAfterWhere )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere417);
            columnNameAfterWhere27=columnNameAfterWhere();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, columnNameAfterWhere27.getTree());
            // MySQLParser.g:145:27: ( COMMA columnNameAfterWhere )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==COMMA) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // MySQLParser.g:145:28: COMMA columnNameAfterWhere
            	    {
            	    COMMA28=(Token)match(input,COMMA,FOLLOW_COMMA_in_columnNamesAfterWhere420); if (state.failed) return retval;
            	    pushFollow(FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere423);
            	    columnNameAfterWhere29=columnNameAfterWhere();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, columnNameAfterWhere29.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


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
        return retval;
    }
    // $ANTLR end "columnNamesAfterWhere"

    public static class selectClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selectClause"
    // MySQLParser.g:147:1: selectClause : 'SELECT' ( distinct )? ( LPAREN )? select_list ( RPAREN )? -> ^( SELECT ( distinct )? select_list ) ;
    public final MySQLParserParser.selectClause_return selectClause() throws RecognitionException {
        MySQLParserParser.selectClause_return retval = new MySQLParserParser.selectClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal30=null;
        Token LPAREN32=null;
        Token RPAREN34=null;
        MySQLParserParser.distinct_return distinct31 = null;

        MySQLParserParser.select_list_return select_list33 = null;


        CommonTree string_literal30_tree=null;
        CommonTree LPAREN32_tree=null;
        CommonTree RPAREN34_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_select_list=new RewriteRuleSubtreeStream(adaptor,"rule select_list");
        RewriteRuleSubtreeStream stream_distinct=new RewriteRuleSubtreeStream(adaptor,"rule distinct");
        try {
            // MySQLParser.g:148:5: ( 'SELECT' ( distinct )? ( LPAREN )? select_list ( RPAREN )? -> ^( SELECT ( distinct )? select_list ) )
            // MySQLParser.g:148:6: 'SELECT' ( distinct )? ( LPAREN )? select_list ( RPAREN )?
            {
            string_literal30=(Token)match(input,82,FOLLOW_82_in_selectClause441); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_82.add(string_literal30);

            // MySQLParser.g:148:15: ( distinct )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==113) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // MySQLParser.g:148:15: distinct
                    {
                    pushFollow(FOLLOW_distinct_in_selectClause443);
                    distinct31=distinct();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_distinct.add(distinct31.getTree());

                    }
                    break;

            }

            // MySQLParser.g:148:25: ( LPAREN )?
            int alt6=2;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // MySQLParser.g:148:25: LPAREN
                    {
                    LPAREN32=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_selectClause446); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN32);


                    }
                    break;

            }

            pushFollow(FOLLOW_select_list_in_selectClause449);
            select_list33=select_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_select_list.add(select_list33.getTree());
            // MySQLParser.g:148:45: ( RPAREN )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RPAREN) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // MySQLParser.g:148:45: RPAREN
                    {
                    RPAREN34=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_selectClause451); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN34);


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
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 148:53: -> ^( SELECT ( distinct )? select_list )
            {
                // MySQLParser.g:148:55: ^( SELECT ( distinct )? select_list )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SELECT, "SELECT"), root_1);

                // MySQLParser.g:148:64: ( distinct )?
                if ( stream_distinct.hasNext() ) {
                    adaptor.addChild(root_1, stream_distinct.nextTree());

                }
                stream_distinct.reset();
                adaptor.addChild(root_1, stream_select_list.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "selectClause"

    public static class whereClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "whereClause"
    // MySQLParser.g:150:1: whereClause : 'WHERE' sqlCondition -> ^( WHERE sqlCondition ) ;
    public final MySQLParserParser.whereClause_return whereClause() throws RecognitionException {
        MySQLParserParser.whereClause_return retval = new MySQLParserParser.whereClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal35=null;
        MySQLParserParser.sqlCondition_return sqlCondition36 = null;


        CommonTree string_literal35_tree=null;
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleSubtreeStream stream_sqlCondition=new RewriteRuleSubtreeStream(adaptor,"rule sqlCondition");
        try {
            // MySQLParser.g:151:2: ( 'WHERE' sqlCondition -> ^( WHERE sqlCondition ) )
            // MySQLParser.g:151:3: 'WHERE' sqlCondition
            {
            string_literal35=(Token)match(input,83,FOLLOW_83_in_whereClause476); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_83.add(string_literal35);

            pushFollow(FOLLOW_sqlCondition_in_whereClause478);
            sqlCondition36=sqlCondition();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_sqlCondition.add(sqlCondition36.getTree());


            // AST REWRITE
            // elements: sqlCondition
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 151:23: -> ^( WHERE sqlCondition )
            {
                // MySQLParser.g:151:25: ^( WHERE sqlCondition )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(WHERE, "WHERE"), root_1);

                adaptor.addChild(root_1, stream_sqlCondition.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "whereClause"

    public static class sqlCondition_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sqlCondition"
    // MySQLParser.g:154:1: sqlCondition : ( 'NOT' condition_or -> ^( CONDITION_OR_NOT condition_or ) | condition_or -> ^( CONDITION_OR condition_or ) );
    public final MySQLParserParser.sqlCondition_return sqlCondition() throws RecognitionException {
        MySQLParserParser.sqlCondition_return retval = new MySQLParserParser.sqlCondition_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal37=null;
        MySQLParserParser.condition_or_return condition_or38 = null;

        MySQLParserParser.condition_or_return condition_or39 = null;


        CommonTree string_literal37_tree=null;
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_condition_or=new RewriteRuleSubtreeStream(adaptor,"rule condition_or");
        try {
            // MySQLParser.g:155:2: ( 'NOT' condition_or -> ^( CONDITION_OR_NOT condition_or ) | condition_or -> ^( CONDITION_OR condition_or ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==84) ) {
                alt8=1;
            }
            else if ( (LA8_0==LPAREN||(LA8_0>=PLUS && LA8_0<=MINUS)||LA8_0==ASTERISK||LA8_0==ID||(LA8_0>=N && LA8_0<=NUMBER)||(LA8_0>=QUOTED_STRING && LA8_0<=DOUBLEQUOTED_STRING)||LA8_0==91||LA8_0==94||(LA8_0>=96 && LA8_0<=98)) ) {
                alt8=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // MySQLParser.g:155:3: 'NOT' condition_or
                    {
                    string_literal37=(Token)match(input,84,FOLLOW_84_in_sqlCondition494); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_84.add(string_literal37);

                    pushFollow(FOLLOW_condition_or_in_sqlCondition496);
                    condition_or38=condition_or();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_condition_or.add(condition_or38.getTree());


                    // AST REWRITE
                    // elements: condition_or
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 155:21: -> ^( CONDITION_OR_NOT condition_or )
                    {
                        // MySQLParser.g:155:23: ^( CONDITION_OR_NOT condition_or )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CONDITION_OR_NOT, "CONDITION_OR_NOT"), root_1);

                        adaptor.addChild(root_1, stream_condition_or.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // MySQLParser.g:156:3: condition_or
                    {
                    pushFollow(FOLLOW_condition_or_in_sqlCondition507);
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
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 156:16: -> ^( CONDITION_OR condition_or )
                    {
                        // MySQLParser.g:156:18: ^( CONDITION_OR condition_or )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CONDITION_OR, "CONDITION_OR"), root_1);

                        adaptor.addChild(root_1, stream_condition_or.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "sqlCondition"

    public static class condition_or_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "condition_or"
    // MySQLParser.g:160:1: condition_or : condition_and ( 'OR' condition_and )* ;
    public final MySQLParserParser.condition_or_return condition_or() throws RecognitionException {
        MySQLParserParser.condition_or_return retval = new MySQLParserParser.condition_or_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal41=null;
        MySQLParserParser.condition_and_return condition_and40 = null;

        MySQLParserParser.condition_and_return condition_and42 = null;


        CommonTree string_literal41_tree=null;

        try {
            // MySQLParser.g:161:2: ( condition_and ( 'OR' condition_and )* )
            // MySQLParser.g:161:3: condition_and ( 'OR' condition_and )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_condition_and_in_condition_or525);
            condition_and40=condition_and();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_and40.getTree());
            // MySQLParser.g:161:17: ( 'OR' condition_and )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==85) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // MySQLParser.g:161:19: 'OR' condition_and
            	    {
            	    string_literal41=(Token)match(input,85,FOLLOW_85_in_condition_or529); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal41_tree = (CommonTree)adaptor.create(string_literal41);
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal41_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_condition_and_in_condition_or532);
            	    condition_and42=condition_and();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_and42.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


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
        return retval;
    }
    // $ANTLR end "condition_or"

    public static class condition_and_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "condition_and"
    // MySQLParser.g:164:1: condition_and : condition_PAREN ( 'AND' condition_PAREN )* ;
    public final MySQLParserParser.condition_and_return condition_and() throws RecognitionException {
        MySQLParserParser.condition_and_return retval = new MySQLParserParser.condition_and_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal44=null;
        MySQLParserParser.condition_PAREN_return condition_PAREN43 = null;

        MySQLParserParser.condition_PAREN_return condition_PAREN45 = null;


        CommonTree string_literal44_tree=null;

        try {
            // MySQLParser.g:165:2: ( condition_PAREN ( 'AND' condition_PAREN )* )
            // MySQLParser.g:165:3: condition_PAREN ( 'AND' condition_PAREN )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_condition_PAREN_in_condition_and545);
            condition_PAREN43=condition_PAREN();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_PAREN43.getTree());
            // MySQLParser.g:165:19: ( 'AND' condition_PAREN )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==86) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // MySQLParser.g:165:21: 'AND' condition_PAREN
            	    {
            	    string_literal44=(Token)match(input,86,FOLLOW_86_in_condition_and549); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal44_tree = (CommonTree)adaptor.create(string_literal44);
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal44_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_condition_PAREN_in_condition_and552);
            	    condition_PAREN45=condition_PAREN();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_PAREN45.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


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
        return retval;
    }
    // $ANTLR end "condition_and"

    public static class condition_PAREN_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "condition_PAREN"
    // MySQLParser.g:167:1: condition_PAREN : ( ( LPAREN condition_or RPAREN )=> LPAREN condition_or RPAREN -> ^( PRIORITY condition_or ) | condition_expr );
    public final MySQLParserParser.condition_PAREN_return condition_PAREN() throws RecognitionException {
        MySQLParserParser.condition_PAREN_return retval = new MySQLParserParser.condition_PAREN_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LPAREN46=null;
        Token RPAREN48=null;
        MySQLParserParser.condition_or_return condition_or47 = null;

        MySQLParserParser.condition_expr_return condition_expr49 = null;


        CommonTree LPAREN46_tree=null;
        CommonTree RPAREN48_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_condition_or=new RewriteRuleSubtreeStream(adaptor,"rule condition_or");
        try {
            // MySQLParser.g:168:2: ( ( LPAREN condition_or RPAREN )=> LPAREN condition_or RPAREN -> ^( PRIORITY condition_or ) | condition_expr )
            int alt11=2;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // MySQLParser.g:168:3: ( LPAREN condition_or RPAREN )=> LPAREN condition_or RPAREN
                    {
                    LPAREN46=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_condition_PAREN572); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN46);

                    pushFollow(FOLLOW_condition_or_in_condition_PAREN574);
                    condition_or47=condition_or();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_condition_or.add(condition_or47.getTree());
                    RPAREN48=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_condition_PAREN576); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN48);



                    // AST REWRITE
                    // elements: condition_or
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 168:59: -> ^( PRIORITY condition_or )
                    {
                        // MySQLParser.g:168:61: ^( PRIORITY condition_or )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PRIORITY, "PRIORITY"), root_1);

                        adaptor.addChild(root_1, stream_condition_or.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // MySQLParser.g:169:3: condition_expr
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_condition_expr_in_condition_PAREN586);
                    condition_expr49=condition_expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_expr49.getTree());

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
        return retval;
    }
    // $ANTLR end "condition_PAREN"

    public static class condition_expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "condition_expr"
    // MySQLParser.g:171:1: condition_expr : condition_left ( comparisonCondition | inCondition | isCondition | likeCondition | betweenCondition ) ;
    public final MySQLParserParser.condition_expr_return condition_expr() throws RecognitionException {
        MySQLParserParser.condition_expr_return retval = new MySQLParserParser.condition_expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.condition_left_return condition_left50 = null;

        MySQLParserParser.comparisonCondition_return comparisonCondition51 = null;

        MySQLParserParser.inCondition_return inCondition52 = null;

        MySQLParserParser.isCondition_return isCondition53 = null;

        MySQLParserParser.likeCondition_return likeCondition54 = null;

        MySQLParserParser.betweenCondition_return betweenCondition55 = null;



        try {
            // MySQLParser.g:172:2: ( condition_left ( comparisonCondition | inCondition | isCondition | likeCondition | betweenCondition ) )
            // MySQLParser.g:172:4: condition_left ( comparisonCondition | inCondition | isCondition | likeCondition | betweenCondition )
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_condition_left_in_condition_expr596);
            condition_left50=condition_left();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(condition_left50.getTree(), root_0);
            // MySQLParser.g:173:2: ( comparisonCondition | inCondition | isCondition | likeCondition | betweenCondition )
            int alt12=5;
            switch ( input.LA(1) ) {
            case EQ:
            case LTH:
            case GTH:
            case NOT_EQ:
            case LEQ:
            case GEQ:
                {
                alt12=1;
                }
                break;
            case 84:
                {
                switch ( input.LA(2) ) {
                case 93:
                    {
                    alt12=4;
                    }
                    break;
                case 87:
                    {
                    alt12=5;
                    }
                    break;
                case 92:
                    {
                    alt12=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 2, input);

                    throw nvae;
                }

                }
                break;
            case 92:
                {
                alt12=2;
                }
                break;
            case 88:
                {
                alt12=3;
                }
                break;
            case 93:
                {
                alt12=4;
                }
                break;
            case 87:
                {
                alt12=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // MySQLParser.g:173:3: comparisonCondition
                    {
                    pushFollow(FOLLOW_comparisonCondition_in_condition_expr601);
                    comparisonCondition51=comparisonCondition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(comparisonCondition51.getTree(), root_0);

                    }
                    break;
                case 2 :
                    // MySQLParser.g:174:4: inCondition
                    {
                    pushFollow(FOLLOW_inCondition_in_condition_expr607);
                    inCondition52=inCondition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(inCondition52.getTree(), root_0);

                    }
                    break;
                case 3 :
                    // MySQLParser.g:175:4: isCondition
                    {
                    pushFollow(FOLLOW_isCondition_in_condition_expr613);
                    isCondition53=isCondition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(isCondition53.getTree(), root_0);

                    }
                    break;
                case 4 :
                    // MySQLParser.g:176:4: likeCondition
                    {
                    pushFollow(FOLLOW_likeCondition_in_condition_expr619);
                    likeCondition54=likeCondition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(likeCondition54.getTree(), root_0);

                    }
                    break;
                case 5 :
                    // MySQLParser.g:177:4: betweenCondition
                    {
                    pushFollow(FOLLOW_betweenCondition_in_condition_expr625);
                    betweenCondition55=betweenCondition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(betweenCondition55.getTree(), root_0);

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
        return retval;
    }
    // $ANTLR end "condition_expr"

    public static class condition_left_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "condition_left"
    // MySQLParser.g:180:1: condition_left : expr -> ^( CONDITION_LEFT expr ) ;
    public final MySQLParserParser.condition_left_return condition_left() throws RecognitionException {
        MySQLParserParser.condition_left_return retval = new MySQLParserParser.condition_left_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.expr_return expr56 = null;


        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // MySQLParser.g:181:2: ( expr -> ^( CONDITION_LEFT expr ) )
            // MySQLParser.g:181:3: expr
            {
            pushFollow(FOLLOW_expr_in_condition_left639);
            expr56=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr56.getTree());


            // AST REWRITE
            // elements: expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 181:7: -> ^( CONDITION_LEFT expr )
            {
                // MySQLParser.g:181:9: ^( CONDITION_LEFT expr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CONDITION_LEFT, "CONDITION_LEFT"), root_1);

                adaptor.addChild(root_1, stream_expr.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "condition_left"

    public static class betweenCondition_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "betweenCondition"
    // MySQLParser.g:183:1: betweenCondition : ( 'NOT' 'BETWEEN' between_and -> ^( NOT_BETWEEN between_and ) | 'BETWEEN' between_and -> ^( BETWEEN between_and ) );
    public final MySQLParserParser.betweenCondition_return betweenCondition() throws RecognitionException {
        MySQLParserParser.betweenCondition_return retval = new MySQLParserParser.betweenCondition_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal57=null;
        Token string_literal58=null;
        Token string_literal60=null;
        MySQLParserParser.between_and_return between_and59 = null;

        MySQLParserParser.between_and_return between_and61 = null;


        CommonTree string_literal57_tree=null;
        CommonTree string_literal58_tree=null;
        CommonTree string_literal60_tree=null;
        RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_between_and=new RewriteRuleSubtreeStream(adaptor,"rule between_and");
        try {
            // MySQLParser.g:184:2: ( 'NOT' 'BETWEEN' between_and -> ^( NOT_BETWEEN between_and ) | 'BETWEEN' between_and -> ^( BETWEEN between_and ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==84) ) {
                alt13=1;
            }
            else if ( (LA13_0==87) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // MySQLParser.g:184:4: 'NOT' 'BETWEEN' between_and
                    {
                    string_literal57=(Token)match(input,84,FOLLOW_84_in_betweenCondition655); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_84.add(string_literal57);

                    string_literal58=(Token)match(input,87,FOLLOW_87_in_betweenCondition657); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_87.add(string_literal58);

                    pushFollow(FOLLOW_between_and_in_betweenCondition659);
                    between_and59=between_and();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_between_and.add(between_and59.getTree());


                    // AST REWRITE
                    // elements: between_and
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 184:31: -> ^( NOT_BETWEEN between_and )
                    {
                        // MySQLParser.g:184:33: ^( NOT_BETWEEN between_and )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NOT_BETWEEN, "NOT_BETWEEN"), root_1);

                        adaptor.addChild(root_1, stream_between_and.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // MySQLParser.g:185:4: 'BETWEEN' between_and
                    {
                    string_literal60=(Token)match(input,87,FOLLOW_87_in_betweenCondition670); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_87.add(string_literal60);

                    pushFollow(FOLLOW_between_and_in_betweenCondition672);
                    between_and61=between_and();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_between_and.add(between_and61.getTree());


                    // AST REWRITE
                    // elements: between_and
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 185:25: -> ^( BETWEEN between_and )
                    {
                        // MySQLParser.g:185:27: ^( BETWEEN between_and )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BETWEEN, "BETWEEN"), root_1);

                        adaptor.addChild(root_1, stream_between_and.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "betweenCondition"

    public static class between_and_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "between_and"
    // MySQLParser.g:188:1: between_and : a= between_and_expression 'AND' b= between_and_expression -> ^( $a $b) ;
    public final MySQLParserParser.between_and_return between_and() throws RecognitionException {
        MySQLParserParser.between_and_return retval = new MySQLParserParser.between_and_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal62=null;
        MySQLParserParser.between_and_expression_return a = null;

        MySQLParserParser.between_and_expression_return b = null;


        CommonTree string_literal62_tree=null;
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleSubtreeStream stream_between_and_expression=new RewriteRuleSubtreeStream(adaptor,"rule between_and_expression");
        try {
            // MySQLParser.g:189:2: (a= between_and_expression 'AND' b= between_and_expression -> ^( $a $b) )
            // MySQLParser.g:189:3: a= between_and_expression 'AND' b= between_and_expression
            {
            pushFollow(FOLLOW_between_and_expression_in_between_and691);
            a=between_and_expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_between_and_expression.add(a.getTree());
            string_literal62=(Token)match(input,86,FOLLOW_86_in_between_and693); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_86.add(string_literal62);

            pushFollow(FOLLOW_between_and_expression_in_between_and697);
            b=between_and_expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_between_and_expression.add(b.getTree());


            // AST REWRITE
            // elements: b, a
            // token labels: 
            // rule labels: retval, b, a
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.tree:null);
            RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 189:58: -> ^( $a $b)
            {
                // MySQLParser.g:189:60: ^( $a $b)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_a.nextNode(), root_1);

                adaptor.addChild(root_1, stream_b.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "between_and"

    public static class between_and_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "between_and_expression"
    // MySQLParser.g:192:1: between_and_expression : ( quoted_string | expr_add );
    public final MySQLParserParser.between_and_expression_return between_and_expression() throws RecognitionException {
        MySQLParserParser.between_and_expression_return retval = new MySQLParserParser.between_and_expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.quoted_string_return quoted_string63 = null;

        MySQLParserParser.expr_add_return expr_add64 = null;



        try {
            // MySQLParser.g:193:2: ( quoted_string | expr_add )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=QUOTED_STRING && LA14_0<=DOUBLEQUOTED_STRING)) ) {
                alt14=1;
            }
            else if ( (LA14_0==LPAREN||(LA14_0>=PLUS && LA14_0<=MINUS)||LA14_0==ASTERISK||LA14_0==ID||(LA14_0>=N && LA14_0<=NUMBER)||LA14_0==91||LA14_0==94||(LA14_0>=96 && LA14_0<=98)) ) {
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
                    // MySQLParser.g:193:4: quoted_string
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_quoted_string_in_between_and_expression717);
                    quoted_string63=quoted_string();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, quoted_string63.getTree());

                    }
                    break;
                case 2 :
                    // MySQLParser.g:194:4: expr_add
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_expr_add_in_between_and_expression722);
                    expr_add64=expr_add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_add64.getTree());

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
        return retval;
    }
    // $ANTLR end "between_and_expression"

    public static class isCondition_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "isCondition"
    // MySQLParser.g:197:1: isCondition : ( 'IS' 'NOT' condition_is_valobject -> ^( ISNOT condition_is_valobject ) | 'IS' condition_is_valobject -> ^( IS condition_is_valobject ) );
    public final MySQLParserParser.isCondition_return isCondition() throws RecognitionException {
        MySQLParserParser.isCondition_return retval = new MySQLParserParser.isCondition_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal65=null;
        Token string_literal66=null;
        Token string_literal68=null;
        MySQLParserParser.condition_is_valobject_return condition_is_valobject67 = null;

        MySQLParserParser.condition_is_valobject_return condition_is_valobject69 = null;


        CommonTree string_literal65_tree=null;
        CommonTree string_literal66_tree=null;
        CommonTree string_literal68_tree=null;
        RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_condition_is_valobject=new RewriteRuleSubtreeStream(adaptor,"rule condition_is_valobject");
        try {
            // MySQLParser.g:198:2: ( 'IS' 'NOT' condition_is_valobject -> ^( ISNOT condition_is_valobject ) | 'IS' condition_is_valobject -> ^( IS condition_is_valobject ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==88) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==84) ) {
                    alt15=1;
                }
                else if ( ((LA15_1>=89 && LA15_1<=91)) ) {
                    alt15=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // MySQLParser.g:198:4: 'IS' 'NOT' condition_is_valobject
                    {
                    string_literal65=(Token)match(input,88,FOLLOW_88_in_isCondition734); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_88.add(string_literal65);

                    string_literal66=(Token)match(input,84,FOLLOW_84_in_isCondition736); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_84.add(string_literal66);

                    pushFollow(FOLLOW_condition_is_valobject_in_isCondition738);
                    condition_is_valobject67=condition_is_valobject();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_condition_is_valobject.add(condition_is_valobject67.getTree());


                    // AST REWRITE
                    // elements: condition_is_valobject
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 198:37: -> ^( ISNOT condition_is_valobject )
                    {
                        // MySQLParser.g:198:39: ^( ISNOT condition_is_valobject )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ISNOT, "ISNOT"), root_1);

                        adaptor.addChild(root_1, stream_condition_is_valobject.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // MySQLParser.g:199:3: 'IS' condition_is_valobject
                    {
                    string_literal68=(Token)match(input,88,FOLLOW_88_in_isCondition748); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_88.add(string_literal68);

                    pushFollow(FOLLOW_condition_is_valobject_in_isCondition750);
                    condition_is_valobject69=condition_is_valobject();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_condition_is_valobject.add(condition_is_valobject69.getTree());


                    // AST REWRITE
                    // elements: condition_is_valobject
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 199:30: -> ^( IS condition_is_valobject )
                    {
                        // MySQLParser.g:199:32: ^( IS condition_is_valobject )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(IS, "IS"), root_1);

                        adaptor.addChild(root_1, stream_condition_is_valobject.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "isCondition"

    public static class condition_is_valobject_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "condition_is_valobject"
    // MySQLParser.g:203:1: condition_is_valobject : ( 'NAN' -> NAN | 'INFINITE' -> INFINITE | 'NULL' -> NULL );
    public final MySQLParserParser.condition_is_valobject_return condition_is_valobject() throws RecognitionException {
        MySQLParserParser.condition_is_valobject_return retval = new MySQLParserParser.condition_is_valobject_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal70=null;
        Token string_literal71=null;
        Token string_literal72=null;

        CommonTree string_literal70_tree=null;
        CommonTree string_literal71_tree=null;
        CommonTree string_literal72_tree=null;
        RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
        RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
        RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");

        try {
            // MySQLParser.g:204:2: ( 'NAN' -> NAN | 'INFINITE' -> INFINITE | 'NULL' -> NULL )
            int alt16=3;
            switch ( input.LA(1) ) {
            case 89:
                {
                alt16=1;
                }
                break;
            case 90:
                {
                alt16=2;
                }
                break;
            case 91:
                {
                alt16=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // MySQLParser.g:204:4: 'NAN'
                    {
                    string_literal70=(Token)match(input,89,FOLLOW_89_in_condition_is_valobject768); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_89.add(string_literal70);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 204:10: -> NAN
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(NAN, "NAN"));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // MySQLParser.g:205:4: 'INFINITE'
                    {
                    string_literal71=(Token)match(input,90,FOLLOW_90_in_condition_is_valobject776); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_90.add(string_literal71);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 205:15: -> INFINITE
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(INFINITE, "INFINITE"));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // MySQLParser.g:206:4: 'NULL'
                    {
                    string_literal72=(Token)match(input,91,FOLLOW_91_in_condition_is_valobject784); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_91.add(string_literal72);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 206:11: -> NULL
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(NULL, "NULL"));

                    }

                    retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "condition_is_valobject"

    public static class inCondition_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inCondition"
    // MySQLParser.g:209:1: inCondition : (not= 'NOT' )? 'IN' ( subquery | ( LPAREN inCondition_expr_adds RPAREN ) ) -> ^( IN ( $not)? ( subquery )? ( inCondition_expr_adds )? ) ;
    public final MySQLParserParser.inCondition_return inCondition() throws RecognitionException {
        MySQLParserParser.inCondition_return retval = new MySQLParserParser.inCondition_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token not=null;
        Token string_literal73=null;
        Token LPAREN75=null;
        Token RPAREN77=null;
        MySQLParserParser.subquery_return subquery74 = null;

        MySQLParserParser.inCondition_expr_adds_return inCondition_expr_adds76 = null;


        CommonTree not_tree=null;
        CommonTree string_literal73_tree=null;
        CommonTree LPAREN75_tree=null;
        CommonTree RPAREN77_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_inCondition_expr_adds=new RewriteRuleSubtreeStream(adaptor,"rule inCondition_expr_adds");
        RewriteRuleSubtreeStream stream_subquery=new RewriteRuleSubtreeStream(adaptor,"rule subquery");
        try {
            // MySQLParser.g:210:2: ( (not= 'NOT' )? 'IN' ( subquery | ( LPAREN inCondition_expr_adds RPAREN ) ) -> ^( IN ( $not)? ( subquery )? ( inCondition_expr_adds )? ) )
            // MySQLParser.g:210:3: (not= 'NOT' )? 'IN' ( subquery | ( LPAREN inCondition_expr_adds RPAREN ) )
            {
            // MySQLParser.g:210:3: (not= 'NOT' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==84) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // MySQLParser.g:210:4: not= 'NOT'
                    {
                    not=(Token)match(input,84,FOLLOW_84_in_inCondition800); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_84.add(not);


                    }
                    break;

            }

            string_literal73=(Token)match(input,92,FOLLOW_92_in_inCondition804); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_92.add(string_literal73);

            // MySQLParser.g:210:21: ( subquery | ( LPAREN inCondition_expr_adds RPAREN ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==LPAREN) ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1==82) ) {
                    alt18=1;
                }
                else if ( (LA18_1==LPAREN||(LA18_1>=PLUS && LA18_1<=MINUS)||LA18_1==ASTERISK||LA18_1==ID||(LA18_1>=N && LA18_1<=NUMBER)||(LA18_1>=QUOTED_STRING && LA18_1<=DOUBLEQUOTED_STRING)||LA18_1==91||LA18_1==94||(LA18_1>=96 && LA18_1<=98)) ) {
                    alt18=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // MySQLParser.g:210:22: subquery
                    {
                    pushFollow(FOLLOW_subquery_in_inCondition807);
                    subquery74=subquery();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_subquery.add(subquery74.getTree());

                    }
                    break;
                case 2 :
                    // MySQLParser.g:211:3: ( LPAREN inCondition_expr_adds RPAREN )
                    {
                    // MySQLParser.g:211:3: ( LPAREN inCondition_expr_adds RPAREN )
                    // MySQLParser.g:211:5: LPAREN inCondition_expr_adds RPAREN
                    {
                    LPAREN75=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_inCondition813); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN75);

                    pushFollow(FOLLOW_inCondition_expr_adds_in_inCondition815);
                    inCondition_expr_adds76=inCondition_expr_adds();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_inCondition_expr_adds.add(inCondition_expr_adds76.getTree());
                    RPAREN77=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_inCondition817); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN77);


                    }


                    }
                    break;

            }



            // AST REWRITE
            // elements: not, inCondition_expr_adds, subquery
            // token labels: not
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_not=new RewriteRuleTokenStream(adaptor,"token not",not);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 211:42: -> ^( IN ( $not)? ( subquery )? ( inCondition_expr_adds )? )
            {
                // MySQLParser.g:211:44: ^( IN ( $not)? ( subquery )? ( inCondition_expr_adds )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(IN, "IN"), root_1);

                // MySQLParser.g:211:49: ( $not)?
                if ( stream_not.hasNext() ) {
                    adaptor.addChild(root_1, stream_not.nextNode());

                }
                stream_not.reset();
                // MySQLParser.g:211:55: ( subquery )?
                if ( stream_subquery.hasNext() ) {
                    adaptor.addChild(root_1, stream_subquery.nextTree());

                }
                stream_subquery.reset();
                // MySQLParser.g:211:65: ( inCondition_expr_adds )?
                if ( stream_inCondition_expr_adds.hasNext() ) {
                    adaptor.addChild(root_1, stream_inCondition_expr_adds.nextTree());

                }
                stream_inCondition_expr_adds.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "inCondition"

    public static class likeCondition_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "likeCondition"
    // MySQLParser.g:215:1: likeCondition : ( 'NOT' 'LIKE' value -> ^( NOT_LIKE value ) | 'LIKE' value -> ^( LIKE value ) );
    public final MySQLParserParser.likeCondition_return likeCondition() throws RecognitionException {
        MySQLParserParser.likeCondition_return retval = new MySQLParserParser.likeCondition_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal78=null;
        Token string_literal79=null;
        Token string_literal81=null;
        MySQLParserParser.value_return value80 = null;

        MySQLParserParser.value_return value82 = null;


        CommonTree string_literal78_tree=null;
        CommonTree string_literal79_tree=null;
        CommonTree string_literal81_tree=null;
        RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");
        try {
            // MySQLParser.g:216:2: ( 'NOT' 'LIKE' value -> ^( NOT_LIKE value ) | 'LIKE' value -> ^( LIKE value ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==84) ) {
                alt19=1;
            }
            else if ( (LA19_0==93) ) {
                alt19=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // MySQLParser.g:216:3: 'NOT' 'LIKE' value
                    {
                    string_literal78=(Token)match(input,84,FOLLOW_84_in_likeCondition844); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_84.add(string_literal78);

                    string_literal79=(Token)match(input,93,FOLLOW_93_in_likeCondition845); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_93.add(string_literal79);

                    pushFollow(FOLLOW_value_in_likeCondition848);
                    value80=value();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_value.add(value80.getTree());


                    // AST REWRITE
                    // elements: value
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 216:21: -> ^( NOT_LIKE value )
                    {
                        // MySQLParser.g:216:23: ^( NOT_LIKE value )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NOT_LIKE, "NOT_LIKE"), root_1);

                        adaptor.addChild(root_1, stream_value.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // MySQLParser.g:217:3: 'LIKE' value
                    {
                    string_literal81=(Token)match(input,93,FOLLOW_93_in_likeCondition858); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_93.add(string_literal81);

                    pushFollow(FOLLOW_value_in_likeCondition860);
                    value82=value();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_value.add(value82.getTree());


                    // AST REWRITE
                    // elements: value
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 217:15: -> ^( LIKE value )
                    {
                        // MySQLParser.g:217:17: ^( LIKE value )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LIKE, "LIKE"), root_1);

                        adaptor.addChild(root_1, stream_value.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "likeCondition"

    public static class inCondition_expr_adds_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inCondition_expr_adds"
    // MySQLParser.g:221:1: inCondition_expr_adds : expr_add ( COMMA expr_add )* -> ^( IN_LISTS ( expr_add )+ ) ;
    public final MySQLParserParser.inCondition_expr_adds_return inCondition_expr_adds() throws RecognitionException {
        MySQLParserParser.inCondition_expr_adds_return retval = new MySQLParserParser.inCondition_expr_adds_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA84=null;
        MySQLParserParser.expr_add_return expr_add83 = null;

        MySQLParserParser.expr_add_return expr_add85 = null;


        CommonTree COMMA84_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_expr_add=new RewriteRuleSubtreeStream(adaptor,"rule expr_add");
        try {
            // MySQLParser.g:221:22: ( expr_add ( COMMA expr_add )* -> ^( IN_LISTS ( expr_add )+ ) )
            // MySQLParser.g:222:2: expr_add ( COMMA expr_add )*
            {
            pushFollow(FOLLOW_expr_add_in_inCondition_expr_adds877);
            expr_add83=expr_add();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr_add.add(expr_add83.getTree());
            // MySQLParser.g:222:10: ( COMMA expr_add )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==COMMA) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // MySQLParser.g:222:11: COMMA expr_add
            	    {
            	    COMMA84=(Token)match(input,COMMA,FOLLOW_COMMA_in_inCondition_expr_adds879); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA84);

            	    pushFollow(FOLLOW_expr_add_in_inCondition_expr_adds881);
            	    expr_add85=expr_add();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expr_add.add(expr_add85.getTree());

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);



            // AST REWRITE
            // elements: expr_add
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 222:27: -> ^( IN_LISTS ( expr_add )+ )
            {
                // MySQLParser.g:222:29: ^( IN_LISTS ( expr_add )+ )
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

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "inCondition_expr_adds"

    public static class identifiers_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "identifiers"
    // MySQLParser.g:225:1: identifiers : columnNameAfterWhere ( COMMA identifier )* ;
    public final MySQLParserParser.identifiers_return identifiers() throws RecognitionException {
        MySQLParserParser.identifiers_return retval = new MySQLParserParser.identifiers_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA87=null;
        MySQLParserParser.columnNameAfterWhere_return columnNameAfterWhere86 = null;

        MySQLParserParser.identifier_return identifier88 = null;


        CommonTree COMMA87_tree=null;

        try {
            // MySQLParser.g:226:2: ( columnNameAfterWhere ( COMMA identifier )* )
            // MySQLParser.g:226:3: columnNameAfterWhere ( COMMA identifier )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_columnNameAfterWhere_in_identifiers901);
            columnNameAfterWhere86=columnNameAfterWhere();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, columnNameAfterWhere86.getTree());
            // MySQLParser.g:226:24: ( COMMA identifier )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==COMMA) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // MySQLParser.g:226:25: COMMA identifier
            	    {
            	    COMMA87=(Token)match(input,COMMA,FOLLOW_COMMA_in_identifiers904); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA87_tree = (CommonTree)adaptor.create(COMMA87);
            	    adaptor.addChild(root_0, COMMA87_tree);
            	    }
            	    pushFollow(FOLLOW_identifier_in_identifiers906);
            	    identifier88=identifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier88.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


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
        return retval;
    }
    // $ANTLR end "identifiers"

    public static class comparisonCondition_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comparisonCondition"
    // MySQLParser.g:229:1: comparisonCondition : relational_op expr -> ^( relational_op expr ) ;
    public final MySQLParserParser.comparisonCondition_return comparisonCondition() throws RecognitionException {
        MySQLParserParser.comparisonCondition_return retval = new MySQLParserParser.comparisonCondition_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.relational_op_return relational_op89 = null;

        MySQLParserParser.expr_return expr90 = null;


        RewriteRuleSubtreeStream stream_relational_op=new RewriteRuleSubtreeStream(adaptor,"rule relational_op");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // MySQLParser.g:230:2: ( relational_op expr -> ^( relational_op expr ) )
            // MySQLParser.g:230:3: relational_op expr
            {
            pushFollow(FOLLOW_relational_op_in_comparisonCondition918);
            relational_op89=relational_op();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_relational_op.add(relational_op89.getTree());
            pushFollow(FOLLOW_expr_in_comparisonCondition920);
            expr90=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr90.getTree());


            // AST REWRITE
            // elements: expr, relational_op
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 230:21: -> ^( relational_op expr )
            {
                // MySQLParser.g:230:23: ^( relational_op expr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_relational_op.nextNode(), root_1);

                adaptor.addChild(root_1, stream_expr.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "comparisonCondition"

    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr"
    // MySQLParser.g:234:1: expr : ( expr_add | subquery ) ;
    public final MySQLParserParser.expr_return expr() throws RecognitionException {
        MySQLParserParser.expr_return retval = new MySQLParserParser.expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.expr_add_return expr_add91 = null;

        MySQLParserParser.subquery_return subquery92 = null;



        try {
            // MySQLParser.g:234:6: ( ( expr_add | subquery ) )
            // MySQLParser.g:234:7: ( expr_add | subquery )
            {
            root_0 = (CommonTree)adaptor.nil();

            // MySQLParser.g:234:7: ( expr_add | subquery )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=PLUS && LA22_0<=MINUS)||LA22_0==ASTERISK||LA22_0==ID||(LA22_0>=N && LA22_0<=NUMBER)||(LA22_0>=QUOTED_STRING && LA22_0<=DOUBLEQUOTED_STRING)||LA22_0==91||LA22_0==94||(LA22_0>=96 && LA22_0<=98)) ) {
                alt22=1;
            }
            else if ( (LA22_0==LPAREN) ) {
                int LA22_2 = input.LA(2);

                if ( (LA22_2==LPAREN||(LA22_2>=PLUS && LA22_2<=MINUS)||LA22_2==ASTERISK||LA22_2==ID||(LA22_2>=N && LA22_2<=NUMBER)||(LA22_2>=QUOTED_STRING && LA22_2<=DOUBLEQUOTED_STRING)||LA22_2==91||LA22_2==94||(LA22_2>=96 && LA22_2<=98)) ) {
                    alt22=1;
                }
                else if ( (LA22_2==82) ) {
                    alt22=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // MySQLParser.g:234:8: expr_add
                    {
                    pushFollow(FOLLOW_expr_add_in_expr939);
                    expr_add91=expr_add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_add91.getTree());

                    }
                    break;
                case 2 :
                    // MySQLParser.g:235:4: subquery
                    {
                    pushFollow(FOLLOW_subquery_in_expr944);
                    subquery92=subquery();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, subquery92.getTree());

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
        return retval;
    }
    // $ANTLR end "expr"

    public static class subquery_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "subquery"
    // MySQLParser.g:239:1: subquery : LPAREN select_command RPAREN -> ^( SUBQUERY select_command ) ;
    public final MySQLParserParser.subquery_return subquery() throws RecognitionException {
        MySQLParserParser.subquery_return retval = new MySQLParserParser.subquery_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LPAREN93=null;
        Token RPAREN95=null;
        MySQLParserParser.select_command_return select_command94 = null;


        CommonTree LPAREN93_tree=null;
        CommonTree RPAREN95_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_select_command=new RewriteRuleSubtreeStream(adaptor,"rule select_command");
        try {
            // MySQLParser.g:239:9: ( LPAREN select_command RPAREN -> ^( SUBQUERY select_command ) )
            // MySQLParser.g:240:2: LPAREN select_command RPAREN
            {
            LPAREN93=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_subquery960); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN93);

            pushFollow(FOLLOW_select_command_in_subquery962);
            select_command94=select_command();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_select_command.add(select_command94.getTree());
            RPAREN95=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_subquery964); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN95);



            // AST REWRITE
            // elements: select_command
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 240:30: -> ^( SUBQUERY select_command )
            {
                // MySQLParser.g:240:32: ^( SUBQUERY select_command )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SUBQUERY, "SUBQUERY"), root_1);

                adaptor.addChild(root_1, stream_select_command.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "subquery"

    public static class expr_add_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr_add"
    // MySQLParser.g:242:1: expr_add : expr_mul ( ( PLUS | MINUS | DOUBLEVERTBAR ) ( expr_mul ) )* ;
    public final MySQLParserParser.expr_add_return expr_add() throws RecognitionException {
        MySQLParserParser.expr_add_return retval = new MySQLParserParser.expr_add_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token PLUS97=null;
        Token MINUS98=null;
        Token DOUBLEVERTBAR99=null;
        MySQLParserParser.expr_mul_return expr_mul96 = null;

        MySQLParserParser.expr_mul_return expr_mul100 = null;


        CommonTree PLUS97_tree=null;
        CommonTree MINUS98_tree=null;
        CommonTree DOUBLEVERTBAR99_tree=null;

        try {
            // MySQLParser.g:243:2: ( expr_mul ( ( PLUS | MINUS | DOUBLEVERTBAR ) ( expr_mul ) )* )
            // MySQLParser.g:243:3: expr_mul ( ( PLUS | MINUS | DOUBLEVERTBAR ) ( expr_mul ) )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_expr_mul_in_expr_add980);
            expr_mul96=expr_mul();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_mul96.getTree());
            // MySQLParser.g:243:12: ( ( PLUS | MINUS | DOUBLEVERTBAR ) ( expr_mul ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=PLUS && LA24_0<=DOUBLEVERTBAR)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // MySQLParser.g:243:14: ( PLUS | MINUS | DOUBLEVERTBAR ) ( expr_mul )
            	    {
            	    // MySQLParser.g:243:14: ( PLUS | MINUS | DOUBLEVERTBAR )
            	    int alt23=3;
            	    switch ( input.LA(1) ) {
            	    case PLUS:
            	        {
            	        alt23=1;
            	        }
            	        break;
            	    case MINUS:
            	        {
            	        alt23=2;
            	        }
            	        break;
            	    case DOUBLEVERTBAR:
            	        {
            	        alt23=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 23, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt23) {
            	        case 1 :
            	            // MySQLParser.g:243:16: PLUS
            	            {
            	            PLUS97=(Token)match(input,PLUS,FOLLOW_PLUS_in_expr_add986); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            PLUS97_tree = (CommonTree)adaptor.create(PLUS97);
            	            root_0 = (CommonTree)adaptor.becomeRoot(PLUS97_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // MySQLParser.g:243:24: MINUS
            	            {
            	            MINUS98=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr_add991); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            MINUS98_tree = (CommonTree)adaptor.create(MINUS98);
            	            root_0 = (CommonTree)adaptor.becomeRoot(MINUS98_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // MySQLParser.g:243:33: DOUBLEVERTBAR
            	            {
            	            DOUBLEVERTBAR99=(Token)match(input,DOUBLEVERTBAR,FOLLOW_DOUBLEVERTBAR_in_expr_add996); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            DOUBLEVERTBAR99_tree = (CommonTree)adaptor.create(DOUBLEVERTBAR99);
            	            root_0 = (CommonTree)adaptor.becomeRoot(DOUBLEVERTBAR99_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    // MySQLParser.g:243:50: ( expr_mul )
            	    // MySQLParser.g:243:51: expr_mul
            	    {
            	    pushFollow(FOLLOW_expr_mul_in_expr_add1002);
            	    expr_mul100=expr_mul();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_mul100.getTree());

            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


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
        return retval;
    }
    // $ANTLR end "expr_add"

    public static class expr_mul_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr_mul"
    // MySQLParser.g:249:1: expr_mul : expr_sign ( ( ASTERISK | DIVIDE | MOD ) expr_sign )* ;
    public final MySQLParserParser.expr_mul_return expr_mul() throws RecognitionException {
        MySQLParserParser.expr_mul_return retval = new MySQLParserParser.expr_mul_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ASTERISK102=null;
        Token DIVIDE103=null;
        Token MOD104=null;
        MySQLParserParser.expr_sign_return expr_sign101 = null;

        MySQLParserParser.expr_sign_return expr_sign105 = null;


        CommonTree ASTERISK102_tree=null;
        CommonTree DIVIDE103_tree=null;
        CommonTree MOD104_tree=null;

        try {
            // MySQLParser.g:250:2: ( expr_sign ( ( ASTERISK | DIVIDE | MOD ) expr_sign )* )
            // MySQLParser.g:250:4: expr_sign ( ( ASTERISK | DIVIDE | MOD ) expr_sign )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_expr_sign_in_expr_mul1022);
            expr_sign101=expr_sign();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_sign101.getTree());
            // MySQLParser.g:250:14: ( ( ASTERISK | DIVIDE | MOD ) expr_sign )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==ASTERISK) ) {
                    int LA26_2 = input.LA(2);

                    if ( (LA26_2==LPAREN||(LA26_2>=PLUS && LA26_2<=MINUS)||LA26_2==ASTERISK||LA26_2==ID||(LA26_2>=N && LA26_2<=NUMBER)||(LA26_2>=QUOTED_STRING && LA26_2<=DOUBLEQUOTED_STRING)||LA26_2==91||LA26_2==94||(LA26_2>=96 && LA26_2<=98)) ) {
                        alt26=1;
                    }


                }
                else if ( ((LA26_0>=DIVIDE && LA26_0<=MOD)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // MySQLParser.g:250:16: ( ASTERISK | DIVIDE | MOD ) expr_sign
            	    {
            	    // MySQLParser.g:250:16: ( ASTERISK | DIVIDE | MOD )
            	    int alt25=3;
            	    switch ( input.LA(1) ) {
            	    case ASTERISK:
            	        {
            	        alt25=1;
            	        }
            	        break;
            	    case DIVIDE:
            	        {
            	        alt25=2;
            	        }
            	        break;
            	    case MOD:
            	        {
            	        alt25=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 25, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt25) {
            	        case 1 :
            	            // MySQLParser.g:250:18: ASTERISK
            	            {
            	            ASTERISK102=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_expr_mul1028); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            ASTERISK102_tree = (CommonTree)adaptor.create(ASTERISK102);
            	            root_0 = (CommonTree)adaptor.becomeRoot(ASTERISK102_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // MySQLParser.g:250:30: DIVIDE
            	            {
            	            DIVIDE103=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_expr_mul1033); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            DIVIDE103_tree = (CommonTree)adaptor.create(DIVIDE103);
            	            root_0 = (CommonTree)adaptor.becomeRoot(DIVIDE103_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // MySQLParser.g:250:40: MOD
            	            {
            	            MOD104=(Token)match(input,MOD,FOLLOW_MOD_in_expr_mul1038); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            MOD104_tree = (CommonTree)adaptor.create(MOD104);
            	            root_0 = (CommonTree)adaptor.becomeRoot(MOD104_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_expr_sign_in_expr_mul1043);
            	    expr_sign105=expr_sign();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_sign105.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


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
        return retval;
    }
    // $ANTLR end "expr_mul"

    public static class expr_sign_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr_sign"
    // MySQLParser.g:252:1: expr_sign : ( PLUS | MINUS )? expr_pow ;
    public final MySQLParserParser.expr_sign_return expr_sign() throws RecognitionException {
        MySQLParserParser.expr_sign_return retval = new MySQLParserParser.expr_sign_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token PLUS106=null;
        Token MINUS107=null;
        MySQLParserParser.expr_pow_return expr_pow108 = null;


        CommonTree PLUS106_tree=null;
        CommonTree MINUS107_tree=null;

        try {
            // MySQLParser.g:253:2: ( ( PLUS | MINUS )? expr_pow )
            // MySQLParser.g:253:4: ( PLUS | MINUS )? expr_pow
            {
            root_0 = (CommonTree)adaptor.nil();

            // MySQLParser.g:253:4: ( PLUS | MINUS )?
            int alt27=3;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==PLUS) ) {
                alt27=1;
            }
            else if ( (LA27_0==MINUS) ) {
                alt27=2;
            }
            switch (alt27) {
                case 1 :
                    // MySQLParser.g:253:6: PLUS
                    {
                    PLUS106=(Token)match(input,PLUS,FOLLOW_PLUS_in_expr_sign1058); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUS106_tree = (CommonTree)adaptor.create(PLUS106);
                    root_0 = (CommonTree)adaptor.becomeRoot(PLUS106_tree, root_0);
                    }

                    }
                    break;
                case 2 :
                    // MySQLParser.g:253:14: MINUS
                    {
                    MINUS107=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr_sign1063); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUS107_tree = (CommonTree)adaptor.create(MINUS107);
                    root_0 = (CommonTree)adaptor.becomeRoot(MINUS107_tree, root_0);
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_expr_pow_in_expr_sign1069);
            expr_pow108=expr_pow();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_pow108.getTree());

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
        return retval;
    }
    // $ANTLR end "expr_sign"

    public static class expr_pow_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr_pow"
    // MySQLParser.g:255:1: expr_pow : expr_expr ( EXPONENT expr_expr )* ;
    public final MySQLParserParser.expr_pow_return expr_pow() throws RecognitionException {
        MySQLParserParser.expr_pow_return retval = new MySQLParserParser.expr_pow_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EXPONENT110=null;
        MySQLParserParser.expr_expr_return expr_expr109 = null;

        MySQLParserParser.expr_expr_return expr_expr111 = null;


        CommonTree EXPONENT110_tree=null;

        try {
            // MySQLParser.g:256:2: ( expr_expr ( EXPONENT expr_expr )* )
            // MySQLParser.g:256:4: expr_expr ( EXPONENT expr_expr )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_expr_expr_in_expr_pow1079);
            expr_expr109=expr_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_expr109.getTree());
            // MySQLParser.g:256:14: ( EXPONENT expr_expr )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==EXPONENT) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // MySQLParser.g:256:16: EXPONENT expr_expr
            	    {
            	    EXPONENT110=(Token)match(input,EXPONENT,FOLLOW_EXPONENT_in_expr_pow1083); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    EXPONENT110_tree = (CommonTree)adaptor.create(EXPONENT110);
            	    root_0 = (CommonTree)adaptor.becomeRoot(EXPONENT110_tree, root_0);
            	    }
            	    pushFollow(FOLLOW_expr_expr_in_expr_pow1086);
            	    expr_expr111=expr_expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_expr111.getTree());

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


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
        return retval;
    }
    // $ANTLR end "expr_pow"

    public static class expr_expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr_expr"
    // MySQLParser.g:259:1: expr_expr : ( value | boolean_literal | 'NULL' | 'ROWNUM' | {...}? ID ( ( LPAREN ( values_func )? RPAREN ) | ) -> ^( ID ( values_func )? ) );
    public final MySQLParserParser.expr_expr_return expr_expr() throws RecognitionException {
        MySQLParserParser.expr_expr_return retval = new MySQLParserParser.expr_expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal114=null;
        Token string_literal115=null;
        Token ID116=null;
        Token LPAREN117=null;
        Token RPAREN119=null;
        MySQLParserParser.value_return value112 = null;

        MySQLParserParser.boolean_literal_return boolean_literal113 = null;

        MySQLParserParser.values_func_return values_func118 = null;


        CommonTree string_literal114_tree=null;
        CommonTree string_literal115_tree=null;
        CommonTree ID116_tree=null;
        CommonTree LPAREN117_tree=null;
        CommonTree RPAREN119_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_values_func=new RewriteRuleSubtreeStream(adaptor,"rule values_func");
        try {
            // MySQLParser.g:260:2: ( value | boolean_literal | 'NULL' | 'ROWNUM' | {...}? ID ( ( LPAREN ( values_func )? RPAREN ) | ) -> ^( ID ( values_func )? ) )
            int alt31=5;
            switch ( input.LA(1) ) {
            case LPAREN:
            case ASTERISK:
            case N:
            case NUMBER:
            case QUOTED_STRING:
            case DOUBLEQUOTED_STRING:
            case 96:
                {
                alt31=1;
                }
                break;
            case ID:
                {
                int LA31_2 = input.LA(2);

                if ( (!(((functionMap.containsKey(input.LT(1).getText().toUpperCase()))))) ) {
                    alt31=1;
                }
                else if ( ((functionMap.containsKey(input.LT(1).getText().toUpperCase()))) ) {
                    alt31=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 2, input);

                    throw nvae;
                }
                }
                break;
            case 97:
            case 98:
                {
                alt31=2;
                }
                break;
            case 91:
                {
                alt31=3;
                }
                break;
            case 94:
                {
                alt31=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // MySQLParser.g:260:3: value
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_value_in_expr_expr1099);
                    value112=value();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, value112.getTree());

                    }
                    break;
                case 2 :
                    // MySQLParser.g:261:3: boolean_literal
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_boolean_literal_in_expr_expr1103);
                    boolean_literal113=boolean_literal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, boolean_literal113.getTree());

                    }
                    break;
                case 3 :
                    // MySQLParser.g:262:3: 'NULL'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    string_literal114=(Token)match(input,91,FOLLOW_91_in_expr_expr1107); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal114_tree = (CommonTree)adaptor.create(string_literal114);
                    adaptor.addChild(root_0, string_literal114_tree);
                    }

                    }
                    break;
                case 4 :
                    // MySQLParser.g:263:3: 'ROWNUM'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    string_literal115=(Token)match(input,94,FOLLOW_94_in_expr_expr1111); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal115_tree = (CommonTree)adaptor.create(string_literal115);
                    adaptor.addChild(root_0, string_literal115_tree);
                    }

                    }
                    break;
                case 5 :
                    // MySQLParser.g:266:3: {...}? ID ( ( LPAREN ( values_func )? RPAREN ) | )
                    {
                    if ( !((functionMap.containsKey(input.LT(1).getText().toUpperCase()))) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "expr_expr", "functionMap.containsKey(input.LT(1).getText().toUpperCase())");
                    }
                    ID116=(Token)match(input,ID,FOLLOW_ID_in_expr_expr1119); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(ID116);

                    // MySQLParser.g:266:70: ( ( LPAREN ( values_func )? RPAREN ) | )
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==LPAREN) ) {
                        alt30=1;
                    }
                    else if ( (LA30_0==EOF||(LA30_0>=COMMA && LA30_0<=EQ)||(LA30_0>=RPAREN && LA30_0<=GEQ)||LA30_0==80||(LA30_0>=83 && LA30_0<=88)||(LA30_0>=92 && LA30_0<=93)||LA30_0==95||(LA30_0>=99 && LA30_0<=101)||LA30_0==103||LA30_0==106||(LA30_0>=108 && LA30_0<=111)) ) {
                        alt30=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 30, 0, input);

                        throw nvae;
                    }
                    switch (alt30) {
                        case 1 :
                            // MySQLParser.g:266:71: ( LPAREN ( values_func )? RPAREN )
                            {
                            // MySQLParser.g:266:71: ( LPAREN ( values_func )? RPAREN )
                            // MySQLParser.g:266:72: LPAREN ( values_func )? RPAREN
                            {
                            LPAREN117=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_expr_expr1123); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN117);

                            // MySQLParser.g:266:79: ( values_func )?
                            int alt29=2;
                            int LA29_0 = input.LA(1);

                            if ( (LA29_0==LPAREN||(LA29_0>=PLUS && LA29_0<=MINUS)||LA29_0==ASTERISK||LA29_0==ID||(LA29_0>=N && LA29_0<=NUMBER)||(LA29_0>=QUOTED_STRING && LA29_0<=DOUBLEQUOTED_STRING)||LA29_0==91||LA29_0==94||(LA29_0>=96 && LA29_0<=98)) ) {
                                alt29=1;
                            }
                            switch (alt29) {
                                case 1 :
                                    // MySQLParser.g:266:79: values_func
                                    {
                                    pushFollow(FOLLOW_values_func_in_expr_expr1125);
                                    values_func118=values_func();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) stream_values_func.add(values_func118.getTree());

                                    }
                                    break;

                            }

                            RPAREN119=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_expr_expr1128); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN119);


                            }


                            }
                            break;
                        case 2 :
                            // MySQLParser.g:266:100: 
                            {
                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: ID, values_func
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 266:101: -> ^( ID ( values_func )? )
                    {
                        // MySQLParser.g:266:103: ^( ID ( values_func )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ID.nextNode(), root_1);

                        // MySQLParser.g:266:108: ( values_func )?
                        if ( stream_values_func.hasNext() ) {
                            adaptor.addChild(root_1, stream_values_func.nextTree());

                        }
                        stream_values_func.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "expr_expr"

    public static class sql_condition_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sql_condition"
    // MySQLParser.g:271:1: sql_condition : condition_or ;
    public final MySQLParserParser.sql_condition_return sql_condition() throws RecognitionException {
        MySQLParserParser.sql_condition_return retval = new MySQLParserParser.sql_condition_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.condition_or_return condition_or120 = null;



        try {
            // MySQLParser.g:272:2: ( condition_or )
            // MySQLParser.g:272:4: condition_or
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_condition_or_in_sql_condition1150);
            condition_or120=condition_or();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, condition_or120.getTree());

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
        return retval;
    }
    // $ANTLR end "sql_condition"

    public static class relational_op_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relational_op"
    // MySQLParser.g:274:1: relational_op : ( EQ | LTH | GTH | NOT_EQ | LEQ | GEQ );
    public final MySQLParserParser.relational_op_return relational_op() throws RecognitionException {
        MySQLParserParser.relational_op_return retval = new MySQLParserParser.relational_op_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set121=null;

        CommonTree set121_tree=null;

        try {
            // MySQLParser.g:275:2: ( EQ | LTH | GTH | NOT_EQ | LEQ | GEQ )
            // MySQLParser.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set121=(Token)input.LT(1);
            if ( input.LA(1)==EQ||(input.LA(1)>=LTH && input.LA(1)<=GEQ) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set121));
                state.errorRecovery=false;state.failed=false;
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
        return retval;
    }
    // $ANTLR end "relational_op"

    public static class fromClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fromClause"
    // MySQLParser.g:278:1: fromClause : 'FROM' selected_table ;
    public final MySQLParserParser.fromClause_return fromClause() throws RecognitionException {
        MySQLParserParser.fromClause_return retval = new MySQLParserParser.fromClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal122=null;
        MySQLParserParser.selected_table_return selected_table123 = null;


        CommonTree string_literal122_tree=null;

        try {
            // MySQLParser.g:279:2: ( 'FROM' selected_table )
            // MySQLParser.g:279:3: 'FROM' selected_table
            {
            root_0 = (CommonTree)adaptor.nil();

            string_literal122=(Token)match(input,95,FOLLOW_95_in_fromClause1190); if (state.failed) return retval;
            pushFollow(FOLLOW_selected_table_in_fromClause1193);
            selected_table123=selected_table();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, selected_table123.getTree());

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
        return retval;
    }
    // $ANTLR end "fromClause"

    public static class select_list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "select_list"
    // MySQLParser.g:282:1: select_list : displayed_column ( COMMA displayed_column )* -> ^( SELECT_LIST ( displayed_column )+ ) ;
    public final MySQLParserParser.select_list_return select_list() throws RecognitionException {
        MySQLParserParser.select_list_return retval = new MySQLParserParser.select_list_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA125=null;
        MySQLParserParser.displayed_column_return displayed_column124 = null;

        MySQLParserParser.displayed_column_return displayed_column126 = null;


        CommonTree COMMA125_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_displayed_column=new RewriteRuleSubtreeStream(adaptor,"rule displayed_column");
        try {
            // MySQLParser.g:283:2: ( displayed_column ( COMMA displayed_column )* -> ^( SELECT_LIST ( displayed_column )+ ) )
            // MySQLParser.g:283:4: displayed_column ( COMMA displayed_column )*
            {
            pushFollow(FOLLOW_displayed_column_in_select_list1204);
            displayed_column124=displayed_column();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_displayed_column.add(displayed_column124.getTree());
            // MySQLParser.g:283:21: ( COMMA displayed_column )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==COMMA) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // MySQLParser.g:283:23: COMMA displayed_column
            	    {
            	    COMMA125=(Token)match(input,COMMA,FOLLOW_COMMA_in_select_list1208); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA125);

            	    pushFollow(FOLLOW_displayed_column_in_select_list1210);
            	    displayed_column126=displayed_column();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_displayed_column.add(displayed_column126.getTree());

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);



            // AST REWRITE
            // elements: displayed_column
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 283:48: -> ^( SELECT_LIST ( displayed_column )+ )
            {
                // MySQLParser.g:283:50: ^( SELECT_LIST ( displayed_column )+ )
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

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "select_list"

    public static class displayed_column_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "displayed_column"
    // MySQLParser.g:286:1: displayed_column : ( quoted_string ( alias )? -> ^( quoted_string ( alias )? ) | {...}? concat LPAREN identifiedOrQuotedString ( COMMA identifiedOrQuotedString )* RPAREN ( alias )? -> ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? ) | {...}? count LPAREN ( distinct )? ( LPAREN )? countColumn ( RPAREN )? RPAREN ( alias )? -> ^( count ( distinct )? countColumn ( alias )? ) | expr_add ( alias )? -> ^( EXPR expr_add ( alias )? ) | {...}? ID ( ( LPAREN ( table_alias )? ( column )? RPAREN ) | ) ( alias )? -> ^( ID ( table_alias )? ( column )? ( alias )? ) );
    public final MySQLParserParser.displayed_column_return displayed_column() throws RecognitionException {
        MySQLParserParser.displayed_column_return retval = new MySQLParserParser.displayed_column_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LPAREN130=null;
        Token COMMA132=null;
        Token RPAREN134=null;
        Token LPAREN137=null;
        Token LPAREN139=null;
        Token RPAREN141=null;
        Token RPAREN142=null;
        Token ID146=null;
        Token LPAREN147=null;
        Token RPAREN150=null;
        MySQLParserParser.quoted_string_return quoted_string127 = null;

        MySQLParserParser.alias_return alias128 = null;

        MySQLParserParser.concat_return concat129 = null;

        MySQLParserParser.identifiedOrQuotedString_return identifiedOrQuotedString131 = null;

        MySQLParserParser.identifiedOrQuotedString_return identifiedOrQuotedString133 = null;

        MySQLParserParser.alias_return alias135 = null;

        MySQLParserParser.count_return count136 = null;

        MySQLParserParser.distinct_return distinct138 = null;

        MySQLParserParser.countColumn_return countColumn140 = null;

        MySQLParserParser.alias_return alias143 = null;

        MySQLParserParser.expr_add_return expr_add144 = null;

        MySQLParserParser.alias_return alias145 = null;

        MySQLParserParser.table_alias_return table_alias148 = null;

        MySQLParserParser.column_return column149 = null;

        MySQLParserParser.alias_return alias151 = null;


        CommonTree LPAREN130_tree=null;
        CommonTree COMMA132_tree=null;
        CommonTree RPAREN134_tree=null;
        CommonTree LPAREN137_tree=null;
        CommonTree LPAREN139_tree=null;
        CommonTree RPAREN141_tree=null;
        CommonTree RPAREN142_tree=null;
        CommonTree ID146_tree=null;
        CommonTree LPAREN147_tree=null;
        CommonTree RPAREN150_tree=null;
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
            // MySQLParser.g:287:2: ( quoted_string ( alias )? -> ^( quoted_string ( alias )? ) | {...}? concat LPAREN identifiedOrQuotedString ( COMMA identifiedOrQuotedString )* RPAREN ( alias )? -> ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? ) | {...}? count LPAREN ( distinct )? ( LPAREN )? countColumn ( RPAREN )? RPAREN ( alias )? -> ^( count ( distinct )? countColumn ( alias )? ) | expr_add ( alias )? -> ^( EXPR expr_add ( alias )? ) | {...}? ID ( ( LPAREN ( table_alias )? ( column )? RPAREN ) | ) ( alias )? -> ^( ID ( table_alias )? ( column )? ( alias )? ) )
            int alt45=5;
            switch ( input.LA(1) ) {
            case QUOTED_STRING:
            case DOUBLEQUOTED_STRING:
                {
                alt45=1;
                }
                break;
            case 112:
                {
                alt45=2;
                }
                break;
            case 114:
                {
                alt45=3;
                }
                break;
            case LPAREN:
            case PLUS:
            case MINUS:
            case ASTERISK:
            case N:
            case NUMBER:
            case 91:
            case 94:
            case 96:
            case 97:
            case 98:
                {
                alt45=4;
                }
                break;
            case ID:
                {
                int LA45_5 = input.LA(2);

                if ( (!(((groupFunc.containsKey(input.LT(1).getText().toUpperCase()))))) ) {
                    alt45=4;
                }
                else if ( ((groupFunc.containsKey(input.LT(1).getText().toUpperCase()))) ) {
                    alt45=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }

            switch (alt45) {
                case 1 :
                    // MySQLParser.g:288:2: quoted_string ( alias )?
                    {
                    pushFollow(FOLLOW_quoted_string_in_displayed_column1233);
                    quoted_string127=quoted_string();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_quoted_string.add(quoted_string127.getTree());
                    // MySQLParser.g:288:16: ( alias )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==ASTERISK||LA33_0==ID||LA33_0==99) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // MySQLParser.g:288:16: alias
                            {
                            pushFollow(FOLLOW_alias_in_displayed_column1235);
                            alias128=alias();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_alias.add(alias128.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: alias, quoted_string
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 288:22: -> ^( quoted_string ( alias )? )
                    {
                        // MySQLParser.g:288:24: ^( quoted_string ( alias )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_quoted_string.nextNode(), root_1);

                        // MySQLParser.g:288:40: ( alias )?
                        if ( stream_alias.hasNext() ) {
                            adaptor.addChild(root_1, stream_alias.nextTree());

                        }
                        stream_alias.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // MySQLParser.g:289:3: {...}? concat LPAREN identifiedOrQuotedString ( COMMA identifiedOrQuotedString )* RPAREN ( alias )?
                    {
                    if ( !((input.LT(1).getText().toUpperCase().equals("CONCAT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "displayed_column", "input.LT(1).getText().toUpperCase().equals(\"CONCAT\")");
                    }
                    pushFollow(FOLLOW_concat_in_displayed_column1249);
                    concat129=concat();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_concat.add(concat129.getTree());
                    LPAREN130=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_displayed_column1251); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN130);

                    pushFollow(FOLLOW_identifiedOrQuotedString_in_displayed_column1253);
                    identifiedOrQuotedString131=identifiedOrQuotedString();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_identifiedOrQuotedString.add(identifiedOrQuotedString131.getTree());
                    // MySQLParser.g:289:98: ( COMMA identifiedOrQuotedString )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==COMMA) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // MySQLParser.g:289:99: COMMA identifiedOrQuotedString
                    	    {
                    	    COMMA132=(Token)match(input,COMMA,FOLLOW_COMMA_in_displayed_column1256); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA132);

                    	    pushFollow(FOLLOW_identifiedOrQuotedString_in_displayed_column1258);
                    	    identifiedOrQuotedString133=identifiedOrQuotedString();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_identifiedOrQuotedString.add(identifiedOrQuotedString133.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop34;
                        }
                    } while (true);

                    RPAREN134=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_displayed_column1262); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN134);

                    // MySQLParser.g:289:139: ( alias )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==ASTERISK||LA35_0==ID||LA35_0==99) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // MySQLParser.g:289:139: alias
                            {
                            pushFollow(FOLLOW_alias_in_displayed_column1264);
                            alias135=alias();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_alias.add(alias135.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: alias, identifiedOrQuotedString, identifiedOrQuotedString, concat
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 289:145: -> ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? )
                    {
                        // MySQLParser.g:289:147: ^( concat identifiedOrQuotedString ( identifiedOrQuotedString )* ( alias )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_concat.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_identifiedOrQuotedString.nextTree());
                        // MySQLParser.g:289:181: ( identifiedOrQuotedString )*
                        while ( stream_identifiedOrQuotedString.hasNext() ) {
                            adaptor.addChild(root_1, stream_identifiedOrQuotedString.nextTree());

                        }
                        stream_identifiedOrQuotedString.reset();
                        // MySQLParser.g:289:209: ( alias )?
                        if ( stream_alias.hasNext() ) {
                            adaptor.addChild(root_1, stream_alias.nextTree());

                        }
                        stream_alias.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // MySQLParser.g:290:3: {...}? count LPAREN ( distinct )? ( LPAREN )? countColumn ( RPAREN )? RPAREN ( alias )?
                    {
                    if ( !((input.LT(1).getText().toUpperCase().equals("COUNT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "displayed_column", "input.LT(1).getText().toUpperCase().equals(\"COUNT\")");
                    }
                    pushFollow(FOLLOW_count_in_displayed_column1285);
                    count136=count();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_count.add(count136.getTree());
                    LPAREN137=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_displayed_column1287); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN137);

                    // MySQLParser.g:290:71: ( distinct )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==113) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // MySQLParser.g:290:71: distinct
                            {
                            pushFollow(FOLLOW_distinct_in_displayed_column1289);
                            distinct138=distinct();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_distinct.add(distinct138.getTree());

                            }
                            break;

                    }

                    // MySQLParser.g:290:81: ( LPAREN )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==LPAREN) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // MySQLParser.g:290:81: LPAREN
                            {
                            LPAREN139=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_displayed_column1292); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN139);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_countColumn_in_displayed_column1295);
                    countColumn140=countColumn();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_countColumn.add(countColumn140.getTree());
                    // MySQLParser.g:290:101: ( RPAREN )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==RPAREN) ) {
                        int LA38_1 = input.LA(2);

                        if ( (LA38_1==RPAREN) ) {
                            alt38=1;
                        }
                    }
                    switch (alt38) {
                        case 1 :
                            // MySQLParser.g:290:101: RPAREN
                            {
                            RPAREN141=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_displayed_column1297); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN141);


                            }
                            break;

                    }

                    RPAREN142=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_displayed_column1300); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN142);

                    // MySQLParser.g:290:116: ( alias )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==ASTERISK||LA39_0==ID||LA39_0==99) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // MySQLParser.g:290:116: alias
                            {
                            pushFollow(FOLLOW_alias_in_displayed_column1302);
                            alias143=alias();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_alias.add(alias143.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: count, countColumn, alias, distinct
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 290:122: -> ^( count ( distinct )? countColumn ( alias )? )
                    {
                        // MySQLParser.g:290:124: ^( count ( distinct )? countColumn ( alias )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_count.nextNode(), root_1);

                        // MySQLParser.g:290:132: ( distinct )?
                        if ( stream_distinct.hasNext() ) {
                            adaptor.addChild(root_1, stream_distinct.nextTree());

                        }
                        stream_distinct.reset();
                        adaptor.addChild(root_1, stream_countColumn.nextTree());
                        // MySQLParser.g:290:154: ( alias )?
                        if ( stream_alias.hasNext() ) {
                            adaptor.addChild(root_1, stream_alias.nextTree());

                        }
                        stream_alias.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // MySQLParser.g:291:3: expr_add ( alias )?
                    {
                    pushFollow(FOLLOW_expr_add_in_displayed_column1319);
                    expr_add144=expr_add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr_add.add(expr_add144.getTree());
                    // MySQLParser.g:291:12: ( alias )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==ASTERISK||LA40_0==ID||LA40_0==99) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // MySQLParser.g:291:12: alias
                            {
                            pushFollow(FOLLOW_alias_in_displayed_column1321);
                            alias145=alias();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_alias.add(alias145.getTree());

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
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 291:18: -> ^( EXPR expr_add ( alias )? )
                    {
                        // MySQLParser.g:291:20: ^( EXPR expr_add ( alias )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EXPR, "EXPR"), root_1);

                        adaptor.addChild(root_1, stream_expr_add.nextTree());
                        // MySQLParser.g:291:36: ( alias )?
                        if ( stream_alias.hasNext() ) {
                            adaptor.addChild(root_1, stream_alias.nextTree());

                        }
                        stream_alias.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 5 :
                    // MySQLParser.g:293:3: {...}? ID ( ( LPAREN ( table_alias )? ( column )? RPAREN ) | ) ( alias )?
                    {
                    if ( !((groupFunc.containsKey(input.LT(1).getText().toUpperCase()))) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "displayed_column", "groupFunc.containsKey(input.LT(1).getText().toUpperCase())");
                    }
                    ID146=(Token)match(input,ID,FOLLOW_ID_in_displayed_column1339); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(ID146);

                    // MySQLParser.g:293:68: ( ( LPAREN ( table_alias )? ( column )? RPAREN ) | )
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==LPAREN) ) {
                        alt43=1;
                    }
                    else if ( (LA43_0==EOF||LA43_0==COMMA||LA43_0==RPAREN||LA43_0==ASTERISK||LA43_0==ID||LA43_0==80||LA43_0==83||LA43_0==95||(LA43_0>=99 && LA43_0<=101)||LA43_0==103||LA43_0==106||(LA43_0>=108 && LA43_0<=111)) ) {
                        alt43=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 43, 0, input);

                        throw nvae;
                    }
                    switch (alt43) {
                        case 1 :
                            // MySQLParser.g:293:69: ( LPAREN ( table_alias )? ( column )? RPAREN )
                            {
                            // MySQLParser.g:293:69: ( LPAREN ( table_alias )? ( column )? RPAREN )
                            // MySQLParser.g:293:70: LPAREN ( table_alias )? ( column )? RPAREN
                            {
                            LPAREN147=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_displayed_column1343); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN147);

                            // MySQLParser.g:293:77: ( table_alias )?
                            int alt41=2;
                            int LA41_0 = input.LA(1);

                            if ( (LA41_0==ASTERISK) ) {
                                int LA41_1 = input.LA(2);

                                if ( (LA41_1==DOT) ) {
                                    alt41=1;
                                }
                            }
                            else if ( (LA41_0==ID) ) {
                                int LA41_2 = input.LA(2);

                                if ( (LA41_2==DOT) ) {
                                    alt41=1;
                                }
                            }
                            switch (alt41) {
                                case 1 :
                                    // MySQLParser.g:293:77: table_alias
                                    {
                                    pushFollow(FOLLOW_table_alias_in_displayed_column1345);
                                    table_alias148=table_alias();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) stream_table_alias.add(table_alias148.getTree());

                                    }
                                    break;

                            }

                            // MySQLParser.g:293:90: ( column )?
                            int alt42=2;
                            int LA42_0 = input.LA(1);

                            if ( (LA42_0==ASTERISK||LA42_0==ID) ) {
                                alt42=1;
                            }
                            switch (alt42) {
                                case 1 :
                                    // MySQLParser.g:293:90: column
                                    {
                                    pushFollow(FOLLOW_column_in_displayed_column1348);
                                    column149=column();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) stream_column.add(column149.getTree());

                                    }
                                    break;

                            }

                            RPAREN150=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_displayed_column1351); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN150);


                            }


                            }
                            break;
                        case 2 :
                            // MySQLParser.g:293:106: 
                            {
                            }
                            break;

                    }

                    // MySQLParser.g:293:108: ( alias )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==ASTERISK||LA44_0==ID||LA44_0==99) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // MySQLParser.g:293:108: alias
                            {
                            pushFollow(FOLLOW_alias_in_displayed_column1356);
                            alias151=alias();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_alias.add(alias151.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: alias, column, table_alias, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 293:114: -> ^( ID ( table_alias )? ( column )? ( alias )? )
                    {
                        // MySQLParser.g:293:116: ^( ID ( table_alias )? ( column )? ( alias )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ID.nextNode(), root_1);

                        // MySQLParser.g:293:121: ( table_alias )?
                        if ( stream_table_alias.hasNext() ) {
                            adaptor.addChild(root_1, stream_table_alias.nextTree());

                        }
                        stream_table_alias.reset();
                        // MySQLParser.g:293:134: ( column )?
                        if ( stream_column.hasNext() ) {
                            adaptor.addChild(root_1, stream_column.nextTree());

                        }
                        stream_column.reset();
                        // MySQLParser.g:293:142: ( alias )?
                        if ( stream_alias.hasNext() ) {
                            adaptor.addChild(root_1, stream_alias.nextTree());

                        }
                        stream_alias.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "displayed_column"

    public static class columnNameAfterWhere_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "columnNameAfterWhere"
    // MySQLParser.g:296:1: columnNameAfterWhere : ( ( table_alias )? identifier -> ^( ASC identifier ( table_alias )? ) | ( table_alias )? identifier ASC -> ^( ASC identifier ( table_alias )? ) | ( table_alias )? identifier DESC -> ^( DESC identifier ( table_alias )? ) );
    public final MySQLParserParser.columnNameAfterWhere_return columnNameAfterWhere() throws RecognitionException {
        MySQLParserParser.columnNameAfterWhere_return retval = new MySQLParserParser.columnNameAfterWhere_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ASC156=null;
        Token DESC159=null;
        MySQLParserParser.table_alias_return table_alias152 = null;

        MySQLParserParser.identifier_return identifier153 = null;

        MySQLParserParser.table_alias_return table_alias154 = null;

        MySQLParserParser.identifier_return identifier155 = null;

        MySQLParserParser.table_alias_return table_alias157 = null;

        MySQLParserParser.identifier_return identifier158 = null;


        CommonTree ASC156_tree=null;
        CommonTree DESC159_tree=null;
        RewriteRuleTokenStream stream_DESC=new RewriteRuleTokenStream(adaptor,"token DESC");
        RewriteRuleTokenStream stream_ASC=new RewriteRuleTokenStream(adaptor,"token ASC");
        RewriteRuleSubtreeStream stream_table_alias=new RewriteRuleSubtreeStream(adaptor,"rule table_alias");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        try {
            // MySQLParser.g:297:2: ( ( table_alias )? identifier -> ^( ASC identifier ( table_alias )? ) | ( table_alias )? identifier ASC -> ^( ASC identifier ( table_alias )? ) | ( table_alias )? identifier DESC -> ^( DESC identifier ( table_alias )? ) )
            int alt49=3;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==ASTERISK||LA49_0==ID) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA49_2 = input.LA(3);

                    if ( (LA49_2==ASTERISK||LA49_2==ID) ) {
                        switch ( input.LA(4) ) {
                        case EOF:
                        case COMMA:
                        case RPAREN:
                        case 101:
                        case 103:
                        case 106:
                            {
                            alt49=1;
                            }
                            break;
                        case ASC:
                            {
                            alt49=2;
                            }
                            break;
                        case DESC:
                            {
                            alt49=3;
                            }
                            break;
                        default:
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 49, 6, input);

                            throw nvae;
                        }

                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 49, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case COMMA:
                case RPAREN:
                case 101:
                case 103:
                case 106:
                    {
                    alt49=1;
                    }
                    break;
                case ASC:
                    {
                    alt49=2;
                    }
                    break;
                case DESC:
                    {
                    alt49=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 49, 1, input);

                    throw nvae;
                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // MySQLParser.g:297:3: ( table_alias )? identifier
                    {
                    // MySQLParser.g:297:3: ( table_alias )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==ASTERISK||LA46_0==ID) ) {
                        int LA46_1 = input.LA(2);

                        if ( (LA46_1==DOT) ) {
                            alt46=1;
                        }
                    }
                    switch (alt46) {
                        case 1 :
                            // MySQLParser.g:297:3: table_alias
                            {
                            pushFollow(FOLLOW_table_alias_in_columnNameAfterWhere1381);
                            table_alias152=table_alias();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_table_alias.add(table_alias152.getTree());

                            }
                            break;

                    }

                    pushFollow(FOLLOW_identifier_in_columnNameAfterWhere1384);
                    identifier153=identifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_identifier.add(identifier153.getTree());


                    // AST REWRITE
                    // elements: identifier, table_alias
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 297:28: -> ^( ASC identifier ( table_alias )? )
                    {
                        // MySQLParser.g:297:30: ^( ASC identifier ( table_alias )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ASC, "ASC"), root_1);

                        adaptor.addChild(root_1, stream_identifier.nextTree());
                        // MySQLParser.g:297:47: ( table_alias )?
                        if ( stream_table_alias.hasNext() ) {
                            adaptor.addChild(root_1, stream_table_alias.nextTree());

                        }
                        stream_table_alias.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // MySQLParser.g:298:3: ( table_alias )? identifier ASC
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
                            pushFollow(FOLLOW_table_alias_in_columnNameAfterWhere1399);
                            table_alias154=table_alias();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_table_alias.add(table_alias154.getTree());

                            }
                            break;

                    }

                    pushFollow(FOLLOW_identifier_in_columnNameAfterWhere1402);
                    identifier155=identifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_identifier.add(identifier155.getTree());
                    ASC156=(Token)match(input,ASC,FOLLOW_ASC_in_columnNameAfterWhere1405); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASC.add(ASC156);



                    // AST REWRITE
                    // elements: identifier, ASC, table_alias
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 298:33: -> ^( ASC identifier ( table_alias )? )
                    {
                        // MySQLParser.g:298:35: ^( ASC identifier ( table_alias )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ASC.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_identifier.nextTree());
                        // MySQLParser.g:298:52: ( table_alias )?
                        if ( stream_table_alias.hasNext() ) {
                            adaptor.addChild(root_1, stream_table_alias.nextTree());

                        }
                        stream_table_alias.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // MySQLParser.g:299:3: ( table_alias )? identifier DESC
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
                            pushFollow(FOLLOW_table_alias_in_columnNameAfterWhere1420);
                            table_alias157=table_alias();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_table_alias.add(table_alias157.getTree());

                            }
                            break;

                    }

                    pushFollow(FOLLOW_identifier_in_columnNameAfterWhere1423);
                    identifier158=identifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_identifier.add(identifier158.getTree());
                    DESC159=(Token)match(input,DESC,FOLLOW_DESC_in_columnNameAfterWhere1426); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DESC.add(DESC159);



                    // AST REWRITE
                    // elements: DESC, table_alias, identifier
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 299:33: -> ^( DESC identifier ( table_alias )? )
                    {
                        // MySQLParser.g:299:35: ^( DESC identifier ( table_alias )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_DESC.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_identifier.nextTree());
                        // MySQLParser.g:299:53: ( table_alias )?
                        if ( stream_table_alias.hasNext() ) {
                            adaptor.addChild(root_1, stream_table_alias.nextTree());

                        }
                        stream_table_alias.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "columnNameAfterWhere"

    public static class columnNameInUpdate_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "columnNameInUpdate"
    // MySQLParser.g:302:1: columnNameInUpdate : ( table_alias )? identifier ;
    public final MySQLParserParser.columnNameInUpdate_return columnNameInUpdate() throws RecognitionException {
        MySQLParserParser.columnNameInUpdate_return retval = new MySQLParserParser.columnNameInUpdate_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.table_alias_return table_alias160 = null;

        MySQLParserParser.identifier_return identifier161 = null;



        try {
            // MySQLParser.g:303:2: ( ( table_alias )? identifier )
            // MySQLParser.g:303:3: ( table_alias )? identifier
            {
            root_0 = (CommonTree)adaptor.nil();

            // MySQLParser.g:303:3: ( table_alias )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==ASTERISK||LA50_0==ID) ) {
                int LA50_1 = input.LA(2);

                if ( (LA50_1==DOT) ) {
                    alt50=1;
                }
            }
            switch (alt50) {
                case 1 :
                    // MySQLParser.g:303:3: table_alias
                    {
                    pushFollow(FOLLOW_table_alias_in_columnNameInUpdate1447);
                    table_alias160=table_alias();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, table_alias160.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_identifier_in_columnNameInUpdate1450);
            identifier161=identifier();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier161.getTree());

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
        return retval;
    }
    // $ANTLR end "columnNameInUpdate"

    public static class table_alias_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "table_alias"
    // MySQLParser.g:305:1: table_alias : identifier DOT -> ^( COL_TAB identifier ) ;
    public final MySQLParserParser.table_alias_return table_alias() throws RecognitionException {
        MySQLParserParser.table_alias_return retval = new MySQLParserParser.table_alias_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token DOT163=null;
        MySQLParserParser.identifier_return identifier162 = null;


        CommonTree DOT163_tree=null;
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        try {
            // MySQLParser.g:306:2: ( identifier DOT -> ^( COL_TAB identifier ) )
            // MySQLParser.g:306:3: identifier DOT
            {
            pushFollow(FOLLOW_identifier_in_table_alias1460);
            identifier162=identifier();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_identifier.add(identifier162.getTree());
            DOT163=(Token)match(input,DOT,FOLLOW_DOT_in_table_alias1462); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_DOT.add(DOT163);



            // AST REWRITE
            // elements: identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 306:17: -> ^( COL_TAB identifier )
            {
                // MySQLParser.g:306:19: ^( COL_TAB identifier )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(COL_TAB, "COL_TAB"), root_1);

                adaptor.addChild(root_1, stream_identifier.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "table_alias"

    public static class column_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "column"
    // MySQLParser.g:308:1: column : ( ASTERISK | identifier );
    public final MySQLParserParser.column_return column() throws RecognitionException {
        MySQLParserParser.column_return retval = new MySQLParserParser.column_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ASTERISK164=null;
        MySQLParserParser.identifier_return identifier165 = null;


        CommonTree ASTERISK164_tree=null;

        try {
            // MySQLParser.g:309:2: ( ASTERISK | identifier )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==ASTERISK) ) {
                alt51=1;
            }
            else if ( (LA51_0==ID) ) {
                alt51=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // MySQLParser.g:309:3: ASTERISK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ASTERISK164=(Token)match(input,ASTERISK,FOLLOW_ASTERISK_in_column1478); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASTERISK164_tree = (CommonTree)adaptor.create(ASTERISK164);
                    adaptor.addChild(root_0, ASTERISK164_tree);
                    }

                    }
                    break;
                case 2 :
                    // MySQLParser.g:310:3: identifier
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_identifier_in_column1482);
                    identifier165=identifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier165.getTree());

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
        return retval;
    }
    // $ANTLR end "column"

    public static class values_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "values"
    // MySQLParser.g:312:1: values : expr ( COMMA expr )* -> ^( INSERT_VAL ( expr )* ) ;
    public final MySQLParserParser.values_return values() throws RecognitionException {
        MySQLParserParser.values_return retval = new MySQLParserParser.values_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA167=null;
        MySQLParserParser.expr_return expr166 = null;

        MySQLParserParser.expr_return expr168 = null;


        CommonTree COMMA167_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // MySQLParser.g:313:2: ( expr ( COMMA expr )* -> ^( INSERT_VAL ( expr )* ) )
            // MySQLParser.g:313:3: expr ( COMMA expr )*
            {
            pushFollow(FOLLOW_expr_in_values1491);
            expr166=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr166.getTree());
            // MySQLParser.g:313:8: ( COMMA expr )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==COMMA) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // MySQLParser.g:313:10: COMMA expr
            	    {
            	    COMMA167=(Token)match(input,COMMA,FOLLOW_COMMA_in_values1495); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA167);

            	    pushFollow(FOLLOW_expr_in_values1497);
            	    expr168=expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expr.add(expr168.getTree());

            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);



            // AST REWRITE
            // elements: expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 313:23: -> ^( INSERT_VAL ( expr )* )
            {
                // MySQLParser.g:313:25: ^( INSERT_VAL ( expr )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(INSERT_VAL, "INSERT_VAL"), root_1);

                // MySQLParser.g:313:38: ( expr )*
                while ( stream_expr.hasNext() ) {
                    adaptor.addChild(root_1, stream_expr.nextTree());

                }
                stream_expr.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "values"

    public static class values_func_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "values_func"
    // MySQLParser.g:315:1: values_func : expr ( COMMA expr )* ;
    public final MySQLParserParser.values_func_return values_func() throws RecognitionException {
        MySQLParserParser.values_func_return retval = new MySQLParserParser.values_func_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA170=null;
        MySQLParserParser.expr_return expr169 = null;

        MySQLParserParser.expr_return expr171 = null;


        CommonTree COMMA170_tree=null;

        try {
            // MySQLParser.g:316:2: ( expr ( COMMA expr )* )
            // MySQLParser.g:316:3: expr ( COMMA expr )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_expr_in_values_func1516);
            expr169=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr169.getTree());
            // MySQLParser.g:316:8: ( COMMA expr )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==COMMA) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // MySQLParser.g:316:10: COMMA expr
            	    {
            	    COMMA170=(Token)match(input,COMMA,FOLLOW_COMMA_in_values_func1520); if (state.failed) return retval;
            	    pushFollow(FOLLOW_expr_in_values_func1523);
            	    expr171=expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr171.getTree());

            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);


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
        return retval;
    }
    // $ANTLR end "values_func"

    public static class value_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "value"
    // MySQLParser.g:318:1: value : ( N | NUMBER | '?' | LPAREN expr RPAREN | quoted_string -> ^( QUTED_STR quoted_string ) | column_spec );
    public final MySQLParserParser.value_return value() throws RecognitionException {
        MySQLParserParser.value_return retval = new MySQLParserParser.value_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token N172=null;
        Token NUMBER173=null;
        Token char_literal174=null;
        Token LPAREN175=null;
        Token RPAREN177=null;
        MySQLParserParser.expr_return expr176 = null;

        MySQLParserParser.quoted_string_return quoted_string178 = null;

        MySQLParserParser.column_spec_return column_spec179 = null;


        CommonTree N172_tree=null;
        CommonTree NUMBER173_tree=null;
        CommonTree char_literal174_tree=null;
        CommonTree LPAREN175_tree=null;
        CommonTree RPAREN177_tree=null;
        RewriteRuleSubtreeStream stream_quoted_string=new RewriteRuleSubtreeStream(adaptor,"rule quoted_string");
        try {
            // MySQLParser.g:318:7: ( N | NUMBER | '?' | LPAREN expr RPAREN | quoted_string -> ^( QUTED_STR quoted_string ) | column_spec )
            int alt54=6;
            switch ( input.LA(1) ) {
            case N:
                {
                alt54=1;
                }
                break;
            case NUMBER:
                {
                alt54=2;
                }
                break;
            case 96:
                {
                alt54=3;
                }
                break;
            case LPAREN:
                {
                alt54=4;
                }
                break;
            case QUOTED_STRING:
            case DOUBLEQUOTED_STRING:
                {
                alt54=5;
                }
                break;
            case ASTERISK:
            case ID:
                {
                alt54=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }

            switch (alt54) {
                case 1 :
                    // MySQLParser.g:319:2: N
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    N172=(Token)match(input,N,FOLLOW_N_in_value1537); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    N172_tree = (CommonTree)adaptor.create(N172);
                    adaptor.addChild(root_0, N172_tree);
                    }

                    }
                    break;
                case 2 :
                    // MySQLParser.g:320:3: NUMBER
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NUMBER173=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value1541); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER173_tree = (CommonTree)adaptor.create(NUMBER173);
                    adaptor.addChild(root_0, NUMBER173_tree);
                    }

                    }
                    break;
                case 3 :
                    // MySQLParser.g:321:3: '?'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    char_literal174=(Token)match(input,96,FOLLOW_96_in_value1545); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal174_tree = (CommonTree)adaptor.create(char_literal174);
                    adaptor.addChild(root_0, char_literal174_tree);
                    }

                    }
                    break;
                case 4 :
                    // MySQLParser.g:322:3: LPAREN expr RPAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LPAREN175=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_value1549); if (state.failed) return retval;
                    pushFollow(FOLLOW_expr_in_value1552);
                    expr176=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr176.getTree());
                    RPAREN177=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_value1554); if (state.failed) return retval;

                    }
                    break;
                case 5 :
                    // MySQLParser.g:323:3: quoted_string
                    {
                    pushFollow(FOLLOW_quoted_string_in_value1559);
                    quoted_string178=quoted_string();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_quoted_string.add(quoted_string178.getTree());


                    // AST REWRITE
                    // elements: quoted_string
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 323:17: -> ^( QUTED_STR quoted_string )
                    {
                        // MySQLParser.g:323:19: ^( QUTED_STR quoted_string )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(QUTED_STR, "QUTED_STR"), root_1);

                        adaptor.addChild(root_1, stream_quoted_string.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 6 :
                    // MySQLParser.g:324:3: column_spec
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_column_spec_in_value1570);
                    column_spec179=column_spec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, column_spec179.getTree());

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
        return retval;
    }
    // $ANTLR end "value"

    public static class value_simple_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "value_simple"
    // MySQLParser.g:326:1: value_simple : ( N | NUMBER | '?' | LPAREN expr RPAREN | quoted_string -> ^( QUTED_STR quoted_string ) | column_spec );
    public final MySQLParserParser.value_simple_return value_simple() throws RecognitionException {
        MySQLParserParser.value_simple_return retval = new MySQLParserParser.value_simple_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token N180=null;
        Token NUMBER181=null;
        Token char_literal182=null;
        Token LPAREN183=null;
        Token RPAREN185=null;
        MySQLParserParser.expr_return expr184 = null;

        MySQLParserParser.quoted_string_return quoted_string186 = null;

        MySQLParserParser.column_spec_return column_spec187 = null;


        CommonTree N180_tree=null;
        CommonTree NUMBER181_tree=null;
        CommonTree char_literal182_tree=null;
        CommonTree LPAREN183_tree=null;
        CommonTree RPAREN185_tree=null;
        RewriteRuleSubtreeStream stream_quoted_string=new RewriteRuleSubtreeStream(adaptor,"rule quoted_string");
        try {
            // MySQLParser.g:326:14: ( N | NUMBER | '?' | LPAREN expr RPAREN | quoted_string -> ^( QUTED_STR quoted_string ) | column_spec )
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
            case 96:
                {
                alt55=3;
                }
                break;
            case LPAREN:
                {
                alt55=4;
                }
                break;
            case QUOTED_STRING:
            case DOUBLEQUOTED_STRING:
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
                    // MySQLParser.g:327:2: N
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    N180=(Token)match(input,N,FOLLOW_N_in_value_simple1580); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    N180_tree = (CommonTree)adaptor.create(N180);
                    adaptor.addChild(root_0, N180_tree);
                    }

                    }
                    break;
                case 2 :
                    // MySQLParser.g:328:3: NUMBER
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NUMBER181=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_value_simple1584); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER181_tree = (CommonTree)adaptor.create(NUMBER181);
                    adaptor.addChild(root_0, NUMBER181_tree);
                    }

                    }
                    break;
                case 3 :
                    // MySQLParser.g:329:3: '?'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    char_literal182=(Token)match(input,96,FOLLOW_96_in_value_simple1588); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal182_tree = (CommonTree)adaptor.create(char_literal182);
                    adaptor.addChild(root_0, char_literal182_tree);
                    }

                    }
                    break;
                case 4 :
                    // MySQLParser.g:330:3: LPAREN expr RPAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LPAREN183=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_value_simple1592); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN183_tree = (CommonTree)adaptor.create(LPAREN183);
                    adaptor.addChild(root_0, LPAREN183_tree);
                    }
                    pushFollow(FOLLOW_expr_in_value_simple1594);
                    expr184=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr184.getTree());
                    RPAREN185=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_value_simple1596); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN185_tree = (CommonTree)adaptor.create(RPAREN185);
                    adaptor.addChild(root_0, RPAREN185_tree);
                    }

                    }
                    break;
                case 5 :
                    // MySQLParser.g:331:3: quoted_string
                    {
                    pushFollow(FOLLOW_quoted_string_in_value_simple1600);
                    quoted_string186=quoted_string();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_quoted_string.add(quoted_string186.getTree());


                    // AST REWRITE
                    // elements: quoted_string
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 331:17: -> ^( QUTED_STR quoted_string )
                    {
                        // MySQLParser.g:331:19: ^( QUTED_STR quoted_string )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(QUTED_STR, "QUTED_STR"), root_1);

                        adaptor.addChild(root_1, stream_quoted_string.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 6 :
                    // MySQLParser.g:332:3: column_spec
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_column_spec_in_value_simple1611);
                    column_spec187=column_spec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, column_spec187.getTree());

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
        return retval;
    }
    // $ANTLR end "value_simple"

    public static class column_specs_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "column_specs"
    // MySQLParser.g:335:1: column_specs : column_spec ( COMMA column_spec )* -> ^( COLUMNS ( column_spec )+ ) ;
    public final MySQLParserParser.column_specs_return column_specs() throws RecognitionException {
        MySQLParserParser.column_specs_return retval = new MySQLParserParser.column_specs_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA189=null;
        MySQLParserParser.column_spec_return column_spec188 = null;

        MySQLParserParser.column_spec_return column_spec190 = null;


        CommonTree COMMA189_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_column_spec=new RewriteRuleSubtreeStream(adaptor,"rule column_spec");
        try {
            // MySQLParser.g:336:2: ( column_spec ( COMMA column_spec )* -> ^( COLUMNS ( column_spec )+ ) )
            // MySQLParser.g:336:4: column_spec ( COMMA column_spec )*
            {
            pushFollow(FOLLOW_column_spec_in_column_specs1622);
            column_spec188=column_spec();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_column_spec.add(column_spec188.getTree());
            // MySQLParser.g:336:16: ( COMMA column_spec )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==COMMA) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // MySQLParser.g:336:18: COMMA column_spec
            	    {
            	    COMMA189=(Token)match(input,COMMA,FOLLOW_COMMA_in_column_specs1626); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA189);

            	    pushFollow(FOLLOW_column_spec_in_column_specs1628);
            	    column_spec190=column_spec();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_column_spec.add(column_spec190.getTree());

            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);



            // AST REWRITE
            // elements: column_spec
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 336:38: -> ^( COLUMNS ( column_spec )+ )
            {
                // MySQLParser.g:336:40: ^( COLUMNS ( column_spec )+ )
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

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "column_specs"

    public static class selected_table_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selected_table"
    // MySQLParser.g:339:1: selected_table : a_table ( COMMA a_table )* -> ^( TABLENAMES ( a_table )+ ) ;
    public final MySQLParserParser.selected_table_return selected_table() throws RecognitionException {
        MySQLParserParser.selected_table_return retval = new MySQLParserParser.selected_table_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA192=null;
        MySQLParserParser.a_table_return a_table191 = null;

        MySQLParserParser.a_table_return a_table193 = null;


        CommonTree COMMA192_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_a_table=new RewriteRuleSubtreeStream(adaptor,"rule a_table");
        try {
            // MySQLParser.g:340:2: ( a_table ( COMMA a_table )* -> ^( TABLENAMES ( a_table )+ ) )
            // MySQLParser.g:340:3: a_table ( COMMA a_table )*
            {
            pushFollow(FOLLOW_a_table_in_selected_table1649);
            a_table191=a_table();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_a_table.add(a_table191.getTree());
            // MySQLParser.g:340:11: ( COMMA a_table )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==COMMA) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // MySQLParser.g:340:12: COMMA a_table
            	    {
            	    COMMA192=(Token)match(input,COMMA,FOLLOW_COMMA_in_selected_table1652); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA192);

            	    pushFollow(FOLLOW_a_table_in_selected_table1654);
            	    a_table193=a_table();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_a_table.add(a_table193.getTree());

            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);



            // AST REWRITE
            // elements: a_table
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 340:27: -> ^( TABLENAMES ( a_table )+ )
            {
                // MySQLParser.g:340:29: ^( TABLENAMES ( a_table )+ )
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

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "selected_table"

    public static class a_table_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "a_table"
    // MySQLParser.g:342:1: a_table : table_spec ( alias )? -> ^( TABLENAME table_spec ( alias )? ) ;
    public final MySQLParserParser.a_table_return a_table() throws RecognitionException {
        MySQLParserParser.a_table_return retval = new MySQLParserParser.a_table_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.table_spec_return table_spec194 = null;

        MySQLParserParser.alias_return alias195 = null;


        RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias");
        RewriteRuleSubtreeStream stream_table_spec=new RewriteRuleSubtreeStream(adaptor,"rule table_spec");
        try {
            // MySQLParser.g:343:2: ( table_spec ( alias )? -> ^( TABLENAME table_spec ( alias )? ) )
            // MySQLParser.g:343:3: table_spec ( alias )?
            {
            pushFollow(FOLLOW_table_spec_in_a_table1672);
            table_spec194=table_spec();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_table_spec.add(table_spec194.getTree());
            // MySQLParser.g:343:15: ( alias )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==ASTERISK||LA58_0==ID||LA58_0==99) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // MySQLParser.g:343:15: alias
                    {
                    pushFollow(FOLLOW_alias_in_a_table1675);
                    alias195=alias();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_alias.add(alias195.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: table_spec, alias
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 343:21: -> ^( TABLENAME table_spec ( alias )? )
            {
                // MySQLParser.g:343:23: ^( TABLENAME table_spec ( alias )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TABLENAME, "TABLENAME"), root_1);

                adaptor.addChild(root_1, stream_table_spec.nextTree());
                // MySQLParser.g:343:46: ( alias )?
                if ( stream_alias.hasNext() ) {
                    adaptor.addChild(root_1, stream_alias.nextTree());

                }
                stream_alias.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "a_table"

    public static class table_spec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "table_spec"
    // MySQLParser.g:345:1: table_spec : ( ( schema_name DOT )? table_name | subquery );
    public final MySQLParserParser.table_spec_return table_spec() throws RecognitionException {
        MySQLParserParser.table_spec_return retval = new MySQLParserParser.table_spec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token DOT197=null;
        MySQLParserParser.schema_name_return schema_name196 = null;

        MySQLParserParser.table_name_return table_name198 = null;

        MySQLParserParser.subquery_return subquery199 = null;


        CommonTree DOT197_tree=null;

        try {
            // MySQLParser.g:346:2: ( ( schema_name DOT )? table_name | subquery )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==ASTERISK||LA60_0==ID) ) {
                alt60=1;
            }
            else if ( (LA60_0==LPAREN) ) {
                alt60=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }
            switch (alt60) {
                case 1 :
                    // MySQLParser.g:346:4: ( schema_name DOT )? table_name
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // MySQLParser.g:346:4: ( schema_name DOT )?
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==ASTERISK||LA59_0==ID) ) {
                        int LA59_1 = input.LA(2);

                        if ( (LA59_1==DOT) ) {
                            alt59=1;
                        }
                    }
                    switch (alt59) {
                        case 1 :
                            // MySQLParser.g:346:6: schema_name DOT
                            {
                            pushFollow(FOLLOW_schema_name_in_table_spec1697);
                            schema_name196=schema_name();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, schema_name196.getTree());
                            DOT197=(Token)match(input,DOT,FOLLOW_DOT_in_table_spec1699); if (state.failed) return retval;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_table_name_in_table_spec1704);
                    table_name198=table_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, table_name198.getTree());

                    }
                    break;
                case 2 :
                    // MySQLParser.g:347:4: subquery
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_subquery_in_table_spec1710);
                    subquery199=subquery();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, subquery199.getTree());

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
        return retval;
    }
    // $ANTLR end "table_spec"

    public static class table_name_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "table_name"
    // MySQLParser.g:350:1: table_name : identifier ;
    public final MySQLParserParser.table_name_return table_name() throws RecognitionException {
        MySQLParserParser.table_name_return retval = new MySQLParserParser.table_name_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.identifier_return identifier200 = null;



        try {
            // MySQLParser.g:351:2: ( identifier )
            // MySQLParser.g:351:3: identifier
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_identifier_in_table_name1721);
            identifier200=identifier();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier200.getTree());

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
        return retval;
    }
    // $ANTLR end "table_name"

    public static class column_spec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "column_spec"
    // MySQLParser.g:354:1: column_spec : ( table_name DOT )? identifier -> ^( COLUMN identifier ( table_name )? ) ;
    public final MySQLParserParser.column_spec_return column_spec() throws RecognitionException {
        MySQLParserParser.column_spec_return retval = new MySQLParserParser.column_spec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token DOT202=null;
        MySQLParserParser.table_name_return table_name201 = null;

        MySQLParserParser.identifier_return identifier203 = null;


        CommonTree DOT202_tree=null;
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleSubtreeStream stream_table_name=new RewriteRuleSubtreeStream(adaptor,"rule table_name");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        try {
            // MySQLParser.g:355:2: ( ( table_name DOT )? identifier -> ^( COLUMN identifier ( table_name )? ) )
            // MySQLParser.g:355:3: ( table_name DOT )? identifier
            {
            // MySQLParser.g:355:3: ( table_name DOT )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==ASTERISK||LA61_0==ID) ) {
                int LA61_1 = input.LA(2);

                if ( (LA61_1==DOT) ) {
                    alt61=1;
                }
            }
            switch (alt61) {
                case 1 :
                    // MySQLParser.g:355:4: table_name DOT
                    {
                    pushFollow(FOLLOW_table_name_in_column_spec1733);
                    table_name201=table_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_table_name.add(table_name201.getTree());
                    DOT202=(Token)match(input,DOT,FOLLOW_DOT_in_column_spec1735); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOT.add(DOT202);


                    }
                    break;

            }

            pushFollow(FOLLOW_identifier_in_column_spec1739);
            identifier203=identifier();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_identifier.add(identifier203.getTree());


            // AST REWRITE
            // elements: table_name, identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 355:32: -> ^( COLUMN identifier ( table_name )? )
            {
                // MySQLParser.g:355:34: ^( COLUMN identifier ( table_name )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(COLUMN, "COLUMN"), root_1);

                adaptor.addChild(root_1, stream_identifier.nextTree());
                // MySQLParser.g:355:54: ( table_name )?
                if ( stream_table_name.hasNext() ) {
                    adaptor.addChild(root_1, stream_table_name.nextTree());

                }
                stream_table_name.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "column_spec"

    public static class schema_name_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "schema_name"
    // MySQLParser.g:358:1: schema_name : identifier ;
    public final MySQLParserParser.schema_name_return schema_name() throws RecognitionException {
        MySQLParserParser.schema_name_return retval = new MySQLParserParser.schema_name_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.identifier_return identifier204 = null;



        try {
            // MySQLParser.g:359:2: ( identifier )
            // MySQLParser.g:359:3: identifier
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_identifier_in_schema_name1759);
            identifier204=identifier();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier204.getTree());

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
        return retval;
    }
    // $ANTLR end "schema_name"

    public static class boolean_literal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "boolean_literal"
    // MySQLParser.g:362:1: boolean_literal : ( 'TRUE' | 'FALSE' );
    public final MySQLParserParser.boolean_literal_return boolean_literal() throws RecognitionException {
        MySQLParserParser.boolean_literal_return retval = new MySQLParserParser.boolean_literal_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set205=null;

        CommonTree set205_tree=null;

        try {
            // MySQLParser.g:363:2: ( 'TRUE' | 'FALSE' )
            // MySQLParser.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set205=(Token)input.LT(1);
            if ( (input.LA(1)>=97 && input.LA(1)<=98) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set205));
                state.errorRecovery=false;state.failed=false;
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
        return retval;
    }
    // $ANTLR end "boolean_literal"

    public static class alias_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "alias"
    // MySQLParser.g:366:1: alias : ( 'AS' )? identifier -> ^( AS identifier ) ;
    public final MySQLParserParser.alias_return alias() throws RecognitionException {
        MySQLParserParser.alias_return retval = new MySQLParserParser.alias_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal206=null;
        MySQLParserParser.identifier_return identifier207 = null;


        CommonTree string_literal206_tree=null;
        RewriteRuleTokenStream stream_99=new RewriteRuleTokenStream(adaptor,"token 99");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        try {
            // MySQLParser.g:367:2: ( ( 'AS' )? identifier -> ^( AS identifier ) )
            // MySQLParser.g:367:4: ( 'AS' )? identifier
            {
            // MySQLParser.g:367:4: ( 'AS' )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==99) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // MySQLParser.g:367:6: 'AS'
                    {
                    string_literal206=(Token)match(input,99,FOLLOW_99_in_alias1789); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_99.add(string_literal206);


                    }
                    break;

            }

            pushFollow(FOLLOW_identifier_in_alias1793);
            identifier207=identifier();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_identifier.add(identifier207.getTree());


            // AST REWRITE
            // elements: identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 367:23: -> ^( AS identifier )
            {
                // MySQLParser.g:367:25: ^( AS identifier )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(AS, "AS"), root_1);

                adaptor.addChild(root_1, stream_identifier.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "alias"

    public static class identifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "identifier"
    // MySQLParser.g:370:1: identifier : ( ID | ASTERISK );
    public final MySQLParserParser.identifier_return identifier() throws RecognitionException {
        MySQLParserParser.identifier_return retval = new MySQLParserParser.identifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set208=null;

        CommonTree set208_tree=null;

        try {
            // MySQLParser.g:371:2: ( ID | ASTERISK )
            // MySQLParser.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set208=(Token)input.LT(1);
            if ( input.LA(1)==ASTERISK||input.LA(1)==ID ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set208));
                state.errorRecovery=false;state.failed=false;
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
        return retval;
    }
    // $ANTLR end "identifier"

    public static class quoted_string_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "quoted_string"
    // MySQLParser.g:455:1: quoted_string : ( QUOTED_STRING | DOUBLEQUOTED_STRING );
    public final MySQLParserParser.quoted_string_return quoted_string() throws RecognitionException {
        MySQLParserParser.quoted_string_return retval = new MySQLParserParser.quoted_string_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set209=null;

        CommonTree set209_tree=null;

        try {
            // MySQLParser.g:456:2: ( QUOTED_STRING | DOUBLEQUOTED_STRING )
            // MySQLParser.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set209=(Token)input.LT(1);
            if ( (input.LA(1)>=QUOTED_STRING && input.LA(1)<=DOUBLEQUOTED_STRING) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set209));
                state.errorRecovery=false;state.failed=false;
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
        return retval;
    }
    // $ANTLR end "quoted_string"

    public static class select_command_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "select_command"
    // MySQLParser.g:482:1: select_command : selectClause ( fromClause )? ( joinClause )* ( whereClause )? ( groupByClause )? ( orderByClause )? ( limitClause )? ( indexClause )? ;
    public final MySQLParserParser.select_command_return select_command() throws RecognitionException {
        MySQLParserParser.select_command_return retval = new MySQLParserParser.select_command_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.selectClause_return selectClause210 = null;

        MySQLParserParser.fromClause_return fromClause211 = null;

        MySQLParserParser.joinClause_return joinClause212 = null;

        MySQLParserParser.whereClause_return whereClause213 = null;

        MySQLParserParser.groupByClause_return groupByClause214 = null;

        MySQLParserParser.orderByClause_return orderByClause215 = null;

        MySQLParserParser.limitClause_return limitClause216 = null;

        MySQLParserParser.indexClause_return indexClause217 = null;



        try {
            // MySQLParser.g:483:6: ( selectClause ( fromClause )? ( joinClause )* ( whereClause )? ( groupByClause )? ( orderByClause )? ( limitClause )? ( indexClause )? )
            // MySQLParser.g:483:8: selectClause ( fromClause )? ( joinClause )* ( whereClause )? ( groupByClause )? ( orderByClause )? ( limitClause )? ( indexClause )?
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_selectClause_in_select_command2342);
            selectClause210=selectClause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, selectClause210.getTree());
            // MySQLParser.g:483:21: ( fromClause )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==95) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // MySQLParser.g:483:22: fromClause
                    {
                    pushFollow(FOLLOW_fromClause_in_select_command2345);
                    fromClause211=fromClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fromClause211.getTree());

                    }
                    break;

            }

            // MySQLParser.g:483:35: ( joinClause )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( ((LA64_0>=108 && LA64_0<=111)) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // MySQLParser.g:483:36: joinClause
            	    {
            	    pushFollow(FOLLOW_joinClause_in_select_command2350);
            	    joinClause212=joinClause();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, joinClause212.getTree());

            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);

            // MySQLParser.g:483:49: ( whereClause )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==83) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // MySQLParser.g:483:50: whereClause
                    {
                    pushFollow(FOLLOW_whereClause_in_select_command2355);
                    whereClause213=whereClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, whereClause213.getTree());

                    }
                    break;

            }

            // MySQLParser.g:483:64: ( groupByClause )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==100) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // MySQLParser.g:483:65: groupByClause
                    {
                    pushFollow(FOLLOW_groupByClause_in_select_command2360);
                    groupByClause214=groupByClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, groupByClause214.getTree());

                    }
                    break;

            }

            // MySQLParser.g:483:81: ( orderByClause )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==80) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // MySQLParser.g:483:82: orderByClause
                    {
                    pushFollow(FOLLOW_orderByClause_in_select_command2365);
                    orderByClause215=orderByClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, orderByClause215.getTree());

                    }
                    break;

            }

            // MySQLParser.g:483:98: ( limitClause )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==106) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // MySQLParser.g:483:99: limitClause
                    {
                    pushFollow(FOLLOW_limitClause_in_select_command2370);
                    limitClause216=limitClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, limitClause216.getTree());

                    }
                    break;

            }

            // MySQLParser.g:483:113: ( indexClause )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==101||LA69_0==103) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // MySQLParser.g:483:113: indexClause
                    {
                    pushFollow(FOLLOW_indexClause_in_select_command2374);
                    indexClause217=indexClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, indexClause217.getTree());

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
        return retval;
    }
    // $ANTLR end "select_command"

    public static class groupByClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "groupByClause"
    // MySQLParser.g:485:1: groupByClause : 'GROUP BY' groupByColumns -> ^( GROUPBY groupByColumns ) ;
    public final MySQLParserParser.groupByClause_return groupByClause() throws RecognitionException {
        MySQLParserParser.groupByClause_return retval = new MySQLParserParser.groupByClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal218=null;
        MySQLParserParser.groupByColumns_return groupByColumns219 = null;


        CommonTree string_literal218_tree=null;
        RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
        RewriteRuleSubtreeStream stream_groupByColumns=new RewriteRuleSubtreeStream(adaptor,"rule groupByColumns");
        try {
            // MySQLParser.g:486:2: ( 'GROUP BY' groupByColumns -> ^( GROUPBY groupByColumns ) )
            // MySQLParser.g:486:4: 'GROUP BY' groupByColumns
            {
            string_literal218=(Token)match(input,100,FOLLOW_100_in_groupByClause2389); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_100.add(string_literal218);

            pushFollow(FOLLOW_groupByColumns_in_groupByClause2391);
            groupByColumns219=groupByColumns();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_groupByColumns.add(groupByColumns219.getTree());


            // AST REWRITE
            // elements: groupByColumns
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 486:29: -> ^( GROUPBY groupByColumns )
            {
                // MySQLParser.g:486:31: ^( GROUPBY groupByColumns )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(GROUPBY, "GROUPBY"), root_1);

                adaptor.addChild(root_1, stream_groupByColumns.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "groupByClause"

    public static class groupByColumns_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "groupByColumns"
    // MySQLParser.g:488:1: groupByColumns : groupByColumn ( COMMA groupByColumn )* ;
    public final MySQLParserParser.groupByColumns_return groupByColumns() throws RecognitionException {
        MySQLParserParser.groupByColumns_return retval = new MySQLParserParser.groupByColumns_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA221=null;
        MySQLParserParser.groupByColumn_return groupByColumn220 = null;

        MySQLParserParser.groupByColumn_return groupByColumn222 = null;


        CommonTree COMMA221_tree=null;

        try {
            // MySQLParser.g:489:2: ( groupByColumn ( COMMA groupByColumn )* )
            // MySQLParser.g:489:4: groupByColumn ( COMMA groupByColumn )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_groupByColumn_in_groupByColumns2407);
            groupByColumn220=groupByColumn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, groupByColumn220.getTree());
            // MySQLParser.g:489:18: ( COMMA groupByColumn )*
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( (LA70_0==COMMA) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // MySQLParser.g:489:19: COMMA groupByColumn
            	    {
            	    COMMA221=(Token)match(input,COMMA,FOLLOW_COMMA_in_groupByColumns2410); if (state.failed) return retval;
            	    pushFollow(FOLLOW_groupByColumn_in_groupByColumns2413);
            	    groupByColumn222=groupByColumn();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, groupByColumn222.getTree());

            	    }
            	    break;

            	default :
            	    break loop70;
                }
            } while (true);


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
        return retval;
    }
    // $ANTLR end "groupByColumns"

    public static class groupByColumn_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "groupByColumn"
    // MySQLParser.g:491:1: groupByColumn : identifier ;
    public final MySQLParserParser.groupByColumn_return groupByColumn() throws RecognitionException {
        MySQLParserParser.groupByColumn_return retval = new MySQLParserParser.groupByColumn_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.identifier_return identifier223 = null;



        try {
            // MySQLParser.g:492:2: ( identifier )
            // MySQLParser.g:492:4: identifier
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_identifier_in_groupByColumn2425);
            identifier223=identifier();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier223.getTree());

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
        return retval;
    }
    // $ANTLR end "groupByColumn"

    public static class indexClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "indexClause"
    // MySQLParser.g:494:2: indexClause : ( 'FORCE' 'INDEX' LPAREN select_list RPAREN | 'IGNORE' 'INDEX' LPAREN select_list RPAREN );
    public final MySQLParserParser.indexClause_return indexClause() throws RecognitionException {
        MySQLParserParser.indexClause_return retval = new MySQLParserParser.indexClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal224=null;
        Token string_literal225=null;
        Token LPAREN226=null;
        Token RPAREN228=null;
        Token string_literal229=null;
        Token string_literal230=null;
        Token LPAREN231=null;
        Token RPAREN233=null;
        MySQLParserParser.select_list_return select_list227 = null;

        MySQLParserParser.select_list_return select_list232 = null;


        CommonTree string_literal224_tree=null;
        CommonTree string_literal225_tree=null;
        CommonTree LPAREN226_tree=null;
        CommonTree RPAREN228_tree=null;
        CommonTree string_literal229_tree=null;
        CommonTree string_literal230_tree=null;
        CommonTree LPAREN231_tree=null;
        CommonTree RPAREN233_tree=null;

        try {
            // MySQLParser.g:495:3: ( 'FORCE' 'INDEX' LPAREN select_list RPAREN | 'IGNORE' 'INDEX' LPAREN select_list RPAREN )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==101) ) {
                alt71=1;
            }
            else if ( (LA71_0==103) ) {
                alt71=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }
            switch (alt71) {
                case 1 :
                    // MySQLParser.g:495:4: 'FORCE' 'INDEX' LPAREN select_list RPAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    string_literal224=(Token)match(input,101,FOLLOW_101_in_indexClause2436); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal224_tree = (CommonTree)adaptor.create(string_literal224);
                    adaptor.addChild(root_0, string_literal224_tree);
                    }
                    string_literal225=(Token)match(input,102,FOLLOW_102_in_indexClause2438); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal225_tree = (CommonTree)adaptor.create(string_literal225);
                    adaptor.addChild(root_0, string_literal225_tree);
                    }
                    LPAREN226=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_indexClause2440); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN226_tree = (CommonTree)adaptor.create(LPAREN226);
                    adaptor.addChild(root_0, LPAREN226_tree);
                    }
                    pushFollow(FOLLOW_select_list_in_indexClause2442);
                    select_list227=select_list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, select_list227.getTree());
                    RPAREN228=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_indexClause2445); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN228_tree = (CommonTree)adaptor.create(RPAREN228);
                    adaptor.addChild(root_0, RPAREN228_tree);
                    }

                    }
                    break;
                case 2 :
                    // MySQLParser.g:496:4: 'IGNORE' 'INDEX' LPAREN select_list RPAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    string_literal229=(Token)match(input,103,FOLLOW_103_in_indexClause2450); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal229_tree = (CommonTree)adaptor.create(string_literal229);
                    adaptor.addChild(root_0, string_literal229_tree);
                    }
                    string_literal230=(Token)match(input,102,FOLLOW_102_in_indexClause2452); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal230_tree = (CommonTree)adaptor.create(string_literal230);
                    adaptor.addChild(root_0, string_literal230_tree);
                    }
                    LPAREN231=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_indexClause2454); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN231_tree = (CommonTree)adaptor.create(LPAREN231);
                    adaptor.addChild(root_0, LPAREN231_tree);
                    }
                    pushFollow(FOLLOW_select_list_in_indexClause2456);
                    select_list232=select_list();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, select_list232.getTree());
                    RPAREN233=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_indexClause2458); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN233_tree = (CommonTree)adaptor.create(RPAREN233);
                    adaptor.addChild(root_0, RPAREN233_tree);
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
        return retval;
    }
    // $ANTLR end "indexClause"

    public static class delete_command_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "delete_command"
    // MySQLParser.g:498:1: delete_command : 'DELETE' fromClause ( whereClause )? ( orderByClause )? ( limitClause )? -> ^( DELETE fromClause ( whereClause )? ( orderByClause )? ( limitClause )? ) ;
    public final MySQLParserParser.delete_command_return delete_command() throws RecognitionException {
        MySQLParserParser.delete_command_return retval = new MySQLParserParser.delete_command_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal234=null;
        MySQLParserParser.fromClause_return fromClause235 = null;

        MySQLParserParser.whereClause_return whereClause236 = null;

        MySQLParserParser.orderByClause_return orderByClause237 = null;

        MySQLParserParser.limitClause_return limitClause238 = null;


        CommonTree string_literal234_tree=null;
        RewriteRuleTokenStream stream_104=new RewriteRuleTokenStream(adaptor,"token 104");
        RewriteRuleSubtreeStream stream_whereClause=new RewriteRuleSubtreeStream(adaptor,"rule whereClause");
        RewriteRuleSubtreeStream stream_limitClause=new RewriteRuleSubtreeStream(adaptor,"rule limitClause");
        RewriteRuleSubtreeStream stream_orderByClause=new RewriteRuleSubtreeStream(adaptor,"rule orderByClause");
        RewriteRuleSubtreeStream stream_fromClause=new RewriteRuleSubtreeStream(adaptor,"rule fromClause");
        try {
            // MySQLParser.g:499:2: ( 'DELETE' fromClause ( whereClause )? ( orderByClause )? ( limitClause )? -> ^( DELETE fromClause ( whereClause )? ( orderByClause )? ( limitClause )? ) )
            // MySQLParser.g:499:3: 'DELETE' fromClause ( whereClause )? ( orderByClause )? ( limitClause )?
            {
            string_literal234=(Token)match(input,104,FOLLOW_104_in_delete_command2468); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_104.add(string_literal234);

            pushFollow(FOLLOW_fromClause_in_delete_command2470);
            fromClause235=fromClause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_fromClause.add(fromClause235.getTree());
            // MySQLParser.g:499:23: ( whereClause )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==83) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // MySQLParser.g:499:23: whereClause
                    {
                    pushFollow(FOLLOW_whereClause_in_delete_command2472);
                    whereClause236=whereClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_whereClause.add(whereClause236.getTree());

                    }
                    break;

            }

            // MySQLParser.g:499:36: ( orderByClause )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==80) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // MySQLParser.g:499:36: orderByClause
                    {
                    pushFollow(FOLLOW_orderByClause_in_delete_command2475);
                    orderByClause237=orderByClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orderByClause.add(orderByClause237.getTree());

                    }
                    break;

            }

            // MySQLParser.g:499:52: ( limitClause )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==106) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // MySQLParser.g:499:53: limitClause
                    {
                    pushFollow(FOLLOW_limitClause_in_delete_command2480);
                    limitClause238=limitClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_limitClause.add(limitClause238.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: fromClause, limitClause, orderByClause, whereClause
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 499:66: -> ^( DELETE fromClause ( whereClause )? ( orderByClause )? ( limitClause )? )
            {
                // MySQLParser.g:499:68: ^( DELETE fromClause ( whereClause )? ( orderByClause )? ( limitClause )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DELETE, "DELETE"), root_1);

                adaptor.addChild(root_1, stream_fromClause.nextTree());
                // MySQLParser.g:499:88: ( whereClause )?
                if ( stream_whereClause.hasNext() ) {
                    adaptor.addChild(root_1, stream_whereClause.nextTree());

                }
                stream_whereClause.reset();
                // MySQLParser.g:499:101: ( orderByClause )?
                if ( stream_orderByClause.hasNext() ) {
                    adaptor.addChild(root_1, stream_orderByClause.nextTree());

                }
                stream_orderByClause.reset();
                // MySQLParser.g:499:116: ( limitClause )?
                if ( stream_limitClause.hasNext() ) {
                    adaptor.addChild(root_1, stream_limitClause.nextTree());

                }
                stream_limitClause.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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

        return retval;
    }
    // $ANTLR end "delete_command"

    public static class update_command_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "update_command"
    // MySQLParser.g:501:1: update_command : 'UPDATE' selected_table setclause ( whereClause )? ( limitClause )? -> ^( UPDATE selected_table setclause ( whereClause )? ( limitClause )? ) ;
    public final MySQLParserParser.update_command_return update_command() throws RecognitionException {
        MySQLParserParser.update_command_return retval = new MySQLParserParser.update_command_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal239=null;
        MySQLParserParser.selected_table_return selected_table240 = null;

        MySQLParserParser.setclause_return setclause241 = null;

        MySQLParserParser.whereClause_return whereClause242 = null;

        MySQLParserParser.limitClause_return limitClause243 = null;


        CommonTree string_literal239_tree=null;
        RewriteRuleTokenStream stream_105=new RewriteRuleTokenStream(adaptor,"token 105");
        RewriteRuleSubtreeStream stream_whereClause=new RewriteRuleSubtreeStream(adaptor,"rule whereClause");
        RewriteRuleSubtreeStream stream_selected_table=new RewriteRuleSubtreeStream(adaptor,"rule selected_table");
        RewriteRuleSubtreeStream stream_limitClause=new RewriteRuleSubtreeStream(adaptor,"rule limitClause");
        RewriteRuleSubtreeStream stream_setclause=new RewriteRuleSubtreeStream(adaptor,"rule setclause");
        try {
            // MySQLParser.g:502:2: ( 'UPDATE' selected_table setclause ( whereClause )? ( limitClause )? -> ^( UPDATE selected_table setclause ( whereClause )? ( limitClause )? ) )
            // MySQLParser.g:502:3: 'UPDATE' selected_table setclause ( whereClause )? ( limitClause )?
            {
            string_literal239=(Token)match(input,105,FOLLOW_105_in_update_command2506); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_105.add(string_literal239);

            pushFollow(FOLLOW_selected_table_in_update_command2508);
            selected_table240=selected_table();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_selected_table.add(selected_table240.getTree());
            pushFollow(FOLLOW_setclause_in_update_command2511);
            setclause241=setclause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_setclause.add(setclause241.getTree());
            // MySQLParser.g:502:38: ( whereClause )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==83) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // MySQLParser.g:502:38: whereClause
                    {
                    pushFollow(FOLLOW_whereClause_in_update_command2513);
                    whereClause242=whereClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_whereClause.add(whereClause242.getTree());

                    }
                    break;

            }

            // MySQLParser.g:502:52: ( limitClause )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==106) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // MySQLParser.g:502:53: limitClause
                    {
                    pushFollow(FOLLOW_limitClause_in_update_command2518);
                    limitClause243=limitClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_limitClause.add(limitClause243.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: selected_table, setclause, whereClause, limitClause
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 502:66: -> ^( UPDATE selected_table setclause ( whereClause )? ( limitClause )? )
            {
                // MySQLParser.g:502:68: ^( UPDATE selected_table setclause ( whereClause )? ( limitClause )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UPDATE, "UPDATE"), root_1);

                adaptor.addChild(root_1, stream_selected_table.nextTree());
                adaptor.addChild(root_1, stream_setclause.nextTree());
                // MySQLParser.g:502:102: ( whereClause )?
                if ( stream_whereClause.hasNext() ) {
                    adaptor.addChild(root_1, stream_whereClause.nextTree());

                }
                stream_whereClause.reset();
                // MySQLParser.g:502:115: ( limitClause )?
                if ( stream_limitClause.hasNext() ) {
                    adaptor.addChild(root_1, stream_limitClause.nextTree());

                }
                stream_limitClause.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "update_command"

    public static class limitClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "limitClause"
    // MySQLParser.g:504:1: limitClause : 'LIMIT' ( skip COMMA )? range -> ^( 'LIMIT' ( skip )? range ) ;
    public final MySQLParserParser.limitClause_return limitClause() throws RecognitionException {
        MySQLParserParser.limitClause_return retval = new MySQLParserParser.limitClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal244=null;
        Token COMMA246=null;
        MySQLParserParser.skip_return skip245 = null;

        MySQLParserParser.range_return range247 = null;


        CommonTree string_literal244_tree=null;
        CommonTree COMMA246_tree=null;
        RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_range=new RewriteRuleSubtreeStream(adaptor,"rule range");
        RewriteRuleSubtreeStream stream_skip=new RewriteRuleSubtreeStream(adaptor,"rule skip");
        try {
            // MySQLParser.g:505:2: ( 'LIMIT' ( skip COMMA )? range -> ^( 'LIMIT' ( skip )? range ) )
            // MySQLParser.g:505:3: 'LIMIT' ( skip COMMA )? range
            {
            string_literal244=(Token)match(input,106,FOLLOW_106_in_limitClause2543); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_106.add(string_literal244);

            // MySQLParser.g:505:11: ( skip COMMA )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==N) ) {
                int LA77_1 = input.LA(2);

                if ( (LA77_1==COMMA) ) {
                    alt77=1;
                }
            }
            else if ( (LA77_0==96) ) {
                int LA77_2 = input.LA(2);

                if ( (LA77_2==COMMA) ) {
                    alt77=1;
                }
            }
            switch (alt77) {
                case 1 :
                    // MySQLParser.g:505:12: skip COMMA
                    {
                    pushFollow(FOLLOW_skip_in_limitClause2546);
                    skip245=skip();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_skip.add(skip245.getTree());
                    COMMA246=(Token)match(input,COMMA,FOLLOW_COMMA_in_limitClause2548); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA246);


                    }
                    break;

            }

            pushFollow(FOLLOW_range_in_limitClause2553);
            range247=range();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_range.add(range247.getTree());


            // AST REWRITE
            // elements: skip, 106, range
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 505:31: -> ^( 'LIMIT' ( skip )? range )
            {
                // MySQLParser.g:505:33: ^( 'LIMIT' ( skip )? range )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_106.nextNode(), root_1);

                // MySQLParser.g:505:43: ( skip )?
                if ( stream_skip.hasNext() ) {
                    adaptor.addChild(root_1, stream_skip.nextTree());

                }
                stream_skip.reset();
                adaptor.addChild(root_1, stream_range.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "limitClause"

    public static class skip_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "skip"
    // MySQLParser.g:507:1: skip : ( N -> ^( SKIP N ) | '?' -> ^( SKIP '?' ) );
    public final MySQLParserParser.skip_return skip() throws RecognitionException {
        MySQLParserParser.skip_return retval = new MySQLParserParser.skip_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token N248=null;
        Token char_literal249=null;

        CommonTree N248_tree=null;
        CommonTree char_literal249_tree=null;
        RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
        RewriteRuleTokenStream stream_N=new RewriteRuleTokenStream(adaptor,"token N");

        try {
            // MySQLParser.g:508:2: ( N -> ^( SKIP N ) | '?' -> ^( SKIP '?' ) )
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==N) ) {
                alt78=1;
            }
            else if ( (LA78_0==96) ) {
                alt78=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;
            }
            switch (alt78) {
                case 1 :
                    // MySQLParser.g:508:3: N
                    {
                    N248=(Token)match(input,N,FOLLOW_N_in_skip2572); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_N.add(N248);



                    // AST REWRITE
                    // elements: N
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 508:5: -> ^( SKIP N )
                    {
                        // MySQLParser.g:508:7: ^( SKIP N )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SKIP, "SKIP"), root_1);

                        adaptor.addChild(root_1, stream_N.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // MySQLParser.g:509:3: '?'
                    {
                    char_literal249=(Token)match(input,96,FOLLOW_96_in_skip2583); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_96.add(char_literal249);



                    // AST REWRITE
                    // elements: 96
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 509:6: -> ^( SKIP '?' )
                    {
                        // MySQLParser.g:509:8: ^( SKIP '?' )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SKIP, "SKIP"), root_1);

                        adaptor.addChild(root_1, stream_96.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "skip"

    public static class range_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "range"
    // MySQLParser.g:511:1: range : ( N -> ^( RANGE N ) | '?' -> ^( RANGE '?' ) );
    public final MySQLParserParser.range_return range() throws RecognitionException {
        MySQLParserParser.range_return retval = new MySQLParserParser.range_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token N250=null;
        Token char_literal251=null;

        CommonTree N250_tree=null;
        CommonTree char_literal251_tree=null;
        RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
        RewriteRuleTokenStream stream_N=new RewriteRuleTokenStream(adaptor,"token N");

        try {
            // MySQLParser.g:511:7: ( N -> ^( RANGE N ) | '?' -> ^( RANGE '?' ) )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==N) ) {
                alt79=1;
            }
            else if ( (LA79_0==96) ) {
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
                    // MySQLParser.g:511:8: N
                    {
                    N250=(Token)match(input,N,FOLLOW_N_in_range2597); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_N.add(N250);



                    // AST REWRITE
                    // elements: N
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 511:9: -> ^( RANGE N )
                    {
                        // MySQLParser.g:511:11: ^( RANGE N )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(RANGE, "RANGE"), root_1);

                        adaptor.addChild(root_1, stream_N.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // MySQLParser.g:512:3: '?'
                    {
                    char_literal251=(Token)match(input,96,FOLLOW_96_in_range2607); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_96.add(char_literal251);



                    // AST REWRITE
                    // elements: 96
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 512:6: -> ^( RANGE '?' )
                    {
                        // MySQLParser.g:512:8: ^( RANGE '?' )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(RANGE, "RANGE"), root_1);

                        adaptor.addChild(root_1, stream_96.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "range"

    public static class joinClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "joinClause"
    // MySQLParser.g:514:1: joinClause : joinType table_spec ( alias )? 'ON' sqlCondition -> ^( joinType table_spec ( alias )? 'ON' sqlCondition ) ;
    public final MySQLParserParser.joinClause_return joinClause() throws RecognitionException {
        MySQLParserParser.joinClause_return retval = new MySQLParserParser.joinClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal255=null;
        MySQLParserParser.joinType_return joinType252 = null;

        MySQLParserParser.table_spec_return table_spec253 = null;

        MySQLParserParser.alias_return alias254 = null;

        MySQLParserParser.sqlCondition_return sqlCondition256 = null;


        CommonTree string_literal255_tree=null;
        RewriteRuleTokenStream stream_107=new RewriteRuleTokenStream(adaptor,"token 107");
        RewriteRuleSubtreeStream stream_alias=new RewriteRuleSubtreeStream(adaptor,"rule alias");
        RewriteRuleSubtreeStream stream_sqlCondition=new RewriteRuleSubtreeStream(adaptor,"rule sqlCondition");
        RewriteRuleSubtreeStream stream_joinType=new RewriteRuleSubtreeStream(adaptor,"rule joinType");
        RewriteRuleSubtreeStream stream_table_spec=new RewriteRuleSubtreeStream(adaptor,"rule table_spec");
        try {
            // MySQLParser.g:515:2: ( joinType table_spec ( alias )? 'ON' sqlCondition -> ^( joinType table_spec ( alias )? 'ON' sqlCondition ) )
            // MySQLParser.g:515:4: joinType table_spec ( alias )? 'ON' sqlCondition
            {
            pushFollow(FOLLOW_joinType_in_joinClause2623);
            joinType252=joinType();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_joinType.add(joinType252.getTree());
            pushFollow(FOLLOW_table_spec_in_joinClause2625);
            table_spec253=table_spec();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_table_spec.add(table_spec253.getTree());
            // MySQLParser.g:515:24: ( alias )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==ASTERISK||LA80_0==ID||LA80_0==99) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // MySQLParser.g:515:24: alias
                    {
                    pushFollow(FOLLOW_alias_in_joinClause2627);
                    alias254=alias();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_alias.add(alias254.getTree());

                    }
                    break;

            }

            string_literal255=(Token)match(input,107,FOLLOW_107_in_joinClause2630); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_107.add(string_literal255);

            pushFollow(FOLLOW_sqlCondition_in_joinClause2632);
            sqlCondition256=sqlCondition();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_sqlCondition.add(sqlCondition256.getTree());


            // AST REWRITE
            // elements: sqlCondition, 107, table_spec, alias, joinType
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 515:48: -> ^( joinType table_spec ( alias )? 'ON' sqlCondition )
            {
                // MySQLParser.g:515:50: ^( joinType table_spec ( alias )? 'ON' sqlCondition )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_joinType.nextNode(), root_1);

                adaptor.addChild(root_1, stream_table_spec.nextTree());
                // MySQLParser.g:515:72: ( alias )?
                if ( stream_alias.hasNext() ) {
                    adaptor.addChild(root_1, stream_alias.nextTree());

                }
                stream_alias.reset();
                adaptor.addChild(root_1, stream_107.nextNode());
                adaptor.addChild(root_1, stream_sqlCondition.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "joinClause"

    public static class joinType_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "joinType"
    // MySQLParser.g:517:1: joinType : ( 'INNER JOIN' | 'LEFT JOIN' | 'RIGHT JOIN' | 'JOIN' );
    public final MySQLParserParser.joinType_return joinType() throws RecognitionException {
        MySQLParserParser.joinType_return retval = new MySQLParserParser.joinType_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set257=null;

        CommonTree set257_tree=null;

        try {
            // MySQLParser.g:518:2: ( 'INNER JOIN' | 'LEFT JOIN' | 'RIGHT JOIN' | 'JOIN' )
            // MySQLParser.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set257=(Token)input.LT(1);
            if ( (input.LA(1)>=108 && input.LA(1)<=111) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set257));
                state.errorRecovery=false;state.failed=false;
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
        return retval;
    }
    // $ANTLR end "joinType"

    public static class concat_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "concat"
    // MySQLParser.g:520:1: concat : 'CONCAT' ;
    public final MySQLParserParser.concat_return concat() throws RecognitionException {
        MySQLParserParser.concat_return retval = new MySQLParserParser.concat_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal258=null;

        CommonTree string_literal258_tree=null;

        try {
            // MySQLParser.g:521:2: ( 'CONCAT' )
            // MySQLParser.g:521:4: 'CONCAT'
            {
            root_0 = (CommonTree)adaptor.nil();

            string_literal258=(Token)match(input,112,FOLLOW_112_in_concat2677); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal258_tree = (CommonTree)adaptor.create(string_literal258);
            adaptor.addChild(root_0, string_literal258_tree);
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
        return retval;
    }
    // $ANTLR end "concat"

    public static class identifiedOrQuotedString_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "identifiedOrQuotedString"
    // MySQLParser.g:523:1: identifiedOrQuotedString : ( ( ( table_alias )? identifier ) | quoted_string ) ;
    public final MySQLParserParser.identifiedOrQuotedString_return identifiedOrQuotedString() throws RecognitionException {
        MySQLParserParser.identifiedOrQuotedString_return retval = new MySQLParserParser.identifiedOrQuotedString_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        MySQLParserParser.table_alias_return table_alias259 = null;

        MySQLParserParser.identifier_return identifier260 = null;

        MySQLParserParser.quoted_string_return quoted_string261 = null;



        try {
            // MySQLParser.g:524:2: ( ( ( ( table_alias )? identifier ) | quoted_string ) )
            // MySQLParser.g:524:4: ( ( ( table_alias )? identifier ) | quoted_string )
            {
            root_0 = (CommonTree)adaptor.nil();

            // MySQLParser.g:524:4: ( ( ( table_alias )? identifier ) | quoted_string )
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==ASTERISK||LA82_0==ID) ) {
                alt82=1;
            }
            else if ( ((LA82_0>=QUOTED_STRING && LA82_0<=DOUBLEQUOTED_STRING)) ) {
                alt82=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;
            }
            switch (alt82) {
                case 1 :
                    // MySQLParser.g:524:6: ( ( table_alias )? identifier )
                    {
                    // MySQLParser.g:524:6: ( ( table_alias )? identifier )
                    // MySQLParser.g:524:7: ( table_alias )? identifier
                    {
                    // MySQLParser.g:524:7: ( table_alias )?
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( (LA81_0==ASTERISK||LA81_0==ID) ) {
                        int LA81_1 = input.LA(2);

                        if ( (LA81_1==DOT) ) {
                            alt81=1;
                        }
                    }
                    switch (alt81) {
                        case 1 :
                            // MySQLParser.g:524:7: table_alias
                            {
                            pushFollow(FOLLOW_table_alias_in_identifiedOrQuotedString2690);
                            table_alias259=table_alias();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, table_alias259.getTree());

                            }
                            break;

                    }

                    pushFollow(FOLLOW_identifier_in_identifiedOrQuotedString2693);
                    identifier260=identifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier260.getTree());

                    }


                    }
                    break;
                case 2 :
                    // MySQLParser.g:524:34: quoted_string
                    {
                    pushFollow(FOLLOW_quoted_string_in_identifiedOrQuotedString2698);
                    quoted_string261=quoted_string();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, quoted_string261.getTree());

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
        return retval;
    }
    // $ANTLR end "identifiedOrQuotedString"

    public static class distinct_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "distinct"
    // MySQLParser.g:526:1: distinct : 'DISTINCT' ;
    public final MySQLParserParser.distinct_return distinct() throws RecognitionException {
        MySQLParserParser.distinct_return retval = new MySQLParserParser.distinct_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal262=null;

        CommonTree string_literal262_tree=null;

        try {
            // MySQLParser.g:527:2: ( 'DISTINCT' )
            // MySQLParser.g:527:4: 'DISTINCT'
            {
            root_0 = (CommonTree)adaptor.nil();

            string_literal262=(Token)match(input,113,FOLLOW_113_in_distinct2710); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal262_tree = (CommonTree)adaptor.create(string_literal262);
            adaptor.addChild(root_0, string_literal262_tree);
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
        return retval;
    }
    // $ANTLR end "distinct"

    public static class count_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "count"
    // MySQLParser.g:529:1: count : 'COUNT' ;
    public final MySQLParserParser.count_return count() throws RecognitionException {
        MySQLParserParser.count_return retval = new MySQLParserParser.count_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal263=null;

        CommonTree string_literal263_tree=null;

        try {
            // MySQLParser.g:530:2: ( 'COUNT' )
            // MySQLParser.g:530:4: 'COUNT'
            {
            root_0 = (CommonTree)adaptor.nil();

            string_literal263=(Token)match(input,114,FOLLOW_114_in_count2720); if (state.failed) return retval;
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
        return retval;
    }
    // $ANTLR end "count"

    public static class identifierOrN_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "identifierOrN"
    // MySQLParser.g:532:1: identifierOrN : ( identifier | N ) ;
    public final MySQLParserParser.identifierOrN_return identifierOrN() throws RecognitionException {
        MySQLParserParser.identifierOrN_return retval = new MySQLParserParser.identifierOrN_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token N265=null;
        MySQLParserParser.identifier_return identifier264 = null;


        CommonTree N265_tree=null;

        try {
            // MySQLParser.g:533:2: ( ( identifier | N ) )
            // MySQLParser.g:534:2: ( identifier | N )
            {
            root_0 = (CommonTree)adaptor.nil();

            // MySQLParser.g:534:2: ( identifier | N )
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==ASTERISK||LA83_0==ID) ) {
                alt83=1;
            }
            else if ( (LA83_0==N) ) {
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
                    // MySQLParser.g:534:3: identifier
                    {
                    pushFollow(FOLLOW_identifier_in_identifierOrN2732);
                    identifier264=identifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier264.getTree());

                    }
                    break;
                case 2 :
                    // MySQLParser.g:534:16: N
                    {
                    N265=(Token)match(input,N,FOLLOW_N_in_identifierOrN2736); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    N265_tree = (CommonTree)adaptor.create(N265);
                    adaptor.addChild(root_0, N265_tree);
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
        return retval;
    }
    // $ANTLR end "identifierOrN"

    public static class countColumn_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "countColumn"
    // MySQLParser.g:536:1: countColumn : ( identifier DOT )? identifierOrN -> ^( COUNTCOLUMN ( identifier )? identifierOrN ) ;
    public final MySQLParserParser.countColumn_return countColumn() throws RecognitionException {
        MySQLParserParser.countColumn_return retval = new MySQLParserParser.countColumn_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token DOT267=null;
        MySQLParserParser.identifier_return identifier266 = null;

        MySQLParserParser.identifierOrN_return identifierOrN268 = null;


        CommonTree DOT267_tree=null;
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleSubtreeStream stream_identifierOrN=new RewriteRuleSubtreeStream(adaptor,"rule identifierOrN");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        try {
            // MySQLParser.g:537:2: ( ( identifier DOT )? identifierOrN -> ^( COUNTCOLUMN ( identifier )? identifierOrN ) )
            // MySQLParser.g:537:4: ( identifier DOT )? identifierOrN
            {
            // MySQLParser.g:537:4: ( identifier DOT )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==ASTERISK||LA84_0==ID) ) {
                int LA84_1 = input.LA(2);

                if ( (LA84_1==DOT) ) {
                    alt84=1;
                }
            }
            switch (alt84) {
                case 1 :
                    // MySQLParser.g:537:5: identifier DOT
                    {
                    pushFollow(FOLLOW_identifier_in_countColumn2748);
                    identifier266=identifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_identifier.add(identifier266.getTree());
                    DOT267=(Token)match(input,DOT,FOLLOW_DOT_in_countColumn2750); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOT.add(DOT267);


                    }
                    break;

            }

            pushFollow(FOLLOW_identifierOrN_in_countColumn2754);
            identifierOrN268=identifierOrN();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_identifierOrN.add(identifierOrN268.getTree());


            // AST REWRITE
            // elements: identifierOrN, identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 537:35: -> ^( COUNTCOLUMN ( identifier )? identifierOrN )
            {
                // MySQLParser.g:537:37: ^( COUNTCOLUMN ( identifier )? identifierOrN )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(COUNTCOLUMN, "COUNTCOLUMN"), root_1);

                // MySQLParser.g:537:51: ( identifier )?
                if ( stream_identifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_identifier.nextTree());

                }
                stream_identifier.reset();
                adaptor.addChild(root_1, stream_identifierOrN.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
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
        return retval;
    }
    // $ANTLR end "countColumn"

    // $ANTLR start synpred1_MySQLParser
    public final void synpred1_MySQLParser_fragment() throws RecognitionException {   
        // MySQLParser.g:168:3: ( LPAREN condition_or RPAREN )
        // MySQLParser.g:168:4: LPAREN condition_or RPAREN
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred1_MySQLParser565); if (state.failed) return ;
        pushFollow(FOLLOW_condition_or_in_synpred1_MySQLParser567);
        condition_or();

        state._fsp--;
        if (state.failed) return ;
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred1_MySQLParser569); if (state.failed) return ;

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


    protected DFA6 dfa6 = new DFA6(this);
    protected DFA11 dfa11 = new DFA11(this);
    static final String DFA6_eotS =
        "\17\uffff";
    static final String DFA6_eofS =
        "\17\uffff";
    static final String DFA6_minS =
        "\1\63\16\uffff";
    static final String DFA6_maxS =
        "\1\162\16\uffff";
    static final String DFA6_acceptS =
        "\1\uffff\1\1\1\2\14\uffff";
    static final String DFA6_specialS =
        "\17\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\1\1\uffff\2\2\1\uffff\1\2\3\uffff\1\2\10\uffff\2\2\2\uffff"+
            "\2\2\20\uffff\1\2\2\uffff\1\2\1\uffff\3\2\15\uffff\1\2\1\uffff"+
            "\1\2",
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

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "148:25: ( LPAREN )?";
        }
    }
    static final String DFA11_eotS =
        "\16\uffff";
    static final String DFA11_eofS =
        "\16\uffff";
    static final String DFA11_minS =
        "\1\63\1\0\14\uffff";
    static final String DFA11_maxS =
        "\1\142\1\0\14\uffff";
    static final String DFA11_acceptS =
        "\2\uffff\1\2\12\uffff\1\1";
    static final String DFA11_specialS =
        "\1\uffff\1\0\14\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1\1\uffff\2\2\1\uffff\1\2\3\uffff\1\2\10\uffff\2\2\2\uffff"+
            "\2\2\20\uffff\1\2\2\uffff\1\2\1\uffff\3\2",
            "\1\uffff",
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

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "167:1: condition_PAREN : ( ( LPAREN condition_or RPAREN )=> LPAREN condition_or RPAREN -> ^( PRIORITY condition_or ) | condition_expr );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA11_1 = input.LA(1);

                         
                        int index11_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_MySQLParser()) ) {s = 13;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index11_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 11, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_start_rule_in_beg242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_command_in_start_rule254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_update_command_in_start_rule258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_insert_command_in_start_rule262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_delete_command_in_start_rule266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_setclause278 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_updateColumnSpecs_in_setclause280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_updateColumnSpec_in_updateColumnSpecs297 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_COMMA_in_updateColumnSpecs300 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_updateColumnSpec_in_updateColumnSpecs302 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_columnNameInUpdate_in_updateColumnSpec320 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_EQ_in_updateColumnSpec322 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_in_updateColumnSpec325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_insert_command335 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_insert_command337 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_selected_table_in_insert_command339 = new BitSet(new long[]{0x0008000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_LPAREN_in_insert_command345 = new BitSet(new long[]{0x1108000000000000L,0x0000000100000660L});
    public static final BitSet FOLLOW_column_specs_in_insert_command347 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_insert_command350 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_insert_command358 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_insert_command360 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_values_in_insert_command362 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_insert_command364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_orderByClause393 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_orderByClause395 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_columnNamesAfterWhere_in_orderByClause397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere417 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_COMMA_in_columnNamesAfterWhere420 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_columnNameAfterWhere_in_columnNamesAfterWhere423 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_82_in_selectClause441 = new BitSet(new long[]{0x1168000000000000L,0x0007000748000660L});
    public static final BitSet FOLLOW_distinct_in_selectClause443 = new BitSet(new long[]{0x1168000000000000L,0x0007000748000660L});
    public static final BitSet FOLLOW_LPAREN_in_selectClause446 = new BitSet(new long[]{0x1168000000000000L,0x0007000748000660L});
    public static final BitSet FOLLOW_select_list_in_selectClause449 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_RPAREN_in_selectClause451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_whereClause476 = new BitSet(new long[]{0x1168000000000000L,0x0000000748100660L});
    public static final BitSet FOLLOW_sqlCondition_in_whereClause478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_sqlCondition494 = new BitSet(new long[]{0x1168000000000000L,0x0000000748100660L});
    public static final BitSet FOLLOW_condition_or_in_sqlCondition496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_or_in_sqlCondition507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_and_in_condition_or525 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_condition_or529 = new BitSet(new long[]{0x1168000000000000L,0x0000000748100660L});
    public static final BitSet FOLLOW_condition_and_in_condition_or532 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_condition_PAREN_in_condition_and545 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_condition_and549 = new BitSet(new long[]{0x1168000000000000L,0x0000000748100660L});
    public static final BitSet FOLLOW_condition_PAREN_in_condition_and552 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_LPAREN_in_condition_PAREN572 = new BitSet(new long[]{0x1168000000000000L,0x0000000748100660L});
    public static final BitSet FOLLOW_condition_or_in_condition_PAREN574 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_condition_PAREN576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_expr_in_condition_PAREN586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_left_in_condition_expr596 = new BitSet(new long[]{0xE004000000000000L,0x0000000031900003L});
    public static final BitSet FOLLOW_comparisonCondition_in_condition_expr601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inCondition_in_condition_expr607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isCondition_in_condition_expr613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_likeCondition_in_condition_expr619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_betweenCondition_in_condition_expr625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_condition_left639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_betweenCondition655 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_betweenCondition657 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_between_and_in_betweenCondition659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_betweenCondition670 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_between_and_in_betweenCondition672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_and_expression_in_between_and691 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_between_and693 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_between_and_expression_in_between_and697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quoted_string_in_between_and_expression717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_add_in_between_and_expression722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_isCondition734 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_isCondition736 = new BitSet(new long[]{0x0000000000000000L,0x000000000E000000L});
    public static final BitSet FOLLOW_condition_is_valobject_in_isCondition738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_isCondition748 = new BitSet(new long[]{0x0000000000000000L,0x000000000E000000L});
    public static final BitSet FOLLOW_condition_is_valobject_in_isCondition750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_condition_is_valobject768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_condition_is_valobject776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_condition_is_valobject784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_inCondition800 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_92_in_inCondition804 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_subquery_in_inCondition807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_inCondition813 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_inCondition_expr_adds_in_inCondition815 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_inCondition817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_likeCondition844 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_93_in_likeCondition845 = new BitSet(new long[]{0x1108000000000000L,0x0000000100000660L});
    public static final BitSet FOLLOW_value_in_likeCondition848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_likeCondition858 = new BitSet(new long[]{0x1108000000000000L,0x0000000100000660L});
    public static final BitSet FOLLOW_value_in_likeCondition860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_add_in_inCondition_expr_adds877 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_COMMA_in_inCondition_expr_adds879 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_add_in_inCondition_expr_adds881 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_columnNameAfterWhere_in_identifiers901 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_COMMA_in_identifiers904 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_identifier_in_identifiers906 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_relational_op_in_comparisonCondition918 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_in_comparisonCondition920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_add_in_expr939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subquery_in_expr944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_subquery960 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_select_command_in_subquery962 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_subquery964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_mul_in_expr_add980 = new BitSet(new long[]{0x00E0000000000002L});
    public static final BitSet FOLLOW_PLUS_in_expr_add986 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_MINUS_in_expr_add991 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_DOUBLEVERTBAR_in_expr_add996 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_mul_in_expr_add1002 = new BitSet(new long[]{0x00E0000000000002L});
    public static final BitSet FOLLOW_expr_sign_in_expr_mul1022 = new BitSet(new long[]{0x0700000000000002L});
    public static final BitSet FOLLOW_ASTERISK_in_expr_mul1028 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_DIVIDE_in_expr_mul1033 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_MOD_in_expr_mul1038 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_sign_in_expr_mul1043 = new BitSet(new long[]{0x0700000000000002L});
    public static final BitSet FOLLOW_PLUS_in_expr_sign1058 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_MINUS_in_expr_sign1063 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_pow_in_expr_sign1069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_expr_in_expr_pow1079 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_EXPONENT_in_expr_pow1083 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_expr_in_expr_pow1086 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_value_in_expr_expr1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_literal_in_expr_expr1103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_expr_expr1107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_expr_expr1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expr_expr1119 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_expr_expr1123 = new BitSet(new long[]{0x1178000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_values_func_in_expr_expr1125 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_expr_expr1128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_or_in_sql_condition1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_relational_op0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_fromClause1190 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_selected_table_in_fromClause1193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_displayed_column_in_select_list1204 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_COMMA_in_select_list1208 = new BitSet(new long[]{0x1168000000000000L,0x0007000748000660L});
    public static final BitSet FOLLOW_displayed_column_in_select_list1210 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_quoted_string_in_displayed_column1233 = new BitSet(new long[]{0x1100000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_alias_in_displayed_column1235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_concat_in_displayed_column1249 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_displayed_column1251 = new BitSet(new long[]{0x1100000000000000L,0x0000000000000600L});
    public static final BitSet FOLLOW_identifiedOrQuotedString_in_displayed_column1253 = new BitSet(new long[]{0x0012000000000000L});
    public static final BitSet FOLLOW_COMMA_in_displayed_column1256 = new BitSet(new long[]{0x1100000000000000L,0x0000000000000600L});
    public static final BitSet FOLLOW_identifiedOrQuotedString_in_displayed_column1258 = new BitSet(new long[]{0x0012000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_displayed_column1262 = new BitSet(new long[]{0x1100000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_alias_in_displayed_column1264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_count_in_displayed_column1285 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_displayed_column1287 = new BitSet(new long[]{0x1108000000000000L,0x0002000000000020L});
    public static final BitSet FOLLOW_distinct_in_displayed_column1289 = new BitSet(new long[]{0x1108000000000000L,0x0002000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_displayed_column1292 = new BitSet(new long[]{0x1108000000000000L,0x0002000000000020L});
    public static final BitSet FOLLOW_countColumn_in_displayed_column1295 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_displayed_column1297 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_displayed_column1300 = new BitSet(new long[]{0x1100000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_alias_in_displayed_column1302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_add_in_displayed_column1319 = new BitSet(new long[]{0x1100000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_alias_in_displayed_column1321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_displayed_column1339 = new BitSet(new long[]{0x1108000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_LPAREN_in_displayed_column1343 = new BitSet(new long[]{0x1110000000000000L});
    public static final BitSet FOLLOW_table_alias_in_displayed_column1345 = new BitSet(new long[]{0x1110000000000000L});
    public static final BitSet FOLLOW_column_in_displayed_column1348 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_displayed_column1351 = new BitSet(new long[]{0x1100000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_alias_in_displayed_column1356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_alias_in_columnNameAfterWhere1381 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_identifier_in_columnNameAfterWhere1384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_alias_in_columnNameAfterWhere1399 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_identifier_in_columnNameAfterWhere1402 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_ASC_in_columnNameAfterWhere1405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_alias_in_columnNameAfterWhere1420 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_identifier_in_columnNameAfterWhere1423 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DESC_in_columnNameAfterWhere1426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_alias_in_columnNameInUpdate1447 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_identifier_in_columnNameInUpdate1450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_table_alias1460 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_DOT_in_table_alias1462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASTERISK_in_column1478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_column1482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_values1491 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_COMMA_in_values1495 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_in_values1497 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_expr_in_values_func1516 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_COMMA_in_values_func1520 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_in_values_func1523 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_N_in_value1537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_value1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_value1545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_value1549 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_in_value1552 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_value1554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quoted_string_in_value1559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_spec_in_value1570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_N_in_value_simple1580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_value_simple1584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_value_simple1588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_value_simple1592 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_expr_in_value_simple1594 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_value_simple1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quoted_string_in_value_simple1600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_spec_in_value_simple1611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_spec_in_column_specs1622 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_COMMA_in_column_specs1626 = new BitSet(new long[]{0x1108000000000000L,0x0000000100000660L});
    public static final BitSet FOLLOW_column_spec_in_column_specs1628 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_a_table_in_selected_table1649 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_COMMA_in_selected_table1652 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_a_table_in_selected_table1654 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_table_spec_in_a_table1672 = new BitSet(new long[]{0x1100000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_alias_in_a_table1675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_schema_name_in_table_spec1697 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_DOT_in_table_spec1699 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_table_name_in_table_spec1704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subquery_in_table_spec1710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_table_name1721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_name_in_column_spec1733 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_DOT_in_column_spec1735 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_identifier_in_column_spec1739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_schema_name1759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_boolean_literal0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_alias1789 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_identifier_in_alias1793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_identifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_quoted_string0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selectClause_in_select_command2342 = new BitSet(new long[]{0x0000000000000002L,0x0000F4B080090000L});
    public static final BitSet FOLLOW_fromClause_in_select_command2345 = new BitSet(new long[]{0x0000000000000002L,0x0000F4B000090000L});
    public static final BitSet FOLLOW_joinClause_in_select_command2350 = new BitSet(new long[]{0x0000000000000002L,0x0000F4B000090000L});
    public static final BitSet FOLLOW_whereClause_in_select_command2355 = new BitSet(new long[]{0x0000000000000002L,0x000004B000010000L});
    public static final BitSet FOLLOW_groupByClause_in_select_command2360 = new BitSet(new long[]{0x0000000000000002L,0x000004A000010000L});
    public static final BitSet FOLLOW_orderByClause_in_select_command2365 = new BitSet(new long[]{0x0000000000000002L,0x000004A000000000L});
    public static final BitSet FOLLOW_limitClause_in_select_command2370 = new BitSet(new long[]{0x0000000000000002L,0x000000A000000000L});
    public static final BitSet FOLLOW_indexClause_in_select_command2374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_groupByClause2389 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_groupByColumns_in_groupByClause2391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupByColumn_in_groupByColumns2407 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_COMMA_in_groupByColumns2410 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_groupByColumn_in_groupByColumns2413 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_identifier_in_groupByColumn2425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_101_in_indexClause2436 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_102_in_indexClause2438 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_indexClause2440 = new BitSet(new long[]{0x1168000000000000L,0x0007000748000660L});
    public static final BitSet FOLLOW_select_list_in_indexClause2442 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_indexClause2445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_indexClause2450 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_102_in_indexClause2452 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_indexClause2454 = new BitSet(new long[]{0x1168000000000000L,0x0007000748000660L});
    public static final BitSet FOLLOW_select_list_in_indexClause2456 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_indexClause2458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_104_in_delete_command2468 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_fromClause_in_delete_command2470 = new BitSet(new long[]{0x0000000000000002L,0x0000040000090000L});
    public static final BitSet FOLLOW_whereClause_in_delete_command2472 = new BitSet(new long[]{0x0000000000000002L,0x0000040000010000L});
    public static final BitSet FOLLOW_orderByClause_in_delete_command2475 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});
    public static final BitSet FOLLOW_limitClause_in_delete_command2480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_105_in_update_command2506 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_selected_table_in_update_command2508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_setclause_in_update_command2511 = new BitSet(new long[]{0x0000000000000002L,0x0000040000080000L});
    public static final BitSet FOLLOW_whereClause_in_update_command2513 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});
    public static final BitSet FOLLOW_limitClause_in_update_command2518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_limitClause2543 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000020L});
    public static final BitSet FOLLOW_skip_in_limitClause2546 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_COMMA_in_limitClause2548 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000020L});
    public static final BitSet FOLLOW_range_in_limitClause2553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_N_in_skip2572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_skip2583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_N_in_range2597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_range2607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_joinType_in_joinClause2623 = new BitSet(new long[]{0x1168000000000000L,0x0000000748000660L});
    public static final BitSet FOLLOW_table_spec_in_joinClause2625 = new BitSet(new long[]{0x1100000000000000L,0x0000080800000000L});
    public static final BitSet FOLLOW_alias_in_joinClause2627 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_107_in_joinClause2630 = new BitSet(new long[]{0x1168000000000000L,0x0000000748100660L});
    public static final BitSet FOLLOW_sqlCondition_in_joinClause2632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_joinType0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_112_in_concat2677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_alias_in_identifiedOrQuotedString2690 = new BitSet(new long[]{0x1100000000000000L});
    public static final BitSet FOLLOW_identifier_in_identifiedOrQuotedString2693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quoted_string_in_identifiedOrQuotedString2698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_distinct2710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_count2720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_identifierOrN2732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_N_in_identifierOrN2736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_countColumn2748 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_DOT_in_countColumn2750 = new BitSet(new long[]{0x1108000000000000L,0x0002000000000020L});
    public static final BitSet FOLLOW_identifierOrN_in_countColumn2754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred1_MySQLParser565 = new BitSet(new long[]{0x1168000000000000L,0x0000000748100660L});
    public static final BitSet FOLLOW_condition_or_in_synpred1_MySQLParser567 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred1_MySQLParser569 = new BitSet(new long[]{0x0000000000000002L});

}