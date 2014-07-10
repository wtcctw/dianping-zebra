/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-10
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.monitor.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @author danson.liu
 *
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID = 6763345531545815287L;
    
    private static final float 	DEFAULT_LOAD_FACTOR = 0.75f;
    protected int             	maxElements;

    public LRUCache(int maxSize) {
        super(maxSize, DEFAULT_LOAD_FACTOR, true);
        this.maxElements = maxSize;
    }

    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return (size() > this.maxElements);
    }
}
