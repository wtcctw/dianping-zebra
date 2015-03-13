package com.dianping.zebra.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.dao.HeartbeatMapper;
import com.dianping.zebra.admin.service.LionHttpService;
import com.dianping.zebra.admin.service.ReportService;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/update")
public class UpdateController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private LionHttpService lionHttpService;
    
    @Autowired
    private HeartbeatMapper heartBeatMapper;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public Object index() {
        boolean isProduct = lionHttpService.isProduct();
        return reportService.getReport(isProduct);
    }

    @RequestMapping(value = "/database", method = RequestMethod.GET)
    @ResponseBody
    public Object database(String database) {
        boolean isProduct = lionHttpService.isProduct();
        return reportService.getDatabase(database, isProduct);
    }

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    @ResponseBody
    public Object app(String app) {
        boolean isProduct = lionHttpService.isProduct();
        return reportService.getApp(app, isProduct);
    }

    @RequestMapping(value = "/delete-info", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(String app,String ip,String beanName) {
        return heartBeatMapper.deleteHeartbeat(app, ip, beanName);
    }

}
