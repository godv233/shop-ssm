package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.common.pojo.UITreeNode;
import com.java.common.util.E3Result;
import com.java.service.ContentCatService;

/**
 * 内容展示控制器
 * 
 * @author GODV
 *
 */
@Controller
public class ContentCatController {
	@Autowired
	private ContentCatService ContentCatService;

	/*
	 * 得到内容标题列表
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<UITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") long parentId) {
		return ContentCatService.getContentCatList(parentId);
	}

	/**
	 * 添加内容标题节点
	 */
	@RequestMapping("/content/category/create")
	@ResponseBody
	public E3Result addContentCategroy(@RequestParam("parentId") Long parentId, @RequestParam("name") String name) {

		return ContentCatService.addContentCatgory(parentId, name);// 调用服务，直接返回

	}
	/**
	 * 更新内容节点
	 */
	@RequestMapping("/content/category/update")
	@ResponseBody
	public E3Result updateContentCategroy(@RequestParam("id") Long id, @RequestParam("name") String name) {

		return ContentCatService.updateContentCatgory(id, name);// 调用服务，直接返回

	}
	/*
	 * 删除内容节点
	 * 如果是叶子节点直接删除
	 * 如果不是叶子节点 1.递归删除    2. 不允许删除
	 */
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public E3Result deleteContentCatgroy(long id) {
		return ContentCatService.deleteContentCatgroy(id);
	}
}
