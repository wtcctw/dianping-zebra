package com.dianping.zebra.admin.service;

import com.dianping.zebra.admin.dto.AppDto;
import com.dianping.zebra.admin.dto.DatabaseDto;
import com.dianping.zebra.admin.dto.ReportDto;
import com.dianping.zebra.admin.entity.HeartbeatEntity;

public interface ReportService {

    public void createOrUpdate(HeartbeatEntity heartbeat);

    public ReportDto getReport(boolean hasExtraInfo);

    public AppDto getApp(String app, boolean hasExtraInfo);

    public DatabaseDto getDatabase(String database, boolean hasExtraInfo);

}