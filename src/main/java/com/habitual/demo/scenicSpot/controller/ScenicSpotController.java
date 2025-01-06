package com.habitual.demo.scenicSpot.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.hotel.entity.dto.HotelPageDto;
import com.habitual.demo.scenicSpot.entity.ScenicSpotEntity;
import com.habitual.demo.scenicSpot.entity.dto.ScenicSpotPageDto;
import com.habitual.demo.scenicSpot.service.ScenicSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 景点门票
 */
@RestController
@RequestMapping("/scenicSpot")
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody ScenicSpotEntity input) {
        return scenicSpotService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return scenicSpotService.delete(id);
    }

    /**
     * 详情
     */
    @GetMapping("detail")
    public CommonResponse detail(@RequestParam Long id) {
        return scenicSpotService.detail(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody ScenicSpotPageDto input) {
        return scenicSpotService.selectByPage(input);
    }

    /**
     * 收藏分页查询
     */
    @PostMapping("selectByPageCollect")
    public CommonResponse selectByPageCollect(@RequestBody ScenicSpotPageDto input) {
        return scenicSpotService.selectByPageCollect(input);
    }

    /**
     * 获取种类列表
     */
    @PostMapping("selectTypeList")
    public CommonResponse selectTypeList() {
        return scenicSpotService.selectTypeList();
    }

    /**
     * 首页推荐景点
     */
    @PostMapping("recommend")
    public CommonResponse recommend() {
        return scenicSpotService.recommend();
    }

}
