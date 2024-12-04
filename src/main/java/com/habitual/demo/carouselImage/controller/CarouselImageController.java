package com.habitual.demo.carouselImage.controller;

import com.habitual.demo.carouselImage.entity.CarouselImageEntity;
import com.habitual.demo.carouselImage.entity.dto.CarouselImagePageDto;
import com.habitual.demo.carouselImage.service.CarouselImageService;
import com.habitual.demo.common.entity.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 轮播图
 */
@RestController
@RequestMapping("/carouselImage")
public class CarouselImageController {

    @Autowired
    private CarouselImageService carouselImageService;

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody CarouselImageEntity input) {
        return carouselImageService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return carouselImageService.delete(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody CarouselImagePageDto input) {
        return carouselImageService.selectByPage(input);
    }

    /**
     * 查询
     */
    @PostMapping("select")
    public CommonResponse select() {
        return carouselImageService.select();
    }

}
