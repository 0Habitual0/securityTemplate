package com.habitual.demo.touristGuide.service.impl;

import com.habitual.demo.touristGuide.entity.TouristGuideEntity;
import com.habitual.demo.touristGuide.entity.dto.TouristGuidePageDto;
import com.habitual.demo.touristGuide.mapper.TouristGuideMapper;
import com.habitual.demo.touristGuide.service.TouristGuideService;
import com.habitual.demo.collect.entity.CollectEntity;
import com.habitual.demo.collect.service.impl.CollectServiceImpl;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.entity.PageResult;
import com.habitual.demo.common.security.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 业务层实现 旅游攻略
 */
@Service
public class TouristGuideServiceImpl implements TouristGuideService {

    @Autowired
    private TouristGuideMapper touristGuideMapper;

    @Autowired
    private CollectServiceImpl collectService;

    @Override
    public CommonResponse save(TouristGuideEntity input) {
        if (Objects.equals(input.getId(), null)) {
            input.setCreateBy(UserContext.getNickname());
            input.setCreateTime(new Date());
            return CommonResponse.success(touristGuideMapper.insert(input));
        } else {
            input.setUpdateBy(UserContext.getNickname());
            input.setUpdateTime(new Date());
            return CommonResponse.success(touristGuideMapper.update(input));
        }
    }

    @Override
    public CommonResponse delete(Long id) {
        collectService.deleteByType("旅游攻略");
        return CommonResponse.success(touristGuideMapper.delete(id));
    }

    @Override
    public CommonResponse selectByPage(TouristGuidePageDto input) {
        input.setOffset((input.getPageNum() - 1) * input.getPageSize());
        List<TouristGuideEntity> list = touristGuideMapper.selectByPage(input);
        int totalCount = touristGuideMapper.getTotalCount();

        int pages = (int) Math.ceil((double) totalCount / input.getPageSize());

        PageResult<TouristGuideEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse selectByPageCollect(TouristGuidePageDto input) {

        PageResult<CollectEntity> collect = collectService.selectByPage(input.getPageNum(), input.getPageSize(), "旅游攻略");

        List<Long> businessIds = collect.getData().stream()
                .map(CollectEntity::getBusinessId)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        if (businessIds.isEmpty()) {
            return CommonResponse.success(collect);
        }

        PageResult<TouristGuideEntity> result = new PageResult<>();
        result.setData(touristGuideMapper.selectByIdIn(businessIds));
        result.setTotalCount(collect.getTotalCount());
        result.setPages(collect.getPages());

        return CommonResponse.success(result);
    }
}
