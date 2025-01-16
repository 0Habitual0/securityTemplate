package com.yt.YcTravelManageSystem.hotel.mapper;

import com.yt.YcTravelManageSystem.hotel.entity.HotelEntity;
import com.yt.YcTravelManageSystem.hotel.entity.dto.HotelPageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问层Mybatis 酒店住宿
 */
@Mapper
public interface HotelMapper {

    int insert(HotelEntity input);

    int update(HotelEntity input);

    int delete(Long id);

    HotelEntity selectById(Long id);

    int getTotalCount();

    List<HotelEntity> selectByPage(HotelPageDto input);

    List<HotelEntity> selectByIdIn(List<Long> ids);

    List<String> selectTypeList();

}
