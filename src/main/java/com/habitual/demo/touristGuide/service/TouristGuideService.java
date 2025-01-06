package com.habitual.demo.touristGuide.service;

import com.habitual.demo.touristGuide.entity.TouristGuideEntity;
import com.habitual.demo.touristGuide.entity.dto.TouristGuidePageDto;
import com.habitual.demo.common.entity.CommonResponse;

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

}
