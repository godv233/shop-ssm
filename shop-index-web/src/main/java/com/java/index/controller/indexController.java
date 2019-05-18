package com.java.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示首页
 * @author GODV
 *
 */
@Controller
public class indexController {
	@RequestMapping("index")
	public String getIndexPage() {
		return "index";
		
	}
}
