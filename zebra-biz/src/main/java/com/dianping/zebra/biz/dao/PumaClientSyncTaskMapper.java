package com.dianping.zebra.biz.dao;

import com.dianping.zebra.biz.entity.PumaClientSyncTaskEntity;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PumaClientSyncTaskMapper {

	List<PumaClientSyncTaskEntity> findAllByRuleName(@Param("ruleName") String ruleName);

	List<PumaClientSyncTaskEntity> findEffectiveTaskByRuleName(@Param("ruleName") String ruleName);

	List<PumaClientSyncTaskEntity> findEffectiveTaskByExecutor(@Param("executor") String executor);

	void insertSyncTask(PumaClientSyncTaskEntity entity);

	void updateSyncTask(PumaClientSyncTaskEntity entity);

	void updateSyncTaskStatus(@Param("pumaClientName") String pumaClientName, @Param("status") int status);

	void updateSyncTaskSyncServers(@Param("pumaClientName") String pumaClientName, @Param("executor") String executor,
	      @Param("executor1") String executor1, @Param("executor2") String executor2);
}
