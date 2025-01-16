package com.yt.YcTravelManageSystem.carouselImage.service;

import com.yt.YcTravelManageSystem.carouselImage.entity.CarouselImageEntity;
import com.yt.YcTravelManageSystem.carouselImage.entity.dto.CarouselImagePageDto;
import com.yt.YcTravelManageSystem.common.entity.CommonResponse;

/**
 * 业务层 轮播图
 */
public interface CarouselImageService {

    CommonResponse save(CarouselImageEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(CarouselImagePageDto input);

    CommonResponse select();

}
