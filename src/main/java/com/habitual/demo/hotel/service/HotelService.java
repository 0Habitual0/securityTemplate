package com.habitual.demo.hotel.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.hotel.entity.HotelEntity;
import com.habitual.demo.hotel.entity.dto.HotelPageDto;

/**
 * 业务层 酒店住宿
 */
public interface HotelService {

    CommonResponse save(HotelEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(HotelPageDto input);

}
