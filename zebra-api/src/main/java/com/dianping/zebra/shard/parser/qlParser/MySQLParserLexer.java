// $ANTLR 3.3 Nov 30, 2010 12:45:30 MySQLParser.g 2011-11-11 11:16:23
 package  com.dianping.zebra.shard.parser.qlParser; 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MySQLParserLexer extends Lexer {
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

    public MySQLParserLexer() {;} 
    public MySQLParserLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public MySQLParserLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "MySQLParser.g"; }

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:9:7: ( 'SET' )
            // MySQLParser.g:9:9: 'SET'
            {
            match("SET"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:10:7: ( 'INSERT' )
            // MySQLParser.g:10:9: 'INSERT'
            {
            match("INSERT"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:11:7: ( 'INTO' )
            // MySQLParser.g:11:9: 'INTO'
            {
            match("INTO"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:12:7: ( 'VALUES' )
            // MySQLParser.g:12:9: 'VALUES'
            {
            match("VALUES"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:13:7: ( 'ORDER' )
            // MySQLParser.g:13:9: 'ORDER'
            {
            match("ORDER"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:14:7: ( 'BY' )
            // MySQLParser.g:14:9: 'BY'
            {
            match("BY"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:15:7: ( 'SELECT' )
            // MySQLParser.g:15:9: 'SELECT'
            {
            match("SELECT"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:16:7: ( 'WHERE' )
            // MySQLParser.g:16:9: 'WHERE'
            {
            match("WHERE"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:17:7: ( 'NOT' )
            // MySQLParser.g:17:9: 'NOT'
            {
            match("NOT"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:18:7: ( 'OR' )
            // MySQLParser.g:18:9: 'OR'
            {
            match("OR"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:19:7: ( 'AND' )
            // MySQLParser.g:19:9: 'AND'
            {
            match("AND"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:20:7: ( 'BETWEEN' )
            // MySQLParser.g:20:9: 'BETWEEN'
            {
            match("BETWEEN"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:21:7: ( 'IS' )
            // MySQLParser.g:21:9: 'IS'
            {
            match("IS"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:22:7: ( 'NAN' )
            // MySQLParser.g:22:9: 'NAN'
            {
            match("NAN"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:23:7: ( 'INFINITE' )
            // MySQLParser.g:23:9: 'INFINITE'
            {
            match("INFINITE"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:24:7: ( 'NULL' )
            // MySQLParser.g:24:9: 'NULL'
            {
            match("NULL"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:25:7: ( 'IN' )
            // MySQLParser.g:25:9: 'IN'
            {
            match("IN"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:26:7: ( 'LIKE' )
            // MySQLParser.g:26:9: 'LIKE'
            {
            match("LIKE"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:27:7: ( 'ROWNUM' )
            // MySQLParser.g:27:9: 'ROWNUM'
            {
            match("ROWNUM"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:28:7: ( 'FROM' )
            // MySQLParser.g:28:9: 'FROM'
            {
            match("FROM"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__95"

    // $ANTLR start "T__96"
    public final void mT__96() throws RecognitionException {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:29:7: ( '?' )
            // MySQLParser.g:29:9: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__96"

    // $ANTLR start "T__97"
    public final void mT__97() throws RecognitionException {
            int _type = T__97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:30:7: ( 'TRUE' )
            // MySQLParser.g:30:9: 'TRUE'
            {
            match("TRUE"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__97"

    // $ANTLR start "T__98"
    public final void mT__98() throws RecognitionException {
            int _type = T__98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:31:7: ( 'FALSE' )
            // MySQLParser.g:31:9: 'FALSE'
            {
            match("FALSE"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__98"

    // $ANTLR start "T__99"
    public final void mT__99() throws RecognitionException {
            int _type = T__99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:32:7: ( 'AS' )
            // MySQLParser.g:32:9: 'AS'
            {
            match("AS"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__99"

    // $ANTLR start "T__100"
    public final void mT__100() throws RecognitionException {
            int _type = T__100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:33:8: ( 'GROUP BY' )
            // MySQLParser.g:33:10: 'GROUP BY'
            {
            match("GROUP BY"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__100"

    // $ANTLR start "T__101"
    public final void mT__101() throws RecognitionException {
            int _type = T__101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:34:8: ( 'FORCE' )
            // MySQLParser.g:34:10: 'FORCE'
            {
            match("FORCE"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__101"

    // $ANTLR start "T__102"
    public final void mT__102() throws RecognitionException {
            int _type = T__102;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:35:8: ( 'INDEX' )
            // MySQLParser.g:35:10: 'INDEX'
            {
            match("INDEX"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__102"

    // $ANTLR start "T__103"
    public final void mT__103() throws RecognitionException {
            int _type = T__103;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:36:8: ( 'IGNORE' )
            // MySQLParser.g:36:10: 'IGNORE'
            {
            match("IGNORE"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__103"

    // $ANTLR start "T__104"
    public final void mT__104() throws RecognitionException {
            int _type = T__104;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:37:8: ( 'DELETE' )
            // MySQLParser.g:37:10: 'DELETE'
            {
            match("DELETE"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__104"

    // $ANTLR start "T__105"
    public final void mT__105() throws RecognitionException {
            int _type = T__105;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:38:8: ( 'UPDATE' )
            // MySQLParser.g:38:10: 'UPDATE'
            {
            match("UPDATE"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__105"

    // $ANTLR start "T__106"
    public final void mT__106() throws RecognitionException {
            int _type = T__106;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:39:8: ( 'LIMIT' )
            // MySQLParser.g:39:10: 'LIMIT'
            {
            match("LIMIT"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__106"

    // $ANTLR start "T__107"
    public final void mT__107() throws RecognitionException {
            int _type = T__107;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:40:8: ( 'ON' )
            // MySQLParser.g:40:10: 'ON'
            {
            match("ON"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__107"

    // $ANTLR start "T__108"
    public final void mT__108() throws RecognitionException {
            int _type = T__108;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:41:8: ( 'INNER JOIN' )
            // MySQLParser.g:41:10: 'INNER JOIN'
            {
            match("INNER JOIN"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__108"

    // $ANTLR start "T__109"
    public final void mT__109() throws RecognitionException {
            int _type = T__109;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:42:8: ( 'LEFT JOIN' )
            // MySQLParser.g:42:10: 'LEFT JOIN'
            {
            match("LEFT JOIN"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__109"

    // $ANTLR start "T__110"
    public final void mT__110() throws RecognitionException {
            int _type = T__110;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:43:8: ( 'RIGHT JOIN' )
            // MySQLParser.g:43:10: 'RIGHT JOIN'
            {
            match("RIGHT JOIN"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__110"

    // $ANTLR start "T__111"
    public final void mT__111() throws RecognitionException {
            int _type = T__111;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:44:8: ( 'JOIN' )
            // MySQLParser.g:44:10: 'JOIN'
            {
            match("JOIN"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__111"

    // $ANTLR start "T__112"
    public final void mT__112() throws RecognitionException {
            int _type = T__112;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:45:8: ( 'CONCAT' )
            // MySQLParser.g:45:10: 'CONCAT'
            {
            match("CONCAT"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__112"

    // $ANTLR start "T__113"
    public final void mT__113() throws RecognitionException {
            int _type = T__113;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:46:8: ( 'DISTINCT' )
            // MySQLParser.g:46:10: 'DISTINCT'
            {
            match("DISTINCT"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__113"

    // $ANTLR start "T__114"
    public final void mT__114() throws RecognitionException {
            int _type = T__114;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:47:8: ( 'COUNT' )
            // MySQLParser.g:47:10: 'COUNT'
            {
            match("COUNT"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "T__114"

    // $ANTLR start "N"
    public final void mN() throws RecognitionException {
            int _type = N;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:376:5: ( ( MINUS )? '0' .. '9' ( '0' .. '9' )* )
            // MySQLParser.g:376:6: ( MINUS )? '0' .. '9' ( '0' .. '9' )*
            {
            // MySQLParser.g:376:6: ( MINUS )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // MySQLParser.g:376:6: MINUS
                    {
                    mMINUS(); 

                    }
                    break;

            }

            matchRange('0','9'); 
            // MySQLParser.g:376:24: ( '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // MySQLParser.g:376:26: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "N"

    // $ANTLR start "ASC"
    public final void mASC() throws RecognitionException {
            int _type = ASC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:379:4: ( 'ASC' )
            // MySQLParser.g:379:5: 'ASC'
            {
            match("ASC"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "ASC"

    // $ANTLR start "DESC"
    public final void mDESC() throws RecognitionException {
            int _type = DESC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:380:5: ( 'DESC' )
            // MySQLParser.g:380:6: 'DESC'
            {
            match("DESC"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "DESC"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
            int _type = EXPONENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:382:2: ( '**' )
            // MySQLParser.g:382:4: '**'
            {
            match("**"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:385:5: ( ( 'A' .. 'Z' | 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#' )* )
            // MySQLParser.g:385:7: ( 'A' .. 'Z' | 'a' .. 'z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // MySQLParser.g:385:29: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '$' | '#' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='#' && LA3_0<='$')||(LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // MySQLParser.g:
            	    {
            	    if ( (input.LA(1)>='#' && input.LA(1)<='$')||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "ID"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:389:2: ( '+' )
            // MySQLParser.g:389:4: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:393:2: ( ( ( N '.' N ) | ( '.' N ) ) )
            // MySQLParser.g:394:2: ( ( N '.' N ) | ( '.' N ) )
            {
            // MySQLParser.g:394:2: ( ( N '.' N ) | ( '.' N ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='-'||(LA4_0>='0' && LA4_0<='9')) ) {
                alt4=1;
            }
            else if ( (LA4_0=='.') ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // MySQLParser.g:394:3: ( N '.' N )
                    {
                    // MySQLParser.g:394:3: ( N '.' N )
                    // MySQLParser.g:394:5: N '.' N
                    {
                    mN(); 
                    match('.'); 
                    mN(); 

                    }


                    }
                    break;
                case 2 :
                    // MySQLParser.g:395:3: ( '.' N )
                    {
                    // MySQLParser.g:395:3: ( '.' N )
                    // MySQLParser.g:395:4: '.' N
                    {
                    match('.'); 
                    mN(); 

                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:401:2: ( '-' )
            // MySQLParser.g:401:4: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:404:2: ( POINT )
            // MySQLParser.g:404:4: POINT
            {
            mPOINT(); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "DOT"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:407:2: ( ',' )
            // MySQLParser.g:407:4: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:410:2: ( '=' )
            // MySQLParser.g:410:4: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "EQ"

    // $ANTLR start "DIVIDE"
    public final void mDIVIDE() throws RecognitionException {
            int _type = DIVIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:414:2: ( '/' )
            // MySQLParser.g:414:4: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "DIVIDE"

    // $ANTLR start "ASTERISK"
    public final void mASTERISK() throws RecognitionException {
            int _type = ASTERISK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:417:2: ( '*' )
            // MySQLParser.g:417:4: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "ASTERISK"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:420:2: ( '%' )
            // MySQLParser.g:420:4: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "MOD"

    // $ANTLR start "ARROW"
    public final void mARROW() throws RecognitionException {
            int _type = ARROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:423:2: ( '=>' )
            // MySQLParser.g:423:4: '=>'
            {
            match("=>"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "ARROW"

    // $ANTLR start "DOUBLEVERTBAR"
    public final void mDOUBLEVERTBAR() throws RecognitionException {
            int _type = DOUBLEVERTBAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:426:2: ( '||' )
            // MySQLParser.g:426:4: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "DOUBLEVERTBAR"

    // $ANTLR start "POINT"
    public final void mPOINT() throws RecognitionException {
            // MySQLParser.g:431:2: ( '.' )
            // MySQLParser.g:431:4: '.'
            {
            match('.'); 

            }

    }
    // $ANTLR end "POINT"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:434:2: ( ')' )
            // MySQLParser.g:434:4: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:437:2: ( '(' )
            // MySQLParser.g:437:4: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "LTH"
    public final void mLTH() throws RecognitionException {
            int _type = LTH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:440:2: ( '<' )
            // MySQLParser.g:440:4: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "LTH"

    // $ANTLR start "NOT_EQ"
    public final void mNOT_EQ() throws RecognitionException {
            int _type = NOT_EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:443:2: ( '<>' | '!=' | '^=' )
            int alt5=3;
            switch ( input.LA(1) ) {
            case '<':
                {
                alt5=1;
                }
                break;
            case '!':
                {
                alt5=2;
                }
                break;
            case '^':
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // MySQLParser.g:443:4: '<>'
                    {
                    match("<>"); 


                    }
                    break;
                case 2 :
                    // MySQLParser.g:443:11: '!='
                    {
                    match("!="); 


                    }
                    break;
                case 3 :
                    // MySQLParser.g:443:18: '^='
                    {
                    match("^="); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "NOT_EQ"

    // $ANTLR start "LEQ"
    public final void mLEQ() throws RecognitionException {
            int _type = LEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:446:2: ( '<=' )
            // MySQLParser.g:446:4: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "LEQ"

    // $ANTLR start "GEQ"
    public final void mGEQ() throws RecognitionException {
            int _type = GEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:449:2: ( '>=' )
            // MySQLParser.g:449:4: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "GEQ"

    // $ANTLR start "GTH"
    public final void mGTH() throws RecognitionException {
            int _type = GTH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:452:2: ( '>' )
            // MySQLParser.g:452:4: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "GTH"

    // $ANTLR start "QUOTED_STRING"
    public final void mQUOTED_STRING() throws RecognitionException {
            int _type = QUOTED_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:460:2: ( '\\'' (~ '\\'' | '\\'\\'' )* '\\'' )
            // MySQLParser.g:460:3: '\\'' (~ '\\'' | '\\'\\'' )* '\\''
            {
            match('\''); 
            // MySQLParser.g:460:8: (~ '\\'' | '\\'\\'' )*
            loop6:
            do {
                int alt6=3;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\'') ) {
                    int LA6_1 = input.LA(2);

                    if ( (LA6_1=='\'') ) {
                        alt6=2;
                    }


                }
                else if ( ((LA6_0>='\u0000' && LA6_0<='&')||(LA6_0>='(' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // MySQLParser.g:460:9: ~ '\\''
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;
            	case 2 :
            	    // MySQLParser.g:460:15: '\\'\\''
            	    {
            	    match("''"); 


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "QUOTED_STRING"

    // $ANTLR start "DOUBLEQUOTED_STRING"
    public final void mDOUBLEQUOTED_STRING() throws RecognitionException {
            int _type = DOUBLEQUOTED_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:465:2: ( '\"' (~ ( '\"' ) )* '\"' )
            // MySQLParser.g:465:4: '\"' (~ ( '\"' ) )* '\"'
            {
            match('\"'); 
            // MySQLParser.g:465:8: (~ ( '\"' ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\u0000' && LA7_0<='!')||(LA7_0>='#' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // MySQLParser.g:465:10: ~ ( '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "DOUBLEQUOTED_STRING"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // MySQLParser.g:468:5: ( ( ' ' | '\\t' | '\\r' '\\n' | '\\n' | '\\r' ) )
            // MySQLParser.g:468:9: ( ' ' | '\\t' | '\\r' '\\n' | '\\n' | '\\r' )
            {
            // MySQLParser.g:468:9: ( ' ' | '\\t' | '\\r' '\\n' | '\\n' | '\\r' )
            int alt8=5;
            switch ( input.LA(1) ) {
            case ' ':
                {
                alt8=1;
                }
                break;
            case '\t':
                {
                alt8=2;
                }
                break;
            case '\r':
                {
                int LA8_3 = input.LA(2);

                if ( (LA8_3=='\n') ) {
                    alt8=3;
                }
                else {
                    alt8=5;}
                }
                break;
            case '\n':
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // MySQLParser.g:468:13: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 2 :
                    // MySQLParser.g:469:7: '\\t'
                    {
                    match('\t'); 

                    }
                    break;
                case 3 :
                    // MySQLParser.g:470:7: '\\r' '\\n'
                    {
                    match('\r'); 
                    match('\n'); 

                    }
                    break;
                case 4 :
                    // MySQLParser.g:471:7: '\\n'
                    {
                    match('\n'); 

                    }
                    break;
                case 5 :
                    // MySQLParser.g:472:7: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // MySQLParser.g:1:8: ( T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | N | ASC | DESC | EXPONENT | ID | PLUS | NUMBER | MINUS | DOT | COMMA | EQ | DIVIDE | ASTERISK | MOD | ARROW | DOUBLEVERTBAR | RPAREN | LPAREN | LTH | NOT_EQ | LEQ | GEQ | GTH | QUOTED_STRING | DOUBLEQUOTED_STRING | WS )
        int alt9=65;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // MySQLParser.g:1:10: T__76
                {
                mT__76(); 

                }
                break;
            case 2 :
                // MySQLParser.g:1:16: T__77
                {
                mT__77(); 

                }
                break;
            case 3 :
                // MySQLParser.g:1:22: T__78
                {
                mT__78(); 

                }
                break;
            case 4 :
                // MySQLParser.g:1:28: T__79
                {
                mT__79(); 

                }
                break;
            case 5 :
                // MySQLParser.g:1:34: T__80
                {
                mT__80(); 

                }
                break;
            case 6 :
                // MySQLParser.g:1:40: T__81
                {
                mT__81(); 

                }
                break;
            case 7 :
                // MySQLParser.g:1:46: T__82
                {
                mT__82(); 

                }
                break;
            case 8 :
                // MySQLParser.g:1:52: T__83
                {
                mT__83(); 

                }
                break;
            case 9 :
                // MySQLParser.g:1:58: T__84
                {
                mT__84(); 

                }
                break;
            case 10 :
                // MySQLParser.g:1:64: T__85
                {
                mT__85(); 

                }
                break;
            case 11 :
                // MySQLParser.g:1:70: T__86
                {
                mT__86(); 

                }
                break;
            case 12 :
                // MySQLParser.g:1:76: T__87
                {
                mT__87(); 

                }
                break;
            case 13 :
                // MySQLParser.g:1:82: T__88
                {
                mT__88(); 

                }
                break;
            case 14 :
                // MySQLParser.g:1:88: T__89
                {
                mT__89(); 

                }
                break;
            case 15 :
                // MySQLParser.g:1:94: T__90
                {
                mT__90(); 

                }
                break;
            case 16 :
                // MySQLParser.g:1:100: T__91
                {
                mT__91(); 

                }
                break;
            case 17 :
                // MySQLParser.g:1:106: T__92
                {
                mT__92(); 

                }
                break;
            case 18 :
                // MySQLParser.g:1:112: T__93
                {
                mT__93(); 

                }
                break;
            case 19 :
                // MySQLParser.g:1:118: T__94
                {
                mT__94(); 

                }
                break;
            case 20 :
                // MySQLParser.g:1:124: T__95
                {
                mT__95(); 

                }
                break;
            case 21 :
                // MySQLParser.g:1:130: T__96
                {
                mT__96(); 

                }
                break;
            case 22 :
                // MySQLParser.g:1:136: T__97
                {
                mT__97(); 

                }
                break;
            case 23 :
                // MySQLParser.g:1:142: T__98
                {
                mT__98(); 

                }
                break;
            case 24 :
                // MySQLParser.g:1:148: T__99
                {
                mT__99(); 

                }
                break;
            case 25 :
                // MySQLParser.g:1:154: T__100
                {
                mT__100(); 

                }
                break;
            case 26 :
                // MySQLParser.g:1:161: T__101
                {
                mT__101(); 

                }
                break;
            case 27 :
                // MySQLParser.g:1:168: T__102
                {
                mT__102(); 

                }
                break;
            case 28 :
                // MySQLParser.g:1:175: T__103
                {
                mT__103(); 

                }
                break;
            case 29 :
                // MySQLParser.g:1:182: T__104
                {
                mT__104(); 

                }
                break;
            case 30 :
                // MySQLParser.g:1:189: T__105
                {
                mT__105(); 

                }
                break;
            case 31 :
                // MySQLParser.g:1:196: T__106
                {
                mT__106(); 

                }
                break;
            case 32 :
                // MySQLParser.g:1:203: T__107
                {
                mT__107(); 

                }
                break;
            case 33 :
                // MySQLParser.g:1:210: T__108
                {
                mT__108(); 

                }
                break;
            case 34 :
                // MySQLParser.g:1:217: T__109
                {
                mT__109(); 

                }
                break;
            case 35 :
                // MySQLParser.g:1:224: T__110
                {
                mT__110(); 

                }
                break;
            case 36 :
                // MySQLParser.g:1:231: T__111
                {
                mT__111(); 

                }
                break;
            case 37 :
                // MySQLParser.g:1:238: T__112
                {
                mT__112(); 

                }
                break;
            case 38 :
                // MySQLParser.g:1:245: T__113
                {
                mT__113(); 

                }
                break;
            case 39 :
                // MySQLParser.g:1:252: T__114
                {
                mT__114(); 

                }
                break;
            case 40 :
                // MySQLParser.g:1:259: N
                {
                mN(); 

                }
                break;
            case 41 :
                // MySQLParser.g:1:261: ASC
                {
                mASC(); 

                }
                break;
            case 42 :
                // MySQLParser.g:1:265: DESC
                {
                mDESC(); 

                }
                break;
            case 43 :
                // MySQLParser.g:1:270: EXPONENT
                {
                mEXPONENT(); 

                }
                break;
            case 44 :
                // MySQLParser.g:1:279: ID
                {
                mID(); 

                }
                break;
            case 45 :
                // MySQLParser.g:1:282: PLUS
                {
                mPLUS(); 

                }
                break;
            case 46 :
                // MySQLParser.g:1:287: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 47 :
                // MySQLParser.g:1:294: MINUS
                {
                mMINUS(); 

                }
                break;
            case 48 :
                // MySQLParser.g:1:300: DOT
                {
                mDOT(); 

                }
                break;
            case 49 :
                // MySQLParser.g:1:304: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 50 :
                // MySQLParser.g:1:310: EQ
                {
                mEQ(); 

                }
                break;
            case 51 :
                // MySQLParser.g:1:313: DIVIDE
                {
                mDIVIDE(); 

                }
                break;
            case 52 :
                // MySQLParser.g:1:320: ASTERISK
                {
                mASTERISK(); 

                }
                break;
            case 53 :
                // MySQLParser.g:1:329: MOD
                {
                mMOD(); 

                }
                break;
            case 54 :
                // MySQLParser.g:1:333: ARROW
                {
                mARROW(); 

                }
                break;
            case 55 :
                // MySQLParser.g:1:339: DOUBLEVERTBAR
                {
                mDOUBLEVERTBAR(); 

                }
                break;
            case 56 :
                // MySQLParser.g:1:353: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 57 :
                // MySQLParser.g:1:360: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 58 :
                // MySQLParser.g:1:367: LTH
                {
                mLTH(); 

                }
                break;
            case 59 :
                // MySQLParser.g:1:371: NOT_EQ
                {
                mNOT_EQ(); 

                }
                break;
            case 60 :
                // MySQLParser.g:1:378: LEQ
                {
                mLEQ(); 

                }
                break;
            case 61 :
                // MySQLParser.g:1:382: GEQ
                {
                mGEQ(); 

                }
                break;
            case 62 :
                // MySQLParser.g:1:386: GTH
                {
                mGTH(); 

                }
                break;
            case 63 :
                // MySQLParser.g:1:390: QUOTED_STRING
                {
                mQUOTED_STRING(); 

                }
                break;
            case 64 :
                // MySQLParser.g:1:404: DOUBLEQUOTED_STRING
                {
                mDOUBLEQUOTED_STRING(); 

                }
                break;
            case 65 :
                // MySQLParser.g:1:424: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA9_eotS =
        "\1\uffff\13\26\1\uffff\6\26\1\103\1\105\1\110\2\uffff\1\111\1\uffff"+
        "\1\113\5\uffff\1\115\1\uffff\1\117\3\uffff\1\26\1\127\1\130\2\26"+
        "\1\134\1\135\1\136\6\26\1\146\16\26\1\uffff\1\105\13\uffff\1\170"+
        "\6\26\2\uffff\3\26\3\uffff\2\26\1\u0084\1\u0085\1\26\1\u0087\1\u0088"+
        "\1\uffff\21\26\1\uffff\2\26\1\u009c\10\26\2\uffff\1\u00a5\2\uffff"+
        "\1\u00a6\4\26\1\u00ab\2\26\1\u00ae\2\26\1\u00b1\2\26\1\u00b4\4\26"+
        "\1\uffff\1\26\1\u00ba\3\26\1\u00be\1\26\1\u00c0\2\uffff\1\u00c1"+
        "\1\uffff\2\26\1\uffff\1\u00c4\1\u00c5\1\uffff\2\26\1\uffff\2\26"+
        "\1\uffff\1\26\1\u00cb\1\u00cc\1\u00cd\1\26\2\uffff\1\u00cf\1\u00d0"+
        "\1\uffff\1\26\2\uffff\1\u00d2\4\uffff\1\u00d3\1\26\1\u00d5\1\u00d6"+
        "\3\uffff\1\26\2\uffff\1\u00d8\2\uffff\1\26\2\uffff\1\u00da\1\uffff"+
        "\1\u00db\2\uffff";
    static final String DFA9_eofS =
        "\u00dc\uffff";
    static final String DFA9_minS =
        "\1\11\1\105\1\107\1\101\1\116\1\105\1\110\1\101\1\116\1\105\1\111"+
        "\1\101\1\uffff\2\122\1\105\1\120\2\117\1\60\1\56\1\52\2\uffff\1"+
        "\55\1\uffff\1\76\5\uffff\1\75\1\uffff\1\75\3\uffff\1\114\2\43\1"+
        "\116\1\114\3\43\1\124\1\105\1\124\1\116\1\114\1\104\1\43\1\113\1"+
        "\106\1\127\1\107\1\117\1\114\1\122\1\125\1\117\1\114\1\123\1\104"+
        "\1\111\1\116\1\uffff\1\56\13\uffff\1\43\2\105\1\117\1\111\2\105"+
        "\2\uffff\1\117\1\125\1\105\3\uffff\1\127\1\122\2\43\1\114\2\43\1"+
        "\uffff\1\105\1\111\1\124\1\116\1\110\1\115\1\123\1\103\1\105\1\125"+
        "\1\105\1\103\1\124\1\101\1\116\1\103\1\116\1\uffff\1\103\1\122\1"+
        "\43\1\116\1\130\2\122\1\105\1\122\2\105\2\uffff\1\43\2\uffff\1\43"+
        "\1\124\1\40\1\125\1\124\1\43\2\105\1\43\1\120\1\124\1\43\1\111\1"+
        "\124\1\43\1\101\3\124\1\uffff\1\111\1\43\1\40\1\105\1\123\1\43\1"+
        "\105\1\43\2\uffff\1\43\1\uffff\1\115\1\40\1\uffff\2\43\1\uffff\1"+
        "\40\1\105\1\uffff\1\116\1\105\1\uffff\1\124\3\43\1\124\2\uffff\2"+
        "\43\1\uffff\1\116\2\uffff\1\43\4\uffff\1\43\1\103\2\43\3\uffff\1"+
        "\105\2\uffff\1\43\2\uffff\1\124\2\uffff\1\43\1\uffff\1\43\2\uffff";
    static final String DFA9_maxS =
        "\1\174\1\105\1\123\1\101\1\122\1\131\1\110\1\125\1\123\1\111\1"+
        "\117\1\122\1\uffff\2\122\1\111\1\120\2\117\2\71\1\52\2\uffff\1\71"+
        "\1\uffff\1\76\5\uffff\1\76\1\uffff\1\75\3\uffff\1\124\2\172\1\116"+
        "\1\114\3\172\1\124\1\105\1\124\1\116\1\114\1\104\1\172\1\115\1\106"+
        "\1\127\1\107\1\117\1\114\1\122\1\125\1\117\2\123\1\104\1\111\1\125"+
        "\1\uffff\1\71\13\uffff\1\172\2\105\1\117\1\111\2\105\2\uffff\1\117"+
        "\1\125\1\105\3\uffff\1\127\1\122\2\172\1\114\2\172\1\uffff\1\105"+
        "\1\111\1\124\1\116\1\110\1\115\1\123\1\103\1\105\1\125\1\105\1\103"+
        "\1\124\1\101\1\116\1\103\1\116\1\uffff\1\103\1\122\1\172\1\116\1"+
        "\130\2\122\1\105\1\122\2\105\2\uffff\1\172\2\uffff\1\172\1\124\1"+
        "\40\1\125\1\124\1\172\2\105\1\172\1\120\1\124\1\172\1\111\1\124"+
        "\1\172\1\101\3\124\1\uffff\1\111\1\172\1\40\1\105\1\123\1\172\1"+
        "\105\1\172\2\uffff\1\172\1\uffff\1\115\1\40\1\uffff\2\172\1\uffff"+
        "\1\40\1\105\1\uffff\1\116\1\105\1\uffff\1\124\3\172\1\124\2\uffff"+
        "\2\172\1\uffff\1\116\2\uffff\1\172\4\uffff\1\172\1\103\2\172\3\uffff"+
        "\1\105\2\uffff\1\172\2\uffff\1\124\2\uffff\1\172\1\uffff\1\172\2"+
        "\uffff";
    static final String DFA9_acceptS =
        "\14\uffff\1\25\11\uffff\1\54\1\55\1\uffff\1\61\1\uffff\1\63\1\65"+
        "\1\67\1\70\1\71\1\uffff\1\73\1\uffff\1\77\1\100\1\101\35\uffff\1"+
        "\57\1\uffff\1\50\1\56\1\53\1\64\1\60\1\66\1\62\1\74\1\72\1\75\1"+
        "\76\7\uffff\1\21\1\15\3\uffff\1\12\1\40\1\6\7\uffff\1\30\21\uffff"+
        "\1\1\13\uffff\1\11\1\16\1\uffff\1\13\1\51\23\uffff\1\3\10\uffff"+
        "\1\20\1\22\1\uffff\1\42\2\uffff\1\24\2\uffff\1\26\2\uffff\1\52\2"+
        "\uffff\1\44\5\uffff\1\33\1\41\2\uffff\1\5\1\uffff\1\10\1\37\1\uffff"+
        "\1\43\1\27\1\32\1\31\4\uffff\1\47\1\7\1\2\1\uffff\1\34\1\4\1\uffff"+
        "\1\23\1\35\1\uffff\1\36\1\45\1\uffff\1\14\1\uffff\1\17\1\46";
    static final String DFA9_specialS =
        "\u00dc\uffff}>";
    static final String[] DFA9_transitionS = {
            "\2\45\2\uffff\1\45\22\uffff\1\45\1\41\1\44\2\uffff\1\34\1\uffff"+
            "\1\43\1\37\1\36\1\25\1\27\1\31\1\23\1\30\1\33\12\24\2\uffff"+
            "\1\40\1\32\1\42\1\14\1\uffff\1\10\1\5\1\22\1\17\1\26\1\13\1"+
            "\16\1\26\1\2\1\21\1\26\1\11\1\26\1\7\1\4\2\26\1\12\1\1\1\15"+
            "\1\20\1\3\1\6\3\26\3\uffff\1\41\2\uffff\32\26\1\uffff\1\35",
            "\1\46",
            "\1\51\6\uffff\1\47\4\uffff\1\50",
            "\1\52",
            "\1\54\3\uffff\1\53",
            "\1\56\23\uffff\1\55",
            "\1\57",
            "\1\61\15\uffff\1\60\5\uffff\1\62",
            "\1\63\4\uffff\1\64",
            "\1\66\3\uffff\1\65",
            "\1\70\5\uffff\1\67",
            "\1\72\15\uffff\1\73\2\uffff\1\71",
            "",
            "\1\74",
            "\1\75",
            "\1\76\3\uffff\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\12\24",
            "\1\106\1\uffff\12\104",
            "\1\107",
            "",
            "",
            "\1\106\2\uffff\12\106",
            "",
            "\1\112",
            "",
            "",
            "",
            "",
            "",
            "\1\114\1\41",
            "",
            "\1\116",
            "",
            "",
            "",
            "\1\121\7\uffff\1\120",
            "\2\26\13\uffff\12\26\7\uffff\3\26\1\125\1\26\1\124\7\26\1"+
            "\126\4\26\1\122\1\123\6\26\4\uffff\1\26\1\uffff\32\26",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\131",
            "\1\132",
            "\2\26\13\uffff\12\26\7\uffff\3\26\1\133\26\26\4\uffff\1\26"+
            "\1\uffff\32\26",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144",
            "\2\26\13\uffff\12\26\7\uffff\2\26\1\145\27\26\4\uffff\1\26"+
            "\1\uffff\32\26",
            "\1\147\1\uffff\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161\6\uffff\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166\6\uffff\1\167",
            "",
            "\1\106\1\uffff\12\104",
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
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "",
            "",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "",
            "",
            "",
            "\1\u0082",
            "\1\u0083",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u0086",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "",
            "\1\u009a",
            "\1\u009b",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "",
            "",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            "",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u00ac",
            "\1\u00ad",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u00af",
            "\1\u00b0",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u00b2",
            "\1\u00b3",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "",
            "\1\u00b9",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u00bf",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            "",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            "\1\u00c2",
            "\1\u00c3",
            "",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            "\1\u00c6",
            "\1\u00c7",
            "",
            "\1\u00c8",
            "\1\u00c9",
            "",
            "\1\u00ca",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u00ce",
            "",
            "",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            "\1\u00d1",
            "",
            "",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            "",
            "",
            "",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\1\u00d4",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            "",
            "",
            "\1\u00d7",
            "",
            "",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            "",
            "\1\u00d9",
            "",
            "",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            "\2\26\13\uffff\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32"+
            "\26",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | N | ASC | DESC | EXPONENT | ID | PLUS | NUMBER | MINUS | DOT | COMMA | EQ | DIVIDE | ASTERISK | MOD | ARROW | DOUBLEVERTBAR | RPAREN | LPAREN | LTH | NOT_EQ | LEQ | GEQ | GTH | QUOTED_STRING | DOUBLEQUOTED_STRING | WS );";
        }
    }
 

}