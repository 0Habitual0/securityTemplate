package com.yt.YcTravelManageSystem.touristGuide.controller;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.touristGuide.entity.TouristGuideCarouselImageEntity;
import com.yt.YcTravelManageSystem.touristGuide.entity.TouristGuideEntity;
import com.yt.YcTravelManageSystem.touristGuide.entity.dto.TouristGuideCarouselImagePageDto;
import com.yt.YcTravelManageSystem.touristGuide.entity.dto.TouristGuidePageDto;
import com.yt.YcTravelManageSystem.touristGuide.service.TouristGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 旅游攻略
 */
@RestController
@RequestMapping("/touristGuide")
public class TouristGuideController {

    @Autowired
    private TouristGuideService touristGuideService;

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody TouristGuideEntity input) {
        return touristGuideService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return touristGuideService.delete(id);
    }

    /**
     * 详情
     */
    @GetMapping("detail")
    public CommonResponse detail(@RequestParam Long id) {
        return touristGuideService.detail(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody TouristGuidePageDto input) {
        return touristGuideService.selectByPage(input);
    }

    /**
     * 收藏分页查询
     */
    @PostMapping("selectByPageCollect")
    public CommonResponse selectByPageCollect(@RequestBody TouristGuidePageDto input) {
        return touristGuideService.selectByPageCollect(input);
    }

    /**
     * 获取种类列表
     */
    @PostMapping("selectTypeList")
    public CommonResponse selectTypeList() {
        return touristGuideService.selectTypeList();
    }

    /**
     * 获取资讯下拉框列表
     */
    @GetMapping("getDropList")
    public CommonResponse getDropList() {
        return touristGuideService.getDropList();
    }

    /**
     * 新增/修改轮播图
     */
    @PostMapping("saveCarouseImage")
    public CommonResponse saveCarouseImage(@RequestBody TouristGuideCarouselImageEntity input) {
        return touristGuideService.saveCarouseImage(input);
    }

    /**
     * 删除轮播图
     */
    @GetMapping("deleteCarouseImage")
    public CommonResponse deleteCarouseImage(@RequestParam Long id) {
        return touristGuideService.deleteCarouseImage(id);
    }

    /**
     * 分页查询轮播图
     */
    @PostMapping("selectByPageCarouseImage")
    public CommonResponse selectByPageCarouseImage(@RequestBody TouristGuideCarouselImagePageDto input) {
        return touristGuideService.selectByPageCarouseImage(input);
    }

}
