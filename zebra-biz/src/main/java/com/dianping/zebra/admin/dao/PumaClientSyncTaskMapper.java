package com.dianping.zebra.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;

public interface PumaClientSyncTaskMapper {

	List<PumaClientSyncTaskEntity> findAllByRuleName(@Param("ruleName") String ruleName);

	List<PumaClientSyncTaskEntity> findEffectiveTaskByRuleName(@Param("ruleName") String ruleName);

	void insertSyncTask(PumaClientSyncTaskEntity entity);

	void updateSyncTask(PumaClientSyncTaskEntity entity);
}
