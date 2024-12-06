package com.habitual.demo.touristRoutes.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.touristRoutes.entity.TouristRoutesEntity;
import com.habitual.demo.touristRoutes.entity.dto.TouristRoutesPageDto;

/**
 * 业务层 旅游线路
 */
public interface TouristRoutesService {

    CommonResponse save(TouristRoutesEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(TouristRoutesPageDto input);

    CommonResponse selectByPageCollect(TouristRoutesPageDto input);

}
