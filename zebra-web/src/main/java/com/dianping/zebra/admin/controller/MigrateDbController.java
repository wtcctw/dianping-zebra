package com.dianping.zebra.admin.controller;

import com.dianping.zebra.admin.entity.ShardDumpDbEntity;
import com.dianping.zebra.admin.service.ShardDumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Dozer @ 6/2/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/migratedb")
public class MigrateDbController {

    @Autowired
    private ShardDumpService shardDumpService;

    @RequestMapping(value = "")
    @ResponseBody
    public List<ShardDumpDbEntity> index() {
        return shardDumpService.getAllDb();
    }
}
