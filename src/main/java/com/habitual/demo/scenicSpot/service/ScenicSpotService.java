package com.habitual.demo.scenicSpot.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.scenicSpot.entity.ScenicSpotEntity;
import com.habitual.demo.scenicSpot.entity.dto.ScenicSpotPageDto;

public interface ScenicSpotService {

    CommonResponse save(ScenicSpotEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(ScenicSpotPageDto input);

}
