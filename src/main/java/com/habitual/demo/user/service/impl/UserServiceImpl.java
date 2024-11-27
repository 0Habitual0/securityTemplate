package com.habitual.demo.user.service.impl;

import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.entity.UserInfo;
import com.habitual.demo.common.security.context.UserContext;
import com.habitual.demo.common.utils.JwtTokenUtil;
import com.habitual.demo.user.entity.UserEntity;
import com.habitual.demo.user.entity.dto.UserChangePasswordDto;
import com.habitual.demo.user.entity.dto.UserDropDownListDto;
import com.habitual.demo.user.entity.dto.UserInfoDto;
import com.habitual.demo.user.entity.dto.UserPageDto;
import com.habitual.demo.user.repository.UserRepository;
import com.habitual.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 业务层实现 用户
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public CommonResponse register(UserEntity input) {
        // 检查登录账号是否重复
        UserEntity existingUser = userRepository.findByUsername(input.getUsername());
        if (existingUser != null) {
            return CommonResponse.fail("登录账号已存在");
        }
        input.setId(null);
        userRepository.save(input);
        return CommonResponse.success("注册成功");
    }

    @Override
    public CommonResponse recoverPassword(UserEntity input) {
        // 检查登录账号是否存在
        UserEntity existingUser = userRepository.findByUsername(input.getUsername());
        if (existingUser == null) {
            return CommonResponse.fail("登录账号不存在");
        }
        if (!Objects.equals(existingUser.getEmail(), input.getEmail())) {
            return CommonResponse.fail("邮箱不正确");
        }
        existingUser.setPassword(input.getPassword());
        userRepository.save(existingUser);
        jwtTokenUtil.deleteToken(existingUser.getUsername());
        return CommonResponse.success("修改成功");
    }

    @Override
    public CommonResponse changePassword(UserChangePasswordDto input) {
        // 检查登录账号是否重复
        UserEntity existingUser = userRepository.findByUsername(input.getUsername());
        if (existingUser == null) {
            return CommonResponse.fail("登录账号不存在");
        }
        if (!Objects.equals(existingUser.getPassword(), input.getOldPassword())) {
            return CommonResponse.fail("旧密码不正确");
        }
        existingUser.setPassword(input.getNewPassword());
        userRepository.save(existingUser);
        jwtTokenUtil.deleteToken(existingUser.getUsername());
        return CommonResponse.success("修改成功");
    }

    @Override
    public CommonResponse login(String username, String password) {
        // 检查登录账号密码是否正确
        UserEntity existingUser = userRepository.findByUsernameAndPassword(username, password);
        if (existingUser == null) {
            return CommonResponse.fail("用户名或密码错误");
        }
        if (Objects.equals(existingUser.getStatus(), 1L)) {
            return CommonResponse.fail("账户已被禁用，请联系管理员");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(existingUser.getId());
        userInfo.setUsername(existingUser.getUsername());
        userInfo.setNickname(existingUser.getNickname());
        return CommonResponse.success(jwtTokenUtil.getToken(userInfo));
    }

    @Override
    public CommonResponse info() {
        UserEntity userEntity = userRepository.findByUsername(UserContext.getUsername());
        UserInfoDto info = new UserInfoDto();
        info.setAvatar(userEntity.getAvatar());
        info.setName(userEntity.getUsername());
        info.setRoles(Collections.singletonList(userEntity.getRole()));
        return CommonResponse.success(info);
    }

    @Override
    public CommonResponse logout() {
        jwtTokenUtil.deleteToken(UserContext.getUsername());
        return CommonResponse.success("登出成功");
    }

    @Override
    public CommonResponse save(UserEntity input) {
        UserEntity existingUser = userRepository.findByUsername(input.getUsername());
        if (existingUser != null && !Objects.equals(existingUser.getId(), input.getId())) {
            return CommonResponse.fail("登录账号已存在");
        }
        if (input.getId() != null) {
            // 更新操作时不更新密码
            userRepository.findById(input.getId()).ifPresent(userToUpdate -> input.setPassword(userToUpdate.getPassword()));
            // 退出登录
            jwtTokenUtil.deleteToken(input.getUsername());
        }
        userRepository.save(input);
        return CommonResponse.success("保存成功");
    }

    @Override
    public CommonResponse delete(Long id) {
        userRepository.deleteById(id);
        return CommonResponse.success("删除成功");
    }

    @Override
    public CommonResponse selectByPage(UserPageDto input) {
        Pageable pageable = PageRequest.of(input.getPageNum() - 1, input.getPageSize());
        Page<UserEntity> result = userRepository.findByCriteria(
                input.getUsername(),
                input.getNickName(),
                input.getSex(),
                input.getAge(),
                input.getEmail(),
                input.getTel(),
                input.getRole(),
                input.getStatus(),
                input.getCreateBy(),
                input.getUpdateBy(),
                input.getRemark(),
                pageable);
        result.forEach(user -> user.setPassword(null));
        return CommonResponse.success(new PagedModel<>(result));
    }

    @Override
    public CommonResponse personalCenter() {
        UserEntity userEntity = userRepository.findByUsername(UserContext.getUsername());
        userEntity.setPassword(null);
        return CommonResponse.success(userEntity);
    }

    @Override
    public CommonResponse dropDownList() {
        List<UserEntity> healthDataEntityList = userRepository.findAll();
        List<UserDropDownListDto> userDTOList = healthDataEntityList.stream()
                .map(user -> new UserDropDownListDto(user.getId(), user.getUsername(), user.getNickname()))
                .toList();
        return CommonResponse.success(userDTOList);
    }

}
