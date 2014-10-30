package com.dianping.zebra.admin.web.dal;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.unidal.dal.jdbc.DalException;
import org.unidal.lookup.ComponentTestCase;

import com.dianping.zebra.admin.datasource.TransactionManagerWrapper;
import com.dianping.zebra.web.dal.lion.Config;
import com.dianping.zebra.web.dal.lion.ConfigDao;
import com.dianping.zebra.web.dal.lion.ConfigEntity;
import com.dianping.zebra.web.dal.lion.ConfigInstance;
import com.dianping.zebra.web.dal.lion.ConfigInstanceDao;
import com.dianping.zebra.web.dal.lion.ConfigInstanceEntity;

public class ConfigTest extends ComponentTestCase {

	private ConfigDao configDao;

	private ConfigInstanceDao configInstanceDao;

	private TransactionManagerWrapper transactionManagerWrapper;

	@Before
	public void setup() {
		configDao = lookup(ConfigDao.class);
		configInstanceDao = lookup(ConfigInstanceDao.class);
		transactionManagerWrapper = lookup(TransactionManagerWrapper.class);
	}

	@Test
	public void test_configDao_findByProjectId() throws DalException {
		List<Config> configs = configDao.findByProjectId(100, ConfigEntity.READSET_FULL);

		System.out.println(configs.size());
	}

	@Test
	public void test_configInstanceDao_findByProjectId() throws DalException {
		List<Config> configs = configDao.findByProjectId(100, ConfigEntity.READSET_FULL);
		int[] configIds = new int[configs.size()];

		for (int i = 0; i < configs.size(); i++) {
			Config config = configs.get(i);

			configIds[i] = config.getId();
		}

		System.out.println(configs.size());

		List<ConfigInstance> configIns = configInstanceDao.findByConfigIdAndEnvId(configIds, 1,
		      ConfigInstanceEntity.READSET_FULL);

		
		System.out.println(configIns.size());
	}
	
	@Test
	public void tt() throws DalException{
		ConfigInstance ci = configInstanceDao.findByConfigKeyAndEnvId("ds.tuangou2010-s1-read.jdbc.password", 1, ConfigInstanceEntity.READSET_FULL);
	
		System.out.println(ci.getValue());
	}

	@Test
	public void test_configInstanceDao_findByDs() throws DalException {
		List<ConfigInstance> findByDs = configInstanceDao.findByDs("%{ds.dianpingac-m1-write.jdbc.url}%", 1,
		      ConfigInstanceEntity.READSET_FULL);

		System.out.println(findByDs.size());
	}

	@Test
	@Ignore
	// in case to change the online database by mistake
	public void test_configInstanceDao_updateReference() throws DalException {
		int[] configInstanceIds = new int[2];
		configInstanceIds[0] = 564;
		configInstanceIds[1] = 654;

		ConfigInstance ins = configInstanceDao.createLocal();
		ins.setConfigIds(configInstanceIds);
		ins.setValue("${ds.tuangoumail-m1-write.jdbc.url}");

		// ${ds.tuangoumail-m1-write.jdbc.url}

		System.out.println(ins);
		configInstanceDao.updateByPKs(ins, ConfigInstanceEntity.UPDATESET_REFERENCE);
	}

	@Test
	@Ignore
	// in case to change the online database by mistake
	public void test_configInstanceDao_with_transaction() throws DalException {
		int[] configInstanceIds = new int[2];
		configInstanceIds[0] = 564;
		configInstanceIds[1] = 654;

		ConfigInstance ins = configInstanceDao.createLocal();
		ins.setConfigIds(configInstanceIds);
		ins.setValue("${ds.tuangoumail-m2-write.jdbc.url}");

		// ${ds.tuangoumail-m1-write.jdbc.url}

		System.out.println(ins);
		// configInstanceDao.getQueryEngine().startTransaction(ConfigInstanceEntity.UPDATE_BY_PKS, ins);
		transactionManagerWrapper.startTransaction(ConfigInstanceEntity.UPDATE_BY_PKS, ins);
		try {
			configInstanceDao.updateByPKs(ins, ConfigInstanceEntity.UPDATESET_REFERENCE);
		} catch (DalException e) {
		} finally {
			// configInstanceDao.getQueryEngine().commitTransaction(ConfigInstanceEntity.UPDATE_BY_PKS, ins);
			transactionManagerWrapper.rollbackTransaction(ConfigInstanceEntity.UPDATE_BY_PKS, ins);
		}
	}
}
