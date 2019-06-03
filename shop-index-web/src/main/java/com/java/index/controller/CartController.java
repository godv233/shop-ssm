package com.java.index.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.common.util.CookieUtils;
import com.java.common.util.E3Result;
import com.java.common.util.JsonUtils;
import com.java.pojo.TbItem;
import com.java.service.ItemService;

/**
 * 购物车控制器
 * 
 * @author GODV
 * @date 2019年6月2日上午10:55:15
 */
@Controller
public class CartController {
	@Value("${COOKIE_CART_TIME}")
	private int CartMaxTime;
	@Autowired
	private ItemService itemService;

	// 添加购物车
	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable long itemId, @RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 从cookie中取购物车列表
		List<TbItem> cartList = getCartListFromCookie(request);
		// 设置属性
		boolean flag = false;
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId) {// 有商品就+num
				flag = true;
				tbItem.setNum(tbItem.getNum() + num);
				break;
			}
		}
		if (!flag) {
			TbItem item = itemService.getItem(itemId);
			// 设置商品数量
			item.setNum(num);
			// 取一张图片
			String image = item.getImage();
			if (image != null && !"".equals(image)) {
				item.setImage(image.split(",")[0]);
			}
			cartList.add(item);
		}
		// 写入cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), CartMaxTime, true);
		// 返回
		return "cartSuccess";

	}

	// 展示购物车
	@RequestMapping("cart/cart")
	public String showCartList(HttpServletRequest request) {
		// 取列表
		List<TbItem> cartList = getCartListFromCookie(request);
		request.setAttribute("cartList", cartList);
		// 返回
		return "cart";
	}

	// 从cookie取得购物车列表
	public List<TbItem> getCartListFromCookie(HttpServletRequest request) {
		String CartJosn = CookieUtils.getCookieValue(request, "cart", true);
		if ("".equals(CartJosn) || CartJosn == null) {// 返回一个为空的list
			return new ArrayList<>();
		}
		List<TbItem> list = JsonUtils.jsonToList(CartJosn, TbItem.class);// 将得到的json转成一个list列表
		return list;

	}

	// 修改购物车商品的数量
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public E3Result updateCartNum(@PathVariable long itemId, @PathVariable int num, HttpServletRequest request,
			HttpServletResponse response) {
		// 从cookie中得到列表
		List<TbItem> cartList = getCartListFromCookie(request);
		// 更新
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId) {
				tbItem.setNum(num);
			}
		}
		// 写回
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), CartMaxTime, true);
		return E3Result.ok();
	}
	//删除购物车商品
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable long itemId,HttpServletRequest request,HttpServletResponse response) {
		//取购物车列表
		List<TbItem> cartList= getCartListFromCookie(request);
		//删除
		for (TbItem tbItem : cartList) {
			if (tbItem.getId()==itemId) {
				//删除
				cartList.remove(tbItem);
				break;
			}
		}
		//写回cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), CartMaxTime, true);
		//返回逻辑视图
		return "redirect:/cart/cart.action";
	}
	
	
}
