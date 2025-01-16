package com.yt.YcTravelManageSystem.scenicSpot.service;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.scenicSpot.entity.ScenicSpotEntity;
import com.yt.YcTravelManageSystem.scenicSpot.entity.dto.ScenicSpotPageDto;

/**
 * 业务层 景点门票
 */
public interface ScenicSpotService {

    CommonResponse save(ScenicSpotEntity input);

    CommonResponse delete(Long id);

    CommonResponse detail(Long id);

    CommonResponse selectByPage(ScenicSpotPageDto input);

    CommonResponse selectByPageCollect(ScenicSpotPageDto input);

    CommonResponse selectTypeList();

    CommonResponse recommend();

}
