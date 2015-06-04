package com.dianping.zebra.admin.service;

import com.dianping.zebra.admin.entity.SyncServerMonitorEntity;

/**
 * Dozer @ 6/4/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface SyncServerMonitorService {
    SyncServerMonitorEntity chooseOne();

    void uploadStatus();
}
