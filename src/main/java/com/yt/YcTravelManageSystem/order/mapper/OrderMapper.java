package com.yt.YcTravelManageSystem.order.mapper;

import com.yt.YcTravelManageSystem.order.entity.BackOrderEntity;
import com.yt.YcTravelManageSystem.order.entity.RealOrderEntity;
import com.yt.YcTravelManageSystem.order.entity.dto.OrderPageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 数据访问层Mybatis 订单退单
 */
@Mapper
public interface OrderMapper {

    int insertReal(RealOrderEntity input);

    List<RealOrderEntity> selectRealByPage(OrderPageDto input);

    int getRealTotalCount(Long userId);

    RealOrderEntity selectRealById(Long id);

    BackOrderEntity selectBackById(Long id);

    int updateReal(RealOrderEntity input);

    int updateBack(BackOrderEntity input);

    int deleteRealById(Long id);

    int deleteBackById(Long id);

    int insertBack(BackOrderEntity input);

    int getBackTotalCount(Long userId);

    List<BackOrderEntity> selectBackByPage(OrderPageDto input);

    List<RealOrderEntity> selectByTypeAndCreateTimeAfter(String type, Date createTimeAfter);

    List<RealOrderEntity> findAllReal();

}
