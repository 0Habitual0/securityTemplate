package com.habitual.demo.touristGuide.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.touristGuide.entity.TouristGuideEntity;
import com.habitual.demo.touristGuide.entity.dto.TouristGuidePageDto;
import com.habitual.demo.touristGuide.service.TouristGuideService;
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

}
