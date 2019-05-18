package com.java.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.common.pojo.UITreeNode;
import com.java.common.util.E3Result;
import com.java.mapper.TbContentCategoryMapper;
import com.java.pojo.TbContentCategory;
import com.java.pojo.TbContentCategoryExample;
import com.java.pojo.TbContentCategoryExample.Criteria;
import com.java.service.ContentCatService;

/**
 * 内容列表的实现
 * 
 * @author GODV
 *
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {
	@Autowired
	private TbContentCategoryMapper contentCatMapper;

	/**
	 * 添加内容标题
	 */
	@Override
	public E3Result addContentCatgory(long id, String name) {
		// 创建一个数据库表对应的pojo对象
		TbContentCategory contentCategory = new TbContentCategory();
		// 设置属性
		contentCategory.setParentId(id);
		contentCategory.setName(name);
		contentCategory.setStatus(1);// 1表示正常， 2表示删除
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategory.setIsParent(false);// 新添加的节点为叶子节点
		// 插入
		contentCatMapper.insertSelective(contentCategory);
		// 判断父节点的isParent属性，改为true
		TbContentCategory parentCategory = contentCatMapper.selectByPrimaryKey(id);
		if (!parentCategory.getIsParent()) {
			parentCategory.setIsParent(true);
			contentCatMapper.updateByPrimaryKeySelective(parentCategory);
		}
		// 返回结果
		return E3Result.ok(contentCategory);
	}

	/**
	 * 内容标题列表
	 */
	@Override
	public List<UITreeNode> getContentCatList(long parentId) {
		// 根据parentid查询子节点列表
				TbContentCategoryExample example = new TbContentCategoryExample();
				Criteria criteria = example.createCriteria();
				//设置查询条件
				criteria.andParentIdEqualTo(parentId);
				//执行查询
				List<TbContentCategory> catList = contentCatMapper.selectByExample(example);
				//转换成EasyUITreeNode的列表
				List<UITreeNode> nodeList = new ArrayList<>();
				for (TbContentCategory tbContentCategory : catList) {
					UITreeNode node = new UITreeNode();
					node.setId(tbContentCategory.getId());
					node.setText(tbContentCategory.getName());
					node.setState(tbContentCategory.getIsParent()?"closed":"open");
					//添加到列表
					nodeList.add(node);
				}
				return nodeList;
	}

	/*
	 * 更新商品标题
	 */
	@Override
	public E3Result updateContentCatgory(long id, String name) {
		TbContentCategory contentCategory = contentCatMapper.selectByPrimaryKey(id);
		if (contentCategory != null) {
			contentCategory.setName(name);
			contentCategory.setUpdated(new Date());
			contentCatMapper.updateByPrimaryKeySelective(contentCategory);// 不为null的字段进行更新
			return E3Result.ok();
		}
		return null;
	}

	/*
	 * 删除标题节点 不是叶子节点 递归删除
	 */
	@Override
	public E3Result deleteContentCatgroy(long id) {
		TbContentCategory contentCategory = contentCatMapper.selectByPrimaryKey(id);// 得到当前节点
		if (!contentCategory.getIsParent()) {// 当前节点为叶子节点时：直接删除
			contentCatMapper.deleteByPrimaryKey(id);
		}
		else if (contentCategory.getIsParent()) {//不是叶子节点就递归删除其父节点为id的所有节点
			deleteParentIdCatgroy(id);
		}
		return E3Result.ok(); 
	}
	//删除父节点为id的所有节点
	public void deleteParentIdCatgroy(long id) {
		TbContentCategory contentCategory = contentCatMapper.selectByPrimaryKey(id);// 得到当前节点
		if (!contentCategory.getIsParent()) {
			contentCatMapper.deleteByPrimaryKey(id);
			return;
		}else  {
			TbContentCategoryExample example=new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(id);
			List<TbContentCategory> lists = contentCatMapper.selectByExample(example);//得到所有parentId为id的节点
			contentCatMapper.deleteByPrimaryKey(id);//删除该节点
			for (TbContentCategory tbContentCategory : lists) {
				deleteParentIdCatgroy(tbContentCategory.getId());
			}
		}
		
	}

}
