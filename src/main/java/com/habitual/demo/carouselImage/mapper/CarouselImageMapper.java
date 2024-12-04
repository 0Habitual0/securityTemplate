package com.habitual.demo.carouselImage.mapper;

import com.habitual.demo.carouselImage.entity.CarouselImageEntity;
import com.habitual.demo.carouselImage.entity.dto.CarouselImagePageDto;
import com.habitual.demo.hotel.entity.dto.HotelPageDto;
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

}
