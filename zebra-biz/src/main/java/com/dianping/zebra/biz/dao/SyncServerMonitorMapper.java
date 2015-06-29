package com.dianping.zebra.biz.dao;

import com.dianping.zebra.biz.entity.SyncServerMonitorEntity;

import java.util.List;

/**
 * Dozer @ 6/4/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface SyncServerMonitorMapper {

    List<SyncServerMonitorEntity> getAllAlive();

    List<SyncServerMonitorEntity> getAllSyncServer();

    int update(SyncServerMonitorEntity entity);

    void create(SyncServerMonitorEntity entity);
}