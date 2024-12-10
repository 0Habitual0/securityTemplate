package com.habitual.demo.hotel.service.impl;

import com.habitual.demo.collect.entity.CollectEntity;
import com.habitual.demo.collect.service.impl.CollectServiceImpl;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.entity.PageResult;
import com.habitual.demo.common.security.context.UserContext;
import com.habitual.demo.hotel.entity.HotelEntity;
import com.habitual.demo.hotel.entity.dto.HotelPageDto;
import com.habitual.demo.hotel.mapper.HotelMapper;
import com.habitual.demo.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 业务层实现 酒店住宿
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private CollectServiceImpl collectService;

    @Override
    public CommonResponse save(HotelEntity input) {
        if (Objects.equals(input.getId(), null)) {
            input.setCreateBy(UserContext.getNickname());
            input.setCreateTime(new Date());
            return CommonResponse.success(hotelMapper.insert(input));
        } else {
            input.setUpdateBy(UserContext.getNickname());
            input.setUpdateTime(new Date());
            return CommonResponse.success(hotelMapper.update(input));
        }
    }

    @Transactional
    @Override
    public CommonResponse delete(Long id) {
        collectService.deleteByType("酒店住宿");
        return CommonResponse.success(hotelMapper.delete(id));
    }

    @Override
    public CommonResponse selectByPage(HotelPageDto input) {
        input.setOffset((input.getPageNum() - 1) * input.getPageSize());
        List<HotelEntity> list = hotelMapper.selectByPage(input);
        int totalCount = hotelMapper.getTotalCount();

        int pages = (int) Math.ceil((double) totalCount / input.getPageSize());

        PageResult<HotelEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse selectByPageCollect(HotelPageDto input) {

        PageResult<CollectEntity> collect = collectService.selectByPage(input.getPageNum(), input.getPageSize(), "酒店住宿");

        List<Long> businessIds = collect.getData().stream()
                .map(CollectEntity::getBusinessId)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        if (businessIds.isEmpty()) {
            return CommonResponse.success(collect);
        }

        PageResult<HotelEntity> result = new PageResult<>();
        result.setData(hotelMapper.selectByIdIn(businessIds));
        result.setTotalCount(collect.getTotalCount());
        result.setPages(collect.getPages());

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse selectTypeList() {
        return CommonResponse.success(hotelMapper.selectTypeList());
    }

}
