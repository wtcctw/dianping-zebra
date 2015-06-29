package com.dianping.zebra.admin.controller;

import com.dianping.zebra.biz.entity.SyncServerMonitorEntity;
import com.dianping.zebra.biz.service.SyncServerMonitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Dozer @ 6/4/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
@Controller
@RequestMapping(value = "/sync-server")
public class SyncServerController extends BasicController {

    @Autowired
    private SyncServerMonitorService syncServerMonitorService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<SyncServerMonitorEntity> index() {
        return syncServerMonitorService.getAllAlive();
    }
}
