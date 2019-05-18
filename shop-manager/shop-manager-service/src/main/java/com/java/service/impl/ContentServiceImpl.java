package com.java.service.impl;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.common.pojo.UIDataResult;
import com.java.common.util.E3Result;
import com.java.mapper.TbContentMapper;
import com.java.pojo.TbContent;
import com.java.pojo.TbContentExample;
import com.java.pojo.TbContentExample.Criteria;
import com.java.service.ContentService;

/**
 * 内容管理服务实现
 * 
 * @author GODV
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	/**
	 * 内容分页查询
	 */
	@Override
	public UIDataResult getContentList(long categoryId, int page, int rows) {

		// 1.执行sql得到list
		TbContentExample example = new TbContentExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> lists = contentMapper.selectByExample(example);
		// 2.设置分页信息
		PageHelper.startPage(page, rows);
		// 3.处理结果
		PageInfo<TbContent> pageInfo = new PageInfo<>(lists);
		long total = pageInfo.getTotal();// 得到总数
		// 设置最终返回的结果
		UIDataResult result = new UIDataResult();
		result.setTotaLong(total);
		result.setRows(lists);
		return result;
	}
	
	@Override
	public E3Result addContent(TbContent content) {
		//将内容数据插入到内容表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入到数据库
		contentMapper.insert(content);
		return E3Result.ok();
	}
	
	
}
