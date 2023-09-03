package com.itender.order.service.impl;

import com.itender.order.client.AccountClient;
import com.itender.order.client.StorageClient;
import com.itender.order.entity.Order;
import com.itender.order.mapper.OrderMapper;
import com.itender.order.service.OrderService;
import feign.FeignException;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:50
 * @desc :
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final AccountClient accountClient;
    private final StorageClient storageClient;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, AccountClient accountClient, StorageClient storageClient) {
        this.orderMapper = orderMapper;
        this.accountClient = accountClient;
        this.storageClient = storageClient;
    }


    @Override
    @GlobalTransactional
    public Integer create(Order order) {
        orderMapper.insert(order);
        try {
            accountClient.deduct(order.getUserId(), order.getMoney());
            storageClient.deduct(order.getCommodityCode(), order.getCount());
        } catch (FeignException e) {
            log.error("下单失败，原因：{}", e.contentUTF8(), e);
            throw new RuntimeException(e.contentUTF8(), e);
        }
        return order.getId();
    }

}
