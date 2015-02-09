package com.dianping.zebra.admin.mapper;

import com.dianping.zebra.admin.dto.HeartbeatDto;
import com.dianping.zebra.admin.entity.HeartbeatEntity;
import com.google.common.base.Strings;
import org.modelmapper.PropertyMap;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class HeartbeatDto2HeartbeatEntityMap extends
        PropertyMap<HeartbeatDto, HeartbeatEntity> {

    @Override
    protected void configure() {
        map().setAppName(Strings.isNullOrEmpty(source.getApp()) ? null : source.getApp().toLowerCase());
        map().setJdbcUrl(source.getUrl());
    }
}
