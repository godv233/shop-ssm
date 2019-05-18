package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.mapper.TbContentMapper;
import com.java.pojo.TbContent;
import com.java.pojo.TbContentExample;
import com.java.pojo.TbItem;
import com.java.pojo.TbContentExample.Criteria;

//测试
@Controller
public class test {
	@Autowired
	private TbContentMapper mapper;

	@RequestMapping("/hello")
	public void hello() {
//		TbContentExample example = new TbContentExample();
//		Criteria createCriteria = example.createCriteria();
//		createCriteria.andCategoryIdEqualTo(8L);
//		List<TbContent> selectByExample = mapper.selectByExample(example);
//		for (TbContent tbContent : selectByExample) {
//			System.out.println(tbContent);
//		}
		System.out.println("hello");
	}
}
