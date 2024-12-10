package com.habitual.demo.scenicSpot.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.scenicSpot.entity.ScenicSpotEntity;
import com.habitual.demo.scenicSpot.entity.dto.ScenicSpotPageDto;

/**
 * 业务层 景点门票
 */
public interface ScenicSpotService {

    CommonResponse save(ScenicSpotEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(ScenicSpotPageDto input);

    CommonResponse selectByPageCollect(ScenicSpotPageDto input);

    CommonResponse selectTypeList();

    CommonResponse recommend();

}
