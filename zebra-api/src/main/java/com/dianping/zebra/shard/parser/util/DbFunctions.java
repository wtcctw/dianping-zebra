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
package com.dianping.zebra.shard.parser.util;

import java.util.List;

import com.dianping.zebra.shard.parser.valueObject.ColumnObject;
import com.dianping.zebra.shard.parser.valueObject.NoOperandFunctionObject;
import com.dianping.zebra.shard.parser.valueObject.OraNestedTab;
import com.dianping.zebra.shard.parser.valueObject.TwoOperandsFunctionObject;
import com.dianping.zebra.shard.parser.valueObject.ValueObject;
import com.dianping.zebra.shard.parser.valueObject.ValueObject.NO_OPERAND_FUNC;
import com.dianping.zebra.shard.parser.valueObject.ValueObject.ORA_FUN_TYPE;
import com.dianping.zebra.shard.parser.valueObject.ValueObject.TWO_OPERANDS_FUNC;
import com.dianping.zebra.shard.parser.valueObject.ValueObject.VAR_OPERANDS_FUNC;
import com.dianping.zebra.shard.parser.valueObject.VarOperandsFunctionObject;
import com.dianping.zebra.shard.parser.valueObject.variable.BindVar;

/**
 * 数据库函数字段,可创建一般的双值函数，列名字段以及数据库默认字段函数。 所有方法均为静态方法，可直接使用静态引用的方式引入到程序中
 * 
 */
public class DbFunctions {
	public final static NoOperandFunctionObject sysdate = createNoOperandFunctionObject(NO_OPERAND_FUNC.SYSDATE);


	public final static NoOperandFunctionObject NULL = createNoOperandFunctionObject(NO_OPERAND_FUNC.NULL);

	/**
	 * 列名包装字段，写入这里的字段会被检查是否为Column Name，如果不是在运行sql的时候会报异常
	 * 与普通的Object对象（也就是java基本类型或String
	 * ,Date)对象相比，他的好处是不会在sql中被转为?然后通过setObject()的方式传入数据库中。
	 * 
	 * @param columnName
	 * @return
	 */
	public static ColumnObject column(String columnName) {
		return createColumnObject(columnName);
	}
	public static ValueObject bindVar(int index){
		return new BindVar(index);
	}
	public static ColumnObject column(String columnName,String alias){
		return createColumnObject(columnName, alias);
	}
	/**
	 * SELECT * FROM TABLE(CAST(STR2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInStr(String listToken, int rowNum) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2VARLIST, listToken, rowNum);
	}
	/**
	 * SELECT * FROM TABLE(CAST(STR2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInStr(List<Object> listToken, int rowNum) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2VARLIST, listToken, rowNum);
	}
	/**
	 * SELECT * FROM TABLE(CAST(STR2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInStr(Object[] listToken, int rowNum) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2VARLIST, listToken, rowNum);
	}
	/**
	 * SELECT * FROM TABLE(CAST(STR2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInStr(String listToken) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2VARLIST, listToken);
	}
	/**
	 * SELECT * FROM TABLE(CAST(STR2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInStr(List<Object> listToken) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2VARLIST, listToken);
	}
	/**
	 * SELECT * FROM TABLE(CAST(STR2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInStr(Object[] listToken) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2VARLIST, listToken);
	}
	/**
	 * SELECT * FROM TABLE(CAST(NUM2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInNum(Object[] listToken, int rowNum) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2NUMLIST, listToken, rowNum);
	}
	/**
	 * SELECT * FROM TABLE(CAST(NUM2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInNum(List<Object> listToken, int rowNum) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2NUMLIST, listToken, rowNum);
	}
	/**
	 * SELECT * FROM TABLE(CAST(NUM2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInNum(String listToken, int rowNum) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2NUMLIST, listToken, rowNum);
	}

	/**
	 * SELECT * FROM TABLE(CAST(NUM2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInNum(String listToken) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2NUMLIST, listToken);
	}
	/**
	 * SELECT * FROM TABLE(CAST(NUM2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInNum(List<Object> listToken) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2NUMLIST, listToken);
	}
	/**
	 * SELECT * FROM TABLE(CAST(NUM2VARLIST(#listToken#) AS VARTABLETYPE)) WHERE
	 * rownum<=#rowNum#
	 * 
	 * @param listToken
	 * @param rowNum
	 * @return
	 */
	public static OraNestedTab buildNestedTabInNum(Object[] listToken) {
		return createORaNestedTab(ORA_FUN_TYPE.STR2NUMLIST, listToken);
	}

	/**
	 * oracle bitand函数:bitand(arg1,arg2)
	 * 
	 * @param arg1
	 *            支持嵌套的各种值类型的exp1 ，具体包括：
	 *            <ul>
	 *            <li>
	 *            
	 *            <b>基本类型：</b>java的基本数据类型以及其包装类，解释器会自动转换为key=?的形式并将该基本数据类型以setObject
	 *            ()的形式输入到perpareStatement中</li>
	 *            <li>
	 *            <b>List类型：</b>如果是List类型，则会自动将list中的每一项自动的进行分析，分别再转化为<b>基本类型
	 *            、List类型或ValueObject</b>进行处理，两个List中的对象之间，自动以","分隔</b></li>
	 *            <li>
	 *            <b>{@link ValueObject}
	 *            类型：</b>如果是ValueObject类型，则会按照ValueObject类型的相关规则进行处理</li>
	 *            </ul>
	 * @param arg2
	 *            支持嵌套的各种值类型的exp1 ，具体包括：
	 *            <ul>
	 *            <li>
	 *            
	 *            <b>基本类型：</b>java的基本数据类型以及其包装类，解释器会自动转换为key=?的形式并将该基本数据类型以setObject
	 *            ()的形式输入到perpareStatement中</li>
	 *            <li>
	 *            <b>List类型：</b>如果是List类型，则会自动将list中的每一项自动的进行分析，分别再转化为<b>基本类型
	 *            、List类型或ValueObject</b>进行处理，两个List中的对象之间，自动以","分隔</b></li>
	 *            <li>
	 *            <b>{@link ValueObject}
	 *            类型：</b>如果是ValueObject类型，则会按照ValueObject类型的相关规则进行处理</li>
	 *            </ul>
	 * @return
	 */
	public static TwoOperandsFunctionObject bitAnd(Object arg1, Object arg2) {
		return createTwoOperandsFunctionObject(TWO_OPERANDS_FUNC.BITAND, arg1,
				arg2);
	}

	/**
	 * oracle bitor函数:bitor(arg1,arg2)
	 * 
	 * @param arg1
	 *            支持嵌套的各种值类型的exp1 ，具体包括：
	 *            <ul>
	 *            <li>
	 *            
	 *            <b>基本类型：</b>java的基本数据类型以及其包装类，解释器会自动转换为key=?的形式并将该基本数据类型以setObject
	 *            ()的形式输入到perpareStatement中</li>
	 *            <li>
	 *            <b>List类型：</b>如果是List类型，则会自动将list中的每一项自动的进行分析，分别再转化为<b>基本类型
	 *            、List类型或ValueObject</b>进行处理，两个List中的对象之间，自动以","分隔</b></li>
	 *            <li>
	 *            <b>{@link ValueObject}
	 *            类型：</b>如果是ValueObject类型，则会按照ValueObject类型的相关规则进行处理</li>
	 *            </ul>
	 * @param arg2
	 *            支持嵌套的各种值类型的exp1 ，具体包括：
	 *            <ul>
	 *            <li>
	 *            
	 *            <b>基本类型：</b>java的基本数据类型以及其包装类，解释器会自动转换为key=?的形式并将该基本数据类型以setObject
	 *            ()的形式输入到perpareStatement中</li>
	 *            <li>
	 *            <b>List类型：</b>如果是List类型，则会自动将list中的每一项自动的进行分析，分别再转化为<b>基本类型
	 *            、List类型或ValueObject</b>进行处理，两个List中的对象之间，自动以","分隔</b></li>
	 *            <li>
	 *            <b>{@link ValueObject}
	 *            类型：</b>如果是ValueObject类型，则会按照ValueObject类型的相关规则进行处理</li>
	 *            </ul>
	 * @return
	 */
	public static TwoOperandsFunctionObject bitOr(Object arg1, Object arg2) {
		return createTwoOperandsFunctionObject(TWO_OPERANDS_FUNC.BITOR, arg1,
				arg2);
	}

	/**
	 * taobao bitxor函数:bitxor_tb(arg1,arg2)
	 * 
	 * @param arg1
	 *            支持嵌套的各种值类型的exp1 ，具体包括：
	 *            <ul>
	 *            <li>
	 *            
	 *            <b>基本类型：</b>java的基本数据类型以及其包装类，解释器会自动转换为key=?的形式并将该基本数据类型以setObject
	 *            ()的形式输入到perpareStatement中</li>
	 *            <li>
	 *            <b>List类型：</b>如果是List类型，则会自动将list中的每一项自动的进行分析，分别再转化为<b>基本类型
	 *            、List类型或ValueObject</b>进行处理，两个List中的对象之间，自动以","分隔</b></li>
	 *            <li>
	 *            <b>{@link ValueObject}
	 *            类型：</b>如果是ValueObject类型，则会按照ValueObject类型的相关规则进行处理</li>
	 *            </ul>
	 * @param arg2
	 *            支持嵌套的各种值类型的exp1 ，具体包括：
	 *            <ul>
	 *            <li>
	 *            
	 *            <b>基本类型：</b>java的基本数据类型以及其包装类，解释器会自动转换为key=?的形式并将该基本数据类型以setObject
	 *            ()的形式输入到perpareStatement中</li>
	 *            <li>
	 *            <b>List类型：</b>如果是List类型，则会自动将list中的每一项自动的进行分析，分别再转化为<b>基本类型
	 *            、List类型或ValueObject</b>进行处理，两个List中的对象之间，自动以","分隔</b></li>
	 *            <li>
	 *            <b>{@link ValueObject}
	 *            类型：</b>如果是ValueObject类型，则会按照ValueObject类型的相关规则进行处理</li>
	 *            </ul>
	 * @return
	 */
	public static TwoOperandsFunctionObject bitXor(Object arg1, Object arg2) {
		return createTwoOperandsFunctionObject(TWO_OPERANDS_FUNC.BITXOR_TB,
				arg1, arg2);
	}

	/**
	 * oracle nvl函数:nvl(arg1,arg2)
	 * 
	 * @param arg1
	 *            支持嵌套的各种值类型的exp1 ，具体包括：
	 *            <ul>
	 *            <li>
	 *            
	 *            <b>基本类型：</b>java的基本数据类型以及其包装类，解释器会自动转换为key=?的形式并将该基本数据类型以setObject
	 *            ()的形式输入到perpareStatement中</li>
	 *            <li>
	 *            <b>List类型：</b>如果是List类型，则会自动将list中的每一项自动的进行分析，分别再转化为<b>基本类型
	 *            、List类型或ValueObject</b>进行处理，两个List中的对象之间，自动以","分隔</b></li>
	 *            <li>
	 *            <b>{@link ValueObject}
	 *            类型：</b>如果是ValueObject类型，则会按照ValueObject类型的相关规则进行处理</li>
	 *            </ul>
	 * @param arg2
	 *            支持嵌套的各种值类型的exp1 ，具体包括：
	 *            <ul>
	 *            <li>
	 *            
	 *            <b>基本类型：</b>java的基本数据类型以及其包装类，解释器会自动转换为key=?的形式并将该基本数据类型以setObject
	 *            ()的形式输入到perpareStatement中</li>
	 *            <li>
	 *            <b>List类型：</b>如果是List类型，则会自动将list中的每一项自动的进行分析，分别再转化为<b>基本类型
	 *            、List类型或ValueObject</b>进行处理，两个List中的对象之间，自动以","分隔</b></li>
	 *            <li>
	 *            <b>{@link ValueObject}
	 *            类型：</b>如果是ValueObject类型，则会按照ValueObject类型的相关规则进行处理</li>
	 *            </ul>
	 * @return
	 */
	public static TwoOperandsFunctionObject nvl(Object arg1, Object arg2) {
		return createTwoOperandsFunctionObject(TWO_OPERANDS_FUNC.NVL, arg1,
				arg2);
	}

	public static TwoOperandsFunctionObject concat(Object arg1, Object arg2) {
		return createTwoOperandsFunctionObject(TWO_OPERANDS_FUNC.CONCAT, arg1,
				arg2);
	}

	public static TwoOperandsFunctionObject add(Object arg1, Object arg2) {
		return createTwoOperandsFunctionObject(TWO_OPERANDS_FUNC.ADD, arg1,
				arg2);
	}

	
	public static TwoOperandsFunctionObject subtract(Object arg1, Object arg2) {
		return createTwoOperandsFunctionObject(TWO_OPERANDS_FUNC.SUBTRACT,
				arg1, arg2);
	}

	
	public static TwoOperandsFunctionObject multiply(Object arg1, Object arg2) {
		return createTwoOperandsFunctionObject(TWO_OPERANDS_FUNC.MULTIPLY,
				arg1, arg2);
	}

	
	public static TwoOperandsFunctionObject divide(Object arg1, Object arg2) {
		return createTwoOperandsFunctionObject(TWO_OPERANDS_FUNC.DIVIDE, arg1,
				arg2);
	}
	
	public static TwoOperandsFunctionObject mod(Object arg1, Object arg2) {
		return createTwoOperandsFunctionObject(TWO_OPERANDS_FUNC.MOD, arg1,
				arg2);
	}

	public static VarOperandsFunctionObject decode(Object... args) {
	
		return createVarOperandsFunctionObject(VAR_OPERANDS_FUNC.DECODE, args);
	}

	private static NoOperandFunctionObject createNoOperandFunctionObject(
			NO_OPERAND_FUNC func) {
		return new NoOperandFunctionObject(func);

	}


	private static TwoOperandsFunctionObject createTwoOperandsFunctionObject(
			TWO_OPERANDS_FUNC func, Object arg1, Object arg2) {
		return new TwoOperandsFunctionObject(func, arg1, arg2);
	}

	private static VarOperandsFunctionObject createVarOperandsFunctionObject(
			VAR_OPERANDS_FUNC func, Object... args) {
		if(args==null){
			args=new Object[]{};
		}
		return new VarOperandsFunctionObject(func, args);
	}

	private static ColumnObject createColumnObject(String columnName) {
		return new ColumnObject(columnName);
	}
	private static ColumnObject createColumnObject(String columnName,String alias) {
		return new ColumnObject(columnName,alias);
	}
	private static OraNestedTab createORaNestedTab(ORA_FUN_TYPE oraFunType,
			String StringToken, int rowNum) {
		return new OraNestedTab(StringToken, rowNum, oraFunType);
	}
	private static OraNestedTab createORaNestedTab(ORA_FUN_TYPE oraFunType,
			List<Object> listStr, int rowNum) {
		return new OraNestedTab(listStr, rowNum, oraFunType);
	}
	private static OraNestedTab createORaNestedTab(ORA_FUN_TYPE oraFunType,
			Object[] listStr, int rowNum) {
		return new OraNestedTab(listStr, rowNum, oraFunType);
	}
	private static OraNestedTab createORaNestedTab(ORA_FUN_TYPE oraFunType,
			String StringToken) {
		return new OraNestedTab(StringToken, oraFunType);
	}
	private static OraNestedTab createORaNestedTab(ORA_FUN_TYPE oraFunType,
			List<Object> listStr) {
		return new OraNestedTab(listStr, oraFunType);
	}
	private static OraNestedTab createORaNestedTab(ORA_FUN_TYPE oraFunType,
			Object[] listStr) {
		return new OraNestedTab(listStr, oraFunType);
	}
}
