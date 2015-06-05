package com.dianping.zebra.admin.controller;

import java.util.List;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.dto.SQLValidateDto;

@Controller
@RequestMapping(value = "/catdata")
public class CatDataController {
	
	@RequestMapping(value = "/databases", method = RequestMethod.GET)
	@ResponseBody
	public Object getDatabases(){
		
		return null;
	}
	
	@RequestMapping(value = "/tables", method = RequestMethod.GET)
	@ResponseBody
	public Object getTables(String database){
		
		return null;
	}
	
	public List<SQLValidateDto> getSQLs(String database){
		
		return null;
	}
}
