package com.yt.YcTravelManageSystem.carouselImage.mapper;

import com.yt.YcTravelManageSystem.carouselImage.entity.CarouselImageEntity;
import com.yt.YcTravelManageSystem.carouselImage.entity.dto.CarouselImagePageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问层Mybatis 轮播图
 */
@Mapper
public interface CarouselImageMapper {

    int insert(CarouselImageEntity input);

    int update(CarouselImageEntity input);

    int delete(Long id);

    int getTotalCount();

    List<CarouselImageEntity> selectByPage(CarouselImagePageDto input);

    List<String> select();

}
