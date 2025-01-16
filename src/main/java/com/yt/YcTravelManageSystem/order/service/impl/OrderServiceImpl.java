package com.yt.YcTravelManageSystem.order.service.impl;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.common.entity.PageResult;
import com.yt.YcTravelManageSystem.common.security.context.UserContext;
import com.yt.YcTravelManageSystem.order.entity.BackOrderEntity;
import com.yt.YcTravelManageSystem.order.entity.RealOrderEntity;
import com.yt.YcTravelManageSystem.order.entity.dto.MainPageDto;
import com.yt.YcTravelManageSystem.order.entity.dto.OrderPageDto;
import com.yt.YcTravelManageSystem.order.mapper.OrderMapper;
import com.yt.YcTravelManageSystem.order.service.OrderService;
import com.yt.YcTravelManageSystem.scenicSpot.entity.ScenicSpotEntity;
import com.yt.YcTravelManageSystem.scenicSpot.mapper.ScenicSpotMapper;
import com.yt.YcTravelManageSystem.touristRoutes.entity.TouristRoutesEntity;
import com.yt.YcTravelManageSystem.touristRoutes.mapper.TouristRoutesMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
            input.setImage(entity.getImage());
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
            input.setImage(entity.getImage());
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
        orderMapper.insertReal(input);
        return CommonResponse.success(input);
    }

    @Override
    public CommonResponse selectRealByPage(OrderPageDto input) {
        if (input.isUserSelect()) {
            input.setUserId(UserContext.getId());
        } else {
            input.setUserId(null);
        }
        input.setOffset((input.getPageNum() - 1) * input.getPageSize());
        List<RealOrderEntity> list = orderMapper.selectRealByPage(input);
        int totalCount = orderMapper.getRealTotalCount(input.getUserId());

        int pages = (int) Math.ceil((double) totalCount / input.getPageSize());

        PageResult<RealOrderEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse changeStatusPaid(Long id, String payType) {
        RealOrderEntity entity = orderMapper.selectRealById(id);
        if (entity == null) {
            return CommonResponse.fail("订单信息不存在");
        }
        if (!Objects.equals(entity.getStatus(), "未支付")) {
            return CommonResponse.fail("订单状态非待审核");
        }
        entity.setPayType(payType);
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
        entity.setStagingStatus(entity.getStatus());
        BackOrderEntity backOrderEntity = new BackOrderEntity();
        BeanUtils.copyProperties(entity, backOrderEntity);
        orderMapper.insertBack(backOrderEntity);
        return CommonResponse.success(orderMapper.updateReal(entity));
    }

    @Override
    @Transactional
    public CommonResponse changeStatusBack(Long id, Boolean status) {
        BackOrderEntity entity = orderMapper.selectBackById(id);
        if (entity == null) {
            return CommonResponse.fail("订单信息不存在");
        }
        if (!Objects.equals(entity.getStatus(), "退单审核中")) {
            return CommonResponse.fail("订单状态非待审核");
        }
        if (status) {
            entity.setStatus("已退单");
            entity.setUpdateBy(UserContext.getNickname());
            entity.setUpdateTime(new Date());
            orderMapper.deleteRealById(id);
            return CommonResponse.success(orderMapper.updateBack(entity));
        } else {

            RealOrderEntity realOrderEntity = new RealOrderEntity();
            BeanUtils.copyProperties(entity, realOrderEntity);
            entity.setStatus(entity.getStagingStatus());
            entity.setUpdateBy(UserContext.getNickname());
            entity.setUpdateTime(new Date());
            orderMapper.deleteBackById(id);
            return CommonResponse.success(orderMapper.updateReal(realOrderEntity));
        }
    }

    @Override
    public CommonResponse selectBackByPage(OrderPageDto input) {
        if (input.isUserSelect()) {
            input.setUserId(UserContext.getId());
        } else {
            input.setUserId(null);
        }
        input.setOffset((input.getPageNum() - 1) * input.getPageSize());
        List<BackOrderEntity> list = orderMapper.selectBackByPage(input);
        int totalCount = orderMapper.getBackTotalCount(input.getUserId());

        int pages = (int) Math.ceil((double) totalCount / input.getPageSize());

        PageResult<BackOrderEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse mainPage() {
        MainPageDto output = new MainPageDto();
        List<RealOrderEntity> allReal = orderMapper.findAllReal();
        output.setScenicSpotNum(allReal.stream().filter(a -> Objects.equals(a.getType(), "景点门票")).count());
        output.setTouristRoutesNum(allReal.stream().filter(a -> Objects.equals(a.getType(), "旅游线路")).count());
        output.setTotalNum(output.getScenicSpotNum() + output.getTouristRoutesNum());
        output.setBackNum(orderMapper.getBackTotalCount(null));
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();

        // 将时间设置为当天零点
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // 将日期减去7天，得到一周前的日期
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        List<RealOrderEntity> weekSS = orderMapper.selectByTypeAndCreateTimeAfter("景点门票", calendar.getTime());
        List<RealOrderEntity> weekTR = orderMapper.selectByTypeAndCreateTimeAfter("旅游线路", calendar.getTime());
        Map<Date, Integer> scenicSpotWeek = new HashMap<>();
        Map<Date, Integer> touristRoutesWeek = new HashMap<>();

        // 处理 "景点门票" 类型的订单
        for (RealOrderEntity order : weekSS) {
            Date orderDate = getZeroTimeDate(order.getCreateTime());
            scenicSpotWeek.put(orderDate, scenicSpotWeek.getOrDefault(orderDate, 0) + 1);
        }

        // 处理 "旅游线路" 类型的订单
        for (RealOrderEntity order : weekTR) {
            Date orderDate = getZeroTimeDate(order.getCreateTime());
            touristRoutesWeek.put(orderDate, touristRoutesWeek.getOrDefault(orderDate, 0) + 1);
        }

        output.setScenicSpotWeek(scenicSpotWeek);
        output.setTouristRoutesWeek(touristRoutesWeek);

        return CommonResponse.success(output);
    }

    @Override
    public CommonResponse detail(Long id, Boolean status) {
        if (status) {
            return CommonResponse.success(orderMapper.selectRealById(id));
        } else {
            return CommonResponse.success(orderMapper.selectBackById(id));
        }
    }

    // 获取零点时间的方法
    private static Date getZeroTimeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
