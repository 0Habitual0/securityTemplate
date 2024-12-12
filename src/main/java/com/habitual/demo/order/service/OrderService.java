package com.habitual.demo.order.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.order.entity.RealOrderEntity;
import com.habitual.demo.order.entity.dto.OrderPageDto;

/**
 * 业务层 订单退单
 */
public interface OrderService {

    CommonResponse saveReal(RealOrderEntity input);

    CommonResponse selectRealByPage(OrderPageDto input);

    CommonResponse changeStatusPaid(Long id);

    CommonResponse changeStatusComplete(Long id);

    CommonResponse changeStatusBackProcess(Long id);

    CommonResponse changeStatusBack(Long id);

    CommonResponse selectBackByPage(OrderPageDto input);

}
