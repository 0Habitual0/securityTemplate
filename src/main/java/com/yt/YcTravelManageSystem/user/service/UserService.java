package com.yt.YcTravelManageSystem.user.service;

import com.yt.YcTravelManageSystem.common.entity.CommonResponse;
import com.yt.YcTravelManageSystem.user.entity.UserEntity;
import com.yt.YcTravelManageSystem.user.entity.dto.UserChangePasswordDto;
import com.yt.YcTravelManageSystem.user.entity.dto.UserPageDto;

/**
 * 业务层 用户
 */
public interface UserService {

    CommonResponse register(UserEntity input);

    CommonResponse recoverPassword(UserEntity input);

    CommonResponse changePassword(UserChangePasswordDto input);

    CommonResponse login(String username, String password, String role);

    CommonResponse info();

    CommonResponse logout();

    CommonResponse save(UserEntity input);

    CommonResponse delete(Long id, String role);

    CommonResponse selectByPage(UserPageDto input);

    CommonResponse userPersonal();

    CommonResponse dropDownList();

}
