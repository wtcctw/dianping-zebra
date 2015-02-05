package com.dianping.zebra.shard.router.rule;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.dianping.zebra.shard.router.rule.engine.GroovyRuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngineEvalContext;

public class GroovyRuleEngineTest {

	@Test
	public void testRule() {
		RuleEngine ruleEngine = new GroovyRuleEngine("(#NoteID#.longValue() % 32).intdiv(8)");
		Map<String, Object> valMap = new HashMap<String, Object>();
		valMap.put("NoteID", 9);
		Object retVal = ruleEngine.eval(new RuleEngineEvalContext(valMap));
		System.out.println(retVal);
	}
	
	@Test
	public void testRule1() {
		RuleEngine ruleEngine = new GroovyRuleEngine("#NoteID#.longValue() % 8");
		Map<String, Object> valMap = new HashMap<String, Object>();
		valMap.put("NoteID", 25);
		Object retVal = ruleEngine.eval(new RuleEngineEvalContext(valMap));
		System.out.println(retVal);
	}
}
