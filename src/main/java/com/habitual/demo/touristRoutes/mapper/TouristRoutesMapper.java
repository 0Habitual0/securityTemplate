package com.habitual.demo.touristRoutes.mapper;

import com.habitual.demo.touristRoutes.entity.TouristRoutesEntity;
import com.habitual.demo.touristRoutes.entity.dto.TouristRoutesPageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问层Mybatis 旅游线路
 */
@Mapper
public interface TouristRoutesMapper {

    int insert(TouristRoutesEntity input);

    int update(TouristRoutesEntity input);

    int delete(Long id);

    int getTotalCount();

    List<TouristRoutesEntity> selectByPage(TouristRoutesPageDto input);

    List<TouristRoutesEntity> selectByIdIn(List<Long> ids);

    List<String> selectTypeList();

}
