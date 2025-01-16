package com.yt.YcTravelManageSystem.collect.service;

import com.yt.YcTravelManageSystem.collect.entity.CollectEntity;
import com.yt.YcTravelManageSystem.common.entity.CommonResponse;

/**
 * 业务层 收藏
 */
public interface CollectService {

    CommonResponse save(CollectEntity input);

    CommonResponse getState(String businessId, String type);

    CommonResponse pieChart();

}
