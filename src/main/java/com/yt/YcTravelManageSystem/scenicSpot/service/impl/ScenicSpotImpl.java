package com.yt.YcTravelManageSystem.scenicSpot.service.impl;

import com.yt.YcTravelManageSystem.collect.entity.CollectEntity;
import com.yt.YcTravelManageSystem.collect.service.impl.CollectServiceImpl;
import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.common.entity.PageResult;
import com.yt.YcTravelManageSystem.common.security.context.UserContext;
import com.yt.YcTravelManageSystem.scenicSpot.entity.ScenicSpotEntity;
import com.yt.YcTravelManageSystem.scenicSpot.entity.dto.ScenicSpotPageDto;
import com.yt.YcTravelManageSystem.scenicSpot.mapper.ScenicSpotMapper;
import com.yt.YcTravelManageSystem.scenicSpot.service.ScenicSpotService;
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
    public CommonResponse detail(Long id) {
        ScenicSpotEntity entity = scenicSpotMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return CommonResponse.fail("数据不存在");
        }
        return CommonResponse.success(entity);
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
    public CommonResponse selectTypeList() {
        return CommonResponse.success(scenicSpotMapper.selectTypeList());
    }

    @Override
    public CommonResponse recommend() {
        return CommonResponse.success(scenicSpotMapper.selectRandomFive());
    }

}
