package com.dianping.zebra.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.dianping.zebra.dao.entity.HeartbeatEntity;
import com.dianping.zebra.dao.plugin.page.PageModel;

public interface HeartbeatMapper {
	List<HeartbeatEntity> getPage(RowBounds rb);

	void getAll(PageModel page);

	void getAllExcludeAppName(@Param("appName") String appName, PageModel page);
}
