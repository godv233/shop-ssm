package com.java.service;
/**
 * 登录接口
 * @author GODV
 * @date 2019年6月1日下午8:27:53
 */

import com.java.common.util.E3Result;

public interface LoginService {
	E3Result login(String username, String password);//登录
}
