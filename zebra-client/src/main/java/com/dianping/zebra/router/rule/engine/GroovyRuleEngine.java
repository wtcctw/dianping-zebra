/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-15
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.router.rule.engine;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import com.dianping.zebra.router.config.RouterConfigException;
import com.dianping.zebra.router.rule.AbstractDimensionRule;
import com.dianping.zebra.router.rule.DimensionRule;

/**
 * TODO 对groovy-all jar包进行瘦身，官方没有，自行提出一个groovy-core包
 * @author danson.liu
 *
 */
public class GroovyRuleEngine implements RuleEngine {
	
	private GroovyObject engineObj;

	public GroovyRuleEngine(String rule) {
		try {
			Matcher matcher = DimensionRule.RULE_COLUMN_PATTERN.matcher(rule);
			StringBuilder engineClazzImpl = new StringBuilder(200)
				.append("class RuleEngine {")
				.append("Object execute(Map context) {")
				.append(matcher.replaceAll("context.get(\"$1\")"))
				.append("}")
				.append("}");
			GroovyClassLoader loader = new GroovyClassLoader(AbstractDimensionRule.class.getClassLoader());
			Class<?> engineClazz = loader.parseClass(engineClazzImpl.toString());
			engineObj = (GroovyObject) engineClazz.newInstance();
		} catch (Exception e) {
			throw new RouterConfigException("Construct groovy rule engine failed, cause: ", e);
		}
	}

	@Override
	public Object eval(RuleEngineEvalContext evalContext) {
		Map<String, Object> valMap = evalContext.getValMap();
		return engineObj.invokeMethod("execute", valMap);
	}
	
	public static void main(String[] args) {
		RuleEngine ruleEngine = new GroovyRuleEngine("(#NoteID#.longValue() % 32).intdiv(8)");
		Map<String, Object> valMap = new HashMap<String, Object>();
		valMap.put("NoteID", 9);
		Object retVal = ruleEngine.eval(new RuleEngineEvalContext(valMap));
		System.out.println(retVal);
	}

}
