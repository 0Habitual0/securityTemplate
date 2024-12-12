package com.habitual.demo.collect.service;

import com.habitual.demo.collect.entity.CollectEntity;
import com.habitual.demo.common.entity.CommonResponse;

/**
 * 业务层 收藏
 */
public interface CollectService {

    CommonResponse save(CollectEntity input);

    CommonResponse getState(String businessId, String type);

    CommonResponse pieChart();

}
