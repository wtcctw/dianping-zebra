tree grammar MySQLWalker;
options {
tokenVocab=MySQLParser; // reuse token types
ASTLabelType=CommonTree; // $label will have type CommonTree
language=Java;
output=template;
rewrite=true;
}
@header
{
package com.dianping.zebra.shard.parser.qlParser;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.math.BigDecimal;

import static com.dianping.zebra.shard.parser.util.DbFunctions.*;

import com.dianping.zebra.shard.parser.condition.ExpressionGroup;
import com.dianping.zebra.shard.parser.condition.WhereCondition;
import com.dianping.zebra.shard.parser.condition.BindIndexHolder;
import com.dianping.zebra.shard.parser.condition.OrExpressionGroup;
import com.dianping.zebra.shard.parser.condition.BetweenPair;

import com.dianping.zebra.shard.parser.sqlParser.*;
import com.dianping.zebra.shard.parser.sqlParser.DMLCommon;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MyWhereCondition;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.Count;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.Concat;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.GroupFunction;
import com.dianping.zebra.shard.parser.sqlParser.groupFunction.GroupFunctionRegister;

import com.dianping.zebra.shard.parser.sqlParser.mySql.MySelect;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MyDelete;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MyUpdate;

import com.dianping.zebra.shard.parser.valueObject.ColumnObject;
import com.dianping.zebra.shard.parser.valueObject.ValueObject;
import com.dianping.zebra.shard.parser.valueObject.FunctionConvertor;

import com.dianping.zebra.shard.parser.util.DbFunctions;
}
@members{
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
}
beg	returns[DMLCommon obj]:
start_rule{$obj=$start_rule.obj;} 	
	;
	
start_rule returns[DMLCommon obj]
	:select_command{$obj=$select_command.select;}
	|insert_command{$obj=$insert_command.ins;}
	|update_command{$obj=$update_command.update;}
	|delete_command{$obj=$delete_command.del;}
	;

setclause[Update update]
	:^(SET updateColumnSpecs[$update]+)	
	;
updateColumnSpecs[Update update]
	:^(SET_ELE updateColumnSpec[$update])	
	;
updateColumnSpec[Update update]
	:^(EQ table_alias? identifier expr[$update])
	{
	$update.addSetElement($identifier.text,$table_alias.aText,$expr.valueObj);
	}
	;

insert_command returns[Insert ins]
@init{$ins=new Insert();}
	:^(INSERT tables[$ins, true] column_specs[$ins]* values[$ins])
	;
values[Insert ins]	:^(INSERT_VAL (expr[$ins]{ins.addValue($expr.valueObj);})*)
	;
column_specs[Insert ins]
	:^(COLUMNS column_spec[$ins]+)
	;
column_spec[Insert ins]
	:^(COLUMN identifier table_name[false]?)
	{
		$ins.addColumnTandC($table_name.text,$identifier.text);
	}
	;



whereClause[WhereCondition where]
	:^(WHERE sqlCondition[$where])
	;
	
orderByClause[WhereCondition where]
	:^(ORDERBY columnNamesAfterWhere[$where])
	;
columnNamesAfterWhere[WhereCondition where]
	:(columnNameAfterWhere[$where])+
	;
columnNameAfterWhere[WhereCondition where]
	:^(ASC  identifier table_alias?){
		$where.addOrderByColumn($table_alias.aText,$identifier.text,true);
	}
	|^(DESC  identifier table_alias?){
		$where.addOrderByColumn($table_alias.aText,$identifier.text,false);
	}
	;
selectClause[Select select]
    :^(SELECT distinct? select_list[$select]) {
    	$select.setHasDistinct($distinct.text != null);
    }
    ;  

sqlCondition[WhereCondition where]
	
	:^(CONDITION_OR_NOT condition[where.getHolder(),where.getExpGroup(),false])
	|^(CONDITION_OR condition[where.getHolder(),where.getExpGroup(),false])
	;

condition[BindIndexHolder where,ExpressionGroup eg,boolean isPriority]	
	:
	{
		OrExpressionGroup orExp=new OrExpressionGroup();
		$eg.addExpressionGroup(orExp);
		orExp.setPriorty(isPriority);
	}^('OR' s1=condition[where,orExp,$isPriority]+)
	| 
	{
		ExpressionGroup andExp=new ExpressionGroup();
		$eg.addExpressionGroup(andExp);
		andExp.setPriorty(isPriority);
	}^('AND' condition[where,andExp,$isPriority]+)
	|condition_PAREN[where,$eg]
	|^(PRIORITY condition[where,$eg,true])
	;

/*condition_and[OrExpressionGroup orGroup] returns[ExpressionGroup andExpGrp]
	:
	{
		$andExpGrp=new ExpressionGroup();
	}
	 ^('AND' condition_PAREN[$andExpGrp]+)
	 
	|condition_PAREN[$orGroup]
	
	|^('OR' s1=condition_and[orExp] s2=condition_and[orExp])
	
	{
	where.addAndExpression($s1.andExpGrp);
	where.addAndExpression($s2.andExpGrp);
	}
	;
	*/
condition_PAREN[BindIndexHolder where,ExpressionGroup exp]
	:condition_expr[$where,$exp]+
	;
condition_expr[BindIndexHolder where,ExpressionGroup exp]
	: comparisonCondition[$where,$exp]
	| inCondition[$where,$exp] 
	| isCondition [$where,$exp]
	| likeCondition[$where,$exp]
	| betweenCondition[$where,$exp]
	;
betweenCondition[BindIndexHolder where,ExpressionGroup exp]
	:^(NOT_BETWEEN between_and[$where] left_cond[$where]) {
		$exp.addNotBetween($left_cond.ret, $between_and.pair);
	}
	|^(BETWEEN between_and[$where] left_cond[$where]) {
		$exp.addBetween($left_cond.ret, $between_and.pair);
	}
	;
between_and[BindIndexHolder where] returns[BetweenPair pair]
	:^(start=between_and_expression[$where] end=between_and_expression[$where]) {
		$pair = new BetweenPair($start.valueObj, $end.valueObj);
	}	
	;
likeCondition[BindIndexHolder where,ExpressionGroup exp]
	:^(NOT_LIKE expr[$where] left_cond[$where]){
		$exp.addNotLike($left_cond.ret,$expr.valueObj);
	}
	|^(LIKE expr[$where] left_cond[$where]){
		$exp.addLike($left_cond.ret,$expr.valueObj);
	}
	;
	
isCondition[BindIndexHolder where,ExpressionGroup exp]
	:^(ISNOT NULL left_cond[$where]){
	$exp.addIsNotNull($left_cond.ret);
	}
	|^(IS NULL left_cond[$where]){
	$exp.addIsNull($left_cond.ret);
	}	
	;


comparisonCondition[BindIndexHolder where,ExpressionGroup exp]
	:^(EQ expr[$where] left_cond[$where])
	{
		$exp.addEqual($left_cond.ret,$expr.valueObj);
	}
	|^(NOT_EQ expr[$where] left_cond[$where])
	{
		$exp.addNotEqual($left_cond.ret,$expr.valueObj);
	}
	|^(LTH expr[$where] left_cond[$where])
	{
		$exp.addLessThan($left_cond.ret,$expr.valueObj);
	}
	|^(GTH expr[$where] left_cond[$where])
	{
		$exp.addGreaterThan($left_cond.ret,$expr.valueObj);
	}
	|^(LEQ expr[$where] left_cond[$where])
	{
		$exp.addLessOrEqual($left_cond.ret,$expr.valueObj);
	}
	|^(GEQ expr[$where] left_cond[$where])
	{
		$exp.addGreaterOrEqual($left_cond.ret,$expr.valueObj);
	}
	/*^(EQ subquery left_cond[$where])
	{
		$exp.addEqual($left_cond.ret,$subquery.subselect);
	}
	|^(NOT_EQ subquery left_cond[$where])
	{
		$exp.addNotEqual($left_cond.ret,$subquery.subselect);
	}
	|^(LTH subquery left_cond[$where])
	{
		$exp.addLessThan($left_cond.ret,$subquery.subselect);
	}
	|^(GTH subquery left_cond[$where])
	{
		$exp.addGreaterThan($left_cond.ret,$subquery.subselect;
	}
	|^(LEQ subquery left_cond[$where])
	{
		$exp.addLessOrEqual($left_cond.ret,$subquery.subselect);
	}
	|^(GEQ subquery left_cond[$where])
	{
		$exp.addGreaterOrEqual($left_cond.ret,$subquery.subselect);
	}*/
	;
left_cond[BindIndexHolder where] returns[Object ret]

	:^(CONDITION_LEFT expr[$where])
	{
		$ret=$expr.valueObj;
	}
	;
in_list[BindIndexHolder where] returns[List list]
	:^(IN_LISTS inCondition_expr_adds[$where])
	{
		$list=$inCondition_expr_adds.list;
	}	
	;
inCondition [BindIndexHolder where,ExpressionGroup exp]
	:^(IN not='NOT'? subquery? in_list[$where]?  left_cond[$where])
		{
		if($not.text!=null){
			if($subquery.subselect!=null){
				/*exp.addNotIn($columnNameAfterWhere.text,$subquery.subselect);*/
			}
			else if($in_list.list!=null){
				$exp.addNotIn($left_cond.ret,$in_list.list);
			}
		}else{
			if($subquery.subselect!=null){
				/*$exp.addIn($columnNameAfterWhere.text,$subquery.subselect);*/
			}
			else if($in_list.list!=null){
				$exp.addIn($left_cond.ret,$in_list.list);
			}
		}
		;
		}
		
	;



	
inCondition_expr_adds[BindIndexHolder where] returns [List list]
	@init{$list=new ArrayList();}:
	(expr_add[$where]
	{
		$list.add($expr_add.valueObjExpr);
	})+

	;
	
expr[BindIndexHolder where] returns [Object valueObj]	
	:(expr_add[$where] {$valueObj=$expr_add.valueObjExpr;}
	|subquery {$valueObj=$subquery.subselect;}
	
	)	
	;
expr_add[BindIndexHolder where]  returns[Object valueObjExpr]
@init{
List<Object> list=new ArrayList<Object>();
}
	:^(PLUS s1=expr_add[$where] s2=expr_add[$where]) {$valueObjExpr=add($s1.valueObjExpr,$s2.valueObjExpr);}
	|^(MINUS s1=expr_add[$where] s2=expr_add[$where]){$valueObjExpr=subtract($s1.valueObjExpr,$s2.valueObjExpr);}
	|^(ASTERISK s1=expr_add[$where] s2=expr_add[$where]){$valueObjExpr=multiply($s1.valueObjExpr,$s2.valueObjExpr);}
	|^(DIVIDE s1=expr_add[$where] s2=expr_add[$where]){$valueObjExpr=divide($s1.valueObjExpr,$s2.valueObjExpr);}
	|^(MOD s1=expr_add[$where] s2=expr_add[$where]){$valueObjExpr=mod($s1.valueObjExpr,$s2.valueObjExpr);}
	|N {$valueObjExpr=new BigDecimal($N.text);}
	|NUMBER{$valueObjExpr=new BigDecimal($NUMBER.text);}
	|boolean_literal
	|'NULL' {$valueObjExpr=DbFunctions.NULL;}
	|'ROWNUM'
	|'?'{$valueObjExpr=DbFunctions.bindVar($where.selfAddAndGet());}
	|^(QUTED_STR quoted_string){$valueObjExpr=$quoted_string.aText;}
	|^(COLUMN identifier table_name[false]?)
	{$valueObjExpr=column($identifier.text,$table_name.text);}
	|^(ID 
	(expr[$where]{list.add($expr.valueObj);})*){
	FunctionConvertor con=functionMap.get(($ID.text).toUpperCase());
	
	$valueObjExpr=con.handle(list.toArray());
	}
	
	;
value	:
	N
	|NUMBER
	|'?'
	|^(QUTED_STR quoted_string)
	;
boolean_literal returns[Object valueObj]
	:s1='TRUE' {$valueObj=Boolean.parseBoolean($s1.text);}
	| s1='FALSE'{$valueObj=Boolean.parseBoolean($s1.text);}
	;	

	
select_list[Select select]
	:^(SELECT_LIST displayed_column[$select]+)
	;	
fromClause[Select select]
	@init{needToRewriteTableName = true;}
	@after{needToRewriteTableName = false;}
	:^(TABLENAMES table[$select, true]+)
	;

table[DMLCommon common, boolean needToRewriteTableName]
	:^(TABLENAME table_spec[$common, $needToRewriteTableName])
	;
tables[DMLCommon common, boolean needToRewriteTableName]
	:^(TABLENAMES table[$common, $needToRewriteTableName]+)
	;
table_spec[DMLCommon common, boolean needToRewriteTableName]
	:( schema_name)? table_name[$needToRewriteTableName]  alias?
	{
		common.addTableNameAndSchemaName($table_name.text,$schema_name.text,$alias.aliText);
	}
	|subquery alias?
	{
		common.addTableSubQuery($subquery.subselect,$alias.aliText);

	}
	;
	
schema_name
	:identifier
	;
subquery returns [Select subselect]:
	^(SUBQUERY select_command)
	{
	$subselect=$select_command.select;
	$subselect.setSubSelect(true);
	}	
	;
table_name[boolean localNeedToRewriteTableName]  //two ways to control whether rewrite table name is necessary, through global variable or constructor parameter of table_name
	:identifier {
		if($localNeedToRewriteTableName || needToRewriteTableName){
			pos2TableName.put($table_name.start.getCharPositionInLine(), $table_name.text);}
		}
	//-> template(a={($localNeedToRewriteTableName || needToRewriteTableName) ? $identifier.text + 0 : $identifier.text}) "<a>"
	;	
displayed_column[Select select]
	:
	^(quoted_string alias?) {$select.addColumn("", $quoted_string.text, $alias.aliText);}
	|^(concat identifiedOrQuotedString (identifiedOrQuotedString)* alias?)
		{
			Concat concat = new Concat();
			concat.setAlias($alias.aliText);
			$select.addColumn(concat);
		}
	|^(count distinct? countColumn alias?) {
		Count count = new Count();
		count.setTable($countColumn.infos.get(0).toString());
		count.setColumn($countColumn.infos.get(1).toString());
		count.setHasDistinct($distinct.text!=null);
		count.setAlias($alias.aliText);
		$select.addColumn(count);
	}
	|^(ID table_alias? columnAnt? alias?)
	{
		GroupFunction func=groupFunc.get($ID.text);
		func.setAlias($alias.aliText);
		func.setTable($table_alias.aText);
		func.setColumn($columnAnt.text);
		$select.addColumn(func);
	}
	/*|^(COLUMN table_alias? columnAnt alias?)
	{$select.addColumn($table_alias.aText,$columnAnt.text,$alias.aliText);}*/
	|^(EXPR expr_add[new BindIndexHolder()] alias?)
	{
		if($expr_add.valueObjExpr instanceof ColumnObject) {
			ColumnObject cobj = (ColumnObject)$expr_add.valueObjExpr;
			$select.addColumn(cobj.getTable(), cobj.getColumnName(), $alias.aliText);
		} else {
			$select.addColumn("", $expr_add.text, $alias.aliText);
		}
	}
	;    
	
columnAnt returns[String aText]
	:ASTERISK {$aText=$ASTERISK.text;}
	|identifier {$aText=$identifier.text;}
	;

quoted_string returns[String aText]
	: QUOTED_STRING {$aText = $QUOTED_STRING.text.substring(1, $QUOTED_STRING.text.length() - 1);}
	| DOUBLEQUOTED_STRING {$aText = $DOUBLEQUOTED_STRING.text.substring(1, $DOUBLEQUOTED_STRING.text.length() - 1);}
	;

identifier
	:ID
	|ASTERISK
   	;
table_alias returns [String aText]
	:^(COL_TAB identifier{$aText=$identifier.text;}	)
	;
	
alias returns [String aliText]
	:^(AS identifier){$aliText=$identifier.text;}
	;
		
/*-------------------------------------------the rules below are Redefined by SubParser 
.For Antlr v3 didn't have any method to reuse rules.So we wrote rules which had been modified by subParser here to make
the refactor easily.-----------------------------*/

select_command returns[Select select]
@init{$select =new MySelect();}
     : selectClause[$select] (fromClause[$select])? (joinClause[$select])* (whereClause[$select.getWhere()])? (groupByClause[$select.getWhere()])? (orderByClause[$select.getWhere()])?  (limitClause[(MyWhereCondition)$select.getWhere()])?
     ;
groupByClause [WhereCondition where]
	: ^(GROUPBY groupByColumns[$where])
	;
groupByColumns [WhereCondition where]
	: (groupByColumn[$where])+
	;
groupByColumn [WhereCondition where]
	: identifier {$where.addGroupByColumn($identifier.text);}
	;
     delete_command returns[Delete del]
@init{$del=new MyDelete();}
	:^(DELETE tables[$del, true] whereClause[$del.getWhere()]? orderByClause[$del.getWhere()]? limitClause[(MyWhereCondition)$del.getWhere()]?)
	;
update_command returns[Update update]
@init{$update=new MyUpdate();}
	:^(UPDATE tables[$update, true] setclause[$update] whereClause[$update.getWhere()]? limitClause[(MyWhereCondition)$update.getWhere()]?
	)
	;
limitClause[MyWhereCondition where]
	:^('LIMIT' skip[$where]? range[$where])
	;
skip[MyWhereCondition where]	
	:^(SKIP N){
		$where.setStart(Integer.valueOf($N.text));
		where.limitInfo.skipIdx=$N.pos;
		where.limitInfo.skip=$N.text;
	}
	|^(SKIP a='?'){
		$where.setStart(DbFunctions.bindVar($where.selfAddAndGet()));
		where.limitInfo.skipIdx=$a.pos;
		where.limitInfo.skip=$a.text;
	}	
	//{$valueObjExpr=DbFunctions.bindVar($where.selfAddAndGet());}						
	;
range[MyWhereCondition where]	:^(RANGE N){
		$where.setRange(Integer.valueOf($N.text));
		where.limitInfo.rangeIdx=$N.pos;
		where.limitInfo.range=$N.text;
	}
|^(RANGE a='?'){
		$where.setRange(DbFunctions.bindVar($where.selfAddAndGet()));
		where.limitInfo.rangeIdx=$a.pos;
		where.limitInfo.range=$a.text;
	}	
	;
joinClause[Select select]
	scope{MyWhereCondition fakeCondition;}
	@init{$joinClause::fakeCondition = new MyWhereCondition();}
	: ^(joinType table_spec[$select, true] alias? 'ON' sqlCondition[$joinClause::fakeCondition]) {$select.incrementBindIndex($joinClause::fakeCondition.selfAddAndGet());}
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
between_and_expression[BindIndexHolder where] returns [Object valueObj]
	:expr_add[$where] {$valueObj=$expr_add.valueObjExpr;}
	| quoted_string {$valueObj = $quoted_string.aText;}
	;
identifierOrN
	:
	(identifier | N)
	;
countColumn returns [List infos]
	: ^(COUNTCOLUMN identifier? identifierOrN) {
		$infos = new ArrayList(2);
		$infos.add($identifier.text==null?"":$identifier.text);
		$infos.add($identifierOrN.text==null?"":$identifierOrN.text);
	}
	;
