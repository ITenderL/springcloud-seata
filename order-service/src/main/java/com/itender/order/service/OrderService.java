package com.itender.order.service;

import com.itender.order.entity.Order;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:19
 * @desc :
 */
public interface OrderService {

    /**
     * 创建订单
     */
    Integer create(Order order);
}
