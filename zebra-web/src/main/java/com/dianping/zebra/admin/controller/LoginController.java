package com.dianping.zebra.admin.controller;

import com.dianping.zebra.admin.dto.LoginDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDto loginDto)
	      throws Exception {
		if ("zebra".equals(loginDto.getUsername()) && "zebra".equals(loginDto.getPassword())) {
			Cookie cookie = new Cookie("AuthorizationFilter", "");
			cookie.setMaxAge(60 * 60 * 12); // 12 hours
			cookie.setPath("/");
			cookie.setValue("AuthorizationFilter");
			response.addCookie(cookie);
			return null;
		} else {
			throw new Exception("password error!");
		}
	}
}
