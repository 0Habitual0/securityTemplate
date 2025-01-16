package com.yt.YcTravelManageSystem.collect.mapper;

import com.yt.YcTravelManageSystem.collect.entity.CollectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问层Mybatis 收藏
 */
@Mapper
public interface CollectMapper {

    int insert(CollectEntity input);

    int deleteByUserIdAndBusinessIdAndType(Long userId, String businessId, String type);

    CollectEntity selectByUserIdAndBusinessIdAndType(Long userId, String businessId, String type);

    List<CollectEntity> findAll();

    void deleteByType(String type);

    int getTotalCountByUserIdAndType(Long userId, String type);

    List<CollectEntity> selectByPageAndUserIdAndType(Long userId, String type, int offset, int pageSize);

}
