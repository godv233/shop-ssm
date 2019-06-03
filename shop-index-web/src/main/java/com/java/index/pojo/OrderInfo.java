package com.java.index.pojo;
/**
 * 订单提交类
 * @author GODV
 * @date 2019年6月2日下午6:06:14
 */

import java.util.List;

import com.java.pojo.TbOrder;
import com.java.pojo.TbOrderItem;
import com.java.pojo.TbOrderShipping;

public class OrderInfo extends TbOrder{
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
