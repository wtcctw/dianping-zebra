package com.dianping.zebra.admin.dao;

import com.dianping.zebra.admin.entity.PumaClientStatusEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Dozer @ 6/12/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface PumaClientStatusMapper {
	PumaClientStatusEntity selectByTaskId(@Param(value = "taskId") int selectByTaskId);

	void create(PumaClientStatusEntity entity);

	void updateSequence(PumaClientStatusEntity entity);
}
