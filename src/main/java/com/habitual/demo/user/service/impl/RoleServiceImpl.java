package com.habitual.demo.user.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.user.entity.RoleEntity;
import com.habitual.demo.user.entity.dto.RolePageDto;
import com.habitual.demo.user.repository.RoleRepository;
import com.habitual.demo.user.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

/**
 * 业务层实现 角色
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public CommonResponse save(RoleEntity input) {
        // 检查登录账号是否重复
        RoleEntity existingUser = roleRepository.findByRoleCode(input.getRoleCode());
        if (existingUser != null) {
            return CommonResponse.fail("角色编码已存在");
        }
        input.setId(null);
        roleRepository.save(input);
        return CommonResponse.success("新增成功");
    }

    @Override
    public CommonResponse delete(Long id) {
        roleRepository.deleteById(id);
        return CommonResponse.success("删除成功");
    }

    @Override
    public CommonResponse selectByPage(RolePageDto input) {
        Pageable pageable = PageRequest.of(input.getPageNum() - 1, input.getPageSize());
        Page<RoleEntity> result = roleRepository.findByCriteria(
                input.getRoleCode(),
                input.getRoleName(),
                input.getStatus(),
                input.getCreateBy(),
                input.getUpdateBy(),
                input.getRemark(),
                pageable);
        return CommonResponse.success(new PagedModel<>(result));
    }

}
