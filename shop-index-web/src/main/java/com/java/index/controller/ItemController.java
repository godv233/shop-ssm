package com.java.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.java.index.pojo.IndexItem;
import com.java.pojo.TbItem;
import com.java.pojo.TbItemDesc;
import com.java.service.ItemService;

/**
 * 首页的商品服务
 * 
 * @author GODV
 * @date 2019年6月1日上午10:54:34
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	public String getItemById(@PathVariable long itemId, Model model) {
		// 调用服务取得商品的基本信息个描述信息.
		TbItem tbItem = itemService.getItem(itemId);
		IndexItem indexItem = new IndexItem(tbItem);
		TbItemDesc itemDesc = itemService.getItemDesc(itemId);
		// 传递页面
		model.addAttribute("item", indexItem);
		model.addAttribute("itemDesc", itemDesc);
		return "item";

	}
}
