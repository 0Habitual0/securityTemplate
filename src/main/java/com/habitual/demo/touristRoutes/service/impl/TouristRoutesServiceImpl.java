package com.habitual.demo.touristRoutes.service.impl;

import com.habitual.demo.collect.entity.CollectEntity;
import com.habitual.demo.collect.service.impl.CollectServiceImpl;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.entity.PageResult;
import com.habitual.demo.common.security.context.UserContext;
import com.habitual.demo.hotel.entity.HotelEntity;
import com.habitual.demo.touristRoutes.entity.TouristRoutesEntity;
import com.habitual.demo.touristRoutes.entity.dto.TouristRoutesPageDto;
import com.habitual.demo.touristRoutes.mapper.TouristRoutesMapper;
import com.habitual.demo.touristRoutes.service.TouristRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 业务层实现 旅游线路
 */
@Service
public class TouristRoutesServiceImpl implements TouristRoutesService {

    @Autowired
    private TouristRoutesMapper touristRoutesMapper;

    @Autowired
    private CollectServiceImpl collectService;

    @Override
    public CommonResponse save(TouristRoutesEntity input) {
        if (Objects.equals(input.getId(), null)) {
            input.setCreateBy(UserContext.getNickname());
            input.setCreateTime(new Date());
            return CommonResponse.success(touristRoutesMapper.insert(input));
        } else {
            input.setUpdateBy(UserContext.getNickname());
            input.setUpdateTime(new Date());
            return CommonResponse.success(touristRoutesMapper.update(input));
        }
    }

    @Override
    public CommonResponse delete(Long id) {
        collectService.deleteByType("旅游线路");
        return CommonResponse.success(touristRoutesMapper.delete(id));
    }

    @Override
    public CommonResponse detail(Long id) {
        TouristRoutesEntity entity = touristRoutesMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return CommonResponse.fail("数据不存在");
        }
        return CommonResponse.success(entity);
    }

    @Override
    public CommonResponse selectByPage(TouristRoutesPageDto input) {
        input.setOffset((input.getPageNum() - 1) * input.getPageSize());
        List<TouristRoutesEntity> list = touristRoutesMapper.selectByPage(input);
        int totalCount = touristRoutesMapper.getTotalCount();

        int pages = (int) Math.ceil((double) totalCount / input.getPageSize());

        PageResult<TouristRoutesEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse selectByPageCollect(TouristRoutesPageDto input) {

        PageResult<CollectEntity> collect = collectService.selectByPage(input.getPageNum(), input.getPageSize(), "旅游线路");

        List<Long> businessIds = collect.getData().stream()
                .map(CollectEntity::getBusinessId)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        if (businessIds.isEmpty()) {
            return CommonResponse.success(collect);
        }

        PageResult<TouristRoutesEntity> result = new PageResult<>();
        result.setData(touristRoutesMapper.selectByIdIn(businessIds));
        result.setTotalCount(collect.getTotalCount());
        result.setPages(collect.getPages());

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse selectTypeList() {
        return CommonResponse.success(touristRoutesMapper.selectTypeList());
    }

}
