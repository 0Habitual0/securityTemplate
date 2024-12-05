package com.habitual.demo.collect.service.impl;

import com.habitual.demo.collect.entity.CollectEntity;
import com.habitual.demo.collect.mapper.CollectMapper;
import com.habitual.demo.collect.service.CollectService;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.entity.PageResult;
import com.habitual.demo.common.security.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 业务层实现 收藏
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;


    @Override
    public CommonResponse save(CollectEntity input) {
        input.setUserId(UserContext.getId());
        input.setCreateBy(UserContext.getNickname());
        input.setCreateTime(new Date());
        CollectEntity isCollect = collectMapper.selectByUserIdAndBusinessId(UserContext.getId(), input.getBusinessId());
        int result;
        if (isCollect == null) {
            result = collectMapper.insert(input);
        } else {
            result = collectMapper.deleteByUserIdAndBusinessId(UserContext.getId(), input.getBusinessId());
        }
        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse getState(String businessId) {
        CollectEntity isCollect = collectMapper.selectByUserIdAndBusinessId(UserContext.getId(), businessId);
        if (isCollect == null) {
            return CommonResponse.success(false);
        } else {
            return CommonResponse.success(true);
        }
    }

    public void deleteByType(String type) {
        collectMapper.deleteByType(type);
    }

    public PageResult<CollectEntity> selectByPage(int pageNum, int pageSize, String type) {
        int offset = (pageNum - 1) * pageSize;
        List<CollectEntity> list = collectMapper.selectByPageAndUserIdAndBusinessId(UserContext.getId(), type, offset, pageSize);
        int totalCount = collectMapper.getTotalCountByUserIdAndType(UserContext.getId(), type);

        int pages = (int) Math.ceil((double) totalCount / pageSize);

        PageResult<CollectEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);
        return result;
    }

}
