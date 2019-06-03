package com.java.service;

import java.util.List;

import com.java.common.pojo.UIDataResult;
import com.java.common.util.E3Result;
import com.java.pojo.TbContent;
import com.java.pojo.TbItem;
import com.java.pojo.TbItemDesc;

public interface ItemService {
	TbItem getItem(long id);// 根据ID查单个商品

	TbItemDesc getItemDesc(long id);// 根据ID查单个商品描述

	UIDataResult getItemList(int page, int rows);// 分页查询

	E3Result addItem(TbItem item, String descption);// 添加商品

	Boolean deleteItemById(Long id);// 删除商品

	Boolean instockItem(Long id);// 下架商品

	Boolean reshelfItem(Long id);// 上架商品

	E3Result updateItemDecs(Long id);// 回显商品详情

	Boolean updateItem(TbItem item, String descption);// 编辑更新商品

	List<TbItem> getIndexItem1List();// 得到部分商品，渲染主页

}
