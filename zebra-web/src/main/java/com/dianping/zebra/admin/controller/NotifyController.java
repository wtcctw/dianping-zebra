//package com.dianping.zebra.admin.controller;
//
//import com.dianping.zebra.admin.entity.HeartbeatEntity;
//import com.dianping.zebra.admin.service.DalConfigService;
//import com.dianping.zebra.admin.service.DalService;
//import com.dianping.zebra.admin.service.LogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * Dozer @ 2015-02
// * mail@dozer.cc
// * http://www.dozer.cc
// */
//
//@Controller
//@RequestMapping(value = "/notify")
//public class NotifyController {
//    @Autowired
//    private DalConfigService m_dalConfigService;
//    @Autowired
//    private DalService m_dalService;
//    @Autowired
//    private LogService m_logService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public Object notify() {
////        if (payload.getApp() != null && payload.getIp() != null && payload.getDataSourceBeanName() != null) {
////            HeartbeatEntity hb = new HeartbeatEntity();
////
////            hb.setAppName(payload.getApp().toLowerCase());
////            hb.setIp(payload.getIp());
////            hb.setDatasourceBeanName(payload.getDataSourceBeanName());
////            hb.setDatabaseName(payload.getDatabase() == null ? NOT_FOUND : payload.getDatabase().toLowerCase());
////            hb.setDatasourceBeanClass(payload.getDataSourceBeanClass() == null ? NOT_FOUND : payload
////                    .getDataSourceBeanClass());
////            hb.setInitPoolSize(payload.getInitPoolSize());
////            hb.setMaxPoolSize(payload.getMaxPoolSize());
////            hb.setMinPoolSize(payload.getMinPoolSize());
////            hb.setReplaced(payload.isReplaced());
////            hb.setVersion(payload.getVersion() == null ? NOT_FOUND : payload.getVersion());
////            hb.setUsername(payload.getUsername() == null ? NOT_FOUND : payload.getUsername());
////            String jdbcUrl = payload.getUrl();
////            if (jdbcUrl != null) {
////                hb.setJdbcUrl(jdbcUrl);
////                String[] parts = jdbcUrl.split(":");
////                if (parts != null && parts.length > 2) {
////                    hb.setDatabaseType(parts[1].toLowerCase());
////                }
////            } else {
////                hb.setJdbcUrl(NOT_FOUND);
////                hb.setDatabaseType(NOT_FOUND);
////            }
////
////            m_reportService.createOrUpdate(hb);
////        }
//
//        return null;
//    }
//}
