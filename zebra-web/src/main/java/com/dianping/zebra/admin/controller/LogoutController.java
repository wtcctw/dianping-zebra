package com.dianping.zebra.admin.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/logout")
public class LogoutController extends BasicController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("AuthorizationFilter") && cookie.getValue().equals("AuthorizationFilter")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					
					response.addCookie(cookie);
				}
			}
		}
		
		return true;
	}
}
