package com.dianping.zebra.util;

import com.dianping.zebra.config.ConfigService;

import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ConfigServiceMock implements ConfigService {
    private final Map<String, String> configs = new ConcurrentHashMap<String, String>();

    @Override
    public void init() {

    }

    @Override
    public String getProperty(String key) {
        return configs.get(key);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public ConfigServiceMock addProperty(String key, String value) {
        configs.put(key, value);
        return this;
    }
}
