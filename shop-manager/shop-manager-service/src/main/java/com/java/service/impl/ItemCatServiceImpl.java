package com.java.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.common.pojo.UITreeNode;
import com.java.mapper.TbItemCatMapper;
import com.java.pojo.TbItemCat;
import com.java.pojo.TbItemCatExample;
import com.java.pojo.TbItemCatExample.Criteria;
import com.java.service.ItemCatService;

/**
 * 商品类别管理服务
 * 
 * @author GODV
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<UITreeNode> getItemCatList(Long ParentId) {
	//根据parentId查询子节点列表
		//设置查询条件
		TbItemCatExample example=new TbItemCatExample();
		Criteria createCriteria = example.createCriteria();
		//执行sql
		createCriteria.andParentIdEqualTo(ParentId);
		//返回结果
		List<TbItemCat> listItemCats = itemCatMapper.selectByExample(example);
		//把结果列表转化成Node
		List<UITreeNode> treeNodes=new ArrayList<UITreeNode>();
		for (TbItemCat itemCat : listItemCats) {
			//设置属性
			UITreeNode node=new UITreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent()?"closed":"open");
			//添加到结果列表
			treeNodes.add(node);
		}
		return treeNodes;//返回列表
	}
}
