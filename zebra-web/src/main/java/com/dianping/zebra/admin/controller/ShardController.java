package com.dianping.zebra.admin.controller;

import com.dianping.zebra.Constants;
import com.dianping.zebra.admin.dto.ShardDumpTaskDto;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
import com.dianping.zebra.admin.entity.ShardMigrateProcessEntity;
import com.dianping.zebra.admin.service.LionService;
import com.dianping.zebra.admin.service.ShardDumpService;
import com.dianping.zebra.admin.service.ShardMigrateProcessService;
import com.dianping.zebra.admin.service.SyncServerMonitorService;
import com.dianping.zebra.config.LionKey;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/shard")
public class ShardController {

    @Autowired
    private LionService lionHttpService;

    @Autowired
    private ShardMigrateProcessService shardMigrateProcessService;

    @Autowired
    private ShardDumpService shardDumpService;

    @Autowired
    private SyncServerMonitorService syncServerMonitorService;

    private final Gson gson = new Gson();

    @RequestMapping(value = "/{env}/config", method = RequestMethod.GET)
    @ResponseBody
    public Object getConfigList(@PathVariable String env) throws IOException {
        Map<String, String> configStr = lionHttpService.getConfigByProject(env, Constants.DEFAULT_SHARDING_PRFIX);
        Set<String> shardKeys = new HashSet<String>();
        for (Map.Entry<String, String> entry : configStr.entrySet()) {
            String key = entry.getKey();

            if (!LionKey.isShardConfigKey(entry.getKey())) {
                continue;
            }

            int begin = key.indexOf('.');

            if (begin > 0) {
                int end = key.indexOf('.', begin + 1);

                shardKeys.add(key.substring(begin + 1, end));
            }
        }

        return shardKeys;
    }

    @RequestMapping(value = "/{env}/config/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Object config(@PathVariable String env, @PathVariable String name) throws IOException {
        String key = String.format("shardds.%s.shard", name);
        String config = lionHttpService.getConfigByHttp(env, key);
        return gson.fromJson(config, RouterRuleConfig.class);
    }

    @RequestMapping(value = "/{env}/update/{name}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable String env, @PathVariable String name, @RequestBody RouterRuleConfig config) {
        String key = String.format("shardds.%s.shard", name);

        lionHttpService.setConfig(env, key, gson.toJson(config));
        return null;
    }

    @RequestMapping(value = "/migrate/process/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ShardMigrateProcessEntity migrateProcess(@PathVariable String name) {
        return shardMigrateProcessService.getProcessByName(name);
    }

    @RequestMapping(value = "/migrate/dump/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<ShardDumpTaskEntity> migrateDumpStatus(@PathVariable String name) {
        return shardDumpService.getTaskByName(name);
    }

    @RequestMapping(value = "/migrate/dump/{name}", method = RequestMethod.POST)
    @ResponseBody
    public List<ShardDumpTaskEntity> migrateDumpCommit(@RequestBody ShardDumpTaskDto dto, @PathVariable String name) {
        String keyName = shardDumpService.getPrimaryKey(dto.getSrcDbName(), dto.getDataBase(), dto.getTableName());
        long maxId = shardDumpService.getMaxIndex(dto.getSrcDbName(), dto.getDataBase(), dto.getTableName(), keyName);

        for (ShardDumpTaskEntity task : dto.getTargets()) {
            task.setName(name);
            task.setDataBase(dto.getDataBase());
            task.setSrcDbName(dto.getSrcDbName());
            task.setTableName(dto.getTableName());
            task.setExecutor(syncServerMonitorService.chooseOne().getName());
            task.setIndexColumnName(keyName);
            task.setIndexKey(1);
            task.setMaxKey(maxId);
            shardDumpService.createTask(task);
        }
        return shardDumpService.getTaskByName(name);
    }
}
