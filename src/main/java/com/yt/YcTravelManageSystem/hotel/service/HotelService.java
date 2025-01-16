package com.yt.YcTravelManageSystem.hotel.service;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.hotel.entity.HotelEntity;
import com.yt.YcTravelManageSystem.hotel.entity.dto.HotelPageDto;

/**
 * 业务层 酒店住宿
 */
public interface HotelService {

    CommonResponse save(HotelEntity input);

    CommonResponse delete(Long id);

    CommonResponse detail(Long id);

    CommonResponse selectByPage(HotelPageDto input);

    CommonResponse selectByPageCollect(HotelPageDto input);

    CommonResponse selectTypeList();

}
