package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 网站首页的控制器
 * @author GODV
 *
 */
@Controller
public class IndexPageController {
	//访问首页
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	//通过点击的页面跳转相应的视图
	@RequestMapping("/{page}")
	private String showPage(@PathVariable String page) {
		return page;
		
	}
}
