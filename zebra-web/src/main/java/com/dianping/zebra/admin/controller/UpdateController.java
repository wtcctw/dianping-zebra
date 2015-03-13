package com.dianping.zebra.admin.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.dao.HeartbeatMapper;
import com.dianping.zebra.admin.dto.DatabaseDto;
import com.dianping.zebra.admin.dto.ReportDto;
import com.dianping.zebra.admin.service.DalConfigService;
import com.dianping.zebra.admin.service.LionService;
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
    private LionService lionService;
    
    @Autowired
    private HeartbeatMapper heartBeatMapper;
    
    @Autowired
    private DalConfigService dalConfigService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public Object index() {
        ReportDto report = reportService.getReport(lionService.isProduct());
        
        Set<String> whiteList = dalConfigService.getWhiteList(lionService.getEnv());
        for(DatabaseDto db : report.getDatabases().values()){
      	  boolean contains = whiteList.contains(db.getName().toLowerCase());
      	  
      	  db.setInWhiteList(contains);
        }
        
        return report;
    }

    @RequestMapping(value = "/database", method = RequestMethod.GET)
    @ResponseBody
    public Object database(String database) {
        boolean isProduct = lionService.isProduct();
        return reportService.getDatabase(database, isProduct);
    }

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    @ResponseBody
    public Object app(String app) {
        boolean isProduct = lionService.isProduct();
        return reportService.getApp(app, isProduct);
    }

    @RequestMapping(value = "/delete-info", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(String app,String ip,String beanName) {
        return heartBeatMapper.deleteHeartbeat(app, ip, beanName);
    }
}
