package com.yt.YcTravelManageSystem.order.controller;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.order.entity.RealOrderEntity;
import com.yt.YcTravelManageSystem.order.entity.dto.OrderPageDto;
import com.yt.YcTravelManageSystem.order.service.OrderService;
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
    @PostMapping("/saveReal")
    public CommonResponse saveReal(@RequestBody RealOrderEntity input) {
        return orderService.saveReal(input);
    }

    /**
     * 分页查询订单
     */
    @PostMapping("/selectRealByPage")
    public CommonResponse selectRealByPage(@RequestBody OrderPageDto input) {
        return orderService.selectRealByPage(input);
    }

    /**
     * 修改订单状态到已支付
     */
    @GetMapping("/changeStatusPaid")
    public CommonResponse changeStatusPaid(@RequestParam Long id, String payType) {
        return orderService.changeStatusPaid(id, payType);
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
    public CommonResponse changeStatusBack(@RequestParam Long id, Boolean status) {
        return orderService.changeStatusBack(id, status);
    }

    /**
     * 分页查询退单
     */
    @PostMapping("/selectBackByPage")
    public CommonResponse selectBackByPage(@RequestBody OrderPageDto input) {
        return orderService.selectBackByPage(input);
    }

    /**
     * 首页
     */
    @GetMapping("/mainPage")
    public CommonResponse mainPage() {
        return orderService.mainPage();
    }

    /**
     * 订单/退单详情(status为true为查询订单，false查询退单)
     */
    @GetMapping("/detail")
    public CommonResponse detail(@RequestParam Long id, Boolean status) {
        return orderService.detail(id, status);
    }

}
