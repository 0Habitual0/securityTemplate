package com.habitual.demo.order.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.order.entity.RealOrderEntity;
import com.habitual.demo.order.entity.dto.OrderPageDto;
import com.habitual.demo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 订单退单
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

//    /**
//     * 新增
//     */
//    @PostMapping("save")
//    public CommonResponse save(@RequestBody RealOrderEntity input) {
//        return orderService.save(input);
//    }
//
//    /**
//     * 分页查询
//     */
//    @PostMapping("selectByPage")
//    public CommonResponse selectByPage(@RequestBody OrderPageDto input) {
//        return orderService.selectByPage(input);
//    }



}
