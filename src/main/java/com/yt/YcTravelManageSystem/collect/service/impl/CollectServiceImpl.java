package com.yt.YcTravelManageSystem.collect.service.impl;

import com.yt.YcTravelManageSystem.collect.entity.CollectEntity;
import com.yt.YcTravelManageSystem.collect.entity.dto.CollectPieChartDto;
import com.yt.YcTravelManageSystem.collect.mapper.CollectMapper;
import com.yt.YcTravelManageSystem.collect.service.CollectService;
import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.common.entity.PageResult;
import com.yt.YcTravelManageSystem.common.security.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        CollectEntity isCollect = collectMapper.selectByUserIdAndBusinessIdAndType(UserContext.getId(), input.getBusinessId(), input.getType());
        boolean result = false;
        if (isCollect == null) {
            collectMapper.insert(input);
            result = true;
        } else {
            collectMapper.deleteByUserIdAndBusinessIdAndType(UserContext.getId(), input.getBusinessId(), input.getType());
            result = false;
        }
        return CommonResponse.success(result);
    }

    @Override
    public CommonResponse getState(String businessId, String type) {
        CollectEntity isCollect = collectMapper.selectByUserIdAndBusinessIdAndType(UserContext.getId(), businessId, type);
        if (isCollect == null) {
            return CommonResponse.success(false);
        } else {
            return CommonResponse.success(true);
        }
    }

    @Override
    public CommonResponse pieChart() {
        List<CollectEntity> allEntity = collectMapper.findAll();
        CollectPieChartDto output = new CollectPieChartDto();
        output.setHotel(allEntity.stream().filter(c -> Objects.equals(c.getType(), "酒店住宿")).count());
        output.setScenicSpot(allEntity.stream().filter(c -> Objects.equals(c.getType(), "景点门票")).count());
        output.setTouristGuide(allEntity.stream().filter(c -> Objects.equals(c.getType(), "旅游攻略")).count());
        output.setTouristRoutes(allEntity.stream().filter(c -> Objects.equals(c.getType(), "旅游线路")).count());
        return CommonResponse.success(output);
    }

    public void deleteByType(String type) {
        collectMapper.deleteByType(type);
    }

    public PageResult<CollectEntity> selectByPage(int pageNum, int pageSize, String type) {
        int offset = (pageNum - 1) * pageSize;
        List<CollectEntity> list = collectMapper.selectByPageAndUserIdAndType(UserContext.getId(), type, offset, pageSize);
        int totalCount = collectMapper.getTotalCountByUserIdAndType(UserContext.getId(), type);

        int pages = (int) Math.ceil((double) totalCount / pageSize);

        PageResult<CollectEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotalCount(totalCount);
        result.setPages(pages);
        return result;
    }

}
