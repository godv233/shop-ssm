package com.java.service.impl;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.common.pojo.UIDataResult;
import com.java.common.pojo.UITreeNode;
import com.java.common.util.E3Result;
import com.java.mapper.TbContentCategoryMapper;
import com.java.mapper.TbContentMapper;
import com.java.pojo.TbContent;
import com.java.pojo.TbContentCategory;
import com.java.pojo.TbContentCategoryExample;
import com.java.pojo.TbContentCategoryExample.Criteria;
import com.java.pojo.TbContentExample;
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
	private TbContentCategoryMapper contentCategoryMapper;
	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public List<UITreeNode> getContentCatList(long patenrId) {
		// 根据id查询子节点列表
		TbContentCategoryExample contentCategoryExample = new TbContentCategoryExample();
		Criteria createCriteria = contentCategoryExample.createCriteria();// 设置查询条件
//		createCriteria.andParentIdEqualTo(patenrId);// 执行查询
		List<TbContentCategory> catList = contentCategoryMapper.selectByExample(contentCategoryExample);
		// 转化列表
		List<UITreeNode> nodeList = new ArrayList<UITreeNode>();
		for (TbContentCategory tbContentCategory : catList) {
			UITreeNode node = new UITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			// 添加到列表
			nodeList.add(node);
		}
		// 返回
		return nodeList;
	}

	@Override
	public E3Result addContentCategroy(Long parentId, String name) {
		// 创建一个pojo对象
		TbContentCategory category = new TbContentCategory();
		// 设置pojo属性
		category.setParentId(parentId);
		category.setName(name);
		category.setStatus(1);// 1正常 2删除
		category.setSortOrder(1);
		category.setIsParent(false);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		// 插入
		contentCategoryMapper.insert(category);
		// 判断节点的isParent属性
		TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentCategory.getIsParent()) {
			parentCategory.setIsParent(true);
			// 更新
			contentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
		// 返回结果
		return E3Result.ok();
	}

	/**
	 * 内容分页查询
	 */
	@Override
	public UIDataResult getContentList(long categoryId, int page, int rows) {

		// 1.执行sql得到list
		TbContentExample example = new TbContentExample();
		com.java.pojo.TbContentExample.Criteria createCriteria = example.createCriteria();
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
