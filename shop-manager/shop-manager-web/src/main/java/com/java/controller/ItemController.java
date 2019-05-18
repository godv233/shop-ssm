package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.common.pojo.UIDataResult;
import com.java.common.util.E3Result;
import com.java.pojo.TbItem;
import com.java.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	@ResponseBody
	private TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItem(itemId);
		return tbItem;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public UIDataResult getItemList(int page, int rows) {
		// 调用服务
		return itemService.getItemList(page, rows);

	}

	@RequestMapping("/item/save")
	@ResponseBody
	public E3Result addItemcontro(TbItem item, String desc) {

		E3Result result = itemService.addItem(item, desc);
		return result;
	}

	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public E3Result deleteItem(String[] ids) {
		for (String string : ids) {
			long id = Long.parseLong(string);// 转化为long
			Boolean resBoolean = itemService.deleteItemById(id);
			if (resBoolean == false) {
				return E3Result.build(500, "删除商品失败");
			}
		}
		return E3Result.ok();
	}

	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public E3Result instockItem(String[] ids) {
		for (String string : ids) {
			long id = Long.parseLong(string);// 转化为long
			Boolean instocRes = itemService.instockItem(id);
			if (instocRes == false) {
			}
		}
		return E3Result.build(200, "已将正常物品下架");
	}

	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public E3Result reshelfItem(String[] ids) {
		for (String string : ids) {
			long id = Long.parseLong(string);// 转化为long
			Boolean isReshelf = itemService.reshelfItem(id);
			if (isReshelf == false) {
			}
		}
		return E3Result.build(200, "已将下架物品上架");
	}

	@RequestMapping("/rest/page/item-edit")
	public String editItem() {
		return "item-edit";

	}

	/*
	 * 更新时回显数据
	 */
	@RequestMapping("/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public E3Result updateItemDesc(@PathVariable Long itemId) {

		return itemService.updateItemDecs(itemId);

	}

	@RequestMapping("/rest/item/param/item/query/{itemId}")
	@ResponseBody
	public E3Result updateItemQuery(@PathVariable Long itemId) {
		return itemService.updateItemDecs(1068358L);

	}

	/**
	 * 前端提交更新到数据库
	 */
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public E3Result updateItem(TbItem itemeEditForm, String desc) {
		if (itemService.updateItem(itemeEditForm, desc)) {
			return E3Result.build(200, "更新成功");
		} else {
			return E3Result.build(500, "更新失败");
		}
	}
}
