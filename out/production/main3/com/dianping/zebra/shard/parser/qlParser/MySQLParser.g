grammar MySQLParser;
options {
	output=AST;
	language=Java;
	ASTLabelType=CommonTree;
}

tokens{
	EXPR;
	GROUPBY;
	COUNTCOLUMN;
	JOINTYPE;
	ALIAS;
	TABLENAME;
	TABLENAMES;
	SUBQUERY;
	COLUMN;
	AS;
	SELECT;
	DISPLAYED_COUNT_COLUMN;
	DISPLAYED_COLUMN;
	IN;
	NOT;
	SELECT_LIST;
	QUTED_STR;
	WHERE;
	CONDITION_OR;
	CONDITION_LEFT;
	IN_LISTS;
	CONDITION_OR_NOT;
	AND;
	OR;
	ISNOT;
	IS;
	NULL;
	NAN;
	INFINITE;
	LIKE;
	NOT_LIKE;
	NOT_BETWEEN;
	BETWEEN;
	ORDERBY;
	INSERT;
	IGNORE;
	INSERT_VAL;
	PRIORITY;
	
	COLUMNS;
	UPDATE;
	SET;
	SET_ELE;
	COL_TAB;
	DELETE;
	/*-------------------------------------------the rules below are Redefined by SubParser 
.For Antlr v3 didn't have any method to reuse rules.So we wrote rules which had been modified by subParser here to make
the refactor easily.-----------------------------*/
	SKIP;
	RANGE;
}
@lexer::header{ package  com.dianping.zebra.shard.parser.qlParser; } 
@header{
package com.dianping.zebra.shard.parser.qlParser;

import java.util.Map;
import java.util.HashMap;

import com.dianping.zebra.shard.parser.sqlParser.groupFunction.GroupFunction;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.GroupFunctionRegister;
import com.dianping.zebra.shard.parser.valueObject.FunctionConvertor;
}
@members{
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

}
@rulecatch {
catch (RecognitionException e) {
throw e;
}
}
beg	:
start_rule 
	;
	
start_rule
	:select_command
	|update_command
	|insert_command
	|delete_command
	;

	
setclause
	:'SET' updateColumnSpecs->^(SET updateColumnSpecs)
	;
	
updateColumnSpecs
	:updateColumnSpec (COMMA updateColumnSpec)*->^(SET_ELE updateColumnSpec)+
	;
updateColumnSpec
	:columnNameInUpdate EQ^ expr
	;
insert_command
	:	'INSERT' ('IGNORE')? 'INTO' selected_table
		( LPAREN column_specs  RPAREN )?
		('VALUES' LPAREN values RPAREN
		)->^(INSERT selected_table column_specs values)
	;

    
orderByClause
	:'ORDER' 'BY' columnNamesAfterWhere->^(ORDERBY columnNamesAfterWhere)
//	|'ORDER' 'BY' columnNamesAfterWhere 'ASC'->^(ORDERBY columnNamesAfterWhere ASC)
//	|'ORDER' 'BY' columnNamesAfterWhere 'DESC'->^(ORDERBY columnNamesAfterWhere DESC)
	;
columnNamesAfterWhere
   	:columnNameAfterWhere (COMMA! columnNameAfterWhere)*	
   	;
selectClause
    :'SELECT' distinct? LPAREN? select_list RPAREN? ->^(SELECT distinct? select_list)
    ;  
whereClause
	:'WHERE' sqlCondition->^(WHERE sqlCondition)
	;

sqlCondition
	:'NOT' condition_or->^(CONDITION_OR_NOT  condition_or)
	|condition_or ->^(CONDITION_OR condition_or)
	;


condition_or
	:condition_and ( 'OR'^ condition_and )*
	;

condition_and
	:condition_PAREN ( 'AND'^ condition_PAREN )*
	;
condition_PAREN
	:(LPAREN condition_or RPAREN)=>LPAREN condition_or RPAREN->^(PRIORITY condition_or)
	|condition_expr
	;
condition_expr
	: condition_left^
	(comparisonCondition^
	| inCondition^
	| isCondition^
	| likeCondition^
	| betweenCondition^
	 )
	;
condition_left
	:expr->^(CONDITION_LEFT expr)
	;
betweenCondition
	: 'NOT' 'BETWEEN' between_and->^(NOT_BETWEEN between_and)
	| 'BETWEEN' between_and->^(BETWEEN between_and)
	;
	
between_and
	:a=between_and_expression 'AND' b=between_and_expression->^($a $b)
	;
	
between_and_expression
	: quoted_string
	| expr_add
	;
	
isCondition
	: 'IS' 'NOT' condition_is_valobject->^(ISNOT condition_is_valobject)
	|'IS' condition_is_valobject->^(IS condition_is_valobject)
//	|LPAREN columnNameAfterWhere 'IS'^ ( 'NOT' )? condition_is_valobject RPAREN
	;

condition_is_valobject
	: 'NAN' ->NAN
	| 'INFINITE' ->INFINITE
	| 'NULL' ->NULL
	;

inCondition
	:(not='NOT')? 'IN' (subquery
	|( LPAREN inCondition_expr_adds RPAREN))->^(IN $not? subquery? inCondition_expr_adds?)

	;

likeCondition
	:'NOT''LIKE'  value->^(NOT_LIKE value)
	|'LIKE' value->^(LIKE value)
//	|LPAREN! columnNameAfterWhere ( 'NOT' )?  'LIKE'^  value RPAREN!
	;

inCondition_expr_adds:
	expr_add(COMMA expr_add)*->^(IN_LISTS expr_add+)
	;	

identifiers
	:columnNameAfterWhere (COMMA identifier)*
	;

comparisonCondition
	:relational_op expr->^(relational_op expr)
	//|relational_op subquery->^(relational_op subquery)
	;
	
expr	:(expr_add
	| subquery 
	)	
	;
	
subquery:
	LPAREN select_command RPAREN->^(SUBQUERY select_command)	
	;
expr_add
	:expr_mul ( ( PLUS^ | MINUS^ | DOUBLEVERTBAR^ ) (expr_mul) )*
	//|LPAREN? expr_mul ( ( PLUS^ | MINUS^ | DOUBLEVERTBAR^ ) expr_mul  RPAREN? )*
	//(( PLUS^ | MINUS^ | DOUBLEVERTBAR^|ASTERISK^ | DIVIDE^) expr_add)?
	;
//t	:LPAREN expr_mul ( ( PLUS^ | MINUS^ | DOUBLEVERTBAR^ ) expr_mul )* RPAREN 	
//	;
expr_mul
	:	expr_sign ( ( ASTERISK^ | DIVIDE^ | MOD^ ) expr_sign )*
	;
expr_sign
	:	( PLUS^ | MINUS^ )? expr_pow
	;
expr_pow
	:	expr_expr ( EXPONENT^ expr_expr )*
	;

expr_expr
	:value
	|boolean_literal
	|'NULL'
	|'ROWNUM'
//	|function
//	|{input.LT(1).getText().toUpperCase().equals("sdf")}? ID LPAREN values_func RPAREN->^(ID values_func)
	|{functionMap.containsKey(input.LT(1).getText().toUpperCase())}? ID ((LPAREN values_func? RPAREN)|)->^(ID values_func?)
	;
/*function
	:'TRUNC' LPAREN values	RPAREN
	;*/
sql_condition
	:	condition_or
	;
relational_op
	:	EQ | LTH | GTH | NOT_EQ | LEQ | GEQ
	;

fromClause
	:'FROM'! selected_table
	;

select_list
	:	displayed_column ( COMMA displayed_column )*->^(SELECT_LIST displayed_column+)
	;
	
displayed_column
	:
	quoted_string alias?->^(quoted_string alias?)
	|{input.LT(1).getText().toUpperCase().equals("CONCAT")}? concat LPAREN identifiedOrQuotedString (COMMA identifiedOrQuotedString)* RPAREN alias?->^(concat identifiedOrQuotedString (identifiedOrQuotedString)* alias?)
	|{input.LT(1).getText().toUpperCase().equals("COUNT")}? count LPAREN distinct? LPAREN? countColumn RPAREN? RPAREN alias?->^(count distinct? countColumn alias?)
	|expr_add alias?->^(EXPR expr_add alias?)
	//|table_alias?  column (alias)?->^(COLUMN table_alias? column alias?)
	|{groupFunc.containsKey(input.LT(1).getText().toUpperCase())}? ID ((LPAREN table_alias? column? RPAREN)|) alias?->^(ID table_alias? column? alias?)
	;
	
columnNameAfterWhere
	:table_alias? identifier  ->^(ASC identifier table_alias?)
	|table_alias? identifier  ASC  ->^(ASC identifier table_alias?)
	|table_alias? identifier  DESC ->^(DESC identifier table_alias?)
	;
	
columnNameInUpdate
	:table_alias? identifier 
	;
table_alias
	:identifier DOT->^(COL_TAB identifier)	
	;
column
	:ASTERISK
	|identifier
	;
values
	:expr ( COMMA expr )*->^(INSERT_VAL expr*)
	;
values_func
	:expr ( COMMA! expr )*
	;	
value	:
	N
	|NUMBER
	|'?'
	|LPAREN! expr RPAREN!
	|quoted_string ->^(QUTED_STR quoted_string)
	|column_spec
	;
value_simple	:
	N
	|NUMBER
	|'?'
	|LPAREN expr RPAREN
	|quoted_string ->^(QUTED_STR quoted_string)
	|column_spec
	;

column_specs
	:	column_spec ( COMMA column_spec )*->^(COLUMNS column_spec+)
	;
	
selected_table
	:a_table (COMMA a_table)*->^(TABLENAMES a_table+)
	;
a_table
	:table_spec  alias?->^(TABLENAME table_spec alias?)
	;
table_spec
	:	( schema_name DOT!)? table_name 
	| subquery 
	;

table_name
	:identifier
	;
	
column_spec
	:(table_name DOT)? identifier ->^(COLUMN identifier table_name?)
	;

schema_name
	:identifier
	;
	
boolean_literal
	:	'TRUE' | 'FALSE'
	;
	
alias
	:	( 'AS')? identifier->^(AS identifier)
	;
	
identifier
	:	ID
	| ASTERISK
//	|	DOUBLEQUOTED_STRING //del on 1-21 13:17
   	;
   N
   	:MINUS? '0' .. '9' ( '0' .. '9' )*
	;  

	ASC:'ASC';
	DESC:'DESC';	
EXPONENT
	:	'**'
	;	
 ID 
    :	('A' .. 'Z'|'a'..'z') ( 'A' .. 'Z'|'a'..'z' | '0' .. '9' | '_' | '$' | '#' )*
//    |	DOUBLEQUOTED_STRING
    ;
PLUS
	:	'+'
	;

NUMBER
	:	
	(( N '.' N )
	|('.' N)
	)
    ;
    
 
MINUS
	:	'-'
	;
	DOT
	:	POINT
	;
	COMMA
	:	','
	;
	EQ
	:	'='
	;
	
DIVIDE
	:	'/'
	;
ASTERISK
	:	'*'
	;
MOD
	: '%'
	;
ARROW
	:	'=>'
	;
DOUBLEVERTBAR
	:	'||'
	;
	
fragment
POINT
	:	'.'
	;	
RPAREN
	:	')'
	;
LPAREN
	:	'('
	;
	LTH
	:	'<'
	;
	NOT_EQ
	:	'<>' | '!=' | '^='
	;
	LEQ
	:	'<='
	;
	GEQ
	:	'>='
	;
	GTH
	:	'>'
	;
	
quoted_string
	: QUOTED_STRING | DOUBLEQUOTED_STRING
	;	

QUOTED_STRING
	:'\'' (~'\''|'\'\'')* '\''
	;

// fragment
DOUBLEQUOTED_STRING
	:	'"' ( ~('"') )* '"'
	;
	
WS  :   (   ' '
		|   '\t'
		|   '\r' '\n' 
		|   '\n'     
		|   '\r'      
		)
		{$channel = HIDDEN;}
	;


/*-------------------------------------------the rules below are Redefined by SubParser 
.For Antlr v3 didn't have any method to reuse rules.So we wrote rules which had been modified by subParser here to make
the refactor easily.-----------------------------*/

select_command
     : selectClause (fromClause)? (joinClause)* (whereClause)? (groupByClause)? (orderByClause)? (limitClause)? indexClause?
     ;
groupByClause
	: 'GROUP BY' groupByColumns->^(GROUPBY groupByColumns)
	;
groupByColumns
	: groupByColumn (COMMA! groupByColumn)*
	;
groupByColumn
	: identifier
	;
 indexClause
 	:'FORCE' 'INDEX' LPAREN select_list  RPAREN
 	|'IGNORE' 'INDEX' LPAREN select_list RPAREN
 	;
delete_command
	:'DELETE' fromClause whereClause? orderByClause?  (limitClause)?->^(DELETE fromClause whereClause? orderByClause? limitClause?)
	;
update_command
	:'UPDATE' selected_table  setclause whereClause?  (limitClause)?->^(UPDATE selected_table setclause whereClause? limitClause?)
	;
limitClause
	:'LIMIT' (skip COMMA )? range->^('LIMIT' skip? range)
	;
skip	
	:N ->^(SKIP N)
	|'?'->^(SKIP '?')
	;
range	:N->^(RANGE N)
	|'?'->^(RANGE '?')
	;
joinClause
	: joinType table_spec alias? 'ON' sqlCondition->^(joinType table_spec alias? 'ON' sqlCondition)
	;
joinType
	: 'INNER JOIN' | 'LEFT JOIN' | 'RIGHT JOIN' | 'JOIN'
	;
concat
	: 'CONCAT'
	;
identifiedOrQuotedString
	: ( (table_alias? identifier) | quoted_string )
	;
distinct
	: 'DISTINCT'
	;
count
	: 'COUNT'
	;
identifierOrN
	:
	(identifier | N)
	;
countColumn
	: (identifier DOT)? identifierOrN->^(COUNTCOLUMN identifier? identifierOrN)
	;