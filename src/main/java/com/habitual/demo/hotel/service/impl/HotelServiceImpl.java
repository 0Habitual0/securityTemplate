package com.habitual.demo.hotel.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.entity.PageResult;
import com.habitual.demo.common.security.context.UserContext;
import com.habitual.demo.hotel.entity.HotelEntity;
import com.habitual.demo.hotel.entity.dto.HotelPageDto;
import com.habitual.demo.hotel.mapper.HotelMapper;
import com.habitual.demo.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 业务层实现 酒店住宿
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

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

    @Override
    public CommonResponse delete(Long id) {
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

}
