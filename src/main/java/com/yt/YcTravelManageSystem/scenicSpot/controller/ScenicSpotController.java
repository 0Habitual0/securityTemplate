package com.yt.YcTravelManageSystem.scenicSpot.controller;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.scenicSpot.entity.ScenicSpotEntity;
import com.yt.YcTravelManageSystem.scenicSpot.entity.dto.ScenicSpotPageDto;
import com.yt.YcTravelManageSystem.scenicSpot.service.ScenicSpotService;
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
