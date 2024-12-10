package com.habitual.demo.scenicSpot.service.impl;

import com.habitual.demo.collect.entity.CollectEntity;
import com.habitual.demo.collect.service.CollectService;
import com.habitual.demo.collect.service.impl.CollectServiceImpl;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.entity.PageResult;
import com.habitual.demo.common.security.context.UserContext;
import com.habitual.demo.hotel.entity.HotelEntity;
import com.habitual.demo.hotel.entity.dto.HotelPageDto;
import com.habitual.demo.scenicSpot.entity.ScenicSpotEntity;
import com.habitual.demo.scenicSpot.entity.dto.ScenicSpotPageDto;
import com.habitual.demo.scenicSpot.mapper.ScenicSpotMapper;
import com.habitual.demo.scenicSpot.service.ScenicSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 业务层实现 景点门票
 */
@Service
public class ScenicSpotImpl implements ScenicSpotService {

    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    @Autowired
    private CollectServiceImpl collectService;

    @Override
    public CommonResponse save(ScenicSpotEntity input) {
        if (Objects.equals(input.getId(), null)) {
            input.setCreateBy(UserContext.getNickname());
            input.setCreateTime(new Date());
            return CommonResponse.success(scenicSpotMapper.insert(input));
        } else {
            input.setUpdateBy(UserContext.getNickname());
            input.setUpdateTime(new Date());
            return CommonResponse.success(scenicSpotMapper.update(input));
        }
    }

    @Override
    public CommonResponse delete(Long id) {
        collectService.deleteByType("景点门票");
        return CommonResponse.success(scenicSpotMapper.delete(id));
    }

    @Override
    public CommonResponse selectByPage(ScenicSpotPageDto input) {
        input.setOffset((input.getPageNum() - 1) * input.getPageSize());
        List<ScenicSpotEntity> list = scenicSpotMapper.selectByPage(input);
        int totalCount = scenicSpotMapper.getTotalCount();

        int pages = (int) Math.ceil((double) totalCount / input.getPageSize());

        PageResult<ScenicSpotEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse selectByPageCollect(ScenicSpotPageDto input) {

        PageResult<CollectEntity> collect = collectService.selectByPage(input.getPageNum(), input.getPageSize(), "景点门票");

        List<Long> businessIds = collect.getData().stream()
                .map(CollectEntity::getBusinessId)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        if (businessIds.isEmpty()) {
            return CommonResponse.success(collect);
        }

        PageResult<ScenicSpotEntity> result = new PageResult<>();
        result.setData(scenicSpotMapper.selectByIdIn(businessIds));
        result.setTotalCount(collect.getTotalCount());
        result.setPages(collect.getPages());

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse recommend() {
        return CommonResponse.success(scenicSpotMapper.selectRandomFive());
    }

}
