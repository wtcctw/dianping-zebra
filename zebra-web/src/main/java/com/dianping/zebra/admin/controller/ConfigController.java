package com.dianping.zebra.admin.controller;

import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.zebra.admin.dto.ConnectionStatusDto;
import com.dianping.zebra.admin.service.ConnectionService;
import com.dianping.zebra.admin.service.DalConfigService;
import com.dianping.zebra.admin.service.LionHttpService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/config")
public class ConfigController {
    private final static String currentEnv = EnvZooKeeperConfig.getEnv();

    @Autowired
    private LionHttpService lionHttpService;

    @Autowired
    private DalConfigService dalConfigService;

    @Autowired
    private ConnectionService connectionService;

    @RequestMapping(value = "/env", method = RequestMethod.GET)
    @ResponseBody
    public Object env() throws Exception {
        Object responseObject;

        if ("qa".equals(currentEnv)) {
            responseObject = lionHttpService.getDevEnv();
        } else if ("prelease".equals(currentEnv)) {
            responseObject = new String[]{"prelease"};
        } else if (lionHttpService.isDev()) {
            responseObject = new String[]{"dev"};
        } else {
            responseObject = lionHttpService.getAllEnv();
        }

        return responseObject;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Object index(String env) throws Exception {
        return lionHttpService.getConfigByProject(env, "groupds");
    }

    @RequestMapping(value = "/updateds", method = RequestMethod.POST)
    @ResponseBody
    public Object updateds(boolean force, @RequestBody DalConfigService.GroupConfigModel dsConfig) throws Exception {
        dalConfigService.updateDsConfig(dsConfig, force);
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(String project, String key) throws Exception {
        if (project.equals("groupds")) {
            if (Strings.isNullOrEmpty(key)) {
                throw new NullPointerException("key");
            }
            String dskey = String.format("groupds.%s.mapping", key.toLowerCase());

            lionHttpService.createKey("groupds", dskey);

            lionHttpService.removeUnset(dskey);

        } else if (project.equals("ds")) {
        }
        return null;
    }


    @RequestMapping(value = "/ds", method = RequestMethod.GET)
    @ResponseBody
    public Object ds(String env, String key, String otherkey) throws Exception {
        return dalConfigService.getDsConfig(env, key, otherkey);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ConnectionStatusDto test(String env, String key) throws Exception {
        env = convertEnv(env);
        key = convertKey(key);

        if (env.equalsIgnoreCase(currentEnv) || "dev".equals(env)) {
            return connectionService.getConnectionResult(key, null);
        } else {
            String host = getHost(env);
            if (Strings.isNullOrEmpty(host)) {
                throw new NullPointerException("host");
            }
            String url = getUrl(env, key, host);

            RestTemplate client = new RestTemplate();
            return client.exchange(url, HttpMethod.GET, null, ConnectionStatusDto.class).getBody();
        }
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Object testWithConfig(String env, String key, @RequestBody DalConfigService.GroupConfigModel dsConfig) throws Exception {
        env = convertEnv(env);
        key = convertKey(key);

        if (env.equalsIgnoreCase(currentEnv) || "dev".equals(env)) {
            return connectionService
                    .getConnectionResult(key, dsConfig);
        } else {
            String host = getHost(env);
            if (Strings.isNullOrEmpty(host)) {
                throw new NullPointerException("host");
            }
            String url = getUrl(env, key, host);

            RestTemplate client = new RestTemplate();
            return client.exchange(url, HttpMethod.POST, new HttpEntity<DalConfigService.GroupConfigModel>(dsConfig), ConnectionStatusDto.class).getBody();
        }
    }

    private String getUrl(String env, String key, String host) {
        return host + "/a/config/test&key=" + key + "&env=" + env;
    }

    private String getHost(String env) throws Exception {
        String host = "";
        if ("alpha".equals(env)) {
            host = "http://192.168.214.228:8080";
        } else if ("qa".equals(env)) {
            host = "http://zebra-web01.beta:8080";
        } else if ("prelease".equals(env)) {
            host = "http://10.2.8.65:8080";
        } else if ("product".equals(env)) {
            host = "http://zebra.dp";
        } else {
            throw new Exception("Error: unrecognized lion env!");
        }
        return host;
    }


    private String convertKey(String key) {
        if (!Strings.isNullOrEmpty(key) && key.toLowerCase().equals("dpreview")) {
            key = "DPReview";
        }
        return key;
    }

    private String convertEnv(String env) {
        if (Strings.isNullOrEmpty(env)) {
            env = EnvZooKeeperConfig.getEnv();
        }
        return env;
    }
}
