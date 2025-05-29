package com.habitual.demo.user.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.user.entity.RoleEntity;
import com.habitual.demo.user.entity.dto.RolePageDto;
import com.habitual.demo.user.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 角色
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody RoleEntity input) {
        return roleService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return roleService.delete(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody RolePageDto input) {
        return roleService.selectByPage(input);
    }

}
