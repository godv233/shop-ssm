package com.java.service;

import com.java.common.util.E3Result;
import com.java.pojo.TbUser;

/**
 * 注册功能接口
 * 
 * @author GODV
 * @date 2019年6月1日下午1:58:26
 */
public interface RegisterService {
	E3Result checkData(String param,int type);
	E3Result register(TbUser user);
}
