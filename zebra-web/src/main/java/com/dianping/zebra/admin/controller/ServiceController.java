package com.dianping.zebra.admin.controller;

import com.dianping.zebra.admin.service.DalConfigService;
import com.dianping.zebra.admin.service.DalResult;
import com.dianping.zebra.admin.service.DalService;
import com.dianping.zebra.admin.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/service")
public class ServiceController {
    @Autowired
    private DalConfigService m_dalConfigService;
    @Autowired
    private DalService m_dalService;
    @Autowired
    private LogService m_logService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Object test(String action, String ip, String port, String user, String env, String database, String name, String[] ips) throws Exception {
        DalResult result = null;

        if ("markDown".equals(action)) {
            if (ip != null && port != null && env != null) {
                result = m_dalService.markDown(env, ip, port, database);
            } else {
                throw new Exception("ip or port or env cannot be null");
            }
        } else if ("markUp".equals(action)) {
            if (ip != null && port != null && env != null) {
                result = m_dalService.markUp(env, ip, port, database);
            } else {
                throw new Exception("ip or port or env cannot be null");
            }
        } else if ("notifyIp".equals(action)) {
            if (ips != null) {
                m_logService.logNotify(ips);
            }
        } else if ("initDs".equals(action)) {

            if (name != null) {
                return m_dalConfigService.generateConfig(name.toLowerCase());
            }
        } else {
            throw new Exception("unkown operation");
        }

        if (result != null) {
            result.setUser(user);
            result.setTime(new Date());
            m_logService.log(result);
        }
        return result;
    }
}
