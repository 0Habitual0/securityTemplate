package com.yt.YcTravelManageSystem.touristGuide.service;

import com.yt.YcTravelManageSystem.touristGuide.entity.TouristGuideCarouselImageEntity;
import com.yt.YcTravelManageSystem.touristGuide.entity.TouristGuideEntity;
import com.yt.YcTravelManageSystem.touristGuide.entity.dto.TouristGuideCarouselImagePageDto;
import com.yt.YcTravelManageSystem.touristGuide.entity.dto.TouristGuidePageDto;
import com.yt.YcTravelManageSystem.common.entity.CommonResponse;

/**
 * 业务层 旅游攻略
 */
public interface TouristGuideService {

    CommonResponse save(TouristGuideEntity input);

    CommonResponse delete(Long id);

    CommonResponse detail(Long id);

    CommonResponse selectByPage(TouristGuidePageDto input);

    CommonResponse selectByPageCollect(TouristGuidePageDto input);

    CommonResponse selectTypeList();

    CommonResponse getDropList();

    CommonResponse saveCarouseImage(TouristGuideCarouselImageEntity input);

    CommonResponse deleteCarouseImage(Long id);

    CommonResponse selectByPageCarouseImage(TouristGuideCarouselImagePageDto input);

}
