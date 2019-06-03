package com.java.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import com.java.common.util.E3Result;
import com.java.common.util.IDUtils;
import com.java.mapper.TbOrderItemMapper;
import com.java.mapper.TbOrderMapper;
import com.java.mapper.TbOrderShippingMapper;
import com.java.pojo.TbOrderItem;
import com.java.pojo.TbOrderShipping;
import com.java.service.OrderService;
import com.java.service.pojo.OrderInfo;

/**
 * 订单服务实现类
 * 
 * @author GODV
 * @date 2019年6月2日下午6:16:19
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;

	// 提交订单
	@Override
	public E3Result createOrder(OrderInfo orderInfo) {
		// 生成订单号 正确的写法应该是数据库中维护一张表，又有一个id字段，一直自增。
		String orderId = String.valueOf(IDUtils.getOrderId());
		// 补全信息
		orderInfo.setOrderId(orderId);
		orderInfo.setStatus(1);// 1.未付款 2.已付款3.已发货4.交易成功5.交易关闭
		orderInfo.setCreateTime(new Date());
		orderInfo.setUpdateTime(new Date());
		// 向三个表插入数据
		orderMapper.insert(orderInfo);
		
		List<TbOrderItem> orderItems = orderInfo.getOrderItems();
		for (TbOrderItem tbOrderItem : orderItems) {
			String orderItemId = String.valueOf(UUID.randomUUID().getMostSignificantBits()).substring(0, 10);// 明细id
			tbOrderItem.setId(orderItemId);
			tbOrderItem.setOrderId(orderId);
			// 向数据库插入
			orderItemMapper.insert(tbOrderItem);
		}

		TbOrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		orderShippingMapper.insert(orderShipping);
		// 返回包含订单号
		
		return E3Result.ok(orderId);
	}

}
