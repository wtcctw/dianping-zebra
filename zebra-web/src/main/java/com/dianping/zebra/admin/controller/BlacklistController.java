package com.dianping.zebra.admin.controller;

import com.dianping.zebra.admin.dto.BlackListDto;
import com.dianping.zebra.admin.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/blacklist")
public class BlacklistController {
    @Autowired
    private BlackListService blackListService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Object index(String env) throws IOException {
        return blackListService.getAllBlackList(env);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(String env, @RequestBody BlackListDto dto) throws IOException {
        blackListService.addItem(env, dto.getIp(), dto.getId(), dto.getComment());
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(String env, String id, String key) throws IOException {
        blackListService.deleteItem(env, key, id);
        return null;
    }
}
