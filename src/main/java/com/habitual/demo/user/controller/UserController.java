package com.habitual.demo.user.controller;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.user.entity.UserEntity;
import com.habitual.demo.user.entity.dto.UserChangePasswordDto;
import com.habitual.demo.user.entity.dto.UserPageDto;
import com.habitual.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层 用户
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("register")
    public CommonResponse register(@RequestBody UserEntity userEntity) {
        return userService.register(userEntity);
    }

    /**
     * 登录
     */
    @PostMapping("login")
    public CommonResponse login(@RequestBody UserEntity userEntity) {
        return userService.login(userEntity.getUsername(), userEntity.getPassword());
    }

    /**
     * 修改密码
     */
    @PostMapping("changePassword")
    public CommonResponse changePassword(@RequestBody UserChangePasswordDto userEntity) {
        return userService.changePassword(userEntity);
    }

    /**
     * 找回密码
     */
    @PostMapping("recoverPassword")
    public CommonResponse recoverPassword(@RequestBody UserEntity userEntity) {
        return userService.recoverPassword(userEntity);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("info")
    public CommonResponse info() {
        return userService.info();
    }

    /**
     * 登出
     */
    @PostMapping("logout")
    public CommonResponse logout() {
        return userService.logout();
    }

    /**
     * 新增/修改
     */
    @PostMapping("save")
    public CommonResponse save(@RequestBody UserEntity input) {
        return userService.save(input);
    }

    /**
     * 删除
     */
    @GetMapping("delete")
    public CommonResponse delete(@RequestParam Long id) {
        return userService.delete(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("selectByPage")
    public CommonResponse selectByPage(@RequestBody UserPageDto input) {
        return userService.selectByPage(input);
    }

    /**
     * 个人中心页
     */
    @GetMapping("userPersonal")
    public CommonResponse userPersonal() {
        return userService.userPersonal();
    }

    /**
     * 下拉框
     */
    @GetMapping("dropDownList")
    public CommonResponse dropDownList() {
        return userService.dropDownList();
    }

}
