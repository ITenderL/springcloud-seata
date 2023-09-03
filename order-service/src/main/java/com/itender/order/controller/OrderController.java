package com.itender.order.controller;

import com.itender.order.entity.Order;
import com.itender.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:19
 * @desc :
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Integer createOrder(@RequestBody Order order) {
        return orderService.create(order);
    }
}
