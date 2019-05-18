package com.java.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.common.pojo.UIDataResult;
import com.java.common.util.E3Result;
import com.java.common.util.IDUtils;
import com.java.mapper.TbItemDescMapper;
import com.java.mapper.TbItemMapper;
import com.java.pojo.TbItem;
import com.java.pojo.TbItemDesc;
import com.java.pojo.TbItemExample;
import com.java.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Override
	public TbItem getItem(long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		return item;

	}

	@Override
	public UIDataResult getItemList(int page, int rows) {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 执行sql得到list
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		// 创建一个返回值对象
		UIDataResult result = new UIDataResult();
		result.setRows(list);
		// 处理结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		Long total = pageInfo.getTotal();
		result.setTotaLong(total);
		return result;
	}

	// 添加商品
	@Override
	public E3Result addItem(TbItem item, String descption) {
		// 使用工具类生成商品id
		long itemId = IDUtils.genItemId();
		// 补全商品信息
		item.setId(itemId);
		item.setStatus((byte) 1);// 1正常 2下架 3删除
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 插入商品
		itemMapper.insert(item);
		// 商品描述bean
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(descption);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		// 插入商品描述
		itemDescMapper.insert(itemDesc);
		// 返回成功
		return E3Result.ok();
	}

	// 删除商品根据id
	@Override
	public Boolean deleteItemById(Long id) {
		// 将状态改为删除
		// 先查找出商品
		TbItem item = itemMapper.selectByPrimaryKey(id);
		item.setStatus((byte) 3);// 1正常 2下架 3删除
		// 更新
		int isDeleteItem = itemMapper.updateByPrimaryKey(item);
		if (isDeleteItem != 0) {
			return true;
		} else {
			return false;
		}
	}

	// 下架商品
	@Override
	public Boolean instockItem(Long id) {
		TbItem resTbItem = itemMapper.selectByPrimaryKey(id);
		if (resTbItem.getStatus() == 1) {// 先查询是不是正常的
			resTbItem.setStatus((byte) 2);// 设置成为下架的
			int resBoolean = itemMapper.updateByPrimaryKey(resTbItem);// 更新数据库
			if (resBoolean != 0) {
				return true;
			} else {
				return false;// 下架出错
			}
		} else {
			return false;
		}
	}

	// 上架商品
	@Override
	public Boolean reshelfItem(Long id) {
		TbItem resTbItem = itemMapper.selectByPrimaryKey(id);
		if (resTbItem.getStatus() == 2) {// 下架商品重新上架
			resTbItem.setStatus((byte) 1);
			int resBoolean = itemMapper.updateByPrimaryKey(resTbItem);// 更新数据库
			if (resBoolean != 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// 回显商品信息
	@Override
	public E3Result updateItemDecs(Long id) {
		TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(id);
		if (tbItemDesc != null) {
			return E3Result.ok(tbItemDesc);
		} else {
			return null;
		}
	}

	// 编辑更新商品
	@Override
	public Boolean updateItem(TbItem item, String descption) {
		item.setUpdated(new Date());
		// 将商品提交到数据库
		int updateByPrimaryKeySelective = itemMapper.updateByPrimaryKeySelective(item);
		// 将商品的详细介绍提交到数据库
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
		if (itemDesc==null) {
			itemDesc=new TbItemDesc();
			itemDesc.setItemDesc(descption);
			itemDesc.setCreated(new Date());
			itemDesc.setUpdated(new Date());
			itemDesc.setItemId(item.getId());
			itemDescMapper.insertSelective(itemDesc);
		}
		if (updateByPrimaryKeySelective != 0) {
			return true;
		} else {
			return false;
		}
	}

}
