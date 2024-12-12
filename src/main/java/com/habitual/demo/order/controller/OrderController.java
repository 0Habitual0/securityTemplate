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

    /**
     * 新增订单
     */
    @PostMapping("saveReal")
    public CommonResponse saveReal(@RequestBody RealOrderEntity input) {
        return orderService.saveReal(input);
    }

    /**
     * 分页查询订单
     */
    @PostMapping("selectRealByPage")
    public CommonResponse selectRealByPage(@RequestBody OrderPageDto input) {
        return orderService.selectRealByPage(input);
    }

    /**
     * 修改订单状态到已支付
     */
    @GetMapping("/changeStatusPaid")
    public CommonResponse changeStatusPaid(@RequestParam Long id) {
        return orderService.changeStatusPaid(id);
    }

    /**
     * 修改订单状态到已完成
     */
    @GetMapping("/changeStatusComplete")
    public CommonResponse changeStatusComplete(@RequestParam Long id) {
        return orderService.changeStatusComplete(id);
    }

    /**
     * 修改订单状态到退单审核
     */
    @GetMapping("/changeStatusBackProcess")
    public CommonResponse changeStatusBackProcess(@RequestParam Long id) {
        return orderService.changeStatusBackProcess(id);
    }

    /**
     * 修改订单状态到退单
     */
    @GetMapping("/changeStatusBack")
    public CommonResponse changeStatusBack(@RequestParam Long id) {
        return orderService.changeStatusBack(id);
    }

    /**
     * 分页查询退单
     */
    @PostMapping("selectBackByPage")
    public CommonResponse selectBackByPage(@RequestBody OrderPageDto input) {
        return orderService.selectBackByPage(input);
    }

}
