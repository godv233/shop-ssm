package com.java.index.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.common.util.E3Result;
import com.java.pojo.TbUser;
import com.java.service.LoginService;

/**
 * 登录控制器
 * 
 * @author GODV
 * @date 2019年6月1日下午7:59:42
 */
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	// 跳转登录页面
	@RequestMapping("/page/login")
	public String loginIndex() {
		return "login";
	}

	// 用户登录
	@RequestMapping("/user/login")
	@ResponseBody
	public E3Result login(String username, String password) {
		E3Result result = loginService.login(username, password);
		if (result.getStatus() != 200) {
			return result;
		}
		// 获得session
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("USER_IN_SESSION", result.getData());
		return E3Result.ok("登录成功");

	}

	// 根据sessionID得到user信息
	@RequestMapping("/user/session")
	@ResponseBody
	public E3Result getUserBySessionId() {
		// 获得session
		HttpSession httpSession = request.getSession(false);
		if (httpSession == null) {
			return E3Result.build(400, "未检查到登录状态");
		}
		TbUser user = (TbUser) httpSession.getAttribute("USER_IN_SESSION");
		return E3Result.ok(user);
	}

	// 用户退出登录，删除他的session
	@RequestMapping("/user/logout")
	public String userLogout() {
		// 获得session
		HttpSession httpSession = request.getSession(true);
		httpSession.removeAttribute("USER_IN_SESSION");
		return "index";
	}

}
