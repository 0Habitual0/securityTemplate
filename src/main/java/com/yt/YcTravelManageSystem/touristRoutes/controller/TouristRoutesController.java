package com.yt.YcTravelManageSystem.touristRoutes.controller;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.touristRoutes.entity.TouristRoutesEntity;
import com.yt.YcTravelManageSystem.touristRoutes.entity.dto.TouristRoutesPageDto;
import com.yt.YcTravelManageSystem.touristRoutes.service.TouristRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 旅游线路
 */
@RestController
@RequestMapping("/touristRoutes")
public class TouristRoutesController {

    @Autowired
    private TouristRoutesService touristRoutesService;

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody TouristRoutesEntity input) {
        return touristRoutesService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return touristRoutesService.delete(id);
    }

    /**
     * 详情
     */
    @GetMapping("detail")
    public CommonResponse detail(@RequestParam Long id) {
        return touristRoutesService.detail(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody TouristRoutesPageDto input) {
        return touristRoutesService.selectByPage(input);
    }

    /**
     * 收藏分页查询
     */
    @PostMapping("selectByPageCollect")
    public CommonResponse selectByPageCollect(@RequestBody TouristRoutesPageDto input) {
        return touristRoutesService.selectByPageCollect(input);
    }

    /**
     * 获取种类列表
     */
    @PostMapping("selectTypeList")
    public CommonResponse selectTypeList() {
        return touristRoutesService.selectTypeList();
    }

}
