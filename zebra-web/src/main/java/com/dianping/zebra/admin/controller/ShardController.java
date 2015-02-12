package com.dianping.zebra.admin.controller;

import com.dianping.zebra.Constants;
import com.dianping.zebra.admin.service.LionHttpService;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/shard")
public class ShardController {

    @Autowired
    private LionHttpService lionHttpService;

    private final Gson gson = new Gson();

    @RequestMapping(value = "/{env}", method = RequestMethod.GET)
    @ResponseBody
    public Object index(@PathVariable String env) throws IOException {
        Map<String, String> configStr = lionHttpService.getConfigByProject(env, Constants.DEFAULT_SHARDING_PRFIX);
        Map<String, RouterRuleConfig> configs = new HashMap<String, RouterRuleConfig>();
        for (Map.Entry<String, String> entry : configStr.entrySet()) {
            if (!entry.getKey().endsWith(".shard")) {
                continue;
            }

            RouterRuleConfig config = gson.fromJson(entry.getValue(), RouterRuleConfig.class);
            configs.put(entry.getKey(), config);
        }
        return configs;
    }

    @RequestMapping(value = "/{env}/test", method = RequestMethod.POST)
    @ResponseBody
    public Object test(@PathVariable String env, @RequestBody RouterRuleConfig config) {
        return null;
    }

    @RequestMapping(value = "/{env}/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable String env, @RequestBody RouterRuleConfig config) {
        return null;
    }
}
