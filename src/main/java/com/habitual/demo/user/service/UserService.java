package com.habitual.demo.user.service;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.user.entity.UserEntity;
import com.habitual.demo.user.entity.dto.UserChangePasswordDto;
import com.habitual.demo.user.entity.dto.UserPageDto;

/**
 * 业务层 用户
 */
public interface UserService {

    CommonResponse register(UserEntity input);

    CommonResponse recoverPassword(UserEntity input);

    CommonResponse changePassword(UserChangePasswordDto input);

    CommonResponse login(String username, String password);

    CommonResponse info();

    CommonResponse logout();

    CommonResponse save(UserEntity input);

    CommonResponse delete(Long id);

    CommonResponse selectByPage(UserPageDto input);

    CommonResponse personalCenter();

    CommonResponse dropDownList();

}
