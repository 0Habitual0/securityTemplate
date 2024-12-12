package com.habitual.demo.order.mapper;

import com.habitual.demo.order.entity.BackOrderEntity;
import com.habitual.demo.order.entity.RealOrderEntity;
import com.habitual.demo.order.entity.dto.OrderPageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问层Mybatis 订单退单
 */
@Mapper
public interface OrderMapper {

    int insertReal(RealOrderEntity input);

    List<RealOrderEntity> selectRealByPage(OrderPageDto input);

    int getRealTotalCount();

    RealOrderEntity selectRealById(Long id);

    int updateReal(RealOrderEntity input);

    int deleteRealById(Long id);

    int insertBack(BackOrderEntity input);

    int getBackTotalCount();

    List<BackOrderEntity> selectBackByPage(OrderPageDto input);

}
