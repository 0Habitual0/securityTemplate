package com.habitual.demo.user.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.user.entity.RoleEntity;
import com.habitual.demo.user.entity.dto.RolePageDto;

/**
 * 业务层 角色
 */
public interface RoleService {

    CommonResponse save(RoleEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(RolePageDto input);

}
