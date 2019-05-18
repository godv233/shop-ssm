package com.java.service;
/**
 * 商品分类服务接口
 * @author GODV
 *
 */

import java.util.List;

import com.java.common.pojo.UITreeNode;

public interface ItemCatService {
	List<UITreeNode> getItemCatList(Long ParentId);
}
