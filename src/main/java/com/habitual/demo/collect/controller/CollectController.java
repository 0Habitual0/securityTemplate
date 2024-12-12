package com.habitual.demo.collect.controller;

import com.habitual.demo.collect.entity.CollectEntity;
import com.habitual.demo.collect.service.CollectService;
import com.habitual.demo.common.entity.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 收藏
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 新增/删除
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody CollectEntity input) {
        return collectService.save(input);
    }

    /**
     * 获取收藏状态
     */
    @GetMapping("getState")
    public CommonResponse getState(@RequestParam String businessId, @RequestParam String type) {
        return collectService.getState(businessId, type);
    }

    /**
     * 收藏饼图
     */
    @GetMapping("pieChart")
    public CommonResponse pieChart() {
        return collectService.pieChart();
    }

}
