package com.java.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.StringUtil;
import com.java.common.util.E3Result;
import com.java.mapper.TbUserMapper;
import com.java.pojo.TbUser;
import com.java.pojo.TbUserExample;
import com.java.pojo.TbUserExample.Criteria;
import com.java.service.RegisterService;

/**
 * 注册功能的实现类
 * 
 * @author GODV
 * @date 2019年6月1日下午2:00:18
 */
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private TbUserMapper userMapper;

	@Override
	public E3Result checkData(String param, int type) {
		// 更具不同的type，设置不同的查询条件 1.用户名 2.手机号3.邮箱
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		} else if (type == 2) {
			criteria.andPhoneEqualTo(param);
		} else if (type == 1) {
			criteria.andEmailEqualTo(param);
		} else {
			return E3Result.build(400, "数据类型错误");
		}
		// 执行
		List<TbUser> list = userMapper.selectByExample(example);
		// 处理结果
		if (list != null && list.size() > 0) {// 有数据，证明重复，返回错误
			return E3Result.ok(false);
		}
		return E3Result.ok(true);
	}

	// 注册。insert数据库
	@Override
	public E3Result register(TbUser user) {
		// 数据有效校验
		if (user.getPassword() == null || user.getPhone() == null || user.getUsername() == null
				|| "".equals(user.getUsername()) || "".equals(user.getPassword()) || "".equals(user.getPhone())) {
			return E3Result.build(400, "用户数据不完整,注册失败");
		}
		E3Result res = checkData(user.getUsername(), 1);// 再次校验用户
		if (!(boolean) res.getData()) {
			return E3Result.build(400, "该用户名已被注册");
		}
		E3Result res1 = checkData(user.getPhone(), 2);// 再次校验手机号
		if (!(boolean) res1.getData()) {
			return E3Result.build(400, "该手机号已被注册");
		}
		// 属性赋值
		user.setCreated(new Date());
		user.setUpdated(new Date());
		// 密码md5加密
		String md5String = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5String);
		// 执行sql
		userMapper.insert(user);
		return E3Result.ok();
	}

}
