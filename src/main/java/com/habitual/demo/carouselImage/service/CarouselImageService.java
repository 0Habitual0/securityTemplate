package com.habitual.demo.carouselImage.service;

import com.habitual.demo.carouselImage.entity.CarouselImageEntity;
import com.habitual.demo.carouselImage.entity.dto.CarouselImagePageDto;
import com.habitual.demo.common.entity.CommonResponse;

/**
 * 业务层 轮播图
 */
public interface CarouselImageService {

    CommonResponse save(CarouselImageEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(CarouselImagePageDto input);

    CommonResponse select();

}
