package com.dianping.zebra.admin.service.impl;

import com.dianping.cat.Cat;
import com.dianping.zebra.admin.dao.SyncServerMonitorMapper;
import com.dianping.zebra.admin.entity.SyncServerMonitorEntity;
import com.dianping.zebra.admin.service.SyncServerMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Dozer @ 6/4/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
@Service
public class SyncServerMonitorServiceImpl implements SyncServerMonitorService {

    @Autowired
    private SyncServerMonitorMapper syncServerMonitorMapper;

    @Override
    public SyncServerMonitorEntity chooseOne() {
        List<SyncServerMonitorEntity> servers = syncServerMonitorMapper.getAllAlive();

        SyncServerMonitorEntity target = null;
        for (SyncServerMonitorEntity server : servers) {
            if (target == null || target.getLoad() > server.getLoad()) {
                target = server;
            }
        }
        return target;
    }

    @Override
    public void uploadStatus() {
        SyncServerMonitorEntity item = new SyncServerMonitorEntity();
        try {
            item.setName(InetAddress.getLocalHost().getHostAddress());
            //todo: get load
        } catch (UnknownHostException e) {
            Cat.logError(e);
        }

        int row = syncServerMonitorMapper.update(item);
        if (row == 0) {
            syncServerMonitorMapper.create(item);
        }
    }
}
