package com.habitual.demo.order.service.impl;

import com.habitual.demo.order.mapper.OrderMapper;
import com.habitual.demo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层实现 订单退单
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

}
