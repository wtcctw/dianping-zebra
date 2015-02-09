package com.dianping.zebra.admin.controller;

import com.dianping.zebra.admin.dto.HeartbeatDto;
import com.dianping.zebra.admin.entity.HeartbeatEntity;
import com.dianping.zebra.admin.service.DalConfigService;
import com.dianping.zebra.admin.service.DalService;
import com.dianping.zebra.admin.service.LogService;
import com.google.common.base.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/notify")
public class NotifyController {
    @Autowired
    private DalConfigService m_dalConfigService;
    @Autowired
    private DalService m_dalService;
    @Autowired
    private LogService m_logService;

    @Autowired
    private ModelMapper mapper;

    private static final String NOT_FOUND = "N/A";

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Object notify(@RequestParam HeartbeatDto dto) {
        if (dto.getApp() != null && dto.getIp() != null && dto.getDataSourceBeanName() != null) {

            HeartbeatEntity entity = mapper.map(dto, HeartbeatEntity.class);
            if (Strings.isNullOrEmpty(entity.getDatabaseName())) {
                entity.setDatabaseName(NOT_FOUND);
            }
            if (Strings.isNullOrEmpty(entity.getDatasourceBeanClass())) {
                entity.setDatasourceBeanClass(NOT_FOUND);
            }
            if (Strings.isNullOrEmpty(entity.getVersion())) {
                entity.setVersion(NOT_FOUND);
            }
            if (Strings.isNullOrEmpty(entity.getUsername())) {
                entity.setUsername(NOT_FOUND);
            }

            if (!Strings.isNullOrEmpty(entity.getJdbcUrl())) {
                String[] parts = entity.getJdbcUrl().split(":");
                if (parts != null && parts.length > 2) {
                    entity.setDatabaseType(parts[1].toLowerCase());
                }
            } else {
                entity.setDatabaseType(NOT_FOUND);
            }

//            m_reportService.createOrUpdate(hb);

        }
        return null;
    }
}
