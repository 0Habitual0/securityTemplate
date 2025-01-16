package com.yt.YcTravelManageSystem.hotel.controller;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.hotel.entity.HotelEntity;
import com.yt.YcTravelManageSystem.hotel.entity.dto.HotelPageDto;
import com.yt.YcTravelManageSystem.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 酒店住宿
 */
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody HotelEntity input) {
        return hotelService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return hotelService.delete(id);
    }

    /**
     * 详情
     */
    @GetMapping("detail")
    public CommonResponse detail(@RequestParam Long id) {
        return hotelService.detail(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody HotelPageDto input) {
        return hotelService.selectByPage(input);
    }

    /**
     * 收藏分页查询
     */
    @PostMapping("selectByPageCollect")
    public CommonResponse selectByPageCollect(@RequestBody HotelPageDto input) {
        return hotelService.selectByPageCollect(input);
    }

    /**
     * 获取种类列表
     */
    @PostMapping("selectTypeList")
    public CommonResponse selectTypeList() {
        return hotelService.selectTypeList();
    }

}
