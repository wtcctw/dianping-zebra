package com.dianping.zebra.admin.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.biz.dto.LoginDto;
import com.dianping.zebra.biz.service.LionService;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BasicController {
	
	@Autowired
	private LionService lionService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDto loginDto)
	      throws Exception {
		String user = lionService.getConfigFromZk("zebra.web.admin.user");
		String passwd = lionService.getConfigFromZk("zebra.web.admin.password");
		
		if (user.equals(loginDto.getUsername()) && passwd.equals(loginDto.getPassword())) {
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

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object isLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("AuthorizationFilter")) {
					return cookie.getValue().equals("AuthorizationFilter");
				}
			}
		}
		
		return false;
	}
}
