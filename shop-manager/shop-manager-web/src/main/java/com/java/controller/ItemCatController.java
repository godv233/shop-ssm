package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.common.pojo.UITreeNode;
import com.java.service.ItemCatService;

/**
 * 商品类别控制器
 * 
 * @author GODV
 *
 */
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService catService;

	@RequestMapping("/item/cat/list")
	@ResponseBody
	private List<UITreeNode> getItemCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
		return catService.getItemCatList(parentId);

	}
}
