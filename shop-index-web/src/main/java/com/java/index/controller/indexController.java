package com.java.index.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.TbContent;
import com.java.pojo.TbItem;
import com.java.service.ContentService;
import com.java.service.ItemService;

/**
 * 展示首页.展示之前，查询内容列表
 * @author GODV
 *
 */
@Controller
public class indexController {
	@Value("${BAN_ID}")
	private long BAN_ID;//首页轮播图id
	@Value("${BAN_RIGHT_ID}")
	private long BAN_RIGHT_ID;//轮播图右边广告id
	
	@Autowired
	private ContentService contentService;
	@Autowired
	private ItemService itemService;
	@RequestMapping("/index")
	public String getIndexPage(Model model) {
		List<TbContent> ad1List = contentService.getContentListByCid(BAN_ID);
		List<TbContent> ad2List = contentService.getContentListByCid(BAN_RIGHT_ID);
		List<TbItem> item1List = itemService.getIndexItem1List();
		//使用model将数据传递给页面
		model.addAttribute("ad1List", ad1List);
		model.addAttribute("ad2List", ad2List);
		model.addAttribute("item1List", item1List);
		return "index";
		
	}
}
