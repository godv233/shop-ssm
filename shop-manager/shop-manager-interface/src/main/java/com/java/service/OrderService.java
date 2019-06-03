package com.java.service;
/**
 * 订单模块接口
 * @author GODV
 * @date 2019年6月2日下午6:09:40
 */

import com.java.common.util.E3Result;
import com.java.service.pojo.OrderInfo;

public interface OrderService {
	E3Result createOrder(OrderInfo orderInfo);//提交订单
}
