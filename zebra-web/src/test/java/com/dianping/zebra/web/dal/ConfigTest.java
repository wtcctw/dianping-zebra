package com.dianping.zebra.web.dal;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.unidal.dal.jdbc.DalException;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.web.dal.lion.Config;
import com.dianping.zebra.web.dal.lion.ConfigDao;
import com.dianping.zebra.web.dal.lion.ConfigEntity;
import com.dianping.zebra.web.dal.lion.ConfigInstance;
import com.dianping.zebra.web.dal.lion.ConfigInstanceDao;
import com.dianping.zebra.web.dal.lion.ConfigInstanceEntity;

public class ConfigTest extends ComponentTestCase {

	private ConfigDao  configDao;
	
	private ConfigInstanceDao configInstanceDao;
	
	@Before
	public void setup() {
		configDao = lookup(ConfigDao.class);
		configInstanceDao = lookup(ConfigInstanceDao.class);
	}
	
	@Test
	public void test_configDao_findByProjectId() throws DalException{
		List<Config> configs = configDao.findByProjectId(100, ConfigEntity.READSET_FULL);
		
		System.out.println(configs.size());
	}
	
	@Test
	public void test_configInstanceDao_findByProjectId() throws DalException{
		List<Config> configs = configDao.findByProjectId(100, ConfigEntity.READSET_FULL);
		int[] configIds = new int[configs.size()];
		
		for(int i = 0 ; i < configs.size() ; i++){
			Config config = configs.get(i);
			
			configIds[i] = config.getId();
		}
		
		System.out.println(configs.size());
		
		List<ConfigInstance> configIns = configInstanceDao.findByConfigIdAndEnvId(configIds, 1, ConfigInstanceEntity.READSET_FULL);
		
		
		System.out.println(configIns.size());
	}
}
