package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.common.pojo.UIDataResult;
import com.java.common.util.E3Result;
import com.java.pojo.TbContent;
import com.java.service.ContentService;

//网站内容管理
@Controller
public class ContentController {
	@Autowired
	private ContentService ContentService;

	@RequestMapping("/content/query/list")
	@ResponseBody
	public UIDataResult getContentList(long categoryId,int page,int rows) {
		return ContentService.getContentList(categoryId, page, rows);
	}
	@RequestMapping("/content/save")
	@ResponseBody
	public E3Result addContent(TbContent content) {
		return ContentService.addContent(content);
		
	}
}
