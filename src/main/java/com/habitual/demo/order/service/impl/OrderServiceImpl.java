package com.habitual.demo.order.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.entity.PageResult;
import com.habitual.demo.common.security.context.UserContext;
import com.habitual.demo.order.entity.BackOrderEntity;
import com.habitual.demo.order.entity.RealOrderEntity;
import com.habitual.demo.order.entity.dto.OrderPageDto;
import com.habitual.demo.order.mapper.OrderMapper;
import com.habitual.demo.order.service.OrderService;
import com.habitual.demo.scenicSpot.entity.ScenicSpotEntity;
import com.habitual.demo.scenicSpot.mapper.ScenicSpotMapper;
import com.habitual.demo.touristRoutes.entity.TouristRoutesEntity;
import com.habitual.demo.touristRoutes.mapper.TouristRoutesMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 业务层实现 订单退单
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    @Autowired
    private TouristRoutesMapper touristRoutesMapper;

    @Override
    @Transactional
    public CommonResponse saveReal(RealOrderEntity input) {
        if (Objects.equals(input.getType(), "景点门票")) {
            ScenicSpotEntity entity = scenicSpotMapper.selectById(input.getBusinessId());
            if (entity == null) {
                return CommonResponse.fail("该商品不存在");
            }
            if (entity.getStock() < input.getNum()) {
                return CommonResponse.fail("该商品库存不足");
            }
            input.setUnitPrice(entity.getPrice());
            input.setTotalPrice(entity.getPrice() * input.getNum());
            input.setRemark("景点门票订单：" + entity.getName());
            entity.setStock(entity.getStock() - input.getNum());
            scenicSpotMapper.update(entity);
        } else if (Objects.equals(input.getType(), "旅游线路")) {
            TouristRoutesEntity entity = touristRoutesMapper.selectById(input.getBusinessId());
            if (entity == null) {
                return CommonResponse.fail("该商品不存在");
            }
            if (entity.getStock() < input.getNum()) {
                return CommonResponse.fail("该商品库存不足");
            }
            input.setUnitPrice(entity.getPrice());
            input.setTotalPrice(entity.getPrice() * input.getNum());
            input.setRemark("旅游线路订单：" + entity.getName());
            entity.setStock(entity.getStock() - input.getNum());
            touristRoutesMapper.update(entity);
        }

        input.setUserId(UserContext.getId());
        input.setOrderCode("ORDER-" + UUID.randomUUID());
        input.setStatus("未支付");
        input.setCreateBy(UserContext.getNickname());
        input.setCreateTime(new Date());
        return CommonResponse.success(orderMapper.insertReal(input));
    }

    @Override
    public CommonResponse selectRealByPage(OrderPageDto input) {
        input.setOffset((input.getPageNum() - 1) * input.getPageSize());
        List<RealOrderEntity> list = orderMapper.selectRealByPage(input);
        int totalCount = orderMapper.getRealTotalCount();

        int pages = (int) Math.ceil((double) totalCount / input.getPageSize());

        PageResult<RealOrderEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse changeStatusPaid(Long id) {
        RealOrderEntity entity = orderMapper.selectRealById(id);
        if (entity == null) {
            return CommonResponse.fail("订单信息不存在");
        }
        if (!Objects.equals(entity.getStatus(), "未支付")) {
            return CommonResponse.fail("订单状态非待审核");
        }
        entity.setStatus("已支付");
        entity.setUpdateBy(UserContext.getNickname());
        entity.setUpdateTime(new Date());
        return CommonResponse.success(orderMapper.updateReal(entity));
    }

    @Override
    public CommonResponse changeStatusComplete(Long id) {
        RealOrderEntity entity = orderMapper.selectRealById(id);
        if (entity == null) {
            return CommonResponse.fail("订单信息不存在");
        }
        if (!Objects.equals(entity.getStatus(), "已支付")) {
            return CommonResponse.fail("订单状态非待审核");
        }
        entity.setStatus("已完成");
        entity.setUpdateBy(UserContext.getNickname());
        entity.setUpdateTime(new Date());
        return CommonResponse.success(orderMapper.updateReal(entity));
    }

    @Override
    public CommonResponse changeStatusBackProcess(Long id) {
        RealOrderEntity entity = orderMapper.selectRealById(id);
        if (entity == null) {
            return CommonResponse.fail("订单信息不存在");
        }
        entity.setStatus("退单审核中");
        entity.setUpdateBy(UserContext.getNickname());
        entity.setUpdateTime(new Date());
        return CommonResponse.success(orderMapper.updateReal(entity));
    }

    @Override
    @Transactional
    public CommonResponse changeStatusBack(Long id) {
        RealOrderEntity entity = orderMapper.selectRealById(id);
        if (entity == null) {
            return CommonResponse.fail("订单信息不存在");
        }
        if (!Objects.equals(entity.getStatus(), "退单审核中")) {
            return CommonResponse.fail("订单状态非待审核");
        }
        entity.setStatus("已退单");
        entity.setUpdateBy(UserContext.getNickname());
        entity.setUpdateTime(new Date());
        orderMapper.deleteRealById(id);

        BackOrderEntity backOrderEntity = new BackOrderEntity();
        BeanUtils.copyProperties(entity, backOrderEntity);
        return CommonResponse.success(orderMapper.insertBack(backOrderEntity));
    }

    @Override
    public CommonResponse selectBackByPage(OrderPageDto input) {
        input.setOffset((input.getPageNum() - 1) * input.getPageSize());
        List<BackOrderEntity> list = orderMapper.selectBackByPage(input);
        int totalCount = orderMapper.getBackTotalCount();

        int pages = (int) Math.ceil((double) totalCount / input.getPageSize());

        PageResult<BackOrderEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);

        return CommonResponse.success(result);
    }

}
