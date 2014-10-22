package com.dianping.zebra.admin.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.unidal.dal.jdbc.DalException;
import org.unidal.lookup.annotation.Inject;

import com.dianping.zebra.admin.datasource.TransactionManagerWrapper;
import com.dianping.zebra.web.dal.lion.Config;
import com.dianping.zebra.web.dal.lion.ConfigDao;
import com.dianping.zebra.web.dal.lion.ConfigEntity;
import com.dianping.zebra.web.dal.lion.ConfigInstance;
import com.dianping.zebra.web.dal.lion.ConfigInstanceDao;
import com.dianping.zebra.web.dal.lion.ConfigInstanceEntity;

public class MergeConfigServiceImpl implements MergeConfigService {

	@Inject
	private ConfigInstanceDao configInstanceDao;

	@Inject
	private ConfigDao configDao;

	@Inject
	private TransactionManagerWrapper transactionManager;

	private final int PROJECT_DS = 100;

	private final int PROJECT_GROUPDS = 10864;

	private void deleteConfigInstance(List<String> from, String to, Env env) throws DalException {
		List<Integer> ids = new ArrayList<Integer>();
		for (String ds : from) {
			if (!ds.equals(to)) {
				List<Config> configs = configDao.findByProjectAndDs(PROJECT_DS, "%" + ds + "%", ConfigEntity.READSET_FULL);

				for (Config config : configs) {
					if (isDalKey(ds, config.getKey())) {
						ids.add(config.getId());
					}
				}
			}
		}

		int[] ids_ = new int[ids.size()];
		for (int i = 0; i < ids.size(); i++) {
			ids_[i] = ids.get(i).intValue();
		}

		ConfigInstance cins = configInstanceDao.createLocal();
		cins.setConfigIds(ids_);
		cins.setEnvId(env.getValue());

		configInstanceDao.deleteByConfigIdsAndEnvId(cins);
	}

	private boolean isDalKey(String ds, String key) {
		if (key.equals(String.format("ds.%s.jdbc.url", ds)) || key.equals(String.format("ds.%s.jdbc.username", ds))
		      || key.equals(String.format("ds.%s.jdbc.password", ds))
		      || key.equals(String.format("ds.%s.jdbc.active", ds))
		      || key.equals(String.format("ds.%s.jdbc.driverClass", ds))
		      || key.equals(String.format("ds.%s.jdbc.properties", ds))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean merge(List<String> from, String to, Env env) {
		try {
			transactionManager.startTransaction(ConfigInstanceEntity.UPDATE_BY_PKS, configInstanceDao.createLocal());
			// merge reference
			mergeInternal(from, to, "url", env);
			mergeInternal(from, to, "username", env);
			mergeInternal(from, to, "password", env);

			// delete unused config instance references
			deleteConfigInstance(from, to, env);

			// update groupds key
			updateGroupDs(from, to, env);

			transactionManager.commitTransaction(ConfigInstanceEntity.UPDATE_BY_PKS, configInstanceDao.createLocal());
		} catch (DalException e) {
			try {
				transactionManager.rollbackTransaction(ConfigInstanceEntity.UPDATE_BY_PKS, configInstanceDao.createLocal());
			} catch (DalException e1) {
			}

			return false;
		}

		return true;
	}

	private void mergeInternal(List<String> from, String to, String key, Env env) throws DalException {
		List<ConfigInstance> targets = new ArrayList<ConfigInstance>();
		// search lion key which refer to from
		for (String fromItem : from) {
			if (!fromItem.equals(to)) {
				String dsKey = "%${ds." + fromItem + ".jdbc." + key + "}%";
				List<ConfigInstance> partTargets = configInstanceDao.findByDs(dsKey, env.getValue(),
				      ConfigInstanceEntity.READSET_FULL);

				for (ConfigInstance config : partTargets) {
					targets.add(config);
				}
			}
		}

		// change them refer to new to
		int[] configInstanceIds = new int[targets.size()];
		int i = 0;
		for (ConfigInstance entry : targets) {
			configInstanceIds[i] = entry.getId();
			i++;
		}

		ConfigInstance ins = configInstanceDao.createLocal();
		ins.setConfigIds(configInstanceIds);
		ins.setValue("${ds." + to + ".jdbc." + key + "}");

		configInstanceDao.updateByPKs(ins, ConfigInstanceEntity.UPDATESET_REFERENCE);
	}

	private void updateGroupDs(List<String> from, String to, Env env) throws DalException {
		Set<ConfigInstance> targets = new HashSet<ConfigInstance>();
		List<ConfigInstance> targets_ = new ArrayList<ConfigInstance>();
		HashMap<Integer, String> origin = new HashMap<Integer, String>();
		List<Config> configs = configDao.findByProjectId(PROJECT_GROUPDS, ConfigEntity.READSET_FULL);
		int[] ids = new int[configs.size()];
		for (int i = 0; i < configs.size(); i++) {
			ids[i] = configs.get(i).getId();
		}

		List<ConfigInstance> configIns = configInstanceDao.findByConfigIdAndEnvId(ids, env.getValue(),
		      ConfigInstanceEntity.READSET_FULL);

		for (ConfigInstance ins : configIns) {
			for (String ds : from) {
				String groupds = ins.getValue();
				if (groupds.contains(ds) && !ds.equals(to)) {
					ins.setValue(groupds.replace(ds, to));
					origin.put(ins.getId(), groupds);
					targets.add(ins);
				}
			}
		}

		for (ConfigInstance ins : targets) {
			configInstanceDao.updateByPK(ins, ConfigInstanceEntity.UPDATESET_REFERENCE);
		}

		for (ConfigInstance ins : targets) {
			ins.setValue(origin.get(ins.getId()));
			targets_.add(ins);
		}
	}
}
