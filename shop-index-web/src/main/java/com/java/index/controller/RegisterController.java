package com.java.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.java.common.util.E3Result;
import com.java.common.util.JsonUtils;
import com.java.pojo.TbUser;
import com.java.service.RegisterService;

/**
 * 登录注册
 * 
 * @author GODV
 * @date 2019年6月1日下午1:39:11
 */
@Controller
public class RegisterController {
	@Autowired
	private RegisterService registerService;

	// 返回注册页面
	@RequestMapping("/registerIndex")
	public String resgisterIndex() {
		return "register";
	}

	// 注册信息数据校验
	@RequestMapping(value = "/user/check/{param}/{type}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String cheakData(@PathVariable String param, @PathVariable Integer type) {
		E3Result checkData = registerService.checkData(param, type);
		return JsonUtils.objectToJson(checkData);
	}

	// 用户注册
	@RequestMapping("/user/register")
	@ResponseBody
	public E3Result register(TbUser user) {
		E3Result result = registerService.register(user);
		return result;

	}

}
