package com.itender.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itender.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author : itender
 * @date : 2023/08/20/ 14:20
 * @desc :
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
