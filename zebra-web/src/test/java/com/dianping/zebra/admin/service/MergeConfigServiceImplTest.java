package com.dianping.zebra.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.admin.admin.service.MergeConfigService;
import com.dianping.zebra.admin.admin.service.MergeConfigService.Env;

public class MergeConfigServiceImplTest extends ComponentTestCase {

	private MergeConfigService m_mergeConfigService;

	@Before
	public void setup() {
		m_mergeConfigService = lookup(MergeConfigService.class);
	}

	@Test
	@Ignore
	// in case to change the online database by mistake
	public void test() {
		List<String> from = new ArrayList<String>();
		from.add("tuangou2010-m2-write");
		from.add("tuangou2010-m3-write");
		from.add("tuangou2010-m4-write");

		String to = "tuangou2010-m2-write";

		System.out.println(m_mergeConfigService.merge(from, to, Env.DEV));
	}
}
