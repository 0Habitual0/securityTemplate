package com.yt.YcTravelManageSystem.touristRoutes.service;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.touristRoutes.entity.TouristRoutesEntity;
import com.yt.YcTravelManageSystem.touristRoutes.entity.dto.TouristRoutesPageDto;

/**
 * 业务层 旅游线路
 */
public interface TouristRoutesService {

    CommonResponse save(TouristRoutesEntity input);

    CommonResponse delete(Long id);

    CommonResponse detail(Long id);

    CommonResponse selectByPage(TouristRoutesPageDto input);

    CommonResponse selectByPageCollect(TouristRoutesPageDto input);

    CommonResponse selectTypeList();

}
