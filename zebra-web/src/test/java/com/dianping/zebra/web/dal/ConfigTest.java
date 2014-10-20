package com.dianping.zebra.web.dal;

import org.junit.Before;
import org.junit.Test;
import org.unidal.dal.jdbc.DalException;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.web.dal.lion.Config;
import com.dianping.zebra.web.dal.lion.ConfigDao;
import com.dianping.zebra.web.dal.lion.ConfigEntity;

public class ConfigTest extends ComponentTestCase {

	private ConfigDao  configDao;
	
	@Before
	public void setup() {
		configDao = lookup(ConfigDao.class);
	}
	
	@Test
	public void test() throws DalException{
		Config config = configDao.findByPK(551, ConfigEntity.READSET_FULL);
		
		System.out.println(config);
	}
}
