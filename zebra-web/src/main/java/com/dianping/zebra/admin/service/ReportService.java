package com.dianping.zebra.admin.service;

import com.dianping.zebra.biz.dto.AppDto;
import com.dianping.zebra.biz.dto.DatabaseDto;
import com.dianping.zebra.biz.dto.ReportDto;
import com.dianping.zebra.biz.entity.HeartbeatEntity;

public interface ReportService {

    public void createOrUpdate(HeartbeatEntity heartbeat);

    public ReportDto getReport(boolean hasExtraInfo);

    public AppDto getApp(String app, boolean hasExtraInfo);

    public DatabaseDto getDatabase(String database, boolean hasExtraInfo);

}