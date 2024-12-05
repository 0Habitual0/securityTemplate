package com.habitual.demo.carouselImage.service.impl;

import com.habitual.demo.carouselImage.entity.CarouselImageEntity;
import com.habitual.demo.carouselImage.entity.dto.CarouselImagePageDto;
import com.habitual.demo.carouselImage.mapper.CarouselImageMapper;
import com.habitual.demo.carouselImage.service.CarouselImageService;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.entity.PageResult;
import com.habitual.demo.common.security.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 业务层实现 轮播图
 */
@Service
public class CarouselImageServiceImpl implements CarouselImageService {

    @Autowired
    private CarouselImageMapper carouselImageMapper;

    @Override
    public CommonResponse save(CarouselImageEntity input) {
        if (Objects.equals(input.getId(), null)) {
            input.setCreateBy(UserContext.getNickname());
            input.setCreateTime(new Date());
            return CommonResponse.success(carouselImageMapper.insert(input));
        } else {
            input.setUpdateBy(UserContext.getNickname());
            input.setUpdateTime(new Date());
            return CommonResponse.success(carouselImageMapper.update(input));
        }
    }

    @Override
    public CommonResponse delete(Long id) {
        return CommonResponse.success(carouselImageMapper.delete(id));
    }

    @Override
    public CommonResponse selectByPage(CarouselImagePageDto input) {
        input.setOffset((input.getPageNum() - 1) * input.getPageSize());
        List<CarouselImageEntity> list = carouselImageMapper.selectByPage(input);
        int totalCount = carouselImageMapper.getTotalCount();

        int pages = (int) Math.ceil((double) totalCount / input.getPageSize());

        PageResult<CarouselImageEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);

        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse select() {
        return CommonResponse.success(carouselImageMapper.select());
    }

}
