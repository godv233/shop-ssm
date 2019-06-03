package com.java.index.controller;
/**
 * 首页底部的额外服务
 * @author GODV
 * @date 2019年5月31日下午3:12:57
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class FooterConteoller {
	@RequestMapping("/index/footerService.html")
	public String getFootService() {
		return "footService";
	}
}
