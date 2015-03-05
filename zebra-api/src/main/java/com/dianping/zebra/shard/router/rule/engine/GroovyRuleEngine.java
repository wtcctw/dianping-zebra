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
package com.dianping.zebra.shard.router.rule.engine;

import com.dianping.zebra.shard.config.RouterConfigException;
import com.dianping.zebra.shard.router.rule.AbstractDimensionRule;
import com.dianping.zebra.shard.router.rule.DimensionRule;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.util.Map;
import java.util.regex.Matcher;

/**
 * TODO 对groovy-all jar包进行瘦身，官方没有，自行提出一个groovy-core包
 *
 * @author danson.liu, Dozer
 */
public class GroovyRuleEngine implements RuleEngine {

    private GroovyObject engineObj;

    @SuppressWarnings("resource")
    public GroovyRuleEngine(String rule) {
        try {
            Matcher matcher = DimensionRule.RULE_COLUMN_PATTERN.matcher(rule);
            StringBuilder engineClazzImpl = new StringBuilder(200).append("class RuleEngine extends " + RuleEngineBase.class.getName() + "{")
                    .append("Object execute(Map context) {").append(matcher.replaceAll("context.get(\"$1\")")).append("}")
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
}
