package com.habitual.demo.scenicSpot.mapper;

import com.habitual.demo.scenicSpot.entity.ScenicSpotEntity;
import com.habitual.demo.scenicSpot.entity.dto.ScenicSpotPageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScenicSpotMapper {

    int insert(ScenicSpotEntity input);

    int update(ScenicSpotEntity input);

    int delete(Long id);

    int getTotalCount();

    List<ScenicSpotEntity> selectByPage(ScenicSpotPageDto input);

}
