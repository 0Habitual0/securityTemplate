package com.yt.YcTravelManageSystem.order.service;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.order.entity.RealOrderEntity;
import com.yt.YcTravelManageSystem.order.entity.dto.OrderPageDto;

/**
 * 业务层 订单退单
 */
public interface OrderService {

    CommonResponse saveReal(RealOrderEntity input);

    CommonResponse selectRealByPage(OrderPageDto input);

    CommonResponse changeStatusPaid(Long id, String payType);

    CommonResponse changeStatusComplete(Long id);

    CommonResponse changeStatusBackProcess(Long id);

    CommonResponse changeStatusBack(Long id, Boolean status);

    CommonResponse selectBackByPage(OrderPageDto input);

    CommonResponse mainPage();

    CommonResponse detail(Long id, Boolean status);

}
