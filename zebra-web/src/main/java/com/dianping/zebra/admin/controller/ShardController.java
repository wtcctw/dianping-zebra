package com.dianping.zebra.admin.controller;

import com.dianping.zebra.Constants;
import com.dianping.zebra.admin.service.LionService;
import com.dianping.zebra.config.ConfigService;
import com.dianping.zebra.config.LionKey;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.jdbc.ShardDataSource;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyChangeListener;
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
    private LionService lionHttpService;

    private final Gson gson = new Gson();

    @RequestMapping(value = "/{env}/config", method = RequestMethod.GET)
    @ResponseBody
    public Object allConfig(@PathVariable String env) throws IOException {
        Map<String, String> configStr = lionHttpService.getConfigByProject(env, Constants.DEFAULT_SHARDING_PRFIX);
        Map<String, RouterRuleConfig> configs = new HashMap<String, RouterRuleConfig>();
        for (Map.Entry<String, String> entry : configStr.entrySet()) {
            if (!LionKey.isShardConfigKey(entry.getKey())) {
                continue;
            }

            RouterRuleConfig config = gson.fromJson(entry.getValue(), RouterRuleConfig.class);
            configs.put(entry.getKey(), config);
        }
        return configs;
    }

    @RequestMapping(value = "/{env}/config/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Object config(@PathVariable String env, @PathVariable String name) throws IOException {
        String config = lionHttpService.getConfigByHttp(env, name);
        return gson.fromJson(config, RouterRuleConfig.class);
    }


    @RequestMapping(value = "/{env}/test", method = RequestMethod.GET)
    @ResponseBody
    public Object test(@PathVariable String env, String key) {
        key = LionKey.getRuleNameFromShardKey(key);

        ShardDataSource ds = new ShardDataSource();
        ds.setRuleName(key);

        return new testResult(testShardDataSource(ds));
    }

    @RequestMapping(value = "/{env}/test", method = RequestMethod.POST)
    @ResponseBody
    public Object testWithConfig(@PathVariable String env, @RequestBody final RouterRuleConfig config) {
        ConfigService configService = new ConfigService() {
            @Override
            public void init() {

            }

            @Override
            public String getProperty(String key) {
                if (LionKey.isShardConfigKey(key)) {
                    return new Gson().toJson(config);
                }
                return null;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            public void destroy() {
            }
        };

        ShardDataSource ds = new ShardDataSource();
        ds.setRuleName("test");
        ds.setConfigService(configService);

        return new testResult(testShardDataSource(ds));
    }

    private String testShardDataSource(ShardDataSource ds) {
        try {
            ds.init();
            return "成功！";
        } catch (Exception exp) {
            return exp.getMessage();
        } finally {
            ds.close();
        }
    }

    class testResult {

        public testResult(String message) {
            this.message = message;
        }

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @RequestMapping(value = "/{env}/update/{name}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable String env, @PathVariable String name, @RequestBody RouterRuleConfig config) {
        lionHttpService.setConfig(env, name, gson.toJson(config));
        return null;
    }
}
