package com.habitual.demo.touristGuide.mapper;

import com.habitual.demo.touristGuide.entity.TouristGuideCarouselImageEntity;
import com.habitual.demo.touristGuide.entity.dto.TouristGuideCarouselImagePageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问层Mybatis 旅游攻略轮播图
 */
@Mapper
public interface TouristGuideCarouselImageMapper {

    int insert(TouristGuideCarouselImageEntity input);

    int update(TouristGuideCarouselImageEntity input);

    int delete(Long id);

    int getTotalCount();

    List<TouristGuideCarouselImageEntity> selectByPage(TouristGuideCarouselImagePageDto input);

    List<TouristGuideCarouselImageEntity> selectByBusinessId(Long businessId);

}
