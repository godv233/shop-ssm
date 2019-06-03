package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.java.common.util.E3Result;
import com.java.mapper.TbUserMapper;
import com.java.pojo.TbUser;
import com.java.pojo.TbUserExample;
import com.java.pojo.TbUserExample.Criteria;
import com.java.service.LoginService;

/**
 * 登陆服务
 * 
 * @author GODV
 * @date 2019年6月1日下午8:29:23
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private TbUserMapper tbUserMapper;
	@Override
	public E3Result login(String username, String password) {
		// 查询用户名是否存在
		TbUserExample example = new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUsernameEqualTo(username);
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return E3Result.build(400, "用户名不存在");
		} else {
			if (!list.get(0).getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {// 判断password的MD5和数据库中的是否一样
				return E3Result.build(400, "密码不正确");
			}
		}
		return E3Result.ok(list.get(0));
	}

}
