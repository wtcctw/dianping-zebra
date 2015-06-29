package com.dianping.zebra.admin.controller;

import com.dianping.zebra.biz.entity.ShardDumpDbEntity;
import com.dianping.zebra.biz.service.ShardDumpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Dozer @ 6/2/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/migratedb")
public class MigrateDbController extends BasicController {

    @Autowired
    private ShardDumpService shardDumpService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<ShardDumpDbEntity> index() {
        return shardDumpService.getAllDb();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public List<ShardDumpDbEntity> create(@RequestBody ShardDumpDbEntity entity) {
        shardDumpService.createDb(entity);
        return shardDumpService.getAllDb();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public List<ShardDumpDbEntity> delete(@PathVariable int id) {
        shardDumpService.deleteDb(id);
        return shardDumpService.getAllDb();
    }
}
