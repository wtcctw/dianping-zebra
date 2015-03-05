package com.dianping.zebra.admin.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ZebraAdminMapper extends ModelMapper {
    public <S, D> void setMappings(List<PropertyMap<S, D>> maps) {
        for (PropertyMap<S, D> map : maps) {
            this.addMappings(map);
        }
    }
}
