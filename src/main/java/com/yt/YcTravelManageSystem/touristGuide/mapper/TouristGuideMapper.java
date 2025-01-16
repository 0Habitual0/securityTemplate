package com.yt.YcTravelManageSystem.touristGuide.mapper;

import com.yt.YcTravelManageSystem.touristGuide.entity.TouristGuideEntity;
import com.yt.YcTravelManageSystem.touristGuide.entity.dto.TouristGuidePageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问层Mybatis 旅游攻略
 */
@Mapper
public interface TouristGuideMapper {

    int insert(TouristGuideEntity input);

    int update(TouristGuideEntity input);

    int delete(Long id);

    TouristGuideEntity selectById(Long id);

    int getTotalCount();

    List<TouristGuideEntity> selectByPage(TouristGuidePageDto input);

    List<TouristGuideEntity> selectByIdIn(List<Long> ids);

    List<String> selectTypeList();

    List<TouristGuideEntity> getDropList();

}
