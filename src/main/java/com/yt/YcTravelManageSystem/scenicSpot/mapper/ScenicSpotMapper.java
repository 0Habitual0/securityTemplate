package com.yt.YcTravelManageSystem.scenicSpot.mapper;

import com.yt.YcTravelManageSystem.scenicSpot.entity.ScenicSpotEntity;
import com.yt.YcTravelManageSystem.scenicSpot.entity.dto.ScenicSpotPageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问层Mybatis 景点门票
 */
@Mapper
public interface ScenicSpotMapper {

    int insert(ScenicSpotEntity input);

    int update(ScenicSpotEntity input);

    int delete(Long id);

    int getTotalCount();

    List<ScenicSpotEntity> selectByPage(ScenicSpotPageDto input);

    List<ScenicSpotEntity> selectByIdIn(List<Long> ids);

    List<String> selectTypeList();

    List<ScenicSpotEntity> selectRandomFive();

    ScenicSpotEntity selectById(Long id);

}
