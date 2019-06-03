package com.java.index.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.common.util.CookieUtils;
import com.java.common.util.E3Result;
import com.java.common.util.JsonUtils;
import com.java.pojo.TbItem;
import com.java.pojo.TbUser;
import com.java.service.CartService;
import com.java.service.OrderService;
import com.java.service.pojo.OrderInfo;

/**
 * 订单模块控制器
 * 
 * @author GODV
 * @date 2019年6月2日下午4:34:22
 */
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	// 订单确认页面
	@RequestMapping("/order/order-cart")
	public String showOrderCart(HttpServletRequest request) {
		// 首先判断用户有没有登录，没有登录跳转登录页面
		HttpSession session = request.getSession();
		Object user = session.getAttribute("USER_IN_SESSION");
		if (user == null) {
			return "redirect:/page/login.action";
		}
		// 已经登录，可以从cookie中得到购物车信息(后期可以改写成从数据库中得到他的购物车信息)
		List<TbItem> items = new ArrayList<>();
		String CartJosn = CookieUtils.getCookieValue(request, "cart", true);
		if (!"".equals(CartJosn) && CartJosn != null) {
			items = JsonUtils.jsonToList(CartJosn, TbItem.class);// 将得到的json转成一个list列表
		}
		request.setAttribute("cartList", items);// 写回数据
		return "order-cart";
	}

	// 提交订单页面
	@RequestMapping(value = "/order/create", method = RequestMethod.POST)
	public String createOrder(OrderInfo orderInfo, HttpServletRequest request) {
		// session取用户信息
		HttpSession session = request.getSession();
		TbUser user = (TbUser) session.getAttribute("USER_IN_SESSION");
		if (user == null) {
			return "redirect:/page/login.action";
		}
		// 添加OrderInfo
		orderInfo.setUserId(user.getId());
		orderInfo.setBuyerNick(user.getUsername());
		// 调用服务返回订单
		E3Result e3Result = orderService.createOrder(orderInfo); 
		// 删除购物车
		if (e3Result.getStatus() == 200) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if ("cart".equals(cookie.getName())) {
					cookie.setValue("");// 清空cookie
				}
			}
		}
		// 订单号返回逻辑视图
		request.setAttribute("orderId", e3Result.getData());
		request.setAttribute("payment", orderInfo.getPayment());
		return "orderSuccess";

	}

}
